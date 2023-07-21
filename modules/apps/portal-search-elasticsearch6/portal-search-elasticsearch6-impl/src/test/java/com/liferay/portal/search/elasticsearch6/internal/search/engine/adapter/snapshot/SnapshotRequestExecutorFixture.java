/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.snapshot;

import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.snapshot.SnapshotRequestExecutor;

/**
 * @author Michael C. Han
 */
public class SnapshotRequestExecutorFixture {

	public SnapshotRequestExecutor getSnapshotRequestExecutor() {
		return _snapshotRequestExecutor;
	}

	public void setUp() {
		_snapshotRequestExecutor = new ElasticsearchSnapshotRequestExecutor() {
			{
				createSnapshotRepositoryRequestExecutor =
					createCreateSnapshotRepositoryRequestExecutor(
						_elasticsearchClientResolver);
				createSnapshotRequestExecutor =
					createCreateSnapshotRequestExecutor(
						_elasticsearchClientResolver);
				deleteSnapshotRequestExecutor =
					createDeleteSnapshotRequestExecutor(
						_elasticsearchClientResolver);
				getSnapshotRepositoriesRequestExecutor =
					createGetSnapshotRepositoriesRequestExecutor(
						_elasticsearchClientResolver);
				getSnapshotsRequestExecutor = createGetSnapshotsRequestExecutor(
					_elasticsearchClientResolver);
				restoreSnapshotRequestExecutor =
					createRestoreSnapshotRequestExecutor(
						_elasticsearchClientResolver);
			}
		};
	}

	protected static CreateSnapshotRepositoryRequestExecutor
		createCreateSnapshotRepositoryRequestExecutor(
			ElasticsearchClientResolver elasticsearchClientResolver) {

		return new CreateSnapshotRepositoryRequestExecutorImpl() {
			{
				setElasticsearchClientResolver(elasticsearchClientResolver);
			}
		};
	}

	protected static CreateSnapshotRequestExecutor
		createCreateSnapshotRequestExecutor(
			ElasticsearchClientResolver elasticsearchClientResolver) {

		return new CreateSnapshotRequestExecutorImpl() {
			{
				setElasticsearchClientResolver(elasticsearchClientResolver);
			}
		};
	}

	protected static DeleteSnapshotRequestExecutor
		createDeleteSnapshotRequestExecutor(
			ElasticsearchClientResolver elasticsearchClientResolver) {

		return new DeleteSnapshotRequestExecutorImpl() {
			{
				setElasticsearchClientResolver(elasticsearchClientResolver);
			}
		};
	}

	protected static GetSnapshotRepositoriesRequestExecutor
		createGetSnapshotRepositoriesRequestExecutor(
			ElasticsearchClientResolver elasticsearchClientResolver) {

		return new GetSnapshotRepositoriesRequestExecutorImpl() {
			{
				setElasticsearchClientResolver(elasticsearchClientResolver);
			}
		};
	}

	protected static GetSnapshotsRequestExecutor
		createGetSnapshotsRequestExecutor(
			ElasticsearchClientResolver elasticsearchClientResolver) {

		return new GetSnapshotsRequestExecutorImpl() {
			{
				setElasticsearchClientResolver(elasticsearchClientResolver);
			}
		};
	}

	protected static RestoreSnapshotRequestExecutor
		createRestoreSnapshotRequestExecutor(
			ElasticsearchClientResolver elasticsearchClientResolver) {

		return new RestoreSnapshotRequestExecutorImpl() {
			{
				setElasticsearchClientResolver(elasticsearchClientResolver);
			}
		};
	}

	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;
	private SnapshotRequestExecutor _snapshotRequestExecutor;

}