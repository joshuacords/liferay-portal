/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProductConsumptionService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProductConsumptionService
 * @generated
 */
public class ProductConsumptionServiceWrapper
	implements ProductConsumptionService,
			   ServiceWrapper<ProductConsumptionService> {

	public ProductConsumptionServiceWrapper(
		ProductConsumptionService productConsumptionService) {

		_productConsumptionService = productConsumptionService;
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			addProductConsumption(
				long accountId, long productEntryId, long productPurchaseId,
				java.util.Date startDate, java.util.Date endDate,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.addProductConsumption(
			accountId, productEntryId, productPurchaseId, startDate, endDate,
			productFields);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			addProductConsumption(
				String accountKey, String productEntryKey,
				String productPurchaseKey, java.util.Date startDate,
				java.util.Date endDate,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.addProductConsumption(
			accountKey, productEntryKey, productPurchaseKey, startDate, endDate,
			productFields);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			deleteProductConsumption(long productConsumptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.deleteProductConsumption(
			productConsumptionId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			deleteProductConsumption(long accountId, long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.deleteProductConsumption(
			accountId, productEntryId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			deleteProductConsumption(String productConsumptionKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.deleteProductConsumption(
			productConsumptionKey);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
				getAccountProductConsumptions(
					long accountId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.getAccountProductConsumptions(
			accountId, start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
				getAccountProductConsumptions(
					String accountKey, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.getAccountProductConsumptions(
			accountKey, start, end);
	}

	@Override
	public int getAccountProductConsumptionsCount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.getAccountProductConsumptionsCount(
			accountId);
	}

	@Override
	public int getAccountProductConsumptionsCount(String accountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.getAccountProductConsumptionsCount(
			accountKey);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
				getAccountProductEntryProductConsumptions(
					long accountId, long productEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.
			getAccountProductEntryProductConsumptions(
				accountId, productEntryId);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
				getContactProductConsumptions(
					long contactId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.getContactProductConsumptions(
			contactId, start, end);
	}

	@Override
	public int getContactProductConsumptionsCount(long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.getContactProductConsumptionsCount(
			contactId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _productConsumptionService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			getProductConsumption(long productConsumptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.getProductConsumption(
			productConsumptionId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			getProductConsumption(String productConsumptionKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.getProductConsumption(
			productConsumptionKey);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
				getProductConsumptions(
					String domain, String entityName, String entityId,
					int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.getProductConsumptions(
			domain, entityName, entityId, start, end);
	}

	@Override
	public int getProductConsumptionsCount(
			String domain, String entityName, String entityId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionService.getProductConsumptionsCount(
			domain, entityName, entityId);
	}

	@Override
	public ProductConsumptionService getWrappedService() {
		return _productConsumptionService;
	}

	@Override
	public void setWrappedService(
		ProductConsumptionService productConsumptionService) {

		_productConsumptionService = productConsumptionService;
	}

	private ProductConsumptionService _productConsumptionService;

}