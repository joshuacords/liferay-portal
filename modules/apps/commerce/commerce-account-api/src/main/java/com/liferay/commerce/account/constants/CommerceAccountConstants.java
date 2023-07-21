/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.constants;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class CommerceAccountConstants {

	public static final String ACCOUNT_GROUP_NAME_GUEST = "Guest";

	public static final int ACCOUNT_GROUP_TYPE_DYNAMIC = 1;

	public static final int ACCOUNT_GROUP_TYPE_GUEST = 2;

	public static final int ACCOUNT_GROUP_TYPE_STATIC = 0;

	public static final int[] ACCOUNT_GROUP_TYPES = {
		ACCOUNT_GROUP_TYPE_STATIC, ACCOUNT_GROUP_TYPE_DYNAMIC
	};

	public static final long ACCOUNT_ID_GUEST = -1;

	public static final int ACCOUNT_TYPE_BUSINESS = 2;

	public static final int ACCOUNT_TYPE_GUEST = 0;

	public static final int ACCOUNT_TYPE_PERSONAL = 1;

	public static final int[] ACCOUNT_TYPES = {
		ACCOUNT_TYPE_GUEST, ACCOUNT_TYPE_PERSONAL, ACCOUNT_TYPE_BUSINESS
	};

	public static final long DEFAULT_PARENT_ACCOUNT_ID = 0;

	public static final String ROLE_NAME_ACCOUNT_ADMINISTRATOR =
		"Account Administrator";

	public static final String ROLE_NAME_ACCOUNT_BUYER = "Buyer";

	public static final String ROLE_NAME_ACCOUNT_ORDER_MANAGER =
		"Order Manager";

	public static final String SERVICE_NAME = "com.liferay.commerce.account";

	public static final int SITE_TYPE_B2B = 1;

	public static final int SITE_TYPE_B2C = 0;

	public static final int SITE_TYPE_B2C_B2B = 2;

	public static final int[] SITE_TYPES = {
		SITE_TYPE_B2C, SITE_TYPE_B2B, SITE_TYPE_B2C_B2B
	};

	public static String getAccountGroupTypeLabel(int accountGroupType) {
		if (accountGroupType == ACCOUNT_GROUP_TYPE_STATIC) {
			return "static";
		}
		else if (accountGroupType == ACCOUNT_GROUP_TYPE_DYNAMIC) {
			return "dynamic";
		}

		return null;
	}

	public static String getAccountTypeLabel(int accountType) {
		if (accountType == ACCOUNT_TYPE_BUSINESS) {
			return "business";
		}
		else if (accountType == ACCOUNT_TYPE_GUEST) {
			return "guest";
		}
		else if (accountType == ACCOUNT_TYPE_PERSONAL) {
			return "personal";
		}

		return null;
	}

	public static String getSiteTypeLabel(int siteType) {
		if (siteType == SITE_TYPE_B2C) {
			return "b2c";
		}
		else if (siteType == SITE_TYPE_B2B) {
			return "b2b";
		}
		else if (siteType == SITE_TYPE_B2C_B2B) {
			return "b2c-b2b";
		}

		return null;
	}

}