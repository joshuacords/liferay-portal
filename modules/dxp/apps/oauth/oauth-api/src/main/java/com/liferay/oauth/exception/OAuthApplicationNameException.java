/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Ivica Cardic
 */
public class OAuthApplicationNameException extends PortalException {

	public OAuthApplicationNameException() {
	}

	public OAuthApplicationNameException(String msg) {
		super(msg);
	}

	public OAuthApplicationNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public OAuthApplicationNameException(Throwable cause) {
		super(cause);
	}

}