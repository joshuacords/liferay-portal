/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Mika Koivisto
 */
public interface StrutsAction {

	public String execute(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception;

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #execute(HttpServletRequest, HttpServletResponse)}
	 */
	@Deprecated
	public default String execute(
			StrutsAction originalStrutsAction,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		return execute(httpServletRequest, httpServletResponse);
	}

}