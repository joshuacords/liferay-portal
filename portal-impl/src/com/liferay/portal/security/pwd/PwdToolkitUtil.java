/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pwd;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.UserPasswordException;
import com.liferay.portal.kernel.model.PasswordPolicy;
import com.liferay.portal.kernel.security.ldap.LDAPSettingsUtil;
import com.liferay.portal.kernel.security.pwd.Toolkit;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

/**
 * @author Brian Wing Shun Chan
 */
public class PwdToolkitUtil {

	public static String generate(PasswordPolicy passwordPolicy) {
		Toolkit toolkit = getToolkit();

		return toolkit.generate(passwordPolicy);
	}

	public static Toolkit getToolkit() {
		return _pwdToolkitUtil._serviceTracker.getService();
	}

	public static void validate(
			long companyId, long userId, String password1, String password2,
			PasswordPolicy passwordPolicy)
		throws PortalException {

		if (!password1.equals(password2)) {
			throw new UserPasswordException.MustMatch(userId);
		}

		if (!LDAPSettingsUtil.isPasswordPolicyEnabled(companyId) &&
			PwdToolkitUtilThreadLocal.isValidate()) {

			Toolkit toolkit = getToolkit();

			toolkit.validate(userId, password1, password2, passwordPolicy);
		}
	}

	private PwdToolkitUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(Toolkit.class);

		_serviceTracker.open();
	}

	private static final PwdToolkitUtil _pwdToolkitUtil = new PwdToolkitUtil();

	private final ServiceTracker<Toolkit, Toolkit> _serviceTracker;

}