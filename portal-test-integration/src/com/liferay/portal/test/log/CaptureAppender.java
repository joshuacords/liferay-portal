/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.test.log;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Closeable;

import java.lang.reflect.Field;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author Shuyang Zhou
 */
public class CaptureAppender extends AppenderSkeleton implements Closeable {

	public CaptureAppender(Logger logger) {
		_logger = logger;

		_level = _logger.getLevel();

		_parentCategory = logger.getParent();

		try {
			_parentField.set(_logger, null);
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void close() {
		closed = true;

		_logger.removeAppender(this);

		_logger.setLevel(_level);

		try {
			_parentField.set(_logger, _parentCategory);
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	public List<LoggingEvent> getLoggingEvents() {
		return _loggingEvents;
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	@Override
	protected void append(LoggingEvent loggingEvent) {
		_loggingEvents.add(new PrintableLoggingEvent(loggingEvent));
	}

	private static final Field _parentField;

	static {
		try {
			_parentField = ReflectionUtil.getDeclaredField(
				Category.class, "parent");
		}
		catch (Exception exception) {
			throw new ExceptionInInitializerError(exception);
		}
	}

	private final Level _level;
	private final Logger _logger;
	private final List<LoggingEvent> _loggingEvents =
		new CopyOnWriteArrayList<>();
	private final Category _parentCategory;

	private static class PrintableLoggingEvent extends LoggingEvent {

		@Override
		public String toString() {
			StringBundler sb = new StringBundler(5);

			sb.append("{level=");
			sb.append(getLevel());
			sb.append(", message=");
			sb.append(getMessage());
			sb.append("}");

			return sb.toString();
		}

		private PrintableLoggingEvent(LoggingEvent loggingEvent) {
			super(
				loggingEvent.getFQNOfLoggerClass(), loggingEvent.getLogger(),
				loggingEvent.getTimeStamp(), loggingEvent.getLevel(),
				loggingEvent.getMessage(), loggingEvent.getThreadName(),
				loggingEvent.getThrowableInformation(), loggingEvent.getNDC(),
				loggingEvent.getLocationInformation(),
				loggingEvent.getProperties());
		}

	}

}