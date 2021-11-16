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

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.provisioning.distributed.messaging.internal.constants.LegacyConstants;
import com.liferay.osb.provisioning.koroneiki.web.service.ContactWebService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true, property = "topic.pattern=entity.role.assigned",
	service = RoleAssignmentMessageSubscriber.class
)
public class RoleAssignmentMessageSubscriber extends BaseMessageSubscriber {

	@Override
	protected void doParse(JSONObject jsonObject) throws Exception {
		JSONObject userJSONObject = jsonObject.getJSONObject("user");

		Contact contact = _contactWebService.fetchContactByEmailAddress(
			userJSONObject.getString("emailAddress"));

		if (contact == null) {
			return;
		}

		JSONObject roleJSONObject = jsonObject.getJSONObject("role");

		String roleUUID = roleJSONObject.getString("uuid");

		if (roleUUID.equals(LegacyConstants.ROLE_VERIFIED_UUID)) {
			contact.setEmailAddressVerified(true);

			_contactWebService.updateContact(
				StringPool.BLANK, StringPool.BLANK, contact.getEmailAddress(),
				contact);
		}
	}

	@Override
	protected void handleError(
			String routingKey, String message, Exception[] exceptions)
		throws PortalException {

		for (Exception exception : exceptions) {
			_log.error(message, exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RoleAssignmentMessageSubscriber.class);

	@Reference
	private ContactWebService _contactWebService;

}