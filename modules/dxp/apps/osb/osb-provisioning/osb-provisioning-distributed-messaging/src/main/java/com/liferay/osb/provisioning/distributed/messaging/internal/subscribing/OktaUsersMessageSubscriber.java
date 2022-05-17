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

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.distributed.messaging.internal.constants.KoroneikiConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.util.CustomerPortalRelease;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "topic.pattern=okta-users",
	service = OktaUsersMessageSubscriber.class
)
public class OktaUsersMessageSubscriber extends BaseMessageSubscriber {

	@Override
	protected void doParse(JSONObject jsonObject) throws Exception {
		String eventType = jsonObject.getString("eventType");

		if (eventType.equals(_EVENT_TYPE_DEACTIVATE)) {
			_unassignContact(jsonObject.getJSONObject("user"));
		}
		else if (eventType.equals(_EVENT_TYPE_GROUP_ADD)) {
			if (_isGroupEmployee(jsonObject)) {
				_addEmployee(jsonObject.getJSONObject("user"));
			}
		}
		else if (eventType.equals(_EVENT_TYPE_GROUP_REMOVE)) {
			if (_isGroupEmployee(jsonObject)) {
				_unassignContact(jsonObject.getJSONObject("user"));
			}
		}
		else if (eventType.equals(_EVENT_TYPE_UPDATE_PASSWORD) ||
				 eventType.equals(_EVENT_TYPE_UPDATE_PROFILE)) {

			_updateContact(jsonObject.getJSONObject("user"));
		}
	}

	private void _addEmployee(JSONObject jsonObject) throws Exception {
		ContactRole contactRole = _contactRoleWebService.getContactRole(
			ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
			ContactRoleConstants.NAME_MEMBER);

		JSONObject profileJSONObject = jsonObject.getJSONObject("profile");

		_accountWebService.assignContactRolesByEmailAddress(
			StringPool.BLANK, StringPool.BLANK,
			KoroneikiConstants.ACCOUNT_KEY_LIFERAY_INC,
			profileJSONObject.getString("email"),
			new String[] {contactRole.getKey()});
	}

	private Contact _fetchContact(JSONObject jsonObject) throws Exception {
		String uuid = jsonObject.getString("uuid");

		if (Validator.isNull(uuid)) {
			return null;
		}

		return _contactWebService.fetchContactByUuid(uuid);
	}

	private boolean _isGroupEmployee(JSONObject jsonObject) {
		JSONObject groupJSONObject = jsonObject.getJSONObject("group");

		String name = groupJSONObject.getString("displayName");

		if (name.equals(_GROUP_NAME_EMPLOYEES)) {
			return true;
		}

		return false;
	}

	private void _unassignContact(JSONObject jsonObject) throws Exception {
		JSONObject profileJSONObject = jsonObject.getJSONObject("profile");

		Contact contact = _contactWebService.fetchContactByEmailAddress(
			profileJSONObject.getString("email"));

		if ((contact == null) || ArrayUtil.isEmpty(contact.getAccounts())) {
			return;
		}

		for (Account account : contact.getAccounts()) {
			_accountWebService.unassignCustomerContact(
				StringPool.BLANK, StringPool.BLANK, account.getKey(),
				contact.getEmailAddress());

			_accountWebService.unassignWorkerContact(
				StringPool.BLANK, StringPool.BLANK, account.getKey(),
				contact.getEmailAddress());
		}
	}

	private void _updateContact(JSONObject jsonObject) throws Exception {
		JSONObject profileJSONObject = jsonObject.getJSONObject("profile");

		Contact contact = _fetchContact(profileJSONObject);

		if (contact == null) {
			return;
		}

		contact.setEmailAddress(profileJSONObject.getString("email"));
		contact.setFirstName(profileJSONObject.getString("firstName"));
		contact.setLastName(profileJSONObject.getString("lastName"));

		String status = jsonObject.getString("status");

		if (status.equals(_STATUS_NAME_ACTIVE)) {
			if (!contact.getEmailAddressVerified()) {
				_customerPortalRelease.sendContactVerifiedWelcomeEmail(contact);
			}

			contact.setEmailAddressVerified(true);
		}

		_contactWebService.updateContactByUuid(
			StringPool.BLANK, StringPool.BLANK, contact.getUuid(), contact);
	}

	private static final String _EVENT_TYPE_DEACTIVATE =
		"user.lifecycle.deactivate";

	private static final String _EVENT_TYPE_GROUP_ADD =
		"group.user_membership.add";

	private static final String _EVENT_TYPE_GROUP_REMOVE =
		"group.user_membership.remove";

	private static final String _EVENT_TYPE_UPDATE_PASSWORD =
		"user.account.update_password";

	private static final String _EVENT_TYPE_UPDATE_PROFILE =
		"user.account.update_profile";

	private static final String _GROUP_NAME_EMPLOYEES = "Employees";

	private static final String _STATUS_NAME_ACTIVE = "ACTIVE";

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private CustomerPortalRelease _customerPortalRelease;

}