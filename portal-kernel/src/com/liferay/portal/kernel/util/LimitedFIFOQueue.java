/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Michael C. Han
 */
public class LimitedFIFOQueue<E> extends AbstractQueue<E> {

	public LimitedFIFOQueue(int capacity) {
		_capacity = capacity;
	}

	@Override
	public Iterator<E> iterator() {
		return _linkedList.iterator();
	}

	@Override
	public boolean offer(E e) {
		if (size() >= _capacity) {
			_linkedList.removeFirst();
		}

		_linkedList.offerLast(e);

		return true;
	}

	@Override
	public E peek() {
		return _linkedList.peek();
	}

	@Override
	public E poll() {
		return _linkedList.poll();
	}

	@Override
	public int size() {
		return _linkedList.size();
	}

	private final int _capacity;
	private final LinkedList<E> _linkedList = new LinkedList<>();

}