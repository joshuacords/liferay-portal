/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.trigger;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;

import java.util.Date;

/**
 * @author Alessio Antonio Rendina
 */
@ProviderType
public interface CommerceDataIntegrationProcessTriggerHelper {

	public void addScheduledTask(
			long commerceDataIntegrationProcessId, String cronExpression,
			Date startDate, Date endDate)
		throws SchedulerException;

	public void deleteScheduledTask(long commerceDataIntegrationProcessId)
		throws SchedulerException;

	public Date getNextFireTime(long commerceDataIntegrationProcessId);

	public Date getPreviousFireTime(long commerceDataIntegrationProcessId);

	public SchedulerResponse getScheduledJob(
			long commerceDataIntegrationProcessId)
		throws SchedulerException;

}