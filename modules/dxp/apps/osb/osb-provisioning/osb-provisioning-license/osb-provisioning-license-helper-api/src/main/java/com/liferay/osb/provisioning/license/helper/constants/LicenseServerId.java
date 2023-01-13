/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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