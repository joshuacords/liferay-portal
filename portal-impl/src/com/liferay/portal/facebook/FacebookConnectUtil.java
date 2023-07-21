/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.facebook;

import com.liferay.portal.kernel.facebook.FacebookConnect;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import javax.portlet.PortletRequest;

/**
 * @author Wilson Man
 * @author Brian Wing Shun Chan
 * @author Mika Koivisto
 */
public class FacebookConnectUtil {

	public static String getAccessToken(
		long companyId, String redirect, String code) {

		return getFacebookConnect().getAccessToken(companyId, redirect, code);
	}

	public static String getAccessTokenURL(long companyId) {
		return getFacebookConnect().getAccessTokenURL(companyId);
	}

	public static String getAppId(long companyId) {
		return getFacebookConnect().getAppId(companyId);
	}

	public static String getAppSecret(long companyId) {
		return getFacebookConnect().getAppSecret(companyId);
	}

	public static String getAuthURL(long companyId) {
		return getFacebookConnect().getAuthURL(companyId);
	}

	public static FacebookConnect getFacebookConnect() {
		return _facebookConnectUtil._serviceTracker.getService();
	}

	public static JSONObject getGraphResources(
		long companyId, String path, String accessToken, String fields) {

		return getFacebookConnect().getGraphResources(
			companyId, path, accessToken, fields);
	}

	public static String getGraphURL(long companyId) {
		return getFacebookConnect().getGraphURL(companyId);
	}

	public static String getProfileImageURL(PortletRequest portletRequest) {
		return getFacebookConnect().getProfileImageURL(portletRequest);
	}

	public static String getRedirectURL(long companyId) {
		return getFacebookConnect().getRedirectURL(companyId);
	}

	public static boolean isEnabled(long companyId) {
		return getFacebookConnect().isEnabled(companyId);
	}

	public static boolean isVerifiedAccountRequired(long companyId) {
		return getFacebookConnect().isVerifiedAccountRequired(companyId);
	}

	private FacebookConnectUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(FacebookConnect.class);

		_serviceTracker.open();
	}

	private static final FacebookConnectUtil _facebookConnectUtil =
		new FacebookConnectUtil();

	private final ServiceTracker<FacebookConnect, FacebookConnect>
		_serviceTracker;

}