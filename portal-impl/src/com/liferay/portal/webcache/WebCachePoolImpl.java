/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.webcache;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheHelperUtil;
import com.liferay.portal.kernel.cache.PortalCacheManagerNames;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePool;

/**
 * @author Brian Wing Shun Chan
 */
public class WebCachePoolImpl implements WebCachePool {

	public void afterPropertiesSet() {
		_portalCache = PortalCacheHelperUtil.getPortalCache(
			PortalCacheManagerNames.SINGLE_VM, _CACHE_NAME);
	}

	@Override
	public void clear() {
		_portalCache.removeAll();
	}

	@Override
	public Object get(String key, WebCacheItem wci) {
		Object obj = _portalCache.get(key);

		if (obj != null) {
			return obj;
		}

		try {
			obj = wci.convert(key);

			if (obj == null) {
				return null;
			}

			int timeToLive = (int)(wci.getRefreshTime() / Time.SECOND);

			_portalCache.put(key, obj, timeToLive);
		}
		catch (WebCacheException webCacheException) {
			if (_log.isWarnEnabled()) {
				Throwable cause = webCacheException.getCause();

				if (cause != null) {
					_log.warn(cause, cause);
				}
				else {
					_log.warn(webCacheException, webCacheException);
				}
			}
		}

		return obj;
	}

	@Override
	public void remove(String key) {
		_portalCache.remove(key);
	}

	private static final String _CACHE_NAME = WebCachePool.class.getName();

	private static final Log _log = LogFactoryUtil.getLog(
		WebCachePoolImpl.class);

	private PortalCache<String, Object> _portalCache;

}