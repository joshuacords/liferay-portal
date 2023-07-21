/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.auth;

import java.util.Set;

/**
 * @author Tomas Polesovsky
 * @author Raymond Augé
 */
public class InterruptedPortletRequestWhitelistUtil {

	public static PortletRequestWhitelist
		getInterruptedPortletRequestWhitelist() {

		return _interruptedPortletRequestWhitelist;
	}

	public static Set<String> getPortletInvocationWhitelist() {
		return getInterruptedPortletRequestWhitelist().
			getPortletInvocationWhitelist();
	}

	public static Set<String> getPortletInvocationWhitelistActions() {
		return getInterruptedPortletRequestWhitelist().
			getPortletInvocationWhitelistActions();
	}

	public static boolean isPortletInvocationWhitelisted(
		long companyId, String portletId, String strutsAction) {

		return getInterruptedPortletRequestWhitelist().
			isPortletInvocationWhitelisted(companyId, portletId, strutsAction);
	}

	public static Set<String> resetPortletInvocationWhitelist() {
		return getInterruptedPortletRequestWhitelist().
			resetPortletInvocationWhitelist();
	}

	public static Set<String> resetPortletInvocationWhitelistActions() {
		return getInterruptedPortletRequestWhitelist().
			resetPortletInvocationWhitelistActions();
	}

	public void setInterruptedPortletRequestWhitelist(
		PortletRequestWhitelist whitelist) {

		_interruptedPortletRequestWhitelist = whitelist;
	}

	private static PortletRequestWhitelist _interruptedPortletRequestWhitelist;

}