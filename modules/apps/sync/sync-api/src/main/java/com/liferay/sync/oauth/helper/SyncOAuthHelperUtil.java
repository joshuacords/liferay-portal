/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.oauth.helper;

import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(immediate = true, service = SyncOAuthHelperUtil.class)
public class SyncOAuthHelperUtil {

	public static void enableOAuth(
			long companyId, ServiceContext serviceContext)
		throws Exception {

		SyncOAuthHelper syncOAuthHelper = getService();

		syncOAuthHelper.enableOAuth(companyId, serviceContext);
	}

	public static SyncOAuthHelper getService() {
		ServiceReference<SyncOAuthHelper> serviceReference =
			_bundleContext.getServiceReference(SyncOAuthHelper.class);

		if (serviceReference != null) {
			SyncOAuthHelper syncOAuthHelper = _bundleContext.getService(
				serviceReference);

			if (syncOAuthHelper != null) {
				return syncOAuthHelper;
			}
		}

		return new SyncOAuthHelper() {

			public void enableOAuth(
				long companyId, ServiceContext serviceContext) {
			}

			public boolean isDeployed() {
				return false;
			}

			public boolean isOAuthApplicationAvailable(
				long oAuthApplicationId) {

				return false;
			}

		};
	}

	public static boolean isDeployed() {
		SyncOAuthHelper syncOAuthHelper = getService();

		return syncOAuthHelper.isDeployed();
	}

	public static boolean isOAuthApplicationAvailable(long oAuthApplicationId) {
		SyncOAuthHelper syncOAuthService = getService();

		return syncOAuthService.isOAuthApplicationAvailable(oAuthApplicationId);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	private static BundleContext _bundleContext;

}