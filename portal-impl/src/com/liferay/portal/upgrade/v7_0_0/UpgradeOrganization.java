/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.upgrade.v7_0_0.util.OrganizationTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Brian Wing Shun Chan
 * @author Christopher Kian
 */
public class UpgradeOrganization extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alter(OrganizationTable.class, new AlterColumnType("statusId", "LONG"));

		upgradeOrganizationLogoId();
		upgradeOrganizationSiteHierarchy();
	}

	protected void upgradeOrganizationLogoId() throws SQLException {
		try (PreparedStatement ps1 = connection.prepareStatement(
				"select groupId, logoId from LayoutSet where logoId > 0 and " +
					"privateLayout = ?");
			PreparedStatement ps2 = connection.prepareStatement(
				"select classPK from Group_ where groupId = ?");
			PreparedStatement ps3 = connection.prepareStatement(
				"update Organization_ set logoId = ? where organizationId = " +
					"?")) {

			ps1.setBoolean(1, false);

			ResultSet rs1 = ps1.executeQuery();

			while (rs1.next()) {
				long groupId = rs1.getLong("groupId");
				long logoId = rs1.getLong("logoId");

				ps2.setLong(1, groupId);

				ResultSet rs2 = ps2.executeQuery();

				while (rs2.next()) {
					long classPK = rs2.getLong("classPK");

					ps3.setLong(1, logoId);
					ps3.setLong(2, classPK);

					ps3.executeUpdate();
				}
			}
		}
	}

	protected void upgradeOrganizationSiteHierarchy() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement preparedStatement1 = connection.prepareStatement(
				StringBundler.concat(
					"select groupId, organizationId, parentOrganizationId, ",
					"site, Organization_.treePath as treePath from Group_, ",
					"Organization_ where classNameId = ",
					PortalUtil.getClassNameId(Organization.class.getName()),
					" and Group_.classPK = Organization_.organizationId"));
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.autoBatch(
					connection.prepareStatement(
						"update Group_ set parentGroupId = ?, treePath = ? " +
							"where groupId = ?"))) {

			List<OrganizationGroup> organizationGroups = new ArrayList<>();

			try (ResultSet resultSet = preparedStatement1.executeQuery()) {
				while (resultSet.next()) {
					organizationGroups.add(
						new OrganizationGroup(
							resultSet.getLong("groupId"),
							resultSet.getLong("organizationId"),
							resultSet.getString("treePath"),
							resultSet.getLong("parentOrganizationId"),
							resultSet.getBoolean("site")));
				}
			}

			for (OrganizationGroup organizationGroup : organizationGroups) {
				if (!organizationGroup._site) {
					continue;
				}

				List<String> treePaths = StringUtil.split(
					organizationGroup._organizationTreePath, CharPool.SLASH);

				Stream<String> stream = treePaths.stream();

				String groupTreePath = stream.filter(
					organizationId -> organizationId.length() > 0
				).map(
					organizationId -> String.valueOf(
						OrganizationGroup.getGroupId(organizationId))
				).collect(
					Collectors.joining(
						StringPool.SLASH, StringPool.SLASH, StringPool.SLASH)
				);

				preparedStatement2.setLong(
					1,
					OrganizationGroup.getGroupId(
						organizationGroup._parentOrganizationId));
				preparedStatement2.setString(2, groupTreePath);
				preparedStatement2.setLong(3, organizationGroup._groupId);

				preparedStatement2.addBatch();
			}

			preparedStatement2.executeBatch();
		}
	}

	private static class OrganizationGroup {

		public static long getGroupId(long organizationId) {
			return getGroupId(String.valueOf(organizationId));
		}

		public static long getGroupId(String organizationId) {
			return _groupIds.get(organizationId);
		}

		public OrganizationGroup(
			long groupId, long organizationId, String organizationTreePath,
			long parentOrganizationId, boolean site) {

			_groupId = groupId;
			_organizationId = organizationId;
			_organizationTreePath = organizationTreePath;
			_parentOrganizationId = parentOrganizationId;
			_site = site;

			_groupIds.put(String.valueOf(_organizationId), _groupId);
		}

		private static final Map<String, Long> _groupIds = HashMapBuilder.put(
			String.valueOf(
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID),
			GroupConstants.DEFAULT_PARENT_GROUP_ID
		).build();

		private final long _groupId;
		private final long _organizationId;
		private final String _organizationTreePath;
		private final long _parentOrganizationId;
		private final boolean _site;

	}

}