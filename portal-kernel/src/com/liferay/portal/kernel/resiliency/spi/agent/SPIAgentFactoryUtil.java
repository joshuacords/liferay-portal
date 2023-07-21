/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.spi.agent;

import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.resiliency.spi.SPIConfiguration;

import java.lang.reflect.Constructor;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shuyang Zhou
 */
public class SPIAgentFactoryUtil {

	public static SPIAgent createSPIAgent(
		SPIConfiguration spiConfiguration,
		RegistrationReference registrationReference) {

		String spiAgentClassName = spiConfiguration.getSPIAgentClassName();

		if (spiAgentClassName == null) {
			throw new NullPointerException("Missing SPI agent class name");
		}

		Class<? extends SPIAgent> spiAgentClass = _spiAgentClasses.get(
			spiAgentClassName);

		if (spiAgentClass == null) {
			throw new IllegalArgumentException(
				"Unkown SPI agent class name " + spiAgentClassName);
		}

		try {
			Constructor<? extends SPIAgent> constructor =
				spiAgentClass.getConstructor(
					SPIConfiguration.class, RegistrationReference.class);

			return constructor.newInstance(
				spiConfiguration, registrationReference);
		}
		catch (Exception exception) {
			throw new RuntimeException(
				"Unable to instantiate " + spiAgentClass, exception);
		}
	}

	public static Set<String> getSPIAgentClassNames() {
		return _spiAgentClasses.keySet();
	}

	public static Class<? extends SPIAgent> registerSPIAgentClass(
		Class<? extends SPIAgent> spiAgentClass) {

		return _spiAgentClasses.put(spiAgentClass.getName(), spiAgentClass);
	}

	public static Class<? extends SPIAgent> unregisterSPIAgentClass(
		String spiAgentClassName) {

		return _spiAgentClasses.remove(spiAgentClassName);
	}

	public void setSPIAgentClasses(Set<String> spiAgentClassNames)
		throws ClassNotFoundException {

		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		for (String spiAgentClassName : spiAgentClassNames) {
			Class<? extends SPIAgent> agentClass =
				(Class<? extends SPIAgent>)classLoader.loadClass(
					spiAgentClassName);

			_spiAgentClasses.put(spiAgentClassName, agentClass);
		}
	}

	private static final Map<String, Class<? extends SPIAgent>>
		_spiAgentClasses = new ConcurrentHashMap<>();

}