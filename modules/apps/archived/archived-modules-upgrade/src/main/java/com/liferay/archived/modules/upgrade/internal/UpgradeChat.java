/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.archived.modules.upgrade.internal;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Preston Crary
 */
public class UpgradeChat extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"delete from ClassName_ where value = " +
				"'com.liferay.chat.model.Entry'");
		runSQL(
			"delete from ClassName_ where value = " +
				"'com.liferay.chat.model.Status'");

		runSQL(
			"delete from Portlet where portletId = " +
				"'com_liferay_chat_web_portlet_ChatPortlet'");

		runSQL(
			"delete from PortletPreferences where portletId =" +
				"'com_liferay_chat_web_portlet_ChatPortlet'");

		runSQL(
			"delete from Release_ where servletContextName = " +
				"'com.liferay.chat.service'");
		runSQL(
			"delete from Release_ where servletContextName = " +
				"'com.liferay.chat.web'");

		runSQL("delete from ServiceComponent where buildNamespace = 'Chat'");

		runSQL("drop table Chat_Entry");
		runSQL("drop table Chat_Status");
	}

}