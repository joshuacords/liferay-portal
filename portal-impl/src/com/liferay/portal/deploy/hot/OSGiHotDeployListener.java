/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.deploy.hot;

import com.liferay.portal.kernel.deploy.hot.BaseHotDeployListener;
import com.liferay.portal.kernel.deploy.hot.HotDeployEvent;
import com.liferay.portal.kernel.deploy.hot.HotDeployException;
import com.liferay.portal.kernel.deploy.hot.HotDeployListener;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerList;

/**
 * @author Adolfo Pérez
 */
public class OSGiHotDeployListener extends BaseHotDeployListener {

	@Override
	public void invokeDeploy(HotDeployEvent hotDeployEvent)
		throws HotDeployException {

		for (HotDeployListener hotDeployListener : _serviceTrackerList) {
			hotDeployListener.invokeDeploy(hotDeployEvent);
		}
	}

	@Override
	public void invokeUndeploy(HotDeployEvent hotDeployEvent)
		throws HotDeployException {

		for (HotDeployListener hotDeployListener : _serviceTrackerList) {
			hotDeployListener.invokeUndeploy(hotDeployEvent);
		}
	}

	private final ServiceTrackerList<HotDeployListener> _serviceTrackerList =
		ServiceTrackerCollections.openList(HotDeployListener.class);

}