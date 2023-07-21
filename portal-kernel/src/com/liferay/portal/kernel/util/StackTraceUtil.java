/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;

import java.io.PrintWriter;

/**
 * @author Brian Wing Shun Chan
 */
public class StackTraceUtil {

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static String getCallerKey() {
		Exception exception = new Exception();

		StackTraceElement[] stackTraceElements = exception.getStackTrace();

		StackTraceElement stackTraceElement = stackTraceElements[1];

		return StringBundler.concat(
			stackTraceElement.getClassName(), "#",
			stackTraceElement.getMethodName(), "#",
			String.valueOf(stackTraceElement.getLineNumber()));
	}

	public static String getStackTrace(Throwable t) {
		String stackTrace = null;

		PrintWriter printWriter = null;

		try {
			UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

			printWriter = UnsyncPrintWriterPool.borrow(unsyncStringWriter);

			t.printStackTrace(printWriter);

			printWriter.flush();

			stackTrace = unsyncStringWriter.toString();
		}
		finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}

		return stackTrace;
	}

}