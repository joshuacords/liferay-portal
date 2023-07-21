/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.atom;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Igor Spasic
 */
public class AtomException extends PortalException {

	public AtomException(int errorCode) {
		super(String.valueOf(errorCode));

		_errorCode = errorCode;
	}

	public AtomException(int errorCode, Throwable cause) {
		super(String.valueOf(errorCode), cause);

		_errorCode = errorCode;
	}

	public AtomException(String msg) {
		super(msg);

		_errorCode = AtomCollectionAdapter.SC_INTERNAL_SERVER_ERROR;
	}

	public int getErrorCode() {
		return _errorCode;
	}

	private final int _errorCode;

}