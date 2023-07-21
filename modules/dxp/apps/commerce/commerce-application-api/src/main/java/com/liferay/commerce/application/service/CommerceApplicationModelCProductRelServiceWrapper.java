/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.application.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceApplicationModelCProductRelService}.
 *
 * @author Luca Pellizzon
 * @see CommerceApplicationModelCProductRelService
 * @generated
 */
public class CommerceApplicationModelCProductRelServiceWrapper
	implements CommerceApplicationModelCProductRelService,
			   ServiceWrapper<CommerceApplicationModelCProductRelService> {

	public CommerceApplicationModelCProductRelServiceWrapper(
		CommerceApplicationModelCProductRelService
			commerceApplicationModelCProductRelService) {

		_commerceApplicationModelCProductRelService =
			commerceApplicationModelCProductRelService;
	}

	@Override
	public
		com.liferay.commerce.application.model.
			CommerceApplicationModelCProductRel
					addCommerceApplicationModelCProductRel(
						long userId, long commerceApplicationModelId,
						long cProductId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceApplicationModelCProductRelService.
			addCommerceApplicationModelCProductRel(
				userId, commerceApplicationModelId, cProductId);
	}

	@Override
	public void deleteCommerceApplicationModelCProductRel(
			long commerceApplicationModelCProductRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceApplicationModelCProductRelService.
			deleteCommerceApplicationModelCProductRel(
				commerceApplicationModelCProductRelId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.application.model.
			CommerceApplicationModelCProductRel>
					getCommerceApplicationModelCProductRels(
						long commerceApplicationModelId, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceApplicationModelCProductRelService.
			getCommerceApplicationModelCProductRels(
				commerceApplicationModelId, start, end);
	}

	@Override
	public int getCommerceApplicationModelCProductRelsCount(
			long commerceApplicationModelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceApplicationModelCProductRelService.
			getCommerceApplicationModelCProductRelsCount(
				commerceApplicationModelId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceApplicationModelCProductRelService.
			getOSGiServiceIdentifier();
	}

	@Override
	public CommerceApplicationModelCProductRelService getWrappedService() {
		return _commerceApplicationModelCProductRelService;
	}

	@Override
	public void setWrappedService(
		CommerceApplicationModelCProductRelService
			commerceApplicationModelCProductRelService) {

		_commerceApplicationModelCProductRelService =
			commerceApplicationModelCProductRelService;
	}

	private CommerceApplicationModelCProductRelService
		_commerceApplicationModelCProductRelService;

}