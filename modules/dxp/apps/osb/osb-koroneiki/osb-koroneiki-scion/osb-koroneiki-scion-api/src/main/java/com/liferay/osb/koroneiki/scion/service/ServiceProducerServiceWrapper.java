/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ServiceProducerService}.
 *
 * @author Brian Wing Shun Chan
 * @see ServiceProducerService
 * @generated
 */
public class ServiceProducerServiceWrapper
	implements ServiceProducerService, ServiceWrapper<ServiceProducerService> {

	public ServiceProducerServiceWrapper(
		ServiceProducerService serviceProducerService) {

		_serviceProducerService = serviceProducerService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _serviceProducerService.getOSGiServiceIdentifier();
	}

	@Override
	public ServiceProducerService getWrappedService() {
		return _serviceProducerService;
	}

	@Override
	public void setWrappedService(
		ServiceProducerService serviceProducerService) {

		_serviceProducerService = serviceProducerService;
	}

	private ServiceProducerService _serviceProducerService;

}