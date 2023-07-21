/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.deploy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;

import javax.enterprise.deploy.shared.ModuleType;
import javax.enterprise.deploy.spi.DeploymentManager;
import javax.enterprise.deploy.spi.TargetModuleID;
import javax.enterprise.deploy.spi.status.DeploymentStatus;
import javax.enterprise.deploy.spi.status.ProgressEvent;
import javax.enterprise.deploy.spi.status.ProgressListener;
import javax.enterprise.deploy.spi.status.ProgressObject;

/**
 * @author Brian Wing Shun Chan
 * @author Sandeep Soni
 */
public class DeploymentProgressListener implements ProgressListener {

	public DeploymentProgressListener(
		DeploymentHandler deploymentHandler, String warContext) {

		_deploymentHandler = deploymentHandler;
		_warContext = warContext;

		_deploymentManager = _deploymentHandler.getDeploymentManager();
	}

	@Override
	public void handleProgressEvent(ProgressEvent progressEvent) {
		DeploymentStatus deploymentStatus = progressEvent.getDeploymentStatus();

		if (_log.isInfoEnabled()) {
			_log.info(deploymentStatus.getMessage());
		}

		if (deploymentStatus.isCompleted()) {
			try {
				TargetModuleID[] targetModuleIDs =
					_deploymentManager.getNonRunningModules(
						ModuleType.WAR, _deploymentManager.getTargets());

				if (ArrayUtil.isNotEmpty(targetModuleIDs)) {
					for (TargetModuleID targetModuleID : targetModuleIDs) {
						if (!_warContext.equals(targetModuleID.getModuleID())) {
							continue;
						}

						ProgressObject startProgress = _deploymentManager.start(
							new TargetModuleID[] {targetModuleID});

						startProgress.addProgressListener(
							new StartProgressListener(_deploymentHandler));

						_deploymentHandler.setError(false);
						_deploymentHandler.setStarted(true);

						break;
					}
				}
				else {
					targetModuleIDs = _deploymentManager.getAvailableModules(
						ModuleType.WAR, _deploymentManager.getTargets());

					for (TargetModuleID targetModuleID : targetModuleIDs) {
						if (!_warContext.equals(targetModuleID.getModuleID())) {
							continue;
						}

						ProgressObject startProgress = _deploymentManager.start(
							new TargetModuleID[] {targetModuleID});

						startProgress.addProgressListener(
							new StartProgressListener(_deploymentHandler));

						_deploymentHandler.setError(false);
						_deploymentHandler.setStarted(true);

						break;
					}
				}
			}
			catch (Exception exception) {
				_log.error(exception, exception);

				_deploymentHandler.setError(true);
				_deploymentHandler.setStarted(false);
			}
		}
		else if (deploymentStatus.isFailed()) {
			_deploymentHandler.setError(true);
			_deploymentHandler.setStarted(false);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DeploymentProgressListener.class);

	private final DeploymentHandler _deploymentHandler;
	private final DeploymentManager _deploymentManager;
	private final String _warContext;

}