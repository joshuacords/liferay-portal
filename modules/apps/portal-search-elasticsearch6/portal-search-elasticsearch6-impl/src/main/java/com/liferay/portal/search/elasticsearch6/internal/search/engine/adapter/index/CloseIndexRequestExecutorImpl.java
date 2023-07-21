/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.index;

import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.index.CloseIndexRequest;
import com.liferay.portal.search.engine.adapter.index.CloseIndexResponse;
import com.liferay.portal.search.engine.adapter.index.IndicesOptions;

import org.elasticsearch.action.admin.indices.close.CloseIndexAction;
import org.elasticsearch.action.admin.indices.close.CloseIndexRequestBuilder;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.common.unit.TimeValue;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = CloseIndexRequestExecutor.class)
public class CloseIndexRequestExecutorImpl
	implements CloseIndexRequestExecutor {

	@Override
	public CloseIndexResponse execute(CloseIndexRequest closeIndexRequest) {
		CloseIndexRequestBuilder closeIndexRequestBuilder =
			createCloseIndexRequestBuilder(closeIndexRequest);

		AcknowledgedResponse acknowledgedResponse =
			closeIndexRequestBuilder.get();

		return new CloseIndexResponse(acknowledgedResponse.isAcknowledged());
	}

	protected CloseIndexRequestBuilder createCloseIndexRequestBuilder(
		CloseIndexRequest closeIndexRequest) {

		CloseIndexRequestBuilder closeIndexRequestBuilder =
			CloseIndexAction.INSTANCE.newRequestBuilder(
				_elasticsearchClientResolver.getClient(false));

		closeIndexRequestBuilder.setIndices(closeIndexRequest.getIndexNames());

		IndicesOptions indicesOptions = closeIndexRequest.getIndicesOptions();

		if (indicesOptions != null) {
			closeIndexRequestBuilder.setIndicesOptions(
				_indicesOptionsTranslator.translate(indicesOptions));
		}

		if (closeIndexRequest.getTimeout() > 0) {
			TimeValue timeValue = TimeValue.timeValueMillis(
				closeIndexRequest.getTimeout());

			closeIndexRequestBuilder.setMasterNodeTimeout(timeValue);
			closeIndexRequestBuilder.setTimeout(timeValue);
		}

		return closeIndexRequestBuilder;
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