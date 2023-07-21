/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.server.manager.internal.executor;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.server.manager.internal.constants.JSONKeys;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = "server.manager.executor.path=/server/debug-port",
	service = Executor.class
)
public class DebugPortExecutor extends BaseExecutor {

	@Override
	public void executeRead(
		HttpServletRequest httpServletRequest, JSONObject responseJSONObject,
		Queue<String> arguments) {

		String debugPort = getDebugPort();

		if (debugPort == null) {
			responseJSONObject.put(
				JSONKeys.ERROR, "Server was not started in debug mode"
			).put(
				JSONKeys.STATUS, 1
			);
		}
		else {
			responseJSONObject.put(JSONKeys.OUTPUT, debugPort);
		}
	}

	protected String getDebugPort() {
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

		List<String> inputArguments = runtimeMXBean.getInputArguments();

		if (inputArguments == null) {
			return null;
		}

		for (String inputArgument : inputArguments) {
			if (!inputArgument.contains("transport=dt_socket")) {
				continue;
			}

			Matcher matcher = _pattern.matcher(inputArgument);

			if (matcher.find()) {
				return matcher.group(1);
			}
		}

		return null;
	}

	private static final Pattern _pattern = Pattern.compile("address=(\\d+)");

}