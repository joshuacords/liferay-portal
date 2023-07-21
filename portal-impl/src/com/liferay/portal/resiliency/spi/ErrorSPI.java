/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.resiliency.spi;

import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.resiliency.mpi.MPI;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.resiliency.spi.SPIConfiguration;
import com.liferay.portal.kernel.resiliency.spi.agent.SPIAgent;
import com.liferay.portal.resiliency.spi.agent.ErrorSPIAgent;

/**
 * @author Shuyang Zhou
 */
public class ErrorSPI implements SPI {

	@Override
	public void addServlet(
		String contextPath, String docBasePath, String mappingPattern,
		String servletClassName) {
	}

	@Override
	public void addWebapp(String contextPath, String docBasePath) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public MPI getMPI() {
		return null;
	}

	@Override
	public RegistrationReference getRegistrationReference() {
		return null;
	}

	@Override
	public SPIAgent getSPIAgent() {
		return _spiAgent;
	}

	@Override
	public SPIConfiguration getSPIConfiguration() {
		return null;
	}

	@Override
	public String getSPIProviderName() {
		return null;
	}

	@Override
	public void init() {
	}

	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
	}

	private static final SPIAgent _spiAgent = new ErrorSPIAgent();

}