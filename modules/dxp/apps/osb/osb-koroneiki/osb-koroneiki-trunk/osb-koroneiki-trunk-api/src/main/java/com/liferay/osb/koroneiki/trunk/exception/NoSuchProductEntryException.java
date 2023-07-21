/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchProductEntryException extends NoSuchModelException {

	public NoSuchProductEntryException() {
	}

	public NoSuchProductEntryException(String msg) {
		super(msg);
	}

	public NoSuchProductEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchProductEntryException(Throwable cause) {
		super(cause);
	}

}