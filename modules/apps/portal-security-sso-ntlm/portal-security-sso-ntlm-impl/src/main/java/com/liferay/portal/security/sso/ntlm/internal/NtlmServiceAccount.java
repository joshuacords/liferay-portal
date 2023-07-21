/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.ntlm.internal;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;

/**
 * @author Marcellus Tavares
 */
public class NtlmServiceAccount {

	public NtlmServiceAccount(String account, String password) {
		setAccount(account);
		setPassword(password);
	}

	public String getAccount() {
		return _account;
	}

	public String getAccountName() {
		return _accountName;
	}

	public String getComputerName() {
		return _computerName;
	}

	public String getPassword() {
		return _password;
	}

	public void setAccount(String account) {
		_account = account;

		_accountName = _account.substring(0, _account.indexOf(CharPool.AT));
		_computerName = _account.substring(
			0, _account.indexOf(StringPool.DOLLAR));
	}

	public void setPassword(String password) {
		_password = password;
	}

	private String _account;
	private String _accountName;
	private String _computerName;
	private String _password;

}