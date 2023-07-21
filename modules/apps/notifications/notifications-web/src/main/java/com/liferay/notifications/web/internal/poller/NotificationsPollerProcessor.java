/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.notifications.web.internal.poller;

import com.liferay.notifications.web.internal.constants.NotificationsPortletKeys;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.poller.BasePollerProcessor;
import com.liferay.portal.kernel.poller.PollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;
import com.liferay.portal.kernel.service.UserNotificationEventLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + NotificationsPortletKeys.NOTIFICATIONS,
	service = PollerProcessor.class
)
public class NotificationsPollerProcessor extends BasePollerProcessor {

	@Override
	protected PollerResponse doReceive(PollerRequest pollerRequest)
		throws Exception {

		return setUserNotificationsCount(pollerRequest);
	}

	@Override
	protected void doSend(PollerRequest pollerRequest) throws Exception {
	}

	@Reference(unbind = "-")
	protected void setUserNotificationEventLocalService(
		UserNotificationEventLocalService userNotificationEventLocalService) {

		_userNotificationEventLocalService = userNotificationEventLocalService;
	}

	protected PollerResponse setUserNotificationsCount(
			PollerRequest pollerRequest)
		throws Exception {

		PollerResponse pollerResponse = pollerRequest.createPollerResponse();

		pollerResponse.setParameter(
			"timestamp", String.valueOf(System.currentTimeMillis()));

		int newUserNotificationsCount =
			_userNotificationEventLocalService.
				getDeliveredUserNotificationEventsCount(
					pollerRequest.getUserId(),
					UserNotificationDeliveryConstants.TYPE_WEBSITE, false);

		pollerResponse.setParameter(
			"newUserNotificationsCount",
			String.valueOf(newUserNotificationsCount));

		int unreadUserNotificationsCount =
			_userNotificationEventLocalService.
				getDeliveredArchivedUserNotificationEventsCount(
					pollerRequest.getUserId(),
					UserNotificationDeliveryConstants.TYPE_WEBSITE, true,
					false);

		pollerResponse.setParameter(
			"unreadUserNotificationsCount",
			String.valueOf(unreadUserNotificationsCount));

		return pollerResponse;
	}

	private UserNotificationEventLocalService
		_userNotificationEventLocalService;

}