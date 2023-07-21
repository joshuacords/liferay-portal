/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.forms.internal.upgrade.v1_0_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Marcellus Tavares
 */
public class UpgradeKaleoProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateWorkflowDefinition();
	}

	protected void updateKaleoProcess(
			long kaleoProcessId, String workflowDefinitioName,
			int workflowDefinitionVersion)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"update KaleoProcess set workflowDefinitionName = ?, " +
					"workflowDefinitionVersion = ? where kaleoProcessId = ?")) {

			ps.setString(1, workflowDefinitioName);
			ps.setInt(2, workflowDefinitionVersion);
			ps.setLong(3, kaleoProcessId);

			ps.executeUpdate();
		}
	}

	protected void updateWorkflowDefinition() throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"select classPK, workflowDefinitionName, " +
					"workflowDefinitionVersion from WorkflowDefinitionLink " +
						"where classNameId = ?")) {

			long kaleoProcessClassNameId = PortalUtil.getClassNameId(
				KaleoProcess.class);

			ps.setLong(1, kaleoProcessClassNameId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					long kaleoProcessId = rs.getLong("classPK");
					String workflowDefinitioName = rs.getString(
						"workflowDefinitionName");
					int workflowDefinitionVersion = rs.getInt(
						"workflowDefinitionVersion");

					updateKaleoProcess(
						kaleoProcessId, workflowDefinitioName,
						workflowDefinitionVersion);
				}
			}
		}
	}

}