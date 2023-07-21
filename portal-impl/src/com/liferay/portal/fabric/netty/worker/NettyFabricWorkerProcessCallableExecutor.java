/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.worker;

import com.liferay.petra.concurrent.NoticeableFuture;
import com.liferay.petra.process.ProcessCallable;
import com.liferay.portal.fabric.netty.rpc.RPCUtil;
import com.liferay.portal.fabric.status.JMXProxyUtil;

import io.netty.channel.Channel;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class NettyFabricWorkerProcessCallableExecutor
	implements JMXProxyUtil.ProcessCallableExecutor {

	public NettyFabricWorkerProcessCallableExecutor(
		Channel channel, long fabricWorkerId, long rpcRelayTime) {

		_channel = channel;
		_fabricWorkerId = fabricWorkerId;

		_rpcRelayTimeout = rpcRelayTime;
	}

	@Override
	public <V extends Serializable> NoticeableFuture<V> execute(
		ProcessCallable<V> processCallable) {

		return RPCUtil.execute(
			_channel,
			new NettyFabricWorkerBridgeRPCCallable<V>(
				_fabricWorkerId, processCallable, _rpcRelayTimeout));
	}

	private final Channel _channel;
	private final long _fabricWorkerId;
	private final long _rpcRelayTimeout;

}