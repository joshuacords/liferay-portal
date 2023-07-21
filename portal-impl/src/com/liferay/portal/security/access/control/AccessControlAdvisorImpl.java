/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.access.control;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.access.control.AccessControlPolicy;
import com.liferay.portal.kernel.security.access.control.AccessControlThreadLocal;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.util.PropsValues;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTracker;
import com.liferay.registry.ServiceTrackerCustomizer;

import java.lang.reflect.Method;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Tomas Polesovsky
 * @author Igor Spasic
 * @author Michael C. Han
 * @author Raymond Augé
 */
public class AccessControlAdvisorImpl implements AccessControlAdvisor {

	@Override
	public void accept(
			Method method, Object[] arguments,
			AccessControlled accessControlled)
		throws SecurityException {

		if (AccessControlThreadLocal.isRemoteAccess()) {
			try {
				for (AccessControlPolicy accessControlPolicy :
						_accessControlPolicies) {

					accessControlPolicy.onServiceRemoteAccess(
						method, arguments, accessControlled);
				}
			}
			catch (SecurityException securityException) {
				if (_log.isDebugEnabled()) {
					_log.debug(securityException, securityException);
				}

				if (PropsValues.ACCESS_CONTROL_SANITIZE_SECURITY_EXCEPTION) {
					throw new SecurityException();
				}

				throw securityException;
			}
		}
		else {
			for (AccessControlPolicy accessControlPolicy :
					_accessControlPolicies) {

				accessControlPolicy.onServiceAccess(
					method, arguments, accessControlled);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccessControlAdvisorImpl.class.getName());

	private static final List<AccessControlPolicy> _accessControlPolicies =
		new CopyOnWriteArrayList<>();
	private static final ServiceTracker<?, ?> _serviceTracker;

	private static class AccessControlPolicyTrackerCustomizer
		implements ServiceTrackerCustomizer
			<AccessControlPolicy, AccessControlPolicy> {

		@Override
		public AccessControlPolicy addingService(
			ServiceReference<AccessControlPolicy> serviceReference) {

			Registry registry = RegistryUtil.getRegistry();

			AccessControlPolicy accessControlPolicy = registry.getService(
				serviceReference);

			_accessControlPolicies.add(accessControlPolicy);

			return accessControlPolicy;
		}

		@Override
		public void modifiedService(
			ServiceReference<AccessControlPolicy> serviceReference,
			AccessControlPolicy accessControlPolicy) {
		}

		@Override
		public void removedService(
			ServiceReference<AccessControlPolicy> serviceReference,
			AccessControlPolicy accessControlPolicy) {

			Registry registry = RegistryUtil.getRegistry();

			registry.ungetService(serviceReference);

			_accessControlPolicies.remove(accessControlPolicy);
		}

	}

	static {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			AccessControlPolicy.class,
			new AccessControlPolicyTrackerCustomizer());

		_serviceTracker.open();
	}

}