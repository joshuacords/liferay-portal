/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.index;

import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.index.IndexInformation;
import com.liferay.portal.search.index.IndexNameBuilder;

import org.elasticsearch.action.admin.indices.get.GetIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequestBuilder;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.Strings;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adam Brandizzi
 */
@Component(immediate = true, service = IndexInformation.class)
public class ElasticsearchIndexInformation implements IndexInformation {

	@Override
	public String getCompanyIndexName(long companyId) {
		return indexNameBuilder.getIndexName(companyId);
	}

	@Override
	public String getFieldMappings(String indexName) {
		IndicesAdminClient indicesAdminClient = getIndicesAdminClient();

		GetMappingsRequestBuilder getMappingsRequestBuilder =
			indicesAdminClient.prepareGetMappings(indexName);

		return Strings.toString(getMappingsRequestBuilder.get(), true, true);
	}

	@Override
	public String[] getIndexNames() {
		IndicesAdminClient indicesAdminClient = getIndicesAdminClient();

		GetIndexRequestBuilder getIndexRequestBuilder =
			indicesAdminClient.prepareGetIndex();

		GetIndexResponse getIndexResponse = getIndexRequestBuilder.get();

		return getIndexResponse.getIndices();
	}

	protected IndicesAdminClient getIndicesAdminClient() {
		Client client = elasticsearchClientResolver.getClient(true);

		AdminClient adminClient = client.admin();

		return adminClient.indices();
	}

	@Reference
	protected ElasticsearchClientResolver elasticsearchClientResolver;

	@Reference
	protected IndexNameBuilder indexNameBuilder;

}