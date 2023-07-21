/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.staging.internal.activator;

import com.liferay.staging.StagingGroupHelper;
import com.liferay.staging.StagingGroupHelperUtil;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Máté Thurzó
 */
public class StagingImplBundleActivator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		_serviceTracker =
			new ServiceTracker<StagingGroupHelper, StagingGroupHelper>(
				bundleContext, StagingGroupHelper.class.getName(), null) {

				@Override
				public StagingGroupHelper addingService(
					ServiceReference<StagingGroupHelper> serviceReference) {

					StagingGroupHelper stagingGroupHelper =
						bundleContext.getService(serviceReference);

					StagingGroupHelperUtil.setStagingGroupHelper(
						stagingGroupHelper);

					return stagingGroupHelper;
				}

			};

		_serviceTracker.open();
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		_serviceTracker.close();
	}

	private ServiceTracker<StagingGroupHelper, StagingGroupHelper>
		_serviceTracker;

}