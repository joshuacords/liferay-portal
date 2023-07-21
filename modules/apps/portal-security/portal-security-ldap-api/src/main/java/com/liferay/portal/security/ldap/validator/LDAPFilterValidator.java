/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.ldap.validator;

/**
 * @author Vilmos Papp
 */
public interface LDAPFilterValidator {

	public boolean isValid(String filter);

	public void validate(String filter) throws LDAPFilterException;

	public void validate(String filter, String filterPropertyName)
		throws LDAPFilterException;

}