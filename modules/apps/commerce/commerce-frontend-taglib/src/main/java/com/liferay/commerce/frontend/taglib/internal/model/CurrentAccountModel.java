/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.taglib.internal.model;

/**
 * @author Marco Leo
 */
public class CurrentAccountModel {

	public CurrentAccountModel(long accountId, String name, String thumbnail) {
		_accountId = accountId;
		_name = name;
		_thumbnail = thumbnail;
	}

	public long getAccountId() {
		return _accountId;
	}

	public String getName() {
		return _name;
	}

	public String getThumbnail() {
		return _thumbnail;
	}

	private final long _accountId;
	private final String _name;
	private final String _thumbnail;

}