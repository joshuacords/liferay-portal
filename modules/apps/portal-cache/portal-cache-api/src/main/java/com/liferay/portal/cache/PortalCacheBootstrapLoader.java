/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache;

/**
 * @author Tina Tian
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public interface PortalCacheBootstrapLoader {

	public static final String BOOTSTRAP_ASYNCHRONOUSLY =
		"bootstrapAsynchronously";

	public static final boolean DEFAULT_BOOTSTRAP_ASYNCHRONOUSLY = true;

	public boolean isAsynchronous();

	public void loadPortalCache(
		String portalCacheManagerName, String portalCacheName);

}