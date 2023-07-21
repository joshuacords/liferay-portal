/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.worker;

import com.liferay.petra.concurrent.BaseFutureListener;
import com.liferay.petra.concurrent.DefaultNoticeableFuture;
import com.liferay.petra.concurrent.FutureListener;
import com.liferay.petra.concurrent.NoticeableFuture;
import com.liferay.petra.process.ProcessCallable;
import com.liferay.portal.fabric.netty.rpc.RPCUtil;
import com.liferay.portal.fabric.repository.Repository;
import com.liferay.portal.fabric.status.FabricStatus;
import com.liferay.portal.fabric.status.RemoteFabricStatus;
import com.liferay.portal.fabric.worker.FabricWorker;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import java.io.Serializable;

import java.nio.file.Path;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * @author Shuyang Zhou
 */
public class NettyFabricWorkerStub<T extends Serializable>
	implements FabricWorker<T> {

	public NettyFabricWorkerStub(
		long id, Channel channel, Repository<Channel> repository,
		Map<Path, Path> outputPathMap, long rpcRelayTimeout) {

		if (channel == null) {
			throw new NullPointerException("Channel is null");
		}

		if (repository == null) {
			throw new NullPointerException("Repository is null");
		}

		if (outputPathMap == null) {
			throw new NullPointerException("Output path map is null");
		}

		_id = id;
		_channel = channel;
		_repository = repository;
		_outputPathMap = outputPathMap;
		_rpcRelayTimeout = rpcRelayTimeout;

		final ChannelFuture channelFuture = _channel.closeFuture();

		final ChannelFutureListener channelCloseListener =
			new ChannelFutureListener() {

				@Override
				public void operationComplete(ChannelFuture channelFuture) {
					_defaultNoticeableFuture.cancel(true);
				}

			};

		channelFuture.addListener(channelCloseListener);

		_defaultNoticeableFuture.addFutureListener(
			new FutureListener<T>() {

				@Override
				public void complete(Future<T> future) {
					channelFuture.removeListener(channelCloseListener);
				}

			});
	}

	@Override
	public FabricStatus getFabricStatus() {
		return new RemoteFabricStatus(
			new NettyFabricWorkerProcessCallableExecutor(
				_channel, _id, _rpcRelayTimeout));
	}

	@Override
	public NoticeableFuture<T> getProcessNoticeableFuture() {
		return _defaultNoticeableFuture;
	}

	public void setCancel() {
		_defaultNoticeableFuture.cancel(true);
	}

	public void setException(Throwable t) {
		_defaultNoticeableFuture.setException(t);
	}

	public void setResult(final T result) {
		NoticeableFuture<Map<Path, Path>> noticeableFuture =
			_repository.getFiles(_channel, _outputPathMap, true);

		noticeableFuture.addFutureListener(
			new BaseFutureListener<Map<Path, Path>>() {

				@Override
				public void completeWithCancel(Future<Map<Path, Path>> future) {
					_defaultNoticeableFuture.cancel(true);
				}

				@Override
				public void completeWithException(
					Future<Map<Path, Path>> future, Throwable throwable) {

					_defaultNoticeableFuture.setException(throwable);
				}

				@Override
				public void completeWithResult(
					Future<Map<Path, Path>> future, Map<Path, Path> map) {

					_defaultNoticeableFuture.set(result);
				}

			});
	}

	@Override
	public <V extends Serializable> NoticeableFuture<V> write(
		ProcessCallable<V> processCallable) {

		return RPCUtil.execute(
			_channel,
			new NettyFabricWorkerBridgeRPCCallable<V>(
				_id, processCallable, _rpcRelayTimeout));
	}

	private final Channel _channel;
	private final DefaultNoticeableFuture<T> _defaultNoticeableFuture =
		new DefaultNoticeableFuture<>();
	private final long _id;
	private final Map<Path, Path> _outputPathMap;
	private final Repository<Channel> _repository;
	private final long _rpcRelayTimeout;

}