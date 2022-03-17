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

import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.distributed.messaging.internal.constants.LegacyConstants;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.AccountWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true, property = "topic.pattern=entity.organization.assigned",
	service = OrganizationAssignmentMessageSubscriber.class
)
public class OrganizationAssignmentMessageSubscriber
	extends BaseMessageSubscriber {

	@Override
	protected void doParse(JSONObject jsonObject) throws Exception {
		JSONObject organizationJSONObject = jsonObject.getJSONObject(
			"organization");

		long organizationId = organizationJSONObject.getLong("organizationId");

		if (organizationId != LegacyConstants.ORGANIZATION_LIFERAY_INC_ID) {
			return;
		}

		List<Account> accounts = _accountWebService.getAccounts(
			ExternalLinkDomain.WEB, ExternalLinkEntityName.WEB_ORGANIZATION,
			String.valueOf(organizationId), 1, 1000);

		JSONObject userJSONObject = jsonObject.getJSONObject("user");

		ContactRole contactRole = _contactRoleWebService.fetchContactRole(
			ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
			ContactRoleConstants.NAME_MEMBER);

		for (Account account : accounts) {
			_accountWebService.assignContactRolesByEmailAddress(
				StringPool.BLANK, StringPool.BLANK, account.getKey(),
				userJSONObject.getString("emailAddress"),
				new String[] {contactRole.getKey()});
		}
	}

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

}