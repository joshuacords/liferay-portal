/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.organization.web.internal.organization.model;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class Organization {

	public Organization(
		long organizationId, String name, List<Organization> organizations,
		int organizationsTotal, int accountsTotal, int usersTotal) {

		_organizationId = organizationId;
		_name = name;
		_organizations = organizations;
		_organizationsTotal = organizationsTotal;
		_accountsTotal = accountsTotal;
		_usersTotal = usersTotal;

		if (_organizationsTotal > 0) {
			_lastLevel = false;
		}
		else {
			_lastLevel = true;
		}

		_success = true;
	}

	public Organization(String[] errorMessages) {
		_errorMessages = errorMessages;

		_success = false;
	}

	public int getAccountsTotal() {
		return _accountsTotal;
	}

	public String[] getErrorMessages() {
		return _errorMessages;
	}

	public boolean getLastLevel() {
		return _lastLevel;
	}

	public String getName() {
		return _name;
	}

	public long getOrganizationId() {
		return _organizationId;
	}

	public List<Organization> getOrganizations() {
		return _organizations;
	}

	public int getOrganizationsTotal() {
		return _organizationsTotal;
	}

	public boolean getSuccess() {
		return _success;
	}

	public int getTotal() {
		return _total;
	}

	public int getUsersTotal() {
		return _usersTotal;
	}

	public void setErrorMessages(String[] errorMessages) {
		_errorMessages = errorMessages;
	}

	public void setSuccess(boolean success) {
		_success = success;
	}

	private int _accountsTotal;
	private String[] _errorMessages;
	private boolean _lastLevel;
	private String _name;
	private long _organizationId;
	private List<Organization> _organizations;
	private int _organizationsTotal;
	private boolean _success;
	private int _total;
	private int _usersTotal;

}