/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.constants.TaprootWebKeys;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
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
		"javax.portlet.name=" + TaprootPortletKeys.CONTACTS_ADMIN,
		"mvc.command.name=/contacts_admin/edit_contact"
	},
	service = MVCRenderCommand.class
)
public class EditContactMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long contactId = ParamUtil.getLong(renderRequest, "contactId");

			if (contactId > 0) {
				renderRequest.setAttribute(
					TaprootWebKeys.CONTACT,
					_contactLocalService.getContact(contactId));
			}

			String tabs1 = ParamUtil.getString(renderRequest, "tabs1");

			if (tabs1.equals("accounts")) {
				return "/contacts_admin/edit_contact_accounts.jsp";
			}
			else if (tabs1.equals("entitlements")) {
				return "/contacts_admin/edit_contact_entitlements.jsp";
			}
			else if (tabs1.equals("external-links")) {
				return "/contacts_admin/edit_contact_external_links.jsp";
			}

			return "/contacts_admin/edit_contact.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			return "/contacts_admin/error.jsp";
		}
	}

	@Reference
	private ContactLocalService _contactLocalService;

}