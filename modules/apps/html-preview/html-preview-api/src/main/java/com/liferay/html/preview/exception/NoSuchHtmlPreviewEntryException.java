/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.html.preview.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchHtmlPreviewEntryException extends NoSuchModelException {

	public NoSuchHtmlPreviewEntryException() {
	}

	public NoSuchHtmlPreviewEntryException(String msg) {
		super(msg);
	}

	public NoSuchHtmlPreviewEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchHtmlPreviewEntryException(Throwable cause) {
		super(cause);
	}

}