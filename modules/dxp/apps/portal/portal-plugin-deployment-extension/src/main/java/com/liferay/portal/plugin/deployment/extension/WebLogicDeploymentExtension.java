/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.plugin.deployment.extension;

import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.tools.deploy.BaseDeployer;
import com.liferay.portal.tools.deploy.extension.DeploymentExtension;

import java.io.File;

/**
 * @author Shuyang Zhou
 */
public class WebLogicDeploymentExtension implements DeploymentExtension {

	@Override
	public void copyXmls(BaseDeployer baseDeployer, File srcFile)
		throws Exception {

		baseDeployer.copyDependencyXml("weblogic.xml", srcFile + "/WEB-INF");
	}

	@Override
	public String getServerId() {
		return ServerDetector.WEBLOGIC_ID;
	}

	@Override
	public void postDeploy(String destDir, String deployDir) {
	}

}