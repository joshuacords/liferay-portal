/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.wedeploy.auth.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp;
import com.liferay.portal.security.wedeploy.auth.service.base.WeDeployAuthAppLocalServiceBaseImpl;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * @author Supritha Sundaram
 */
@Component(
	property = "model.class.name=com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp",
	service = AopService.class
)
public class WeDeployAuthAppLocalServiceImpl
	extends WeDeployAuthAppLocalServiceBaseImpl {

	@Override
	public WeDeployAuthApp addWeDeployAuthApp(
			long userId, String name, String redirectURI,
			ServiceContext serviceContext)
		throws PortalException {

		// WeDeploy auth app

		User user = userLocalService.fetchUserById(userId);
		Date date = new Date();

		long weDeployAuthAppId = counterLocalService.increment();

		WeDeployAuthApp weDeployAuthApp = weDeployAuthAppPersistence.create(
			weDeployAuthAppId);

		weDeployAuthApp.setCompanyId(user.getCompanyId());
		weDeployAuthApp.setUserId(user.getUserId());
		weDeployAuthApp.setUserName(user.getFullName());
		weDeployAuthApp.setCreateDate(serviceContext.getCreateDate(date));
		weDeployAuthApp.setModifiedDate(serviceContext.getModifiedDate(date));
		weDeployAuthApp.setName(name);
		weDeployAuthApp.setRedirectURI(redirectURI);

		String clientId = PortalUUIDUtil.generate();

		weDeployAuthApp.setClientId(clientId);

		String clientSecret = DigesterUtil.digestHex(
			Digester.MD5, clientId, PwdGenerator.getPassword());

		weDeployAuthApp.setClientSecret(clientSecret);

		weDeployAuthApp = weDeployAuthAppPersistence.update(weDeployAuthApp);

		// Resources

		resourceLocalService.addModelResources(weDeployAuthApp, serviceContext);

		return weDeployAuthApp;
	}

	@Override
	public WeDeployAuthApp fetchWeDeployAuthApp(
		String redirectURI, String clientId) {

		return weDeployAuthAppPersistence.fetchByRU_CI(redirectURI, clientId);
	}

}