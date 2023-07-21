/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.scheduler.quartz.internal.messaging.proxy;

import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.proxy.ProxyMessageListener;
import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.scheduler.quartz.internal.QuartzSchedulerEngine;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Tina Tian
 */
@Component(
	enabled = false, immediate = true,
	property = "destination.name=" + DestinationNames.SCHEDULER_ENGINE,
	service = ProxyMessageListener.class
)
public class QuartzSchedulerProxyMessageListener extends ProxyMessageListener {

	@Override
	@Reference(unbind = "-")
	public void setMessageBus(MessageBus messageBus) {
		_messageBus = messageBus;
	}

	@Activate
	protected void activate() {
		setManager(_schedulerEngine);
		setMessageBus(_messageBus);
	}

	@Reference(service = QuartzSchedulerEngine.class, unbind = "-")
	protected void setSchedulerEngine(SchedulerEngine schedulerEngine) {
		_schedulerEngine = schedulerEngine;
	}

	private MessageBus _messageBus;
	private SchedulerEngine _schedulerEngine;

}