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
public class UpgradeKaleoDefinition extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"select kaleoDefinitionId, content from KaleoDefinition " +
					"where content like '%WorkflowConstants.toStatus(%'");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long kaleoDefinitionId = rs.getLong(1);

				String content = rs.getString(2);

				content = StringUtil.replace(
					content, "WorkflowConstants.toStatus(",
					"WorkflowConstants.getLabelStatus(");

				updateContent(kaleoDefinitionId, content);
			}
		}
	}

	protected void updateContent(long kaleoDefinitionId, String content)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"update KaleoDefinition set content = ? where " +
					"kaleoDefinitionId = ?")) {

			ps.setString(1, content);
			ps.setLong(2, kaleoDefinitionId);

			ps.executeUpdate();
		}
	}

}