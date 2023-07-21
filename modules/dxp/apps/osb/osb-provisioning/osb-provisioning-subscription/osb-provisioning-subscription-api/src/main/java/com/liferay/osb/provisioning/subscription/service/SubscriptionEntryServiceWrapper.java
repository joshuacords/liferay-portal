/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.subscription.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SubscriptionEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see SubscriptionEntryService
 * @generated
 */
public class SubscriptionEntryServiceWrapper
	implements ServiceWrapper<SubscriptionEntryService>,
			   SubscriptionEntryService {

	public SubscriptionEntryServiceWrapper(
		SubscriptionEntryService subscriptionEntryService) {

		_subscriptionEntryService = subscriptionEntryService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _subscriptionEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public SubscriptionEntryService getWrappedService() {
		return _subscriptionEntryService;
	}

	@Override
	public void setWrappedService(
		SubscriptionEntryService subscriptionEntryService) {

		_subscriptionEntryService = subscriptionEntryService;
	}

	private SubscriptionEntryService _subscriptionEntryService;

}