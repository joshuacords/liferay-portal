/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.web.internal.model;

import com.liferay.commerce.frontend.model.LabelField;

/**
 * @author Alessio Antonio Rendina
 */
public class Account {

	public Account(
		long accountId, boolean active, String name, String email,
		String address, LabelField statusLabel, String thumbnail, String href) {

		_accountId = accountId;
		_active = active;
		_name = name;
		_email = email;
		_address = address;
		_statusLabel = statusLabel;
		_thumbnail = thumbnail;
		_href = href;
	}

	public long getAccountId() {
		return _accountId;
	}

	public boolean getActive() {
		return _active;
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

	public LabelField getStatusLabel() {
		return _statusLabel;
	}

	public String getThumbnail() {
		return _thumbnail;
	}

	private final long _accountId;
	private final boolean _active;
	private final String _address;
	private final String _email;
	private final String _href;
	private final String _name;
	private final LabelField _statusLabel;
	private final String _thumbnail;

}