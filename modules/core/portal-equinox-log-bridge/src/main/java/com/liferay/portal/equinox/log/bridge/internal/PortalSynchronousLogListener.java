/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.equinox.log.bridge.internal;

import org.eclipse.equinox.log.ExtendedLogEntry;
import org.eclipse.equinox.log.SynchronousLogListener;
import org.eclipse.osgi.framework.log.FrameworkLogEntry;

import org.osgi.framework.BundleException;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Raymond Augé
 * @author Kamesh Sampath
 */
public class PortalSynchronousLogListener implements SynchronousLogListener {

	@Override
	public void logged(LogEntry logEntry) {
		if (!(logEntry instanceof ExtendedLogEntry)) {
			return;
		}

		ExtendedLogEntry extendedLogEntry = (ExtendedLogEntry)logEntry;

		Object context = extendedLogEntry.getContext();

		if (context instanceof FrameworkLogEntry) {
			FrameworkLogEntry frameworkLogEntry = (FrameworkLogEntry)context;

			_log(
				frameworkLogEntry.getEntry(), frameworkLogEntry.getSeverity(),
				frameworkLogEntry.getMessage(), frameworkLogEntry.getContext(),
				frameworkLogEntry.getThrowable());

			FrameworkLogEntry[] childFrameworkLogEntries =
				frameworkLogEntry.getChildren();

			if ((childFrameworkLogEntries != null) &&
				(childFrameworkLogEntries.length > 0)) {

				for (FrameworkLogEntry childFrameworkLogEntry :
						childFrameworkLogEntries) {

					_log(
						childFrameworkLogEntry.getEntry(),
						childFrameworkLogEntry.getSeverity(),
						childFrameworkLogEntry.getMessage(),
						childFrameworkLogEntry.getContext(),
						childFrameworkLogEntry.getThrowable());
				}
			}

			return;
		}

		_log(
			extendedLogEntry.getLoggerName(), extendedLogEntry.getLevel(),
			extendedLogEntry.getMessage(), context,
			extendedLogEntry.getException());
	}

	private synchronized void _log(
		String name, int level, String message, Object context,
		Throwable throwable) {

		if (context == null) {
			context = "";
		}

		Logger log = LoggerFactory.getLogger("osgi.logging.".concat(name));

		if ((level == LogService.LOG_DEBUG) && log.isDebugEnabled()) {
			log.debug(_FORMAT, message, context, throwable);
		}
		else if ((level == LogService.LOG_ERROR) && log.isErrorEnabled()) {
			log.error(_FORMAT, message, context, throwable);
		}
		else if ((level == LogService.LOG_INFO) && log.isInfoEnabled()) {
			log.info(_FORMAT, message, context, throwable);
		}
		else if ((level == LogService.LOG_WARNING) && log.isWarnEnabled()) {
			log.warn(_FORMAT, message, context, throwable);
		}

		if ((throwable != null) && (throwable instanceof BundleException) &&
			(_JENKINS_HOME != null)) {

			String throwableMessage = throwable.getMessage();

			if (throwableMessage.startsWith("Could not resolve module")) {
				log.error(_FORMAT, "Exiting the JVM", context, throwable);

				System.exit(1);
			}
		}
	}

	private static final String _FORMAT = "{} {}";

	private static final String _JENKINS_HOME = System.getenv("JENKINS_HOME");

}