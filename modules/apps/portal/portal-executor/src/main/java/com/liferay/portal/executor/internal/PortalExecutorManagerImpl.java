/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.executor.internal;

import com.liferay.portal.kernel.concurrent.FutureListener;
import com.liferay.portal.kernel.concurrent.NoticeableFuture;
import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.executor.PortalExecutorManager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shuyang Zhou
 */
@Component(immediate = true, service = PortalExecutorManager.class)
public class PortalExecutorManagerImpl implements PortalExecutorManager {

	@Override
	public ThreadPoolExecutor getPortalExecutor(String name) {
		return getPortalExecutor(name, true);
	}

	@Override
	public ThreadPoolExecutor getPortalExecutor(
		String name, boolean createIfAbsent) {

		ThreadPoolExecutor threadPoolExecutor = _threadPoolExecutors.get(name);

		if ((threadPoolExecutor == null) && createIfAbsent) {
			threadPoolExecutor = _portalExecutorFactory.createPortalExecutor(
				name);

			ThreadPoolExecutor previousThreadPoolExecutor =
				registerPortalExecutor(name, threadPoolExecutor);

			if (previousThreadPoolExecutor != null) {
				threadPoolExecutor.shutdown();

				threadPoolExecutor = previousThreadPoolExecutor;
			}
		}

		return threadPoolExecutor;
	}

	@Override
	public ThreadPoolExecutor registerPortalExecutor(
		String name, ThreadPoolExecutor threadPoolExecutor) {

		ThreadPoolExecutor previousThreadPoolExecutor =
			_threadPoolExecutors.putIfAbsent(name, threadPoolExecutor);

		if (previousThreadPoolExecutor == null) {
			NoticeableFuture<Void> terminationNoticeableFuture =
				threadPoolExecutor.terminationNoticeableFuture();

			terminationNoticeableFuture.addFutureListener(
				new UnregisterFutureListener(name));
		}

		return previousThreadPoolExecutor;
	}

	@Reference(unbind = "-")
	public void setPortalExecutorFactory(
		PortalExecutorFactory portalExecutorFactory) {

		_portalExecutorFactory = portalExecutorFactory;
	}

	@Override
	public void shutdown() {
		shutdown(false);
	}

	@Override
	public void shutdown(boolean interrupt) {
		for (ThreadPoolExecutor threadPoolExecutor :
				_threadPoolExecutors.values()) {

			if (interrupt) {
				threadPoolExecutor.shutdownNow();
			}
			else {
				threadPoolExecutor.shutdown();
			}
		}
	}

	@Deactivate
	protected void deactivate() {
		shutdown(true);
	}

	protected class UnregisterFutureListener implements FutureListener<Void> {

		@Override
		public void complete(Future<Void> future) {
			_threadPoolExecutors.remove(name);
		}

		protected UnregisterFutureListener(String name) {
			this.name = name;
		}

		protected final String name;

	}

	private PortalExecutorFactory _portalExecutorFactory;
	private final ConcurrentMap<String, ThreadPoolExecutor>
		_threadPoolExecutors = new ConcurrentHashMap<>();

}