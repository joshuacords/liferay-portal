/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_1_x;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.upgrade.v7_1_x.util.AssetCategoryTable;
import com.liferay.portal.upgrade.v7_1_x.util.AssetVocabularyTable;
import com.liferay.portal.upgrade.v7_1_x.util.OrganizationTable;
import com.liferay.portal.upgrade.v7_1_x.util.UserGroupTable;
import com.liferay.portal.upgrade.v7_1_x.util.UserTable;

/**
 * @author Shuyang Zhou
 */
public class UpgradeExternalReferenceCode extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		for (Class<?> tableClass : _TABLE_CLASSES) {
			alter(
				tableClass,
				new AlterTableAddColumn(
					"externalReferenceCode VARCHAR(75) null"));
		}
	}

	private static final Class<?>[] _TABLE_CLASSES = {
		AssetCategoryTable.class, AssetVocabularyTable.class,
		OrganizationTable.class, UserGroupTable.class, UserTable.class
	};

}