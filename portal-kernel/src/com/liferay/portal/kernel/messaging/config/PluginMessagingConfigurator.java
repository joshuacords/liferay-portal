/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.messaging.config;

import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.servlet.ServletContextClassLoaderPool;

/**
 * @author Michael C. Han
 */
public class PluginMessagingConfigurator extends BaseMessagingConfigurator {

	@Override
	public void afterPropertiesSet() {
		_servletContextName = PortletClassLoaderUtil.getServletContextName();

		super.afterPropertiesSet();
	}

	@Override
	protected ClassLoader getOperatingClassloader() {
		ClassLoader classLoader = ServletContextClassLoaderPool.getClassLoader(
			_servletContextName);

		if (classLoader == null) {
			throw new IllegalStateException(
				"Unable to find the class loader for servlet context " +
					_servletContextName);
		}

		return classLoader;
	}

	private String _servletContextName;

}