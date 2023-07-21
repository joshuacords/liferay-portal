/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.google;

import com.liferay.portal.kernel.model.User;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author Stian Sigvartsen
 */
public interface GoogleAuthorization {

	public User addOrUpdateUser(
			HttpSession session, long companyId, String authorizationCode,
			String returnRequestUri, List<String> scopes)
		throws Exception;

	public String getLoginRedirect(
			long companyId, String returnRequestUri, List<String> scopes)
		throws Exception;

	public boolean isEnabled(long companyId);

}