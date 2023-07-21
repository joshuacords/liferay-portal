/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.internal.upgrade.v2_2_0;

import com.liferay.commerce.discount.internal.upgrade.base.BaseCommerceDiscountUpgradeProcess;
import com.liferay.commerce.discount.model.impl.CommerceDiscountImpl;

/**
 * @author Riccardo Alberti
 */
public class CommerceDiscountUpgradeProcess
	extends BaseCommerceDiscountUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		addColumn(
			CommerceDiscountImpl.class, CommerceDiscountImpl.TABLE_NAME,
			"level", "VARCHAR(75)");
		addColumn(
			CommerceDiscountImpl.class, CommerceDiscountImpl.TABLE_NAME,
			"rulesConjunction", "BOOLEAN");

		runSQL("update CommerceDiscount set rulesConjunction = [$TRUE$]");
	}

}