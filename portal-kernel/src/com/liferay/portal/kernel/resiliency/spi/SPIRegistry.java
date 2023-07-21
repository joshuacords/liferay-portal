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
public interface SPIRegistry {

	public void addExcludedPortletId(String portletId);

	public SPI getErrorSPI();

	public Set<String> getExcludedPortletIds();

	public SPI getPortletSPI(String portletId);

	public SPI getServletContextSPI(String servletContextName);

	public void registerSPI(SPI spi) throws RemoteException;

	public void removeExcludedPortletId(String portletId);

	public void setSPIRegistryValidator(
		SPIRegistryValidator spiRegistryValidator);

	public void unregisterSPI(SPI spi);

}