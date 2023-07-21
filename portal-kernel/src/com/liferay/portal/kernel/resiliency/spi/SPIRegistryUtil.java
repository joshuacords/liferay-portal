/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.spi;

import java.rmi.RemoteException;

import java.util.Set;

/**
 * @author Shuyang Zhou
 */
public class SPIRegistryUtil {

	public static void addExcludedPortletId(String portletId) {
		getSPIRegistry().addExcludedPortletId(portletId);
	}

	public static SPI getErrorSPI() {
		return getSPIRegistry().getErrorSPI();
	}

	public static Set<String> getExcludedPortletIds() {
		return getSPIRegistry().getExcludedPortletIds();
	}

	public static SPI getPortletSPI(String portletId) {
		return getSPIRegistry().getPortletSPI(portletId);
	}

	public static SPI getServletContextSPI(String servletContextName) {
		return getSPIRegistry().getServletContextSPI(servletContextName);
	}

	public static SPIRegistry getSPIRegistry() {
		return _spiRegistry;
	}

	public static void registerSPI(SPI spi) throws RemoteException {
		getSPIRegistry().registerSPI(spi);
	}

	public static void removeExcludedPortletId(String portletId) {
		getSPIRegistry().removeExcludedPortletId(portletId);
	}

	public static void setSPIRegistryValidator(
		SPIRegistryValidator spiRegistryValidator) {

		getSPIRegistry().setSPIRegistryValidator(spiRegistryValidator);
	}

	public static void unregisterSPI(SPI spi) {
		getSPIRegistry().unregisterSPI(spi);
	}

	public void setSPIRegistry(SPIRegistry spiRegistry) {
		_spiRegistry = spiRegistry;
	}

	private static SPIRegistry _spiRegistry;

}