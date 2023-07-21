/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.upgrade.v1_8_0;

import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Alec Sloan
 */
public class CPAttachmentFileEntryUpgradeProcess extends UpgradeProcess {

	public CPAttachmentFileEntryUpgradeProcess(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		long classNameId = _classNameLocalService.getClassNameId(
			CPDefinition.class);

		String updateCPAttachmentFileEntrySQL =
			"update CPAttachmentFileEntry set groupId = ? where classNameId " +
				"= ? and classPK = ?";

		String selectCPDefinitionSQL =
			"select CPDefinition.groupId, CPDefinitionId from CPDefinition " +
				"inner join CPAttachmentFileEntry on CPDefinitionId = classPK";

		try (PreparedStatement ps =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, updateCPAttachmentFileEntrySQL);
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(selectCPDefinitionSQL)) {

			while (rs.next()) {
				ps.setLong(1, rs.getLong("groupId"));
				ps.setLong(2, classNameId);
				ps.setLong(3, rs.getLong("CPDefinitionId"));

				ps.addBatch();
			}

			ps.executeBatch();
		}
	}

	private final ClassNameLocalService _classNameLocalService;

}