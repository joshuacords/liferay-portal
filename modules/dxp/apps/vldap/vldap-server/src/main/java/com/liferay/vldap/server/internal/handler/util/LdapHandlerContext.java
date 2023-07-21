/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.handler.util;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;

import javax.security.sasl.SaslServer;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class LdapHandlerContext {

	public Company getCompany() {
		return _company;
	}

	public long getCompanyId() {
		return _company.getCompanyId();
	}

	public SaslCallbackHandler getSaslCallbackHandler() {
		return _saslCallbackHandler;
	}

	public SaslServer getSaslServer() {
		return _saslServer;
	}

	public User getUser() {
		return _user;
	}

	public void setCompany(Company company) {
		_company = company;
	}

	public void setSaslCallbackHandler(
		SaslCallbackHandler saslCallbackHandler) {

		_saslCallbackHandler = saslCallbackHandler;
	}

	public void setSaslServer(SaslServer saslServer) {
		_saslServer = saslServer;
	}

	public void setUser(User user) {
		_user = user;
	}

	private Company _company;
	private SaslCallbackHandler _saslCallbackHandler;
	private SaslServer _saslServer;
	private User _user;

}