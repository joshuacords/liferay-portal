/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.oauth;

import com.liferay.portal.kernel.oauth.Token;

/**
 * @author Brian Wing Shun Chan
 */
public class TokenImpl implements Token {

	public TokenImpl(org.scribe.model.Token token) {
		_token = token;
	}

	@Override
	public String getSecret() {
		return _token.getSecret();
	}

	@Override
	public String getToken() {
		return _token.getToken();
	}

	@Override
	public Object getWrappedToken() {
		return _token;
	}

	private final org.scribe.model.Token _token;

}