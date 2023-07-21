/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.resiliency.spi.search;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.nio.intraband.messaging.IntrabandBridgeMessageListener;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.resiliency.spi.SPIUtil;
import com.liferay.portal.kernel.search.SearchEngineHelperUtil;

import java.rmi.RemoteException;

import java.util.Set;

/**
 * @author Shuyang Zhou
 */
public class SPISearchEngineConfigurator {

	public void afterPropertiesSet() throws RemoteException {
		if (!SPIUtil.isSPI()) {
			return;
		}

		Set<String> searchEngineIds =
			SearchEngineHelperUtil.getSearchEngineIds();

		for (String searchEngineId : searchEngineIds) {
			String destinationName =
				SearchEngineHelperUtil.getSearchWriterDestinationName(
					searchEngineId);

			Destination destination = MessageBusUtil.getDestination(
				destinationName);

			destination.unregisterMessageListeners();

			SPI spi = SPIUtil.getSPI();

			destination.register(
				new IntrabandBridgeMessageListener(
					spi.getRegistrationReference()));
		}
	}

}