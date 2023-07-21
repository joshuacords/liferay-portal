/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.exception;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletDefaultPreferencesException extends PortalException {

	public PortletDefaultPreferencesException() {
	}

	public PortletDefaultPreferencesException(String msg) {
		super(msg);
	}

	public PortletDefaultPreferencesException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public PortletDefaultPreferencesException(Throwable cause) {
		super(cause);
	}

}