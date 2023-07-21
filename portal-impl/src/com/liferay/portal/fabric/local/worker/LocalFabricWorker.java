/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.local.worker;

import com.liferay.petra.concurrent.NoticeableFuture;
import com.liferay.petra.process.ProcessCallable;
import com.liferay.petra.process.ProcessChannel;
import com.liferay.portal.fabric.status.FabricStatus;
import com.liferay.portal.fabric.status.RemoteFabricStatus;
import com.liferay.portal.fabric.worker.FabricWorker;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class LocalFabricWorker<T extends Serializable>
	implements FabricWorker<T> {

	public LocalFabricWorker(ProcessChannel<T> processChannel) {
		_processChannel = processChannel;

		_fabricStatus = new RemoteFabricStatus(
			new LocalFabricWorkerProcessCallableExecutor(_processChannel));
	}

	@Override
	public FabricStatus getFabricStatus() {
		return _fabricStatus;
	}

	@Override
	public NoticeableFuture<T> getProcessNoticeableFuture() {
		return _processChannel.getProcessNoticeableFuture();
	}

	@Override
	public <V extends Serializable> NoticeableFuture<V> write(
		ProcessCallable<V> processCallable) {

		return _processChannel.write(processCallable);
	}

	private final FabricStatus _fabricStatus;
	private final ProcessChannel<T> _processChannel;

}