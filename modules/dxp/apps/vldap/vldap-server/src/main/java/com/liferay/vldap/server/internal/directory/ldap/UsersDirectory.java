/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.ldap;

import com.liferay.portal.kernel.model.Company;

import java.util.LinkedHashMap;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class UsersDirectory extends Directory {

	public UsersDirectory(String top, Company company) {
		addAttribute("cn", "Users");
		addAttribute("objectclass", "groupOfNames");

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		addMemberAttributes(top, company, params);

		setName(top, company, "Users");
	}

}