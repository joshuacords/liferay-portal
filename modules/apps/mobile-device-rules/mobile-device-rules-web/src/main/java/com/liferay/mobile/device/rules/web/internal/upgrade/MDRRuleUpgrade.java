/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.upgrade;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Tom Wang
 */
public class MDRRuleUpgrade extends UpgradeProcess {

	public MDRRuleUpgrade(String oldPackageName, String newPackageName) {
		_oldPackageName = oldPackageName;
		_newPackageName = newPackageName;
	}

	@Override
	public void doUpgrade() throws Exception {
		runSQL(
			StringBundler.concat(
				"update MDRRule set type_ = '", _newPackageName,
				"' where type_ = '", _oldPackageName, "'"));
	}

	private final String _newPackageName;
	private final String _oldPackageName;

}