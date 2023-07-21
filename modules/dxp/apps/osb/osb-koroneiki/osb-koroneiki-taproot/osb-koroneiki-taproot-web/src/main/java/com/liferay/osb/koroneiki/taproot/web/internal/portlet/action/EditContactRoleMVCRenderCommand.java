/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.constants.TaprootWebKeys;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
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
		"javax.portlet.name=" + TaprootPortletKeys.CONTACT_ROLES_ADMIN,
		"mvc.command.name=/contact_roles_admin/edit_contact_role"
	},
	service = MVCRenderCommand.class
)
public class EditContactRoleMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long contactRoleId = ParamUtil.getLong(
				renderRequest, "contactRoleId");

			if (contactRoleId > 0) {
				renderRequest.setAttribute(
					TaprootWebKeys.CONTACT_ROLE,
					_contactRoleLocalService.getContactRole(contactRoleId));
			}

			String tabs1 = ParamUtil.getString(renderRequest, "tabs1");

			if (tabs1.equals("external-links")) {
				return "/contact_roles_admin" +
					"/edit_contact_role_external_links.jsp";
			}

			return "/contact_roles_admin/edit_contact_role.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			return "/contact_roles_admin/error.jsp";
		}
	}

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

}