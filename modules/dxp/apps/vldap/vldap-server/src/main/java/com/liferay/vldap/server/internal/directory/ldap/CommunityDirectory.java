/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.ldap;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 * @author Jonathan Potter
 */
public class CommunityDirectory extends Directory {

	public CommunityDirectory(String top, Company company, Group community) {
		Locale locale = LocaleUtil.getDefault();

		addAttribute("cn", community.getName(locale));
		addAttribute("description", community.getDescription(locale));

		addAttribute("objectclass", "groupOfNames");
		addAttribute("objectclass", "liferayCommunity");
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", community.getName(locale));

		addMemberAttributes(
			top, company,
			LinkedHashMapBuilder.<String, Object>put(
				"usersGroups", community.getGroupId()
			).build());

		setName(top, company, "Communities", community.getName(locale));
	}

}