/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.nio.intraband.mailbox;

/**
 * @author Shuyang Zhou
 */
public class MailboxException extends Exception {

	public MailboxException() {
	}

	public MailboxException(String message) {
		super(message);
	}

	public MailboxException(String message, Throwable cause) {
		super(message, cause);
	}

	public MailboxException(Throwable cause) {
		super(cause);
	}

}