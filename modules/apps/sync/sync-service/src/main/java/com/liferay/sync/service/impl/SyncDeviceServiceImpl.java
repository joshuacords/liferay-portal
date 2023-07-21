/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.sync.constants.SyncDeviceConstants;
import com.liferay.sync.internal.util.SyncDeviceThreadLocal;
import com.liferay.sync.model.SyncDevice;
import com.liferay.sync.service.base.SyncDeviceServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(
	property = {
		"json.web.service.context.name=sync",
		"json.web.service.context.path=SyncDevice"
	},
	service = AopService.class
)
public class SyncDeviceServiceImpl extends SyncDeviceServiceBaseImpl {

	@Override
	public SyncDevice registerSyncDevice(
			String type, long buildNumber, int featureSet, String uuid)
		throws PortalException {

		User user = getUser();

		SyncDevice syncDevice =
			syncDeviceLocalService.fetchSyncDeviceByUuidAndCompanyId(
				uuid, user.getCompanyId());

		if (syncDevice == null) {
			syncDevice = SyncDeviceThreadLocal.getSyncDevice();

			return syncDeviceLocalService.addSyncDevice(
				user.getUserId(), type, buildNumber, syncDevice.getHostname(),
				featureSet);
		}

		if (syncDevice.getUserId() != user.getUserId()) {
			throw new PrincipalException();
		}

		return syncDeviceLocalService.updateSyncDevice(
			syncDevice.getSyncDeviceId(), type, buildNumber, featureSet,
			syncDevice.getHostname(), syncDevice.getStatus());
	}

	@Override
	public void unregisterSyncDevice(String uuid) throws PortalException {
		User user = getUser();

		SyncDevice syncDevice =
			syncDeviceLocalService.fetchSyncDeviceByUuidAndCompanyId(
				uuid, user.getCompanyId());

		if ((syncDevice == null) ||
			(syncDevice.getUserId() != user.getUserId()) ||
			(syncDevice.getStatus() == SyncDeviceConstants.STATUS_WIPED)) {

			return;
		}

		syncDeviceLocalService.deleteSyncDevice(syncDevice);
	}

}