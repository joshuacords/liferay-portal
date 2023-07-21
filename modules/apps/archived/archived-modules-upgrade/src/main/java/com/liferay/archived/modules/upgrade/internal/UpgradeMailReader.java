/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.archived.modules.upgrade.internal;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Preston Crary
 */
public class UpgradeMailReader extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"delete from ClassName_ where value = " +
				"'com.liferay.mail.reader.model.Account'");
		runSQL(
			"delete from ClassName_ where value = " +
				"'com.liferay.mail.reader.model.Attachment'");
		runSQL(
			"delete from ClassName_ where value = " +
				"'com.liferay.mail.reader.model.Folder'");
		runSQL(
			"delete from ClassName_ where value = " +
				"'com.liferay.mail.reader.model.Message'");

		LayoutTypeSettingsUtil.removePortletId(
			connection, "com_liferay_mail_reader_web_portlet_MailPortlet");

		runSQL(
			"delete from Portlet where portletId = " +
				"'com_liferay_mail_reader_web_portlet_MailPortlet'");

		runSQL(
			"delete from PortletPreferences where portletId = " +
				"'com_liferay_mail_reader_web_portlet_MailPortlet'");

		runSQL(
			"delete from Release_ where servletContextName = " +
				"'com.liferay.mail.reader.service'");
		runSQL(
			"delete from Release_ where servletContextName = " +
				"'com.liferay.mail.reader.web'");

		runSQL(
			"delete from ResourcePermission where name = " +
				"'com.liferay.mail.reader.model.Account'");
		runSQL(
			"delete from ResourcePermission where name = " +
				"'com.liferay.mail.reader.model.Attachment'");
		runSQL(
			"delete from ResourcePermission where name = " +
				"'com.liferay.mail.reader.model.Folder'");
		runSQL(
			"delete from ResourcePermission where name = " +
				"'com.liferay.mail.reader.model.Message'");

		runSQL("delete from ServiceComponent where buildNamespace = 'Mail'");

		runSQL("drop table Mail_Account");
		runSQL("drop table Mail_Attachment");
		runSQL("drop table Mail_Folder");
		runSQL("drop table Mail_Message");
	}

}