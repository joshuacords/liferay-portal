/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.oauth;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuthFactoryUtil {

	public static OAuthManager createOAuthManager(
			String key, String secret, String accessURL, String requestURL,
			String callbackURL, String scope)
		throws OAuthException {

		return getOAuthFactory().createOAuthManager(
			key, secret, accessURL, requestURL, callbackURL, scope);
	}

	public static OAuthRequest createOAuthRequest(Verb verb, String url)
		throws OAuthException {

		return getOAuthFactory().createOAuthRequest(verb, url);
	}

	public static Token createToken(String token, String secret)
		throws OAuthException {

		return getOAuthFactory().createToken(token, secret);
	}

	public static Verifier createVerifier(String verifier)
		throws OAuthException {

		return getOAuthFactory().createVerifier(verifier);
	}

	public static OAuthFactory getOAuthFactory() {
		return _oAuthFactory;
	}

	public void setOAuthFactory(OAuthFactory oAuthFactory) {
		_oAuthFactory = oAuthFactory;
	}

	private static OAuthFactory _oAuthFactory;

}