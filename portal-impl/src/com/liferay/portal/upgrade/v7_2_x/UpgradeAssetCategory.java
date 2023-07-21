/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_2_x;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.upgrade.v7_2_x.util.AssetCategoryTable;

/**
 * @author Vendel Toreki
 */
public class UpgradeAssetCategory extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumnType(
				getTableName(AssetCategoryTable.class), "title", "TEXT null")) {

			alter(
				AssetCategoryTable.class,
				new AlterColumnType("title", "TEXT null"));
		}

		if (!hasColumnType(
				getTableName(AssetCategoryTable.class), "description",
				"TEXT null")) {

			alter(
				AssetCategoryTable.class,
				new AlterColumnType("description", "TEXT null"));
		}
	}

}