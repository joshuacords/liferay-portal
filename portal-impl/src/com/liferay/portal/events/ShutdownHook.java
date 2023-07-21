/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.events;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.Map;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class ShutdownHook implements Runnable {

	@Override
	public void run() {
		if (GetterUtil.getBoolean(
				System.getProperty("shutdown.hook.print.full.thread.dump"))) {

			printFullThreadDump();
		}
	}

	protected void printFullThreadDump() {
		StringBundler sb = new StringBundler();

		sb.append("Full thread dump ");
		sb.append(System.getProperty("java.vm.name"));
		sb.append(" ");
		sb.append(System.getProperty("java.vm.version"));
		sb.append("\n\n");

		Map<Thread, StackTraceElement[]> stackTraces =
			Thread.getAllStackTraces();

		for (Map.Entry<Thread, StackTraceElement[]> entry :
				stackTraces.entrySet()) {

			Thread thread = entry.getKey();
			StackTraceElement[] elements = entry.getValue();

			sb.append("\"");
			sb.append(thread.getName());
			sb.append("\"");

			ThreadGroup threadGroup = thread.getThreadGroup();

			if (threadGroup != null) {
				sb.append(" (");
				sb.append(threadGroup.getName());
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}

			sb.append(", priority=");
			sb.append(thread.getPriority());
			sb.append(", id=");
			sb.append(thread.getId());
			sb.append(", state=");
			sb.append(thread.getState());
			sb.append("\n");

			for (StackTraceElement element : elements) {
				sb.append("\t");
				sb.append(element);
				sb.append("\n");
			}

			sb.append("\n");
		}

		System.out.println(sb);
	}

}