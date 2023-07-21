/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.persistence.internal.upgrade;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.configuration.persistence.upgrade.ConfigurationUpgradeStepFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.upgrade.UpgradeStep;
import com.liferay.portal.util.PropsValues;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;

import java.util.Dictionary;

import org.apache.felix.cm.PersistenceManager;

/**
 * @author Preston Crary
 */
public class ConfigurationUpgradeStepFactoryImpl
	implements ConfigurationUpgradeStepFactory {

	public ConfigurationUpgradeStepFactoryImpl(
		PersistenceManager persistenceManager) {

		_persistenceManager = persistenceManager;
	}

	@Override
	public UpgradeStep createUpgradeStep(String oldPid, String newPid) {
		return dbProcessContext -> {
			try {
				if (_persistenceManager.exists(oldPid)) {
					Dictionary<String, String> dictionary =
						_persistenceManager.load(oldPid);

					dictionary.remove("service.pid");

					dictionary.put("service.pid", newPid);

					_persistenceManager.store(newPid, dictionary);

					_persistenceManager.delete(oldPid);
				}

				_renameConfigurationFile(oldPid, newPid, "cfg");
				_renameConfigurationFile(oldPid, newPid, "config");
			}
			catch (IOException ioException) {
				throw new UpgradeException(ioException);
			}
		};
	}

	private void _renameConfigurationFile(
			String oldPid, String newPid, String extension)
		throws IOException {

		File oldConfigFile = new File(
			StringBundler.concat(
				PropsValues.MODULE_FRAMEWORK_CONFIGS_DIR, "/", oldPid, ".",
				extension));

		if (!oldConfigFile.exists()) {
			return;
		}

		File newConfigFile = new File(
			StringBundler.concat(
				PropsValues.MODULE_FRAMEWORK_CONFIGS_DIR, "/", newPid, ".",
				extension));

		if (newConfigFile.exists()) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					StringBundler.concat(
						"Unable to rename ", oldConfigFile.getAbsolutePath(),
						" to ", newConfigFile.getAbsolutePath(),
						" because the file already exists"));
			}

			return;
		}

		Files.move(oldConfigFile.toPath(), newConfigFile.toPath());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ConfigurationUpgradeStepFactoryImpl.class);

	private final PersistenceManager _persistenceManager;

}