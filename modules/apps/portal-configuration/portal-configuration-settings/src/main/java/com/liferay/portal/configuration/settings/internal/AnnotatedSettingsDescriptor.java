/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.settings.internal;

import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsDescriptor;
import com.liferay.portal.kernel.util.StringUtil;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Iván Zaera
 */
public class AnnotatedSettingsDescriptor implements SettingsDescriptor {

	public AnnotatedSettingsDescriptor(Class<?> settingsClass) {
		_settingsClass = settingsClass;

		Method[] methods = _getPropertyMethods();

		_initAllKeys(methods);
		_initMultiValuedKeys(methods);
	}

	@Override
	public Set<String> getAllKeys() {
		return _allKeys;
	}

	@Override
	public Set<String> getMultiValuedKeys() {
		return _multiValuedKeys;
	}

	private Method[] _getPropertyMethods() {
		List<Method> propertyMethods = new ArrayList<>();

		Method[] methods = _settingsClass.getMethods();

		for (Method method : methods) {
			Settings.Property settingsProperty = method.getAnnotation(
				Settings.Property.class);

			if ((settingsProperty != null) && settingsProperty.ignore()) {
				continue;
			}

			String name = method.getName();

			if (name.equals("getClass")) {
				continue;
			}

			if (name.startsWith("get") || name.startsWith("is")) {
				propertyMethods.add(method);
			}
		}

		return propertyMethods.toArray(new Method[0]);
	}

	private String _getPropertyName(Method propertyMethod) {
		Settings.Property settingsProperty = propertyMethod.getAnnotation(
			Settings.Property.class);

		if (settingsProperty != null) {
			String name = settingsProperty.name();

			if (!name.isEmpty()) {
				return name;
			}
		}

		String name = propertyMethod.getName();

		if (name.startsWith("get")) {
			name = name.substring(3);
		}
		else if (name.startsWith("is")) {
			name = name.substring(2);
		}
		else {
			throw new IllegalArgumentException(
				"Invalid method name for getter " + propertyMethod.getName());
		}

		return StringUtil.toLowerCase(name.substring(0, 1)) + name.substring(1);
	}

	private void _initAllKeys(Method[] propertyMethods) {
		for (Method propertyMethod : propertyMethods) {
			_allKeys.add(_getPropertyName(propertyMethod));
		}
	}

	private void _initMultiValuedKeys(Method[] propertyMethods) {
		for (Method propertyMethod : propertyMethods) {
			Class<?> clazz = propertyMethod.getReturnType();

			if (clazz == String[].class) {
				_multiValuedKeys.add(_getPropertyName(propertyMethod));
			}
		}
	}

	private final Set<String> _allKeys = new HashSet<>();
	private final Set<String> _multiValuedKeys = new HashSet<>();
	private final Class<?> _settingsClass;

}