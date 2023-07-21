/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.concurrent.CompeteLatch;

import java.io.Serializable;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Shuyang Zhou
 */
public class BlockingPortalCache<K extends Serializable, V>
	extends PortalCacheWrapper<K, V> {

	public BlockingPortalCache(PortalCache<K, V> portalCache) {
		super(portalCache);
	}

	@Override
	public V get(K key) {
		V value = portalCache.get(key);

		if (value != null) {
			return value;
		}

		CompeteLatch lastCompeteLatch = _competeLatch.get();

		if (lastCompeteLatch != null) {
			lastCompeteLatch.done();

			_competeLatch.set(null);
		}

		CompeteLatch currentCompeteLatch = _competeLatchMap.get(key);

		if (currentCompeteLatch == null) {
			CompeteLatch newCompeteLatch = new CompeteLatch();

			currentCompeteLatch = _competeLatchMap.putIfAbsent(
				key, newCompeteLatch);

			if (currentCompeteLatch == null) {
				currentCompeteLatch = newCompeteLatch;
			}
		}

		_competeLatch.set(currentCompeteLatch);

		if (!currentCompeteLatch.compete()) {
			try {
				currentCompeteLatch.await();
			}
			catch (InterruptedException interruptedException) {
			}

			_competeLatch.set(null);

			value = portalCache.get(key);
		}

		return value;
	}

	public boolean isBlocking() {
		return true;
	}

	@Override
	public void put(K key, V value) {
		put(key, value, DEFAULT_TIME_TO_LIVE);
	}

	@Override
	public void put(K key, V value, int timeToLive) {
		portalCache.put(key, value, timeToLive);

		CompeteLatch competeLatch = _competeLatch.get();

		if (competeLatch != null) {
			competeLatch.done();

			_competeLatch.set(null);
		}

		_competeLatchMap.remove(key);
	}

	@Override
	public void remove(K key) {
		portalCache.remove(key);

		CompeteLatch competeLatch = _competeLatchMap.remove(key);

		if (competeLatch != null) {
			competeLatch.done();
		}
	}

	@Override
	public void removeAll() {
		portalCache.removeAll();

		Collection<CompeteLatch> competeLatches = _competeLatchMap.values();

		Iterator<CompeteLatch> iterator = competeLatches.iterator();

		while (iterator.hasNext()) {
			CompeteLatch competeLatch = iterator.next();

			iterator.remove();

			competeLatch.done();
		}
	}

	private static final ThreadLocal<CompeteLatch> _competeLatch =
		new ThreadLocal<>();

	private final ConcurrentMap<K, CompeteLatch> _competeLatchMap =
		new ConcurrentHashMap<>();

}