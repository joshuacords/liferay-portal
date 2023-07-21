/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.concurrent;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LoggingTimer;

/**
 * @author     Michael C. Han
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public abstract class ThrowableAwareRunnable implements Runnable {

	public Throwable getThrowable() {
		return _throwable;
	}

	public boolean hasException() {
		if (_throwable != null) {
			return true;
		}

		return false;
	}

	@Override
	public void run() {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			doRun();
		}
		catch (Exception exception) {
			_log.error(
				"Unable to process runnable: " + exception.getMessage(),
				exception);

			_throwable = exception;
		}
	}

	protected abstract void doRun() throws Exception;

	private static final Log _log = LogFactoryUtil.getLog(
		ThrowableAwareRunnable.class);

	private Throwable _throwable;

}