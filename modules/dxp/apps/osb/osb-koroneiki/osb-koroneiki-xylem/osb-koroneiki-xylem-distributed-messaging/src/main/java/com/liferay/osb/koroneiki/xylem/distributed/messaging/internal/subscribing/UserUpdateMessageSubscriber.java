/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.xylem.distributed.messaging.internal.subscribing;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "topic.pattern=entity.user.update",
	service = UserUpdateMessageSubscriber.class
)
public class UserUpdateMessageSubscriber implements MessageSubscriber {

	public void receive(Message message) {
		try {
			JSONObject jsonObject = _jsonFactory.createJSONObject(
				(String)message.getPayload());

			String uuid = jsonObject.getString("uuid");

			Contact contact = _contactLocalService.fetchContactByUuid(uuid);

			if (contact == null) {
				return;
			}

			_contactLocalService.updateContact(
				contact.getContactId(), contact.getUuid(),
				jsonObject.getString("firstName"),
				jsonObject.getString("middleName"),
				jsonObject.getString("lastName"),
				jsonObject.getString("emailAddress"),
				jsonObject.getString("languageId"),
				contact.isEmailAddressVerified());
		}
		catch (Exception exception) {
			_log.error(message);

			_log.error(exception, exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserUpdateMessageSubscriber.class);

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private JSONFactory _jsonFactory;

}