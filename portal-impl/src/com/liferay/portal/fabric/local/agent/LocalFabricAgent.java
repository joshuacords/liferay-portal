/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.local.agent;

import com.liferay.petra.concurrent.FutureListener;
import com.liferay.petra.concurrent.NoticeableFuture;
import com.liferay.petra.process.ProcessCallable;
import com.liferay.petra.process.ProcessConfig;
import com.liferay.petra.process.ProcessException;
import com.liferay.petra.process.ProcessExecutor;
import com.liferay.portal.fabric.agent.FabricAgent;
import com.liferay.portal.fabric.local.worker.LocalFabricWorker;
import com.liferay.portal.fabric.status.FabricStatus;
import com.liferay.portal.fabric.status.LocalFabricStatus;
import com.liferay.portal.fabric.worker.FabricWorker;

import java.io.Serializable;

import java.util.Collection;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;

/**
 * @author Shuyang Zhou
 */
public class LocalFabricAgent implements FabricAgent {

	public LocalFabricAgent(ProcessExecutor processExecutor) {
		_processExecutor = processExecutor;
	}

	@Override
	public <T extends Serializable> FabricWorker<T> execute(
			ProcessConfig processConfig, ProcessCallable<T> processCallable)
		throws ProcessException {

		final FabricWorker<T> fabricWorker = new LocalFabricWorker<>(
			_processExecutor.execute(processConfig, processCallable));

		_fabricWorkerQueue.add(fabricWorker);

		NoticeableFuture<T> noticeableFuture =
			fabricWorker.getProcessNoticeableFuture();

		noticeableFuture.addFutureListener(
			new FutureListener<T>() {

				@Override
				public void complete(Future<T> future) {
					_fabricWorkerQueue.remove(fabricWorker);
				}

			});

		return fabricWorker;
	}

	@Override
	public FabricStatus getFabricStatus() {
		return LocalFabricStatus.INSTANCE;
	}

	@Override
	public Collection<? extends FabricWorker<?>> getFabricWorkers() {
		return Collections.unmodifiableCollection(_fabricWorkerQueue);
	}

	private final Queue<FabricWorker<?>> _fabricWorkerQueue =
		new ConcurrentLinkedQueue<>();
	private final ProcessExecutor _processExecutor;

}