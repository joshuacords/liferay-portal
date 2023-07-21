/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency;

import com.liferay.portal.kernel.resiliency.mpi.MPI;
import com.liferay.portal.kernel.resiliency.spi.SPI;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class RMISignatureTest {

	@Test
	public void testMPISignature() {
		_checkRMISignature(MPI.class, false);
	}

	@Test
	public void testSPISignature() {
		_checkRMISignature(SPI.class, true);
	}

	private void _checkRMISignature(
		Class<? extends Remote> rmiClass, boolean serializable) {

		Assert.assertTrue(
			rmiClass + " does not implement " + Remote.class,
			Remote.class.isAssignableFrom(rmiClass));

		if (serializable) {
			Assert.assertTrue(
				rmiClass + " does not implement " + Serializable.class,
				Serializable.class.isAssignableFrom(rmiClass));
		}

		Method[] methods = rmiClass.getDeclaredMethods();

		for (Method method : methods) {
			boolean exists = false;

			Class<?>[] exceptionTypes = method.getExceptionTypes();

			for (Class<?> exceptionType : exceptionTypes) {
				if (RemoteException.class.isAssignableFrom(exceptionType)) {
					exists = true;

					break;
				}
			}

			Assert.assertTrue(
				method + " does not throw " + RemoteException.class, exists);
		}
	}

}