/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.poller.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.kernel.notifications.UnknownChannelException;
import com.liferay.portal.kernel.poller.PollerHeader;
import com.liferay.portal.kernel.poller.PollerResponse;

/**
 * @author Edward Han
 */
public class PollerNotificationsBridgeMessageListener
	extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		Object messagePayload = message.getPayload();

		if (!(messagePayload instanceof PollerResponse)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Received message with payload not of type PollerResponse");
			}

			return;
		}

		PollerResponse pollerResponse = (PollerResponse)messagePayload;

		if (pollerResponse.isEmpty()) {
			return;
		}

		PollerHeader pollerHeader = pollerResponse.getPollerHeader();

		NotificationEvent notificationEvent =
			NotificationEventFactoryUtil.createNotificationEvent(
				System.currentTimeMillis(),
				PollerNotificationsBridgeMessageListener.class.getName(),
				pollerResponse.toJSONObject());

		try {
			ChannelHubManagerUtil.sendNotificationEvent(
				pollerHeader.getCompanyId(), pollerHeader.getUserId(),
				notificationEvent);
		}
		catch (UnknownChannelException unknownChannelException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to complete processing because user session ended",
					unknownChannelException);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PollerNotificationsBridgeMessageListener.class);

}