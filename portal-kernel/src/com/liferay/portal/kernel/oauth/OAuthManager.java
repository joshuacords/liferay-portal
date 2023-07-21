/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.oauth;

/**
 * @author Brian Wing Shun Chan
 */
public interface OAuthManager {

	public Token getAccessToken(Token requestToken, Verifier verifier)
		throws OAuthException;

	public Token getRequestToken() throws OAuthException;

	public String getVersion() throws OAuthException;

	public void signRequest(Token accessToken, OAuthRequest oAuthRequest)
		throws OAuthException;

}