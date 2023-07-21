/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.upgrade.dao.orm;

import com.liferay.portal.kernel.dao.db.DBType;

/**
 * @author     Cristina González
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class UpgradeOptimizedConnectionProviderRegistryUtil {

	public static UpgradeOptimizedConnectionProvider
		getUpgradeOptimizedConnectionProvider(DBType dbType) {

		return null;
	}

	public static void setUpgradeOptimizedConnectionProviderRegistry(
		UpgradeOptimizedConnectionProviderRegistry
			upgradeOptimizedConnectionProviderRegistry) {
	}

}