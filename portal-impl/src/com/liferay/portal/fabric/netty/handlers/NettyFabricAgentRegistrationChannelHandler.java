/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.handlers;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.fabric.agent.FabricAgentRegistry;
import com.liferay.portal.fabric.netty.agent.NettyFabricAgentConfig;
import com.liferay.portal.fabric.netty.agent.NettyFabricAgentStub;
import com.liferay.portal.fabric.netty.fileserver.handlers.FileResponseChannelHandler;
import com.liferay.portal.fabric.netty.repository.NettyRepository;
import com.liferay.portal.fabric.repository.Repository;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Shuyang Zhou
 */
public class NettyFabricAgentRegistrationChannelHandler
	extends SimpleChannelInboundHandler<NettyFabricAgentConfig> {

	public NettyFabricAgentRegistrationChannelHandler(
		FabricAgentRegistry fabricAgentRegistry, Path repositoryParentPath,
		EventExecutorGroup eventExecutorGroup, long getFileTimeout,
		long rpcRelayTimeout, long startupTimeout) {

		if (fabricAgentRegistry == null) {
			throw new NullPointerException("Fabric agent registry is null");
		}

		if (repositoryParentPath == null) {
			throw new NullPointerException("Repository parent path is null");
		}

		if (eventExecutorGroup == null) {
			throw new NullPointerException("Event executor group is null");
		}

		_fabricAgentRegistry = fabricAgentRegistry;
		_repositoryParentPath = repositoryParentPath;
		_eventExecutorGroup = eventExecutorGroup;
		_getFileTimeout = getFileTimeout;
		_rpcRelayTimeout = rpcRelayTimeout;
		_startupTimeout = startupTimeout;
	}

	@Override
	public void exceptionCaught(
		ChannelHandlerContext channelHandlerContext, Throwable throwable) {

		final Channel channel = channelHandlerContext.channel();

		_log.error("Closing " + channel + " due to:", throwable);

		ChannelFuture channelFuture = channel.close();

		channelFuture.addListener(
			new ChannelFutureListener() {

				@Override
				public void operationComplete(ChannelFuture channelFuture) {
					if (_log.isInfoEnabled()) {
						_log.info(channel + " is closed");
					}
				}

			});
	}

	@Override
	protected void channelRead0(
			ChannelHandlerContext channelHandlerContext,
			NettyFabricAgentConfig nettyFabricAgentConfig)
		throws IOException {

		Channel channel = channelHandlerContext.channel();

		String socketAddressString = String.valueOf(channel.localAddress());

		Path repositoryPath = Paths.get(
			_repositoryParentPath.toString(),
			StringUtil.replace(
				socketAddressString, CharPool.COLON, CharPool.DASH));

		Files.createDirectories(repositoryPath);

		Repository<Channel> repository = new NettyRepository(
			repositoryPath, _getFileTimeout);

		ChannelPipeline channelPipeline = channel.pipeline();

		channelPipeline.addLast(
			new FileResponseChannelHandler(
				repository.getAsyncBroker(), _eventExecutorGroup));

		NettyFabricAgentStub nettyFabricAgentStub = new NettyFabricAgentStub(
			channel, repository, nettyFabricAgentConfig.getRepositoryPath(),
			_rpcRelayTimeout, _startupTimeout);

		if (!_fabricAgentRegistry.registerFabricAgent(
				nettyFabricAgentStub,
				new OnRegistration(
					channel, nettyFabricAgentStub, repository))) {

			if (_log.isWarnEnabled()) {
				_log.warn("Rejected duplicated fabric agent on " + channel);
			}

			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Registered fabric agent on " + channel);
		}
	}

	protected class OnRegistration implements Runnable {

		public OnRegistration(
			Channel channel, NettyFabricAgentStub nettyFabricAgentStub,
			Repository<Channel> repository) {

			_channel = channel;
			_nettyFabricAgentStub = nettyFabricAgentStub;
			_repository = repository;
		}

		@Override
		public void run() {
			NettyChannelAttributes.setNettyFabricAgentStub(
				_channel, _nettyFabricAgentStub);

			ChannelFuture channelFuture = _channel.closeFuture();

			channelFuture.addListener(
				new PostDisconnectChannelFutureListener(
					_channel, _nettyFabricAgentStub, _repository));
		}

		private final Channel _channel;
		private final NettyFabricAgentStub _nettyFabricAgentStub;
		private final Repository<Channel> _repository;

	}

	protected class PostDisconnectChannelFutureListener
		implements ChannelFutureListener {

		public PostDisconnectChannelFutureListener(
			Channel channel, NettyFabricAgentStub nettyFabricAgentStub,
			Repository<Channel> repository) {

			_channel = channel;
			_nettyFabricAgentStub = nettyFabricAgentStub;
			_repository = repository;
		}

		@Override
		public void operationComplete(ChannelFuture channelFuture) {
			if (_fabricAgentRegistry.unregisterFabricAgent(
					_nettyFabricAgentStub, null)) {

				if (_log.isInfoEnabled()) {
					_log.info("Unregistered fabric agent on " + _channel);
				}
			}
			else if (_log.isWarnEnabled()) {
				_log.warn("Unable to unregister fabric agent on " + _channel);
			}

			_repository.dispose(true);
		}

		private final Channel _channel;
		private final NettyFabricAgentStub _nettyFabricAgentStub;
		private final Repository<Channel> _repository;

	}

	private static final Log _log = LogFactoryUtil.getLog(
		NettyFabricAgentRegistrationChannelHandler.class);

	private final EventExecutorGroup _eventExecutorGroup;
	private final FabricAgentRegistry _fabricAgentRegistry;
	private final long _getFileTimeout;
	private final Path _repositoryParentPath;
	private final long _rpcRelayTimeout;
	private final long _startupTimeout;

}