/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EntitlementService}.
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementService
 * @generated
 */
public class EntitlementServiceWrapper
	implements EntitlementService, ServiceWrapper<EntitlementService> {

	public EntitlementServiceWrapper(EntitlementService entitlementService) {
		_entitlementService = entitlementService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _entitlementService.getOSGiServiceIdentifier();
	}

	@Override
	public EntitlementService getWrappedService() {
		return _entitlementService;
	}

	@Override
	public void setWrappedService(EntitlementService entitlementService) {
		_entitlementService = entitlementService;
	}

	private EntitlementService _entitlementService;

}