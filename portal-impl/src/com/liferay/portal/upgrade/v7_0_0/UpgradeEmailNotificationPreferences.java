/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.portal.kernel.upgrade.RenameUpgradePortalPreferences;
import com.liferay.portal.kernel.util.PropsKeys;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Eduardo García
 */
public class UpgradeEmailNotificationPreferences
	extends RenameUpgradePortalPreferences {

	public UpgradeEmailNotificationPreferences() {
		_preferenceNamesMap.put(
			PropsKeys.ADMIN_EMAIL_PASSWORD_RESET_BODY,
			"adminEmailPasswordResetBody");
		_preferenceNamesMap.put(
			PropsKeys.ADMIN_EMAIL_PASSWORD_RESET_SUBJECT,
			"adminEmailPasswordResetSubject");
		_preferenceNamesMap.put(
			PropsKeys.ADMIN_EMAIL_PASSWORD_SENT_BODY,
			"adminEmailPasswordSentBody");
		_preferenceNamesMap.put(
			PropsKeys.ADMIN_EMAIL_PASSWORD_SENT_SUBJECT,
			"adminEmailPasswordSentSubject");
		_preferenceNamesMap.put(
			PropsKeys.ADMIN_EMAIL_USER_ADDED_BODY, "adminEmailUserAddedBody");
		_preferenceNamesMap.put(
			PropsKeys.ADMIN_EMAIL_USER_ADDED_NO_PASSWORD_BODY,
			"adminEmailUserAddedNoPasswordBody");
		_preferenceNamesMap.put(
			PropsKeys.ADMIN_EMAIL_USER_ADDED_SUBJECT,
			"adminEmailUserAddedSubject");
		_preferenceNamesMap.put(
			PropsKeys.ADMIN_EMAIL_VERIFICATION_BODY,
			"adminEmailVerificationBody");
		_preferenceNamesMap.put(
			PropsKeys.ADMIN_EMAIL_VERIFICATION_SUBJECT,
			"adminEmailVerificationSubject");
	}

	@Override
	protected Map<String, String> getPreferenceNamesMap() {
		return _preferenceNamesMap;
	}

	private final Map<String, String> _preferenceNamesMap = new HashMap<>();

}