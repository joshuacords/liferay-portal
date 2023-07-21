/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.portlet;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Iván Zaera Avellón
 */
public class SearchDisplayStyleUtil {

	public static String getDisplayStyle(
		HttpServletRequest httpServletRequest, String portletName,
		String defaultValue) {

		return getDisplayStyle(
			httpServletRequest, portletName, "display-style", defaultValue);
	}

	public static String getDisplayStyle(
		HttpServletRequest httpServletRequest, String portletName, String key,
		String defaultValue) {

		String displayStyle = ParamUtil.getString(
			httpServletRequest, "displayStyle");

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(
				httpServletRequest);

		if (Validator.isNull(displayStyle)) {
			displayStyle = portalPreferences.getValue(
				portletName, key, defaultValue);
		}

		portalPreferences.setValue(portletName, key, displayStyle);

		return displayStyle;
	}

}