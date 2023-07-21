/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.model.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.IOException;

/**
 * @author Adam Brandizzi
 */
public class CalendarNotificationTemplateImpl
	extends CalendarNotificationTemplateBaseImpl {

	@Override
	public String getNotificationTypeSettings() {
		if (_notificationTypeSettingsProperties == null) {
			return super.getNotificationTypeSettings();
		}

		return _notificationTypeSettingsProperties.toString();
	}

	@Override
	public UnicodeProperties getNotificationTypeSettingsProperties() {
		if (_notificationTypeSettingsProperties == null) {
			_notificationTypeSettingsProperties = new UnicodeProperties(true);

			try {
				_notificationTypeSettingsProperties.load(
					super.getNotificationTypeSettings());
			}
			catch (IOException ioException) {
				_log.error(ioException, ioException);
			}
		}

		return _notificationTypeSettingsProperties;
	}

	@Override
	public void setNotificationTypeSettings(String notificationTypeSettings) {
		_notificationTypeSettingsProperties = null;

		super.setNotificationTypeSettings(notificationTypeSettings);
	}

	@Override
	public void setTypeSettingsProperties(
		UnicodeProperties notificationTypeSettingsProperties) {

		_notificationTypeSettingsProperties =
			notificationTypeSettingsProperties;

		super.setNotificationTypeSettings(
			_notificationTypeSettingsProperties.toString());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CalendarNotificationTemplateImpl.class);

	private UnicodeProperties _notificationTypeSettingsProperties;

}