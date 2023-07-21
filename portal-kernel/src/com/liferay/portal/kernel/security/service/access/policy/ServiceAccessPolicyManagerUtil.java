/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.service.access.policy;

import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import java.util.List;

/**
 * @author Mika Koivisto
 */
public class ServiceAccessPolicyManagerUtil {

	public static String getDefaultApplicationServiceAccessPolicyName(
		long companyId) {

		return getServiceAccessPolicyManager().
			getDefaultApplicationServiceAccessPolicyName(companyId);
	}

	public static String getDefaultUserServiceAccessPolicyName(long companyId) {
		return getServiceAccessPolicyManager().
			getDefaultUserServiceAccessPolicyName(companyId);
	}

	public static List<ServiceAccessPolicy> getServiceAccessPolicies(
		long companyId, int start, int end) {

		return getServiceAccessPolicyManager().getServiceAccessPolicies(
			companyId, start, end);
	}

	public static int getServiceAccessPoliciesCount(long companyId) {
		return getServiceAccessPolicyManager().getServiceAccessPoliciesCount(
			companyId);
	}

	public static ServiceAccessPolicy getServiceAccessPolicy(
		long companyId, String name) {

		return getServiceAccessPolicyManager().getServiceAccessPolicy(
			companyId, name);
	}

	public static ServiceAccessPolicyManager getServiceAccessPolicyManager() {
		return _serviceAccessPolicyManagerUtil._serviceTracker.getService();
	}

	private ServiceAccessPolicyManagerUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			ServiceAccessPolicyManager.class);

		_serviceTracker.open();
	}

	private static final ServiceAccessPolicyManagerUtil
		_serviceAccessPolicyManagerUtil = new ServiceAccessPolicyManagerUtil();

	private final ServiceTracker<?, ServiceAccessPolicyManager> _serviceTracker;

}