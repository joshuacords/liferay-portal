/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.freemarker.internal;

import java.io.IOException;

import java.net.URL;
import java.net.URLClassLoader;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.Bundle;

/**
 * @author Miguel Pastor
 * @author Raymond Augé
 */
public class FreeMarkerBundleClassloader extends URLClassLoader {

	public FreeMarkerBundleClassloader(Bundle... bundles) {
		super(new URL[0]);

		if (bundles.length == 0) {
			throw new IllegalArgumentException("Bundles are empty");
		}

		Collections.addAll(_bundles, bundles);
	}

	@Override
	public URL findResource(String name) {
		for (Bundle bundle : _bundles) {
			URL url = bundle.getResource(name);

			if (url != null) {
				return url;
			}
		}

		return null;
	}

	@Override
	public Enumeration<URL> findResources(String name) {
		for (Bundle bundle : _bundles) {
			try {
				Enumeration<URL> enumeration = bundle.getResources(name);

				if ((enumeration != null) && enumeration.hasMoreElements()) {
					return enumeration;
				}
			}
			catch (IOException ioException) {
			}
		}

		return Collections.enumeration(Collections.<URL>emptyList());
	}

	@Override
	public URL getResource(String name) {
		return findResource(name);
	}

	@Override
	public Enumeration<URL> getResources(String name) {
		return findResources(name);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		for (Bundle bundle : _bundles) {
			try {
				return bundle.loadClass(name);
			}
			catch (ClassNotFoundException classNotFoundException) {
			}
		}

		throw new ClassNotFoundException(name);
	}

	@Override
	protected Class<?> loadClass(String name, boolean resolve)
		throws ClassNotFoundException {

		Class<?> clazz = findClass(name);

		if (resolve) {
			resolveClass(clazz);
		}

		return clazz;
	}

	private final Set<Bundle> _bundles = ConcurrentHashMap.newKeySet();

}