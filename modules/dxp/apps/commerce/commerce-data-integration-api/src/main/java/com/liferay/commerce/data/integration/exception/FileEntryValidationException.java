/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Riccardo Ferrari
 */
public class FileEntryValidationException extends PortalException {

	public FileEntryValidationException() {
	}

	public FileEntryValidationException(String msg) {
		super(msg);
	}

	public FileEntryValidationException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public FileEntryValidationException(Throwable cause) {
		super(cause);
	}

}