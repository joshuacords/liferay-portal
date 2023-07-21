/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v3_0_0;

import com.liferay.commerce.internal.upgrade.base.BaseCommerceServiceUpgradeProcess;
import com.liferay.commerce.model.impl.CommerceSubscriptionEntryModelImpl;

/**
 * @author Luca Pellizzon
 */
public class CommerceSubscriptionCycleEntryUpgradeProcess
	extends BaseCommerceServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		addColumn(
			CommerceSubscriptionEntryModelImpl.class,
			CommerceSubscriptionEntryModelImpl.TABLE_NAME, "currentCycle",
			"LONG");

		if (hasColumn(
				CommerceSubscriptionEntryModelImpl.TABLE_NAME,
				"currentCycle")) {

			runSQL(
				"UPDATE CommerceSubscriptionEntry SET currentCycle = 0 WHERE " +
					"currentCycle IS NULL");
		}

		if (hasTable("CSubscriptionCycleEntry")) {
			runSQL("drop table CSubscriptionCycleEntry");
		}
	}

}