/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.cluster;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.elasticsearch6.internal.util.LogUtil;

import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequestBuilder;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.settings.Settings;

/**
 * @author André de Oliveira
 */
public class ReplicasManagerImpl implements ReplicasManager {

	public ReplicasManagerImpl(IndicesAdminClient indicesAdminClient) {
		_indicesAdminClient = indicesAdminClient;
	}

	@Override
	public void updateNumberOfReplicas(
		int numberOfReplicas, String... indices) {

		UpdateSettingsRequestBuilder updateSettingsRequestBuilder =
			_indicesAdminClient.prepareUpdateSettings(indices);

		Settings.Builder builder = Settings.builder();

		builder.put("number_of_replicas", numberOfReplicas);

		updateSettingsRequestBuilder.setSettings(builder);

		try {
			ActionResponse actionResponse = updateSettingsRequestBuilder.get();

			LogUtil.logActionResponse(_log, actionResponse);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to update number of replicas", exception);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ReplicasManagerImpl.class);

	private final IndicesAdminClient _indicesAdminClient;

}