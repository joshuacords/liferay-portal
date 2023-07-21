/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.persistence.internal.upgrade.v1_1_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.saml.persistence.internal.upgrade.v1_1_1.util.SamlSpSessionTable;

import java.sql.SQLException;

/**
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 */
public class UpgradeSamlSpSession extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL(
				"alter_column_type SamlSpSession jSessionId VARCHAR(200) null");
		}
		catch (SQLException sqlException) {
			upgradeTable(
				SamlSpSessionTable.TABLE_NAME, SamlSpSessionTable.TABLE_COLUMNS,
				SamlSpSessionTable.TABLE_SQL_CREATE,
				SamlSpSessionTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}