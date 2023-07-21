/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.scheduler.internal.verify;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.scheduler.internal.configuration.SchedulerEngineHelperConfiguration;
import com.liferay.portal.verify.VerifyProcess;

import java.util.Dictionary;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	enabled = false, immediate = true,
	property = "verify.process.name=com.liferay.portal.scheduler.internal.verify",
	service = VerifyProcess.class
)
public class SchedulerHelperPropertiesVerifyProcess extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		upgradeConfiguration();
	}

	protected void upgradeConfiguration() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			String audiMessageScheduleJobString = props.get(
				LEGACY_AUDIT_MESSAGE_SCHEDULER_JOB);

			if (Validator.isNull(audiMessageScheduleJobString)) {
				return;
			}

			Configuration configuration = configurationAdmin.getConfiguration(
				SchedulerEngineHelperConfiguration.class.getName(),
				StringPool.QUESTION);

			Dictionary<String, Object> properties = new HashMapDictionary<>();

			boolean auditMessageScheduleJob = GetterUtil.getBoolean(
				audiMessageScheduleJobString);

			properties.put(
				AUDIT_SCHEDULER_JOB_ENABLED, auditMessageScheduleJob);

			configuration.update(properties);
		}
	}

	protected static final String AUDIT_SCHEDULER_JOB_ENABLED =
		"auditSchedulerJobEnabled";

	protected static final String LEGACY_AUDIT_MESSAGE_SCHEDULER_JOB =
		"audit.message.scheduler.job";

	@Reference
	protected ConfigurationAdmin configurationAdmin;

	@Reference
	protected Props props;

}