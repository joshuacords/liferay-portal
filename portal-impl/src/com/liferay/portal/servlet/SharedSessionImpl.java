/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.servlet.SharedSession;
import com.liferay.portal.resiliency.spi.agent.SPIAgentRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Raymond Augé
 */
public class SharedSessionImpl implements SharedSession {

	@Override
	public HttpSession getSharedSessionWrapper(
		HttpSession portalSession, HttpServletRequest httpServletRequest) {

		HttpSession portletSession = httpServletRequest.getSession();

		SPIAgentRequest.populatePortletSessionAttributes(
			httpServletRequest, portalSession);

		return new SharedSessionWrapper(portalSession, portletSession);
	}

}