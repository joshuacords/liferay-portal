/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author     Shuyang Zhou
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class MapBackedSet<E> extends AbstractSet<E> implements Serializable {

	public MapBackedSet(Map<E, Boolean> backedMap) {
		if (!backedMap.isEmpty()) {
			throw new IllegalArgumentException("Map is not empty");
		}

		_backedMap = backedMap;

		_backedMapKeySet = backedMap.keySet();
	}

	@Override
	public boolean add(E element) {
		if (_backedMap.put(element, Boolean.TRUE) == null) {
			return true;
		}

		return false;
	}

	@Override
	public void clear() {
		_backedMap.clear();
	}

	@Override
	public boolean contains(Object obj) {
		return _backedMap.containsKey(obj);
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		return _backedMapKeySet.containsAll(collection);
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj == this) || _backedMapKeySet.equals(obj)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return _backedMapKeySet.hashCode();
	}

	@Override
	public boolean isEmpty() {
		return _backedMap.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return _backedMapKeySet.iterator();
	}

	@Override
	public boolean remove(Object obj) {
		if (_backedMap.remove(obj) != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		return _backedMapKeySet.removeAll(collection);
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		return _backedMapKeySet.retainAll(collection);
	}

	@Override
	public int size() {
		return _backedMap.size();
	}

	@Override
	public Object[] toArray() {
		return _backedMapKeySet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] array) {
		return _backedMapKeySet.toArray(array);
	}

	@Override
	public String toString() {
		return _backedMapKeySet.toString();
	}

	private void readObject(ObjectInputStream objectInputStream)
		throws ClassNotFoundException, IOException {

		objectInputStream.defaultReadObject();

		_backedMapKeySet = _backedMap.keySet();
	}

	private final Map<E, Boolean> _backedMap;
	private transient Set<E> _backedMapKeySet;

}