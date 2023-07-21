/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.petra.string.StringPool;

/**
 * @author Michael C. Han
 */
public class PortalRunMode {

	public static boolean isTestMode() {
		String liferayMode = SystemProperties.get("liferay.mode");

		if (Validator.isNotNull(liferayMode) && liferayMode.equals("test")) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static void setTestMode(boolean mode) {
		if (mode) {
			SystemProperties.set("liferay.mode", "test");
		}
		else {
			SystemProperties.set("liferay.mode", StringPool.BLANK);
		}
	}

}