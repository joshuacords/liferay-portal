/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.tuning.rankings.web.internal.index.creation.activator;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.uuid.PortalUUID;
import com.liferay.portal.search.engine.SearchEngineInformation;
import com.liferay.portal.search.tuning.rankings.web.internal.background.task.RankingIndexCreationBackgroundTaskExecutor;

import java.util.HashMap;
import java.util.Objects;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Wade Cao
 * @author Adam Brandizzi
 */
@Component(
	immediate = true, service = RankingIndexCreationBundleActivator.class
)
public class RankingIndexCreationBundleActivator {

	@Activate
	protected void activate() {
		if (Objects.equals(searchEngineInformation.getVendorString(), "Solr")) {
			return;
		}

		_addBackgroundTask();
	}

	@Reference
	protected SearchEngineInformation searchEngineInformation;

	private void _addBackgroundTask() {
		try {
			_backgroundTaskManager.addBackgroundTask(
				UserConstants.USER_ID_DEFAULT, CompanyConstants.SYSTEM,
				"createRankingIndex-" + _portalUUID.generate(),
				RankingIndexCreationBackgroundTaskExecutor.class.getName(),
				new HashMap<>(), new ServiceContext());
		}
		catch (PortalException portalException) {
			_log.error(
				"Unable to schedule the job for RankingIndexRename",
				portalException);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RankingIndexCreationBundleActivator.class);

	@Reference
	private BackgroundTaskManager _backgroundTaskManager;

	@Reference
	private PortalUUID _portalUUID;

	@Reference
	private RankingIndexCreationBackgroundTaskExecutor
		_rankingIndexRenameBackgroundTaskExecutor;

}