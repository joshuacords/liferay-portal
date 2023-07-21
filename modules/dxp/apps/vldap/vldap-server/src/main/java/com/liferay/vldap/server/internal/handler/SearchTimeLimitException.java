/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.vldap.server.internal.handler;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class SearchTimeLimitException extends SystemException {

	public SearchTimeLimitException() {
	}

	public SearchTimeLimitException(String msg) {
		super(msg);
	}

	public SearchTimeLimitException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SearchTimeLimitException(Throwable cause) {
		super(cause);
	}

}