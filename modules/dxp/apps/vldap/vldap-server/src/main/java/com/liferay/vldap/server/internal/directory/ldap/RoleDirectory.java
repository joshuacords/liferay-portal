/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.ldap;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;

/**
 * @author Brian Wing Shun Chan
 * @author Jonathan Potter
 */
public class RoleDirectory extends Directory {

	public RoleDirectory(String top, Company company, Role role) {
		this(role.getName());

		addAttribute("description", role.getDescription());
		addRoleMembers(top, company, role.getRoleId());
		setName(top, company, "Roles", role.getName());
	}

	public void addRoleMembers(String top, Company company, long roleId) {
		addMemberAttributes(
			top, company,
			LinkedHashMapBuilder.<String, Object>put(
				"usersRoles", roleId
			).build());
	}

	protected RoleDirectory(String name) {
		addAttribute("cn", name);
		addAttribute("objectclass", "groupOfNames");
		addAttribute("objectclass", "liferayRole");
		addAttribute("objectclass", "organizationalRole");
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", name);
	}

}