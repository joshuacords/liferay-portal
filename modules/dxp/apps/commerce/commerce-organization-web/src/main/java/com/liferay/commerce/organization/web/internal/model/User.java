/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.organization.web.internal.model;

/**
 * @author Alessio Antonio Rendina
 */
public class User {

	public User(
		long userId, long organizationId, String name, String email,
		String roles, String href) {

		_userId = userId;
		_organizationId = organizationId;
		_name = name;
		_email = email;
		_roles = roles;
		_href = href;
	}

	public String getEmail() {
		return _email;
	}

	public String getHref() {
		return _href;
	}

	public String getName() {
		return _name;
	}

	public long getOrganizationId() {
		return _organizationId;
	}

	public String getRoles() {
		return _roles;
	}

	public long getUserId() {
		return _userId;
	}

	private final String _email;
	private final String _href;
	private final String _name;
	private final long _organizationId;
	private final String _roles;
	private final long _userId;

}