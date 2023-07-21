/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.checker.browser.visitor;

import com.liferay.multi.factor.authentication.checker.browser.BrowserMFAChecker;
import com.liferay.multi.factor.authentication.checker.visitor.BaseMFACheckerVisitor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Carlos Sierra Andrés
 */
public class IsBrowserVerifiedMFACheckerVisitor extends BaseMFACheckerVisitor {

	public IsBrowserVerifiedMFACheckerVisitor(
		HttpServletRequest httpServletRequest, long userId) {

		super(
			BrowserMFAChecker.class,
			browserMFAChecker -> browserMFAChecker.isBrowserVerified(
				httpServletRequest, userId));
	}

}