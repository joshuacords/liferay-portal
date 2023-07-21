/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.audit.storage.internal.upgrade;

import com.liferay.portal.kernel.upgrade.BaseUpgradeSQLServerDatetime;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.security.audit.storage.internal.upgrade.v1_0_1.UpgradeSchema;
import com.liferay.portal.security.audit.storage.internal.upgrade.v1_0_1.util.AuditEventTable;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.release.BaseUpgradeServiceModuleRelease;

import org.osgi.service.component.annotations.Component;

/**
 * @author Samuel Ziemer
 */
@Component(service = UpgradeStepRegistrator.class)
public class AuditStorageServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		try {
			BaseUpgradeServiceModuleRelease upgradeServiceModuleRelease =
				new BaseUpgradeServiceModuleRelease() {

					@Override
					protected String getNamespace() {
						return "Audit";
					}

					@Override
					protected String getNewBundleSymbolicName() {
						return "com.liferay.portal.security.audit.storage." +
							"service";
					}

					@Override
					protected String getOldBundleSymbolicName() {
						return "audit-portlet";
					}

				};

			upgradeServiceModuleRelease.upgrade();
		}
		catch (UpgradeException upgradeException) {
			throw new RuntimeException(upgradeException);
		}

		registry.register("0.0.1", "1.0.0", new DummyUpgradeStep());

		registry.register("1.0.0", "1.0.1", new UpgradeSchema());

		registry.register(
			"1.0.1", "2.0.0",
			new BaseUpgradeSQLServerDatetime(
				new Class<?>[] {AuditEventTable.class}));

		registry.register("2.0.0", "2.0.1", new DummyUpgradeStep());
	}

}