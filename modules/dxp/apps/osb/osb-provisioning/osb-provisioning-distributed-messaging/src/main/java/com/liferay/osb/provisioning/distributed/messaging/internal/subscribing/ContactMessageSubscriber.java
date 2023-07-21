/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactSerDes;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.service.LicenseKeyLocalService;
import com.liferay.osb.provisioning.subscription.model.SubscriptionEntry;
import com.liferay.osb.provisioning.subscription.service.SubscriptionEntryLocalService;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ClassNameLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "topic.pattern=koroneiki.account.contactrole.unassigned",
	service = ContactMessageSubscriber.class
)
public class ContactMessageSubscriber extends BaseMessageSubscriber {

	@Override
	protected void doParse(JSONObject jsonObject) throws Exception {
		Account account = AccountSerDes.toDTO(jsonObject.getString("account"));
		Contact contact = ContactSerDes.toDTO(jsonObject.getString("contact"));

		String accountKey = account.getKey();

		List<ContactRole> contactRoles =
			_contactRoleWebService.getAccountCustomerContactRoles(
				accountKey, contact.getEmailAddress(), 1, 1000);

		if (!contactRoles.isEmpty()) {
			return;
		}

		long classNameId = _classNameLocalService.getClassNameId(
			LicenseKey.class);

		List<SubscriptionEntry> subscriptionEntries =
			_subscriptionEntryLocalService.getSubscriptionEntries(
				classNameId, contact.getUuid());

		for (SubscriptionEntry subscriptionEntry : subscriptionEntries) {
			LicenseKey licenseKey = _licenseKeyLocalService.getLicenseKey(
				subscriptionEntry.getClassPK());

			if (accountKey.equals(licenseKey.getAccountKey())) {
				_subscriptionEntryLocalService.deleteSubscriptionEntry(
					subscriptionEntry.getSubscriptionEntryId());
			}
		}
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

	@Reference
	private SubscriptionEntryLocalService _subscriptionEntryLocalService;

}