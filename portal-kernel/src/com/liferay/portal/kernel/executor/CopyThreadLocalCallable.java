/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.executor;

import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.util.ThreadLocalBinder;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author     Shuyang Zhou
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public abstract class CopyThreadLocalCallable<T> implements Callable<T> {

	public CopyThreadLocalCallable(boolean readOnly, boolean clearOnExit) {
		this(null, readOnly, clearOnExit);
	}

	public CopyThreadLocalCallable(
		ThreadLocalBinder threadLocalBinder, boolean readOnly,
		boolean clearOnExit) {

		_threadLocalBinder = threadLocalBinder;

		if (_threadLocalBinder != null) {
			_threadLocalBinder.record();
		}

		if (readOnly) {
			_longLivedThreadLocals = Collections.unmodifiableMap(
				CentralizedThreadLocal.getLongLivedThreadLocals());
			_shortLivedlThreadLocals = Collections.unmodifiableMap(
				CentralizedThreadLocal.getShortLivedThreadLocals());
		}
		else {
			_longLivedThreadLocals =
				CentralizedThreadLocal.getLongLivedThreadLocals();
			_shortLivedlThreadLocals =
				CentralizedThreadLocal.getShortLivedThreadLocals();
		}

		_clearOnExit = clearOnExit;
	}

	@Override
	public final T call() throws Exception {
		CentralizedThreadLocal.setThreadLocals(
			_longLivedThreadLocals, _shortLivedlThreadLocals);

		if (_threadLocalBinder != null) {
			_threadLocalBinder.bind();
		}

		try {
			return doCall();
		}
		finally {
			if (_clearOnExit) {
				if (_threadLocalBinder != null) {
					_threadLocalBinder.cleanUp();
				}

				CentralizedThreadLocal.clearLongLivedThreadLocals();
				CentralizedThreadLocal.clearShortLivedThreadLocals();
			}
		}
	}

	public abstract T doCall() throws Exception;

	private final boolean _clearOnExit;
	private final Map<CentralizedThreadLocal<?>, Object> _longLivedThreadLocals;
	private final Map<CentralizedThreadLocal<?>, Object>
		_shortLivedlThreadLocals;
	private final ThreadLocalBinder _threadLocalBinder;

}