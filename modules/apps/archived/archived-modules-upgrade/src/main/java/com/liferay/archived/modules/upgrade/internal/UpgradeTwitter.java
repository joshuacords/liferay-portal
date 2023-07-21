/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.archived.modules.upgrade.internal;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Alejandro Tardín
 */
public class UpgradeTwitter extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"delete from ClassName_ where value = " +
				"'com.liferay.twitter.model.Feed'");

		LayoutTypeSettingsUtil.removePortletId(
			connection, "com_liferay_twitter_web_portlet_TwitterPortlet");

		runSQL(
			"delete from Portlet where portletId = " +
				"'com_liferay_twitter_web_portlet_TwitterPortlet'");

		runSQL(
			"delete from PortletPreferences where portletId = " +
				"'com_liferay_twitter_web_portlet_TwitterPortlet'");

		runSQL(
			"delete from Release_ where servletContextName = " +
				"'com.liferay.twitter.service'");
		runSQL(
			"delete from Release_ where servletContextName = " +
				"'com.liferay.twitter.web'");

		runSQL(
			"delete from ResourcePermission where name = " +
				"'com.liferay.twitter.model.Feed'");

		runSQL("delete from ServiceComponent where buildNamespace = 'Twitter'");

		runSQL("drop table Twitter_Feed");
	}

}