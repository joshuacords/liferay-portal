/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso;

import com.liferay.portal.kernel.security.sso.OpenSSO;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import java.io.IOException;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * See https://issues.liferay.com/browse/LEP-5943.
 * </p>
 *
 * @author Prashant Dighe
 * @author Brian Wing Shun Chan
 * @author Wesley Gong
 */
public class OpenSSOUtil {

	public static Map<String, String> getAttributes(
		HttpServletRequest httpServletRequest, String serviceUrl) {

		return _getOpenSSO().getAttributes(httpServletRequest, serviceUrl);
	}

	public static String getSubjectId(
		HttpServletRequest httpServletRequest, String serviceUrl) {

		return _getOpenSSO().getSubjectId(httpServletRequest, serviceUrl);
	}

	public static boolean isAuthenticated(
			HttpServletRequest httpServletRequest, String serviceUrl)
		throws IOException {

		return _getOpenSSO().isAuthenticated(httpServletRequest, serviceUrl);
	}

	public static boolean isValidServiceUrl(String serviceUrl) {
		return _getOpenSSO().isValidServiceUrl(serviceUrl);
	}

	public static boolean isValidUrl(String url) {
		return _getOpenSSO().isValidUrl(url);
	}

	public static boolean isValidUrls(String[] urls) {
		return _getOpenSSO().isValidUrls(urls);
	}

	private static OpenSSO _getOpenSSO() {
		return _openSSOUtil._serviceTracker.getService();
	}

	private OpenSSOUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(OpenSSO.class);

		_serviceTracker.open();
	}

	private static final OpenSSOUtil _openSSOUtil = new OpenSSOUtil();

	private final ServiceTracker<OpenSSO, OpenSSO> _serviceTracker;

}