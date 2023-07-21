/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.server.manager.internal.executor;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.ServerDetector;

import java.io.File;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = "server.manager.executor.path=/server/log/error",
	service = Executor.class
)
public class ErrorLogExecutor extends OutputLogExecutor {

	@Override
	protected File getLogFile() {
		File logFile = null;

		if (ServerDetector.isJBoss()) {
			File logDirectory = new File(
				System.getProperty("jboss.server.log.dir"));

			logFile = new File(logDirectory, "boot.log");
		}
		else if (ServerDetector.isTomcat()) {
			logFile = new File(
				StringBundler.concat(
					System.getProperty("catalina.base"), "/logs/catalina.",
					getTomcatDateString(), ".log"));
		}

		return logFile;
	}

}