/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.util;

import java.io.Serializable;

/**
 * @author Ivica Cardic
 */
public interface OAuthAccessor extends Serializable {

	public String getAccessToken();

	public OAuthConsumer getOAuthConsumer();

	public Object getProperty(String name);

	public String getRequestToken();

	public String getTokenSecret();

	public Object getWrappedOAuthAccessor();

	public void setAccessToken(String accesToken);

	public void setProperty(String name, Object value);

	public void setRequestToken(String requestToken);

	public void setTokenSecret(String tokenSecret);

}