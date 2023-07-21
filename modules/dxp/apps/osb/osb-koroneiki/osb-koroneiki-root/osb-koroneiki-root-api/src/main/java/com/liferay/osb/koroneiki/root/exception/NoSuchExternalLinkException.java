/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchExternalLinkException extends NoSuchModelException {

	public NoSuchExternalLinkException() {
	}

	public NoSuchExternalLinkException(String msg) {
		super(msg);
	}

	public NoSuchExternalLinkException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchExternalLinkException(Throwable cause) {
		super(cause);
	}

}