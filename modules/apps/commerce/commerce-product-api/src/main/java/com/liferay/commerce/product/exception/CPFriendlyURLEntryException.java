/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CPFriendlyURLEntryException extends PortalException {

	public static final int ADJACENT_SLASHES = 3;

	public static final int ENDS_WITH_SLASH = 2;

	public static final int INVALID_CHARACTERS = 4;

	public static final int TOO_DEEP = 5;

	public static final int TOO_LONG = 1;

	public CPFriendlyURLEntryException(int type) {
		_type = type;
	}

	public int getType() {
		return _type;
	}

	private final int _type;

}