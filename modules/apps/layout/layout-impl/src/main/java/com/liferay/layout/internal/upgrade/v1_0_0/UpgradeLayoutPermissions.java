/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.upgrade.v1_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.dao.orm.common.SQLTransformer;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourceLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Michael Bowerman
 */
public class UpgradeLayoutPermissions extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(9);

		sb.append("select Layout.companyId, Layout.plid, Layout.privateLayout");
		sb.append(", Layout.groupId, Layout.userId from Layout left join ");
		sb.append("ResourcePermission on (ResourcePermission.companyId = ");
		sb.append("Layout.companyId and ResourcePermission.name = '");
		sb.append(Layout.class.getName());
		sb.append("' and ResourcePermission.scope = ");
		sb.append(ResourceConstants.SCOPE_INDIVIDUAL);
		sb.append(" and ResourcePermission.primKeyId = Layout.plid) where ");
		sb.append("ResourcePermission.resourcePermissionId is null");

		String sql = SQLTransformer.transform(sb.toString());

		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long companyId = rs.getLong("companyId");
				long groupId = rs.getLong("groupId");
				long plid = rs.getLong("plid");
				boolean privateLayout = rs.getBoolean("privateLayout");
				long userId = rs.getLong("userId");

				boolean addGroupPermission = true;
				boolean addGuestPermission = true;

				if (privateLayout) {
					addGuestPermission = false;

					Group group = GroupLocalServiceUtil.getGroup(groupId);

					if (group.isUser() || group.isUserGroup()) {
						addGroupPermission = false;
					}
				}

				ResourceLocalServiceUtil.addResources(
					companyId, groupId, userId, Layout.class.getName(), plid,
					false, addGroupPermission, addGuestPermission);
			}
		}
	}

}