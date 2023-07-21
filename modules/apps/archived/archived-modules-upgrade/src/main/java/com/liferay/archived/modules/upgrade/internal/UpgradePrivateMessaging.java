/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.archived.modules.upgrade.internal;

import com.liferay.message.boards.service.MBThreadLocalService;
import com.liferay.portal.dao.orm.common.SQLTransformer;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Alejandro Tardín
 */
public class UpgradePrivateMessaging extends UpgradeProcess {

	public UpgradePrivateMessaging(MBThreadLocalService mbThreadLocalService) {
		_mbThreadLocalService = mbThreadLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"delete from ClassName_ where value = " +
				"'com.liferay.social.privatemessaging.model.UserThread'");

		runSQL(
			"delete from Portlet where portletId = " +
				"'com_liferay_social_privatemessaging_web_portlet_" +
					"PrivateMessagingPortlet'");

		runSQL(
			"delete from PortletPreferences where portletId =" +
				"'com_liferay_social_privatemessaging_web_portlet_" +
					"PrivateMessagingPortlet'");

		runSQL(
			"delete from Release_ where servletContextName = " +
				"'com.liferay.social.privatemessaging.service'");
		runSQL(
			"delete from Release_ where servletContextName = " +
				"'com.liferay.social.privatemessaging.web'");

		runSQL("delete from ServiceComponent where buildNamespace = 'PM'");

		_deleteThreads();

		runSQL("drop table PM_UserThread");

		LayoutTypeSettingsUtil.removePortletId(
			connection,
			"com_liferay_social_privatemessaging_web_portlet_" +
				"PrivateMessagingPortlet");
	}

	private void _deleteThreads() throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				SQLTransformer.transform(
					"select mbThreadId from PM_UserThread"));
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				_mbThreadLocalService.deleteMBThread(rs.getLong(1));
			}
		}
	}

	private final MBThreadLocalService _mbThreadLocalService;

}