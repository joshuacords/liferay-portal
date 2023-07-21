/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.internal.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Jorge Ferrer
 */
public class GenericsUtil {

	public static Class<?> getItemClass(Class clazz) {
		Type[] genericInterfaceTypes = clazz.getGenericInterfaces();

		for (Type genericInterfaceType : genericInterfaceTypes) {
			ParameterizedType parameterizedType =
				(ParameterizedType)genericInterfaceType;

			return (Class<?>)parameterizedType.getActualTypeArguments()[0];
		}

		Class<?> superClass = clazz.getSuperclass();

		if (superClass != null) {
			return getItemClass(superClass);
		}

		return Object.class;
	}

	public static Class<?> getItemClass(Object object) {
		Class<?> infoListProviderClass = object.getClass();

		return getItemClass(infoListProviderClass);
	}

	public static String getItemClassName(Object object) {
		Class<?> clazz = getItemClass(object);

		return clazz.getName();
	}

}