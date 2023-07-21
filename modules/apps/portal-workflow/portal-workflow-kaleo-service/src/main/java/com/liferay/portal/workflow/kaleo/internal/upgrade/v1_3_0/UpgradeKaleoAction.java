/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.internal.upgrade.v1_3_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Leonardo Barros
 */
public class UpgradeKaleoAction extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"select kaleoActionId, script from KaleoAction where script " +
					"like '%WorkflowConstants.toStatus(%'");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long kaleoActionId = rs.getLong(1);

				String script = rs.getString(2);

				script = StringUtil.replace(
					script, "WorkflowConstants.toStatus(",
					"WorkflowConstants.getLabelStatus(");

				updateScript(kaleoActionId, script);
			}
		}
	}

	protected void updateScript(long kaleoActionId, String script)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"update KaleoAction set script = ? where kaleoActionId = ?")) {

			ps.setString(1, script);
			ps.setLong(2, kaleoActionId);

			ps.executeUpdate();
		}
	}

}