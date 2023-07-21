/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.security.auth.BaseAuthTokenWhitelist;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Raymond Augé
 * @author Tomas Polesovsky
 */
public class AuthTokenWhitelistImpl extends BaseAuthTokenWhitelist {

	public AuthTokenWhitelistImpl() {
		trackWhitelistServices(
			PropsKeys.AUTH_TOKEN_IGNORE_ORIGINS, _originCSRFWhitelist);

		registerPortalProperty(PropsKeys.AUTH_TOKEN_IGNORE_ORIGINS);

		trackWhitelistServices(
			PropsKeys.AUTH_TOKEN_IGNORE_PORTLETS, _portletCSRFWhitelist);

		registerPortalProperty(PropsKeys.AUTH_TOKEN_IGNORE_PORTLETS);

		trackWhitelistServices(
			PropsKeys.PORTLET_ADD_DEFAULT_RESOURCE_CHECK_WHITELIST,
			_portletInvocationWhitelist);

		registerPortalProperty(
			PropsKeys.PORTLET_ADD_DEFAULT_RESOURCE_CHECK_WHITELIST);
	}

	@Override
	public boolean isOriginCSRFWhitelisted(long companyId, String origin) {
		for (String whitelistedOrigin : _originCSRFWhitelist) {
			if (origin.startsWith(whitelistedOrigin)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isPortletCSRFWhitelisted(
		HttpServletRequest httpServletRequest, Portlet portlet) {

		return _portletCSRFWhitelist.contains(portlet.getRootPortletId());
	}

	@Override
	public boolean isPortletInvocationWhitelisted(
		HttpServletRequest httpServletRequest, Portlet portlet) {

		return _portletInvocationWhitelist.contains(portlet.getPortletId());
	}

	@Override
	public boolean isPortletURLCSRFWhitelisted(
		LiferayPortletURL liferayPortletURL) {

		String rootPortletId = PortletIdCodec.decodePortletName(
			liferayPortletURL.getPortletId());

		return _portletCSRFWhitelist.contains(rootPortletId);
	}

	@Override
	public boolean isPortletURLPortletInvocationWhitelisted(
		LiferayPortletURL liferayPortletURL) {

		return _portletInvocationWhitelist.contains(
			liferayPortletURL.getPortletId());
	}

	@Override
	public boolean isValidSharedSecret(String sharedSecret) {
		if (Validator.isNull(sharedSecret)) {
			return false;
		}

		if (Validator.isNull(PropsValues.AUTH_TOKEN_SHARED_SECRET)) {
			return false;
		}

		return sharedSecret.equals(
			DigesterUtil.digest(PropsValues.AUTH_TOKEN_SHARED_SECRET));
	}

	private final Set<String> _originCSRFWhitelist = Collections.newSetFromMap(
		new ConcurrentHashMap<>());
	private final Set<String> _portletCSRFWhitelist = Collections.newSetFromMap(
		new ConcurrentHashMap<>());
	private final Set<String> _portletInvocationWhitelist =
		Collections.newSetFromMap(new ConcurrentHashMap<>());

}