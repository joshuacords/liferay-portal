/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.server.manager.internal.executor;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.server.manager.internal.constants.JSONKeys;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cindy Li
 */
@Component(
	immediate = true,
	property = "server.manager.executor.path=/server/freemarker",
	service = Executor.class
)
public class FreeMarkerExecutor extends BaseExecutor {

	@Override
	public void executeRead(
			HttpServletRequest httpServletRequest,
			JSONObject responseJSONObject, Queue<String> arguments)
		throws Exception {

		ExecutorPathResolver executorPathResolver = new ExecutorPathResolver(
			_executorServiceRegistry.getAvailableExecutorPaths());

		List<String> paths = executorPathResolver.getNextExecutorsPaths(_path);

		responseJSONObject.put(
			JSONKeys.OUTPUT,
			"Valid paths are " + StringUtil.merge(paths, ", "));
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_path = MapUtil.getString(properties, "server.manager.executor.path");
	}

	@Reference
	private ExecutorServiceRegistry _executorServiceRegistry;

	private String _path;

}