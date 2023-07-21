/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Yuanyuan Huang
 */
public class ProductBundleNameException extends PortalException {

	public ProductBundleNameException() {
	}

	public ProductBundleNameException(String msg) {
		super(msg);
	}

	public ProductBundleNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ProductBundleNameException(Throwable cause) {
		super(cause);
	}

	public static class MustNotBeDuplicate extends ProductBundleNameException {

		public MustNotBeDuplicate(String name) {
			super(
				String.format(
					"A product bundle with name %s is already in use", name));
		}

	}

}