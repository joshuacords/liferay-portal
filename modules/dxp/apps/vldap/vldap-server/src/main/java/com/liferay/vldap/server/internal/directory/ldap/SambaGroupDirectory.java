/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.ldap;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Organization;

/**
 * @author Minhchau Dang
 */
public class SambaGroupDirectory extends RoleDirectory {

	public SambaGroupDirectory(
		String top, Company company, Organization organization,
		SambaGroup sambaGroup) {

		super(sambaGroup.getName());

		addAttribute("displayName", sambaGroup.getName());
		addAttribute("objectclass", "sambaGroupMapping");
		addAttribute("sambaGroupType", "4");
		addAttribute("sambaSID", sambaGroup.getSambaSID());

		String gidNumber = sambaGroup.getGIDNumber();

		if (gidNumber != null) {
			addAttribute("objectclass", "posixGroup");
			addAttribute("gidNumber", gidNumber);
		}

		setName(
			top, company, "Organizations", organization.getName(),
			"Samba Groups", "cn=" + sambaGroup.getName());
	}

}