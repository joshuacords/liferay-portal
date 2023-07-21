/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.internal.upgrade.v2_4_1;

import com.liferay.commerce.discount.internal.upgrade.base.BaseCommerceDiscountUpgradeProcess;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceDiscountUpgradeProcess
	extends BaseCommerceDiscountUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update CommerceDiscount set target = 'product-groups' where " +
				"target = 'pricing-class'");
		runSQL(
			"update CommerceDiscount set target = 'products' where target = " +
				"'product'");
	}

}