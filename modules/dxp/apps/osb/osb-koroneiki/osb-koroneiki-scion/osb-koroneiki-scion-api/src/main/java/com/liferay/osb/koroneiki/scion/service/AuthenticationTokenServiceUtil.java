/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service;

import com.liferay.osb.koroneiki.scion.model.AuthenticationToken;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for AuthenticationToken. This utility wraps
 * <code>com.liferay.osb.koroneiki.scion.service.impl.AuthenticationTokenServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AuthenticationTokenService
 * @generated
 */
public class AuthenticationTokenServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.scion.service.impl.AuthenticationTokenServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static AuthenticationToken addAuthenticationToken(
			long serviceProducerId, String name, String token)
		throws PortalException {

		return getService().addAuthenticationToken(
			serviceProducerId, name, token);
	}

	public static AuthenticationToken deleteAuthenticationToken(
			long authenticationTokenId)
		throws PortalException {

		return getService().deleteAuthenticationToken(authenticationTokenId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static AuthenticationToken updateAuthenticationToken(
			long authenticationTokenId, String name)
		throws PortalException {

		return getService().updateAuthenticationToken(
			authenticationTokenId, name);
	}

	public static AuthenticationToken updateStatus(
			long authenticationTokenId, int status)
		throws PortalException {

		return getService().updateStatus(authenticationTokenId, status);
	}

	public static AuthenticationTokenService getService() {
		return _service;
	}

	public static void setService(AuthenticationTokenService service) {
		_service = service;
	}

	private static volatile AuthenticationTokenService _service;

}