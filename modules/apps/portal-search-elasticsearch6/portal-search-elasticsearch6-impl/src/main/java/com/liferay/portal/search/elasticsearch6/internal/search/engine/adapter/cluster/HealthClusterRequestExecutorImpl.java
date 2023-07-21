/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.cluster;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.cluster.HealthClusterRequest;
import com.liferay.portal.search.engine.adapter.cluster.HealthClusterResponse;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthAction;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequestBuilder;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.common.unit.TimeValue;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
 */
@Component(immediate = true, service = HealthClusterRequestExecutor.class)
public class HealthClusterRequestExecutorImpl
	implements HealthClusterRequestExecutor {

	@Override
	public HealthClusterResponse execute(
		HealthClusterRequest healthClusterRequest) {

		ClusterHealthRequestBuilder clusterHealthRequestBuilder =
			createClusterHealthRequestBuilder(healthClusterRequest);

		ClusterHealthResponse clusterHealthResponse =
			clusterHealthRequestBuilder.get();

		ClusterHealthStatus clusterHealthStatus =
			clusterHealthResponse.getStatus();

		return new HealthClusterResponse(
			_clusterHealthStatusTranslator.translate(clusterHealthStatus),
			clusterHealthResponse.toString());
	}

	protected ClusterHealthRequestBuilder createClusterHealthRequestBuilder(
		HealthClusterRequest healthClusterRequest) {

		ClusterHealthRequestBuilder clusterHealthRequestBuilder =
			ClusterHealthAction.INSTANCE.newRequestBuilder(
				_elasticsearchClientResolver.getClient(true));

		if (ArrayUtil.isNotEmpty(healthClusterRequest.getIndexNames())) {
			clusterHealthRequestBuilder.setIndices(
				healthClusterRequest.getIndexNames());
		}

		long timeout = healthClusterRequest.getTimeout();

		if (timeout > 0) {
			clusterHealthRequestBuilder.setMasterNodeTimeout(
				TimeValue.timeValueMillis(timeout));
			clusterHealthRequestBuilder.setTimeout(
				TimeValue.timeValueMillis(timeout));
		}

		if (healthClusterRequest.getWaitForClusterHealthStatus() != null) {
			clusterHealthRequestBuilder.setWaitForStatus(
				_clusterHealthStatusTranslator.translate(
					healthClusterRequest.getWaitForClusterHealthStatus()));
		}

		return clusterHealthRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setClusterHealthStatusTranslator(
		ClusterHealthStatusTranslator clusterHealthStatusTranslator) {

		_clusterHealthStatusTranslator = clusterHealthStatusTranslator;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private ClusterHealthStatusTranslator _clusterHealthStatusTranslator;
	private ElasticsearchClientResolver _elasticsearchClientResolver;

}