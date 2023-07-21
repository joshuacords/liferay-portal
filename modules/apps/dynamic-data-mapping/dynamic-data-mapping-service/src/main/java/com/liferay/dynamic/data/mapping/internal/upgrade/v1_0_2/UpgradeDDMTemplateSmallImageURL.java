/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_2;

import com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_0.util.DDMTemplateTable;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author David Zhang
 */
public class UpgradeDDMTemplateSmallImageURL extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alter(
			DDMTemplateTable.class,
			new AlterColumnType("smallImageURL", "STRING null"));
	}

}