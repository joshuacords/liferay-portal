/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.messaging;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;

/**
 * @author Shuyang Zhou
 */
public class DestinationConfigurationProcessCallable
	implements ProcessCallable<Boolean> {

	public DestinationConfigurationProcessCallable(String destinationName) {
		_destinationName = destinationName;
	}

	@Override
	public Boolean call() throws ProcessException {
		MessageBus messageBus = MessageBusUtil.getMessageBus();

		Destination destination = messageBus.getDestination(_destinationName);

		if (destination == null) {
			throw new ProcessException("No such destination " + destination);
		}

		if (destination instanceof IntrabandBridgeDestination) {
			return Boolean.FALSE;
		}

		IntrabandBridgeDestination intrabandBridgeDestination =
			new IntrabandBridgeDestination(destination);

		messageBus.addDestination(intrabandBridgeDestination);

		return Boolean.TRUE;
	}

	private static final long serialVersionUID = 1L;

	private final String _destinationName;

}