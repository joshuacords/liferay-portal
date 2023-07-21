/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notifications.web.internal.search;

import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.model.UserNotificationEvent;

import javax.portlet.PortletResponse;

/**
 * @author István András Dézsi
 */
public class UserNotificationEventRowChecker extends EmptyOnClickRowChecker {

	public UserNotificationEventRowChecker(PortletResponse portletResponse) {
		super(portletResponse);
	}

	@Override
	public boolean isDisabled(Object obj) {
		UserNotificationEvent userNotificationEvent =
			(UserNotificationEvent)obj;

		if (userNotificationEvent.isActionRequired()) {
			return true;
		}

		return super.isDisabled(obj);
	}

}