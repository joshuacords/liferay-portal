/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.page.internal.upgrade.v2_2_2;

import com.liferay.asset.display.page.constants.AssetDisplayPageConstants;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Samuel Ziemer
 */
public class UpgradeAssetDisplayPageEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb1 = new StringBundler(12);

		sb1.append("select groupId, companyId, userId, userName, ");
		sb1.append("classNameId, classPK from AssetEntry where classNameId ");
		sb1.append("in (");

		long blogsClassNameId = PortalUtil.getClassNameId(
			"com.liferay.blogs.kernel.model.BlogsEntry");

		sb1.append(blogsClassNameId);

		sb1.append(", ");

		long dlFileEntryClassNameId = PortalUtil.getClassNameId(
			"com.liferay.document.library.kernel.model.DLFileEntry");

		sb1.append(dlFileEntryClassNameId);

		sb1.append(") and classPK not in (select classPK from ");
		sb1.append("AssetDisplayPageEntry where classNameId in (");
		sb1.append(blogsClassNameId);
		sb1.append(", ");
		sb1.append(dlFileEntryClassNameId);
		sb1.append("))");

		StringBundler sb2 = new StringBundler(5);

		sb2.append("insert into AssetDisplayPageEntry (uuid_, ");
		sb2.append("assetDisplayPageEntryId, groupId, companyId, userId, ");
		sb2.append("userName, createDate, modifiedDate, classNameId, ");
		sb2.append("classPK, layoutPageTemplateEntryId, type_, plid) values( ");
		sb2.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		try (PreparedStatement ps1 = connection.prepareStatement(
				sb1.toString());
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, sb2.toString())) {

			try (ResultSet rs = ps1.executeQuery()) {
				while (rs.next()) {
					Timestamp now = new Timestamp(System.currentTimeMillis());

					ps2.setString(1, PortalUUIDUtil.generate());
					ps2.setLong(2, increment());
					ps2.setLong(3, rs.getLong("groupId"));
					ps2.setLong(4, rs.getLong("companyId"));
					ps2.setLong(5, rs.getLong("userId"));
					ps2.setString(6, rs.getString("userName"));
					ps2.setTimestamp(7, now);
					ps2.setTimestamp(8, now);
					ps2.setLong(9, rs.getLong("classNameId"));
					ps2.setLong(10, rs.getLong("classPK"));
					ps2.setLong(11, 0);
					ps2.setLong(12, AssetDisplayPageConstants.TYPE_DEFAULT);
					ps2.setLong(13, 0);

					ps2.addBatch();
				}

				ps2.executeBatch();
			}
		}
	}

}