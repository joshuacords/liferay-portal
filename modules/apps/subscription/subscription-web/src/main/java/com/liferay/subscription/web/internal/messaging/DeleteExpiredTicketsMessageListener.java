/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.subscription.web.internal.messaging;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.subscription.model.Subscription;
import com.liferay.subscription.web.internal.configuration.SubscriptionConfiguration;
import com.liferay.subscription.web.internal.constants.SubscriptionConstants;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(
	configurationPid = "com.liferay.subscription.web.internal.configuration.SubscriptionConfiguration",
	immediate = true, service = {}
)
public class DeleteExpiredTicketsMessageListener extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_subscriptionConfiguration = ConfigurableUtil.createConfigurable(
			SubscriptionConfiguration.class, properties);

		Class<?> clazz = getClass();

		String className = clazz.getName();

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null,
			_subscriptionConfiguration.deleteExpiredTicketsInterval(),
			TimeUnit.HOUR);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
			className, trigger);

		_schedulerEngineHelper.register(
			this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		long subscriptionClassNameId = _classNameLocalService.getClassNameId(
			Subscription.class);

		ActionableDynamicQuery actionableDynamicQuery =
			_ticketLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> dynamicQuery.add(
				RestrictionsFactoryUtil.and(
					RestrictionsFactoryUtil.eq(
						"type", SubscriptionConstants.TICKET_TYPE),
					RestrictionsFactoryUtil.eq(
						"classNameId", subscriptionClassNameId))));
		actionableDynamicQuery.setPerformActionMethod(
			(Ticket ticket) -> {
				if (ticket.isExpired()) {
					_ticketLocalService.deleteTicket(ticket);
				}
			});

		actionableDynamicQuery.performActions();
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	private volatile SubscriptionConfiguration _subscriptionConfiguration;

	@Reference
	private TicketLocalService _ticketLocalService;

	@Reference
	private TriggerFactory _triggerFactory;

}