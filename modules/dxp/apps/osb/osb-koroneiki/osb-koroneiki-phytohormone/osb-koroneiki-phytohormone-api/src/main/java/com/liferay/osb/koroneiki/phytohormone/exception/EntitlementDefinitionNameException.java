/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class EntitlementDefinitionNameException extends PortalException {

	public EntitlementDefinitionNameException() {
	}

	public EntitlementDefinitionNameException(String msg) {
		super(msg);
	}

	public EntitlementDefinitionNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public EntitlementDefinitionNameException(Throwable cause) {
		super(cause);
	}

	public static class MustNotBeDuplicate
		extends EntitlementDefinitionNameException {

		public MustNotBeDuplicate(long classNameId, String name) {
			super(
				String.format(
					"An entitlement definition with class name ID %s and " +
						"name %s is already in use",
					classNameId, name));
		}

	}

}