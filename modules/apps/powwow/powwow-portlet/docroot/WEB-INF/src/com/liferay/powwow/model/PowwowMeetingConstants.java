/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.model;

import com.liferay.petra.string.StringPool;

/**
 * @author Shinn Lok
 */
public class PowwowMeetingConstants {

	public static final String LABEL_ANY = "any";

	public static final String LABEL_COMPLETED = "completed";

	public static final String LABEL_IN_PROGRESS = "in-progress";

	public static final String LABEL_SCHEDULED = "scheduled";

	public static final String OPTION_AUTO_START_VIDEO = "autoStartVideo";

	public static final String OPTION_PASSWORD = "password";

	public static final int POWWOW_SERVER_ID_DEFAULT = 0;

	public static final int STATUS_ANY = -1;

	public static final int STATUS_COMPLETED = 2;

	public static final int STATUS_IN_PROGRESS = 1;

	public static final int STATUS_SCHEDULED = 0;

	public static String getStatusLabel(int status) {
		if (status == STATUS_ANY) {
			return LABEL_ANY;
		}
		else if (status == STATUS_COMPLETED) {
			return LABEL_COMPLETED;
		}
		else if (status == STATUS_IN_PROGRESS) {
			return LABEL_IN_PROGRESS;
		}
		else if (status == STATUS_SCHEDULED) {
			return LABEL_SCHEDULED;
		}

		return StringPool.BLANK;
	}

}