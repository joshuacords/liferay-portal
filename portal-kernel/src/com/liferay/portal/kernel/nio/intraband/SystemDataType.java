/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband;

/**
 * @author Shuyang Zhou
 */
public enum SystemDataType {

	MAILBOX((byte)3), MESSAGE((byte)2), PROXY((byte)1), RPC((byte)0);

	public byte getValue() {
		return _value;
	}

	private SystemDataType(byte value) {
		_value = value;
	}

	private final byte _value;

}