/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.ldap;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;

/**
 * @author Brian Wing Shun Chan
 * @author Jonathan Potter
 */
public class OrganizationDirectory extends Directory {

	public OrganizationDirectory(
		String top, Company company, Organization organization) {

		addAttribute("cn", organization.getName());
		addAttribute("objectclass", "groupOfNames");
		addAttribute("objectclass", "liferayOrganization");
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", organization.getName());

		addMemberAttributes(
			top, company,
			LinkedHashMapBuilder.<String, Object>put(
				"usersOrgs", organization.getOrganizationId()
			).build());

		setName(top, company, "Organizations", organization.getName());
	}

}