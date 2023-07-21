/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.jaas.ext.jboss;

import com.liferay.portal.kernel.security.jaas.PortalGroup;
import com.liferay.portal.kernel.security.jaas.PortalPrincipal;
import com.liferay.portal.security.jaas.ext.BasicLoginModule;

import java.security.Principal;

import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalLoginModule extends BasicLoginModule {

	@Override
	public boolean commit() throws LoginException {
		boolean commitValue = super.commit();

		if (commitValue) {
			PortalGroup rolesPortalGroup = new PortalGroup("Roles");

			rolesPortalGroup.addMember(new PortalPrincipal("users"));

			Subject subject = getSubject();

			Set<Principal> principals = subject.getPrincipals();

			principals.add(rolesPortalGroup);

			PortalGroup callerPrincipalGroup = new PortalGroup(
				"CallerPrincipal");

			callerPrincipalGroup.addMember(getPrincipal());

			principals.add(callerPrincipalGroup);
		}

		return commitValue;
	}

}