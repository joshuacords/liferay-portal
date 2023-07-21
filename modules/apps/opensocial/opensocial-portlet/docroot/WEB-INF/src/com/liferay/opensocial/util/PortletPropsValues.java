/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Michael Young
 */
public class PortletPropsValues {

	public static final int PUBSUB_URI_LOAD_TIMEOUT = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.PUBSUB_URI_LOAD_TIMEOUT));

	public static final boolean SHINDIG_JS_DEBUG = GetterUtil.getBoolean(
		PortletProps.get(PortletPropsKeys.SHINDIG_JS_DEBUG));

	public static final boolean SHINDIG_NO_CACHE = GetterUtil.getBoolean(
		PortletProps.get(PortletPropsKeys.SHINDIG_NO_CACHE));

	public static final String SHINDIG_OAUTH_CALLBACK_URL =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.SHINDIG_OAUTH_CALLBACK_URL));

	public static final String SHINDIG_OAUTH_KEY_FILE_NAME =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.SHINDIG_OAUTH_KEY_FILE_NAME));

	public static final String SHINDIG_OAUTH_KEY_NAME = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.SHINDIG_OAUTH_KEY_NAME));

}