/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.util;

import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * @author Javier Gamarra
 */
public class UriInfoUtil {

	public static String getAbsolutePath(UriInfo uriInfo) {
		if (_isHttpsEnabled()) {
			UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();

			return String.valueOf(
				uriBuilder.scheme(
					"https"
				).build());
		}

		return String.valueOf(uriInfo.getAbsolutePath());
	}

	public static String getBasePath(UriInfo uriInfo) {
		UriBuilder uriBuilder = getBaseUriBuilder(uriInfo);

		return String.valueOf(uriBuilder.build());
	}

	public static UriBuilder getBaseUriBuilder(UriInfo uriInfo) {
		if (_isHttpsEnabled()) {
			UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();

			return uriBuilder.scheme("https");
		}

		return uriInfo.getBaseUriBuilder();
	}

	private static boolean _isHttpsEnabled() {
		if (Http.HTTPS.equals(PropsUtil.get(PropsKeys.WEB_SERVER_PROTOCOL)) ||
			Http.HTTPS.equals(
				PropsUtil.get(PropsKeys.PORTAL_INSTANCE_PROTOCOL))) {

			return true;
		}

		return false;
	}

}