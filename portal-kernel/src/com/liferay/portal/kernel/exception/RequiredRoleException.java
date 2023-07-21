/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.exception;

/**
 * @author Brian Wing Shun Chan
 * @author Roberto Díaz
 */
public class RequiredRoleException extends PortalException {

	public RequiredRoleException() {
	}

	public RequiredRoleException(String msg) {
		super(msg);
	}

	public RequiredRoleException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RequiredRoleException(Throwable cause) {
		super(cause);
	}

	public static class MustNotRemoveLastAdministator
		extends RequiredRoleException {

		public MustNotRemoveLastAdministator() {
		}

		public MustNotRemoveLastAdministator(String msg) {
			super(msg);
		}

		public MustNotRemoveLastAdministator(String msg, Throwable cause) {
			super(msg, cause);
		}

		public MustNotRemoveLastAdministator(Throwable cause) {
			super(cause);
		}

	}

	public static class MustNotRemoveUserRole extends RequiredRoleException {

		public MustNotRemoveUserRole() {
		}

		public MustNotRemoveUserRole(String msg) {
			super(msg);
		}

		public MustNotRemoveUserRole(String msg, Throwable cause) {
			super(msg, cause);
		}

		public MustNotRemoveUserRole(Throwable cause) {
			super(cause);
		}

	}

}