/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.xuggler;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Andrew Betts
 */
public class XugglerInstallException extends PortalException {

	public static class MustBeURLClassLoader extends XugglerInstallException {

		public MustBeURLClassLoader() {
			super(
				"Unable to install JAR because the portal class loader is " +
					"not an instance of URLClassLoader");
		}

	}

	public static class MustInstallJar extends XugglerInstallException {

		public MustInstallJar(String name, Throwable cause) {
			super("Unable to install jar " + name, cause);
		}

	}

	private XugglerInstallException(String message) {
		super(message);
	}

	private XugglerInstallException(String message, Throwable cause) {
		super(message, cause);
	}

}