/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Bunyan (6.0.x), moved to {@link
 *             com.liferay.portal.kernel.servlet.ProtectedServletRequest}
 */
@Deprecated
public class ProtectedServletRequest
	extends com.liferay.portal.kernel.servlet.ProtectedServletRequest {

	public ProtectedServletRequest(
		HttpServletRequest httpServletRequest, String remoteUser) {

		super(httpServletRequest, remoteUser);
	}

}