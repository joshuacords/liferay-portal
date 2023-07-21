/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.index;

import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.index.IndicesOptions;
import com.liferay.portal.search.engine.adapter.index.OpenIndexRequest;
import com.liferay.portal.search.engine.adapter.index.OpenIndexResponse;

import org.elasticsearch.action.admin.indices.open.OpenIndexAction;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequestBuilder;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.common.unit.TimeValue;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = OpenIndexRequestExecutor.class)
public class OpenIndexRequestExecutorImpl implements OpenIndexRequestExecutor {

	@Override
	public OpenIndexResponse execute(OpenIndexRequest openIndexRequest) {
		OpenIndexRequestBuilder openIndexRequestBuilder =
			createOpenIndexRequestBuilder(openIndexRequest);

		AcknowledgedResponse acknowledgedResponse =
			openIndexRequestBuilder.get();

		return new OpenIndexResponse(acknowledgedResponse.isAcknowledged());
	}

	protected OpenIndexRequestBuilder createOpenIndexRequestBuilder(
		OpenIndexRequest openIndexRequest) {

		OpenIndexRequestBuilder openIndexRequestBuilder =
			new OpenIndexRequestBuilder(
				_elasticsearchClientResolver.getClient(false),
				OpenIndexAction.INSTANCE);

		openIndexRequestBuilder.setIndices(openIndexRequest.getIndexNames());

		IndicesOptions indicesOptions = openIndexRequest.getIndicesOptions();

		if (indicesOptions != null) {
			openIndexRequestBuilder.setIndicesOptions(
				_indicesOptionsTranslator.translate(indicesOptions));
		}

		if (openIndexRequest.getTimeout() > 0) {
			TimeValue timeValue = TimeValue.timeValueMillis(
				openIndexRequest.getTimeout());

			openIndexRequestBuilder.setMasterNodeTimeout(timeValue);
			openIndexRequestBuilder.setTimeout(timeValue);
		}

		if (openIndexRequest.getWaitForActiveShards() > 0) {
			openIndexRequestBuilder.setWaitForActiveShards(
				openIndexRequest.getWaitForActiveShards());
		}

		return openIndexRequestBuilder;
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