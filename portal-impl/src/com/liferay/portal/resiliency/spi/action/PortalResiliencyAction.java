/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.resiliency.spi.action;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.ActionResult;
import com.liferay.portal.kernel.portlet.PortletContainer;
import com.liferay.portal.kernel.portlet.PortletContainerUtil;
import com.liferay.portal.kernel.resiliency.spi.agent.SPIAgent;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.resiliency.spi.agent.SPIAgentRequest;
import com.liferay.portal.resiliency.spi.agent.SPIAgentResponse;
import com.liferay.portal.struts.Action;
import com.liferay.portal.struts.model.ActionForward;
import com.liferay.portal.struts.model.ActionMapping;

import java.util.List;

import javax.portlet.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Shuyang Zhou
 */
public class PortalResiliencyAction implements Action {

	@Override
	public ActionForward execute(
			ActionMapping actionMapping, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		SPIAgentRequest spiAgentRequest =
			(SPIAgentRequest)httpServletRequest.getAttribute(
				WebKeys.SPI_AGENT_REQUEST);

		HttpSession session = httpServletRequest.getSession();

		spiAgentRequest.populateSessionAttributes(session);

		PrincipalThreadLocal.setPassword(
			(String)session.getAttribute(WebKeys.USER_PASSWORD));

		try {
			_execute(httpServletRequest, httpServletResponse);
		}
		finally {
			SPIAgentResponse spiAgentResponse =
				(SPIAgentResponse)httpServletRequest.getAttribute(
					WebKeys.SPI_AGENT_RESPONSE);

			spiAgentResponse.captureRequestSessionAttributes(
				httpServletRequest);

			httpServletRequest.setAttribute(
				WebKeys.PORTAL_RESILIENCY_ACTION, Boolean.TRUE);
		}

		return null;
	}

	private void _execute(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		PortletContainer portletContainer =
			PortletContainerUtil.getPortletContainer();

		Portlet portlet = (Portlet)httpServletRequest.getAttribute(
			WebKeys.SPI_AGENT_PORTLET);

		String portletId = ParamUtil.getString(httpServletRequest, "p_p_id");

		if (portletId.equals(portlet.getPortletId())) {
			portletContainer.preparePortlet(httpServletRequest, portlet);
		}

		SPIAgent.Lifecycle lifecycle =
			(SPIAgent.Lifecycle)httpServletRequest.getAttribute(
				WebKeys.SPI_AGENT_LIFECYCLE);

		if (lifecycle == SPIAgent.Lifecycle.ACTION) {

			// Action and event requests can make a transient change to a
			// layout's type settings. The fix should be done via a regular
			// request attribute, but to avoid a massive refactor, the SPI
			// simply sends back the modified layout type settings to the MPI

			Layout requestLayout = (Layout)httpServletRequest.getAttribute(
				WebKeys.LAYOUT);

			String typeSettings = requestLayout.getTypeSettings();

			ActionResult actionResult = portletContainer.processAction(
				httpServletRequest, httpServletResponse, portlet);

			String newTypeSettings = requestLayout.getTypeSettings();

			if (!newTypeSettings.equals(typeSettings)) {
				httpServletRequest.setAttribute(
					WebKeys.SPI_AGENT_LAYOUT_TYPE_SETTINGS, newTypeSettings);
			}

			httpServletRequest.setAttribute(
				WebKeys.SPI_AGENT_ACTION_RESULT, actionResult);
		}
		else if (lifecycle == SPIAgent.Lifecycle.EVENT) {
			Layout requestLayout = (Layout)httpServletRequest.getAttribute(
				WebKeys.LAYOUT);

			String typeSettings = requestLayout.getTypeSettings();

			Layout layout = (Layout)httpServletRequest.getAttribute(
				WebKeys.SPI_AGENT_LAYOUT);

			Event event = (Event)httpServletRequest.getAttribute(
				WebKeys.SPI_AGENT_EVENT);

			List<Event> events = portletContainer.processEvent(
				httpServletRequest, httpServletResponse, portlet, layout,
				event);

			String newTypeSettings = requestLayout.getTypeSettings();

			if (!newTypeSettings.equals(typeSettings)) {
				httpServletRequest.setAttribute(
					WebKeys.SPI_AGENT_LAYOUT_TYPE_SETTINGS, newTypeSettings);
			}

			httpServletRequest.setAttribute(
				WebKeys.SPI_AGENT_EVENT_RESULT, events);
		}
		else if (lifecycle == SPIAgent.Lifecycle.RENDER) {
			portletContainer.render(
				httpServletRequest, httpServletResponse, portlet);
		}
		else if (lifecycle == SPIAgent.Lifecycle.RESOURCE) {
			portletContainer.serveResource(
				httpServletRequest, httpServletResponse, portlet);
		}
		else {
			throw new IllegalArgumentException("Unkown lifecycle " + lifecycle);
		}
	}

}