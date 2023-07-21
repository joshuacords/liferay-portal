/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.javadoc;

import java.lang.reflect.Method;

/**
 * @author Igor Spasic
 */
public class JavadocManagerUtil {

	public static JavadocManager getJavadocManager() {
		return _javadocManager;
	}

	public static void load(
		String servletContextName, ClassLoader classLoader) {

		getJavadocManager().load(servletContextName, classLoader);
	}

	public static JavadocClass lookupJavadocClass(Class<?> clazz) {
		return getJavadocManager().lookupJavadocClass(clazz);
	}

	public static JavadocMethod lookupJavadocMethod(Method method) {
		return getJavadocManager().lookupJavadocMethod(method);
	}

	public static void unload(String servletContextName) {
		getJavadocManager().unload(servletContextName);
	}

	public void setJavadocManager(JavadocManager javadocManager) {
		_javadocManager = javadocManager;
	}

	private static JavadocManager _javadocManager;

}