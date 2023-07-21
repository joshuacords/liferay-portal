/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v3_2_9;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Rodrigo Paulino
 */
public class UpgradeDDMFormInstance extends UpgradeProcess {

	public UpgradeDDMFormInstance(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	@Override
	protected void doUpgrade() throws Exception {
		StringBuilder sb = new StringBuilder(4);

		sb.append("where settings_ not like '%workflowDefinition\\\",\\\"");
		sb.append("value\\\":\\\"[\\\\\\\\\"no-workflow\\\\\\\\\"]\\\"%' and ");
		sb.append("settings_ like '%workflowDefinition\\\",\\\"value\\\":\\\"");
		sb.append("[\\\\\\\\\"%@%\\\\\\\\\"]\\\"%'");

		try (PreparedStatement ps1 = connection.prepareStatement(
				"select formInstanceId, settings_ from DDMFormInstance " +
					sb.toString());
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update DDMFormInstance set settings_ = ? where " +
						"formInstanceId = ?");
			PreparedStatement ps3 = connection.prepareStatement(
				"select formInstanceVersionId, settings_ from " +
					"DDMFormInstanceVersion " + sb.toString());
			PreparedStatement ps4 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update DDMFormInstanceVersion set settings_ = ? where " +
						"formInstanceVersionId = ?")) {

			_executePreparedStatements("formInstanceId", ps1, ps2);

			_executePreparedStatements("formInstanceVersionId", ps3, ps4);
		}
	}

	private void _executePreparedStatements(
			String idColumnName, PreparedStatement selectPreparedStatement,
			PreparedStatement updatePreparedStatement)
		throws JSONException, SQLException {

		try (ResultSet rs = selectPreparedStatement.executeQuery()) {
			while (rs.next()) {
				JSONObject settingsJSONObject = _jsonFactory.createJSONObject(
					rs.getString("settings_"));

				if (_upgradeSettings(settingsJSONObject)) {
					updatePreparedStatement.setString(
						1, settingsJSONObject.toString());
					updatePreparedStatement.setLong(
						2, rs.getLong(idColumnName));

					updatePreparedStatement.addBatch();
				}
			}
		}

		updatePreparedStatement.executeBatch();
	}

	private boolean _upgradeSettings(JSONObject settingsJSONObject) {
		boolean upgraded = false;

		JSONArray fieldValuesJSONArray = settingsJSONObject.getJSONArray(
			"fieldValues");

		for (int i = 0; i < fieldValuesJSONArray.length(); i++) {
			JSONObject fieldValueJSONObject =
				fieldValuesJSONArray.getJSONObject(i);

			if (StringUtil.equals(
					fieldValueJSONObject.getString("name"),
					"workflowDefinition")) {

				String workflowDefinition = fieldValueJSONObject.getString(
					"value");

				if (workflowDefinition.matches("\\[\".+@\\d+\"\\]")) {
					fieldValueJSONObject.put(
						"value",
						workflowDefinition.replaceAll(
							"@\\d+\"\\]",
							StringPool.QUOTE + StringPool.CLOSE_BRACKET));

					upgraded = true;
				}

				break;
			}
		}

		return upgraded;
	}

	private final JSONFactory _jsonFactory;

}