/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.orm;

import com.liferay.portal.kernel.cache.PortalCache;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public interface EntityCache {

	public void clearCache();

	public void clearCache(Class<?> clazz);

	public void clearLocalCache();

	public PortalCache<Serializable, Serializable> getPortalCache(
		Class<?> clazz);

	public Serializable getResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey);

	public void invalidate();

	public Serializable loadResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey,
		SessionFactory sessionFactory);

	public void putResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey,
		Serializable result);

	public void putResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey,
		Serializable result, boolean quiet);

	public void removeCache(String className);

	public void removeResult(
		boolean entityCacheEnabled, Class<?> clazz, Serializable primaryKey);

}