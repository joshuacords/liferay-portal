/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.google.constants;

/**
 * @author Stian Sigvartsen
 */
public class LegacyGoogleLoginPropsKeys {

	public static final String AUTH_ENABLED = "google-auth-enabled";

	public static final String CLIENT_ID = "google-client-id";

	public static final String CLIENT_SECRET = "google-client-secret";

	public static final String[] GOOGLE_LOGIN_KEYS = {
		AUTH_ENABLED, CLIENT_ID, CLIENT_SECRET
	};

}