/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.captcha;

import com.liferay.portal.kernel.util.ServiceProxyFactory;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerMap;

import java.io.IOException;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class CaptchaUtil {

	public static void check(HttpServletRequest httpServletRequest)
		throws CaptchaException {

		getCaptcha().check(httpServletRequest);
	}

	public static void check(PortletRequest portletRequest)
		throws CaptchaException {

		getCaptcha().check(portletRequest);
	}

	public static Captcha getCaptcha() {
		if (_serviceTrackerMap == null) {
			return null;
		}

		String captchaClassName = _captchaSettings.getCaptchaEngine();

		return _serviceTrackerMap.getService(captchaClassName);
	}

	public static String getTaglibPath() {
		return getCaptcha().getTaglibPath();
	}

	public static boolean isEnabled(HttpServletRequest httpServletRequest) {
		return getCaptcha().isEnabled(httpServletRequest);
	}

	public static boolean isEnabled(PortletRequest portletRequest) {
		return getCaptcha().isEnabled(portletRequest);
	}

	public static void serveImage(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		getCaptcha().serveImage(httpServletRequest, httpServletResponse);
	}

	public static void serveImage(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		getCaptcha().serveImage(resourceRequest, resourceResponse);
	}

	public void setCaptcha(Captcha captcha) throws Exception {
		Class<?> clazz = captcha.getClass();

		_captchaSettings.setCaptchaEngine(clazz.getName());
	}

	private static volatile CaptchaSettings _captchaSettings =
		ServiceProxyFactory.newServiceTrackedInstance(
			CaptchaSettings.class, CaptchaUtil.class, "_captchaSettings",
			false);
	private static final ServiceTrackerMap<String, Captcha> _serviceTrackerMap =
		ServiceTrackerCollections.openSingleValueMap(
			Captcha.class, "captcha.engine.impl");

}