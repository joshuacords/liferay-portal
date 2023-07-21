/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.scheduler.config;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.proxy.ProxyModeThreadLocal;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shuyang Zhou
 * @author Tina Tian
 */
public class PluginSchedulingConfigurator {

	public void afterPropertiesSet() {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		boolean forceSync = ProxyModeThreadLocal.isForceSync();

		ProxyModeThreadLocal.setForceSync(true);

		try {
			ClassLoader portalClassLoader =
				PortalClassLoaderUtil.getClassLoader();

			currentThread.setContextClassLoader(portalClassLoader);

			for (SchedulerEntry schedulerEntry : _schedulerEntries) {
				try {
					MessageListener messageListener =
						(MessageListener)InstanceFactory.newInstance(
							PortletClassLoaderUtil.getClassLoader(),
							schedulerEntry.getEventListenerClass());

					SchedulerEngineHelperUtil.register(
						messageListener, schedulerEntry,
						DestinationNames.SCHEDULER_DISPATCH);

					_messageListeners.put(
						schedulerEntry.getEventListenerClass(),
						messageListener);
				}
				catch (Exception exception) {
					_log.error(
						"Unable to schedule " + schedulerEntry, exception);
				}
			}
		}
		finally {
			ProxyModeThreadLocal.setForceSync(forceSync);

			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	public void destroy() {
		for (MessageListener messageListener : _messageListeners.values()) {
			SchedulerEngineHelperUtil.unregister(messageListener);
		}

		_messageListeners.clear();
		_schedulerEntries.clear();
	}

	public void setSchedulerEntries(List<SchedulerEntry> schedulerEntries) {
		_schedulerEntries = schedulerEntries;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PluginSchedulingConfigurator.class);

	private final Map<String, MessageListener> _messageListeners =
		new HashMap<>();
	private List<SchedulerEntry> _schedulerEntries = Collections.emptyList();

}