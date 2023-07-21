/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.persistence.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.saml.persistence.internal.upgrade.v1_0_0.util.SamlIdpSpSessionTable;

import java.sql.SQLException;

/**
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 */
public class UpgradeSamlIdpSpSession extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try {
			runSQL(
				"alter_column_type SamlIdpSpSession samlSpEntityId " +
					"VARCHAR(1024) null");
			runSQL(
				"alter_column_type SamlIdpSpSession nameIdFormat " +
					"VARCHAR(1024) null");
			runSQL(
				"alter_column_type SamlIdpSpSession nameIdValue " +
					"VARCHAR(1024) null");
		}
		catch (SQLException sqlException) {
			upgradeTable(
				SamlIdpSpSessionTable.TABLE_NAME,
				SamlIdpSpSessionTable.TABLE_COLUMNS,
				SamlIdpSpSessionTable.TABLE_SQL_CREATE,
				SamlIdpSpSessionTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}