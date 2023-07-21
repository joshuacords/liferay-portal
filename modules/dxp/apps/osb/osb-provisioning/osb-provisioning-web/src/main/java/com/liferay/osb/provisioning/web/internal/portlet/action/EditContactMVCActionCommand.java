/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.problem.Problem;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	property = {
		"javax.portlet.name=" + ProvisioningPortletKeys.USERS,
		"mvc.command.name=/users/edit_contact"
	},
	service = MVCActionCommand.class
)
public class EditContactMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		try {
			updateContact(actionRequest, actionResponse, user);
		}
		catch (Problem.ProblemException problemException) {
			_log.error(problemException, problemException);

			SessionErrors.add(
				actionRequest, problemException.getClass(), problemException);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}

		sendRedirect(actionRequest, actionResponse);
	}

	protected void updateContact(
			ActionRequest actionRequest, ActionResponse actionResponse,
			User user)
		throws Exception {

		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");

		String uuid = ParamUtil.getString(actionRequest, "uuid");
		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String middleName = ParamUtil.getString(actionRequest, "middleName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");

		Contact contact = new Contact();

		contact.setEmailAddress(emailAddress);
		contact.setUuid(uuid);
		contact.setFirstName(firstName);
		contact.setMiddleName(middleName);
		contact.setLastName(lastName);

		_contactWebservice.updateContactByEmailAddress(
			user.getFullName(), user.getUuid(), emailAddress, contact);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditContactMVCActionCommand.class);

	@Reference
	private ContactWebService _contactWebservice;

}