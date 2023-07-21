/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.search.engine.adapter.snapshot;

import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchClientResolver;
import com.liferay.portal.search.engine.adapter.snapshot.CreateSnapshotRepositoryRequest;
import com.liferay.portal.search.engine.adapter.snapshot.CreateSnapshotRepositoryResponse;

import org.elasticsearch.action.admin.cluster.repositories.put.PutRepositoryAction;
import org.elasticsearch.action.admin.cluster.repositories.put.PutRepositoryRequestBuilder;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.repositories.fs.FsRepository;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(service = CreateSnapshotRepositoryRequestExecutor.class)
public class CreateSnapshotRepositoryRequestExecutorImpl
	implements CreateSnapshotRepositoryRequestExecutor {

	@Override
	public CreateSnapshotRepositoryResponse execute(
		CreateSnapshotRepositoryRequest createSnapshotRepositoryRequest) {

		PutRepositoryRequestBuilder putRepositoryRequestBuilder =
			createPutRepositoryRequestBuilder(createSnapshotRepositoryRequest);

		AcknowledgedResponse acknowledgedResponse =
			putRepositoryRequestBuilder.get();

		return new CreateSnapshotRepositoryResponse(
			acknowledgedResponse.isAcknowledged());
	}

	protected PutRepositoryRequestBuilder createPutRepositoryRequestBuilder(
		CreateSnapshotRepositoryRequest createSnapshotRepositoryRequest) {

		PutRepositoryRequestBuilder putRepositoryRequestBuilder =
			PutRepositoryAction.INSTANCE.newRequestBuilder(
				_elasticsearchClientResolver.getClient(false));

		putRepositoryRequestBuilder.setName(
			createSnapshotRepositoryRequest.getName());

		Settings.Builder builder = Settings.builder();

		builder.put(
			FsRepository.COMPRESS_SETTING.getKey(),
			createSnapshotRepositoryRequest.isCompress());

		builder.put(
			FsRepository.LOCATION_SETTING.getKey(),
			createSnapshotRepositoryRequest.getLocation());

		putRepositoryRequestBuilder.setSettings(builder);

		putRepositoryRequestBuilder.setType(
			createSnapshotRepositoryRequest.getType());
		putRepositoryRequestBuilder.setVerify(
			createSnapshotRepositoryRequest.isVerify());

		return putRepositoryRequestBuilder;
	}

	@Reference(unbind = "-")
	protected void setElasticsearchClientResolver(
		ElasticsearchClientResolver elasticsearchClientResolver) {

		_elasticsearchClientResolver = elasticsearchClientResolver;
	}

	private ElasticsearchClientResolver _elasticsearchClientResolver;

}