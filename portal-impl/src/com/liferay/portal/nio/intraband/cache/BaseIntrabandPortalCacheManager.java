/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.nio.intraband.cache;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheManager;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.nio.intraband.proxy.IntrabandProxyUtil;
import com.liferay.portal.nio.intraband.proxy.WarnLogExceptionHandler;

import java.io.Serializable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shuyang Zhou
 */
public abstract class BaseIntrabandPortalCacheManager
	<K extends Serializable, V extends Serializable>
		implements PortalCacheManager<K, V> {

	public static Class<? extends PortalCache<?, ?>> getPortalCacheStubClass() {
		return _STUB_CLASS;
	}

	@Override
	public void destroy() {
		_portalCaches.clear();
	}

	public PortalCache<K, V> fetchPortalCache(String portalCacheName) {
		return _portalCaches.get(portalCacheName);
	}

	@Override
	public PortalCache<K, V> getPortalCache(String portalCacheName) {
		return getPortalCache(portalCacheName, false);
	}

	@Override
	public PortalCache<K, V> getPortalCache(
		String portalCacheName, boolean blocking) {

		PortalCache<K, V> portalCache = _portalCaches.get(portalCacheName);

		if (portalCache == null) {
			portalCache = (PortalCache<K, V>)IntrabandProxyUtil.newStubInstance(
				_STUB_CLASS, portalCacheName, _registrationReference,
				WarnLogExceptionHandler.INSTANCE);

			_portalCaches.put(portalCacheName, portalCache);
		}

		return portalCache;
	}

	@Override
	public void removePortalCache(String portalCacheName) {
		_portalCaches.remove(portalCacheName);
	}

	private static final Class<? extends PortalCache<?, ?>> _STUB_CLASS =
		(Class<? extends PortalCache<?, ?>>)IntrabandProxyUtil.getStubClass(
			PortalCache.class, PortalCache.class.getName());

	private final Map<String, PortalCache<K, V>> _portalCaches =
		new ConcurrentHashMap<>();
	private final RegistrationReference _registrationReference = null;

}