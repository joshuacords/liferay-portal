/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.ldap;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.vldap.server.internal.handler.BindLdapHandler;
import com.liferay.vldap.server.internal.util.OIDConstants;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class RootDirectory extends Directory {

	public RootDirectory() {
		addAttribute("namingcontexts", "o=Liferay");
		addAttribute("objectclass", "extensibleObject");
		addAttribute("objectclass", "top");
		addAttribute("subschemasubentry", "cn=" + SchemaDirectory.COMMON_NAME);
		addAttribute(
			"supportedfeatures", OIDConstants.ALL_OPERATIONAL_ATTRIBUTES);
		addAttribute("supportedcontrol", "1.2.840.113556.1.4.319");
		addAttribute("supportedldapversion", "3");
		addAttribute("supportedsaslmechanisms", BindLdapHandler.DIGEST_MD5);
		addAttribute("vendorname", "Liferay, Inc.");
		addAttribute("vendorversion", ReleaseInfo.getVersion());
	}

	@Override
	public String getName() {
		return StringPool.BLANK;
	}

}