/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.support.tomcat.loader;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalClassLoaderFactory {

	public static ClassLoader getClassLoader() {
		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		if (classLoader == null) {
			_log.error(
				"Portal class loader is not available to override the " +
					"default Catalina web class loader");
		}

		return classLoader;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortalClassLoaderFactory.class);

}