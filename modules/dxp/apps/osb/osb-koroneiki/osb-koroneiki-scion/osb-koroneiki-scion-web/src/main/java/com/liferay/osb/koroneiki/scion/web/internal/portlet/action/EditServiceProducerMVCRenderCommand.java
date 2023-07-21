/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.web.internal.portlet.action;

import com.liferay.osb.koroneiki.scion.constants.ScionPortletKeys;
import com.liferay.osb.koroneiki.scion.constants.ScionWebKeys;
import com.liferay.osb.koroneiki.scion.service.ServiceProducerLocalService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + ScionPortletKeys.SERVICE_PRODUCERS_ADMIN,
		"mvc.command.name=/service_producers_admin/edit_service_producer"
	},
	service = MVCRenderCommand.class
)
public class EditServiceProducerMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long serviceProducerId = ParamUtil.getLong(
				renderRequest, "serviceProducerId");

			if (serviceProducerId > 0) {
				renderRequest.setAttribute(
					ScionWebKeys.SERVICE_PRODUCER,
					_serviceProducerLocalService.getServiceProducer(
						serviceProducerId));
			}

			return "/service_producers_admin/edit_service_producer.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			return "/service_producers_admin/error.jsp";
		}
	}

	@Reference
	private ServiceProducerLocalService _serviceProducerLocalService;

}