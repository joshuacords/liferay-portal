/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProductFieldService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProductFieldService
 * @generated
 */
public class ProductFieldServiceWrapper
	implements ProductFieldService, ServiceWrapper<ProductFieldService> {

	public ProductFieldServiceWrapper(ProductFieldService productFieldService) {
		_productFieldService = productFieldService;
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductField addProductField(
			long classNameId, long classPK, String name, String value)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productFieldService.addProductField(
			classNameId, classPK, name, value);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductField
			deleteProductField(long productFieldId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productFieldService.deleteProductField(productFieldId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _productFieldService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductField
			updateProductField(long productFieldId, String value)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productFieldService.updateProductField(productFieldId, value);
	}

	@Override
	public ProductFieldService getWrappedService() {
		return _productFieldService;
	}

	@Override
	public void setWrappedService(ProductFieldService productFieldService) {
		_productFieldService = productFieldService;
	}

	private ProductFieldService _productFieldService;

}