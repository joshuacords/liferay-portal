/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.upgrade.v1_9_0;

import com.liferay.commerce.product.internal.upgrade.base.BaseCommerceProductServiceUpgradeProcess;
import com.liferay.commerce.product.model.impl.CPDefinitionOptionRelModelImpl;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Marco Leo
 */
public class CPDefinitionOptionRelUpgradeProcess
	extends BaseCommerceProductServiceUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		addColumn(
			CPDefinitionOptionRelModelImpl.class,
			CPDefinitionOptionRelModelImpl.TABLE_NAME, "key_", "VARCHAR(75)");

		String selectCPOptionSQL =
			"select distinct CPOptionId, key_  from CPOption";
		String updateCPDefinitionOptionRelSQL =
			"update CPDefinitionOptionRel set key_ = ? WHERE CPOptionId = ?";

		try (PreparedStatement ps =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, updateCPDefinitionOptionRelSQL);
			Statement s = connection.createStatement(
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery(selectCPOptionSQL)) {

			while (rs.next()) {
				ps.setString(1, rs.getString("key_"));
				ps.setLong(2, rs.getLong("CPOptionId"));

				ps.addBatch();
			}

			ps.executeBatch();
		}
	}

}