/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notifications.web.internal.upgrade.v2_1_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Roberto Díaz
 */
public class UpgradeUserNotificationEvent extends UpgradeProcess {

	public UpgradeUserNotificationEvent(
		UserNotificationEventLocalService userNotificationEventLocalService) {

		_userNotificationEventLocalService = userNotificationEventLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable("Notifications_UserNotificationEvent")) {
			updateUserNotificationEventActionRequired();
		}

		updateUserNotificationEvents();
	}

	protected void updateUserNotificationEventActionRequired()
		throws Exception {

		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			StringBundler sb = new StringBundler(5);

			sb.append("update UserNotificationEvent set actionRequired = ");
			sb.append("[$TRUE$] where userNotificationEventId in (select ");
			sb.append("userNotificationEventId from ");
			sb.append("Notifications_UserNotificationEvent where ");
			sb.append("actionRequired = [$TRUE$])");

			runSQL(sb.toString());

			runSQL(
				"update UserNotificationEvent set actionRequired = [$FALSE$] " +
					"where actionRequired IS NULL");
		}
	}

	protected void updateUserNotificationEvents() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps1 = connection.prepareStatement(
				"select userNotificationEventId, payload, actionRequired " +
					"from UserNotificationEvent where payload like " +
						"'%actionRequired%'");
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update UserNotificationEvent set payload = ?, " +
						"actionRequired = ? where userNotificationEventId = ?");
			ResultSet rs = ps1.executeQuery()) {

			runSQL("update UserNotificationEvent set delivered = [$TRUE$]");

			runSQL(
				StringBundler.concat(
					"update UserNotificationEvent set deliveryType = ",
					UserNotificationDeliveryConstants.TYPE_WEBSITE,
					" where deliveryType = 0 or deliveryType is null"));

			while (rs.next()) {
				long userNotificationEventId = rs.getLong(
					"userNotificationEventId");
				String payload = rs.getString("payload");
				boolean actionRequired = rs.getBoolean("actionRequired");

				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
					payload);

				actionRequired |= jsonObject.getBoolean("actionRequired");

				jsonObject.remove("actionRequired");

				ps2.setString(1, jsonObject.toString());

				ps2.setBoolean(2, actionRequired);
				ps2.setLong(3, userNotificationEventId);

				ps2.addBatch();
			}

			ps2.executeBatch();
		}
	}

	private final UserNotificationEventLocalService
		_userNotificationEventLocalService;

}