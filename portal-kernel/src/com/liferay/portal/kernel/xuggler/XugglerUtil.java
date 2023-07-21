/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.xuggler;

/**
 * @author Alexander Chow
 */
public class XugglerUtil {

	public static Xuggler getXuggler() {
		return _xuggler;
	}

	public static void installNativeLibraries(String name) throws Exception {
		getXuggler().installNativeLibraries(name);
	}

	public static boolean isEnabled() {
		return getXuggler().isEnabled();
	}

	public static boolean isEnabled(boolean checkNativeLibraries) {
		return getXuggler().isEnabled(checkNativeLibraries);
	}

	public static boolean isNativeLibraryCopied() {
		return getXuggler().isNativeLibraryCopied();
	}

	public static boolean isNativeLibraryInstalled() {
		return getXuggler().isNativeLibraryInstalled();
	}

	public void setXuggler(Xuggler xuggler) {
		_xuggler = xuggler;
	}

	private static Xuggler _xuggler;

}