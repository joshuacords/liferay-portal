/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.upgrade.v4_4_0;

import com.liferay.commerce.internal.upgrade.base.BaseCommerceServiceUpgradeProcess;
import com.liferay.commerce.model.impl.CommerceOrderItemModelImpl;
import com.liferay.commerce.model.impl.CommerceOrderModelImpl;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceOrderManuallyAdjustedUpgradeProcess
	extends BaseCommerceServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		addColumn(
			CommerceOrderModelImpl.class, CommerceOrderModelImpl.TABLE_NAME,
			"manuallyAdjusted", "BOOLEAN");
		addColumn(
			CommerceOrderItemModelImpl.class,
			CommerceOrderItemModelImpl.TABLE_NAME, "manuallyAdjusted",
			"BOOLEAN");
	}

}