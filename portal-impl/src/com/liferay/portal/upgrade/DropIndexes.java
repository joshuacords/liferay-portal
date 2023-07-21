/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade;

import com.liferay.portal.events.StartupHelperUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Brian Wing Shun Chan
 */
public class DropIndexes extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		StartupHelperUtil.setDropIndexes(true);
	}

}