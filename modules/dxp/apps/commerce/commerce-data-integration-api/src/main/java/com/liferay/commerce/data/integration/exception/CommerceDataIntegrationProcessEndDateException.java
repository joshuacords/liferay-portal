/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceDataIntegrationProcessEndDateException
	extends PortalException {

	public CommerceDataIntegrationProcessEndDateException() {
	}

	public CommerceDataIntegrationProcessEndDateException(String msg) {
		super(msg);
	}

	public CommerceDataIntegrationProcessEndDateException(
		String msg, Throwable cause) {

		super(msg, cause);
	}

	public CommerceDataIntegrationProcessEndDateException(Throwable cause) {
		super(cause);
	}

}