/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.web.internal.model;

/**
 * @author Alessio Antonio Rendina
 * @author Alec Sloan
 */
public class Member {

	public Member(
		long memberId, long accountId, String name, String email, String roles,
		String href) {

		_memberId = memberId;
		_accountId = accountId;
		_name = name;
		_email = email;
		_roles = roles;
		_href = href;
	}

	public long getAccountId() {
		return _accountId;
	}

	public String getEmail() {
		return _email;
	}

	public String getHref() {
		return _href;
	}

	public long getMemberId() {
		return _memberId;
	}

	public String getName() {
		return _name;
	}

	public String getRoles() {
		return _roles;
	}

	private final long _accountId;
	private final String _email;
	private final String _href;
	private final long _memberId;
	private final String _name;
	private final String _roles;

}