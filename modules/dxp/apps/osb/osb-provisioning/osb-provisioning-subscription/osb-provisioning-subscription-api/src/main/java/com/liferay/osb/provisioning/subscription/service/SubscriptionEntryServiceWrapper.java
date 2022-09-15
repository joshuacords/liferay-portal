/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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