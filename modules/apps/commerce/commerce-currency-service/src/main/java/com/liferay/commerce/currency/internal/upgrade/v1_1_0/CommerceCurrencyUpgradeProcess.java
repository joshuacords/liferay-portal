/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.currency.internal.upgrade.v1_1_0;

import com.liferay.commerce.currency.internal.upgrade.base.BaseCommerceCurrencyUpgradeProcess;
import com.liferay.commerce.currency.model.impl.CommerceCurrencyModelImpl;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceCurrencyUpgradeProcess
	extends BaseCommerceCurrencyUpgradeProcess {

	@Override
	public void doUpgrade() throws Exception {
		dropColumn(CommerceCurrencyModelImpl.TABLE_NAME, "groupId");
	}

}