/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_0_1;

/**
 * @author Roberto Díaz
 */
public class UpgradeModules
	extends com.liferay.portal.upgrade.v7_0_0.UpgradeModules {

	@Override
	public String[] getBundleSymbolicNames() {
		return _BUNDLE_SYMBOLIC_NAMES;
	}

	@Override
	public String[][] getConvertedLegacyModules() {
		return _CONVERTED_LEGACY_MODULES;
	}

	private static final String[] _BUNDLE_SYMBOLIC_NAMES = {
		"com.liferay.announcements.web", "com.liferay.directory.web",
		"com.liferay.microblogs.web", "com.liferay.notifications.web",
		"com.liferay.recent.documents.web", "com.liferay.social.networking.web",
		"com.liferay.social.privatemessaging.web"
	};

	private static final String[][] _CONVERTED_LEGACY_MODULES = {
		{"com.liferay.contacts.web", "com.liferay.contacts.web", "Contacts"},
		{
			"push-notifications-portlet",
			"com.liferay.push.notifications.service", "PushNotifications"
		},
		{"sync-web", "com.liferay.sync.service", "Sync"}
	};

}