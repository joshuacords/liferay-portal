/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class ProductEntryNameException extends PortalException {

	public ProductEntryNameException() {
	}

	public ProductEntryNameException(String msg) {
		super(msg);
	}

	public ProductEntryNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ProductEntryNameException(Throwable cause) {
		super(cause);
	}

	public static class MustNotBeDuplicate extends ProductEntryNameException {

		public MustNotBeDuplicate(String name) {
			super(
				String.format(
					"A product with name %s is already in use", name));
		}

	}

}