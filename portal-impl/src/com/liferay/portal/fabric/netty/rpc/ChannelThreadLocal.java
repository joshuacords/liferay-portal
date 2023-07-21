/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.rpc;

import com.liferay.petra.lang.CentralizedThreadLocal;

import io.netty.channel.Channel;

/**
 * @author Shuyang Zhou
 */
public class ChannelThreadLocal {

	public static Channel getChannel() {
		Channel channel = _channelThreadLocal.get();

		if (channel == null) {
			throw new IllegalStateException("Channel is null");
		}

		return channel;
	}

	public static void removeChannel() {
		_channelThreadLocal.remove();
	}

	public static void setChannel(Channel channel) {
		_channelThreadLocal.set(channel);
	}

	private static final ThreadLocal<Channel> _channelThreadLocal =
		new CentralizedThreadLocal<>(false);

}