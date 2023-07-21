/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency;

/**
 * @author Shuyang Zhou
 */
public class PortalResiliencyException extends Exception {

	public PortalResiliencyException(String message) {
		super(message);
	}

	public PortalResiliencyException(String message, Throwable cause) {
		super(message, cause);
	}

	public PortalResiliencyException(Throwable cause) {
		super(cause);
	}

}