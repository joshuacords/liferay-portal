/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.auth.http;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tomas Polesovsky
 */
public class HttpAuthManagerUtil {

	public static void generateChallenge(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse,
		HttpAuthorizationHeader httpAuthorizationHeader) {

		_getHttpAuthManager().generateChallenge(
			httpServletRequest, httpServletResponse, httpAuthorizationHeader);
	}

	public static long getBasicUserId(HttpServletRequest httpServletRequest)
		throws PortalException {

		return _getHttpAuthManager().getBasicUserId(httpServletRequest);
	}

	public static long getDigestUserId(HttpServletRequest httpServletRequest)
		throws PortalException {

		return _getHttpAuthManager().getDigestUserId(httpServletRequest);
	}

	public static long getUserId(
			HttpServletRequest httpServletRequest,
			HttpAuthorizationHeader httpAuthorizationHeader)
		throws PortalException {

		return _getHttpAuthManager().getUserId(
			httpServletRequest, httpAuthorizationHeader);
	}

	public static HttpAuthorizationHeader parse(
		HttpServletRequest httpServletRequest) {

		return _getHttpAuthManager().parse(httpServletRequest);
	}

	private static HttpAuthManager _getHttpAuthManager() {
		return _httpAuthManagerUtil._serviceTracker.getService();
	}

	private HttpAuthManagerUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(HttpAuthManager.class);

		_serviceTracker.open();
	}

	private static final HttpAuthManagerUtil _httpAuthManagerUtil =
		new HttpAuthManagerUtil();

	private final ServiceTracker<?, HttpAuthManager> _serviceTracker;

}