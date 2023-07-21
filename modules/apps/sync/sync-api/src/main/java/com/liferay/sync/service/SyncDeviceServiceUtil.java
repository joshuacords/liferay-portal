/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.sync.model.SyncDevice;

/**
 * Provides the remote service utility for SyncDevice. This utility wraps
 * <code>com.liferay.sync.service.impl.SyncDeviceServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDeviceService
 * @generated
 */
public class SyncDeviceServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.sync.service.impl.SyncDeviceServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static SyncDevice registerSyncDevice(
			String type, long buildNumber, int featureSet, String uuid)
		throws PortalException {

		return getService().registerSyncDevice(
			type, buildNumber, featureSet, uuid);
	}

	public static void unregisterSyncDevice(String uuid)
		throws PortalException {

		getService().unregisterSyncDevice(uuid);
	}

	public static SyncDeviceService getService() {
		return _service;
	}

	public static void setService(SyncDeviceService service) {
		_service = service;
	}

	private static volatile SyncDeviceService _service;

}