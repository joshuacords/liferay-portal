/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.AuthFailure;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Scott Lee
 */
@OSGiBeanProperties(property = "key=auth.failure")
public class LoginFailure implements AuthFailure {

	@Override
	public void onFailureByEmailAddress(
			long companyId, String emailAddress,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			UserLocalServiceUtil.checkLoginFailureByEmailAddress(
				companyId, emailAddress);
		}
		catch (Exception exception) {
			throw new AuthException(exception);
		}
	}

	@Override
	public void onFailureByScreenName(
			long companyId, String screenName, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			UserLocalServiceUtil.checkLoginFailureByScreenName(
				companyId, screenName);
		}
		catch (Exception exception) {
			throw new AuthException(exception);
		}
	}

	@Override
	public void onFailureByUserId(
			long companyId, long userId, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			UserLocalServiceUtil.checkLoginFailureById(userId);
		}
		catch (Exception exception) {
			throw new AuthException(exception);
		}
	}

}