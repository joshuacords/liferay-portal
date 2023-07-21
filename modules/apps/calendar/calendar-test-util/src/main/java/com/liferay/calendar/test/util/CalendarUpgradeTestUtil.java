/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.test.util;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;

import java.sql.SQLException;

/**
 * @author Adam Brandizzi
 */
public class CalendarUpgradeTestUtil {

	public static UpgradeProcess getServiceUpgradeStep(
		String upgradeStepClassName) {

		return getUpgradeStep(
			"com.liferay.calendar.internal.upgrade.CalendarServiceUpgrade",
			upgradeStepClassName);
	}

	public static UpgradeDatabaseTestHelper getUpgradeDatabaseTestHelper()
		throws SQLException {

		return new UpgradeDatabaseTestHelperImpl(DataAccess.getConnection());
	}

	public static UpgradeProcess getUpgradeStep(
		String upgradeClassName, String upgradeStepClassName) {

		Registry registry = RegistryUtil.getRegistry();

		return registry.callService(
			upgradeClassName,
			(UpgradeStepRegistrator upgradeStepRegistror) -> {
				SearchRegistry searchRegistry = new SearchRegistry(
					upgradeStepClassName);

				upgradeStepRegistror.register(searchRegistry);

				return searchRegistry.getUpgradeStep();
			});
	}

	public static UpgradeProcess getWebUpgradeStep(
		String upgradeStepClassName) {

		return getUpgradeStep(
			"com.liferay.calendar.web.internal.upgrade.CalendarWebUpgrade",
			upgradeStepClassName);
	}

	private static class SearchRegistry
		implements UpgradeStepRegistrator.Registry {

		public SearchRegistry(String upgradeStepClassName) {
			_upgradeStepClassName = upgradeStepClassName;
		}

		public UpgradeProcess getUpgradeStep() {
			return _upgradeStep;
		}

		@Override
		public void register(
			String bundleSymbolicName, String fromSchemaVersionString,
			String toSchemaVersionString, UpgradeStep... upgradeSteps) {

			register(
				fromSchemaVersionString, toSchemaVersionString, upgradeSteps);
		}

		@Override
		public void register(
			String fromSchemaVersionString, String toSchemaVersionString,
			UpgradeStep... upgradeSteps) {

			for (UpgradeStep upgradeStep : upgradeSteps) {
				Class<?> clazz = upgradeStep.getClass();

				String className = clazz.getName();

				if (className.contains(_upgradeStepClassName)) {
					_upgradeStep = (UpgradeProcess)upgradeStep;
				}
			}
		}

		private UpgradeProcess _upgradeStep;
		private final String _upgradeStepClassName;

	}

}