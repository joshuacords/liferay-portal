/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.ldap.internal.configuration;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.ldap.configuration.ConfigurationProvider;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationEvent;
import org.osgi.service.cm.ConfigurationListener;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = ConfigurationListener.class)
public class LDAPConfigurationListener implements ConfigurationListener {

	@Override
	public void configurationEvent(ConfigurationEvent configurationEvent) {
		if (Validator.isNull(configurationEvent.getFactoryPid()) ||
			!_configurationProviders.containsKey(
				configurationEvent.getFactoryPid())) {

			return;
		}

		ConfigurationProvider<?> configurationProvider =
			_configurationProviders.get(configurationEvent.getFactoryPid());

		try {
			if (configurationEvent.getType() == ConfigurationEvent.CM_DELETED) {
				configurationProvider.unregisterConfiguration(
					configurationEvent.getPid());
			}
			else {
				Configuration configuration =
					_configurationAdmin.getConfiguration(
						configurationEvent.getPid(), StringPool.QUESTION);

				configurationProvider.registerConfiguration(configuration);
			}
		}
		catch (IOException ioException) {
			throw new SystemException(
				"Unable to load configuration " + configurationEvent.getPid(),
				ioException);
		}
	}

	@Deactivate
	protected void deactivate() {
		_configurationProviders.clear();
	}

	@Reference(unbind = "-")
	protected void setConfigurationAdmin(
		ConfigurationAdmin configurationAdmin) {

		_configurationAdmin = configurationAdmin;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected synchronized void setConfigurationProvider(
		ConfigurationProvider<?> configurationProvider,
		Map<String, Object> properties) {

		String factoryPid = MapUtil.getString(properties, "factoryPid");

		if (Validator.isNull(factoryPid)) {
			throw new IllegalArgumentException(
				"No factory PID specified for configuration provider " +
					configurationProvider);
		}

		_configurationProviders.put(factoryPid, configurationProvider);

		try {
			Configuration[] configurations =
				_configurationAdmin.listConfigurations(
					"(service.factoryPid=" + factoryPid + ")");

			if (configurations != null) {
				for (Configuration configuration : configurations) {
					configurationProvider.registerConfiguration(configuration);
				}
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to register configurations", exception);
			}
		}
	}

	protected synchronized void unsetConfigurationProvider(
		ConfigurationProvider<?> configurationProvider,
		Map<String, Object> properties) {

		String factoryPid = MapUtil.getString(properties, "factoryPid");

		_configurationProviders.remove(factoryPid);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LDAPConfigurationListener.class);

	private ConfigurationAdmin _configurationAdmin;
	private final Map<String, ConfigurationProvider<?>>
		_configurationProviders = new HashMap<>();

}