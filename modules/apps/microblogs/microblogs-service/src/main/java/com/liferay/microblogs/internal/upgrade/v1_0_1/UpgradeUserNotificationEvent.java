/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.microblogs.internal.upgrade.v1_0_1;

import com.liferay.microblogs.constants.MicroblogsPortletKeys;
import com.liferay.microblogs.model.MicroblogsEntryConstants;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Evan Thibodeau
 */
public class UpgradeUserNotificationEvent extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeNotifications();
	}

	protected void updateNotification(
			long userNotificationEventId, JSONObject jsonObject)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"update UserNotificationEvent set payload = ? where " +
					"userNotificationEventId = ?")) {

			ps.setString(1, jsonObject.toString());
			ps.setLong(2, userNotificationEventId);

			ps.executeUpdate();
		}
	}

	protected void upgradeNotifications() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(
				"select userNotificationEventId, payload from " +
					"UserNotificationEvent where type_ = ?")) {

			ps.setString(1, MicroblogsPortletKeys.MICROBLOGS);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					String payload = rs.getString("payload");

					JSONObject payloadJSONObject =
						JSONFactoryUtil.createJSONObject(payload);

					int notificationType = payloadJSONObject.getInt(
						"notificationType");

					if (notificationType != 0) {
						return;
					}

					long userNotificationEventId = rs.getLong(
						"userNotificationEventId");

					payloadJSONObject.put(
						"notificationType",
						MicroblogsEntryConstants.NOTIFICATION_TYPE_REPLY);

					updateNotification(
						userNotificationEventId, payloadJSONObject);
				}
			}
		}
	}

}