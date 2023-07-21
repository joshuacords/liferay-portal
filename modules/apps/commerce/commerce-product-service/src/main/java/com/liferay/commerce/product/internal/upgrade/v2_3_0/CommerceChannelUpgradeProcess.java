/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.upgrade.v2_3_0;

import com.liferay.commerce.pricing.constants.CommercePricingConstants;
import com.liferay.commerce.product.internal.upgrade.base.BaseCommerceProductServiceUpgradeProcess;
import com.liferay.commerce.product.model.impl.CommerceChannelModelImpl;

/**
 * @author Riccardo Alberti
 */
public class CommerceChannelUpgradeProcess
	extends BaseCommerceProductServiceUpgradeProcess {

	@Override
	public void doUpgrade() throws Exception {
		addColumn(
			CommerceChannelModelImpl.class, CommerceChannelModelImpl.TABLE_NAME,
			"priceDisplayType", "VARCHAR(75)");

		addColumn(
			CommerceChannelModelImpl.class, CommerceChannelModelImpl.TABLE_NAME,
			"discountsTargetNetPrice", "BOOLEAN");

		runSQL(
			"update CommerceChannel set priceDisplayType = '" +
				CommercePricingConstants.TAX_EXCLUDED_FROM_PRICE + "'");

		runSQL("update CommerceChannel set discountsTargetNetPrice = [$TRUE$]");
	}

}