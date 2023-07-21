/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.index;

import com.liferay.portal.search.test.util.IdempotentRetryAssert;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.admin.indices.mapping.get.GetFieldMappingsRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.get.GetFieldMappingsResponse;
import org.elasticsearch.action.admin.indices.mapping.get.GetFieldMappingsResponse.FieldMappingMetadata;
import org.elasticsearch.client.IndicesAdminClient;

import org.junit.Assert;

/**
 * @author Artur Aquino
 * @author André de Oliveira
 */
public class FieldMappingAssert {

	public static void assertAnalyzer(
			String expectedValue, String field, String type, String index,
			IndicesAdminClient indicesAdminClient)
		throws Exception {

		assertFieldMappingMetadata(
			expectedValue, "analyzer", field, type, index, indicesAdminClient);
	}

	public static void assertFieldMappingMetadata(
			final String expectedValue, final String key, final String field,
			final String type, final String index,
			final IndicesAdminClient indicesAdminClient)
		throws Exception {

		IdempotentRetryAssert.retryAssert(
			10, TimeUnit.SECONDS,
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					doAssertFieldMappingMetadata(
						expectedValue, key, field, type, index,
						indicesAdminClient);

					return null;
				}

			});
	}

	public static void assertType(
			String expectedValue, String field, String type, String index,
			IndicesAdminClient indicesAdminClient)
		throws Exception {

		assertFieldMappingMetadata(
			expectedValue, "type", field, type, index, indicesAdminClient);
	}

	protected static void doAssertFieldMappingMetadata(
		String expectedValue, String key, String field, String type,
		String index, IndicesAdminClient indicesAdminClient) {

		FieldMappingMetadata fieldMappingMetadata = getFieldMapping(
			field, type, index, indicesAdminClient);

		String value = getFieldMappingMetadataValue(
			fieldMappingMetadata, field, key);

		Assert.assertEquals(expectedValue, value);
	}

	protected static FieldMappingMetadata getFieldMapping(
		String field, String type, String index,
		IndicesAdminClient indicesAdminClient) {

		GetFieldMappingsRequestBuilder getFieldMappingsRequestBuilder =
			indicesAdminClient.prepareGetFieldMappings(index);

		getFieldMappingsRequestBuilder.setFields(field);
		getFieldMappingsRequestBuilder.setTypes(type);

		GetFieldMappingsResponse getFieldMappingsResponse =
			getFieldMappingsRequestBuilder.get();

		return getFieldMappingsResponse.fieldMappings(index, type, field);
	}

	@SuppressWarnings("unchecked")
	protected static String getFieldMappingMetadataValue(
		FieldMappingMetadata fieldMappingMetadata, String field, String key) {

		Map<String, Object> mappings = fieldMappingMetadata.sourceAsMap();

		Map<String, Object> mapping = (Map<String, Object>)mappings.get(field);

		return (String)mapping.get(key);
	}

}