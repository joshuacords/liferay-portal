/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.model;

import com.liferay.petra.string.StringPool;

/**
 * @author Shinn Lok
 */
public class PowwowParticipantConstants {

	public static final String LABEL_ATTENDEE = "attendee";

	public static final String LABEL_HOST = "host";

	public static final int STATUS_DEFAULT = 0;

	public static final int STATUS_INVITED = 1;

	public static final int TYPE_ATTENDEE = 0;

	public static final int TYPE_HOST = 1;

	public static String getTypeLabel(int type) {
		if (type == TYPE_ATTENDEE) {
			return LABEL_ATTENDEE;
		}
		else if (type == TYPE_HOST) {
			return LABEL_HOST;
		}

		return StringPool.BLANK;
	}

}