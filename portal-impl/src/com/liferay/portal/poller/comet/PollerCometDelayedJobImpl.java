/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.poller.comet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.util.PropsValues;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Edward Han
 */
public class PollerCometDelayedJobImpl
	extends BaseMessageListener implements PollerCometDelayedJob {

	@Override
	public void addPollerCometDelayedTask(
		PollerCometDelayedTask pollerCometDelayedTask) {

		synchronized (_pollerCometDelayedTasks) {
			if (_timer == null) {
				_timer = new Timer(PollerCometDelayedJobImpl.class.getName());

				_timer.schedule(
					new PollerCometTimerTask(),
					PropsValues.POLLER_NOTIFICATIONS_TIMEOUT);
			}

			_pollerCometDelayedTasks.add(pollerCometDelayedTask);
		}
	}

	@Override
	protected synchronized void doReceive(Message message) throws Exception {
		synchronized (_pollerCometDelayedTasks) {
			for (PollerCometDelayedTask pollerCometDelayedTask :
					_pollerCometDelayedTasks) {

				try {
					pollerCometDelayedTask.executeTask();
				}
				catch (Exception exception) {
					if (_log.isWarnEnabled()) {
						_log.warn("Unable to do task" + exception);
					}
				}
			}

			_pollerCometDelayedTasks.clear();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PollerCometDelayedJobImpl.class);

	private final List<PollerCometDelayedTask> _pollerCometDelayedTasks =
		new LinkedList<>();
	private Timer _timer;

	private class PollerCometTimerTask extends TimerTask {

		@Override
		public void run() {
			synchronized (_pollerCometDelayedTasks) {
				for (PollerCometDelayedTask pollerCometDelayedTask :
						_pollerCometDelayedTasks) {

					try {
						pollerCometDelayedTask.executeTask();
					}
					catch (Exception exception) {
						if (_log.isWarnEnabled()) {
							_log.warn("Unable to do task" + exception);
						}
					}
				}

				_pollerCometDelayedTasks.clear();
			}
		}

	}

}