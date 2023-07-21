/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.constants.ProvisioningWebKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

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
		"javax.portlet.name=" + ProvisioningPortletKeys.ACCOUNTS,
		"mvc.command.name=/accounts/assign_contacts"
	},
	service = MVCRenderCommand.class
)
public class AssignAccountContactsMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		String accountKey = ParamUtil.getString(renderRequest, "accountKey");

		String uuid = ParamUtil.getString(renderRequest, "uuid");

		try {
			renderRequest.setAttribute(
				ProvisioningWebKeys.ACCOUNT,
				_accountWebService.getAccount(accountKey));

			if (Validator.isNotNull(uuid)) {
				renderRequest.setAttribute(
					ProvisioningWebKeys.CONTACT,
					_contactWebService.getContactByUuid(uuid));
			}

			return "/accounts/assign_contacts.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass(), exception);

			return "/common/error.jsp";
		}
	}

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactWebService _contactWebService;

}