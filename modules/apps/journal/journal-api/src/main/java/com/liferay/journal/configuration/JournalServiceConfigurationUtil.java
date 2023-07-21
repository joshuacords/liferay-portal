/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.configuration;

import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

/**
 * @author     Eduardo García
 * @deprecated As of Judson (7.1.x), see {@link JournalServiceConfiguration}
 */
@Deprecated
public class JournalServiceConfigurationUtil {

	public static String get(String key) {
		return _configuration.get(key);
	}

	public static String get(String key, Filter filter) {
		return _configuration.get(key, filter);
	}

	public static String[] getArray(String key) {
		return _configuration.getArray(key);
	}

	public static String getContent(String location) {
		try {
			return StringUtil.read(
				JournalServiceConfigurationUtil.class.getClassLoader(),
				location);
		}
		catch (IOException ioException) {
			return null;
		}
	}

	private static final Configuration _configuration =
		ConfigurationFactoryUtil.getConfiguration(
			JournalServiceConfigurationUtil.class.getClassLoader(), "portlet");

}