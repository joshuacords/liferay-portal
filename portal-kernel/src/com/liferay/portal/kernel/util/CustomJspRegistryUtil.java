/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.util.Set;

/**
 * @author Ryan Park
 * @author Brian Wing Shun Chan
 */
public class CustomJspRegistryUtil {

	public static String getCustomJspFileName(
		String servletContextName, String fileName) {

		return getCustomJspRegistry().getCustomJspFileName(
			servletContextName, fileName);
	}

	public static CustomJspRegistry getCustomJspRegistry() {
		return _customJspRegistry;
	}

	public static String getDisplayName(String servletContextName) {
		return getCustomJspRegistry().getDisplayName(servletContextName);
	}

	public static Set<String> getServletContextNames() {
		return getCustomJspRegistry().getServletContextNames();
	}

	public static void registerServletContextName(
		String servletContextName, String displayName) {

		getCustomJspRegistry().registerServletContextName(
			servletContextName, displayName);
	}

	public static void unregisterServletContextName(String servletContextName) {
		getCustomJspRegistry().unregisterServletContextName(servletContextName);
	}

	public void setCustomJspRegistry(CustomJspRegistry customJspRegistry) {
		_customJspRegistry = customJspRegistry;
	}

	private static CustomJspRegistry _customJspRegistry;

}