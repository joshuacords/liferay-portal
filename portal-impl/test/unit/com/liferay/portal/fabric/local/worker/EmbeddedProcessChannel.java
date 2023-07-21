/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.local.worker;

import com.liferay.petra.concurrent.DefaultNoticeableFuture;
import com.liferay.petra.concurrent.NoticeableFuture;
import com.liferay.petra.process.ProcessCallable;
import com.liferay.petra.process.ProcessChannel;
import com.liferay.petra.process.ProcessException;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class EmbeddedProcessChannel<T extends Serializable>
	implements ProcessChannel<T> {

	public EmbeddedProcessChannel(NoticeableFuture<T> noticeableFuture) {
		_noticeableFuture = noticeableFuture;
	}

	@Override
	public NoticeableFuture<T> getProcessNoticeableFuture() {
		return _noticeableFuture;
	}

	@Override
	public <V extends Serializable> NoticeableFuture<V> write(
		ProcessCallable<V> processCallable) {

		DefaultNoticeableFuture<V> defaultNoticeableFuture =
			new DefaultNoticeableFuture<>();

		try {
			defaultNoticeableFuture.set(processCallable.call());
		}
		catch (ProcessException processException) {
			defaultNoticeableFuture.setException(processException);
		}

		return defaultNoticeableFuture;
	}

	private final NoticeableFuture<T> _noticeableFuture;

}