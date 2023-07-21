/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.mailbox;

import com.liferay.mail.reader.model.Account;
import com.liferay.mail.reader.service.AccountLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Michael C. Han
 * @author Scott Lee
 */
public class PasswordRetriever {

	public PasswordRetriever(HttpServletRequest httpServletRequest) {
		if (httpServletRequest == null) {
			throw new IllegalStateException("Request is null");
		}

		_httpServletRequest = httpServletRequest;
	}

	public String getPassword(long accountId) throws PortalException {
		Account account = AccountLocalServiceUtil.getAccount(accountId);

		if (account.isSavePassword()) {
			return account.getPasswordDecrypted();
		}

		HttpSession session = _httpServletRequest.getSession();

		return (String)session.getAttribute(encodeKey(accountId));
	}

	public void removePassword(long accountId) {
		HttpSession session = _httpServletRequest.getSession();

		session.removeAttribute(encodeKey(accountId));
	}

	public void setPassword(long accountId, String password) {
		HttpSession session = _httpServletRequest.getSession();

		session.setAttribute(encodeKey(accountId), password);
	}

	protected String encodeKey(long accountId) {
		return PasswordRetriever.class.getName(
		).concat(
			StringPool.POUND
		).concat(
			String.valueOf(accountId)
		);
	}

	private final HttpServletRequest _httpServletRequest;

}