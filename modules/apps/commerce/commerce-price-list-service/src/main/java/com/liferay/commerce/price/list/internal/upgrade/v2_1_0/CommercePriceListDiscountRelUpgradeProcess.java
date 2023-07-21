/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.internal.upgrade.v2_1_0;

import com.liferay.commerce.price.list.model.impl.CommercePriceListDiscountRelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Riccardo Alberti
 */
public class CommercePriceListDiscountRelUpgradeProcess extends UpgradeProcess {

	@Override
	public void doUpgrade() throws Exception {
		if (!hasTable(CommercePriceListDiscountRelImpl.TABLE_NAME)) {
			runSQL(CommercePriceListDiscountRelImpl.TABLE_SQL_CREATE);
		}
	}

}