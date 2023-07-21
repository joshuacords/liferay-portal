/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.log;

/**
 * @author Brian Wing Shun Chan
 */
public class LogWrapper implements Log {

	public LogWrapper(Log log) {
		_log = log;
	}

	@Override
	public void debug(Object msg) {
		try {
			_log.debug(msg);
		}
		catch (Exception exception) {
			printMsg(msg);
		}
	}

	@Override
	public void debug(Object msg, Throwable t) {
		try {
			_log.debug(msg, t);
		}
		catch (Exception exception) {
			printMsg(msg);
		}
	}

	@Override
	public void debug(Throwable t) {
		try {
			_log.debug(t);
		}
		catch (Exception exception) {
			printMsg(t.getMessage());
		}
	}

	@Override
	public void error(Object msg) {
		try {
			_log.error(msg);
		}
		catch (Exception exception) {
			printMsg(msg);
		}
	}

	@Override
	public void error(Object msg, Throwable t) {
		try {
			_log.error(msg, t);
		}
		catch (Exception exception) {
			printMsg(msg);
		}
	}

	@Override
	public void error(Throwable t) {
		try {
			_log.error(t);
		}
		catch (Exception exception) {
			printMsg(t.getMessage());
		}
	}

	@Override
	public void fatal(Object msg) {
		try {
			_log.fatal(msg);
		}
		catch (Exception exception) {
			printMsg(msg);
		}
	}

	@Override
	public void fatal(Object msg, Throwable t) {
		try {
			_log.fatal(msg, t);
		}
		catch (Exception exception) {
			printMsg(msg);
		}
	}

	@Override
	public void fatal(Throwable t) {
		try {
			_log.fatal(t);
		}
		catch (Exception exception) {
			printMsg(t.getMessage());
		}
	}

	public Log getWrappedLog() {
		return _log;
	}

	@Override
	public void info(Object msg) {
		try {
			_log.info(msg);
		}
		catch (Exception exception) {
			printMsg(msg);
		}
	}

	@Override
	public void info(Object msg, Throwable t) {
		try {
			_log.info(msg, t);
		}
		catch (Exception exception) {
			printMsg(msg);
		}
	}

	@Override
	public void info(Throwable t) {
		try {
			_log.info(t);
		}
		catch (Exception exception) {
			printMsg(t.getMessage());
		}
	}

	@Override
	public boolean isDebugEnabled() {
		return _log.isDebugEnabled();
	}

	@Override
	public boolean isErrorEnabled() {
		return _log.isErrorEnabled();
	}

	@Override
	public boolean isFatalEnabled() {
		return _log.isFatalEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		return _log.isInfoEnabled();
	}

	@Override
	public boolean isTraceEnabled() {
		return _log.isTraceEnabled();
	}

	@Override
	public boolean isWarnEnabled() {
		return _log.isWarnEnabled();
	}

	public void setLog(Log log) {
		_log = log;
	}

	@Override
	public void setLogWrapperClassName(String className) {
		_log.setLogWrapperClassName(className);
	}

	@Override
	public void trace(Object msg) {
		try {
			_log.trace(msg);
		}
		catch (Exception exception) {
			printMsg(msg);
		}
	}

	@Override
	public void trace(Object msg, Throwable t) {
		try {
			_log.trace(msg, t);
		}
		catch (Exception exception) {
			printMsg(msg);
		}
	}

	@Override
	public void trace(Throwable t) {
		try {
			_log.trace(t);
		}
		catch (Exception exception) {
			printMsg(t.getMessage());
		}
	}

	@Override
	public void warn(Object msg) {
		try {
			_log.warn(msg);
		}
		catch (Exception exception) {
			printMsg(msg);
		}
	}

	@Override
	public void warn(Object msg, Throwable t) {
		try {
			_log.warn(msg, t);
		}
		catch (Exception exception) {
			printMsg(msg);
		}
	}

	@Override
	public void warn(Throwable t) {
		try {
			_log.warn(t);
		}
		catch (Exception exception) {
			printMsg(t.getMessage());
		}
	}

	protected void printMsg(Object msg) {
		System.err.println(msg);
	}

	private Log _log;

}