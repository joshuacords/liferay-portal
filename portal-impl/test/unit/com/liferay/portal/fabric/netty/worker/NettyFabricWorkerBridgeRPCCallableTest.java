/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.worker;

import com.liferay.petra.concurrent.DefaultNoticeableFuture;
import com.liferay.petra.concurrent.NoticeableFuture;
import com.liferay.petra.process.ProcessCallable;
import com.liferay.petra.process.ProcessException;
import com.liferay.portal.fabric.local.worker.EmbeddedProcessChannel;
import com.liferay.portal.fabric.local.worker.LocalFabricWorker;
import com.liferay.portal.fabric.netty.NettyTestUtil;
import com.liferay.portal.fabric.netty.handlers.NettyChannelAttributes;
import com.liferay.portal.fabric.netty.rpc.ChannelThreadLocal;
import com.liferay.portal.fabric.netty.util.NettyUtilAdvice;
import com.liferay.portal.fabric.worker.FabricWorker;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.test.rule.NewEnv;
import com.liferay.portal.test.rule.AdviseWith;
import com.liferay.portal.test.rule.AspectJNewEnvTestRule;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import io.netty.channel.embedded.EmbeddedChannel;

import java.io.Serializable;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
@NewEnv(type = NewEnv.Type.CLASSLOADER)
public class NettyFabricWorkerBridgeRPCCallableTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			AspectJNewEnvTestRule.INSTANCE, CodeCoverageAssertor.INSTANCE,
			LiferayUnitTestRule.INSTANCE);

	@Before
	public void setUp() {
		ChannelThreadLocal.setChannel(_embeddedChannel);
	}

	@After
	public void tearDown() {
		ChannelThreadLocal.removeChannel();
	}

	@AdviseWith(adviceClasses = NettyUtilAdvice.class)
	@Test
	public void testCall() throws Exception {
		FabricWorker<Serializable> fabricWorker = new LocalFabricWorker<>(
			new EmbeddedProcessChannel<Serializable>(
				new DefaultNoticeableFuture<Serializable>()));

		NettyChannelAttributes.putFabricWorker(
			_embeddedChannel, 0, fabricWorker);

		NettyFabricWorkerBridgeRPCCallable<Serializable>
			nettyFabricWorkerBridgeRPCCallable =
				new NettyFabricWorkerBridgeRPCCallable<Serializable>(
					0,
					new ProcessCallable<Serializable>() {

						@Override
						public Serializable call() {
							return null;
						}

					},
					0);

		NoticeableFuture<Serializable> noticeableFuture =
			nettyFabricWorkerBridgeRPCCallable.call();

		Assert.assertNull(noticeableFuture.get());
	}

	@AdviseWith(adviceClasses = NettyUtilAdvice.class)
	@Test
	public void testCallTimeoutCancelled() throws ProcessException {
		FabricWorker<Serializable> fabricWorker =
			new LocalFabricWorker<Serializable>(
				new EmbeddedProcessChannel<Serializable>(
					new DefaultNoticeableFuture<Serializable>()) {

					@Override
					public <V extends Serializable> NoticeableFuture<V> write(
						ProcessCallable<V> processCallable) {

						return new DefaultNoticeableFuture<>();
					}

				});

		NettyChannelAttributes.putFabricWorker(
			_embeddedChannel, 0, fabricWorker);

		NettyFabricWorkerBridgeRPCCallable<Serializable>
			nettyFabricWorkerBridgeRPCCallable =
				new NettyFabricWorkerBridgeRPCCallable<Serializable>(
					0,
					new ProcessCallable<Serializable>() {

						@Override
						public Serializable call() {
							return null;
						}

					},
					0);

		NoticeableFuture<Serializable> noticeableFuture =
			nettyFabricWorkerBridgeRPCCallable.call();

		Assert.assertTrue(noticeableFuture.isCancelled());
	}

	@NewEnv(type = NewEnv.Type.NONE)
	@Test
	public void testCallUnableToLocateFabricWorker() {
		NettyFabricWorkerBridgeRPCCallable<Serializable>
			nettyFabricWorkerBridgeRPCCallable =
				new NettyFabricWorkerBridgeRPCCallable<Serializable>(
					0,
					new ProcessCallable<Serializable>() {

						@Override
						public Serializable call() {
							return null;
						}

					},
					0);

		try {
			nettyFabricWorkerBridgeRPCCallable.call();

			Assert.fail();
		}
		catch (ProcessException processException) {
			Assert.assertEquals(
				"Unable to locate fabric worker with ID 0",
				processException.getMessage());
		}
	}

	private final EmbeddedChannel _embeddedChannel =
		NettyTestUtil.createEmptyEmbeddedChannel();

}