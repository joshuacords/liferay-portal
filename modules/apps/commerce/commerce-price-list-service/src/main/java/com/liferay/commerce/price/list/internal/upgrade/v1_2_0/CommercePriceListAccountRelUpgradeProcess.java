/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.price.list.internal.upgrade.v1_2_0;

import com.liferay.commerce.price.list.model.impl.CommercePriceListAccountRelModelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Ethan Bustad
 * @author Alessio Antonio Rendina
 */
public class CommercePriceListAccountRelUpgradeProcess extends UpgradeProcess {

	@Override
	public void doUpgrade() throws Exception {
		if (!hasTable(CommercePriceListAccountRelModelImpl.TABLE_NAME)) {
			runSQL(CommercePriceListAccountRelModelImpl.TABLE_SQL_CREATE);
		}
	}

}