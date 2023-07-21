/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.test.rule;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

/**
 * @author Shuyang Zhou
 */
public class LogAssertionUncaughtExceptionHandler
	implements Thread.UncaughtExceptionHandler {

	public LogAssertionUncaughtExceptionHandler(
		Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {

		_uncaughtExceptionHandler = uncaughtExceptionHandler;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {
		if (_uncaughtExceptionHandler != null) {
			_uncaughtExceptionHandler.uncaughtException(thread, throwable);
		}

		StringBundler sb = new StringBundler(4);

		sb.append("Uncaught exception in ");
		sb.append(thread);
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append(throwable);

		LogAssertionTestRule.caughtFailure(
			new AssertionError(sb.toString(), throwable));
	}

	private final Thread.UncaughtExceptionHandler _uncaughtExceptionHandler;

}