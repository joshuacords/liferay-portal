/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.web.internal.portlet.action;

import com.liferay.commerce.data.integration.constants.CommerceDataIntegrationPortletKeys;
import com.liferay.commerce.data.integration.process.type.ProcessTypeRegistry;
import com.liferay.commerce.data.integration.service.CommerceDataIntegrationProcessService;
import com.liferay.commerce.data.integration.trigger.CommerceDataIntegrationProcessTriggerHelper;
import com.liferay.commerce.data.integration.web.internal.display.context.CommerceDataIntegrationProcessDisplayContext;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"javax.portlet.name=" + CommerceDataIntegrationPortletKeys.COMMERCE_DATA_INTEGRATION,
		"mvc.command.name=editCommerceDataIntegrationProcess"
	},
	service = MVCRenderCommand.class
)
public class EditCommerceDataIntegrationProcessRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		CommerceDataIntegrationProcessDisplayContext
			commerceDataIntegrationProcessDisplayContext =
				new CommerceDataIntegrationProcessDisplayContext(
					_commerceDataIntegrationProcessScheduledTaskHelper,
					_commerceDataIntegrationProcessService,
					_processTypeRegistry, renderRequest);

		renderRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT,
			commerceDataIntegrationProcessDisplayContext);

		return "/edit_process.jsp";
	}

	@Reference
	private CommerceDataIntegrationProcessTriggerHelper
		_commerceDataIntegrationProcessScheduledTaskHelper;

	@Reference
	private CommerceDataIntegrationProcessService
		_commerceDataIntegrationProcessService;

	@Reference
	private ProcessTypeRegistry _processTypeRegistry;

}