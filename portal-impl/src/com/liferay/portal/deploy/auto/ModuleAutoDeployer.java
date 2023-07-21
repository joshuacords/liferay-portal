/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.deploy.auto;

import com.liferay.portal.kernel.deploy.auto.AutoDeployException;
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.tools.deploy.BaseDeployer;
import com.liferay.portal.util.PropsUtil;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * @author Miguel Pastor
 * @author Gregory Amerson
 */
public class ModuleAutoDeployer extends BaseDeployer {

	@Override
	public int deployFile(AutoDeploymentContext autoDeploymentContext)
		throws Exception {

		String[] moduleFrameworkAutoDeployDirs = PropsUtil.getArray(
			PropsKeys.MODULE_FRAMEWORK_AUTO_DEPLOY_DIRS);

		String destDir = null;

		for (String moduleFrameworkAutoDeployDir :
				moduleFrameworkAutoDeployDirs) {

			if (moduleFrameworkAutoDeployDir.endsWith("modules")) {
				destDir = moduleFrameworkAutoDeployDir;
			}
		}

		FileUtil.mkdirs(destDir);

		try {
			FileUtils.copyFileToDirectory(
				autoDeploymentContext.getFile(), new File(destDir));
		}
		catch (IOException ioException) {
			throw new AutoDeployException(ioException);
		}

		return AutoDeployer.CODE_DEFAULT;
	}

}