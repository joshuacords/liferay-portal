/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.internal.upgrade;

import com.liferay.exportimport.internal.upgrade.v1_0_0.UpgradePublisherRequest;
import com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalService;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniel Kocsis
 */
@Component(
	immediate = true,
	service = {ExportImportServiceUpgrade.class, UpgradeStepRegistrator.class}
)
public class ExportImportServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register("0.0.0", "1.0.1", new DummyUpgradeStep());

		registry.register(
			"0.0.1", "1.0.0",
			new com.liferay.exportimport.internal.upgrade.v1_0_0.
				UpgradeBackgroundTaskExecutorClassNames(),
			new UpgradePublisherRequest(
				_exportImportConfigurationLocalService, _groupLocalService,
				_schedulerEngineHelper, _userLocalService));

		registry.register(
			"1.0.0", "1.0.1",
			new com.liferay.exportimport.internal.upgrade.v1_0_1.
				UpgradeBackgroundTaskExecutorClassNames());
	}

	@Reference(unbind = "-")
	protected void setExportImportConfigurationLocalService(
		ExportImportConfigurationLocalService
			exportImportConfigurationLocalService) {

		_exportImportConfigurationLocalService =
			exportImportConfigurationLocalService;
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.portal.background.task.service)(&(release.schema.version>=2.0.0)(!(release.schema.version>=3.0.0))))",
		unbind = "-"
	)
	protected void setRelease(Release release) {
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(
		SchedulerEngineHelper schedulerEngineHelper) {

		_schedulerEngineHelper = schedulerEngineHelper;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private ExportImportConfigurationLocalService
		_exportImportConfigurationLocalService;
	private GroupLocalService _groupLocalService;
	private SchedulerEngineHelper _schedulerEngineHelper;
	private UserLocalService _userLocalService;

}