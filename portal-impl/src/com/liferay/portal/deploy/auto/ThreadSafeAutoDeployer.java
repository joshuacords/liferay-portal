/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.deploy.auto;

import com.liferay.portal.kernel.deploy.auto.AutoDeployException;
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;

import java.io.IOException;

/**
 * @author Brian Wing Shun Chan
 */
public class ThreadSafeAutoDeployer implements AutoDeployer {

	public ThreadSafeAutoDeployer(AutoDeployer autoDeployer) {
		_autoDeployer = autoDeployer;
	}

	@Override
	public int autoDeploy(AutoDeploymentContext autoDeploymentContext)
		throws AutoDeployException {

		try (AutoDeployer cloneAutoDeployer =
				_autoDeployer.cloneAutoDeployer()) {

			return cloneAutoDeployer.autoDeploy(autoDeploymentContext);
		}
		catch (IOException ioException) {
			throw new AutoDeployException(ioException);
		}
	}

	@Override
	public AutoDeployer cloneAutoDeployer() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void close() throws IOException {
		_autoDeployer.close();
	}

	private final AutoDeployer _autoDeployer;

}