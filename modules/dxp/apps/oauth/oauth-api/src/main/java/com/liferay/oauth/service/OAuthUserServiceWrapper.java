/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OAuthUserService}.
 *
 * @author Ivica Cardic
 * @see OAuthUserService
 * @generated
 */
public class OAuthUserServiceWrapper
	implements OAuthUserService, ServiceWrapper<OAuthUserService> {

	public OAuthUserServiceWrapper(OAuthUserService oAuthUserService) {
		_oAuthUserService = oAuthUserService;
	}

	@Override
	public com.liferay.oauth.model.OAuthUser addOAuthUser(
			String consumerKey,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _oAuthUserService.addOAuthUser(consumerKey, serviceContext);
	}

	@Override
	public com.liferay.oauth.model.OAuthUser deleteOAuthUser(
			long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _oAuthUserService.deleteOAuthUser(oAuthApplicationId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _oAuthUserService.getOSGiServiceIdentifier();
	}

	@Override
	public OAuthUserService getWrappedService() {
		return _oAuthUserService;
	}

	@Override
	public void setWrappedService(OAuthUserService oAuthUserService) {
		_oAuthUserService = oAuthUserService;
	}

	private OAuthUserService _oAuthUserService;

}