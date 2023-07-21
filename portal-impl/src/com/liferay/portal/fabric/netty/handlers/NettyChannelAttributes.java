/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.handlers;

import com.liferay.petra.concurrent.AsyncBroker;
import com.liferay.petra.concurrent.FutureListener;
import com.liferay.petra.concurrent.NoticeableFuture;
import com.liferay.portal.fabric.netty.agent.NettyFabricAgentStub;
import com.liferay.portal.fabric.netty.rpc.RPCUtil;
import com.liferay.portal.fabric.worker.FabricWorker;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.io.Serializable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Shuyang Zhou
 */
public class NettyChannelAttributes {

	public static <T extends Serializable> AsyncBroker<Long, T> getAsyncBroker(
		Channel channel) {

		Attribute<AsyncBroker<Long, Serializable>> attribute = channel.attr(
			_asyncBrokerKey);

		AsyncBroker<Long, Serializable> asyncBroker = attribute.get();

		if (asyncBroker == null) {
			asyncBroker = new AsyncBroker<>();

			AsyncBroker<Long, Serializable> previousAsyncBroker =
				attribute.setIfAbsent(asyncBroker);

			if (previousAsyncBroker != null) {
				asyncBroker = previousAsyncBroker;
			}
		}

		return (AsyncBroker<Long, T>)asyncBroker;
	}

	public static <T extends Serializable> FabricWorker<T> getFabricWorker(
		Channel channel, long id) {

		Map<Long, FabricWorker<?>> fabricWorkers = getFabricWorkers(channel);

		if (fabricWorkers == null) {
			return null;
		}

		return (FabricWorker<T>)fabricWorkers.get(id);
	}

	public static Map<Long, FabricWorker<?>> getFabricWorkers(Channel channel) {
		Attribute<Map<Long, FabricWorker<?>>> attribute = channel.attr(
			_fabricWorkersKey);

		return attribute.get();
	}

	public static NettyFabricAgentStub getNettyFabricAgentStub(
		Channel channel) {

		Attribute<NettyFabricAgentStub> attribute = channel.attr(
			_nettyFabricAgentStubKey);

		return attribute.get();
	}

	public static long nextId(Channel channel) {
		Attribute<AtomicLong> attribute = channel.attr(_idGeneratorKey);

		AtomicLong attachmentIdGenerator = attribute.get();

		if (attachmentIdGenerator == null) {
			attachmentIdGenerator = new AtomicLong();

			AtomicLong previousAttachmentIdGenerator = attribute.setIfAbsent(
				attachmentIdGenerator);

			if (previousAttachmentIdGenerator != null) {
				attachmentIdGenerator = previousAttachmentIdGenerator;
			}
		}

		return attachmentIdGenerator.getAndIncrement();
	}

	public static <T extends Serializable> void putFabricWorker(
		Channel channel, final long id, FabricWorker<T> fabricWorker) {

		Attribute<Map<Long, FabricWorker<?>>> attribute = channel.attr(
			_fabricWorkersKey);

		Map<Long, FabricWorker<?>> fabricWorkers = attribute.get();

		if (fabricWorkers == null) {
			fabricWorkers = new ConcurrentHashMap<>();

			Map<Long, FabricWorker<?>> previousFabricWorkers =
				attribute.setIfAbsent(fabricWorkers);

			if (previousFabricWorkers != null) {
				fabricWorkers = previousFabricWorkers;
			}
		}

		fabricWorkers.put(id, fabricWorker);

		NoticeableFuture<T> noticeableFuture =
			fabricWorker.getProcessNoticeableFuture();

		final Map<Long, FabricWorker<?>> fabricWorkersRef = fabricWorkers;

		noticeableFuture.addFutureListener(
			new FutureListener<T>() {

				@Override
				public void complete(Future<T> future) {
					fabricWorkersRef.remove(id);
				}

			});
	}

	public static void setNettyFabricAgentStub(
		Channel channel, NettyFabricAgentStub nettyFabricAgentStub) {

		Attribute<NettyFabricAgentStub> attribute = channel.attr(
			_nettyFabricAgentStubKey);

		attribute.set(nettyFabricAgentStub);
	}

	private static final AttributeKey<AsyncBroker<Long, Serializable>>
		_asyncBrokerKey = AttributeKey.valueOf(
			RPCUtil.class.getName() + "-AsyncBroker");
	private static final AttributeKey<Map<Long, FabricWorker<?>>>
		_fabricWorkersKey = AttributeKey.valueOf(
			NettyChannelAttributes.class.getName() + "-FabricWorkers");
	private static final AttributeKey<AtomicLong> _idGeneratorKey =
		AttributeKey.valueOf(RPCUtil.class.getName() + "-IdGenerator");
	private static final AttributeKey<NettyFabricAgentStub>
		_nettyFabricAgentStubKey = AttributeKey.valueOf(
			NettyFabricAgentStub.class.getName());

}