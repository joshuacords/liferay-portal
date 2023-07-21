/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wesley Gong
 * @author Calvin Keum
 */
public class UpgradeReportEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable("Reports_Entry")) {
			updateReportEntries();
		}
	}

	protected String updateEntryParameters(String reportParameters) {
		Matcher matcher = _pattern.matcher(reportParameters);

		if (!matcher.find()) {
			return reportParameters;
		}

		JSONArray reportParametersJSONArray = JSONFactoryUtil.createJSONArray();

		String[] keyValuePairs = StringUtil.split(reportParameters);

		for (String keyValuePair : keyValuePairs) {
			if (Validator.isNull(keyValuePair) ||
				!keyValuePair.contains(StringPool.EQUAL)) {

				continue;
			}

			reportParametersJSONArray.put(
				JSONUtil.put(
					"key", keyValuePair.split(StringPool.EQUAL)[0]
				).put(
					"value", keyValuePair.split(StringPool.EQUAL)[1]
				));
		}

		return reportParametersJSONArray.toString();
	}

	protected void updateReportEntries() throws Exception {
		try (PreparedStatement ps1 = connection.prepareStatement(
				"select companyId, entryId, reportParameters from " +
					"Reports_Entry")) {

			try (PreparedStatement ps2 =
					AutoBatchPreparedStatementUtil.concurrentAutoBatch(
						connection,
						"update Reports_Entry set reportParameters = ? where " +
							"companyId = ? and entryId = ?");
				ResultSet rs = ps1.executeQuery()) {

				while (rs.next()) {
					String reportParameters = rs.getString("reportParameters");

					String updatedReportParameters = updateEntryParameters(
						reportParameters);

					if (Validator.isNotNull(reportParameters) &&
						reportParameters.equals(updatedReportParameters)) {

						continue;
					}

					ps2.setString(1, updatedReportParameters);
					ps2.setLong(2, rs.getLong("companyId"));
					ps2.setLong(3, rs.getLong("entryId"));

					ps2.addBatch();
				}

				ps2.executeBatch();
			}
		}
	}

	private static final Pattern _pattern = Pattern.compile("[.*=.*]+");

}