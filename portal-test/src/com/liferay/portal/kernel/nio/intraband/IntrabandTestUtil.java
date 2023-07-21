/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband;

import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SocketUtil;

import java.io.IOException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;

import java.nio.channels.ScatteringByteChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import java.util.logging.LogRecord;

import org.junit.Assert;

/**
 * @author Shuyang Zhou
 */
public class IntrabandTestUtil {

	public static void assertMessageStartWith(
		LogRecord logRecord, String messagePrefix) {

		String message = logRecord.getMessage();

		Assert.assertTrue(message.startsWith(messagePrefix));
	}

	public static <T> T createProxy(Class<?>... interfaces) {
		return (T)ProxyUtil.newProxyInstance(
			IntrabandTestUtil.class.getClassLoader(), interfaces,
			new InvocationHandler() {

				@Override
				public Object invoke(
					Object proxy, Method method, Object[] args) {

					throw new UnsupportedOperationException();
				}

			});
	}

	public static SocketChannel[] createSocketChannelPeers()
		throws IOException {

		SocketChannel clientPeerSocketChannel = null;
		SocketChannel serverPeerSocketChannel = null;

		try (ServerSocketChannel serverSocketChannel =
				SocketUtil.createServerSocketChannel(
					InetAddress.getLocalHost(), 15238,
					_serverSocketConfigurator)) {

			ServerSocket serverSocket = serverSocketChannel.socket();

			clientPeerSocketChannel = SocketChannel.open(
				new InetSocketAddress(
					InetAddress.getLocalHost(), serverSocket.getLocalPort()));

			serverPeerSocketChannel = serverSocketChannel.accept();
		}

		SocketChannel[] socketChannels = new SocketChannel[2];

		socketChannels[0] = serverPeerSocketChannel;
		socketChannels[1] = clientPeerSocketChannel;

		return socketChannels;
	}

	public static Datagram readDatagramFully(
			ScatteringByteChannel scatteringByteChannel)
		throws IOException {

		Datagram datagram = DatagramHelper.createReceiveDatagram();

		while (!DatagramHelper.readFrom(datagram, scatteringByteChannel));

		return datagram;
	}

	private static final SocketUtil.ServerSocketConfigurator
		_serverSocketConfigurator = new SocketUtil.ServerSocketConfigurator() {

			@Override
			public void configure(ServerSocket serverSocket)
				throws SocketException {

				serverSocket.setReuseAddress(true);
			}

		};

}