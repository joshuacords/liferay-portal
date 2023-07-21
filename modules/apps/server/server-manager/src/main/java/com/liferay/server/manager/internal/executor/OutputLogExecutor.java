/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.server.manager.internal.executor;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.FastDateFormatFactory;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.server.manager.internal.constants.JSONKeys;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.text.Format;

import java.util.Date;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = "server.manager.executor.path=/server/log/output",
	service = Executor.class
)
public class OutputLogExecutor extends BaseExecutor {

	@Override
	public void executeRead(
			HttpServletRequest httpServletRequest,
			JSONObject responseJSONObject, Queue<String> arguments)
		throws Exception {

		File logFile = getLogFile();

		if ((logFile == null) || !logFile.exists()) {
			return;
		}

		InputStream inputStream = new FileInputStream(logFile);

		int offset = GetterUtil.getInteger(arguments.poll());

		inputStream.skip(offset);

		OutputStream outputStream = new ByteArrayOutputStream();

		StreamUtil.transfer(inputStream, outputStream);

		responseJSONObject.put(JSONKeys.OUTPUT, outputStream.toString());
	}

	@Activate
	protected void activate() {
		_simpleDateFormat = _fastDateFormatFactory.getSimpleDateFormat(
			"yyyy-MM-dd");
	}

	protected String getLiferayDateString() {
		return getTomcatDateString();
	}

	protected File getLogFile() {
		File logFile = null;

		if (ServerDetector.isJBoss()) {
			File logDirectory = new File(
				System.getProperty("jboss.server.log.dir"));

			logFile = new File(logDirectory, "server.log");
		}
		else {
			logFile = new File(
				StringBundler.concat(
					PropsUtil.get(PropsKeys.LIFERAY_HOME), "/logs/liferay.",
					getLiferayDateString(), ".log"));
		}

		return logFile;
	}

	protected String getTomcatDateString() {
		Date date = new Date();

		return _simpleDateFormat.format(date);
	}

	@Reference
	private FastDateFormatFactory _fastDateFormatFactory;

	private Format _simpleDateFormat;

}