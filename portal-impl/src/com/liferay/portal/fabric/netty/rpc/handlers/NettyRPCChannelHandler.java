/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.rpc.handlers;

import com.liferay.portal.fabric.netty.rpc.RPCSerializable;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Shuyang Zhou
 */
@ChannelHandler.Sharable
public class NettyRPCChannelHandler
	extends SimpleChannelInboundHandler<RPCSerializable> {

	public static final NettyRPCChannelHandler INSTANCE =
		new NettyRPCChannelHandler();

	public static final String NAME = NettyRPCChannelHandler.class.getName();

	@Override
	protected void channelRead0(
		ChannelHandlerContext channelHandlerContext,
		RPCSerializable rpcSerializable) {

		rpcSerializable.execute(channelHandlerContext.channel());
	}

	private NettyRPCChannelHandler() {
	}

}