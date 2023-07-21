/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class ExternalLinkEntityNameException extends PortalException {

	public ExternalLinkEntityNameException() {
	}

	public ExternalLinkEntityNameException(String msg) {
		super(msg);
	}

	public ExternalLinkEntityNameException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ExternalLinkEntityNameException(Throwable cause) {
		super(cause);
	}

}