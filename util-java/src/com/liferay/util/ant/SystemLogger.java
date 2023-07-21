/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.ant;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;

import java.io.IOException;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.util.StringUtils;

/**
 * @author Brian Wing Shun Chan
 */
public class SystemLogger extends DefaultLogger {

	@Override
	public void messageLogged(BuildEvent event) {
		int priority = event.getPriority();

		if (priority > msgOutputLevel) {
			return;
		}

		StringBundler sb = new StringBundler();

		try (UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(
					new UnsyncStringReader(event.getMessage()))) {

			String line = unsyncBufferedReader.readLine();

			boolean first = true;

			while (line != null) {
				if (!first) {
					sb.append(StringUtils.LINE_SEP);
				}

				first = false;

				sb.append("  ");
				sb.append(line);

				line = unsyncBufferedReader.readLine();
			}
		}
		catch (IOException ioException) {
		}

		String msg = sb.toString();

		if (priority != Project.MSG_ERR) {
			printMessage(msg, out, priority);
		}
		else {
			printMessage(msg, err, priority);
		}

		log(msg);
	}

}