/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.service.ContactAccountRoleService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + TaprootPortletKeys.ACCOUNTS_ADMIN,
		"mvc.command.name=/accounts_admin/assign_account_contact_roles"
	},
	service = MVCActionCommand.class
)
public class AssignAccountContactRolesMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			long contactId = ParamUtil.getLong(actionRequest, "contactId");
			long accountId = ParamUtil.getLong(actionRequest, "accountId");
			long[] addContactRoleIds = ParamUtil.getLongValues(
				actionRequest, "addContactRoleIds");
			long[] deleteContactRoleIds = ParamUtil.getLongValues(
				actionRequest, "deleteContactRoleIds");

			for (long contactRoleId : addContactRoleIds) {
				_contactAccountRoleService.addContactAccountRole(
					contactId, accountId, contactRoleId);
			}

			for (long contactRoleId : deleteContactRoleIds) {
				_contactAccountRoleService.deleteContactAccountRole(
					contactId, accountId, contactRoleId);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssignAccountContactRolesMVCActionCommand.class);

	@Reference
	private ContactAccountRoleService _contactAccountRoleService;

}