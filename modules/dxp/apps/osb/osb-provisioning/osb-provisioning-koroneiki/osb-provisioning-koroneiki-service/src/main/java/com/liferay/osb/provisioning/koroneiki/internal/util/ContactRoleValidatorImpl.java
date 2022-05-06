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

package com.liferay.osb.provisioning.koroneiki.internal.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.provisioning.exception.RequiredContactRoleException;
import com.liferay.osb.provisioning.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.provisioning.koroneiki.validator.ContactRoleValidator;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.osb.provisioning.search.FilterQuery;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ContactRoleValidator.class)
public class ContactRoleValidatorImpl implements ContactRoleValidator {

	public void validateAdminContactRoleUnassignment(
			String accountKey, String emailAddress)
		throws Exception {

		ContactRole partnerManagerContactRole =
			_contactRoleWebService.getContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				ContactRoleConstants.NAME_PARTNER_MANAGER);
		ContactRole supportAdministratorContactRole =
			_contactRoleWebService.getContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(),
				ContactRoleConstants.NAME_SUPPORT_ADMINISTRATOR);

		String[] accountKeysContactRoleKeys = {
			accountKey + "_" + partnerManagerContactRole.getKey(),
			accountKey + "_" + supportAdministratorContactRole.getKey()
		};

		FilterQuery filterQuery = new FilterQuery();

		filterQuery.addLambdaEquals(
			true, "accountKeysContactRoleKeys", accountKeysContactRoleKeys);
		filterQuery.addEquals(true, "emailAddress", emailAddress, true);

		List<Contact> contacts = _contactWebService.search(
			StringPool.BLANK, filterQuery, 1, 1, StringPool.BLANK);

		if (contacts.isEmpty()) {
			throw new RequiredContactRoleException();
		}
	}

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

}