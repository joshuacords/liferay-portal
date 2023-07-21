/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.ehcache.internal;

import com.liferay.portal.cache.io.SerializableObjectWrapper;
import com.liferay.portal.kernel.cache.PortalCacheManager;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * @author Tina Tian
 */
public class SerializableEhcachePortalCache<K extends Serializable, V>
	extends EhcachePortalCache<K, V> {

	public SerializableEhcachePortalCache(
		PortalCacheManager<K, V> portalCacheManager, Ehcache ehcache) {

		super(portalCacheManager, ehcache);
	}

	@Override
	public List<K> getKeys() {
		List<?> rawKeys = ehcache.getKeys();

		if (rawKeys.isEmpty()) {
			return Collections.emptyList();
		}

		List<K> keys = new ArrayList<>(rawKeys.size());

		for (Object object : rawKeys) {
			keys.add(SerializableObjectWrapper.<K>unwrap(object));
		}

		return keys;
	}

	protected Element createElement(K key, V value) {
		Object objectValue = value;

		if (value instanceof Serializable) {
			objectValue = new SerializableObjectWrapper((Serializable)value);
		}

		return new Element(new SerializableObjectWrapper(key), objectValue);
	}

	@Override
	protected V doGet(K key) {
		Element element = ehcache.get(new SerializableObjectWrapper(key));

		if (element == null) {
			return null;
		}

		return SerializableObjectWrapper.unwrap(element.getObjectValue());
	}

	@Override
	protected void doPut(K key, V value, int timeToLive) {
		Element element = createElement(key, value);

		if (timeToLive != DEFAULT_TIME_TO_LIVE) {
			element.setTimeToLive(timeToLive);
		}

		ehcache.put(element);
	}

	@Override
	protected V doPutIfAbsent(K key, V value, int timeToLive) {
		Element element = createElement(key, value);

		if (timeToLive != DEFAULT_TIME_TO_LIVE) {
			element.setTimeToLive(timeToLive);
		}

		Element oldElement = ehcache.putIfAbsent(element);

		if (oldElement == null) {
			return null;
		}

		return SerializableObjectWrapper.unwrap(oldElement.getObjectValue());
	}

	@Override
	protected void doRemove(K key) {
		ehcache.remove(new SerializableObjectWrapper(key));
	}

	@Override
	protected boolean doRemove(K key, V value) {
		return ehcache.removeElement(createElement(key, value));
	}

	@Override
	protected V doReplace(K key, V value, int timeToLive) {
		Element element = createElement(key, value);

		if (timeToLive != DEFAULT_TIME_TO_LIVE) {
			element.setTimeToLive(timeToLive);
		}

		Element oldElement = ehcache.replace(element);

		if (oldElement == null) {
			return null;
		}

		return SerializableObjectWrapper.unwrap(oldElement.getObjectValue());
	}

	@Override
	protected boolean doReplace(K key, V oldValue, V newValue, int timeToLive) {
		Element newElement = createElement(key, newValue);

		if (timeToLive != DEFAULT_TIME_TO_LIVE) {
			newElement.setTimeToLive(timeToLive);
		}

		return ehcache.replace(createElement(key, oldValue), newElement);
	}

}