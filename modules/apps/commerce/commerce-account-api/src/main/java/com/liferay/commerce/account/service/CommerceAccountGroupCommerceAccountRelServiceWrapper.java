/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.account.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceAccountGroupCommerceAccountRelService}.
 *
 * @author Marco Leo
 * @see CommerceAccountGroupCommerceAccountRelService
 * @generated
 */
public class CommerceAccountGroupCommerceAccountRelServiceWrapper
	implements CommerceAccountGroupCommerceAccountRelService,
			   ServiceWrapper<CommerceAccountGroupCommerceAccountRelService> {

	public CommerceAccountGroupCommerceAccountRelServiceWrapper(
		CommerceAccountGroupCommerceAccountRelService
			commerceAccountGroupCommerceAccountRelService) {

		_commerceAccountGroupCommerceAccountRelService =
			commerceAccountGroupCommerceAccountRelService;
	}

	@Override
	public
		com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel
					addCommerceAccountGroupCommerceAccountRel(
						long commerceAccountGroupId, long commerceAccountId,
						com.liferay.portal.kernel.service.ServiceContext
							serviceContext)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupCommerceAccountRelService.
			addCommerceAccountGroupCommerceAccountRel(
				commerceAccountGroupId, commerceAccountId, serviceContext);
	}

	@Override
	public void deleteCommerceAccountGroupCommerceAccountRel(
			long commerceAccountGroupCommerceAccountRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceAccountGroupCommerceAccountRelService.
			deleteCommerceAccountGroupCommerceAccountRel(
				commerceAccountGroupCommerceAccountRelId);
	}

	@Override
	public
		com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel
					getCommerceAccountGroupCommerceAccountRel(
						long commerceAccountGroupId, long commerceAccountId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupCommerceAccountRelService.
			getCommerceAccountGroupCommerceAccountRel(
				commerceAccountGroupId, commerceAccountId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.account.model.
			CommerceAccountGroupCommerceAccountRel>
					getCommerceAccountGroupCommerceAccountRels(
						long commerceAccountGroupId, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupCommerceAccountRelService.
			getCommerceAccountGroupCommerceAccountRels(
				commerceAccountGroupId, start, end);
	}

	@Override
	public int getCommerceAccountGroupCommerceAccountRelsCount(
			long commerceAccountGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceAccountGroupCommerceAccountRelService.
			getCommerceAccountGroupCommerceAccountRelsCount(
				commerceAccountGroupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceAccountGroupCommerceAccountRelService.
			getOSGiServiceIdentifier();
	}

	@Override
	public CommerceAccountGroupCommerceAccountRelService getWrappedService() {
		return _commerceAccountGroupCommerceAccountRelService;
	}

	@Override
	public void setWrappedService(
		CommerceAccountGroupCommerceAccountRelService
			commerceAccountGroupCommerceAccountRelService) {

		_commerceAccountGroupCommerceAccountRelService =
			commerceAccountGroupCommerceAccountRelService;
	}

	private CommerceAccountGroupCommerceAccountRelService
		_commerceAccountGroupCommerceAccountRelService;

}