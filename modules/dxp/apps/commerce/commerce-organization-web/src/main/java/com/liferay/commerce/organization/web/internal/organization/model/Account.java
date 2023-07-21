/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.organization.web.internal.organization.model;

/**
 * @author Alessio Antonio Rendina
 */
public class Account {

	public Account(long accountId, String name, String imageUrl, String email) {
		_accountId = accountId;
		_name = name;
		_imageUrl = imageUrl;
		_email = email;
	}

	public long getAccountId() {
		return _accountId;
	}

	public String getEmail() {
		return _email;
	}

	public String getImageUrl() {
		return _imageUrl;
	}

	public String getName() {
		return _name;
	}

	private final long _accountId;
	private final String _email;
	private final String _imageUrl;
	private final String _name;

}