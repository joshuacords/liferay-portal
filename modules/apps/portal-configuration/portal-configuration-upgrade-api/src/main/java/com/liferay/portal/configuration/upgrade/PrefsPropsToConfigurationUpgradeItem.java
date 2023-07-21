/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.upgrade;

/**
 * @author     Drew Brokke
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class PrefsPropsToConfigurationUpgradeItem {

	public PrefsPropsToConfigurationUpgradeItem(
		String prefsPropsName, PrefsPropsValueType prefsPropsValueType,
		String configurationMethodName) {

		_prefsPropsName = prefsPropsName;
		_prefsPropsValueType = prefsPropsValueType;
		_configurationMethodName = configurationMethodName;
	}

	public PrefsPropsToConfigurationUpgradeItem(
		String prefsPropsName, String configurationMethodName) {

		this(
			prefsPropsName, PrefsPropsValueType.STRING,
			configurationMethodName);
	}

	public String getConfigurationMethodName() {
		return _configurationMethodName;
	}

	public String getPrefsPropsName() {
		return _prefsPropsName;
	}

	public PrefsPropsValueType getPrefsPropsValueType() {
		return _prefsPropsValueType;
	}

	private final String _configurationMethodName;
	private final String _prefsPropsName;
	private final PrefsPropsValueType _prefsPropsValueType;

}