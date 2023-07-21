/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AuthenticationTokenService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuthenticationTokenService
 * @generated
 */
public class AuthenticationTokenServiceWrapper
	implements AuthenticationTokenService,
			   ServiceWrapper<AuthenticationTokenService> {

	public AuthenticationTokenServiceWrapper(
		AuthenticationTokenService authenticationTokenService) {

		_authenticationTokenService = authenticationTokenService;
	}

	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
			addAuthenticationToken(
				long serviceProducerId, String name, String token)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authenticationTokenService.addAuthenticationToken(
			serviceProducerId, name, token);
	}

	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
			deleteAuthenticationToken(long authenticationTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authenticationTokenService.deleteAuthenticationToken(
			authenticationTokenId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _authenticationTokenService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
			updateAuthenticationToken(long authenticationTokenId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authenticationTokenService.updateAuthenticationToken(
			authenticationTokenId, name);
	}

	@Override
	public com.liferay.osb.koroneiki.scion.model.AuthenticationToken
			updateStatus(long authenticationTokenId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _authenticationTokenService.updateStatus(
			authenticationTokenId, status);
	}

	@Override
	public AuthenticationTokenService getWrappedService() {
		return _authenticationTokenService;
	}

	@Override
	public void setWrappedService(
		AuthenticationTokenService authenticationTokenService) {

		_authenticationTokenService = authenticationTokenService;
	}

	private AuthenticationTokenService _authenticationTokenService;

}