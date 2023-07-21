/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.resiliency.spi.agent;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.ActionResult;
import com.liferay.portal.kernel.resiliency.PortalResiliencyException;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.resiliency.spi.agent.SPIAgent;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Collections;

import javax.portlet.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Shuyang Zhou
 */
public class ErrorSPIAgent implements SPIAgent {

	@Override
	public void destroy() {
	}

	@Override
	public void init(SPI spi) {
	}

	@Override
	public HttpServletRequest prepareRequest(
		HttpServletRequest httpServletRequest) {

		return httpServletRequest;
	}

	@Override
	public HttpServletResponse prepareResponse(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		return httpServletResponse;
	}

	@Override
	public void service(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws PortalResiliencyException {

		SPIAgent.Lifecycle lifecycle =
			(SPIAgent.Lifecycle)httpServletRequest.getAttribute(
				WebKeys.SPI_AGENT_LIFECYCLE);

		if (lifecycle.equals(SPIAgent.Lifecycle.ACTION)) {
			httpServletRequest.setAttribute(
				WebKeys.SPI_AGENT_ACTION_RESULT,
				new ActionResult(
					Collections.<Event>emptyList(), StringPool.SLASH));
		}
		else if (lifecycle.equals(SPIAgent.Lifecycle.EVENT)) {
			httpServletRequest.setAttribute(
				WebKeys.SPI_AGENT_EVENT_RESULT, Collections.emptyList());
		}
		else if (lifecycle.equals(SPIAgent.Lifecycle.RENDER)) {
			try {
				PrintWriter printWriter = httpServletResponse.getWriter();

				printWriter.write("<div class=\"alert alert-error\">");
				printWriter.write("SPI is temporarily unavailable.");
				printWriter.write("</div>");
			}
			catch (IOException ioException) {
				throw new PortalResiliencyException(ioException);
			}
		}
	}

	@Override
	public void transferResponse(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, Exception exception) {
	}

}