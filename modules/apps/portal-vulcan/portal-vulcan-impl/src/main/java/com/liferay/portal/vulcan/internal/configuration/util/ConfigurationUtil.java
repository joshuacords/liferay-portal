/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.internal.configuration.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.vulcan.internal.configuration.VulcanConfiguration;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * @author Javier Gamarra
 */
public class ConfigurationUtil {

	public static Map<String, Configuration> getConfigurations(
		ConfigurationAdmin configurationAdmin) {

		Map<String, Configuration> configurations = new HashMap<>();

		try {
			String filterString = String.format(
				"(service.factoryPid=%s)", VulcanConfiguration.class.getName());

			for (Configuration configuration :
					configurationAdmin.listConfigurations(filterString)) {

				Dictionary<String, Object> properties =
					configuration.getProperties();

				configurations.put(
					(String)properties.get("path"), configuration);
			}
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}

		return configurations;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ConfigurationUtil.class);

}