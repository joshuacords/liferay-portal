/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.service;

import com.liferay.oauth.model.OAuthUser;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for OAuthUser. This utility wraps
 * <code>com.liferay.oauth.service.impl.OAuthUserServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Ivica Cardic
 * @see OAuthUserService
 * @generated
 */
public class OAuthUserServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.oauth.service.impl.OAuthUserServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static OAuthUser addOAuthUser(
			String consumerKey,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addOAuthUser(consumerKey, serviceContext);
	}

	public static OAuthUser deleteOAuthUser(long oAuthApplicationId)
		throws PortalException {

		return getService().deleteOAuthUser(oAuthApplicationId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static OAuthUserService getService() {
		return _service;
	}

	public static void setService(OAuthUserService service) {
		_service = service;
	}

	private static volatile OAuthUserService _service;

}