/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.multi.factor.authentication.checker.headless;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tomas Polesovsky
 */
public interface HeadlessMFAChecker {

	public boolean isHeadlessVerified(
		HttpServletRequest httpServletRequest, long userId);

	public boolean verifyHeadlessRequest(
		HttpServletRequest httpServletRequest, long userId);

}