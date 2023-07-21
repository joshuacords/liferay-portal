/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.openid.internal.auto.login;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.openid.OpenId;
import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.BaseAutoLogin;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Participates in every unauthenticated HTTP request to Liferay Portal.
 *
 * <p>
 * This class looks for the <code>OPENID_ID_LOGIN</code> HTTP session attribute.
 * If this attribute is found and if the attribute's value matches the ID of an
 * existing Liferay Portal user, then the user is logged in without any further
 * challenge.
 * </p>
 *
 * @author Jorge Ferrer
 */
@Component(immediate = true, service = AutoLogin.class)
public class OpenIdAutoLogin extends BaseAutoLogin {

	@Override
	protected String[] doLogin(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		if (!_openId.isEnabled(_portal.getCompanyId(httpServletRequest))) {
			return null;
		}

		HttpSession session = httpServletRequest.getSession();

		Long userId = (Long)session.getAttribute(WebKeys.OPEN_ID_LOGIN);

		if (userId == null) {
			return null;
		}

		session.removeAttribute(WebKeys.OPEN_ID_LOGIN);

		User user = _userLocalService.getUserById(userId);

		String[] credentials = new String[3];

		credentials[0] = String.valueOf(user.getUserId());
		credentials[1] = user.getPassword();
		credentials[2] = Boolean.TRUE.toString();

		return credentials;
	}

	@Reference(unbind = "-")
	protected void setOpenId(OpenId openId) {
		_openId = openId;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private OpenId _openId;

	@Reference
	private Portal _portal;

	private UserLocalService _userLocalService;

}