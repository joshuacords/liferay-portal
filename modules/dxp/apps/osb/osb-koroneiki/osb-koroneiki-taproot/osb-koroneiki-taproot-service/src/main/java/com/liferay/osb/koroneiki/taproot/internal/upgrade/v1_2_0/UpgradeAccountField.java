/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.upgrade.v1_2_0;

import com.liferay.osb.koroneiki.taproot.model.impl.AccountFieldModelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Amos Fong
 */
public class UpgradeAccountField extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateAccountField();
	}

	protected void updateAccountField() throws Exception {
		if (!hasTable(AccountFieldModelImpl.TABLE_NAME)) {
			runSQL(AccountFieldModelImpl.TABLE_SQL_CREATE);
		}
	}

}