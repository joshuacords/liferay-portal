/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.notifications;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public class UnknownChannelException extends ChannelException {

	public UnknownChannelException() {
	}

	public UnknownChannelException(String msg) {
		super(msg);
	}

	public UnknownChannelException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public UnknownChannelException(Throwable cause) {
		super(cause);
	}

}