/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.openid;

import java.net.URL;

import java.util.Collection;

/**
 * @author Michael C. Han
 */
public interface OpenIdProviderRegistry {

	public static final String OPEN_ID_PROVIDER_NAME_DEFAULT = "default";

	public OpenIdProvider getOpenIdProvider(String name);

	public OpenIdProvider getOpenIdProvider(URL url);

	public Collection<String> getOpenIdProviderNames();

}