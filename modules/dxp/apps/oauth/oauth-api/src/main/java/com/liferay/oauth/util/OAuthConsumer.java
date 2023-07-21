/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.util;

import com.liferay.oauth.model.OAuthApplication;

/**
 * @author Ivica Cardic
 */
public interface OAuthConsumer {

	public String getCallbackURL();

	public OAuthApplication getOAuthApplication();

	public Object getProperty(String name);

	public Object getWrappedOAuthConsumer();

	public void setWrappedOAuthConsumer(Object oAuthConsumer);

}