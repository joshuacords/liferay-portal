/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchAttachmentException extends NoSuchModelException {

	public NoSuchAttachmentException() {
	}

	public NoSuchAttachmentException(String msg) {
		super(msg);
	}

	public NoSuchAttachmentException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchAttachmentException(Throwable cause) {
		super(cause);
	}

}