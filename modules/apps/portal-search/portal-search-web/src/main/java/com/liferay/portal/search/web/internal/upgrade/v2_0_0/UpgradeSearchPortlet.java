/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.upgrade.v2_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.search.web.constants.SearchPortletKeys;

import java.sql.PreparedStatement;

/**
 * @author Bryan Engler
 */
public class UpgradeSearchPortlet extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradePortletPreferencesPortletId();
	}

	protected void upgradePortletPreferencesPortletId() throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"update PortletPreferences set portletId = ? where " +
					"portletId= ? and plid = ?")) {

			ps.setString(
				1, SearchPortletKeys.SEARCH + "_INSTANCE_templateSearch");
			ps.setString(2, SearchPortletKeys.SEARCH);
			ps.setLong(3, 0);

			ps.executeUpdate();
		}
	}

}