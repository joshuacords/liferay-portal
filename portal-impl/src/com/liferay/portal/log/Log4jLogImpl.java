/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.log;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogWrapper;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author Brian Wing Shun Chan
 */
public class Log4jLogImpl implements Log {

	public Log4jLogImpl(Logger logger) {
		_logger = logger;
	}

	@Override
	public void debug(Object msg) {
		_logger.log(_logWrapperClassName, Level.DEBUG, msg, null);
	}

	@Override
	public void debug(Object msg, Throwable t) {
		_logger.log(_logWrapperClassName, Level.DEBUG, msg, t);
	}

	@Override
	public void debug(Throwable t) {
		_logger.log(_logWrapperClassName, Level.DEBUG, null, t);
	}

	@Override
	public void error(Object msg) {
		_logger.log(_logWrapperClassName, Level.ERROR, msg, null);
	}

	@Override
	public void error(Object msg, Throwable t) {
		_logger.log(_logWrapperClassName, Level.ERROR, msg, t);
	}

	@Override
	public void error(Throwable t) {
		_logger.log(_logWrapperClassName, Level.ERROR, null, t);
	}

	@Override
	public void fatal(Object msg) {
		_logger.log(_logWrapperClassName, Level.FATAL, msg, null);
	}

	@Override
	public void fatal(Object msg, Throwable t) {
		_logger.log(_logWrapperClassName, Level.FATAL, msg, t);
	}

	@Override
	public void fatal(Throwable t) {
		_logger.log(_logWrapperClassName, Level.FATAL, null, t);
	}

	public Logger getWrappedLogger() {
		return _logger;
	}

	@Override
	public void info(Object msg) {
		_logger.log(_logWrapperClassName, Level.INFO, msg, null);
	}

	@Override
	public void info(Object msg, Throwable t) {
		_logger.log(_logWrapperClassName, Level.INFO, msg, t);
	}

	@Override
	public void info(Throwable t) {
		_logger.log(_logWrapperClassName, Level.INFO, null, t);
	}

	@Override
	public boolean isDebugEnabled() {
		return _logger.isDebugEnabled();
	}

	@Override
	public boolean isErrorEnabled() {
		return _logger.isEnabledFor(Level.ERROR);
	}

	@Override
	public boolean isFatalEnabled() {
		return _logger.isEnabledFor(Level.FATAL);
	}

	@Override
	public boolean isInfoEnabled() {
		return _logger.isInfoEnabled();
	}

	@Override
	public boolean isTraceEnabled() {
		return _logger.isTraceEnabled();
	}

	@Override
	public boolean isWarnEnabled() {
		return _logger.isEnabledFor(Level.WARN);
	}

	@Override
	public void setLogWrapperClassName(String className) {
		_logWrapperClassName = className;
	}

	@Override
	public void trace(Object msg) {
		_logger.log(_logWrapperClassName, Level.TRACE, msg, null);
	}

	@Override
	public void trace(Object msg, Throwable t) {
		_logger.log(_logWrapperClassName, Level.TRACE, msg, t);
	}

	@Override
	public void trace(Throwable t) {
		_logger.log(_logWrapperClassName, Level.TRACE, null, t);
	}

	@Override
	public void warn(Object msg) {
		_logger.log(_logWrapperClassName, Level.WARN, msg, null);
	}

	@Override
	public void warn(Object msg, Throwable t) {
		_logger.log(_logWrapperClassName, Level.WARN, msg, t);
	}

	@Override
	public void warn(Throwable t) {
		_logger.log(_logWrapperClassName, Level.WARN, null, t);
	}

	private final Logger _logger;
	private String _logWrapperClassName = LogWrapper.class.getName();

}