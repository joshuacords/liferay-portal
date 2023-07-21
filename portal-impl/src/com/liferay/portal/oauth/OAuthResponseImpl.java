/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.oauth;

import com.liferay.portal.kernel.oauth.OAuthResponse;

import org.scribe.model.Response;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuthResponseImpl implements OAuthResponse {

	public OAuthResponseImpl(Response response) {
		_response = response;
	}

	@Override
	public String getBody() {
		return _response.getBody();
	}

	@Override
	public int getStatus() {
		return _response.getCode();
	}

	@Override
	public Object getWrappedOAuthResponse() {
		return _response;
	}

	private final Response _response;

}