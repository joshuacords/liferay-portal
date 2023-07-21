/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service.impl;

import com.liferay.osb.koroneiki.scion.constants.ScionActionKeys;
import com.liferay.osb.koroneiki.scion.model.AuthenticationToken;
import com.liferay.osb.koroneiki.scion.permission.AuthenticationTokenPermission;
import com.liferay.osb.koroneiki.scion.service.base.AuthenticationTokenServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 * @author Kyle Bischof
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=AuthenticationToken"
	},
	service = AopService.class
)
public class AuthenticationTokenServiceImpl
	extends AuthenticationTokenServiceBaseImpl {

	public AuthenticationToken addAuthenticationToken(
			long serviceProducerId, String name, String token)
		throws PortalException {

		_authenticationTokenPermission.check(
			getPermissionChecker(), ScionActionKeys.ADD_AUTHENTICATION_TOKEN);

		return authenticationTokenLocalService.addAuthenticationToken(
			getUserId(), serviceProducerId, name, token);
	}

	public AuthenticationToken deleteAuthenticationToken(
			long authenticationTokenId)
		throws PortalException {

		_authenticationTokenPermission.check(
			getPermissionChecker(), authenticationTokenId, ActionKeys.DELETE);

		return authenticationTokenLocalService.deleteAuthenticationToken(
			authenticationTokenId);
	}

	public AuthenticationToken updateAuthenticationToken(
			long authenticationTokenId, String name)
		throws PortalException {

		_authenticationTokenPermission.check(
			getPermissionChecker(), authenticationTokenId, ActionKeys.UPDATE);

		return authenticationTokenLocalService.updateAuthenticationToken(
			authenticationTokenId, name);
	}

	public AuthenticationToken updateStatus(
			long authenticationTokenId, int status)
		throws PortalException {

		_authenticationTokenPermission.check(
			getPermissionChecker(), authenticationTokenId, ActionKeys.UPDATE);

		return authenticationTokenLocalService.updateStatus(
			authenticationTokenId, status);
	}

	@Reference
	private AuthenticationTokenPermission _authenticationTokenPermission;

}