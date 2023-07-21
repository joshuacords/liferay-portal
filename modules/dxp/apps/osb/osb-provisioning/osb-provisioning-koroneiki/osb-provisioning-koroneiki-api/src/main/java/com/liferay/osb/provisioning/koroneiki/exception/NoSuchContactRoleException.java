/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Amos Fong
 */
public class NoSuchContactRoleException extends NoSuchModelException {

	public NoSuchContactRoleException() {
	}

	public NoSuchContactRoleException(String msg) {
		super(msg);
	}

	public NoSuchContactRoleException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchContactRoleException(Throwable cause) {
		super(cause);
	}

}