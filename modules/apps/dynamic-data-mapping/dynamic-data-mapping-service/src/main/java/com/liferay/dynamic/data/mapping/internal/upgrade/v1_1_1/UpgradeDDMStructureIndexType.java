/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.upgrade.v1_1_1;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.metadata.RawMetadataProcessor;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Lino Alves
 */
public class UpgradeDDMStructureIndexType extends UpgradeProcess {

	public UpgradeDDMStructureIndexType(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_upgradeDDMStructureDefinition();
	}

	private void _upgradeDDMStructureDefinition() throws Exception {
		StringBundler sb = new StringBundler(2);

		sb.append("select DDMStructure.definition, DDMStructure.structureId ");
		sb.append("from DDMStructure where structureKey = ? ");

		try (PreparedStatement ps1 = connection.prepareStatement(sb.toString());
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update DDMStructure set definition = ? where " +
						"structureId = ?");
			PreparedStatement ps3 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update DDMStructureVersion set definition = ? where " +
						"structureId = ?")) {

			ps1.setString(1, RawMetadataProcessor.TIKA_RAW_METADATA);

			try (ResultSet rs = ps1.executeQuery()) {
				while (rs.next()) {
					String newDefinition = _upgradeIndexType(rs.getString(1));

					ps2.setString(1, newDefinition);

					ps2.setLong(2, rs.getLong(2));

					ps2.addBatch();

					ps3.setString(1, newDefinition);

					ps3.setLong(2, rs.getLong(2));

					ps3.addBatch();
				}

				ps2.executeBatch();
				ps3.executeBatch();
			}
		}
	}

	private String _upgradeIndexType(String definition) throws PortalException {
		try {
			JSONObject definitionJSONObject = _jsonFactory.createJSONObject(
				definition);

			JSONArray fieldsJSONArray = definitionJSONObject.getJSONArray(
				"fields");

			for (int i = 0; i < fieldsJSONArray.length(); i++) {
				JSONObject jsonObject = fieldsJSONArray.getJSONObject(i);

				if (!jsonObject.has("indexType")) {
					jsonObject.put("indexType", "text");
				}
			}

			return definitionJSONObject.toString();
		}
		catch (JSONException jsonException) {
			return definition;
		}
	}

	private final JSONFactory _jsonFactory;

}