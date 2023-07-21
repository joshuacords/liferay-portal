/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.proxy;

import com.liferay.petra.executor.PortalExecutorManager;
import com.liferay.portal.kernel.io.Deserializer;
import com.liferay.portal.kernel.nio.intraband.Datagram;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.util.concurrent.ExecutorService;

/**
 * @author Shuyang Zhou
 */
public class AsyncIntrabandProxySkeleton implements IntrabandProxySkeleton {

	public static IntrabandProxySkeleton createAsyncIntrabandProxySkeleton(
		String skeletonId, IntrabandProxySkeleton intrabandProxySkeleton) {

		ExecutorService executorService =
			_portalExecutorManager.getPortalExecutor(skeletonId, false);

		if (executorService == null) {
			return intrabandProxySkeleton;
		}

		return new AsyncIntrabandProxySkeleton(
			executorService, intrabandProxySkeleton);
	}

	@Override
	public void dispatch(
		final RegistrationReference registrationReference,
		final Datagram datagram, final Deserializer deserializer) {

		_executorService.execute(
			new Runnable() {

				@Override
				public void run() {
					_intrabandProxySkeleton.dispatch(
						registrationReference, datagram, deserializer);
				}

			});
	}

	private AsyncIntrabandProxySkeleton(
		ExecutorService executorService,
		IntrabandProxySkeleton intrabandProxySkeleton) {

		_executorService = executorService;
		_intrabandProxySkeleton = intrabandProxySkeleton;
	}

	private static volatile PortalExecutorManager _portalExecutorManager =
		ServiceProxyFactory.newServiceTrackedInstance(
			PortalExecutorManager.class, AsyncIntrabandProxySkeleton.class,
			"_portalExecutorManager", true);

	private final ExecutorService _executorService;
	private final IntrabandProxySkeleton _intrabandProxySkeleton;

}