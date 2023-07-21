/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v3_1_0;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.internal.upgrade.base.BaseCommerceServiceUpgradeProcess;
import com.liferay.commerce.model.impl.CommerceOrderModelImpl;

/**
 * @author Marco Leo
 */
public class CommerceOrderUpgradeProcess
	extends BaseCommerceServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"couponCode", "VARCHAR(75)");

		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"lastPriceUpdateDate", "DATE");

		if (hasColumn(
				CommerceOrderModelImpl.TABLE_NAME, "lastPriceUpdateDate")) {

			String sql =
				"UPDATE %s SET lastPriceUpdateDate = createDate WHERE " +
					"orderStatus = %s";

			runSQL(
				String.format(
					sql, CommerceOrderModelImpl.TABLE_NAME,
					CommerceOrderConstants.ORDER_STATUS_OPEN));
		}
	}

}