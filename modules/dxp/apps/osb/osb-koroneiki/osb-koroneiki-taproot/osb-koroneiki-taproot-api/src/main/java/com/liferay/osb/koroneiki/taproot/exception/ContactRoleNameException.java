/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class ContactRoleNameException extends PortalException {

	public ContactRoleNameException() {
	}

	public ContactRoleNameException(String msg) {
		super(msg);
	}

	public ContactRoleNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ContactRoleNameException(Throwable cause) {
		super(cause);
	}

	public static class MustNotBeDuplicate extends ContactRoleNameException {

		public MustNotBeDuplicate(String name, String type) {
			super(
				String.format(
					"A contact role with name %s and type %s is already in use",
					name, type));
		}

	}

}