/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.distributed.messaging.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchQueuedMessageException extends NoSuchModelException {

	public NoSuchQueuedMessageException() {
	}

	public NoSuchQueuedMessageException(String msg) {
		super(msg);
	}

	public NoSuchQueuedMessageException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchQueuedMessageException(Throwable throwable) {
		super(throwable);
	}

}