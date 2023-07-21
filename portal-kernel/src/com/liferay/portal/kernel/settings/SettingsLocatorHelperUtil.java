/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.settings;

import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerList;

/**
 * @author Iván Zaera
 */
public class SettingsLocatorHelperUtil {

	public static Settings getCompanyConfigurationBeanSettings(
		long companyId, String configurationPid, Settings parentSettings) {

		return getSettingsLocatorHelper().getCompanyConfigurationBeanSettings(
			companyId, configurationPid, parentSettings);
	}

	public static Settings getCompanyPortletPreferencesSettings(
		long companyId, String settingsId, Settings parentSettings) {

		return getSettingsLocatorHelper().getCompanyPortletPreferencesSettings(
			companyId, settingsId, parentSettings);
	}

	public static Settings getGroupConfigurationBeanSettings(
		long groupId, String configurationPid, Settings parentSettings) {

		return getSettingsLocatorHelper().getGroupConfigurationBeanSettings(
			groupId, configurationPid, parentSettings);
	}

	public static Settings getPortletInstanceConfigurationBeanSettings(
		String portletId, String configurationPid, Settings parentSettings) {

		return getSettingsLocatorHelper().
			getPortletInstanceConfigurationBeanSettings(
				portletId, configurationPid, parentSettings);
	}

	public static SettingsLocatorHelper getSettingsLocatorHelper() {
		return _settingsLocatorHelpers.get(0);
	}

	public Settings getConfigurationBeanSettings(String settingsId) {
		return getSettingsLocatorHelper().getConfigurationBeanSettings(
			settingsId);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #getConfigurationBeanSettings(String)}
	 */
	@Deprecated
	public Settings getConfigurationBeanSettings(
		String settingsId, Settings parentSettings) {

		return getSettingsLocatorHelper().getConfigurationBeanSettings(
			settingsId, parentSettings);
	}

	public Settings getGroupPortletPreferencesSettings(
		long groupId, String settingsId, Settings parentSettings) {

		return getSettingsLocatorHelper().getGroupPortletPreferencesSettings(
			groupId, settingsId, parentSettings);
	}

	public Settings getPortalPreferencesSettings(
		long companyId, Settings parentSettings) {

		return getSettingsLocatorHelper().getPortalPreferencesSettings(
			companyId, parentSettings);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public Settings getPortalPropertiesSettings() {
		return getSettingsLocatorHelper().getPortalPropertiesSettings();
	}

	public Settings getPortletInstancePortletPreferencesSettings(
		long companyId, long plid, String portletId, Settings parentSettings) {

		return getSettingsLocatorHelper().
			getPortletInstancePortletPreferencesSettings(
				companyId, plid, portletId, parentSettings);
	}

	public Settings getServerSettings(String settingsId) {
		return getSettingsLocatorHelper().getServerSettings(settingsId);
	}

	private static final ServiceTrackerList<SettingsLocatorHelper>
		_settingsLocatorHelpers = ServiceTrackerCollections.openList(
			SettingsLocatorHelper.class);

}