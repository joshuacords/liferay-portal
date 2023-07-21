/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.spi.provider;

import com.liferay.portal.kernel.resiliency.spi.MockSPI;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.SyncThrowableThread;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.SynchronousQueue;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class SPISynchronousQueueUtilTest {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		CodeCoverageAssertor.INSTANCE;

	@Test
	public void testConstructor() {
		new SPISynchronousQueueUtil();
	}

	@Test
	public void testSPISynchronousQueueUtil() throws InterruptedException {

		// Create

		final String spiUUID = "spiUUID";

		SynchronousQueue<SPI> synchronousQueue =
			SPISynchronousQueueUtil.createSynchronousQueue(spiUUID);

		Map<String, SynchronousQueue<SPI>> synchronizerRegistry =
			ReflectionTestUtil.getFieldValue(
				SPISynchronousQueueUtil.class, "_synchronousQueues");

		Assert.assertSame(synchronousQueue, synchronizerRegistry.get(spiUUID));

		// Notify nonexistent

		try {
			SPISynchronousQueueUtil.notifySynchronousQueue("nonexistent", null);

			Assert.fail();
		}
		catch (IllegalStateException illegalStateException) {
			Assert.assertEquals(
				"No SPI synchronous queue with uuid nonexistent",
				illegalStateException.getMessage());
		}

		// Notify existent

		final MockSPI mockSPI = new MockSPI();

		SyncThrowableThread<Void> syncThrowableThread =
			new SyncThrowableThread<>(
				new Callable<Void>() {

					@Override
					public Void call() throws InterruptedException {
						SPISynchronousQueueUtil.notifySynchronousQueue(
							spiUUID, mockSPI);

						return null;
					}

				});

		syncThrowableThread.start();

		Assert.assertSame(mockSPI, synchronousQueue.take());

		syncThrowableThread.sync();

		// Destroy

		synchronousQueue = SPISynchronousQueueUtil.createSynchronousQueue(
			spiUUID);

		Assert.assertSame(synchronousQueue, synchronizerRegistry.get(spiUUID));

		SPISynchronousQueueUtil.destroySynchronousQueue(spiUUID);

		Assert.assertTrue(
			synchronizerRegistry.toString(), synchronizerRegistry.isEmpty());
	}

}