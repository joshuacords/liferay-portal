/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.web.internal.model;

/**
 * @author Alessio Antonio Rendina
 */
public class AccountRole {

	public AccountRole(long roleId, String name) {
		_roleId = roleId;
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public long getRoleId() {
		return _roleId;
	}

	private final String _name;
	private final long _roleId;

}