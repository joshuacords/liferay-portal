/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.distributed.messaging.rabbitmq.connector.messaging;

import com.liferay.osb.distributed.messaging.rabbitmq.connector.consumer.BaseConsumer;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "check.interval=15",
	service = ExclusiveConsumerCheckMessageListener.class
)
public class ExclusiveConsumerCheckMessageListener extends BaseMessageListener {

	public synchronized void register(BaseConsumer consumer) {
		if (_consumers.isEmpty()) {
			Class<?> clazz = getClass();

			Trigger trigger = _triggerFactory.createTrigger(
				clazz.getName(), clazz.getName(), null, null, _checkInterval,
				TimeUnit.MINUTE);

			SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
				clazz.getName(), trigger);

			_schedulerEngineHelper.register(
				this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
		}

		_consumers.add(consumer);
	}

	public synchronized void unregister(BaseConsumer consumer) {
		_consumers.remove(consumer);

		if (_consumers.isEmpty()) {
			_schedulerEngineHelper.unregister(this);
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		_checkInterval = GetterUtil.getInteger(
			properties.get("check.interval"));
	}

	@Deactivate
	protected synchronized void deactivate() {
		if (!_consumers.isEmpty()) {
			_schedulerEngineHelper.unregister(this);
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		for (BaseConsumer consumer : _consumers) {
			consumer.checkConsumer();
		}
	}

	private int _checkInterval;
	private final Set<BaseConsumer> _consumers = Collections.newSetFromMap(
		new ConcurrentHashMap<>());

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

}