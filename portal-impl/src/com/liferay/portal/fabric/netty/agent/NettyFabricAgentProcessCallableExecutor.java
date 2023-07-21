/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.agent;

import com.liferay.petra.concurrent.NoticeableFuture;
import com.liferay.petra.process.ProcessCallable;
import com.liferay.portal.fabric.netty.rpc.RPCUtil;
import com.liferay.portal.fabric.netty.rpc.SyncProcessRPCCallable;
import com.liferay.portal.fabric.status.JMXProxyUtil;

import io.netty.channel.Channel;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class NettyFabricAgentProcessCallableExecutor
	implements JMXProxyUtil.ProcessCallableExecutor {

	public NettyFabricAgentProcessCallableExecutor(Channel channel) {
		_channel = channel;
	}

	@Override
	public <V extends Serializable> NoticeableFuture<V> execute(
		ProcessCallable<V> processCallable) {

		return RPCUtil.execute(
			_channel, new SyncProcessRPCCallable<V>(processCallable));
	}

	private final Channel _channel;

}