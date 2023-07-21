/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.worker;

import com.liferay.petra.concurrent.NoticeableFuture;
import com.liferay.petra.process.ProcessCallable;
import com.liferay.petra.process.ProcessException;
import com.liferay.portal.fabric.netty.handlers.NettyChannelAttributes;
import com.liferay.portal.fabric.netty.rpc.ChannelThreadLocal;
import com.liferay.portal.fabric.netty.rpc.RPCCallable;
import com.liferay.portal.fabric.netty.util.NettyUtil;
import com.liferay.portal.fabric.worker.FabricWorker;

import io.netty.channel.Channel;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class NettyFabricWorkerBridgeRPCCallable<T extends Serializable>
	implements RPCCallable<T> {

	public NettyFabricWorkerBridgeRPCCallable(
		long id, ProcessCallable<T> processCallable, long rpcRelayTime) {

		_id = id;
		_processCallable = processCallable;

		_rpcRelayTimeout = rpcRelayTime;
	}

	@Override
	public NoticeableFuture<T> call() throws ProcessException {
		Channel channel = ChannelThreadLocal.getChannel();

		FabricWorker<T> fabricWorker = NettyChannelAttributes.getFabricWorker(
			channel, _id);

		if (fabricWorker == null) {
			throw new ProcessException(
				"Unable to locate fabric worker with ID " + _id);
		}

		NoticeableFuture<T> noticeableFuture = fabricWorker.write(
			_processCallable);

		NettyUtil.scheduleCancellation(
			channel, noticeableFuture, _rpcRelayTimeout);

		return noticeableFuture;
	}

	private static final long serialVersionUID = 1L;

	private final long _id;
	private final ProcessCallable<T> _processCallable;
	private final long _rpcRelayTimeout;

}