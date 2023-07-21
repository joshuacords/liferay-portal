/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Edward C. Han
 */
public class DuplicateRuleGroupInstanceException extends PortalException {

	public DuplicateRuleGroupInstanceException() {
	}

	public DuplicateRuleGroupInstanceException(String msg) {
		super(msg);
	}

	public DuplicateRuleGroupInstanceException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DuplicateRuleGroupInstanceException(Throwable cause) {
		super(cause);
	}

}