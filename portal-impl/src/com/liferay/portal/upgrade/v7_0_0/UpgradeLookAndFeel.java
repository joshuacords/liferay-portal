/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portal.kernel.util.GetterUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Eduardo García
 */
public class UpgradeLookAndFeel extends BaseUpgradePortletPreferences {

	@Override
	protected String getUpdatePortletPreferencesWhereClause() {
		return "preferences like '%portletSetupShowBorders%'";
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		boolean showBorders = GetterUtil.getBoolean(
			portletPreferences.getValue("portletSetupShowBorders", null), true);

		if (!showBorders) {
			portletPreferences.setValue(
				"portletSetupPortletDecoratorId", "borderless");
		}

		portletPreferences.reset("portletSetupShowBorders");

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

}