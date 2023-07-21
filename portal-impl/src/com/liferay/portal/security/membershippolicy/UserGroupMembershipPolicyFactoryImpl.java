/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.membershippolicy;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.membershippolicy.UserGroupMembershipPolicy;
import com.liferay.portal.kernel.security.membershippolicy.UserGroupMembershipPolicyFactory;
import com.liferay.portal.util.PropsValues;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;

/**
 * @author Sergio González
 * @author Shuyang Zhou
 * @author Peter Fellwock
 */
public class UserGroupMembershipPolicyFactoryImpl
	implements UserGroupMembershipPolicyFactory {

	@Override
	public UserGroupMembershipPolicy getUserGroupMembershipPolicy() {
		return _userGroupMembershipPolicyFactoryImpl._serviceTracker.
			getService();
	}

	private UserGroupMembershipPolicyFactoryImpl() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			UserGroupMembershipPolicy.class,
			new UserGroupMembershipPolicyTrackerCustomizer());

		_serviceTracker.open();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserGroupMembershipPolicyFactoryImpl.class);

	private static final UserGroupMembershipPolicyFactoryImpl
		_userGroupMembershipPolicyFactoryImpl =
			new UserGroupMembershipPolicyFactoryImpl();

	private final ServiceTracker<?, UserGroupMembershipPolicy> _serviceTracker;

	private static class UserGroupMembershipPolicyTrackerCustomizer
		implements ServiceTrackerCustomizer
			<UserGroupMembershipPolicy, UserGroupMembershipPolicy> {

		@Override
		public UserGroupMembershipPolicy addingService(
			ServiceReference<UserGroupMembershipPolicy> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			UserGroupMembershipPolicy userGroupMembershipPolicy =
				registry.getService(serviceReference);

			if (PropsValues.MEMBERSHIP_POLICY_AUTO_VERIFY) {
				try {
					userGroupMembershipPolicy.verifyPolicy();
				}
				catch (PortalException portalException) {
					_log.error(portalException, portalException);
				}
			}

			return userGroupMembershipPolicy;
		}

		@Override
		public void modifiedService(
			ServiceReference<UserGroupMembershipPolicy> serviceReference,
			UserGroupMembershipPolicy userGroupMembershipPolicy) {
		}

		@Override
		public void removedService(
			ServiceReference<UserGroupMembershipPolicy> serviceReference,
			UserGroupMembershipPolicy userGroupMembershipPolicy) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);
		}

	}

}