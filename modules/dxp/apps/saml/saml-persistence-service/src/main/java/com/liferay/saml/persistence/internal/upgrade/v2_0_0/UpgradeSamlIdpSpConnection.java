/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.persistence.internal.upgrade.v2_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.saml.persistence.internal.upgrade.v2_0_0.util.SamlIdpSpConnectionTable;

import java.sql.SQLException;

/**
 * @author Carlos Sierra Andrés
 */
public class UpgradeSamlIdpSpConnection extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			alter(
				SamlIdpSpConnectionTable.class,
				new AlterTableAddColumn("encryptionForced BOOLEAN"));
		}
		catch (SQLException sqlException) {
			upgradeTable(
				SamlIdpSpConnectionTable.TABLE_NAME,
				SamlIdpSpConnectionTable.TABLE_COLUMNS,
				SamlIdpSpConnectionTable.TABLE_SQL_CREATE,
				SamlIdpSpConnectionTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}