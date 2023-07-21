/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.notifications;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Edward Han
 */
public abstract class BaseChannelImpl implements Channel {

	@Override
	public void cleanUp() throws ChannelException {
		lock.lock();

		try {
			long currentTime = System.currentTimeMillis();

			if (currentTime > _nextCleanUpTime) {
				_nextCleanUpTime = currentTime + _cleanUpInterval;

				try {
					doCleanUp();
				}
				catch (ChannelException channelException) {
					throw channelException;
				}
				catch (Exception exception) {
					throw new ChannelException(exception);
				}
			}
		}
		finally {
			lock.unlock();
		}
	}

	@Override
	public void close() throws ChannelException {
		flush();
	}

	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public List<NotificationEvent> getNotificationEvents()
		throws ChannelException {

		return getNotificationEvents(true);
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	public boolean hasNotificationEvents() {
		try {
			List<NotificationEvent> notificationEvents = getNotificationEvents(
				false);

			if (!notificationEvents.isEmpty()) {
				return true;
			}
		}
		catch (ChannelException channelException) {
			_log.error("Unable to fetch notifications", channelException);
		}

		return false;
	}

	@Override
	public void registerChannelListener(ChannelListener channelListener) {
		lock.lock();

		try {
			List<ChannelListener> channelListeners = _getChannelListeners();

			channelListeners.add(channelListener);

			if (hasNotificationEvents()) {
				notifyChannelListeners();
			}
		}
		finally {
			lock.unlock();
		}
	}

	public void setCleanUpInterval(long cleanUpInterval) {
		_cleanUpInterval = cleanUpInterval;
	}

	@Override
	public void unregisterChannelListener(ChannelListener channelListener) {
		lock.lock();

		try {
			List<ChannelListener> channelListeners = _getChannelListeners();

			channelListeners.remove(channelListener);
		}
		finally {
			lock.unlock();
		}

		channelListener.channelListenerRemoved(_userId);
	}

	protected BaseChannelImpl(long companyId, long usedId) {
		_companyId = companyId;

		_userId = usedId;
	}

	protected abstract void doCleanUp() throws Exception;

	protected void notifyChannelListeners() {
		for (ChannelListener channelListener : _getChannelListeners()) {
			channelListener.notificationEventsAvailable(_userId);
		}
	}

	protected final Lock lock = new ReentrantLock();

	private List<ChannelListener> _getChannelListeners() {
		if (_channelListeners == null) {
			_channelListeners = new ArrayList<>();
		}

		return _channelListeners;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseChannelImpl.class);

	private List<ChannelListener> _channelListeners;
	private long _cleanUpInterval;
	private final long _companyId;
	private long _nextCleanUpTime;
	private final long _userId;

}