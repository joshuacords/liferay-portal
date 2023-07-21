/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.polls.internal.upgrade.v1_0_0;

import com.liferay.polls.constants.PollsPortletKeys;
import com.liferay.portal.kernel.util.LoggingTimer;

/**
 * @author Máté Thurzó
 */
public class UpgradeLastPublishDate
	extends com.liferay.portal.upgrade.v7_0_0.UpgradeLastPublishDate {

	protected void addLastPublishDateColumns() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			addLastPublishDateColumn("PollsChoice");
			addLastPublishDateColumn("PollsQuestion");
			addLastPublishDateColumn("PollsVote");
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		addLastPublishDateColumns();

		updateLastPublishDates(PollsPortletKeys.POLLS, "PollsChoice");
		updateLastPublishDates(PollsPortletKeys.POLLS, "PollsQuestion");
		updateLastPublishDates(PollsPortletKeys.POLLS, "PollsVote");
	}

}