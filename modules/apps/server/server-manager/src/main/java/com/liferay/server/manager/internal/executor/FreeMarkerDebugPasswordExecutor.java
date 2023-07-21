/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.server.manager.internal.executor;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.server.manager.internal.constants.JSONKeys;

import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Cindy Li
 */
@Component(
	immediate = true,
	property = "server.manager.executor.path=/server/freemarker/debug-password",
	service = Executor.class
)
public class FreeMarkerDebugPasswordExecutor extends BaseExecutor {

	@Override
	public void executeRead(
		HttpServletRequest httpServletRequest, JSONObject responseJSONObject,
		Queue<String> arguments) {

		String freeMarkerDebugPassword = System.getProperty(
			"freemarker.debug.password");

		if (freeMarkerDebugPassword == null) {
			responseJSONObject.put(
				JSONKeys.ERROR, "FreeMarker debugger is not enabled"
			).put(
				JSONKeys.STATUS, 1
			);
		}
		else {
			responseJSONObject.put(JSONKeys.OUTPUT, freeMarkerDebugPassword);
		}
	}

}