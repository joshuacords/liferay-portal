/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommonLicenseKeyService}.
 *
 * @author Brian Wing Shun Chan
 * @see CommonLicenseKeyService
 * @generated
 */
public class CommonLicenseKeyServiceWrapper
	implements CommonLicenseKeyService,
			   ServiceWrapper<CommonLicenseKeyService> {

	public CommonLicenseKeyServiceWrapper(
		CommonLicenseKeyService commonLicenseKeyService) {

		_commonLicenseKeyService = commonLicenseKeyService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commonLicenseKeyService.getOSGiServiceIdentifier();
	}

	@Override
	public CommonLicenseKeyService getWrappedService() {
		return _commonLicenseKeyService;
	}

	@Override
	public void setWrappedService(
		CommonLicenseKeyService commonLicenseKeyService) {

		_commonLicenseKeyService = commonLicenseKeyService;
	}

	private CommonLicenseKeyService _commonLicenseKeyService;

}