/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v2_0_3;

import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Rafael Praxedes
 */
public class UpgradeDDMFormInstanceDefinition extends UpgradeProcess {

	public UpgradeDDMFormInstanceDefinition(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"select structureId, definition from DDMStructure where " +
					"classNameId = ?");
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update DDMStructure set definition = ? where " +
						"structureId = ?")) {

			ps.setLong(1, PortalUtil.getClassNameId(DDMFormInstance.class));

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String definition = rs.getString("definition");

					ps2.setString(1, updateFieldsToLocalizable(definition));

					long structureId = rs.getLong("structureId");

					ps2.setLong(2, structureId);

					ps2.addBatch();
				}
			}

			ps2.executeBatch();
		}
	}

	protected void updateFieldsToLocalizable(JSONArray fieldsJSONArray) {
		for (int i = 0; i < fieldsJSONArray.length(); i++) {
			JSONObject fieldJSONObject = fieldsJSONArray.getJSONObject(i);

			fieldJSONObject.put("localizable", true);

			JSONArray nestedFieldsJSONArray = fieldJSONObject.getJSONArray(
				"nestedFields");

			if (nestedFieldsJSONArray != null) {
				updateFieldsToLocalizable(nestedFieldsJSONArray);
			}
		}
	}

	protected String updateFieldsToLocalizable(String definition)
		throws PortalException {

		JSONObject ddmFormJSONObject = _jsonFactory.createJSONObject(
			definition);

		JSONArray fieldsJSONArray = ddmFormJSONObject.getJSONArray("fields");

		updateFieldsToLocalizable(fieldsJSONArray);

		return ddmFormJSONObject.toString();
	}

	private final JSONFactory _jsonFactory;

}