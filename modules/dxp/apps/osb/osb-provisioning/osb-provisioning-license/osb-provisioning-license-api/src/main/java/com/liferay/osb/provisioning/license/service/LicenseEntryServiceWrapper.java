/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LicenseEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryService
 * @generated
 */
public class LicenseEntryServiceWrapper
	implements LicenseEntryService, ServiceWrapper<LicenseEntryService> {

	public LicenseEntryServiceWrapper(LicenseEntryService licenseEntryService) {
		_licenseEntryService = licenseEntryService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _licenseEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public LicenseEntryService getWrappedService() {
		return _licenseEntryService;
	}

	@Override
	public void setWrappedService(LicenseEntryService licenseEntryService) {
		_licenseEntryService = licenseEntryService;
	}

	private LicenseEntryService _licenseEntryService;

}