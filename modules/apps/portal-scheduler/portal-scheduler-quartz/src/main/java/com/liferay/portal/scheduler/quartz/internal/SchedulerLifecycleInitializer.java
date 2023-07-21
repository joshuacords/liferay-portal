/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.scheduler.quartz.internal;

import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerLifecycle;
import com.liferay.portal.kernel.util.PortalLifecycle;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(enabled = false, immediate = true, service = {})
public class SchedulerLifecycleInitializer {

	@Activate
	protected void activate() {
		SchedulerLifecycle schedulerLifecycle = new SchedulerLifecycle();

		schedulerLifecycle.registerPortalLifecycle(PortalLifecycle.METHOD_INIT);
	}

	@Reference(unbind = "-")
	protected void setQuartzSchemaManager(
		QuartzSchemaManager quartzSchemaManager) {
	}

	@Reference(unbind = "-")
	protected void setSchedulerEngineHelper(
		SchedulerEngineHelper schedulerEngineHelper) {
	}

}