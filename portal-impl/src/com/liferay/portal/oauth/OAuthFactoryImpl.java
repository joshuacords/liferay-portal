/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.oauth;

import com.liferay.portal.kernel.oauth.OAuthException;
import com.liferay.portal.kernel.oauth.OAuthFactory;
import com.liferay.portal.kernel.oauth.OAuthManager;
import com.liferay.portal.kernel.oauth.OAuthRequest;
import com.liferay.portal.kernel.oauth.Token;
import com.liferay.portal.kernel.oauth.Verb;
import com.liferay.portal.kernel.oauth.Verifier;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuthFactoryImpl implements OAuthFactory {

	@Override
	public OAuthManager createOAuthManager(
			String key, String secret, String accessURL, String requestURL,
			String callbackURL, String scope)
		throws OAuthException {

		try {
			return new OAuthManagerImpl(
				key, secret, accessURL, requestURL, callbackURL, scope);
		}
		catch (Exception exception) {
			throw new OAuthException(exception);
		}
	}

	@Override
	public OAuthRequest createOAuthRequest(Verb verb, String url)
		throws OAuthException {

		try {
			return new OAuthRequestImpl(
				new org.scribe.model.OAuthRequest(
					VerbTranslator.translate(verb), url));
		}
		catch (Exception exception) {
			throw new OAuthException(exception);
		}
	}

	@Override
	public Token createToken(String token, String secret)
		throws OAuthException {

		try {
			return new TokenImpl(new org.scribe.model.Token(token, secret));
		}
		catch (Exception exception) {
			throw new OAuthException(exception);
		}
	}

	@Override
	public Verifier createVerifier(String verifier) throws OAuthException {
		try {
			return new VerifierImpl(new org.scribe.model.Verifier(verifier));
		}
		catch (Exception exception) {
			throw new OAuthException(exception);
		}
	}

}