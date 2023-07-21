/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.rule;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Edward Han
 */
public class UnknownRuleHandlerException extends PortalException {

	public UnknownRuleHandlerException() {
	}

	public UnknownRuleHandlerException(String msg) {
		super(msg);
	}

	public UnknownRuleHandlerException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public UnknownRuleHandlerException(Throwable cause) {
		super(cause);
	}

}