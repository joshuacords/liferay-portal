/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.internal.upgrade.v3_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.segments.constants.SegmentsExperienceConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author David Arques
 */
public class UpgradeLayoutPageTemplateStructure extends UpgradeProcess {

	protected void alterTable() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			runSQL("alter table LayoutPageTemplateStructure drop column data_");
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeSchema();

		upgradeLayoutPageTemplatesStructures();

		alterTable();
	}

	protected void upgradeLayoutPageTemplatesStructures() throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append("select layoutPageTemplateStructureId, groupId, companyId, ");
		sb.append("userId, userName, createDate, data_ from ");
		sb.append("LayoutPageTemplateStructure");

		try (PreparedStatement ps = connection.prepareStatement(
				sb.toString())) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					long layoutPageTemplateStructureId = rs.getLong(
						"layoutPageTemplateStructureId");
					long groupId = rs.getLong("groupId");
					long companyId = rs.getLong("companyId");
					long userId = rs.getLong("userId");
					String userName = rs.getString("userName");
					Timestamp createDate = rs.getTimestamp("createDate");
					String data = rs.getString("data_");

					_updateLayoutPageTemplateStructureRels(
						groupId, companyId, userId, userName, createDate,
						layoutPageTemplateStructureId, data);
				}
			}
		}
	}

	protected void upgradeSchema() throws Exception {
		String template = StringUtil.read(
			UpgradeLayoutPageTemplateStructure.class.getResourceAsStream(
				"dependencies/update.sql"));

		runSQLTemplateString(template, false, false);
	}

	private void _updateLayoutPageTemplateStructureRels(
		long groupId, long companyId, long userId, String userName,
		Timestamp createDate, long layoutPageTemplateStructureId, String data) {

		StringBundler sb = new StringBundler(5);

		sb.append("insert into LayoutPageTemplateStructureRel (uuid_, ");
		sb.append("lPageTemplateStructureRelId, groupId, companyId, userId, ");
		sb.append("userName, createDate, modifiedDate, ");
		sb.append("layoutPageTemplateStructureId, segmentsExperienceId, ");
		sb.append("data_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		String sql = sb.toString();

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(sql);

			ps.setString(1, PortalUUIDUtil.generate());
			ps.setLong(2, increment());
			ps.setLong(3, groupId);
			ps.setLong(4, companyId);
			ps.setLong(5, userId);
			ps.setString(6, userName);
			ps.setTimestamp(7, createDate);
			ps.setTimestamp(8, createDate);
			ps.setLong(9, layoutPageTemplateStructureId);
			ps.setLong(10, SegmentsExperienceConstants.ID_DEFAULT);
			ps.setString(11, data);

			ps.executeUpdate();
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}
		finally {
			DataAccess.cleanUp(ps);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeLayoutPageTemplateStructure.class);

}