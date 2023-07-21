/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband;

import com.liferay.petra.executor.PortalExecutorManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.util.concurrent.Executor;

/**
 * @author Shuyang Zhou
 */
public abstract class BaseAsyncDatagramReceiveHandler
	implements DatagramReceiveHandler {

	public BaseAsyncDatagramReceiveHandler() {
		Class<? extends BaseAsyncDatagramReceiveHandler> clazz = getClass();

		_executor = _portalExecutorManager.getPortalExecutor(clazz.getName());
	}

	@Override
	public void receive(
		RegistrationReference registrationReference, Datagram datagram) {

		_executor.execute(new DispatchJob(registrationReference, datagram));
	}

	protected abstract void doReceive(
			RegistrationReference registrationReference, Datagram datagram)
		throws Exception;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseAsyncDatagramReceiveHandler.class);

	private static volatile PortalExecutorManager _portalExecutorManager =
		ServiceProxyFactory.newServiceTrackedInstance(
			PortalExecutorManager.class, BaseAsyncDatagramReceiveHandler.class,
			"_portalExecutorManager", true);

	private final Executor _executor;

	private class DispatchJob implements Runnable {

		public DispatchJob(
			RegistrationReference registrationReference, Datagram datagram) {

			_registrationReference = registrationReference;
			_datagram = datagram;
		}

		@Override
		public void run() {
			try {
				doReceive(_registrationReference, _datagram);
			}
			catch (Exception exception) {
				_log.error("Unable to dispatch", exception);
			}
		}

		private final Datagram _datagram;
		private final RegistrationReference _registrationReference;

	}

}