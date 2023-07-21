/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.mpi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Shuyang Zhou
 */
public interface MPI extends Remote {

	public boolean isAlive() throws RemoteException;

}