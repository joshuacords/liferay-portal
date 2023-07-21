/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.spi;

import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.resiliency.mpi.MPI;
import com.liferay.portal.kernel.resiliency.spi.agent.SPIAgent;

import java.rmi.RemoteException;

/**
 * @author Shuyang Zhou
 */
public class MockSPI implements SPI {

	@Override
	public void addServlet(
		String contextPath, String docBasePath, String mappingPattern,
		String servletClassName) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addWebapp(String contextPath, String docBasePath) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void destroy() throws RemoteException {
		if (failOnDestroy) {
			throw new RemoteException();
		}

		destroyed = true;
	}

	@Override
	public MPI getMPI() {
		return mpi;
	}

	@Override
	public RegistrationReference getRegistrationReference()
		throws RemoteException {

		if (registrationReference == null) {
			throw new RemoteException();
		}

		return registrationReference;
	}

	@Override
	public SPIAgent getSPIAgent() {
		throw new UnsupportedOperationException();
	}

	@Override
	public SPIConfiguration getSPIConfiguration() throws RemoteException {
		if (failOnGetConfiguration) {
			throw new RemoteException();
		}

		return spiConfiguration;
	}

	@Override
	public String getSPIProviderName() {
		return spiProviderName;
	}

	@Override
	public void init() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isAlive() throws RemoteException {
		if (failOnIsAlive) {
			throw new RemoteException();
		}

		return true;
	}

	@Override
	public void start() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void stop() throws RemoteException {
		if (failOnStop) {
			throw new RemoteException();
		}

		stopped = true;
	}

	public boolean destroyed;
	public boolean failOnDestroy;
	public boolean failOnGetConfiguration;
	public boolean failOnIsAlive;
	public boolean failOnStop;
	public MPI mpi;
	public RegistrationReference registrationReference;
	public SPIConfiguration spiConfiguration;
	public String spiProviderName;
	public boolean stopped;

}