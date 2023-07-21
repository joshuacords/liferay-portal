/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.checker.headless.visitor;

import com.liferay.multi.factor.authentication.checker.headless.HeadlessMFAChecker;
import com.liferay.multi.factor.authentication.checker.visitor.BaseMFACheckerVisitor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Carlos Sierra Andrés
 */
public class IsHeadlessVerifiedMFACheckerVisitor extends BaseMFACheckerVisitor {

	public IsHeadlessVerifiedMFACheckerVisitor(
		HttpServletRequest httpServletRequest, long userId) {

		super(
			HeadlessMFAChecker.class,
			headlessMFAChecker -> headlessMFAChecker.isHeadlessVerified(
				httpServletRequest, userId));
	}

}