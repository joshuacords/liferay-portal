/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.membershippolicy;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.membershippolicy.RoleMembershipPolicy;
import com.liferay.portal.kernel.security.membershippolicy.RoleMembershipPolicyFactory;
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
public class RoleMembershipPolicyFactoryImpl
	implements RoleMembershipPolicyFactory {

	@Override
	public RoleMembershipPolicy getRoleMembershipPolicy() {
		return _roleMembershipPolicyFactoryImpl._serviceTracker.getService();
	}

	private RoleMembershipPolicyFactoryImpl() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			RoleMembershipPolicy.class,
			new RoleMembershipPolicyTrackerCustomizer());

		_serviceTracker.open();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RoleMembershipPolicyFactoryImpl.class);

	private static final RoleMembershipPolicyFactoryImpl
		_roleMembershipPolicyFactoryImpl =
			new RoleMembershipPolicyFactoryImpl();

	private final ServiceTracker<?, RoleMembershipPolicy> _serviceTracker;

	private static class RoleMembershipPolicyTrackerCustomizer
		implements ServiceTrackerCustomizer
			<RoleMembershipPolicy, RoleMembershipPolicy> {

		@Override
		public RoleMembershipPolicy addingService(
			ServiceReference<RoleMembershipPolicy> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			RoleMembershipPolicy roleMembershipPolicy = registry.getService(
				serviceReference);

			if (PropsValues.MEMBERSHIP_POLICY_AUTO_VERIFY) {
				try {
					roleMembershipPolicy.verifyPolicy();
				}
				catch (PortalException portalException) {
					_log.error(portalException, portalException);
				}
			}

			return roleMembershipPolicy;
		}

		@Override
		public void modifiedService(
			ServiceReference<RoleMembershipPolicy> serviceReference,
			RoleMembershipPolicy roleMembershipPolicy) {
		}

		@Override
		public void removedService(
			ServiceReference<RoleMembershipPolicy> serviceReference,
			RoleMembershipPolicy roleMembershipPolicy) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);
		}

	}

}