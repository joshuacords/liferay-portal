/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phytohormone.constants.PhytohormonePortletKeys;
import com.liferay.osb.koroneiki.root.constants.RootWebKeys;
import com.liferay.osb.koroneiki.root.service.ExternalLinkLocalService;
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
		"javax.portlet.name=" + PhytohormonePortletKeys.ENTITLEMENT_DEFINITIONS_ADMIN,
		"mvc.command.name=/edit_external_link"
	},
	service = MVCRenderCommand.class
)
public class EditExternalLinkMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long externalLinkId = ParamUtil.getLong(
				renderRequest, "externalLinkId");

			if (externalLinkId > 0) {
				renderRequest.setAttribute(
					RootWebKeys.EXTERNAL_LINK,
					_externalLinkLocalService.getExternalLink(externalLinkId));
			}

			return "/edit_external_link.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			throw new PortletException(exception);
		}
	}

	@Reference
	private ExternalLinkLocalService _externalLinkLocalService;

}