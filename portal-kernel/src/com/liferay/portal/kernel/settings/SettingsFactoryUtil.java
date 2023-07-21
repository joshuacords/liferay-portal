/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.settings;

import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerList;

import java.util.List;

/**
 * @author Raymond Augé
 * @author Jorge Ferrer
 */
public class SettingsFactoryUtil {

	public static ArchivedSettings getPortletInstanceArchivedSettings(
			long groupId, String portletId, String name)
		throws SettingsException {

		return getSettingsFactory().getPortletInstanceArchivedSettings(
			groupId, portletId, name);
	}

	public static List<ArchivedSettings> getPortletInstanceArchivedSettingsList(
		long groupId, String portletId) {

		return getSettingsFactory().getPortletInstanceArchivedSettingsList(
			groupId, portletId);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             SettingsLocatorHelperUtil#getServerSettings(String)}
	 */
	@Deprecated
	public static Settings getServerSettings(String settingsId) {
		return getSettingsFactory().getServerSettings(settingsId);
	}

	public static Settings getSettings(SettingsLocator settingsLocator)
		throws SettingsException {

		return getSettingsFactory().getSettings(settingsLocator);
	}

	public static SettingsDescriptor getSettingsDescriptor(String settingsId) {
		return getSettingsFactory().getSettingsDescriptor(settingsId);
	}

	public static SettingsFactory getSettingsFactory() {
		return _settingsFactories.get(0);
	}

	public static void registerSettingsMetadata(
		Class<?> settingsClass, Object configurationBean,
		FallbackKeys fallbackKeys) {

		getSettingsFactory().registerSettingsMetadata(
			settingsClass, configurationBean, fallbackKeys);
	}

	private static final ServiceTrackerList<SettingsFactory>
		_settingsFactories = ServiceTrackerCollections.openList(
			SettingsFactory.class);

}