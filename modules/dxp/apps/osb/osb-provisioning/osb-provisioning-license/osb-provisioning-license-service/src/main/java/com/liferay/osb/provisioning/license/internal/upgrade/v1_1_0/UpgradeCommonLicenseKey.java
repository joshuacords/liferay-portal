/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.internal.upgrade.v1_1_0;

import com.liferay.osb.provisioning.license.model.impl.CommonLicenseKeyModelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Amos Fong
 */
public class UpgradeCommonLicenseKey extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateCommonLicenseKey();
	}

	protected void updateCommonLicenseKey() throws Exception {
		if (!hasTable(CommonLicenseKeyModelImpl.TABLE_NAME)) {
			runSQL(CommonLicenseKeyModelImpl.TABLE_SQL_CREATE);
		}
	}

}