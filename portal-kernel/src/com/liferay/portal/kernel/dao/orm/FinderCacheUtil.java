/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.orm;

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author Brian Wing Shun Chan
 */
public class FinderCacheUtil {

	public static void clearCache() {
		_finderCache.clearCache();
	}

	public static void clearCache(String className) {
		_finderCache.clearCache(className);
	}

	public static void clearLocalCache() {
		_finderCache.clearLocalCache();
	}

	public static FinderCache getFinderCache() {
		return _finderCache;
	}

	public static Object getResult(
		FinderPath finderPath, Object[] args,
		BasePersistenceImpl<? extends BaseModel<?>> basePersistenceImpl) {

		return _finderCache.getResult(finderPath, args, basePersistenceImpl);
	}

	public static void invalidate() {
		getFinderCache().invalidate();
	}

	public static void putResult(
		FinderPath finderPath, Object[] args, Object result) {

		_finderCache.putResult(finderPath, args, result);
	}

	public static void putResult(
		FinderPath finderPath, Object[] args, Object result, boolean quiet) {

		_finderCache.putResult(finderPath, args, result, quiet);
	}

	public static void removeCache(String className) {
		_finderCache.removeCache(className);
	}

	public static void removeResult(FinderPath finderPath, Object[] args) {
		_finderCache.removeResult(finderPath, args);
	}

	private static volatile FinderCache _finderCache =
		ServiceProxyFactory.newServiceTrackedInstance(
			FinderCache.class, FinderCacheUtil.class, "_finderCache", false);

}