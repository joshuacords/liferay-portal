/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.test.rule;

import com.liferay.petra.string.StringBundler;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;

/**
 * @author William Newbury
 */
public class LogAssertionAppender extends AppenderSkeleton {

	public static final LogAssertionAppender INSTANCE =
		new LogAssertionAppender();

	@Override
	public void close() {
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	@Override
	protected void append(LoggingEvent loggingEvent) {
		Level level = loggingEvent.getLevel();

		if (level.equals(Level.ERROR) || level.equals(Level.FATAL)) {
			StringBundler sb = new StringBundler(6);

			sb.append("{level=");
			sb.append(loggingEvent.getLevel());
			sb.append(", loggerName=");
			sb.append(loggingEvent.getLoggerName());
			sb.append(", message=");
			sb.append(loggingEvent.getMessage());

			ThrowableInformation throwableInformation =
				loggingEvent.getThrowableInformation();

			LogAssertionTestRule.caughtFailure(
				new AssertionError(
					sb.toString(), throwableInformation.getThrowable()));
		}
	}

}