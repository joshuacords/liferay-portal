/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.deploy.auto;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.deploy.DeployUtil;
import com.liferay.portal.kernel.deploy.auto.AutoDeployException;
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.tools.deploy.ThemeDeployer;
import com.liferay.portal.util.PropsValues;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 */
public class ThemeAutoDeployer extends ThemeDeployer implements AutoDeployer {

	public ThemeAutoDeployer() {
		try {
			baseDir = PropsValues.AUTO_DEPLOY_DEPLOY_DIR;
			destDir = DeployUtil.getAutoDeployDestDir();
			appServerType = ServerDetector.getServerId();
			themeTaglibDTD = DeployUtil.getResourcePath(
				tempDirPaths, "liferay-theme.tld");
			utilTaglibDTD = DeployUtil.getResourcePath(
				tempDirPaths, "liferay-util.tld");
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

	@Override
	public int autoDeploy(AutoDeploymentContext autoDeploymentContext)
		throws AutoDeployException {

		File file = autoDeploymentContext.getFile();

		if (file.isDirectory()) {
			try {
				if (_log.isInfoEnabled()) {
					_log.info("Modifying themes for " + file.getPath());
				}

				deployDirectory(
					file, autoDeploymentContext.getContext(), false,
					autoDeploymentContext.getPluginPackage());

				if (_log.isInfoEnabled()) {
					_log.info(
						"Themes for " + file.getPath() +
							" modified successfully");
				}

				return AutoDeployer.CODE_DEFAULT;
			}
			catch (Exception exception) {
				throw new AutoDeployException(exception);
			}
		}
		else {
			return super.autoDeploy(autoDeploymentContext);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ThemeAutoDeployer.class);

}