/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.settings;

import java.util.List;

/**
 * @author Raymond Augé
 * @author Jorge Ferrer
 */
public interface SettingsFactory {

	public ArchivedSettings getPortletInstanceArchivedSettings(
			long groupId, String portletId, String name)
		throws SettingsException;

	public List<ArchivedSettings> getPortletInstanceArchivedSettingsList(
		long groupId, String portletId);

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             SettingsLocatorHelper#getServerSettings(String)}
	 */
	@Deprecated
	public Settings getServerSettings(String settingsId);

	public Settings getSettings(SettingsLocator settingsLocator)
		throws SettingsException;

	public SettingsDescriptor getSettingsDescriptor(String settingsId);

	public void registerSettingsMetadata(
		Class<?> settingsClass, Object configurationBean,
		FallbackKeys fallbackKeys);

}