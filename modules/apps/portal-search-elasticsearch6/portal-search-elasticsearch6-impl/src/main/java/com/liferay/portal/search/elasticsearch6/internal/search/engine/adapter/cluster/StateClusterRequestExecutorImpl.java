/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.cluster;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.cluster.StateClusterRequest;
import com.liferay.portal.search.engine.adapter.cluster.StateClusterResponse;

import java.io.IOException;

import org.elasticsearch.action.admin.cluster.state.ClusterStateAction;
import org.elasticsearch.action.admin.cluster.state.ClusterStateRequestBuilder;
import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
 */
@Component(immediate = true, service = StateClusterRequestExecutor.class)
public class StateClusterRequestExecutorImpl
	implements StateClusterRequestExecutor {

	@Override
	public StateClusterResponse execute(
		StateClusterRequest stateClusterRequest) {

		ClusterStateRequestBuilder clusterStateRequestBuilder =
			createClusterStateRequestBuilder(stateClusterRequest);

		ClusterStateResponse clusterStateResponse =
			clusterStateRequestBuilder.get();

		try {
			ClusterState clusterState = clusterStateResponse.getState();

			XContentBuilder xContentBuilder = XContentFactory.jsonBuilder();

			xContentBuilder.startObject();

			xContentBuilder = clusterState.toXContent(
				xContentBuilder, ToXContent.EMPTY_PARAMS);

			xContentBuilder.endObject();

			return new StateClusterResponse(Strings.toString(xContentBuilder));
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	protected ClusterStateRequestBuilder createClusterStateRequestBuilder(
		StateClusterRequest stateClusterRequest) {

		ClusterStateRequestBuilder clusterStateRequestBuilder =
			ClusterStateAction.INSTANCE.newRequestBuilder(
				_elasticsearchClientResolver.getClient(true));

		clusterStateRequestBuilder.setIndices(
			stateClusterRequest.getIndexNames());

		return clusterStateRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;

}