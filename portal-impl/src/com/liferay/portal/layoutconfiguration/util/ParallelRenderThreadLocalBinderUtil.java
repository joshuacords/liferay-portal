/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.layoutconfiguration.util;

import com.liferay.portal.kernel.util.ThreadLocalBinder;

/**
 * @author Shuyang Zhou
 */
public class ParallelRenderThreadLocalBinderUtil {

	public static void bind() {
		_threadLocalBinder.bind();
	}

	public static void cleanUp() {
		_threadLocalBinder.cleanUp();
	}

	public static ThreadLocalBinder getThreadLocalBinder() {
		return _threadLocalBinder;
	}

	public static void record() {
		_threadLocalBinder.record();
	}

	public static void setThreadLocalBinder(
		ThreadLocalBinder threadLocalBinder) {

		_threadLocalBinder = threadLocalBinder;
	}

	private static ThreadLocalBinder _threadLocalBinder;

}