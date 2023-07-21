/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.oauth;

import com.liferay.portal.kernel.oauth.OAuthException;
import com.liferay.portal.kernel.oauth.OAuthRequest;
import com.liferay.portal.kernel.oauth.OAuthResponse;
import com.liferay.portal.kernel.oauth.Verb;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuthRequestImpl implements OAuthRequest {

	public OAuthRequestImpl(org.scribe.model.OAuthRequest oAuthRequest) {
		_oAuthRequest = oAuthRequest;
	}

	@Override
	public void addBodyParameter(String key, String value) {
		_oAuthRequest.addBodyParameter(key, value);
	}

	@Override
	public String getURL() {
		return _oAuthRequest.getUrl();
	}

	@Override
	public Verb getVerb() {
		return VerbTranslator.translate(_oAuthRequest.getVerb());
	}

	@Override
	public Object getWrappedOAuthRequest() {
		return _oAuthRequest;
	}

	@Override
	public OAuthResponse send() throws OAuthException {
		try {
			return new OAuthResponseImpl(_oAuthRequest.send());
		}
		catch (Exception exception) {
			throw new OAuthException(exception);
		}
	}

	private final org.scribe.model.OAuthRequest _oAuthRequest;

}