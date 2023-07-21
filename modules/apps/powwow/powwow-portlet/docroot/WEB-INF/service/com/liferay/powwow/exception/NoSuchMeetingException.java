/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Shinn Lok
 */
public class NoSuchMeetingException extends NoSuchModelException {

	public NoSuchMeetingException() {
	}

	public NoSuchMeetingException(String msg) {
		super(msg);
	}

	public NoSuchMeetingException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchMeetingException(Throwable cause) {
		super(cause);
	}

}