/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.internal.upgrade.v1_3_2;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;

import java.sql.PreparedStatement;

/**
 * @author Rafael Praxedes
 */
public class UpgradeKaleoClassNameAndKaleoClassPK extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeKaleoClassNameAndKaleoClassPK(
			"KaleoAction", "kaleoNodeId", KaleoNode.class.getName());
		upgradeKaleoClassNameAndKaleoClassPK(
			"KaleoLog", "kaleoNodeId", KaleoNode.class.getName());
		upgradeKaleoClassNameAndKaleoClassPK(
			"KaleoNotification", "kaleoNodeId", KaleoNode.class.getName());
		upgradeKaleoClassNameAndKaleoClassPK(
			"KaleoTaskAssignment", "kaleoTaskId", KaleoTask.class.getName());
	}

	protected void upgradeKaleoClassNameAndKaleoClassPK(
			String tableName, String columnName, String kaleoClassName)
		throws Exception {

		if (!hasColumn(tableName, columnName)) {
			return;
		}

		StringBundler sb = new StringBundler(10);

		sb.append("update ");
		sb.append(tableName);
		sb.append(" set kaleoClassName = ?, kaleoClassPK = ");
		sb.append(columnName);
		sb.append(" where kaleoClassName is null and kaleoClassPK is null ");
		sb.append("and ");
		sb.append(columnName);
		sb.append(" is not null and ");
		sb.append(columnName);
		sb.append(" > 0 ");

		try (PreparedStatement ps = connection.prepareStatement(
				sb.toString())) {

			ps.setString(1, kaleoClassName);

			ps.executeUpdate();
		}
	}

}