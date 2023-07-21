/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.HashCode;
import com.liferay.portal.kernel.util.HashCodeFactory;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class HashCodeFactoryImpl implements HashCodeFactory {

	@Override
	public HashCode getHashCode() {
		return new HashCodeImpl();
	}

	@Override
	public HashCode getHashCode(
		int initialNonZeroOddNumber, int multiplierNonZeroOddNumber) {

		return new HashCodeImpl(
			initialNonZeroOddNumber, multiplierNonZeroOddNumber);
	}

}