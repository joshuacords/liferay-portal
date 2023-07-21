/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.service.impl;

import com.liferay.oauth.model.OAuthApplication;
import com.liferay.oauth.service.base.OAuthApplicationServiceBaseImpl;
import com.liferay.oauth.service.permission.OAuthApplicationPermission;
import com.liferay.oauth.service.permission.OAuthPermission;
import com.liferay.oauth.util.OAuthActionKeys;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.InputStream;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(
	property = {
		"json.web.service.context.name=oauth",
		"json.web.service.context.path=OAuthApplication"
	},
	service = AopService.class
)
public class OAuthApplicationServiceImpl
	extends OAuthApplicationServiceBaseImpl {

	@Override
	public OAuthApplication addOAuthApplication(
			String name, String description, int accessLevel,
			boolean shareableAccessToken, String callbackURI, String websiteURL,
			ServiceContext serviceContext)
		throws PortalException {

		OAuthPermission.check(
			getPermissionChecker(), OAuthActionKeys.ADD_APPLICATION);

		return oAuthApplicationLocalService.addOAuthApplication(
			getUserId(), name, description, accessLevel, shareableAccessToken,
			callbackURI, websiteURL, serviceContext);
	}

	@Override
	public void deleteLogo(long oAuthApplicationId) throws PortalException {
		OAuthApplicationPermission.check(
			getPermissionChecker(), oAuthApplicationId, OAuthActionKeys.UPDATE);

		oAuthApplicationLocalService.deleteLogo(oAuthApplicationId);
	}

	@Override
	public OAuthApplication deleteOAuthApplication(long oAuthApplicationId)
		throws PortalException {

		OAuthApplication oAuthApplication =
			oAuthApplicationPersistence.findByPrimaryKey(oAuthApplicationId);

		OAuthApplicationPermission.check(
			getPermissionChecker(), oAuthApplication, OAuthActionKeys.DELETE);

		return oAuthApplicationLocalService.deleteOAuthApplication(
			oAuthApplication);
	}

	@Override
	public OAuthApplication updateLogo(
			long oAuthApplicationId, InputStream inputStream)
		throws PortalException {

		OAuthApplicationPermission.check(
			getPermissionChecker(), oAuthApplicationId, OAuthActionKeys.UPDATE);

		return oAuthApplicationLocalService.updateLogo(
			oAuthApplicationId, inputStream);
	}

	@Override
	public OAuthApplication updateOAuthApplication(
			long oAuthApplicationId, String name, String description,
			boolean shareableAccessToken, String callbackURI, String websiteURL,
			ServiceContext serviceContext)
		throws PortalException {

		OAuthApplicationPermission.check(
			getPermissionChecker(), oAuthApplicationId, OAuthActionKeys.UPDATE);

		return oAuthApplicationLocalService.updateOAuthApplication(
			oAuthApplicationId, name, description, shareableAccessToken,
			callbackURI, websiteURL, serviceContext);
	}

}