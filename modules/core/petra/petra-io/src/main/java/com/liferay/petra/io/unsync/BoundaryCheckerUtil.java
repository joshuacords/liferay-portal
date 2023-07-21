/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.io.unsync;

import com.liferay.petra.string.StringBundler;

/**
 * @author Preston Crary
 */
class BoundaryCheckerUtil {

	public static void check(int count, int offset, int length) {
		int end = offset + length;

		if ((offset < 0) || (offset > count) || (length < 0) || (end > count) ||
			(end < 0)) {

			StringBundler sb = new StringBundler(7);

			sb.append("{count=");
			sb.append(count);
			sb.append(", offset=");
			sb.append(offset);
			sb.append(", length=");
			sb.append(length);
			sb.append("}");

			throw new IndexOutOfBoundsException(sb.toString());
		}
	}

}