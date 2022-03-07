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

package com.liferay.osb.provisioning.koroneiki.constants;

/**
 * @author Amos Fong
 */
public class ContactRoleConstants {

	public static final String NAME_ANALYTICS_CLOUD_OWNER =
		"Analytics Cloud Owner";

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