/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.upgrade.internal;

import com.liferay.portal.configuration.upgrade.PrefsPropsToConfigurationUpgrade;
import com.liferay.portal.configuration.upgrade.PrefsPropsToConfigurationUpgradeItem;
import com.liferay.portal.configuration.upgrade.PrefsPropsValueType;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Dictionary;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;

import org.osgi.service.cm.Configuration;

/**
 * @author     Drew Brokke
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class PrefsPropsToConfigurationUpgradeImpl
	implements PrefsPropsToConfigurationUpgrade {

	@Override
	public void upgradePrefsPropsToConfiguration(
			PortletPreferences portletPreferences, Configuration configuration,
			PrefsPropsToConfigurationUpgradeItem[]
				prefsPropsToConfigurationUpgradeItems)
		throws UpgradeException {

		if (ArrayUtil.isEmpty(prefsPropsToConfigurationUpgradeItems)) {
			return;
		}

		Dictionary<String, Object> properties = configuration.getProperties();

		if (properties == null) {
			properties = new HashMapDictionary<>();
		}

		try {
			for (PrefsPropsToConfigurationUpgradeItem
					prefsPropsToConfigurationUpgradeItem :
						prefsPropsToConfigurationUpgradeItems) {

				String prefsPropsName =
					prefsPropsToConfigurationUpgradeItem.getPrefsPropsName();

				Object value = PrefsPropsUtil.getString(
					portletPreferences, prefsPropsName);

				if (Validator.isNull(value)) {
					continue;
				}

				properties.put(
					prefsPropsToConfigurationUpgradeItem.
						getConfigurationMethodName(),
					_getTypedValue(
						value,
						prefsPropsToConfigurationUpgradeItem.
							getPrefsPropsValueType()));

				portletPreferences.reset(prefsPropsName);
			}

			if (properties.isEmpty()) {
				configuration.delete();
			}
			else {
				configuration.update(properties);
			}
		}
		catch (IOException | ReadOnlyException exception) {
			throw new UpgradeException(exception);
		}
	}

	private static Object _getTypedValue(
		Object value, PrefsPropsValueType prefsPropsValueType) {

		if (prefsPropsValueType == PrefsPropsValueType.BOOLEAN) {
			return GetterUtil.getBoolean(value);
		}
		else if (prefsPropsValueType == PrefsPropsValueType.DOUBLE) {
			return GetterUtil.getDouble(value);
		}
		else if (prefsPropsValueType == PrefsPropsValueType.INT) {
			return GetterUtil.getInteger(value);
		}
		else if (prefsPropsValueType == PrefsPropsValueType.LONG) {
			return GetterUtil.getLong(value);
		}
		else if (prefsPropsValueType == PrefsPropsValueType.SHORT) {
			return GetterUtil.getShort(value);
		}
		else if (prefsPropsValueType == PrefsPropsValueType.STRING_ARRAY) {
			return StringUtil.split((String)value);
		}

		return value;
	}

}