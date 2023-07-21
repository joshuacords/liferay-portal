/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.registry;

import com.liferay.portal.kernel.upgrade.UpgradeStep;

/**
 * @author Carlos Sierra Andrés
 */
public interface UpgradeStepRegistrator {

	public void register(Registry registry);

	public interface Registry {

		/**
		 * @deprecated As of Judson (7.1.x), replaced by {@link
		 *             #register(String, String, UpgradeStep[])}
		 */
		@Deprecated
		public void register(
			String bundleSymbolicName, String fromSchemaVersionString,
			String toSchemaVersionString, UpgradeStep... upgradeSteps);

		public void register(
			String fromSchemaVersionString, String toSchemaVersionString,
			UpgradeStep... upgradeSteps);

	}

}