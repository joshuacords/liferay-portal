/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), replaced by
 *             {com.liferay.petra.collection.stack.FiniteStack}
 */
@Deprecated
public class FiniteUniqueStack<E> extends FiniteStack<E> {

	public FiniteUniqueStack(int maxSize) {
		super(maxSize);
	}

	@Override
	public E push(E item) {
		if (!contains(item)) {
			super.push(item);
		}
		else {
			if (!item.equals(peek())) {
				remove(item);

				super.push(item);
			}
		}

		return item;
	}

}