/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.upgrade.v1_3_0;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Ethan Bustad
 * @author Alessio Antonio Rendina
 */
public class CPFriendlyURLEntryUpgradeProcess extends UpgradeProcess {

	public CPFriendlyURLEntryUpgradeProcess(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		long cpDefinitionClassNameId = _classNameLocalService.getClassNameId(
			CPDefinition.class);
		long cProductClassNameId = _classNameLocalService.getClassNameId(
			CProduct.class);

		String updateCPFriendlyURLSQL =
			"update CPFriendlyURLEntry set classNameId = ?, classPK = ? " +
				"where classNameId = ? and classPK = ?";

		String selectCPFriendlyURLEntrySQL = StringBundler.concat(
			"select distinct classPK, CProductId from CPFriendlyURLEntry ",
			"inner join CPDefinition on CPDefinition.CPDefinitionId = ",
			"CPFriendlyURLEntry.classPK where classNameId = ",
			String.valueOf(cpDefinitionClassNameId));

		try (PreparedStatement ps =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, updateCPFriendlyURLSQL);
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(selectCPFriendlyURLEntrySQL)) {

			while (rs.next()) {
				long classPK = rs.getLong("classPK");
				long cProductId = rs.getLong("CProductId");

				ps.setLong(1, cProductClassNameId);
				ps.setLong(2, cProductId);
				ps.setLong(3, cpDefinitionClassNameId);
				ps.setLong(4, classPK);

				ps.addBatch();
			}

			ps.executeBatch();
		}
	}

	private final ClassNameLocalService _classNameLocalService;

}