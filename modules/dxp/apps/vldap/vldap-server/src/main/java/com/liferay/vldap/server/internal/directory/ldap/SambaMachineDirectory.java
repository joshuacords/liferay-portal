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
public class SambaMachineDirectory extends Directory {

	public SambaMachineDirectory(
		String top, Company company, Organization organization,
		String sambaDomainName) {

		addAttribute("objectclass", "sambaDomain");
		addAttribute("objectclass", "top");
		addAttribute("sambaAlgorithmicRidBase", "1000");
		addAttribute("sambaDomainName", sambaDomainName);
		addAttribute("sambaLockoutThreshold", "0");
		addAttribute("sambaNextUserRid", "1000");
		addAttribute("sambaPwdHistoryLength", "0");
		addAttribute("sambaSID", "S-1-5-21-" + company.getCompanyId());

		setName(
			top, company, "Organizations", organization.getName(),
			"Samba Machines", "sambaDomainName=" + sambaDomainName);
	}

}