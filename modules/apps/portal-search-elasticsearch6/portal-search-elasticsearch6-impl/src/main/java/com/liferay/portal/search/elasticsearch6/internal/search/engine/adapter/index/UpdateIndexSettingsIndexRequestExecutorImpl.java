/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.index;

import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.index.IndicesOptions;
import com.liferay.portal.search.engine.adapter.index.UpdateIndexSettingsIndexRequest;
import com.liferay.portal.search.engine.adapter.index.UpdateIndexSettingsIndexResponse;

import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsAction;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequestBuilder;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.common.xcontent.XContentType;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = UpdateIndexSettingsIndexRequestExecutor.class)
public class UpdateIndexSettingsIndexRequestExecutorImpl
	implements UpdateIndexSettingsIndexRequestExecutor {

	@Override
	public UpdateIndexSettingsIndexResponse execute(
		UpdateIndexSettingsIndexRequest updateIndexSettingsIndexRequest) {

		UpdateSettingsRequestBuilder updateSettingsRequestBuilder =
			createUpdateSettingsRequestBuilder(updateIndexSettingsIndexRequest);

		AcknowledgedResponse acknowledgedResponse =
			updateSettingsRequestBuilder.get();

		return new UpdateIndexSettingsIndexResponse(
			acknowledgedResponse.isAcknowledged());
	}

	protected UpdateSettingsRequestBuilder createUpdateSettingsRequestBuilder(
		UpdateIndexSettingsIndexRequest updateIndexSettingsIndexRequest) {

		UpdateSettingsRequestBuilder updateSettingsRequestBuilder =
			UpdateSettingsAction.INSTANCE.newRequestBuilder(
				_elasticsearchClientResolver.getClient(false));

		updateSettingsRequestBuilder.setIndices(
			updateIndexSettingsIndexRequest.getIndexNames());

		updateSettingsRequestBuilder.setSettings(
			updateIndexSettingsIndexRequest.getSettings(), XContentType.JSON);

		IndicesOptions indicesOptions =
			updateIndexSettingsIndexRequest.getIndicesOptions();

		if (indicesOptions != null) {
			updateSettingsRequestBuilder.setIndicesOptions(
				_indicesOptionsTranslator.translate(indicesOptions));
		}

		return updateSettingsRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	@Reference(unbind = "-")
	protected void setIndicesOptionsTranslator(
		IndicesOptionsTranslator indicesOptionsTranslator) {

		_indicesOptionsTranslator = indicesOptionsTranslator;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;
	private IndicesOptionsTranslator _indicesOptionsTranslator;

}