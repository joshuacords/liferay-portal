/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.notifications;

import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Edward Han
 * @author Raymond Augé
 */
public class NotificationEventFactoryUtil {

	public static NotificationEvent createNotificationEvent(
		long timestamp, String type, JSONObject payloadJSONObject) {

		return getNotificationEventFactory().createNotificationEvent(
			timestamp, type, payloadJSONObject);
	}

	public static NotificationEventFactory getNotificationEventFactory() {
		return _notificationEventFactory;
	}

	public void setNotificationEventFactory(
		NotificationEventFactory notificationEventFactory) {

		_notificationEventFactory = notificationEventFactory;
	}

	private static NotificationEventFactory _notificationEventFactory;

}