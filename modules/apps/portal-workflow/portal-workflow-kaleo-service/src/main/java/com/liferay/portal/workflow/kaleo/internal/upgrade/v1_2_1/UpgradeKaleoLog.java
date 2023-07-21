/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.internal.upgrade.v1_2_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.workflow.kaleo.internal.upgrade.v1_2_0.util.KaleoLogTable;

/**
 * @author Kenneth Chang
 */
public class UpgradeKaleoLog extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alter(
			KaleoLogTable.class, new AlterColumnType("comment_", "TEXT null"));
	}

}