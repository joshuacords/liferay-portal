/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.deploy;

import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.plugin.PluginPackage;

import java.util.List;
import java.util.Properties;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public class DeployManagerUtil {

	public static void deploy(AutoDeploymentContext autoDeploymentContext)
		throws Exception {

		getDeployManager().deploy(autoDeploymentContext);
	}

	public static String getDeployDir() throws Exception {
		return getDeployManager().getDeployDir();
	}

	public static DeployManager getDeployManager() {
		return _deployManager;
	}

	public static String getInstalledDir() throws Exception {
		return getDeployManager().getInstalledDir();
	}

	public static PluginPackage getInstalledPluginPackage(String context) {
		return getDeployManager().getInstalledPluginPackage(context);
	}

	public static List<PluginPackage> getInstalledPluginPackages() {
		return getDeployManager().getInstalledPluginPackages();
	}

	public static List<String[]> getLevelsRequiredDeploymentContexts() {
		return getDeployManager().getLevelsRequiredDeploymentContexts();
	}

	public static List<String[]> getLevelsRequiredDeploymentWARFileNames() {
		return getDeployManager().getLevelsRequiredDeploymentWARFileNames();
	}

	public static boolean isDeployed(String context) {
		return getDeployManager().isDeployed(context);
	}

	public static boolean isRequiredDeploymentContext(String context) {
		return getDeployManager().isRequiredDeploymentContext(context);
	}

	public static PluginPackage readPluginPackageProperties(
		String displayName, Properties properties) {

		return getDeployManager().readPluginPackageProperties(
			displayName, properties);
	}

	public static PluginPackage readPluginPackageXml(String xml)
		throws Exception {

		return getDeployManager().readPluginPackageXml(xml);
	}

	public static void redeploy(String context) throws Exception {
		getDeployManager().redeploy(context);
	}

	public static void reset() {
		_deployManager = null;
	}

	public static void undeploy(String context) throws Exception {
		getDeployManager().undeploy(context);
	}

	public void setDeployManager(DeployManager deployManager) {
		_deployManager = deployManager;
	}

	private static DeployManager _deployManager;

}