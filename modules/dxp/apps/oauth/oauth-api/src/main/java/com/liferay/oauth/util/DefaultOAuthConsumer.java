/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.util;

import com.liferay.oauth.model.OAuthApplication;

import java.io.Serializable;

/**
 * @author Ivica Cardic
 */
public class DefaultOAuthConsumer implements OAuthConsumer, Serializable {

	public DefaultOAuthConsumer(OAuthApplication oAuthApplication) {
		_oAuthApplication = oAuthApplication;

		_oAuthConsumer = new net.oauth.OAuthConsumer(
			oAuthApplication.getCallbackURI(),
			oAuthApplication.getConsumerKey(),
			oAuthApplication.getConsumerSecret(), null);
	}

	public DefaultOAuthConsumer(net.oauth.OAuthConsumer oAuthConsumer) {
		_oAuthConsumer = oAuthConsumer;

		_oAuthApplication = null;
	}

	@Override
	public String getCallbackURL() {
		return _oAuthConsumer.callbackURL;
	}

	@Override
	public OAuthApplication getOAuthApplication() {
		return _oAuthApplication;
	}

	@Override
	public Object getProperty(String name) {
		return _oAuthConsumer.getProperty(name);
	}

	@Override
	public Object getWrappedOAuthConsumer() {
		return _oAuthConsumer;
	}

	@Override
	public void setWrappedOAuthConsumer(Object oAuthConsumer) {
		_oAuthConsumer = (net.oauth.OAuthConsumer)oAuthConsumer;
	}

	private final OAuthApplication _oAuthApplication;
	private net.oauth.OAuthConsumer _oAuthConsumer;

}