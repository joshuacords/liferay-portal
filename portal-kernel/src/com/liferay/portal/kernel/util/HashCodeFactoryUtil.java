/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class HashCodeFactoryUtil {

	public static HashCode getHashCode() {
		return getHashCodeFactory().getHashCode();
	}

	public static HashCode getHashCode(
		int initialNonZeroOddNumber, int multiplierNonZeroOddNumber) {

		return getHashCodeFactory().getHashCode(
			initialNonZeroOddNumber, multiplierNonZeroOddNumber);
	}

	public static HashCodeFactory getHashCodeFactory() {
		return _hashCodeFactory;
	}

	public void setHashCodeFactory(HashCodeFactory hashCodeFactory) {
		_hashCodeFactory = hashCodeFactory;
	}

	private static HashCodeFactory _hashCodeFactory;

}