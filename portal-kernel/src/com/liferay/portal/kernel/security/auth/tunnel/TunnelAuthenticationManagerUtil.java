/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.auth.tunnel;

import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tomas Polesovsky
 */
public class TunnelAuthenticationManagerUtil {

	public static long getUserId(HttpServletRequest httpServletRequest)
		throws AuthException {

		return _getTunnelManagerUtil().getUserId(httpServletRequest);
	}

	public static void setCredentials(
			String login, HttpURLConnection httpURLConnection)
		throws Exception {

		_getTunnelManagerUtil().setCredentials(login, httpURLConnection);
	}

	private static TunnelAuthenticationManager _getTunnelManagerUtil() {
		return _tunnelAuthenticationManagerUtil._serviceTracker.getService();
	}

	private TunnelAuthenticationManagerUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			TunnelAuthenticationManager.class);

		_serviceTracker.open();
	}

	private static final TunnelAuthenticationManagerUtil
		_tunnelAuthenticationManagerUtil =
			new TunnelAuthenticationManagerUtil();

	private final ServiceTracker<?, TunnelAuthenticationManager>
		_serviceTracker;

}