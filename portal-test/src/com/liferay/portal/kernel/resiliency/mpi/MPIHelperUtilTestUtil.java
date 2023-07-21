/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.mpi;

import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.test.ReflectionTestUtil;

import java.rmi.RemoteException;

import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class MPIHelperUtilTestUtil {

	public static void directResigterSPI(String spiId, SPI spi)
		throws RemoteException {

		Map<String, Object> spiProviderContainers =
			ReflectionTestUtil.getFieldValue(
				MPIHelperUtil.class, "_spiProviderContainers");

		Object spiProviderContainer = spiProviderContainers.get(
			spi.getSPIProviderName());

		Map<String, SPI> spis = ReflectionTestUtil.getFieldValue(
			spiProviderContainer, "_spis");

		spis.put(spiId, spi);
	}

}