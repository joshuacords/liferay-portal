/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.jaas.ext;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.security.jaas.PortalPrincipal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.security.jaas.JAASHelper;

import java.io.IOException;

import java.security.Principal;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 * @author Brian Wing Shun Chan
 */
public class BasicLoginModule implements LoginModule {

	@Override
	public boolean abort() {
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		Principal principal = getPrincipal();

		if (principal != null) {
			Subject subject = getSubject();

			Set<Principal> principals = subject.getPrincipals();

			principals.add(getPrincipal());

			return true;
		}

		return false;
	}

	@Override
	public void initialize(
		Subject subject, CallbackHandler callbackHandler,
		Map<String, ?> sharedState, Map<String, ?> options) {

		_subject = subject;
		_callbackHandler = callbackHandler;
	}

	@Override
	public boolean login() throws LoginException {
		String[] credentials = null;

		try {
			credentials = authenticate();
		}
		catch (Exception exception) {
			_log.error(exception.getMessage());

			throw new LoginException();
		}

		if ((credentials != null) && (credentials.length == 2)) {
			setPrincipal(getPortalPrincipal(credentials[0]));
			setPassword(credentials[1]);

			return true;
		}

		throw new LoginException();
	}

	@Override
	public boolean logout() {
		Subject subject = getSubject();

		Set<Principal> principals = subject.getPrincipals();

		principals.clear();

		return true;
	}

	protected String[] authenticate()
		throws IOException, UnsupportedCallbackException {

		NameCallback nameCallback = new NameCallback("name: ");
		PasswordCallback passwordCallback = new PasswordCallback(
			"password: ", false);

		_callbackHandler.handle(
			new Callback[] {nameCallback, passwordCallback});

		String name = nameCallback.getName();

		String password = null;
		char[] passwordChar = passwordCallback.getPassword();

		if (passwordChar != null) {
			password = new String(passwordChar);
		}

		if (name == null) {
			return new String[] {StringPool.BLANK, StringPool.BLANK};
		}

		try {
			List<Company> companies = CompanyLocalServiceUtil.getCompanies();

			for (Company company : companies) {
				long userId = JAASHelper.getJaasUserId(
					company.getCompanyId(), name);

				if (userId == 0) {
					continue;
				}

				if (UserLocalServiceUtil.authenticateForJAAS(
						userId, password)) {

					return new String[] {name, password};
				}
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return null;
	}

	protected String getPassword() {
		return _password;
	}

	protected Principal getPortalPrincipal(String name) throws LoginException {
		return new PortalPrincipal(name);
	}

	protected Principal getPrincipal() {
		return _principal;
	}

	protected Subject getSubject() {
		return _subject;
	}

	protected void setPassword(String password) {
		_password = password;
	}

	protected void setPrincipal(Principal principal) {
		_principal = principal;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BasicLoginModule.class);

	private CallbackHandler _callbackHandler;
	private String _password;
	private Principal _principal;
	private Subject _subject;

}