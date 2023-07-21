/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.rpc;

import com.liferay.petra.concurrent.AsyncBroker;
import com.liferay.petra.concurrent.NoticeableFuture;
import com.liferay.portal.fabric.netty.handlers.NettyChannelAttributes;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class RPCUtil {

	public static <T extends Serializable> NoticeableFuture<T> execute(
		Channel channel, RPCCallable<T> rpcCallable) {

		final AsyncBroker<Long, T> asyncBroker =
			NettyChannelAttributes.getAsyncBroker(channel);

		final long id = NettyChannelAttributes.nextId(channel);

		final NoticeableFuture<T> noticeableFuture = asyncBroker.post(id);

		ChannelFuture channelFuture = channel.writeAndFlush(
			new RPCRequest<T>(id, rpcCallable));

		channelFuture.addListener(
			new ChannelFutureListener() {

				@Override
				public void operationComplete(ChannelFuture channelFuture) {
					if (channelFuture.isSuccess()) {
						return;
					}

					if (channelFuture.isCancelled()) {
						noticeableFuture.cancel(true);

						return;
					}

					if (!asyncBroker.takeWithException(
							id, channelFuture.cause())) {

						_log.error(
							"Unable to place exception because no future " +
								"exists with ID " + id,
							channelFuture.cause());
					}
				}

			});

		return noticeableFuture;
	}

	private static final Log _log = LogFactoryUtil.getLog(RPCUtil.class);

}