/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.koroneiki.constants;

/**
 * @author Amos Fong
 */
public class ContactRoleConstants {

	public static final String NAME_ANALYTICS_CLOUD_OWNER =
		"Analytics Cloud Owner";

	public static final String NAME_LIFERAY_CUSTOMER_SUCCESS =
		"Liferay Customer Success";

	public static final String NAME_LIFERAY_SALES = "Liferay Sales";

	public static final String NAME_MEMBER = "Member";

	public static final String NAME_PARTNER_MANAGER = "Partner Manager";

	public static final String NAME_PARTNER_MEMBER = "Partner Member";

	public static final String NAME_PRIMARY_CONTACT = "Primary Contact";

	public static final String NAME_SECONDARY_CONTACT = "Secondary Contact";

	public static final String NAME_SUPPORT_ADMINISTRATOR =
		"Support Administrator";

	public static final String NAME_SUPPORT_CLOSED_WATCHER =
		"Support Closed Watcher";

	public static final String NAME_SUPPORT_REQUESTER = "Support Requester";

	public static final String NAME_SUPPORT_USER = "Support User";

	public static final String[] PARTNER_CONTACT_ROLES = {
		NAME_PARTNER_MANAGER, NAME_PARTNER_MEMBER
	};

	public static final String[] SUPPORT_CONTACT_ROLES = {
		NAME_SUPPORT_REQUESTER, NAME_SUPPORT_USER, NAME_SUPPORT_CLOSED_WATCHER
	};

	public static final String[] SUPPORT_SEAT_CONTACT_ROLES = {
		NAME_SUPPORT_ADMINISTRATOR, NAME_SUPPORT_REQUESTER
	};

}