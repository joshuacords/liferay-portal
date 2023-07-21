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
public class ParallelDestinationPrototype implements DestinationPrototype {

	public ParallelDestinationPrototype(
		PortalExecutorManager portalExecutorManager) {

		_portalExecutorManager = portalExecutorManager;
	}

	@Override
	public Destination createDestination(
		DestinationConfiguration destinationConfiguration) {

		ParallelDestination parallelDestination = new ParallelDestination();

		parallelDestination.setName(
			destinationConfiguration.getDestinationName());
		parallelDestination.setMaximumQueueSize(
			destinationConfiguration.getMaximumQueueSize());
		parallelDestination.setPortalExecutorManager(_portalExecutorManager);
		parallelDestination.setRejectedExecutionHandler(
			destinationConfiguration.getRejectedExecutionHandler());
		parallelDestination.setWorkersSize(
			destinationConfiguration.getWorkersCoreSize(),
			destinationConfiguration.getWorkersMaxSize());

		parallelDestination.afterPropertiesSet();

		return parallelDestination;
	}

	private final PortalExecutorManager _portalExecutorManager;

}