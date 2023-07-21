/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.configuration;

import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * @author Tina Tian
 */
public class PortalCacheConfiguration {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #PORTAL_CACHE_NAME_DEFAULT}
	 */
	@Deprecated
	public static final String DEFAULT_PORTAL_CACHE_NAME = "default";

	public static final String PORTAL_CACHE_LISTENER_PROPERTIES_KEY_SCOPE =
		"PORTAL_CACHE_LISTENER_PROPERTIES_KEY_SCOPE";

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #PORTAL_CACHE_LISTENER_PROPERTIES_KEY_SCOPE}
	 */
	@Deprecated
	public static final String PORTAL_CACHE_LISTENER_SCOPE =
		"PORTAL_CACHE_LISTENER_SCOPE";

	public static final String PORTAL_CACHE_NAME_DEFAULT = "default";

	public PortalCacheConfiguration(
		String portalCacheName,
		Set<Properties> portalCacheListenerPropertiesSet,
		Properties portalCacheBootstrapLoaderProperties) {

		if (portalCacheName == null) {
			throw new NullPointerException("Portal cache name is null");
		}

		_portalCacheName = portalCacheName;

		if (portalCacheListenerPropertiesSet == null) {
			_portalCacheListenerPropertiesSet = Collections.emptySet();
		}
		else {
			_portalCacheListenerPropertiesSet = new HashSet<>(
				portalCacheListenerPropertiesSet);
		}
	}

	/**
	 * @deprecated As of Mueller (7.2.x), with no direct replacement
	 */
	@Deprecated
	public Properties getPortalCacheBootstrapLoaderProperties() {
		return null;
	}

	public Set<Properties> getPortalCacheListenerPropertiesSet() {
		return _portalCacheListenerPropertiesSet;
	}

	public String getPortalCacheName() {
		return _portalCacheName;
	}

	public PortalCacheConfiguration newPortalCacheConfiguration(
		String portalCacheName) {

		return new PortalCacheConfiguration(
			portalCacheName, _portalCacheListenerPropertiesSet, null);
	}

	/**
	 * @deprecated As of Mueller (7.2.x), with no direct replacement
	 */
	@Deprecated
	public void setPortalCacheBootstrapLoaderProperties(
		Properties portalCacheBootstrapLoaderProperties) {
	}

	private final Set<Properties> _portalCacheListenerPropertiesSet;
	private final String _portalCacheName;

}