/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.settings.internal.util;

import aQute.bnd.annotation.metatype.Meta;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Iván Zaera
 */
public class ConfigurationPidUtil {

	public static String getConfigurationPid(Class<?> configurationBeanClass) {
		for (Annotation annotation : configurationBeanClass.getAnnotations()) {
			Class<? extends Annotation> clazz = annotation.annotationType();

			String name = clazz.getName();

			if (name.equals(Meta.OCD.class.getName())) {
				try {
					Method method = clazz.getMethod("id");

					method.setAccessible(true);

					return (String)method.invoke(annotation);
				}
				catch (ReflectiveOperationException
							reflectiveOperationException) {

					throw new IllegalArgumentException(
						"Unable to obtain configuration PID",
						reflectiveOperationException);
				}
			}
		}

		throw new IllegalArgumentException(
			"Invalid configuration bean class: " +
				configurationBeanClass.getName());
	}

}