/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.openid.connect.internal;

import com.liferay.portal.kernel.exception.PortalException;

import com.nimbusds.openid.connect.sdk.claims.UserInfo;

/**
 * @author Michael C. Han
 */
public interface OpenIdConnectUserInfoProcessor {

	public long processUserInfo(
			UserInfo userInfo, long companyId, String mainPath,
			String portalURL)
		throws PortalException;

}