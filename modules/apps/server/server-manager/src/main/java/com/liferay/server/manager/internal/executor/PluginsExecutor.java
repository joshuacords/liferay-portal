/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.server.manager.internal.executor;

import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.server.manager.internal.constants.JSONKeys;

import java.util.List;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true, property = "server.manager.executor.path=/plugins",
	service = Executor.class
)
public class PluginsExecutor extends BaseExecutor {

	@Override
	public void executeCreate(
			HttpServletRequest httpServletRequest,
			JSONObject responseJSONObject, Queue<String> arguments)
		throws Exception {

		_pluginExecutor.executeCreate(
			httpServletRequest, responseJSONObject, arguments);
	}

	@Override
	public void executeRead(
		HttpServletRequest httpServletRequest, JSONObject responseJSONObject,
		Queue<String> arguments) {

		JSONArray pluginPackagesJSONArray = JSONFactoryUtil.createJSONArray();

		List<PluginPackage> pluginPackages =
			DeployManagerUtil.getInstalledPluginPackages();

		for (PluginPackage pluginPackage : pluginPackages) {
			pluginPackagesJSONArray.put(pluginPackage.getContext());
		}

		responseJSONObject.put(JSONKeys.OUTPUT, pluginPackagesJSONArray);
	}

	private final Executor _pluginExecutor = new PluginExecutor();

}