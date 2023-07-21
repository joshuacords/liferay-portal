/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.alloy.mvc;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Connor McKay
 */
public abstract class AlloyFriendlyURLMapper extends DefaultFriendlyURLMapper {

	@Override
	public String buildPath(LiferayPortletURL liferayPortletURL) {
		Map<String, String> routeParameters = new HashMap<>();

		buildRouteParameters(liferayPortletURL, routeParameters);

		// Populate method parameter based on the portlet lifecycle

		String lifecycle = liferayPortletURL.getLifecycle();

		String delta = routeParameters.get("delta");

		if (lifecycle.equals(PortletRequest.ACTION_PHASE) || (delta != null)) {
			routeParameters.put("method", HttpMethods.POST);
		}
		else {
			routeParameters.put("method", HttpMethods.GET);
		}

		// Map URL with router

		String friendlyURLPath = router.parametersToUrl(routeParameters);

		if (friendlyURLPath == null) {
			return null;
		}

		// Remove mapped parameters from URL

		addParametersIncludedInPath(liferayPortletURL, routeParameters);

		// Remove method

		int pos = friendlyURLPath.indexOf(CharPool.SLASH);

		if (pos != -1) {
			friendlyURLPath = friendlyURLPath.substring(pos);
		}
		else {
			friendlyURLPath = StringPool.BLANK;
		}

		// Add mapping

		return StringPool.SLASH.concat(
			getMapping()
		).concat(
			friendlyURLPath
		);
	}

	@Override
	public void populateParams(
		String friendlyURLPath, Map<String, String[]> parameterMap,
		Map<String, Object> requestContext) {

		// Determine lifecycle from request method

		HttpServletRequest httpServletRequest =
			(HttpServletRequest)requestContext.get("request");

		friendlyURLPath =
			httpServletRequest.getMethod() +
				friendlyURLPath.substring(getMapping().length() + 1);

		if (friendlyURLPath.endsWith(StringPool.SLASH)) {
			friendlyURLPath = friendlyURLPath.substring(
				0, friendlyURLPath.length() - 1);
		}

		Map<String, String> routeParameters = new HashMap<>();

		if (!router.urlToParameters(friendlyURLPath, routeParameters)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No route could be found to match URL " + friendlyURLPath);
			}

			return;
		}

		String portletId = getPortletInstanceKey(routeParameters);

		if (portletId == null) {
			return;
		}

		String namespace = PortalUtil.getPortletNamespace(portletId);

		addParameter(namespace, parameterMap, "p_p_id", portletId);

		addParameter(
			parameterMap, "p_p_lifecycle", getLifecycle(httpServletRequest));

		String format = routeParameters.get("format");

		if (Validator.isNotNull(format)) {
			addParameter(parameterMap, "p_p_state", "exclusive");
		}

		populateParams(parameterMap, namespace, routeParameters);
	}

	protected String getLifecycle(HttpServletRequest httpServletRequest) {
		if (PortalUtil.isMultipartRequest(httpServletRequest)) {
			return "1";
		}

		return ParamUtil.getString(httpServletRequest, "p_p_lifecycle", "0");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AlloyFriendlyURLMapper.class);

}