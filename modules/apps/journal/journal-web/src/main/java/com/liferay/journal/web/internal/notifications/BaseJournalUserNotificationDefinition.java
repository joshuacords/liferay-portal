/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.notifications;

import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Alejandro Tardín
 */
public abstract class BaseJournalUserNotificationDefinition
	extends UserNotificationDefinition {

	public BaseJournalUserNotificationDefinition(
		int notificationType, String description) {

		super(JournalPortletKeys.JOURNAL, 0, notificationType, description);

		_description = description;
	}

	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			locale, "com.liferay.journal.lang");

		String description = ResourceBundleUtil.getString(
			resourceBundle, _description);

		if (description != null) {
			return description;
		}

		return _description;
	}

	private final String _description;

}