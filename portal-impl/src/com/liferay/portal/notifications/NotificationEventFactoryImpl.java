/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.notifications;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactory;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public class NotificationEventFactoryImpl implements NotificationEventFactory {

	@Override
	public NotificationEvent createNotificationEvent(
		long timestamp, String type, JSONObject payloadJSONObject) {

		return new NotificationEvent(timestamp, type, payloadJSONObject);
	}

}