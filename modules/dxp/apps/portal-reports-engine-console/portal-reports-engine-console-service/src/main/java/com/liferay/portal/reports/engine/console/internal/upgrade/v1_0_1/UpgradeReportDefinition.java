/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.internal.upgrade.v1_0_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.reports.engine.console.internal.upgrade.v1_0_1.util.DefinitionTable;

/**
 * @author Eduardo Pérez
 */
public class UpgradeReportDefinition extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alter(
			DefinitionTable.class,
			new AlterColumnType("reportParameters", "TEXT null"));
	}

}