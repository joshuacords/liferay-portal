/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheHelperUtil;
import com.liferay.portal.kernel.cache.PortalCacheManagerNames;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Shuyang Zhou
 */
public class PortalPreferencesWrapperCacheUtil {

	public static final String CACHE_NAME =
		PortalPreferencesWrapperCacheUtil.class.getName();

	public static PortalPreferencesWrapper get(long ownerId, int ownerType) {
		return _portalPreferencesWrapperPortalCache.get(
			_getCacheKey(ownerId, ownerType));
	}

	public static void put(
		long ownerId, int ownerType,
		PortalPreferencesWrapper portalPreferencesWrapper) {

		PortalCacheHelperUtil.putWithoutReplicator(
			_portalPreferencesWrapperPortalCache,
			_getCacheKey(ownerId, ownerType), portalPreferencesWrapper);
	}

	public static void remove(long ownerId, int ownerType) {
		_portalPreferencesWrapperPortalCache.remove(
			_getCacheKey(ownerId, ownerType));
	}

	private static String _getCacheKey(long ownerId, int ownerType) {
		String cacheKey = StringUtil.toHexString(ownerId);

		return cacheKey.concat(StringUtil.toHexString(ownerType));
	}

	private static final PortalCache<String, PortalPreferencesWrapper>
		_portalPreferencesWrapperPortalCache =
			PortalCacheHelperUtil.getPortalCache(
				PortalCacheManagerNames.MULTI_VM, CACHE_NAME, false, true);

}