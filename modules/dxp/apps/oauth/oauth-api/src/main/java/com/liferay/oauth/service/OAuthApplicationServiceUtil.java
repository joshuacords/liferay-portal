/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.service;

import com.liferay.oauth.model.OAuthApplication;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.InputStream;

/**
 * Provides the remote service utility for OAuthApplication. This utility wraps
 * <code>com.liferay.oauth.service.impl.OAuthApplicationServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Ivica Cardic
 * @see OAuthApplicationService
 * @generated
 */
public class OAuthApplicationServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.oauth.service.impl.OAuthApplicationServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static OAuthApplication addOAuthApplication(
			String name, String description, int accessLevel,
			boolean shareableAccessToken, String callbackURI, String websiteURL,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addOAuthApplication(
			name, description, accessLevel, shareableAccessToken, callbackURI,
			websiteURL, serviceContext);
	}

	public static void deleteLogo(long oAuthApplicationId)
		throws PortalException {

		getService().deleteLogo(oAuthApplicationId);
	}

	public static OAuthApplication deleteOAuthApplication(
			long oAuthApplicationId)
		throws PortalException {

		return getService().deleteOAuthApplication(oAuthApplicationId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static OAuthApplication updateLogo(
			long oAuthApplicationId, InputStream inputStream)
		throws PortalException {

		return getService().updateLogo(oAuthApplicationId, inputStream);
	}

	public static OAuthApplication updateOAuthApplication(
			long oAuthApplicationId, String name, String description,
			boolean shareableAccessToken, String callbackURI, String websiteURL,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateOAuthApplication(
			oAuthApplicationId, name, description, shareableAccessToken,
			callbackURI, websiteURL, serviceContext);
	}

	public static OAuthApplicationService getService() {
		return _service;
	}

	public static void setService(OAuthApplicationService service) {
		_service = service;
	}

	private static volatile OAuthApplicationService _service;

}