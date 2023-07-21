/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.service.impl;

import com.liferay.oauth.model.OAuthApplication;
import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.service.base.OAuthUserServiceBaseImpl;
import com.liferay.oauth.service.permission.OAuthUserPermission;
import com.liferay.oauth.util.OAuthActionKeys;
import com.liferay.oauth.util.OAuthUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(
	property = {
		"json.web.service.context.name=oauth",
		"json.web.service.context.path=OAuthUser"
	},
	service = AopService.class
)
public class OAuthUserServiceImpl extends OAuthUserServiceBaseImpl {

	@Override
	public OAuthUser addOAuthUser(
			String consumerKey, ServiceContext serviceContext)
		throws PortalException {

		OAuthApplication oAuthApplication =
			oAuthApplicationPersistence.findByConsumerKey(consumerKey);

		User user = getUser();

		OAuthUser oAuthUser = oAuthUserPersistence.fetchByU_OAI(
			user.getUserId(), oAuthApplication.getOAuthApplicationId());

		if (oAuthUser != null) {
			return oAuthUser;
		}

		String accessToken = OAuthUtil.randomizeToken(
			oAuthApplication.getConsumerKey());

		String accessSecret = OAuthUtil.randomizeToken(
			consumerKey.concat(accessToken));

		return oAuthUserLocalService.addOAuthUser(
			user.getUserId(), oAuthApplication.getOAuthApplicationId(),
			accessToken, accessSecret, serviceContext);
	}

	@Override
	public OAuthUser deleteOAuthUser(long oAuthApplicationId)
		throws PortalException {

		OAuthUser oAuthUser = oAuthUserPersistence.findByU_OAI(
			getUserId(), oAuthApplicationId);

		OAuthUserPermission.check(
			getPermissionChecker(), oAuthUser, OAuthActionKeys.DELETE);

		return oAuthUserLocalService.deleteOAuthUser(
			getUserId(), oAuthApplicationId);
	}

}