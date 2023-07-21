/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.jaas.ext.weblogic;

import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.security.jaas.ext.BasicLoginModule;

import java.security.Principal;

import javax.security.auth.login.LoginException;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalLoginModule extends BasicLoginModule {

	@Override
	protected Principal getPortalPrincipal(String name) throws LoginException {
		try {
			return (Principal)InstanceFactory.newInstance(
				_WLS_USER_IMPL, String.class, name);
		}
		catch (Exception exception) {
			throw new LoginException(exception.getMessage());
		}
	}

	private static final String _WLS_USER_IMPL =
		"weblogic.security.principal.WLSUserImpl";

}