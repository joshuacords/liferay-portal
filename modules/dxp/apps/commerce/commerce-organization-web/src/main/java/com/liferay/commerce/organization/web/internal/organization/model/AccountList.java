/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.organization.web.internal.organization.model;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class AccountList {

	public AccountList(List<Account> accounts, int total) {
		_accounts = accounts;
		_total = total;
	}

	public AccountList(String[] errorMessages) {
		_errorMessages = errorMessages;

		_success = false;
	}

	public List<Account> getAccounts() {
		return _accounts;
	}

	public int getTotal() {
		return _total;
	}

	public void setErrorMessages(String[] errorMessages) {
		_errorMessages = errorMessages;
	}

	public void setSuccess(boolean success) {
		_success = success;
	}

	private List<Account> _accounts;
	private String[] _errorMessages;
	private boolean _success;
	private int _total;

}