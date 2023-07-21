/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.snapshot;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.snapshot.CreateSnapshotRequest;
import com.liferay.portal.search.engine.adapter.snapshot.CreateSnapshotResponse;
import com.liferay.portal.search.engine.adapter.snapshot.SnapshotDetails;

import org.elasticsearch.action.admin.cluster.snapshots.create.CreateSnapshotAction;
import org.elasticsearch.action.admin.cluster.snapshots.create.CreateSnapshotRequestBuilder;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = CreateSnapshotRequestExecutor.class)
public class CreateSnapshotRequestExecutorImpl
	implements CreateSnapshotRequestExecutor {

	@Override
	public CreateSnapshotResponse execute(
		CreateSnapshotRequest createSnapshotRequest) {

		CreateSnapshotRequestBuilder createSnapshotRequestBuilder =
			createCreateSnapshotRequestBuilder(createSnapshotRequest);

		org.elasticsearch.action.admin.cluster.snapshots.create.
			CreateSnapshotResponse elasticsearchCreateSnapshotResponse =
				createSnapshotRequestBuilder.get();

		SnapshotDetails snapshotDetails = SnapshotInfoConverter.convert(
			elasticsearchCreateSnapshotResponse.getSnapshotInfo());

		return new CreateSnapshotResponse(snapshotDetails);
	}

	protected CreateSnapshotRequestBuilder createCreateSnapshotRequestBuilder(
		CreateSnapshotRequest createSnapshotRequest) {

		CreateSnapshotRequestBuilder createSnapshotRequestBuilder =
			new CreateSnapshotRequestBuilder(
				_elasticsearchClientResolver.getClient(false),
				CreateSnapshotAction.INSTANCE);

		if (ArrayUtil.isNotEmpty(createSnapshotRequest.getIndexNames())) {
			createSnapshotRequestBuilder.setIndices(
				createSnapshotRequest.getIndexNames());
		}

		createSnapshotRequestBuilder.setRepository(
			createSnapshotRequest.getRepositoryName());
		createSnapshotRequestBuilder.setSnapshot(
			createSnapshotRequest.getSnapshotName());
		createSnapshotRequestBuilder.setWaitForCompletion(
			createSnapshotRequest.isWaitForCompletion());

		return createSnapshotRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;

}