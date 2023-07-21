/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.upgrade;

import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.upgrade.v6_2_0.BaseUpgradePortletPreferences;

import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Marcellus Tavares
 */
public abstract class RenameUpgradePortletPreferences
	extends BaseUpgradePortletPreferences {

	protected abstract Map<String, String> getPreferenceNamesMap();

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences preferences = PortletPreferencesFactoryUtil.fromXML(
			companyId, ownerId, ownerType, plid, portletId, xml);

		Map<String, String[]> preferencesMap = preferences.getMap();

		Map<String, String> preferenceNamesMap = getPreferenceNamesMap();

		for (Map.Entry<String, String> entry : preferenceNamesMap.entrySet()) {
			String name = entry.getKey();

			String[] values = preferencesMap.get(name);

			if (values == null) {
				continue;
			}

			preferences.reset(name);

			String newName = entry.getValue();

			String[] newValues = preferencesMap.get(newName);

			if (newValues == null) {
				preferences.setValues(newName, values);
			}
		}

		return PortletPreferencesFactoryUtil.toXML(preferences);
	}

}