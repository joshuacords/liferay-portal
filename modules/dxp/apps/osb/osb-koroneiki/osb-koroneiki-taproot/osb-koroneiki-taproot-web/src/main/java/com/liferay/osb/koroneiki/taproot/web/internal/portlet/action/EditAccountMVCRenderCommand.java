/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.constants.TaprootWebKeys;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
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
		"javax.portlet.name=" + TaprootPortletKeys.ACCOUNTS_ADMIN,
		"mvc.command.name=/accounts_admin/edit_account"
	},
	service = MVCRenderCommand.class
)
public class EditAccountMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			long accountId = ParamUtil.getLong(renderRequest, "accountId");

			if (accountId > 0) {
				renderRequest.setAttribute(
					TaprootWebKeys.ACCOUNT,
					_accountLocalService.getAccount(accountId));
			}

			String tabs1 = ParamUtil.getString(renderRequest, "tabs1");

			if (tabs1.equals("addresses")) {
				return "/accounts_admin/edit_account_addresses.jsp";
			}
			else if (tabs1.equals("assigned-teams")) {
				return "/accounts_admin/edit_account_assigned_teams.jsp";
			}
			else if (tabs1.equals("child-accounts")) {
				return "/accounts_admin/edit_account_child_accounts.jsp";
			}
			else if (tabs1.equals("consumptions")) {
				return "/accounts_admin/edit_account_consumptions.jsp";
			}
			else if (tabs1.equals("customer-contacts")) {
				renderRequest.setAttribute(
					TaprootWebKeys.CONTACT_ROLE_TYPE,
					ContactRole.Type.ACCOUNT_CUSTOMER.toString());

				return "/accounts_admin/edit_account_contact_roles.jsp";
			}
			else if (tabs1.equals("entitlements")) {
				return "/accounts_admin/edit_account_entitlements.jsp";
			}
			else if (tabs1.equals("external-links")) {
				return "/accounts_admin/edit_account_external_links.jsp";
			}
			else if (tabs1.equals("notes")) {
				return "/accounts_admin/edit_account_notes.jsp";
			}
			else if (tabs1.equals("purchases")) {
				return "/accounts_admin/edit_account_purchases.jsp";
			}
			else if (tabs1.equals("teams")) {
				return "/accounts_admin/edit_account_teams.jsp";
			}
			else if (tabs1.equals("worker-contacts")) {
				renderRequest.setAttribute(
					TaprootWebKeys.CONTACT_ROLE_TYPE,
					ContactRole.Type.ACCOUNT_WORKER.toString());

				return "/accounts_admin/edit_account_contact_roles.jsp";
			}

			return "/accounts_admin/edit_account.jsp";
		}
		catch (Exception exception) {
			SessionErrors.add(renderRequest, exception.getClass());

			return "/accounts_admin/error.jsp";
		}
	}

	@Reference
	private AccountLocalService _accountLocalService;

}