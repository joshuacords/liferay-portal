/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.test.log;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.log.LogWrapper;
import com.liferay.portal.log.Log4jLogImpl;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author Shuyang Zhou
 */
public class Log4JLoggerTestUtil {

	public static CaptureAppender configureLog4JLogger(
		String name, Level level) {

		LogWrapper logWrapper = (LogWrapper)LogFactoryUtil.getLog(name);

		Log log = logWrapper.getWrappedLog();

		if (!(log instanceof Log4jLogImpl)) {
			throw new IllegalStateException(
				"Log " + name + " is not a Log4j logger");
		}

		Log4jLogImpl log4jLogImpl = (Log4jLogImpl)log;

		Logger logger = log4jLogImpl.getWrappedLogger();

		CaptureAppender captureAppender = new CaptureAppender(logger);

		logger.addAppender(captureAppender);

		logger.setLevel(level);

		return captureAppender;
	}

	public static Level setLoggerLevel(String name, Level level) {
		LogWrapper logWrapper = (LogWrapper)LogFactoryUtil.getLog(name);

		Log log = logWrapper.getWrappedLog();

		if (!(log instanceof Log4jLogImpl)) {
			throw new IllegalStateException(
				"Log " + name + " is not a Log4j logger");
		}

		Log4jLogImpl log4jLogImpl = (Log4jLogImpl)log;

		Logger logger = log4jLogImpl.getWrappedLogger();

		Level oldLevel = logger.getLevel();

		logger.setLevel(level);

		return oldLevel;
	}

}