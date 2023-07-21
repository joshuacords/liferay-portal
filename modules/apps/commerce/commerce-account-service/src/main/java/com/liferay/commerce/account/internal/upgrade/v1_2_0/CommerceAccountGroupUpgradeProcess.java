/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.internal.upgrade.v1_2_0;

import com.liferay.commerce.account.model.impl.CommerceAccountGroupModelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountGroupUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasTable(CommerceAccountGroupModelImpl.TABLE_NAME)) {
			runSQL(CommerceAccountGroupModelImpl.TABLE_SQL_CREATE);
		}
	}

}