/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.helper.constants;

import com.liferay.petra.string.StringPool;

/**
 * @author Kyle Bischof
 */
public class LicenseServerId {

	public static final String DEVELOPER = "Developer";

	public static final String ELASTIC = "Elastic";

	public static final String ENTERPRISE = "Enterprise";

	public static final String OEM = "OEM";

	public static final String VIRTUAL_CLUSTER = "Virtual Cluster";

	public static final String getServerId(String licenseType) {
		if (licenseType.equals(LicenseType.DEVELOPER) ||
			licenseType.equals(LicenseType.DEVELOPER_CLUSTER)) {

			return DEVELOPER;
		}
		else if (licenseType.equals(LicenseType.ELASTIC)) {
			return ELASTIC;
		}
		else if (licenseType.equals(LicenseType.ENTERPRISE)) {
			return ENTERPRISE;
		}
		else if (licenseType.equals(LicenseType.OEM)) {
			return OEM;
		}
		else if (licenseType.equals(LicenseType.VIRTUAL_CLUSTER)) {
			return VIRTUAL_CLUSTER;
		}

		return StringPool.BLANK;
	}

}