/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.module.framework;

import com.liferay.petra.string.CharPool;

import java.net.URL;
import java.net.URLClassLoader;

import java.util.Arrays;

/**
 * @author Miguel Pastor
 */
public class ModuleFrameworkClassLoader extends URLClassLoader {

	public ModuleFrameworkClassLoader(
		URL[] urls, ClassLoader parent, String[] packageNames) {

		super(urls, parent);

		_packageNames = packageNames;
	}

	@Override
	protected Class<?> loadClass(String name, boolean resolve)
		throws ClassNotFoundException {

		Object lock = getClassLoadingLock(name);

		synchronized (lock) {
			Class<?> clazz = findLoadedClass(name);

			if (clazz == null) {
				if (_hasPackageName(name)) {
					try {
						clazz = findClass(name);
					}
					catch (ClassNotFoundException classNotFoundException) {
						clazz = super.loadClass(name, resolve);
					}
				}
				else {
					try {
						clazz = super.loadClass(name, resolve);
					}
					catch (ClassNotFoundException classNotFoundException) {
						clazz = findClass(name);
					}
				}
			}

			if (resolve) {
				resolveClass(clazz);
			}

			return clazz;
		}
	}

	private boolean _hasPackageName(String name) {
		String packageName = name;

		int index = name.lastIndexOf(CharPool.PERIOD);

		if (index != -1) {
			packageName = name.substring(0, index);
		}

		index = Arrays.binarySearch(_packageNames, packageName);

		if (index >= 0) {
			return true;
		}

		index = -index - 1;

		if ((index == 0) || !packageName.startsWith(_packageNames[index - 1])) {
			return false;
		}

		return true;
	}

	private final String[] _packageNames;

	static {
		ClassLoader.registerAsParallelCapable();
	}

}