/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.taproot.web.internal.portlet.action;

import com.liferay.osb.koroneiki.root.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.koroneiki.taproot.constants.TaprootPortletKeys;
import com.liferay.osb.koroneiki.taproot.exception.NoSuchContactException;
import com.liferay.osb.koroneiki.taproot.model.Contact;
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
		"mvc.command.name=/accounts_admin/assign_account_contact"
	},
	service = MVCActionCommand.class
)
public class AssignAccountContactMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			long accountId = ParamUtil.getLong(actionRequest, "accountId");

			String emailAddress = ParamUtil.getString(
				actionRequest, "emailAddress");

			Contact contact =
				_contactIdentityProvider.fetchContactByEmailAddress(
					emailAddress);

			if (contact == null) {
				throw new NoSuchContactException();
			}

			long contactRoleId = ParamUtil.getLong(
				actionRequest, "contactRoleId");

			_contactAccountRoleService.addContactAccountRole(
				contact.getContactId(), accountId, contactRoleId);

			sendRedirect(actionRequest, actionResponse);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw exception;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssignAccountContactMVCActionCommand.class);

	@Reference
	private ContactAccountRoleService _contactAccountRoleService;

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _contactIdentityProvider;

}