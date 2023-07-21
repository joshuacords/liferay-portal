/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.spi.provider;

import com.liferay.portal.kernel.resiliency.spi.SPI;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

/**
 * @author Shuyang Zhou
 */
public class SPISynchronousQueueUtil {

	public static SynchronousQueue<SPI> createSynchronousQueue(String spiUUID) {
		SynchronousQueue<SPI> synchronousQueue = new SynchronousQueue<>();

		_synchronousQueues.put(spiUUID, synchronousQueue);

		return synchronousQueue;
	}

	public static void destroySynchronousQueue(String spiUUID) {
		_synchronousQueues.remove(spiUUID);
	}

	public static void notifySynchronousQueue(String spiUUID, SPI spi)
		throws InterruptedException {

		SynchronousQueue<SPI> synchronousQueue = _synchronousQueues.remove(
			spiUUID);

		if (synchronousQueue == null) {
			throw new IllegalStateException(
				"No SPI synchronous queue with uuid " + spiUUID);
		}

		synchronousQueue.put(spi);
	}

	private static final Map<String, SynchronousQueue<SPI>> _synchronousQueues =
		new ConcurrentHashMap<>();

}