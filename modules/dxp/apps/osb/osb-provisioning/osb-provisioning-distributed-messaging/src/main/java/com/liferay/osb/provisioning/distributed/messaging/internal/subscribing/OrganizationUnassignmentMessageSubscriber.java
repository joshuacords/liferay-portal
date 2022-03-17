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
import com.liferay.osb.provisioning.distributed.messaging.internal.constants.LegacyConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=entity.organization.unassigned",
	service = OrganizationUnassignmentMessageSubscriber.class
)
public class OrganizationUnassignmentMessageSubscriber
	extends BaseMessageSubscriber {

	@Override
	protected void doParse(JSONObject jsonObject) throws Exception {
		JSONObject organizationJSONObject = jsonObject.getJSONObject(
			"organization");

		long organizationId = organizationJSONObject.getLong("organizationId");

		if (organizationId != LegacyConstants.ORGANIZATION_LIFERAY_INC_ID) {
			return;
		}

		JSONObject userJSONObject = jsonObject.getJSONObject("user");

		Contact contact = _contactWebService.fetchContactByEmailAddress(
			userJSONObject.getString("emailAddress"));

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

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

}