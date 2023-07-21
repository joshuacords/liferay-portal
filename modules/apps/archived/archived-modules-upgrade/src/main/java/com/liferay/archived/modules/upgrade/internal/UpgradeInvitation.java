/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.archived.modules.upgrade.internal;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Adolfo Pérez
 */
public class UpgradeInvitation extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		LayoutTypeSettingsUtil.removePortletId(
			connection, "com_liferay_invitation_web_portlet_InvitationPortlet");

		runSQL(
			"delete from Portlet where portletId = " +
				"'com_liferay_invitation_web_portlet_InvitationPortlet'");

		runSQL(
			"delete from PortletPreferences where portletId =" +
				"'com_liferay_invitation_web_portlet_InvitationPortlet'");

		runSQL(
			"delete from Release_ where servletContextName = " +
				"'com.liferay.invitation.web'");
	}

}