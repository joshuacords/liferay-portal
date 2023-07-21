/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.deploy.auto;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.deploy.DeployUtil;
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.tools.deploy.WebDeployer;
import com.liferay.portal.util.PropsValues;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Ferrer
 */
public class WebAutoDeployer extends WebDeployer implements AutoDeployer {

	public WebAutoDeployer() {
		try {
			baseDir = PropsValues.AUTO_DEPLOY_DEPLOY_DIR;
			destDir = DeployUtil.getAutoDeployDestDir();
			appServerType = ServerDetector.getServerId();
			unpackWar = PropsValues.AUTO_DEPLOY_UNPACK_WAR;
			filePattern = StringPool.BLANK;
			jbossPrefix = PropsValues.AUTO_DEPLOY_JBOSS_PREFIX;
			tomcatLibDir = PropsValues.AUTO_DEPLOY_TOMCAT_LIB_DIR;
			wildflyPrefix = PropsValues.AUTO_DEPLOY_WILDFLY_PREFIX;

			List<String> jars = new ArrayList<>();

			addExtJar(jars, "ext-util-bridges.jar");
			addExtJar(jars, "ext-util-java.jar");
			addExtJar(jars, "ext-util-taglib.jar");
			addRequiredJar(jars, "util-bridges.jar");
			addRequiredJar(jars, "util-java.jar");
			addRequiredJar(jars, "util-taglib.jar");

			this.jars = jars;

			checkArguments();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WebAutoDeployer.class);

}