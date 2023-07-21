/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.orm;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class EntityCacheUtil {

	public static void clearCache() {
		_entityCache.clearCache();
	}

	public static void clearCache(Class<?> clazz) {
		_entityCache.clearCache(clazz);
	}

	public static void clearLocalCache() {
		_entityCache.clearLocalCache();
	}

	public static EntityCache getEntityCache() {
		return _entityCache;
	}

	public static PortalCache<Serializable, Serializable> getPortalCache(
		Class<?> clazz) {

		return _entityCache.getPortalCache(clazz);
	}

	public static Serializable getResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey) {

		return _entityCache.getResult(entityCacheEnabled, clazz, primaryKey);
	}

	public static void invalidate() {
		_entityCache.invalidate();
	}

	public static Serializable loadResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey,
		SessionFactory sessionFactory) {

		return _entityCache.loadResult(
			entityCacheEnabled, clazz, primaryKey, sessionFactory);
	}

	public static void putResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey,
		Serializable result) {

		_entityCache.putResult(entityCacheEnabled, clazz, primaryKey, result);
	}

	public static void putResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey,
		Serializable result, boolean quiet) {

		_entityCache.putResult(
			entityCacheEnabled, clazz, primaryKey, result, quiet);
	}

	public static void removeCache(String className) {
		_entityCache.removeCache(className);
	}

	public static void removeResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey) {

		_entityCache.removeResult(entityCacheEnabled, clazz, primaryKey);
	}

	private static volatile EntityCache _entityCache =
		ServiceProxyFactory.newServiceTrackedInstance(
			EntityCache.class, EntityCacheUtil.class, "_entityCache", false);

}