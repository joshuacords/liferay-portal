/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.cache;

import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.io.Serializable;

/**
 * @author     Brian Wing Shun Chan
 * @author     Michael Young
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
@OSGiBeanProperties(service = MultiVMPoolUtil.class)
public class MultiVMPoolUtil {

	public static void clear() {
		_multiVMPool.clear();
	}

	public static <K extends Serializable, V extends Serializable>
		PortalCache<K, V> getPortalCache(String portalCacheName) {

		return (PortalCache<K, V>)_multiVMPool.getPortalCache(portalCacheName);
	}

	public static <K extends Serializable, V extends Serializable>
		PortalCache<K, V> getPortalCache(
			String portalCacheName, boolean blocking) {

		return (PortalCache<K, V>)_multiVMPool.getPortalCache(
			portalCacheName, blocking);
	}

	public static <K extends Serializable, V extends Serializable>
		PortalCache<K, V> getPortalCache(
			String portalCacheName, boolean blocking, boolean mvcc) {

		return (PortalCache<K, V>)_multiVMPool.getPortalCache(
			portalCacheName, blocking, mvcc);
	}

	public static <K extends Serializable, V extends Serializable>
		PortalCacheManager<K, V> getPortalCacheManager() {

		return (PortalCacheManager<K, V>)_multiVMPool.getPortalCacheManager();
	}

	public static void removePortalCache(String portalCacheName) {
		_multiVMPool.removePortalCache(portalCacheName);
	}

	private static volatile MultiVMPool _multiVMPool =
		ServiceProxyFactory.newServiceTrackedInstance(
			MultiVMPool.class, MultiVMPoolUtil.class, "_multiVMPool", true);

}