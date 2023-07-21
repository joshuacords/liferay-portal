/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.spring.extender.internal.configuration;

import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;

import java.net.URL;

/**
 * @author Preston Crary
 */
public class ConfigurationUtil {

	public static Configuration getConfiguration(
		ClassLoader classLoader, String name) {

		if (hasConfiguration(classLoader, name)) {
			return ConfigurationFactoryUtil.getConfiguration(classLoader, name);
		}

		return null;
	}

	public static boolean hasConfiguration(
		ClassLoader classLoader, String name) {

		URL url = classLoader.getResource(name + ".properties");

		if (url == null) {
			return false;
		}

		return true;
	}

}