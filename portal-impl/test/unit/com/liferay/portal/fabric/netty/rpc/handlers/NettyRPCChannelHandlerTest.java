/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.rpc.handlers;

import com.liferay.portal.fabric.netty.rpc.RPCSerializable;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.embedded.EmbeddedChannel;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class NettyRPCChannelHandlerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			CodeCoverageAssertor.INSTANCE, LiferayUnitTestRule.INSTANCE);

	@Test
	public void testChannelRead0() {
		EmbeddedChannel embeddedChannel = new EmbeddedChannel(
			NettyRPCChannelHandler.INSTANCE);

		final AtomicReference<Channel> channelReference =
			new AtomicReference<>();

		RPCSerializable rpcSerializable = new RPCSerializable(
			System.currentTimeMillis()) {

			@Override
			public void execute(Channel channel) {
				channelReference.set(channel);
			}

			private static final long serialVersionUID = 1L;

		};

		embeddedChannel.writeInbound(rpcSerializable);

		Assert.assertSame(embeddedChannel, channelReference.get());
	}

	@Test
	public void testStructure() throws ReflectiveOperationException {
		Assert.assertNotNull(
			NettyRPCChannelHandler.class.getAnnotation(
				ChannelHandler.Sharable.class));

		Field instanceField = NettyRPCChannelHandler.class.getField("INSTANCE");

		Assert.assertEquals(
			Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL,
			instanceField.getModifiers());
		Assert.assertSame(
			NettyRPCChannelHandler.class, instanceField.getType());
		Assert.assertNotNull(instanceField.get(null));

		Field nameField = NettyRPCChannelHandler.class.getField("NAME");

		Assert.assertEquals(
			Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL,
			nameField.getModifiers());
		Assert.assertSame(String.class, nameField.getType());
		Assert.assertEquals(
			NettyRPCChannelHandler.class.getName(), nameField.get(null));

		Constructor<NettyRPCChannelHandler> constructor =
			NettyRPCChannelHandler.class.getDeclaredConstructor();

		Assert.assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	}

}