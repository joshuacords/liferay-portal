/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.audit.router.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.audit.AuditMessageProcessor;
import com.liferay.portal.security.audit.formatter.LogMessageFormatter;
import com.liferay.portal.security.audit.router.configuration.LoggingAuditMessageProcessorConfiguration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
@Component(
	configurationPid = "com.liferay.portal.security.audit.router.configuration.LoggingAuditMessageProcessorConfiguration",
	immediate = true, property = "eventTypes=*",
	service = AuditMessageProcessor.class
)
public class LoggingAuditMessageProcessor implements AuditMessageProcessor {

	@Override
	public void process(AuditMessage auditMessage) {
		try {
			doProcess(auditMessage);
		}
		catch (Exception exception) {
			_log.fatal(
				"Unable to process audit message " + auditMessage, exception);
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_loggingAuditMessageProcessorConfiguration =
			ConfigurableUtil.createConfigurable(
				LoggingAuditMessageProcessorConfiguration.class, properties);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void addLogMessageFormatter(
		LogMessageFormatter logMessageFormatter,
		Map<String, Object> properties) {

		String format = (String)properties.get("format");

		if (Validator.isNull(format)) {
			throw new IllegalArgumentException(
				"The property \"format\" is null");
		}

		_logMessageFormatters.put(format, logMessageFormatter);
	}

	protected void doProcess(AuditMessage auditMessage) throws Exception {
		if (_loggingAuditMessageProcessorConfiguration.enabled() &&
			(_log.isInfoEnabled() ||
			 _loggingAuditMessageProcessorConfiguration.outputToConsole())) {

			LogMessageFormatter logMessageFormatter = _logMessageFormatters.get(
				_loggingAuditMessageProcessorConfiguration.logMessageFormat());

			if (logMessageFormatter == null) {
				if (_log.isWarnEnabled()) {
					String logMessageFormat =
						_loggingAuditMessageProcessorConfiguration.
							logMessageFormat();

					_log.warn(
						"No log message formatter found for log message " +
							"format " + logMessageFormat);
				}

				return;
			}

			String logMessage = logMessageFormatter.format(auditMessage);

			if (_log.isInfoEnabled()) {
				_log.info(logMessage);
			}

			if (_loggingAuditMessageProcessorConfiguration.outputToConsole()) {
				System.out.println(
					"LoggingAuditMessageProcessor: " + logMessage);
			}
		}
	}

	protected void removeLogMessageFormatter(
		LogMessageFormatter logMessageFormatter,
		Map<String, Object> properties) {

		String format = (String)properties.get("format");

		if (Validator.isNull(format)) {
			throw new IllegalArgumentException(
				"The property \"format\" is null");
		}

		_logMessageFormatters.remove(format);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LoggingAuditMessageProcessor.class);

	private volatile LoggingAuditMessageProcessorConfiguration
		_loggingAuditMessageProcessorConfiguration;
	private final Map<String, LogMessageFormatter> _logMessageFormatters =
		new ConcurrentHashMap<>();

}