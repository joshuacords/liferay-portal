/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.distributed.messaging.internal.subscribing;

import com.liferay.osb.provisioning.identity.management.constants.OktaConstants;
import com.liferay.osb.provisioning.identity.management.provider.ContactIdentityProvider;
import com.liferay.osb.provisioning.koroneiki.constants.EntitlementConstants;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "topic.pattern=koroneiki.entitlement.delete",
	service = EntitlementDeleteMessageSubscriber.class
)
public class EntitlementDeleteMessageSubscriber extends BaseMessageSubscriber {

	@Override
	protected void doParse(JSONObject jsonObject) throws Exception {
		JSONObject contactJSONObject = jsonObject.getJSONObject("contact");

		if (contactJSONObject == null) {
			return;
		}

		JSONObject entitlementJSONObject = jsonObject.getJSONObject(
			"entitlement");

		String name = entitlementJSONObject.getString("name");

		if (name.equals(EntitlementConstants.CUSTOMER)) {
			_contactIdentityProvider.removeMembership(
				OktaConstants.GROUP_NAME_CUSTOMERS,
				contactJSONObject.getString("emailAddress"));
		}
		else if (name.equals(EntitlementConstants.PARTNER)) {
			_contactIdentityProvider.removeMembership(
				OktaConstants.GROUP_NAME_PARTNERS,
				contactJSONObject.getString("emailAddress"));
		}
	}

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _contactIdentityProvider;

}