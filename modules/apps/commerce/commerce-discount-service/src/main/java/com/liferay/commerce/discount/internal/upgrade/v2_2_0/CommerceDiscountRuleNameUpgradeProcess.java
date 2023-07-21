/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.internal.upgrade.v2_2_0;

import com.liferay.commerce.discount.internal.upgrade.base.BaseCommerceDiscountUpgradeProcess;
import com.liferay.commerce.discount.model.impl.CommerceDiscountRuleImpl;

/**
 * @author Riccardo Alberti
 */
public class CommerceDiscountRuleNameUpgradeProcess
	extends BaseCommerceDiscountUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		addColumn(
			CommerceDiscountRuleImpl.class, CommerceDiscountRuleImpl.TABLE_NAME,
			"name", "VARCHAR(75)");

		runSQL("update CommerceDiscountRule set name = type_");
	}

}