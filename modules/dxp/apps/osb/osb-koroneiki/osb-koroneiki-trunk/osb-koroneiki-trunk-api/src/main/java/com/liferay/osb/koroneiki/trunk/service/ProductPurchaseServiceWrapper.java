/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProductPurchaseService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProductPurchaseService
 * @generated
 */
public class ProductPurchaseServiceWrapper
	implements ProductPurchaseService, ServiceWrapper<ProductPurchaseService> {

	public ProductPurchaseServiceWrapper(
		ProductPurchaseService productPurchaseService) {

		_productPurchaseService = productPurchaseService;
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			addProductPurchase(
				long accountId, long productEntryId, java.util.Date startDate,
				java.util.Date endDate, java.util.Date originalEndDate,
				int quantity, int status,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.addProductPurchase(
			accountId, productEntryId, startDate, endDate, originalEndDate,
			quantity, status, productFields);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			addProductPurchase(
				String accountKey, String productEntryKey,
				java.util.Date startDate, java.util.Date endDate,
				java.util.Date originalEndDate, int quantity, int status,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.addProductPurchase(
			accountKey, productEntryKey, startDate, endDate, originalEndDate,
			quantity, status, productFields);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			deleteProductPurchase(long productPurchaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.deleteProductPurchase(productPurchaseId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			deleteProductPurchase(String productPurchaseKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.deleteProductPurchase(
			productPurchaseKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
			getAccountProductEntryProductPurchases(
				long accountId, long productEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getAccountProductEntryProductPurchases(
			accountId, productEntryId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
			getAccountProductPurchases(long accountId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getAccountProductPurchases(
			accountId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
			getAccountProductPurchases(String accountKey, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getAccountProductPurchases(
			accountKey, start, end);
	}

	@Override
	public int getAccountProductPurchasesCount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getAccountProductPurchasesCount(
			accountId);
	}

	@Override
	public int getAccountProductPurchasesCount(String accountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getAccountProductPurchasesCount(
			accountKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
			getContactProductPurchases(long contactId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getContactProductPurchases(
			contactId, start, end);
	}

	@Override
	public int getContactProductPurchasesCount(long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getContactProductPurchasesCount(
			contactId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _productPurchaseService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			getProductPurchase(long productPurchaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getProductPurchase(productPurchaseId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			getProductPurchase(String productPurchaseKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getProductPurchase(productPurchaseKey);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductPurchase>
			getProductPurchases(
				String domain, String entityName, String entityId, int start,
				int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getProductPurchases(
			domain, entityName, entityId, start, end);
	}

	@Override
	public int getProductPurchasesCount(
			String domain, String entityName, String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.getProductPurchasesCount(
			domain, entityName, entityId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductPurchase
			updateProductPurchase(
				long productPurchaseId, java.util.Date startDate,
				java.util.Date endDate, java.util.Date originalEndDate,
				int quantity, int status,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productPurchaseService.updateProductPurchase(
			productPurchaseId, startDate, endDate, originalEndDate, quantity,
			status, productFields);
	}

	@Override
	public ProductPurchaseService getWrappedService() {
		return _productPurchaseService;
	}

	@Override
	public void setWrappedService(
		ProductPurchaseService productPurchaseService) {

		_productPurchaseService = productPurchaseService;
	}

	private ProductPurchaseService _productPurchaseService;

}