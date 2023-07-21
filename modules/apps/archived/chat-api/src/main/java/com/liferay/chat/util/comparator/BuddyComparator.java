/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.chat.util.comparator;

import com.liferay.portal.kernel.model.ContactConstants;

import java.util.Comparator;

/**
 * @author Ryan Park
 */
public class BuddyComparator implements Comparator<Object[]> {

	public BuddyComparator() {
		this(false);
	}

	public BuddyComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object[] buddy1, Object[] buddy2) {
		long userId1 = 0;

		if (buddy1[0] instanceof Long) {
			userId1 = (Long)buddy1[0];
		}

		long userId2 = 0;

		if (buddy2[0] instanceof Long) {
			userId2 = (Long)buddy2[0];
		}

		int value = 0;

		if (userId1 == userId2) {
			return value;
		}

		boolean awake1 = (Boolean)buddy1[6];
		boolean awake2 = (Boolean)buddy2[6];

		if (awake1 && !awake2) {
			value = 1;
		}
		else if (!awake1 && awake2) {
			value = -1;
		}

		if (value == 0) {
			String firstName1 = (String)buddy1[2];
			String middleName1 = (String)buddy1[3];
			String lastName1 = (String)buddy1[4];

			String fullName1 = ContactConstants.getFullName(
				firstName1, middleName1, lastName1);

			String lastName2 = (String)buddy2[4];
			String firstName2 = (String)buddy2[2];
			String middleName2 = (String)buddy2[3];

			String fullName2 = ContactConstants.getFullName(
				firstName2, middleName2, lastName2);

			value = fullName1.compareTo(fullName2);
		}

		if (_asc) {
			return value;
		}

		return -value;
	}

	private final boolean _asc;

}