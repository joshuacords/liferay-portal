/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class TeamNameException extends PortalException {

	public TeamNameException() {
	}

	public TeamNameException(String msg) {
		super(msg);
	}

	public TeamNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public TeamNameException(Throwable cause) {
		super(cause);
	}

	public static class MustNotBeDuplicate extends TeamNameException {

		public MustNotBeDuplicate(String name, String accountName) {
			super(
				String.format(
					"A team with name %s on account %s is already in use", name,
					accountName));
		}

	}

}