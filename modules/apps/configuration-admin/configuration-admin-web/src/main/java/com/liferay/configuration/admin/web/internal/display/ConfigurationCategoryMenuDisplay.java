/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.configuration.admin.web.internal.display;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Jorge Ferrer
 */
public class ConfigurationCategoryMenuDisplay {

	public ConfigurationCategoryMenuDisplay(
		ConfigurationCategoryDisplay configurationCategoryDisplay,
		Set<ConfigurationEntry> configurationEntries) {

		_configurationCategoryDisplay = configurationCategoryDisplay;

		Stream<String> scopeStream = Arrays.stream(_UI_ORDERED_SCOPES);

		scopeStream.forEach(
			scopeKey -> _configurationScopeDisplays.put(
				scopeKey, new ConfigurationScopeDisplay(scopeKey)));

		for (ConfigurationEntry configurationEntry : configurationEntries) {
			_addConfigurationEntry(configurationEntry);
		}
	}

	public ConfigurationCategoryDisplay getConfigurationCategoryDisplay() {
		return _configurationCategoryDisplay;
	}

	public Collection<ConfigurationScopeDisplay>
		getConfigurationScopeDisplays() {

		return _configurationScopeDisplays.values();
	}

	public ConfigurationEntry getFirstConfigurationEntry() {
		for (ConfigurationScopeDisplay configurationScopeDisplay :
				_configurationScopeDisplays.values()) {

			List<ConfigurationEntry> configurationEntries =
				configurationScopeDisplay.getConfigurationEntries();

			if (!configurationEntries.isEmpty()) {
				return configurationEntries.get(0);
			}
		}

		return null;
	}

	public boolean isEmpty() {
		for (ConfigurationScopeDisplay configurationScopeDisplay :
				_configurationScopeDisplays.values()) {

			if (!configurationScopeDisplay.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	private void _addConfigurationEntry(ConfigurationEntry configurationEntry) {
		ConfigurationScopeDisplay configurationScopeDisplay =
			_configurationScopeDisplays.get(configurationEntry.getScope());

		if (configurationScopeDisplay == null) {
			configurationScopeDisplay = new ConfigurationScopeDisplay(
				configurationEntry.getScope());

			_configurationScopeDisplays.put(
				configurationEntry.getScope(), configurationScopeDisplay);
		}

		configurationScopeDisplay.add(configurationEntry);
	}

	private static final String[] _UI_ORDERED_SCOPES = {
		ExtendedObjectClassDefinition.Scope.SYSTEM.toString(),
		ExtendedObjectClassDefinition.Scope.COMPANY.toString(),
		ExtendedObjectClassDefinition.Scope.GROUP.toString(),
		ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE.toString()
	};

	private final ConfigurationCategoryDisplay _configurationCategoryDisplay;
	private final Map<String, ConfigurationScopeDisplay>
		_configurationScopeDisplays = new LinkedHashMap<>();

}