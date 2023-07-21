/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.spi;

import com.liferay.portal.kernel.resiliency.spi.remote.RemoteSPI;

import java.rmi.RemoteException;

/**
 * @author Shuyang Zhou
 */
public class MockRemoteSPI extends RemoteSPI {

	public MockRemoteSPI(SPIConfiguration spiConfiguration) {
		super(spiConfiguration);
	}

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
	public String getSPIProviderName() {
		return _spiProviderName;
	}

	@Override
	public void init() {
		throw new UnsupportedOperationException();
	}

	public void setFailOnDestroy(boolean failOnDestroy) {
		_failOnDestroy = failOnDestroy;
	}

	public void setFailOnStop(boolean failOnStop) {
		_failOnStop = failOnStop;
	}

	public void setSpiProviderName(String spiProviderName) {
		_spiProviderName = spiProviderName;
	}

	@Override
	public void start() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void stop() throws RemoteException {
		if (_failOnStop) {
			throw new RemoteException();
		}
	}

	@Override
	protected void doDestroy() throws RemoteException {
		if (_failOnDestroy) {
			throw new RemoteException();
		}
	}

	private boolean _failOnDestroy;
	private boolean _failOnStop;
	private String _spiProviderName;

}