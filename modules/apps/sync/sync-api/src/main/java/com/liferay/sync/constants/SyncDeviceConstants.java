/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.constants;

/**
 * @author Shinn Lok
 */
public class SyncDeviceConstants {

	/**
	 * Feature set 1:
	 *
	 * Sites removed from Sync context
	 */
	public static final int FEATURE_SET_1 = 1;

	public static final int STATUS_ACTIVE = 0;

	public static final int STATUS_INACTIVE = 1;

	public static final String STATUS_LABEL_ACTIVE = "active";

	public static final String STATUS_LABEL_INACTIVE = "inactive";

	public static final String STATUS_LABEL_PENDING_WIPE = "pending-wipe";

	public static final String STATUS_LABEL_WIPED = "wiped";

	public static final int STATUS_PENDING_WIPE = 2;

	public static final int STATUS_WIPED = 3;

	public static String getStatusLabel(int status) {
		if (status == STATUS_ACTIVE) {
			return STATUS_LABEL_ACTIVE;
		}
		else if (status == STATUS_INACTIVE) {
			return STATUS_LABEL_INACTIVE;
		}
		else if (status == STATUS_PENDING_WIPE) {
			return STATUS_LABEL_PENDING_WIPE;
		}
		else if (status == STATUS_WIPED) {
			return STATUS_LABEL_WIPED;
		}

		return null;
	}

}