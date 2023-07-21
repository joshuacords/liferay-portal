/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.snapshot;

import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.snapshot.GetSnapshotsRequest;
import com.liferay.portal.search.engine.adapter.snapshot.GetSnapshotsResponse;
import com.liferay.portal.search.engine.adapter.snapshot.SnapshotDetails;

import java.util.List;

import org.elasticsearch.action.admin.cluster.snapshots.get.GetSnapshotsAction;
import org.elasticsearch.action.admin.cluster.snapshots.get.GetSnapshotsRequestBuilder;
import org.elasticsearch.snapshots.SnapshotInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = GetSnapshotsRequestExecutor.class)
public class GetSnapshotsRequestExecutorImpl
	implements GetSnapshotsRequestExecutor {

	@Override
	public GetSnapshotsResponse execute(
		GetSnapshotsRequest getSnapshotsRequest) {

		GetSnapshotsRequestBuilder getSnapshotsRequestBuilder =
			createGetSnapshotsRequest(getSnapshotsRequest);

		org.elasticsearch.action.admin.cluster.snapshots.get.
			GetSnapshotsResponse elasticsearchGetSnapshotsResponse =
				getSnapshotsRequestBuilder.get();

		GetSnapshotsResponse getSnapshotsResponse = new GetSnapshotsResponse();

		List<SnapshotInfo> snapshotInfos =
			elasticsearchGetSnapshotsResponse.getSnapshots();

		snapshotInfos.forEach(
			snapshotInfo -> {
				SnapshotDetails snapshotDetails = SnapshotInfoConverter.convert(
					snapshotInfo);

				getSnapshotsResponse.addSnapshotInfo(snapshotDetails);
			});

		return getSnapshotsResponse;
	}

	protected GetSnapshotsRequestBuilder createGetSnapshotsRequest(
		GetSnapshotsRequest getSnapshotsRequest) {

		GetSnapshotsRequestBuilder getSnapshotsRequestBuilder =
			GetSnapshotsAction.INSTANCE.newRequestBuilder(
				_elasticsearchClientResolver.getClient(false));

		getSnapshotsRequestBuilder.setIgnoreUnavailable(
			getSnapshotsRequest.isIgnoreUnavailable());
		getSnapshotsRequestBuilder.setRepository(
			getSnapshotsRequest.getRepositoryName());
		getSnapshotsRequestBuilder.setSnapshots(
			getSnapshotsRequest.getSnapshotNames());
		getSnapshotsRequestBuilder.setVerbose(getSnapshotsRequest.isVerbose());

		return getSnapshotsRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;

}