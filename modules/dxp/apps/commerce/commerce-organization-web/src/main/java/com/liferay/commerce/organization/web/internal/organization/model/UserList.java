/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.organization.web.internal.organization.model;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class UserList {

	public UserList(List<User> users, int total) {
		_users = users;
		_total = total;
	}

	public UserList(String[] errorMessages) {
		_errorMessages = errorMessages;

		_success = false;
	}

	public int getTotal() {
		return _total;
	}

	public List<User> getUsers() {
		return _users;
	}

	public void setErrorMessages(String[] errorMessages) {
		_errorMessages = errorMessages;
	}

	public void setSuccess(boolean success) {
		_success = success;
	}

	private String[] _errorMessages;
	private boolean _success;
	private int _total;
	private List<User> _users;

}