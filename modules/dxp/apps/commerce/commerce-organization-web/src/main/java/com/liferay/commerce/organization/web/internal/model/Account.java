/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.organization.web.internal.model;

/**
 * @author Alessio Antonio Rendina
 */
public class Account {

	public Account(
		long accountId, String name, String email, String address,
		String thumbnail, String href) {

		_accountId = accountId;
		_name = name;
		_email = email;
		_address = address;
		_thumbnail = thumbnail;
		_href = href;
	}

	public long getAccountId() {
		return _accountId;
	}

	public String getAddress() {
		return _address;
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

	public String getThumbnail() {
		return _thumbnail;
	}

	private final long _accountId;
	private final String _address;
	private final String _email;
	private final String _href;
	private final String _name;
	private final String _thumbnail;

}