/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchAccountFieldException extends NoSuchModelException {

	public NoSuchAccountFieldException() {
	}

	public NoSuchAccountFieldException(String msg) {
		super(msg);
	}

	public NoSuchAccountFieldException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchAccountFieldException(Throwable throwable) {
		super(throwable);
	}

}