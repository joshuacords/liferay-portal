/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.module.configuration.internal;

import com.liferay.petra.concurrent.ConcurrentReferenceKeyHashMap;
import com.liferay.petra.concurrent.ConcurrentReferenceValueHashMap;
import com.liferay.petra.memory.FinalizeManager;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.TypedSettings;

import java.lang.ref.Reference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Preston Crary
 */
public class ConfigurationOverrideInstance {

	public static final Object NULL_RESULT = new Object();

	public static ConfigurationOverrideInstance
			getConfigurationOverrideInstance(
				Class<?> clazz, TypedSettings typedSettings)
		throws ConfigurationException, ReflectiveOperationException {

		Class<?> configurationOverrideClass = _getOverrideClass(clazz);

		if (configurationOverrideClass == null) {
			return null;
		}

		ConfigurationOverrideInstance configurationOverrideInstance =
			_configurationOverrideInstances.get(configurationOverrideClass);

		if (configurationOverrideInstance == null) {
			configurationOverrideInstance = new ConfigurationOverrideInstance(
				configurationOverrideClass, typedSettings);

			_configurationOverrideInstances.put(
				configurationOverrideClass, configurationOverrideInstance);
		}

		return configurationOverrideInstance;
	}

	public Object invoke(Method method) throws ReflectiveOperationException {
		Method overriddenMethod = _methods.get(method.getName());

		if (overriddenMethod == null) {
			return NULL_RESULT;
		}

		return overriddenMethod.invoke(_configurationOverrideInstance);
	}

	private static Class<?> _getOverrideClass(Class<?> clazz) {
		Settings.OverrideClass overrideClass = clazz.getAnnotation(
			Settings.OverrideClass.class);

		if (overrideClass == null) {
			return null;
		}

		if (overrideClass.value() == Object.class) {
			return null;
		}

		return overrideClass.value();
	}

	private ConfigurationOverrideInstance(
			Class<?> configurationOverrideClass, TypedSettings typedSettings)
		throws ConfigurationException, ReflectiveOperationException {

		Constructor<?> constructor = configurationOverrideClass.getConstructor(
			TypedSettings.class);

		_configurationOverrideInstance = constructor.newInstance(typedSettings);

		for (Method method : configurationOverrideClass.getMethods()) {
			_methods.put(method.getName(), method);
		}
	}

	private static final Map<Class<?>, ConfigurationOverrideInstance>
		_configurationOverrideInstances = new ConcurrentReferenceKeyHashMap<>(
			new ConcurrentReferenceValueHashMap
				<Reference<Class<?>>, ConfigurationOverrideInstance>(
					FinalizeManager.WEAK_REFERENCE_FACTORY),
			FinalizeManager.WEAK_REFERENCE_FACTORY);

	private final Object _configurationOverrideInstance;
	private final Map<String, Method> _methods = new HashMap<>();

}