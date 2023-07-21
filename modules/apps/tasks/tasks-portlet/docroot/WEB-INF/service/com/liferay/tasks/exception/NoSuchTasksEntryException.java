/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.tasks.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchTasksEntryException extends NoSuchModelException {

	public NoSuchTasksEntryException() {
	}

	public NoSuchTasksEntryException(String msg) {
		super(msg);
	}

	public NoSuchTasksEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchTasksEntryException(Throwable cause) {
		super(cause);
	}

}