/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.upgrade.v1_3_0;

import com.liferay.commerce.product.internal.upgrade.base.BaseCommerceProductServiceUpgradeProcess;
import com.liferay.commerce.product.model.impl.CPDefinitionModelImpl;
import com.liferay.commerce.product.model.impl.CProductModelImpl;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Ethan Bustad
 * @author Alessio Antonio Rendina
 */
public class CProductUpgradeProcess
	extends BaseCommerceProductServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasTable(CProductModelImpl.TABLE_NAME)) {
			runSQL(CProductModelImpl.TABLE_SQL_CREATE);
		}

		addColumn(
			CPDefinitionModelImpl.class, CPDefinitionModelImpl.TABLE_NAME,
			"CProductId", "LONG");
		addColumn(
			CPDefinitionModelImpl.class, CPDefinitionModelImpl.TABLE_NAME,
			"version", "INTEGER");

		String insertCProductSQL = StringBundler.concat(
			"insert into CProduct (uuid_, CProductId, groupId, companyId, ",
			"userId, userName, createDate, modifiedDate, ",
			"publishedCPDefinitionId, latestVersion) values (?, ?, ?, ?, ?, ",
			"?, ?, ?, ?, 1)");
		String updateCPDefinitionSQL =
			"update CPDefinition set CProductId = ?, version = 1 where " +
				"CPDefinitionId = ?";

		try (PreparedStatement ps1 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, insertCProductSQL);
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, updateCPDefinitionSQL);
			Statement s = connection.createStatement(
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(
				"select cpDefinitionId, groupId, companyId, userId, userName " +
					"from CPDefinition")) {

			while (rs.next()) {
				String uuid = PortalUUIDUtil.generate();
				long cProductId = increment();
				long groupId = rs.getLong("groupId");
				long companyId = rs.getLong("companyId");
				long userId = rs.getLong("userId");
				String userName = rs.getString("userName");
				long cpDefinitionId = rs.getLong("CPDefinitionId");

				ps1.setString(1, uuid);
				ps1.setLong(2, cProductId);
				ps1.setLong(3, groupId);
				ps1.setLong(4, companyId);
				ps1.setLong(5, userId);
				ps1.setString(6, userName);

				Date now = new Date(System.currentTimeMillis());

				ps1.setDate(7, now);
				ps1.setDate(8, now);

				ps1.setLong(9, cpDefinitionId);

				ps1.addBatch();

				ps2.setLong(1, cProductId);
				ps2.setLong(2, cpDefinitionId);

				ps2.addBatch();
			}

			ps1.executeBatch();
			ps2.executeBatch();
		}
	}

}