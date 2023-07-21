/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch7.internal.search.engine.adapter.snapshot;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.elasticsearch7.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.snapshot.GetSnapshotRepositoriesRequest;
import com.liferay.portal.search.engine.adapter.snapshot.GetSnapshotRepositoriesResponse;
import com.liferay.portal.search.engine.adapter.snapshot.SnapshotRepositoryDetails;

import java.util.List;

import org.elasticsearch.action.admin.cluster.repositories.get.GetRepositoriesAction;
import org.elasticsearch.action.admin.cluster.repositories.get.GetRepositoriesRequestBuilder;
import org.elasticsearch.action.admin.cluster.repositories.get.GetRepositoriesResponse;
import org.elasticsearch.cluster.metadata.RepositoryMetadata;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.repositories.RepositoryMissingException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = GetSnapshotRepositoriesRequestExecutor.class)
public class GetSnapshotRepositoriesRequestExecutorImpl
	implements GetSnapshotRepositoriesRequestExecutor {

	@Override
	public GetSnapshotRepositoriesResponse execute(
		GetSnapshotRepositoriesRequest getSnapshotRepositoriesRequest) {

		GetRepositoriesRequestBuilder getRepositoriesRequestBuilder =
			createGetRepositoriesRequestBuilder(getSnapshotRepositoriesRequest);

		GetSnapshotRepositoriesResponse getSnapshotRepositoriesResponse =
			new GetSnapshotRepositoriesResponse();

		try {
			GetRepositoriesResponse elasticsearchGetRepositoriesResponse =
				getRepositoriesRequestBuilder.get();

			List<RepositoryMetadata> repositoriesMetadatas =
				elasticsearchGetRepositoriesResponse.repositories();

			repositoriesMetadatas.forEach(
				repositoryMetadata -> {
					Settings repositoryMetadataSettings =
						repositoryMetadata.settings();

					SnapshotRepositoryDetails snapshotRepositoryDetails =
						new SnapshotRepositoryDetails(
							repositoryMetadata.name(),
							repositoryMetadata.type(),
							repositoryMetadataSettings.toString());

					getSnapshotRepositoriesResponse.
						addSnapshotRepositoryMetadata(
							snapshotRepositoryDetails);
				});
		}
		catch (RepositoryMissingException repositoryMissingException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					repositoryMissingException, repositoryMissingException);
			}
		}

		return getSnapshotRepositoriesResponse;
	}

	protected GetRepositoriesRequestBuilder createGetRepositoriesRequestBuilder(
		GetSnapshotRepositoriesRequest getSnapshotRepositoriesRequest) {

		GetRepositoriesRequestBuilder getRepositoriesRequestBuilder =
			new GetRepositoriesRequestBuilder(
				_elasticsearchClientResolver.getClient(false),
				GetRepositoriesAction.INSTANCE);

		getRepositoriesRequestBuilder.addRepositories(
			getSnapshotRepositoriesRequest.getRepositoryNames());

		return getRepositoriesRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		GetSnapshotRepositoriesRequestExecutorImpl.class);

	private ElasticsearchClientResolver _elasticsearchClientResolver;

}