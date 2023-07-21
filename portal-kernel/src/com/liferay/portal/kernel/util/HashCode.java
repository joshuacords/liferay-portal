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
public interface HashCode {

	public HashCode append(boolean value);

	public HashCode append(boolean[] value);

	public HashCode append(byte value);

	public HashCode append(byte[] value);

	public HashCode append(char value);

	public HashCode append(char[] value);

	public HashCode append(double value);

	public HashCode append(double[] value);

	public HashCode append(float value);

	public HashCode append(float[] value);

	public HashCode append(int value);

	public HashCode append(int[] value);

	public HashCode append(long value);

	public HashCode append(long[] value);

	public HashCode append(Object value);

	public HashCode append(Object[] value);

	public HashCode append(short value);

	public HashCode append(short[] value);

	public HashCode appendSuper(int superHashCode);

	public int toHashCode();

}