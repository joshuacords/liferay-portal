/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.log;

/**
 * @author Brian Wing Shun Chan
 */
public interface Log {

	public void debug(Object msg);

	public void debug(Object msg, Throwable t);

	public void debug(Throwable t);

	public void error(Object msg);

	public void error(Object msg, Throwable t);

	public void error(Throwable t);

	public void fatal(Object msg);

	public void fatal(Object msg, Throwable t);

	public void fatal(Throwable t);

	public void info(Object msg);

	public void info(Object msg, Throwable t);

	public void info(Throwable t);

	public boolean isDebugEnabled();

	public boolean isErrorEnabled();

	public boolean isFatalEnabled();

	public boolean isInfoEnabled();

	public boolean isTraceEnabled();

	public boolean isWarnEnabled();

	public void setLogWrapperClassName(String className);

	public void trace(Object msg);

	public void trace(Object msg, Throwable t);

	public void trace(Throwable t);

	public void warn(Object msg);

	public void warn(Object msg, Throwable t);

	public void warn(Throwable t);

}