/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phytohormone.constants.PhytohormonePortletKeys;
import com.liferay.osb.koroneiki.phytohormone.constants.PhytohormoneWebKeys;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalService;
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
		"mvc.command.name=/entitlement_definitions_admin/edit_entitlement_definition"
	},
	service = MVCRenderCommand.class
)
public class EditEntitlementDefinitionMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long entitlementDefinitionId = ParamUtil.getLong(
				renderRequest, "entitlementDefinitionId");

			if (entitlementDefinitionId > 0) {
				renderRequest.setAttribute(
					PhytohormoneWebKeys.ENTITLEMENT_DEFINITION,
					_entitlementDefinitionLocalService.getEntitlementDefinition(
						entitlementDefinitionId));
			}

			String tabs1 = ParamUtil.getString(renderRequest, "tabs1");

			if (tabs1.equals("external-links")) {
				return "/entitlement_definitions_admin" +
					"/edit_entitlement_definition_external_links.jsp";
			}

			return "/entitlement_definitions_admin" +
				"/edit_entitlement_definition.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			return "/entitlement_definitions_admin/error.jsp";
		}
	}

	@Reference
	private EntitlementDefinitionLocalService
		_entitlementDefinitionLocalService;

}