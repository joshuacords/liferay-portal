/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.upgrade;

import com.liferay.portal.kernel.upgrade.UpgradeException;

import javax.portlet.PortletPreferences;

import org.osgi.service.cm.Configuration;

/**
 * @author     Drew Brokke
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             PrefsPropsToConfigurationUpgradeHelper}
 */
@Deprecated
public interface PrefsPropsToConfigurationUpgrade {

	public void upgradePrefsPropsToConfiguration(
			PortletPreferences portletPreferences, Configuration configuration,
			PrefsPropsToConfigurationUpgradeItem...
				prefsPropsToConfigurationUpgradeItems)
		throws UpgradeException;

}