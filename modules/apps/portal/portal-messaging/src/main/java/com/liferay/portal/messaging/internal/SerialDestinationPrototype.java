/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.messaging.internal;

import com.liferay.portal.kernel.executor.PortalExecutorManager;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;

/**
 * @author Michael C. Han
 */
public class SerialDestinationPrototype implements DestinationPrototype {

	public SerialDestinationPrototype(
		PortalExecutorManager portalExecutorManager) {

		_portalExecutorManager = portalExecutorManager;
	}

	@Override
	public Destination createDestination(
		DestinationConfiguration destinationConfiguration) {

		SerialDestination serialDestination = new SerialDestination();

		serialDestination.setName(
			destinationConfiguration.getDestinationName());
		serialDestination.setMaximumQueueSize(
			destinationConfiguration.getMaximumQueueSize());
		serialDestination.setPortalExecutorManager(_portalExecutorManager);
		serialDestination.setRejectedExecutionHandler(
			destinationConfiguration.getRejectedExecutionHandler());
		serialDestination.setWorkersSize(_WORKERS_CORE_SIZE, _WORKERS_MAX_SIZE);

		serialDestination.afterPropertiesSet();

		return serialDestination;
	}

	private static final int _WORKERS_CORE_SIZE = 1;

	private static final int _WORKERS_MAX_SIZE = 1;

	private final PortalExecutorManager _portalExecutorManager;

}