/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.poller;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.poller.PollerHeader;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Edward Han
 */
public interface PollerRequestHandler {

	public PollerHeader getPollerHeader(String pollerRequestString);

	public JSONObject processRequest(
			HttpServletRequest httpServletRequest, String pollerRequestString)
		throws Exception;

}