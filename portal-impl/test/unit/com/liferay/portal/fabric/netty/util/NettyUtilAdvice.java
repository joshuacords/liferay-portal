/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.netty.util;

import com.liferay.petra.concurrent.FutureListener;
import com.liferay.petra.concurrent.NoticeableFuture;

import io.netty.channel.Channel;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author Shuyang Zhou
 */
@Aspect
public class NettyUtilAdvice {

	public static void shutdown() {
		_scheduledExecutorService.shutdown();
	}

	@Around(
		"execution(public static void com.liferay.portal.fabric.netty.util." +
			"NettyUtil.scheduleCancellation(io.netty.channel.Channel, " +
				"com.liferay.petra.concurrent.NoticeableFuture, long)) && " +
					"args(channel, noticeableFuture, timeout)"
	)
	public <T> void scheduleCancellation(
		Channel channel, final NoticeableFuture<T> noticeableFuture,
		long timeout) {

		if (timeout == 0) {
			noticeableFuture.cancel(true);

			return;
		}

		final Future<?> cancellationFuture = _scheduledExecutorService.schedule(
			new Runnable() {

				@Override
				public void run() {
					noticeableFuture.cancel(true);
				}

			},
			timeout, TimeUnit.MILLISECONDS);

		noticeableFuture.addFutureListener(
			new FutureListener<T>() {

				@Override
				public void complete(Future<T> future) {
					cancellationFuture.cancel(true);
				}

			});
	}

	private static final ScheduledExecutorService _scheduledExecutorService =
		Executors.newSingleThreadScheduledExecutor();

}