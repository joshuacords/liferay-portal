/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.discount.internal.upgrade.v2_2_0;

import com.liferay.commerce.discount.model.impl.CommerceDiscountCommerceAccountGroupRelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Riccardo Alberti
 */
public class CommerceDiscountCommerceAccountGroupRelUpgradeProcess
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasTable(CommerceDiscountCommerceAccountGroupRelImpl.TABLE_NAME)) {
			runSQL(
				CommerceDiscountCommerceAccountGroupRelImpl.TABLE_SQL_CREATE);
		}
	}

}