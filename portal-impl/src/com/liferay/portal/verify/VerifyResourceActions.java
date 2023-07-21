/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author     Michael Bowerman
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class VerifyResourceActions extends VerifyProcess {

	protected void deleteDuplicateBitwiseValuesOnResource() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps1 = connection.prepareStatement(
				"select name, bitwiseValue, min(resourceActionId) as " +
					"minResourceActionId from ResourceAction group by name, " +
						"bitwiseValue having count(resourceActionId) > 1");
			PreparedStatement ps2 = connection.prepareStatement(
				"select resourceActionId, actionId from ResourceAction where " +
					"name = ? and bitwiseValue = ? and resourceActionId != ?");
			ResultSet rs1 = ps1.executeQuery()) {

			while (rs1.next()) {
				String name = rs1.getString("name");
				long bitwiseValue = rs1.getLong("bitwiseValue");
				long minResourceActionId = rs1.getLong("minResourceActionId");

				ps2.setString(1, name);
				ps2.setLong(2, bitwiseValue);
				ps2.setLong(3, minResourceActionId);

				try (ResultSet rs2 = ps2.executeQuery()) {
					while (rs2.next()) {
						if (_log.isInfoEnabled()) {
							StringBundler sb = new StringBundler(7);

							sb.append("Deleting resource action ");
							sb.append(rs2.getString("actionId"));
							sb.append(" from resource ");
							sb.append(name);
							sb.append(" because its bitwise value is the ");
							sb.append("same as another resource action on ");
							sb.append("the same resource");

							_log.info(sb.toString());
						}

						ResourceActionLocalServiceUtil.deleteResourceAction(
							rs2.getLong("resourceActionId"));
					}
				}
			}
		}
	}

	@Override
	protected void doVerify() throws Exception {
		deleteDuplicateBitwiseValuesOnResource();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		VerifyResourceActions.class);

}