/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

import com.liferay.portal.kernel.openid.OpenId;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

/**
 * @author Jorge Ferrer
 */
public class OpenIdUtil {

	public static boolean isEnabled(long companyId) {
		return getOpenId().isEnabled(companyId);
	}

	protected static OpenId getOpenId() {
		return _openIdUtil._serviceTracker.getService();
	}

	private OpenIdUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(OpenId.class);

		_serviceTracker.open();
	}

	private static final OpenIdUtil _openIdUtil = new OpenIdUtil();

	private final ServiceTracker<OpenId, OpenId> _serviceTracker;

}