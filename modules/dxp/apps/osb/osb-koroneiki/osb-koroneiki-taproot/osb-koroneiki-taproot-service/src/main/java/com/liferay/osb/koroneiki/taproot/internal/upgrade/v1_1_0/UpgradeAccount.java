/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.upgrade.v1_1_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Amos Fong
 */
public class UpgradeAccount extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateAccount();
	}

	protected void updateAccount() throws Exception {
		if (!hasColumn("Koroneiki_Account", "dataRegion")) {
			runSQL("alter table Koroneiki_Account add dataRegion varchar(75)");

			runSQL(
				"update Koroneiki_Account set dataRegion = 'Brazil' where " +
					"region = 'Brazil'");
			runSQL(
				"update Koroneiki_Account set dataRegion = 'Hungary' where " +
					"region = 'Global' or region = 'Hungary' or region = " +
						"'Spain'");
			runSQL(
				"update Koroneiki_Account set dataRegion = 'Japan' where " +
					"region = 'Australia' or region = 'China' or region = " +
						"'India' or region = 'Japan'");
			runSQL(
				"update Koroneiki_Account set dataRegion = 'United States' " +
					"where region = 'United States'");
		}
	}

}