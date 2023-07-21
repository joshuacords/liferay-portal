/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.pacl.permission;

import java.security.BasicPermission;

/**
 * @author     Raymond Augé
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class PortalHookPermission extends BasicPermission {

	public static void checkPermission(
		String name, ClassLoader portletClassLoader, Object subject) {

		_pacl.checkPermission(name, portletClassLoader, subject);
	}

	public PortalHookPermission(
		String name, ClassLoader classLoader, Object subject) {

		super(name);

		_classLoader = classLoader;
		_subject = subject;
	}

	public ClassLoader getClassLoader() {
		return _classLoader;
	}

	public Object getSubject() {
		return _subject;
	}

	public interface PACL {

		public void checkPermission(
			String name, ClassLoader portletClassLoader, Object subject);

	}

	private static final PACL _pacl = new NoPACL();

	private final transient ClassLoader _classLoader;
	private final transient Object _subject;

	private static class NoPACL implements PACL {

		@Override
		public void checkPermission(
			String name, ClassLoader portletClassLoader, Object subject) {
		}

	}

}