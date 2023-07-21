/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.internal.upgrade.v1_3_4;

import com.liferay.knowledge.base.constants.KBActionKeys;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Adolfo Pérez
 */
public class UpgradeResourceAction extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (_hasViewFeedbackResourceAction()) {
			runSQL(
				"delete from ResourceAction where actionId = '" +
					KBActionKeys.VIEW_SUGGESTIONS + "'");

			runSQL(
				StringBundler.concat(
					"update ResourceAction set actionId = '",
					KBActionKeys.VIEW_SUGGESTIONS, "' where actionId = '",
					_ACTION_ID_VIEW_KB_FEEDBACK, "'"));
		}
	}

	private boolean _hasViewFeedbackResourceAction() throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select count(*) from ResourceAction where actionId = ?");

			ps.setString(1, _ACTION_ID_VIEW_KB_FEEDBACK);

			rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.getInt(1) > 0) {
					return true;
				}

				return false;
			}

			return false;
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	private static final String _ACTION_ID_VIEW_KB_FEEDBACK =
		"VIEW_KB_FEEDBACK";

}