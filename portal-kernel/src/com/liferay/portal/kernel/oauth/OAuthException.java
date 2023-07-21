/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.oauth;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuthException extends PortalException {

	public OAuthException() {
	}

	public OAuthException(String msg) {
		super(msg);
	}

	public OAuthException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public OAuthException(Throwable cause) {
		super(cause);
	}

}