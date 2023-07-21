/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.web.internal.auth.publicpath;

import com.liferay.oauth.constants.OAuthConstants;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(
	immediate = true,
	property = {
		"auth.public.path=" + OAuthConstants.PUBLIC_PATH_ACCESS_TOKEN,
		"auth.public.path=" + OAuthConstants.PUBLIC_PATH_AUTHORIZE,
		"auth.public.path=" + OAuthConstants.PUBLIC_PATH_REQUEST_TOKEN
	},
	service = Object.class
)
public class AuthPublicPath {
}