/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Alessio Antonio Rendina
 */
public class NoSuchDataIntegrationProcessException
	extends NoSuchModelException {

	public NoSuchDataIntegrationProcessException() {
	}

	public NoSuchDataIntegrationProcessException(String msg) {
		super(msg);
	}

	public NoSuchDataIntegrationProcessException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchDataIntegrationProcessException(Throwable cause) {
		super(cause);
	}

}