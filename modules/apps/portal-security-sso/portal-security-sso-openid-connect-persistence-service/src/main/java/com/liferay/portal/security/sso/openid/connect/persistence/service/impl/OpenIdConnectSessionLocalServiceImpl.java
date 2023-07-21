/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.openid.connect.persistence.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.security.sso.openid.connect.persistence.model.OpenIdConnectSession;
import com.liferay.portal.security.sso.openid.connect.persistence.service.base.OpenIdConnectSessionLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author Arthur Chan
 */
@Component(
	property = "model.class.name=com.liferay.portal.security.sso.openid.connect.persistence.model.OpenIdConnectSession",
	service = AopService.class
)
public class OpenIdConnectSessionLocalServiceImpl
	extends OpenIdConnectSessionLocalServiceBaseImpl {

	@Override
	public void deleteOpenIdConnectSessions(long userId) {
		openIdConnectSessionPersistence.removeByUserId(userId);
	}

	@Override
	public void deleteOpenIdConnectSessions(String configurationPid) {
		openIdConnectSessionPersistence.removeByConfigurationPid(
			configurationPid);
	}

	@Override
	public OpenIdConnectSession fetchOpenIdConnectSession(
		long userId, String configurationPid) {

		return openIdConnectSessionPersistence.fetchByU_C(
			userId, configurationPid);
	}

}