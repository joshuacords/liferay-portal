/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.ldap;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;

/**
 * @author Raymond Augé
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class UserGroupDirectory extends Directory {

	public UserGroupDirectory(
		String top, Company company, UserGroup userGroup) {

		addAttribute("cn", userGroup.getName());
		addAttribute("description", userGroup.getDescription());
		addAttribute("objectclass", "groupOfNames");
		addAttribute("objectclass", "liferayUserGroup");
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", userGroup.getName());

		addMemberAttributes(
			top, company,
			LinkedHashMapBuilder.<String, Object>put(
				"usersUserGroups", userGroup.getUserGroupId()
			).build());

		setName(top, company, "User Groups", userGroup.getName());
	}

}