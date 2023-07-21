/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.notifications;

import java.util.Collection;
import java.util.List;

/**
 * @author Edward Han
 */
public interface Channel {

	public void cleanUp() throws ChannelException;

	public void close() throws ChannelException;

	public void confirmDelivery(Collection<String> notificationEventUuids)
		throws ChannelException;

	public void confirmDelivery(
			Collection<String> notificationEventUuids, boolean archive)
		throws ChannelException;

	public void confirmDelivery(String notificationEventUuid)
		throws ChannelException;

	public void confirmDelivery(String notificationEventUuid, boolean archive)
		throws ChannelException;

	public void deleteUserNotificiationEvent(String notificationEventUuid)
		throws ChannelException;

	public void deleteUserNotificiationEvents(
			Collection<String> notificationEventUuids)
		throws ChannelException;

	public void flush() throws ChannelException;

	public void flush(long timestamp) throws ChannelException;

	public List<NotificationEvent> getNotificationEvents()
		throws ChannelException;

	public List<NotificationEvent> getNotificationEvents(boolean flush)
		throws ChannelException;

	public long getUserId();

	public void init() throws ChannelException;

	public void registerChannelListener(ChannelListener channelListener);

	public void removeTransientNotificationEvents(
		Collection<NotificationEvent> notificationEvents);

	public void removeTransientNotificationEventsByUuid(
		Collection<String> notificationEventUuids);

	public void sendNotificationEvent(NotificationEvent notificationEvent)
		throws ChannelException;

	public void sendNotificationEvents(
			Collection<NotificationEvent> notificationEvents)
		throws ChannelException;

	public void storeNotificationEvent(
			NotificationEvent notificationEvent, long currentTime)
		throws ChannelException;

	public void unregisterChannelListener(ChannelListener channelListener);

}