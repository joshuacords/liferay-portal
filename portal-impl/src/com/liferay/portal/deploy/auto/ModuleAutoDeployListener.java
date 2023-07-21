/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.deploy.auto;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.deploy.auto.AutoDeployException;
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.deploy.auto.BaseAutoDeployListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

/**
 * @author Miguel Pastor
 * @author Manuel de la Peña
 */
public class ModuleAutoDeployListener extends BaseAutoDeployListener {

	@Override
	protected AutoDeployer buildAutoDeployer() {
		return new ThreadSafeAutoDeployer(new ModuleAutoDeployer());
	}

	@Override
	protected String getPluginPathInfoMessage(File file) {
		return "Copied module for " + file.getPath();
	}

	@Override
	protected String getSuccessMessage(File file) {
		return "Module for " + file.getPath() + " copied successfully";
	}

	@Override
	protected boolean isDeployable(File file) throws AutoDeployException {
		return isModule(file);
	}

	protected boolean isModule(File file) throws AutoDeployException {
		PluginAutoDeployListenerHelper pluginAutoDeployListenerHelper =
			new PluginAutoDeployListenerHelper(file);

		if (!pluginAutoDeployListenerHelper.isJarFile()) {
			return false;
		}

		Manifest manifest = null;

		try (JarInputStream jarInputStream = new JarInputStream(
				new FileInputStream(file))) {

			manifest = jarInputStream.getManifest();
		}
		catch (IOException ioException) {
			throw new AutoDeployException(ioException);
		}

		if (manifest == null) {
			return false;
		}

		Attributes attributes = manifest.getMainAttributes();

		String bundleSymbolicName = attributes.getValue("Bundle-SymbolicName");

		if (bundleSymbolicName == null) {
			return false;
		}

		int index = bundleSymbolicName.indexOf(CharPool.SEMICOLON);

		if (index != -1) {
			bundleSymbolicName = bundleSymbolicName.substring(0, index);
		}

		return !bundleSymbolicName.isEmpty();
	}

}