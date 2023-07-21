/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.openid.connect.internal;

import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.openid.connect.sdk.Nonce;

import java.io.Serializable;

/**
 * @author Arthur Chan
 */
public class OpenIdConnectAuthenticationSession implements Serializable {

	public OpenIdConnectAuthenticationSession(
		Nonce nonce, String providerName, State state) {

		_nonce = nonce;
		_providerName = providerName;
		_state = state;
	}

	public Nonce getNonce() {
		return _nonce;
	}

	public String getProviderName() {
		return _providerName;
	}

	public State getState() {
		return _state;
	}

	private final Nonce _nonce;
	private final String _providerName;
	private final State _state;

}