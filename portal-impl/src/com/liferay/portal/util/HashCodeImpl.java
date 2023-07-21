/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.HashCode;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class HashCodeImpl implements HashCode {

	public HashCodeImpl() {
		_hashCodeBuilder = new HashCodeBuilder();
	}

	public HashCodeImpl(
		int initialNonZeroOddNumber, int multiplierNonZeroOddNumber) {

		_hashCodeBuilder = new HashCodeBuilder(
			initialNonZeroOddNumber, multiplierNonZeroOddNumber);
	}

	@Override
	public HashCode append(boolean value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(boolean[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(byte value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(byte[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(char value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(char[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(double value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(double[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(float value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(float[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(int value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(int[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(long value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(long[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(Object value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(Object[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(short value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode append(short[] value) {
		_hashCodeBuilder.append(value);

		return this;
	}

	@Override
	public HashCode appendSuper(int superHashCode) {
		_hashCodeBuilder.appendSuper(superHashCode);

		return this;
	}

	@Override
	public int toHashCode() {
		return _hashCodeBuilder.toHashCode();
	}

	private final HashCodeBuilder _hashCodeBuilder;

}