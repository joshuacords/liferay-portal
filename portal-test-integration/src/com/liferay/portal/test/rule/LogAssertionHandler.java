/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.test.rule;

import com.liferay.petra.string.StringBundler;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * @author William Newbury
 */
public class LogAssertionHandler extends Handler {

	public static final LogAssertionHandler INSTANCE =
		new LogAssertionHandler();

	@Override
	public void close() throws SecurityException {
	}

	@Override
	public void flush() {
	}

	@Override
	public void publish(LogRecord logRecord) {
		Level level = logRecord.getLevel();

		if (level.equals(Level.SEVERE)) {
			StringBundler sb = new StringBundler(6);

			sb.append("{level=");
			sb.append(logRecord.getLevel());
			sb.append(", loggerName=");
			sb.append(logRecord.getLoggerName());
			sb.append(", message=");
			sb.append(logRecord.getMessage());

			LogAssertionTestRule.caughtFailure(
				new AssertionError(sb.toString(), logRecord.getThrown()));
		}
	}

}