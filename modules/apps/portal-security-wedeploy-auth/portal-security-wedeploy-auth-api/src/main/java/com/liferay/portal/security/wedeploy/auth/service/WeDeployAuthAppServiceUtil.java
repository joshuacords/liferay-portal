/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.wedeploy.auth.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp;

/**
 * Provides the remote service utility for WeDeployAuthApp. This utility wraps
 * <code>com.liferay.portal.security.wedeploy.auth.service.impl.WeDeployAuthAppServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Supritha Sundaram
 * @see WeDeployAuthAppService
 * @generated
 */
public class WeDeployAuthAppServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.security.wedeploy.auth.service.impl.WeDeployAuthAppServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static WeDeployAuthApp addWeDeployAuthApp(
			String name, String redirectURI,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addWeDeployAuthApp(
			name, redirectURI, serviceContext);
	}

	public static WeDeployAuthApp deleteWeDeployAuthApp(long weDeployAuthAppId)
		throws PortalException {

		return getService().deleteWeDeployAuthApp(weDeployAuthAppId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static WeDeployAuthAppService getService() {
		return _service;
	}

	public static void setService(WeDeployAuthAppService service) {
		_service = service;
	}

	private static volatile WeDeployAuthAppService _service;

}