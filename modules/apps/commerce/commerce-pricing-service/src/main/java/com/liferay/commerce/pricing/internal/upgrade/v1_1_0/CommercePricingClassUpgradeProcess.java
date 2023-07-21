/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.internal.upgrade.v1_1_0;

import com.liferay.commerce.pricing.internal.upgrade.base.BaseCommercePricingUpgradeProcess;
import com.liferay.commerce.pricing.model.impl.CommercePricingClassImpl;

/**
 * @author Riccardo Alberti
 */
public class CommercePricingClassUpgradeProcess
	extends BaseCommercePricingUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		changeColumnType(
			CommercePricingClassImpl.class, CommercePricingClassImpl.TABLE_NAME,
			"title", "TEXT");

		changeColumnType(
			CommercePricingClassImpl.class, CommercePricingClassImpl.TABLE_NAME,
			"description", "TEXT");
	}

}