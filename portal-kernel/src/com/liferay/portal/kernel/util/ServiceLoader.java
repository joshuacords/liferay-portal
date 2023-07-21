/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.lang.reflect.Constructor;

import java.net.URL;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Miguel Pastor
 * @author Raymond Augé
 */
public class ServiceLoader {

	public static <S> List<S> load(Class<S> clazz) throws Exception {
		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		return load(classLoader, clazz);
	}

	public static <S> List<S> load(ClassLoader classLoader, Class<S> clazz)
		throws Exception {

		return load(classLoader, classLoader, clazz);
	}

	public static <S> List<S> load(
			ClassLoader lookupClassLoader, ClassLoader defineClassLoader,
			Class<S> clazz)
		throws Exception {

		Enumeration<URL> enu = lookupClassLoader.getResources(
			"META-INF/services/" + clazz.getName());

		List<S> services = new ArrayList<>();

		while (enu.hasMoreElements()) {
			URL url = enu.nextElement();

			try {
				_load(services, defineClassLoader, clazz, url);
			}
			catch (Exception exception) {
				_log.error(
					StringBundler.concat(
						"Unable to load ", String.valueOf(clazz), " with ",
						String.valueOf(defineClassLoader)),
					exception);
			}
		}

		return services;
	}

	private static <S> void _load(
			List<S> services, ClassLoader classLoader, Class<S> clazz, URL url)
		throws Exception {

		try (InputStream inputStream = url.openStream()) {
			BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream, StringPool.UTF8));

			while (true) {
				String line = bufferedReader.readLine();

				if (line == null) {
					break;
				}

				int comment = line.indexOf(CharPool.POUND);

				if (comment >= 0) {
					line = line.substring(0, comment);
				}

				String name = line.trim();

				if (name.length() == 0) {
					continue;
				}

				Class<?> serviceClass = Class.forName(name, true, classLoader);

				Class<? extends S> serviceImplClass = serviceClass.asSubclass(
					clazz);

				Constructor<? extends S> constructor =
					serviceImplClass.getConstructor();

				S service = constructor.newInstance();

				services.add(service);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(ServiceLoader.class);

}