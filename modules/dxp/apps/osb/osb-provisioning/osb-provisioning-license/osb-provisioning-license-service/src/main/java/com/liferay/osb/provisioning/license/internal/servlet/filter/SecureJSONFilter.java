/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.internal.servlet.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.servlet.ProtectedServletRequest;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"api.token=fK2MakiRmvlaiwJ2b0", "api.token=ke8eJgsT2nla73NsRe",
		"api.token=lakIw83Ha2vbapqz3j", "api.token=lwn8C5iAlUi2bvaqSm",
		"api.token=sP3Ujen2h1kfMeRWkx", "api.token=vuH6GaVgwkueqmxsR3",
		"dispatcher=FORWARD", "dispatcher=REQUEST", "servlet-context-name=",
		"servlet-filter-name=Secure JSON Filter",
		"url-pattern=/api/jsonws/provisioning.licensekey/*"
	},
	service = Filter.class
)
public class SecureJSONFilter extends BaseFilter {

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)servletRequest;

		try {
			String requestToken = request.getHeader("OSB_API_Token");

			if (Validator.isNotNull(requestToken) && (_apiTokens != null) &&
				_apiTokens.contains(requestToken)) {

				User user = _userLocalService.getUser(_USER_JSON_ID);

				PermissionThreadLocal.setPermissionChecker(
					PermissionCheckerFactoryUtil.create(user));

				PrincipalThreadLocal.setName(_USER_JSON_ID);

				request = new ProtectedServletRequest(
					request, String.valueOf(user.getUserId()),
					HttpServletRequest.DIGEST_AUTH);

				filterChain.doFilter(request, servletResponse);

				return;
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		_apiTokens = Arrays.asList((String[])properties.get("api.token"));
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private static final long _USER_JSON_ID = 20130;

	private static final Log _log = LogFactoryUtil.getLog(
		SecureJSONFilter.class);

	private List<String> _apiTokens;

	@Reference
	private UserLocalService _userLocalService;

}