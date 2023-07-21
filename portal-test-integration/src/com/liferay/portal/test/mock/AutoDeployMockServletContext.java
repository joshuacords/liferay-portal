/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.test.mock;

import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.File;

import java.util.Collections;
import java.util.Objects;

import javax.servlet.ServletRegistration;

import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockServletContext;

/**
 * @author     Cristina González
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class AutoDeployMockServletContext extends MockServletContext {

	public AutoDeployMockServletContext(ResourceLoader resourceLoader) {
		super(getResourceBasePath(), resourceLoader);
	}

	@Override
	public ServletRegistration getServletRegistration(String servletName) {
		if (Objects.equals(servletName, "Main Servlet")) {
			return (ServletRegistration)ProxyUtil.newProxyInstance(
				ServletRegistration.class.getClassLoader(),
				new Class<?>[] {ServletRegistration.class},
				(proxy, method, args) -> {
					if (Objects.equals(method.getName(), "getMappings")) {
						return Collections.singleton("/c/*");
					}

					return null;
				});
		}

		return null;
	}

	protected static String getResourceBasePath() {
		File file = new File("portal-web/docroot");

		return "file:" + file.getAbsolutePath();
	}

	/**
	 * @see com.liferay.portal.server.capabilities.TomcatServerCapabilities
	 */
	protected Boolean autoDeploy = Boolean.TRUE;

}