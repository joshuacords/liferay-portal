/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.cache;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 */
public class CacheRegistryUtil {

	public static void clear() {
		_cacheRegistryUtil._clear();
	}

	public static void clear(String name) {
		_cacheRegistryUtil._clear(name);
	}

	public static boolean isActive() {
		return _cacheRegistryUtil._isActive();
	}

	public static void setActive(boolean active) {
		_cacheRegistryUtil._setActive(active);
	}

	private CacheRegistryUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			CacheRegistryItem.class,
			new CacheRegistryItemServiceTrackerCustomizer());

		_serviceTracker.open();
	}

	private void _clear() {
		for (Map.Entry<String, CacheRegistryItem> entry :
				_cacheRegistryItems.entrySet()) {

			CacheRegistryItem cacheRegistryItem = entry.getValue();

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Invalidating " + cacheRegistryItem.getRegistryName());
			}

			cacheRegistryItem.invalidate();
		}
	}

	private void _clear(String name) {
		CacheRegistryItem cacheRegistryItem = _cacheRegistryItems.get(name);

		if (cacheRegistryItem != null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Invalidating " + name);
			}

			cacheRegistryItem.invalidate();
		}
		else {
			_log.error("No cache registry found with name " + name);
		}
	}

	private boolean _isActive() {
		return _active;
	}

	private void _setActive(boolean active) {
		_active = active;

		if (!active) {
			_clear();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CacheRegistryUtil.class);

	private static final CacheRegistryUtil _cacheRegistryUtil =
		new CacheRegistryUtil();

	private volatile boolean _active = true;
	private final Map<String, CacheRegistryItem> _cacheRegistryItems =
		new ConcurrentHashMap<>();
	private final ServiceTracker<CacheRegistryItem, CacheRegistryItem>
		_serviceTracker;

	private class CacheRegistryItemServiceTrackerCustomizer
		implements ServiceTrackerCustomizer
			<CacheRegistryItem, CacheRegistryItem> {

		@Override
		public CacheRegistryItem addingService(
			ServiceReference<CacheRegistryItem> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			CacheRegistryItem cacheRegistryItem = registry.getService(
				serviceReference);

			_cacheRegistryItems.put(
				cacheRegistryItem.getRegistryName(), cacheRegistryItem);

			return cacheRegistryItem;
		}

		@Override
		public void modifiedService(
			ServiceReference<CacheRegistryItem> serviceReference,
			CacheRegistryItem cacheRegistryItem) {
		}

		@Override
		public void removedService(
			ServiceReference<CacheRegistryItem> serviceReference,
			CacheRegistryItem cacheRegistryItem) {

			_cacheRegistryItems.remove(cacheRegistryItem.getRegistryName());
		}

	}

}