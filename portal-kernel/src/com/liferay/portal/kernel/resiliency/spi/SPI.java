/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.spi;

import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.resiliency.mpi.MPI;
import com.liferay.portal.kernel.resiliency.spi.agent.SPIAgent;

import java.io.Serializable;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Shuyang Zhou
 */
public interface SPI extends Remote, Serializable {

	public static final String SPI_INSTANCE_PUBLICATION_KEY =
		"SPI_INSTANCE_PUBLICATION_KEY";

	public void addServlet(
			String contextPath, String docBasePath, String mappingPattern,
			String servletClassName)
		throws RemoteException;

	public void addWebapp(String contextPath, String docBasePath)
		throws RemoteException;

	public void destroy() throws RemoteException;

	public MPI getMPI() throws RemoteException;

	public RegistrationReference getRegistrationReference()
		throws RemoteException;

	public SPIAgent getSPIAgent() throws RemoteException;

	public SPIConfiguration getSPIConfiguration() throws RemoteException;

	public String getSPIProviderName() throws RemoteException;

	public void init() throws RemoteException;

	public boolean isAlive() throws RemoteException;

	public void start() throws RemoteException;

	public void stop() throws RemoteException;

}