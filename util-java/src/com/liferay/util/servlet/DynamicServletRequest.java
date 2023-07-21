/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Newton (6.2.x), moved to {@link
 *             com.liferay.portal.kernel.servlet.DynamicServletRequest}
 */
@Deprecated
public class DynamicServletRequest
	extends com.liferay.portal.kernel.servlet.DynamicServletRequest {

	public DynamicServletRequest(HttpServletRequest httpServletRequest) {
		super(httpServletRequest);
	}

	public DynamicServletRequest(
		HttpServletRequest httpServletRequest, boolean inherit) {

		super(httpServletRequest, inherit);
	}

	public DynamicServletRequest(
		HttpServletRequest httpServletRequest, Map<String, String[]> params) {

		super(httpServletRequest, params);
	}

	public DynamicServletRequest(
		HttpServletRequest httpServletRequest, Map<String, String[]> params,
		boolean inherit) {

		super(httpServletRequest, params, inherit);
	}

}