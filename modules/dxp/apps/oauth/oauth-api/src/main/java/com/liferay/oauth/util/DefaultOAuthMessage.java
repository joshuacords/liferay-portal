/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.util;

import java.io.IOException;

/**
 * @author Ivica Cardic
 */
public class DefaultOAuthMessage implements OAuthMessage {

	public DefaultOAuthMessage(net.oauth.OAuthMessage oAuthMessage) {
		_oAuthMessage = oAuthMessage;
	}

	@Override
	public String getConsumerKey() throws IOException {
		return _oAuthMessage.getConsumerKey();
	}

	@Override
	public String getParameter(String name) throws IOException {
		return _oAuthMessage.getParameter(name);
	}

	@Override
	public String getToken() throws IOException {
		return _oAuthMessage.getToken();
	}

	@Override
	public Object getWrappedOAuthMessage() {
		return _oAuthMessage;
	}

	private final net.oauth.OAuthMessage _oAuthMessage;

}