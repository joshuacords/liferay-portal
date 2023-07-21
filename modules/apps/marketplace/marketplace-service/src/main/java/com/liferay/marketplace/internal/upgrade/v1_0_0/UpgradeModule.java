/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.marketplace.internal.upgrade.v1_0_0;

import com.liferay.marketplace.internal.util.ContextUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.io.IOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Joan Kim
 * @author Ryan Park
 */
public class UpgradeModule extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateModules();
	}

	protected void updateModules() throws Exception {
		if (!hasColumn("Marketplace_Module", "bundleSymbolicName")) {
			runSQL(
				"alter table Marketplace_Module add bundleSymbolicName " +
					"VARCHAR(500)");
		}

		if (!hasColumn("Marketplace_Module", "bundleVersion")) {
			runSQL(
				"alter table Marketplace_Module add bundleVersion VARCHAR(75)");
		}

		try (PreparedStatement ps = connection.prepareStatement(
				"select moduleId, contextName from Marketplace_Module");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long moduleId = rs.getLong("moduleId");
				String contextName = rs.getString("contextName");

				String newContextName = null;

				try {
					newContextName = ContextUtil.getContextName(contextName);

					runSQL(
						StringBundler.concat(
							"update Marketplace_Module set contextName = '",
							newContextName, "' where moduleId = ", moduleId));
				}
				catch (IOException ioException) {
					_log.error(
						StringBundler.concat(
							"Unable to update module + ", moduleId,
							" with the new context name ", newContextName),
						ioException);
				}
			}
		}
		catch (SQLException sqlException) {
			_log.error("Unable to update modules", sqlException);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(UpgradeModule.class);

}