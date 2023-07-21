/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.internal.upgrade.v2_1_0;

import com.liferay.commerce.price.list.constants.CommercePriceListConstants;
import com.liferay.commerce.price.list.internal.upgrade.base.BaseCommercePriceListUpgradeProcess;
import com.liferay.commerce.price.list.model.impl.CommercePriceListModelImpl;

/**
 * @author Alessio Antonio Rendina
 */
public class CommercePriceListUpgradeProcess
	extends BaseCommercePriceListUpgradeProcess {

	@Override
	public void doUpgrade() throws Exception {
		addColumn(
			CommercePriceListModelImpl.class,
			CommercePriceListModelImpl.TABLE_NAME, "type_", "VARCHAR(75)");

		addColumn(
			CommercePriceListModelImpl.class,
			CommercePriceListModelImpl.TABLE_NAME, "catalogBasePriceList",
			"BOOLEAN");

		runSQL(
			"UPDATE CommercePriceList SET type_ = '" +
				CommercePriceListConstants.TYPE_PRICE_LIST + "'");

		runSQL("UPDATE CommercePriceList SET catalogBasePriceList = [$FALSE$]");
	}

}