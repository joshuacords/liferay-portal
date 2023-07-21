/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.membershippolicy;

import com.liferay.portal.kernel.security.membershippolicy.OrganizationMembershipPolicy;
import com.liferay.portal.kernel.security.membershippolicy.OrganizationMembershipPolicyFactory;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

/**
 * @author Sergio González
 * @author Shuyang Zhou
 * @author Peter Fellwock
 */
public class OrganizationMembershipPolicyFactoryImpl
	implements OrganizationMembershipPolicyFactory {

	@Override
	public OrganizationMembershipPolicy getOrganizationMembershipPolicy() {
		return _organizationMembershipPolicyFactoryImpl._serviceTracker.
			getService();
	}

	private OrganizationMembershipPolicyFactoryImpl() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			OrganizationMembershipPolicy.class);

		_serviceTracker.open();
	}

	private static final OrganizationMembershipPolicyFactoryImpl
		_organizationMembershipPolicyFactoryImpl =
			new OrganizationMembershipPolicyFactoryImpl();

	private final ServiceTracker<?, OrganizationMembershipPolicy>
		_serviceTracker;

}