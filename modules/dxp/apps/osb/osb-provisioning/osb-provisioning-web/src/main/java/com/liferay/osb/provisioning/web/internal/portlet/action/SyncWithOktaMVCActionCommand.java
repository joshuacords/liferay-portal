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

package com.liferay.osb.provisioning.web.internal.portlet.action;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.provisioning.constants.ProvisioningPortletKeys;
import com.liferay.osb.provisioning.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
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
		"javax.portlet.name=" + ProvisioningPortletKeys.USERS,
		"mvc.command.name=/users/sync_with_okta"
	},
	service = MVCActionCommand.class
)
public class SyncWithOktaMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			String contactUuid = ParamUtil.getString(
				actionRequest, "contactUuid");

			Contact contact = _contactWebService.getContactByUuid(contactUuid);

			_contactIdentityProvider.syncContact(contact);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			SessionErrors.add(actionRequest, exception.getClass());
		}

		sendRedirect(actionRequest, actionResponse);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SyncWithOktaMVCActionCommand.class);

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _contactIdentityProvider;

	@Reference
	private ContactWebService _contactWebService;

}