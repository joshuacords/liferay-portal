/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.helper.constants;

import com.liferay.petra.string.StringPool;

/**
 * @author Amos Fong
 */
public class LicenseType {

	public static final String BACKUP = "backup";

	public static final String CLUSTER = "cluster";

	public static final String DEVELOPER = "developer";

	public static final String DEVELOPER_CLUSTER = "developer-cluster";

	public static final String ELASTIC = "elastic";

	public static final String ENTERPRISE = "enterprise";

	public static final String LIMITED = "limited";

	public static final String NON_PRODUCTION = "non-production";

	public static final String OEM = "oem";

	public static final String PER_USER = "per-user";

	public static final String PRODUCTION = "production";

	public static final String[] VALUES = {
		CLUSTER, DEVELOPER, DEVELOPER_CLUSTER, ELASTIC, ENTERPRISE, LIMITED,
		OEM, PER_USER, PRODUCTION, LicenseType.VIRTUAL_CLUSTER
	};

	public static final String VIRTUAL_CLUSTER = "virtual-cluster";

	public static String getLabel(String type) {
		if (type.equals(BACKUP)) {
			return "Backup";
		}
		else if (type.equals(CLUSTER)) {
			return "Cluster";
		}
		else if (type.equals(DEVELOPER)) {
			return "Developer";
		}
		else if (type.equals(DEVELOPER_CLUSTER)) {
			return "Developer Cluster";
		}
		else if (type.equals(ELASTIC)) {
			return "Elastic";
		}
		else if (type.equals(ENTERPRISE)) {
			return "Enterprise";
		}
		else if (type.equals(LIMITED)) {
			return "Limited";
		}
		else if (type.equals(NON_PRODUCTION)) {
			return "Non-Production";
		}
		else if (type.equals(OEM)) {
			return "OEM";
		}
		else if (type.equals(PER_USER)) {
			return "Per User";
		}
		else if (type.equals(PRODUCTION)) {
			return "Production";
		}
		else if (type.equals(VIRTUAL_CLUSTER)) {
			return "Virtual Cluster";
		}

		return StringPool.BLANK;
	}

}