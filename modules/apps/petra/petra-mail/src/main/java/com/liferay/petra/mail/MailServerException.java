/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.mail;

import com.liferay.portal.kernel.exception.NestableException;

/**
 * @author Alexander Chow
 * @see    com.liferay.util.mail.MailServerException
 */
public class MailServerException extends NestableException {

	public MailServerException() {
	}

	public MailServerException(String msg) {
		super(msg);
	}

	public MailServerException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public MailServerException(Throwable cause) {
		super(cause);
	}

}