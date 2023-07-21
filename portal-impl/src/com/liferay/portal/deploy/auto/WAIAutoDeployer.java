/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.deploy.auto;

import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.bridges.wai.WAIPortlet;

import java.io.File;

import java.util.Map;
import java.util.Properties;

/**
 * @author Jorge Ferrer
 * @author Connor McKay
 */
public class WAIAutoDeployer extends PortletAutoDeployer {

	@Override
	public void copyXmls(
			File srcFile, String displayName, PluginPackage pluginPackage)
		throws Exception {

		super.copyXmls(srcFile, displayName, pluginPackage);

		// The default context.xml file for Tomcat causes portlets to be run
		// from the temp directory, which prevents some applications from saving
		// their settings. There is no easy way to prevent this file from being
		// copied, so it must be deleted afterwards.

		FileUtil.delete(srcFile + "/META-INF/context.xml");

		String portletName = displayName;

		if (pluginPackage != null) {
			portletName = pluginPackage.getName();
		}

		Map<String, String> filterMap = HashMapBuilder.put(
			"portlet_name", displayName
		).put(
			"portlet_title", portletName
		).build();

		if (pluginPackage != null) {
			Properties deploymentSettings =
				pluginPackage.getDeploymentSettings();

			filterMap.put(
				"portlet_class",
				deploymentSettings.getProperty(
					"wai.portlet", WAIPortlet.class.getName()));

			filterMap.put(
				"friendly_url_mapper_class",
				deploymentSettings.getProperty(
					"wai.friendly.url.mapper",
					DefaultFriendlyURLMapper.class.getName()));

			filterMap.put(
				"friendly_url_mapping",
				deploymentSettings.getProperty(
					"wai.friendly.url.mapping", "waiapp"));

			filterMap.put(
				"friendly_url_routes",
				deploymentSettings.getProperty(
					"wai.friendly.url.routes",
					"com/liferay/util/bridges/wai" +
						"/wai-friendly-url-routes.xml"));
		}
		else {
			filterMap.put("portlet_class", WAIPortlet.class.getName());

			filterMap.put(
				"friendly_url_mapper_class",
				DefaultFriendlyURLMapper.class.getName());

			filterMap.put("friendly_url_mapping", "waiapp");

			filterMap.put(
				"friendly_url_routes",
				"com/liferay/util/bridges/wai/wai-friendly-url-routes.xml");
		}

		_setInitParams(filterMap, pluginPackage);

		copyDependencyXml(
			"liferay-display.xml", srcFile + "/WEB-INF", filterMap);
		copyDependencyXml(
			"liferay-portlet.xml", srcFile + "/WEB-INF", filterMap);
		copyDependencyXml("portlet.xml", srcFile + "/WEB-INF", filterMap);
		copyDependencyXml("iframe.jsp", srcFile + "/WEB-INF/jsp/liferay/wai");
	}

	private void _setInitParams(
		Map<String, String> filterMap, PluginPackage pluginPackage) {

		for (int i = 0; i < _INIT_PARAM_NAMES.length; i++) {
			String name = _INIT_PARAM_NAMES[i];

			String value = null;

			if (pluginPackage != null) {
				Properties deploymentSettings =
					pluginPackage.getDeploymentSettings();

				value = deploymentSettings.getProperty(name);
			}

			if (Validator.isNull(value)) {
				value = _INIT_PARAM_DEFAULT_VALUES[i];
			}

			filterMap.put("init_param_name_" + i, name);
			filterMap.put("init_param_value_" + i, value);
		}
	}

	private static final String[] _INIT_PARAM_DEFAULT_VALUES = {"500"};

	private static final String[] _INIT_PARAM_NAMES = {
		"wai.connector.iframe.height.default"
	};

}