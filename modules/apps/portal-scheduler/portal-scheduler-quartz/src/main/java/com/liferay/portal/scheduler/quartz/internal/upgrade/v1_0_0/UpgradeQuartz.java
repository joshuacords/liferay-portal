/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.scheduler.quartz.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Akos Thurzo
 */
public class UpgradeQuartz extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateJobDetails();
	}

	protected void updateJobDetails() {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps = connection.prepareStatement(
				"update QUARTZ_JOB_DETAILS set job_class_name = ? where " +
					"job_class_name = ?")) {

			ps.setString(
				1,
				"com.liferay.portal.scheduler.quartz.internal.job." +
					"MessageSenderJob");
			ps.setString(
				2, "com.liferay.portal.scheduler.job.MessageSenderJob");

			ps.executeUpdate();
		}
		catch (SQLException sqlException) {
			if (_log.isWarnEnabled()) {
				_log.warn(sqlException, sqlException);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(UpgradeQuartz.class);

}