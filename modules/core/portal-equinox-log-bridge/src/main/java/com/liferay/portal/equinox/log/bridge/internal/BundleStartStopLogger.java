/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.equinox.log.bridge.internal;

import java.util.concurrent.atomic.AtomicBoolean;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.SynchronousBundleListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Raymond Augé
 */
public class BundleStartStopLogger implements SynchronousBundleListener {

	public BundleStartStopLogger(AtomicBoolean atomicBoolean) {
		_portalStarted = atomicBoolean;
	}

	@Override
	public void bundleChanged(BundleEvent bundleEvent) {
		Bundle bundle = bundleEvent.getBundle();

		if (bundle.getSymbolicName() == null) {
			_log.error("{} has a null symbolic name", bundle.getLocation());
		}

		if (_portalStarted.get()) {
			if (_log.isInfoEnabled()) {
				if (bundleEvent.getType() == BundleEvent.STARTED) {
					_log.info("STARTED {}", bundle);
				}
				else if (bundleEvent.getType() == BundleEvent.STOPPED) {
					_log.info("STOPPED {}", bundle);
				}
			}
		}
		else if (_log.isDebugEnabled()) {
			if (bundleEvent.getType() == BundleEvent.STARTED) {
				_log.debug("STARTED {}", bundle);
			}
			else if (bundleEvent.getType() == BundleEvent.STOPPED) {
				_log.debug("STOPPED {}", bundle);
			}
		}
	}

	private static final Logger _log = LoggerFactory.getLogger(
		BundleStartStopLogger.class);

	private final AtomicBoolean _portalStarted;

}