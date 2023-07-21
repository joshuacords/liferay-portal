/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.constants;

/**
 * @author Shinn Lok
 */
public class OAuthConstants {

	public static final String PUBLIC_PATH_ACCESS_TOKEN =
		"/portal/oauth/access_token";

	public static final String PUBLIC_PATH_AUTHORIZE =
		"/portal/oauth/authorize";

	public static final String PUBLIC_PATH_REQUEST_TOKEN =
		"/portal/oauth/request_token";

	public static final String[] PUBLIC_PATHS = {
		PUBLIC_PATH_ACCESS_TOKEN, PUBLIC_PATH_AUTHORIZE,
		PUBLIC_PATH_REQUEST_TOKEN
	};

}