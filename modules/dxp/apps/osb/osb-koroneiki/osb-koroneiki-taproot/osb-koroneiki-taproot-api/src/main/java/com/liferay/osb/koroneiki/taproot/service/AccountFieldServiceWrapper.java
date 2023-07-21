/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountFieldService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountFieldService
 * @generated
 */
public class AccountFieldServiceWrapper
	implements AccountFieldService, ServiceWrapper<AccountFieldService> {

	public AccountFieldServiceWrapper(AccountFieldService accountFieldService) {
		_accountFieldService = accountFieldService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountFieldService.getOSGiServiceIdentifier();
	}

	@Override
	public AccountFieldService getWrappedService() {
		return _accountFieldService;
	}

	@Override
	public void setWrappedService(AccountFieldService accountFieldService) {
		_accountFieldService = accountFieldService;
	}

	private AccountFieldService _accountFieldService;

}