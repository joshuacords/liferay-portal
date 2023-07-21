/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.internal.upgrade;

import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.reports.engine.console.internal.upgrade.v1_0_1.UpgradeKernelPackage;
import com.liferay.portal.reports.engine.console.internal.upgrade.v1_0_1.UpgradeLastPublishDate;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.release.BaseUpgradeServiceModuleRelease;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Wesley Gong
 * @author Calvin Keum
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class ReportsServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		try {
			BaseUpgradeServiceModuleRelease upgradeServiceModuleRelease =
				new BaseUpgradeServiceModuleRelease() {

					@Override
					protected String getNewBundleSymbolicName() {
						return "com.liferay.portal.reports.engine.console." +
							"service";
					}

					@Override
					protected String getOldBundleSymbolicName() {
						return "reports-portlet";
					}

				};

			upgradeServiceModuleRelease.upgrade();
		}
		catch (UpgradeException upgradeException) {
			throw new RuntimeException(upgradeException);
		}

		registry.register(
			"com.liferay.portal.reports.engine.console.service", "0.0.1",
			"1.0.0",
			new com.liferay.portal.reports.engine.console.internal.upgrade.
				v1_0_0.UpgradeReportDefinition(),
			new com.liferay.portal.reports.engine.console.internal.upgrade.
				v1_0_0.UpgradeReportEntry());

		registry.register(
			"com.liferay.portal.reports.engine.console.service", "1.0.0",
			"1.0.1", new UpgradeKernelPackage(), new UpgradeLastPublishDate(),
			new com.liferay.portal.reports.engine.console.internal.upgrade.
				v1_0_1.UpgradeReportDefinition(),
			new com.liferay.portal.reports.engine.console.internal.upgrade.
				v1_0_1.UpgradeReportEntry());
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}