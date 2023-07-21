/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util;

import java.util.Stack;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), replaced by
 *             {com.liferay.petra.collection.stack.FiniteStack}
 */
@Deprecated
public class FiniteStack<E> extends Stack<E> {

	public FiniteStack(int maxSize) {
		_maxSize = maxSize;
	}

	@Override
	public E push(E item) {
		super.push(item);

		int size = size();

		if (size > _maxSize) {
			removeElementAt(size - 1);
		}

		return item;
	}

	private final int _maxSize;

}