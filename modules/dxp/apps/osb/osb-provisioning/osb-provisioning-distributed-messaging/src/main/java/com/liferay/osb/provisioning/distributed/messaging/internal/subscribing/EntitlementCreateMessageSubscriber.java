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
	immediate = true, property = "topic.pattern=koroneiki.entitlement.create",
	service = EntitlementCreateMessageSubscriber.class
)
public class EntitlementCreateMessageSubscriber extends BaseMessageSubscriber {

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
			_contactIdentityProvider.addMembership(
				OktaConstants.GROUP_NAME_CUSTOMERS,
				contactJSONObject.getString("emailAddress"));
		}
		else if (name.equals(EntitlementConstants.PARTNER)) {
			_contactIdentityProvider.addMembership(
				OktaConstants.GROUP_NAME_PARTNERS,
				contactJSONObject.getString("emailAddress"));
		}
	}

	@Reference(target = "(provider=okta)")
	private ContactIdentityProvider _contactIdentityProvider;

}