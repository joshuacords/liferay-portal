/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.poller;

/**
 * @author Edward Han
 */
public class PollerWriterClosedException extends PollerException {

	public PollerWriterClosedException() {
	}

	public PollerWriterClosedException(String msg) {
		super(msg);
	}

	public PollerWriterClosedException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public PollerWriterClosedException(Throwable cause) {
		super(cause);
	}

}