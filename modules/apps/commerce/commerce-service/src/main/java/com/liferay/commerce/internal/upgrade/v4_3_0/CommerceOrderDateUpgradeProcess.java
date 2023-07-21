/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v4_3_0;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.internal.upgrade.v1_1_0.BaseCommerceOrderUpgradeProcess;
import com.liferay.commerce.model.impl.CommerceOrderModelImpl;

/**
 * @author Alec Sloan
 */
public class CommerceOrderDateUpgradeProcess
	extends BaseCommerceOrderUpgradeProcess {

	public CommerceOrderDateUpgradeProcess() {
		super(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"orderDate", "DATE");
	}

	@Override
	protected void doUpgrade() throws Exception {
		super.doUpgrade();

		runSQL(
			"update CommerceOrder set orderDate = createDate where " +
				"orderStatus <> " + CommerceOrderConstants.ORDER_STATUS_OPEN);
	}

}