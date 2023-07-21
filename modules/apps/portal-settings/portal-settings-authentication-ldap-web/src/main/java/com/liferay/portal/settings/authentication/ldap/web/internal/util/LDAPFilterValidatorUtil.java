/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.settings.authentication.ldap.web.internal.util;

import com.liferay.portal.security.ldap.validator.LDAPFilterValidator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shuyang Zhou
 */
@Component(immediate = true, service = {})
public class LDAPFilterValidatorUtil {

	public static boolean isValidFilter(String filter) {
		return _ldapFilterValidator.isValid(filter);
	}

	@Reference(unbind = "-")
	protected void setLDAPFilterValidator(
		LDAPFilterValidator ldapFilterValidator) {

		_ldapFilterValidator = ldapFilterValidator;
	}

	private static LDAPFilterValidator _ldapFilterValidator;

}