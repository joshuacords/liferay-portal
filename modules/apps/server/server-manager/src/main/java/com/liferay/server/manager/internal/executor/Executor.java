/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.server.manager.internal.executor;

import com.liferay.portal.kernel.json.JSONObject;

import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public interface Executor {

	public void executeCreate(
			HttpServletRequest httpServletRequest,
			JSONObject responseJSONObject, Queue<String> arguments)
		throws Exception;

	public void executeDelete(
			HttpServletRequest httpServletRequest,
			JSONObject responseJSONObject, Queue<String> arguments)
		throws Exception;

	public void executeRead(
			HttpServletRequest httpServletRequest,
			JSONObject responseJSONObject, Queue<String> arguments)
		throws Exception;

	public void executeUpdate(
			HttpServletRequest httpServletRequest,
			JSONObject responseJSONObject, Queue<String> arguments)
		throws Exception;

}