/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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