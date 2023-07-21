/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.internal.upgrade.v1_3_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Adolfo Pérez
 */
public class UpgradeKBComment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeIndexes();
	}

	protected void upgradeIndexes() throws Exception {
		runSQL("drop index IX_FD56A55D on KBComment");

		runSQL(
			"create index IX_FD56A55D on KBComment(userId, classNameId, " +
				"classPK)");
	}

}