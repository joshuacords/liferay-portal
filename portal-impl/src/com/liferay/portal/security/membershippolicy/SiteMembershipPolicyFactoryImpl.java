/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.membershippolicy;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.membershippolicy.SiteMembershipPolicy;
import com.liferay.portal.kernel.security.membershippolicy.SiteMembershipPolicyFactory;
import com.liferay.portal.util.PropsValues;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;

/**
 * @author Sergio González
 * @author Shuyang Zhou
 * @author Roberto Díaz
 * @author Peter Fellwock
 */
public class SiteMembershipPolicyFactoryImpl
	implements SiteMembershipPolicyFactory {

	@Override
	public SiteMembershipPolicy getSiteMembershipPolicy() {
		return _siteMembershipPolicyFactoryImpl._serviceTracker.getService();
	}

	private SiteMembershipPolicyFactoryImpl() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			SiteMembershipPolicy.class,
			new SiteMembershipPolicyTrackerCustomizer());

		_serviceTracker.open();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SiteMembershipPolicyFactoryImpl.class);

	private static final SiteMembershipPolicyFactoryImpl
		_siteMembershipPolicyFactoryImpl =
			new SiteMembershipPolicyFactoryImpl();

	private final ServiceTracker<?, SiteMembershipPolicy> _serviceTracker;

	private static class SiteMembershipPolicyTrackerCustomizer
		implements ServiceTrackerCustomizer
			<SiteMembershipPolicy, SiteMembershipPolicy> {

		@Override
		public SiteMembershipPolicy addingService(
			ServiceReference<SiteMembershipPolicy> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			SiteMembershipPolicy siteMembershipPolicy = registry.getService(
				serviceReference);

			if (PropsValues.MEMBERSHIP_POLICY_AUTO_VERIFY) {
				try {
					siteMembershipPolicy.verifyPolicy();
				}
				catch (PortalException portalException) {
					_log.error(portalException, portalException);
				}
			}

			return siteMembershipPolicy;
		}

		@Override
		public void modifiedService(
			ServiceReference<SiteMembershipPolicy> serviceReference,
			SiteMembershipPolicy siteMembershipPolicy) {
		}

		@Override
		public void removedService(
			ServiceReference<SiteMembershipPolicy> serviceReference,
			SiteMembershipPolicy siteMembershipPolicy) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);
		}

	}

}