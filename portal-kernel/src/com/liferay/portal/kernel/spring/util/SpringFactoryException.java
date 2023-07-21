/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.spring.util;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class SpringFactoryException extends PortalException {

	public SpringFactoryException() {
	}

	public SpringFactoryException(String msg) {
		super(msg);
	}

	public SpringFactoryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public SpringFactoryException(Throwable cause) {
		super(cause);
	}

}