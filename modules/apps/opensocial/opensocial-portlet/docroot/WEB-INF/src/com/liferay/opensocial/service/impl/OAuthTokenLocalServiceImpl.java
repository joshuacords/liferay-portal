/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.service.impl;

import com.liferay.opensocial.model.OAuthToken;
import com.liferay.opensocial.service.base.OAuthTokenLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Dennis Ju
 */
public class OAuthTokenLocalServiceImpl extends OAuthTokenLocalServiceBaseImpl {

	@Override
	public OAuthToken addOAuthToken(
			long userId, String gadgetKey, String serviceName, long moduleId,
			String accessToken, String tokenName, String tokenSecret,
			String sessionHandle, long expiration)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		long oAuthTokenId = counterLocalService.increment();

		OAuthToken oAuthToken = oAuthTokenPersistence.create(oAuthTokenId);

		oAuthToken.setCompanyId(user.getCompanyId());
		oAuthToken.setUserId(user.getUserId());
		oAuthToken.setUserName(user.getFullName());
		oAuthToken.setCreateDate(now);
		oAuthToken.setModifiedDate(now);
		oAuthToken.setGadgetKey(gadgetKey);
		oAuthToken.setServiceName(serviceName);
		oAuthToken.setModuleId(moduleId);
		oAuthToken.setAccessToken(accessToken);
		oAuthToken.setTokenName(tokenName);
		oAuthToken.setTokenSecret(tokenSecret);
		oAuthToken.setSessionHandle(sessionHandle);
		oAuthToken.setExpiration(expiration);

		return oAuthTokenPersistence.update(oAuthToken);
	}

	@Override
	public void deleteOAuthToken(
			long userId, String gadgetKey, String serviceName, long moduleId,
			String tokenName)
		throws PortalException {

		oAuthTokenPersistence.removeByU_G_S_M_T(
			userId, gadgetKey, serviceName, moduleId, tokenName);
	}

	@Override
	public void deleteOAuthTokens(String gadgetKey, String serviceName) {
		oAuthTokenPersistence.removeByG_S(gadgetKey, serviceName);
	}

	@Override
	public OAuthToken fetchOAuthToken(
		long userId, String gadgetKey, String serviceName, long moduleId,
		String tokenName) {

		return oAuthTokenPersistence.fetchByU_G_S_M_T(
			userId, gadgetKey, serviceName, moduleId, tokenName);
	}

	@Override
	public OAuthToken getOAuthToken(
			long userId, String gadgetKey, String serviceName, long moduleId,
			String tokenName)
		throws PortalException {

		return oAuthTokenPersistence.findByU_G_S_M_T(
			userId, gadgetKey, serviceName, moduleId, tokenName);
	}

	@Override
	public List<OAuthToken> getOAuthTokens(
		String gadgetKey, String serviceName) {

		return oAuthTokenPersistence.findByG_S(gadgetKey, serviceName);
	}

}