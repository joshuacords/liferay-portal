/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.deploy.auto;

import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 */
public interface AutoDeployer extends Closeable {

	public static final int CODE_DEFAULT = 1;

	public static final int CODE_NOT_APPLICABLE = 0;

	public static final int CODE_SKIP_NEWER_VERSION = 2;

	public int autoDeploy(AutoDeploymentContext autoDeploymentContext)
		throws AutoDeployException;

	public AutoDeployer cloneAutoDeployer() throws AutoDeployException;

	@Override
	public default void close() throws IOException {
	}

}