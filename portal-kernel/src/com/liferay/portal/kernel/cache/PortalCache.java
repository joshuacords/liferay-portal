/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.cache;

import com.liferay.portal.kernel.nio.intraband.proxy.annotation.Id;
import com.liferay.portal.kernel.nio.intraband.proxy.annotation.Proxy;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @author Edward Han
 * @author Shuyang Zhou
 */
@ProviderType
public interface PortalCache<K extends Serializable, V> {

	public static final int DEFAULT_TIME_TO_LIVE = 0;

	@Proxy
	public V get(K key);

	@Proxy
	public List<K> getKeys();

	public PortalCacheManager<K, V> getPortalCacheManager();

	@Id
	public String getPortalCacheName();

	public boolean isBlocking();

	public boolean isMVCC();

	@Proxy
	public void put(K key, V value);

	@Proxy
	public void put(K key, V value, int timeToLive);

	public void registerPortalCacheListener(
		PortalCacheListener<K, V> portalCacheListener);

	public void registerPortalCacheListener(
		PortalCacheListener<K, V> portalCacheListener,
		PortalCacheListenerScope portalCacheListenerScope);

	@Proxy
	public void remove(K key);

	@Proxy
	public void removeAll();

	public void unregisterPortalCacheListener(
		PortalCacheListener<K, V> portalCacheListener);

	public void unregisterPortalCacheListeners();

}