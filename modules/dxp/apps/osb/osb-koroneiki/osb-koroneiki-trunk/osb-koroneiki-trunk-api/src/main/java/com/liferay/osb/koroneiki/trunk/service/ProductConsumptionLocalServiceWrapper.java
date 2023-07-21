/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProductConsumptionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProductConsumptionLocalService
 * @generated
 */
public class ProductConsumptionLocalServiceWrapper
	implements ProductConsumptionLocalService,
			   ServiceWrapper<ProductConsumptionLocalService> {

	public ProductConsumptionLocalServiceWrapper(
		ProductConsumptionLocalService productConsumptionLocalService) {

		_productConsumptionLocalService = productConsumptionLocalService;
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			addProductConsumption(
				long userId, long accountId, long productEntryId,
				long productPurchaseId, java.util.Date startDate,
				java.util.Date endDate,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.addProductConsumption(
			userId, accountId, productEntryId, productPurchaseId, startDate,
			endDate, productFields);
	}

	/**
	 * Adds the product consumption to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductConsumptionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productConsumption the product consumption
	 * @return the product consumption that was added
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
		addProductConsumption(
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption
				productConsumption) {

		return _productConsumptionLocalService.addProductConsumption(
			productConsumption);
	}

	/**
	 * Creates a new product consumption with the primary key. Does not add the product consumption to the database.
	 *
	 * @param productConsumptionId the primary key for the new product consumption
	 * @return the new product consumption
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
		createProductConsumption(long productConsumptionId) {

		return _productConsumptionLocalService.createProductConsumption(
			productConsumptionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the product consumption with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductConsumptionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productConsumptionId the primary key of the product consumption
	 * @return the product consumption that was removed
	 * @throws PortalException if a product consumption with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			deleteProductConsumption(long productConsumptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.deleteProductConsumption(
			productConsumptionId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			deleteProductConsumption(
				long userId, long accountId, long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.deleteProductConsumption(
			userId, accountId, productEntryId);
	}

	/**
	 * Deletes the product consumption from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductConsumptionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productConsumption the product consumption
	 * @return the product consumption that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			deleteProductConsumption(
				com.liferay.osb.koroneiki.trunk.model.ProductConsumption
					productConsumption)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.deleteProductConsumption(
			productConsumption);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _productConsumptionLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _productConsumptionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _productConsumptionLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _productConsumptionLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _productConsumptionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _productConsumptionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
		fetchProductConsumption(long productConsumptionId) {

		return _productConsumptionLocalService.fetchProductConsumption(
			productConsumptionId);
	}

	/**
	 * Returns the product consumption with the matching UUID and company.
	 *
	 * @param uuid the product consumption's UUID
	 * @param companyId the primary key of the company
	 * @return the matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
		fetchProductConsumptionByUuidAndCompanyId(String uuid, long companyId) {

		return _productConsumptionLocalService.
			fetchProductConsumptionByUuidAndCompanyId(uuid, companyId);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
				getAccountProductConsumptions(
					long accountId, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.getAccountProductConsumptions(
			accountId, start, end);
	}

	@Override
	public int getAccountProductConsumptionsCount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.
			getAccountProductConsumptionsCount(accountId);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
			getAccountProductEntryProductConsumptions(
				long accountId, long productEntryId) {

		return _productConsumptionLocalService.
			getAccountProductEntryProductConsumptions(
				accountId, productEntryId);
	}

	@Override
	public int getAccountProductEntryProductConsumptionsCount(
		long accountId, long productEntryId) {

		return _productConsumptionLocalService.
			getAccountProductEntryProductConsumptionsCount(
				accountId, productEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _productConsumptionLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
			getContactProductConsumptions(long contactId, int start, int end) {

		return _productConsumptionLocalService.getContactProductConsumptions(
			contactId, start, end);
	}

	@Override
	public int getContactProductConsumptionsCount(long contactId) {
		return _productConsumptionLocalService.
			getContactProductConsumptionsCount(contactId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _productConsumptionLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _productConsumptionLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _productConsumptionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the product consumption with the primary key.
	 *
	 * @param productConsumptionId the primary key of the product consumption
	 * @return the product consumption
	 * @throws PortalException if a product consumption with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			getProductConsumption(long productConsumptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.getProductConsumption(
			productConsumptionId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			getProductConsumption(String productConsumptionKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.getProductConsumption(
			productConsumptionKey);
	}

	/**
	 * Returns the product consumption with the matching UUID and company.
	 *
	 * @param uuid the product consumption's UUID
	 * @param companyId the primary key of the company
	 * @return the matching product consumption
	 * @throws PortalException if a matching product consumption could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
			getProductConsumptionByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.
			getProductConsumptionByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of all the product consumptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of product consumptions
	 */
	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
			getProductConsumptions(int start, int end) {

		return _productConsumptionLocalService.getProductConsumptions(
			start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.trunk.model.ProductConsumption>
				getProductConsumptions(
					long userId, long accountId, long productEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.getProductConsumptions(
			userId, accountId, productEntryId);
	}

	/**
	 * Returns the number of product consumptions.
	 *
	 * @return the number of product consumptions
	 */
	@Override
	public int getProductConsumptionsCount() {
		return _productConsumptionLocalService.getProductConsumptionsCount();
	}

	@Override
	public int getProductEntryProductConsumptionsCount(long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.
			getProductEntryProductConsumptionsCount(productEntryId);
	}

	@Override
	public int getProductPurchaseProductConsumptionsCount(
			long productPurchaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.
			getProductPurchaseProductConsumptionsCount(productPurchaseId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption reindex(
			long productConsumptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.reindex(productConsumptionId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(
			long companyId, String keywords, int start, int end,
			com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productConsumptionLocalService.search(
			companyId, keywords, start, end, sort);
	}

	/**
	 * Updates the product consumption in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductConsumptionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productConsumption the product consumption
	 * @return the product consumption that was updated
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductConsumption
		updateProductConsumption(
			com.liferay.osb.koroneiki.trunk.model.ProductConsumption
				productConsumption) {

		return _productConsumptionLocalService.updateProductConsumption(
			productConsumption);
	}

	@Override
	public ProductConsumptionLocalService getWrappedService() {
		return _productConsumptionLocalService;
	}

	@Override
	public void setWrappedService(
		ProductConsumptionLocalService productConsumptionLocalService) {

		_productConsumptionLocalService = productConsumptionLocalService;
	}

	private ProductConsumptionLocalService _productConsumptionLocalService;

}