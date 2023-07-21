/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.internal.upgrade.v1_11_0;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.exception.NoSuchClassNameException;
import com.liferay.portal.kernel.exception.NoSuchGroupException;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Alec Sloan
 */
public class CPAttachmentFileEntryGroupUpgradeProcess extends UpgradeProcess {

	public CPAttachmentFileEntryGroupUpgradeProcess(
		AssetCategoryLocalService assetCategoryLocalService,
		ClassNameLocalService classNameLocalService) {

		_assetCategoryLocalService = assetCategoryLocalService;
		_classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try (Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(
				"select classNameId, classPK from CPAttachmentFileEntry")) {

			long cpDefinitionClassNameId = _getCPDefinitionClassNameId();
			long assetCategoryClassNameId =
				_classNameLocalService.getClassNameId(AssetCategory.class);

			String updateCPAttachmentFileEntrySQL =
				"update CPAttachmentFileEntry set groupId = ? where " +
					"classNameId = ? and classPK = ?";

			try (PreparedStatement ps =
					AutoBatchPreparedStatementUtil.concurrentAutoBatch(
						connection, updateCPAttachmentFileEntrySQL)) {

				while (rs.next()) {
					long classNameId = rs.getLong("classNameId");
					long classPK = rs.getLong("classPK");

					long groupId;

					if (classNameId == assetCategoryClassNameId) {
						AssetCategory assetCategory =
							_assetCategoryLocalService.getAssetCategory(
								classPK);

						groupId = assetCategory.getGroupId();
					}
					else if (classNameId == cpDefinitionClassNameId) {
						groupId = _getGroupIdFromCPDefinition(classPK);
					}
					else {
						continue;
					}

					ps.setLong(1, groupId);
					ps.setLong(2, classNameId);
					ps.setLong(3, classPK);

					ps.addBatch();
				}

				ps.executeBatch();
			}
		}
	}

	private long _getCPDefinitionClassNameId() throws Exception {
		try (Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(
				"select classNameId from ClassName_ where value = " +
					"'com.liferay.commerce.product.model.CPDefinition'")) {

			if (rs.next()) {
				return rs.getLong("classNameId");
			}
		}

		throw new NoSuchClassNameException();
	}

	private long _getGroupIdFromCPDefinition(long cpDefinitionId)
		throws Exception {

		try (Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(
				"select groupId from CPDefinition where cpDefinitionId = " +
					cpDefinitionId)) {

			if (rs.next()) {
				return rs.getLong("groupId");
			}
		}

		throw new NoSuchGroupException();
	}

	private final AssetCategoryLocalService _assetCategoryLocalService;
	private final ClassNameLocalService _classNameLocalService;

}