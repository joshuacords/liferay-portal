/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.license.messaging;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Igor Beslic
 */
public enum LicenseManagerMessageType {

	LCS_AVAILABLE, SUBSCRIPTION_VALID, VALIDATE_LCS, VALIDATE_SUBSCRIPTION;

	public static String MESSAGE_BUS_DESTINATION_REQUEST =
		"liferay/lcs_request";

	public static String MESSAGE_BUS_DESTINATION_STATUS = "liferay/lcs_status";

	/**
	 * @deprecated As of Wilberforce (7.0.x), ), with no direct replacement
	 */
	@Deprecated
	public static JSONObject getMessagePayload(Message message) {
		return getMessagePayload(message.getPayload());
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static JSONObject getMessagePayload(Object object) {
		if (object instanceof String) {
			return getMessagePayload((String)object);
		}

		return null;
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static JSONObject getMessagePayload(String json) {
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(json);

			valueOf(jsonObject);

			return jsonObject;
		}
		catch (Exception exception) {
			return null;
		}
	}

	public static LicenseManagerMessageType valueOf(JSONObject jsonObject) {
		String type = jsonObject.getString("type");

		return valueOf(type);
	}

	public Message createMessage() {
		Message message = new Message();

		message.setDestinationName(getDestinationName());
		message.setPayload(String.format("{\"type\": \"%s\"}", name()));

		return message;
	}

	public Message createMessage(LCSPortletState lcsPortletState) {
		Message message = new Message();

		message.setDestinationName(getDestinationName());

		message.setPayload(
			String.format(
				"{\"state\": %d, \"type\": \"%s\"}", lcsPortletState.intValue(),
				name()));

		return message;
	}

	public String getDestinationName() {
		if ((this == LCS_AVAILABLE) || (this == SUBSCRIPTION_VALID)) {
			return MESSAGE_BUS_DESTINATION_STATUS;
		}
		else if ((this == VALIDATE_LCS) || (this == VALIDATE_SUBSCRIPTION)) {
			return MESSAGE_BUS_DESTINATION_REQUEST;
		}

		return null;
	}

}