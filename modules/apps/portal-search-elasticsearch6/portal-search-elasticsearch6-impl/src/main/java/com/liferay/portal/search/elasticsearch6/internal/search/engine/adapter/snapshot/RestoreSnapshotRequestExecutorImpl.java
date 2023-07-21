/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.snapshot;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.snapshot.RestoreSnapshotRequest;
import com.liferay.portal.search.engine.adapter.snapshot.RestoreSnapshotResponse;

import java.util.List;

import org.elasticsearch.action.admin.cluster.snapshots.restore.RestoreSnapshotAction;
import org.elasticsearch.action.admin.cluster.snapshots.restore.RestoreSnapshotRequestBuilder;
import org.elasticsearch.snapshots.RestoreInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = RestoreSnapshotRequestExecutor.class)
public class RestoreSnapshotRequestExecutorImpl
	implements RestoreSnapshotRequestExecutor {

	@Override
	public RestoreSnapshotResponse execute(
		RestoreSnapshotRequest restoreSnapshotRequest) {

		RestoreSnapshotRequestBuilder restoreSnapshotRequestBuilder =
			createRestoreSnapshotRequestBuilder(restoreSnapshotRequest);

		org.elasticsearch.action.admin.cluster.snapshots.restore.
			RestoreSnapshotResponse elasticsearchRestoreSnapshotResponse =
				restoreSnapshotRequestBuilder.get();

		RestoreInfo restoreInfo =
			elasticsearchRestoreSnapshotResponse.getRestoreInfo();

		List<String> indexNames = restoreInfo.indices();

		return new RestoreSnapshotResponse(
			restoreInfo.name(), indexNames.toArray(new String[0]),
			restoreInfo.totalShards(), restoreInfo.failedShards());
	}

	protected RestoreSnapshotRequestBuilder createRestoreSnapshotRequestBuilder(
		RestoreSnapshotRequest restoreSnapshotRequest) {

		RestoreSnapshotRequestBuilder restoreSnapshotRequestBuilder =
			RestoreSnapshotAction.INSTANCE.newRequestBuilder(
				_elasticsearchClientResolver.getClient(false));

		restoreSnapshotRequestBuilder.setIncludeAliases(
			restoreSnapshotRequest.isIncludeAliases());
		restoreSnapshotRequestBuilder.setIndices(
			restoreSnapshotRequest.getIndexNames());
		restoreSnapshotRequestBuilder.setPartial(
			restoreSnapshotRequest.isPartialRestore());

		if (Validator.isNotNull(
				restoreSnapshotRequest.getRenameReplacement())) {

			restoreSnapshotRequestBuilder.setRenameReplacement(
				restoreSnapshotRequest.getRenameReplacement());
		}

		if (Validator.isNotNull(restoreSnapshotRequest.getRenamePattern())) {
			restoreSnapshotRequestBuilder.setRenamePattern(
				restoreSnapshotRequest.getRenamePattern());
		}

		restoreSnapshotRequestBuilder.setRepository(
			restoreSnapshotRequest.getRepositoryName());
		restoreSnapshotRequestBuilder.setRestoreGlobalState(
			restoreSnapshotRequest.isRestoreGlobalState());
		restoreSnapshotRequestBuilder.setSnapshot(
			restoreSnapshotRequest.getSnapshotName());
		restoreSnapshotRequestBuilder.setWaitForCompletion(
			restoreSnapshotRequest.isWaitForCompletion());

		return restoreSnapshotRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;

}