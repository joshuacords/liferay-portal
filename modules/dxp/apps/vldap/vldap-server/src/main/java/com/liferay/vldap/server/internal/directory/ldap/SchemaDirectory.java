/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.directory.ldap;

import com.liferay.vldap.server.internal.util.LdapUtil;

/**
 * @author Jonathan Potter
 */
public class SchemaDirectory extends Directory {

	public static final String COMMON_NAME = "subschema";

	public SchemaDirectory() {
		this(COMMON_NAME);
	}

	public SchemaDirectory(String commonName) {
		addAttribute("objectclass", "subschema");
		addAttribute("objectclass", "top");
		addAttribute("cn", commonName);

		_name = "cn=".concat(LdapUtil.escape(commonName));
	}

	@Override
	protected String getName() {
		return _name;
	}

	private final String _name;

}