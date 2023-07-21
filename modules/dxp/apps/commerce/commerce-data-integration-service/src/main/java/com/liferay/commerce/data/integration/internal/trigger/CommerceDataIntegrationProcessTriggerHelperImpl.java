/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.internal.trigger;

import com.liferay.commerce.data.integration.constants.CommerceDataIntegrationConstants;
import com.liferay.commerce.data.integration.trigger.CommerceDataIntegrationProcessTriggerHelper;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	service = CommerceDataIntegrationProcessTriggerHelper.class
)
public class CommerceDataIntegrationProcessTriggerHelperImpl
	implements CommerceDataIntegrationProcessTriggerHelper {

	public static final String JOB_NAME_PREFIX_X =
		"COMMERCE_DATA_INTEGRATION_PROCESS_%s";

	@Override
	public void addScheduledTask(
			long commerceDataIntegrationProcessId, String cronExpression,
			Date startDate, Date endDate)
		throws SchedulerException {

		deleteScheduledTask(commerceDataIntegrationProcessId);

		Trigger trigger = _triggerFactory.createTrigger(
			String.valueOf(commerceDataIntegrationProcessId),
			_getGroupName(commerceDataIntegrationProcessId), startDate, endDate,
			cronExpression);

		JSONObject payLoad = JSONUtil.put(
			"commerceDataIntegrationProcessId",
			commerceDataIntegrationProcessId);

		_schedulerEngineHelper.schedule(
			trigger, StorageType.PERSISTED, null,
			CommerceDataIntegrationConstants.EXECUTOR_DESTINATION_NAME,
			payLoad.toString(), 1000);
	}

	@Override
	public void deleteScheduledTask(long commerceDataIntegrationProcessId)
		throws SchedulerException {

		SchedulerResponse scheduledJob = getScheduledJob(
			commerceDataIntegrationProcessId);

		if (scheduledJob != null) {
			_schedulerEngineHelper.delete(
				String.valueOf(commerceDataIntegrationProcessId),
				_getGroupName(commerceDataIntegrationProcessId),
				StorageType.PERSISTED);
		}
	}

	@Override
	public Date getNextFireTime(long commerceDataIntegrationProcessId) {
		Date nextFireTime = null;

		try {
			nextFireTime = _schedulerEngineHelper.getNextFireTime(
				String.valueOf(commerceDataIntegrationProcessId),
				_getGroupName(commerceDataIntegrationProcessId),
				StorageType.PERSISTED);
		}
		catch (SchedulerException se) {
			_log.error(se, se);
		}

		return nextFireTime;
	}

	@Override
	public Date getPreviousFireTime(long commerceDataIntegrationProcessId) {
		Date nextFireTime = null;

		try {
			nextFireTime = _schedulerEngineHelper.getPreviousFireTime(
				String.valueOf(commerceDataIntegrationProcessId),
				_getGroupName(commerceDataIntegrationProcessId),
				StorageType.PERSISTED);
		}
		catch (SchedulerException se) {
			_log.error(se, se);
		}

		return nextFireTime;
	}

	@Override
	public SchedulerResponse getScheduledJob(
			long commerceDataIntegrationProcessId)
		throws SchedulerException {

		return _schedulerEngineHelper.getScheduledJob(
			String.valueOf(commerceDataIntegrationProcessId),
			_getGroupName(commerceDataIntegrationProcessId),
			StorageType.PERSISTED);
	}

	private String _getGroupName(long commerceDataIntegrationProcessId) {
		return String.format(
			JOB_NAME_PREFIX_X, commerceDataIntegrationProcessId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDataIntegrationProcessTriggerHelperImpl.class);

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

}