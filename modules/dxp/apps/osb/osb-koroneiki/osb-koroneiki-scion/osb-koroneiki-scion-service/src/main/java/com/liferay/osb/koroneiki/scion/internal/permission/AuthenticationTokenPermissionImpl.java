/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.internal.permission;

import com.liferay.osb.koroneiki.scion.model.AuthenticationToken;
import com.liferay.osb.koroneiki.scion.permission.AuthenticationTokenPermission;
import com.liferay.osb.koroneiki.scion.service.AuthenticationTokenLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = AuthenticationTokenPermission.class)
public class AuthenticationTokenPermissionImpl
	implements AuthenticationTokenPermission {

	public static final String RESOURCE_NAME_AUTHENTICATION_TOKENS =
		"com.liferay.osb.koroneiki.scion.authentication.tokens";

	@Override
	public void check(
			PermissionChecker permissionChecker,
			AuthenticationToken authenticationToken, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, authenticationToken, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, AuthenticationToken.class.getName(),
				authenticationToken.getAuthenticationTokenId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long authenticationTokenId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, authenticationTokenId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, AuthenticationToken.class.getName(),
				authenticationTokenId, actionId);
		}
	}

	@Override
	public void check(PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, RESOURCE_NAME_AUTHENTICATION_TOKENS, 0,
				actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			AuthenticationToken authenticationToken, String actionId)
		throws PortalException {

		if (permissionChecker.hasOwnerPermission(
				authenticationToken.getCompanyId(),
				AuthenticationToken.class.getName(),
				authenticationToken.getAuthenticationTokenId(),
				authenticationToken.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			0, AuthenticationToken.class.getName(),
			authenticationToken.getAuthenticationTokenId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long authenticationTokenId,
			String actionId)
		throws PortalException {

		if (contains(
				permissionChecker,
				_authenticationTokenLocalService.getAuthenticationToken(
					authenticationTokenId),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] authenticationTokenIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(authenticationTokenIds)) {
			return false;
		}

		for (long authenticationTokenId : authenticationTokenIds) {
			if (!contains(permissionChecker, authenticationTokenId, actionId)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		return permissionChecker.hasPermission(
			0, RESOURCE_NAME_AUTHENTICATION_TOKENS,
			RESOURCE_NAME_AUTHENTICATION_TOKENS, actionId);
	}

	@Reference
	private AuthenticationTokenLocalService _authenticationTokenLocalService;

}