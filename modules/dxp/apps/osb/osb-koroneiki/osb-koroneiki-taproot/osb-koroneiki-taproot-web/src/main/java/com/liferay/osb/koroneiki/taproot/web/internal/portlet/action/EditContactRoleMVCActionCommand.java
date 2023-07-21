/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.exception.ContactRoleNameException;
import com.liferay.osb.koroneiki.taproot.exception.ContactRoleTypeException;
import com.liferay.osb.koroneiki.taproot.exception.NoSuchContactRoleException;
import com.liferay.osb.koroneiki.taproot.exception.RequiredContactRoleException;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
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
		"javax.portlet.name=" + TaprootPortletKeys.CONTACT_ROLES_ADMIN,
		"mvc.command.name=/contact_roles_admin/edit_contact_role"
	},
	service = MVCActionCommand.class
)
public class EditContactRoleMVCActionCommand extends BaseMVCActionCommand {

	protected void deleteContactRole(ActionRequest actionRequest)
		throws PortalException {

		long contactRoleId = ParamUtil.getLong(actionRequest, "contactRoleId");

		_contactRoleService.deleteContactRole(contactRoleId);
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.DELETE)) {
				deleteContactRole(actionRequest);
			}
			else {
				updateContactRole(actionRequest);
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			if (exception instanceof ContactRoleNameException ||
				exception instanceof ContactRoleTypeException ||
				exception instanceof NoSuchContactRoleException) {

				SessionErrors.add(actionRequest, exception.getClass());

				actionResponse.setRenderParameter(
					"mvcRenderCommandName",
					"/contact_roles_admin/edit_contact_role");
			}
			else if (exception instanceof RequiredContactRoleException) {
				SessionErrors.add(actionRequest, exception.getClass());

				sendRedirect(actionRequest, actionResponse);
			}
			else {
				throw exception;
			}
		}
	}

	protected void updateContactRole(ActionRequest actionRequest)
		throws PortalException {

		long contactRoleId = ParamUtil.getLong(actionRequest, "contactRoleId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");

		if (contactRoleId <= 0) {
			String type = ParamUtil.getString(actionRequest, "type");

			_contactRoleService.addContactRole(name, description, type);
		}
		else {
			_contactRoleService.updateContactRole(
				contactRoleId, name, description);
		}
	}

	@Reference
	private ContactRoleService _contactRoleService;

}