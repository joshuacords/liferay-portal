/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.microblogs.internal.upgrade.v1_0_2;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Matthew Kong
 */
public class UpgradeSocial extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeMicroblogActivities();
	}

	protected void updateSocialActivity(long activityId, JSONObject jsonObject)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"update SocialActivity set extraData = ? where activityId = " +
					"?")) {

			ps.setString(1, jsonObject.toString());
			ps.setLong(2, activityId);

			ps.executeUpdate();
		}
	}

	protected void upgradeMicroblogActivities() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(
				"select activityId, extraData from SocialActivity where " +
					"classNameId = ?")) {

			ps.setLong(1, PortalUtil.getClassNameId(MicroblogsEntry.class));

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					long activityId = rs.getLong("activityId");

					String extraData = rs.getString("extraData");

					JSONObject extraDataJSONObject =
						JSONFactoryUtil.createJSONObject(extraData);

					long parentMicroblogsEntryId = extraDataJSONObject.getLong(
						"receiverMicroblogsEntryId");

					extraDataJSONObject.put(
						"parentMicroblogsEntryId", parentMicroblogsEntryId);

					extraDataJSONObject.remove("receiverMicroblogsEntryId");

					updateSocialActivity(activityId, extraDataJSONObject);
				}
			}
		}
	}

}