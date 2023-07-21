/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProductBundleProductsLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleProductsLocalService
 * @generated
 */
public class ProductBundleProductsLocalServiceWrapper
	implements ProductBundleProductsLocalService,
			   ServiceWrapper<ProductBundleProductsLocalService> {

	public ProductBundleProductsLocalServiceWrapper(
		ProductBundleProductsLocalService productBundleProductsLocalService) {

		_productBundleProductsLocalService = productBundleProductsLocalService;
	}

	@Override
	public com.liferay.osb.provisioning.model.ProductBundleProducts
			addProductBundleProducts(long productBundleId, String productKey)
		throws Exception {

		return _productBundleProductsLocalService.addProductBundleProducts(
			productBundleId, productKey);
	}

	/**
	 * Adds the product bundle products to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductBundleProductsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productBundleProducts the product bundle products
	 * @return the product bundle products that was added
	 */
	@Override
	public com.liferay.osb.provisioning.model.ProductBundleProducts
		addProductBundleProducts(
			com.liferay.osb.provisioning.model.ProductBundleProducts
				productBundleProducts) {

		return _productBundleProductsLocalService.addProductBundleProducts(
			productBundleProducts);
	}

	/**
	 * Creates a new product bundle products with the primary key. Does not add the product bundle products to the database.
	 *
	 * @param productBundleProductsPK the primary key for the new product bundle products
	 * @return the new product bundle products
	 */
	@Override
	public com.liferay.osb.provisioning.model.ProductBundleProducts
		createProductBundleProducts(
			com.liferay.osb.provisioning.service.persistence.
				ProductBundleProductsPK productBundleProductsPK) {

		return _productBundleProductsLocalService.createProductBundleProducts(
			productBundleProductsPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productBundleProductsLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.osb.provisioning.model.ProductBundleProducts
			deleteProductBundleProducts(long productBundleId, String productKey)
		throws Exception {

		return _productBundleProductsLocalService.deleteProductBundleProducts(
			productBundleId, productKey);
	}

	/**
	 * Deletes the product bundle products from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductBundleProductsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productBundleProducts the product bundle products
	 * @return the product bundle products that was removed
	 */
	@Override
	public com.liferay.osb.provisioning.model.ProductBundleProducts
		deleteProductBundleProducts(
			com.liferay.osb.provisioning.model.ProductBundleProducts
				productBundleProducts) {

		return _productBundleProductsLocalService.deleteProductBundleProducts(
			productBundleProducts);
	}

	/**
	 * Deletes the product bundle products with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductBundleProductsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products that was removed
	 * @throws PortalException if a product bundle products with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.model.ProductBundleProducts
			deleteProductBundleProducts(
				com.liferay.osb.provisioning.service.persistence.
					ProductBundleProductsPK productBundleProductsPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productBundleProductsLocalService.deleteProductBundleProducts(
			productBundleProductsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _productBundleProductsLocalService.dynamicQuery();
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

		return _productBundleProductsLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.model.impl.ProductBundleProductsModelImpl</code>.
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

		return _productBundleProductsLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.model.impl.ProductBundleProductsModelImpl</code>.
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

		return _productBundleProductsLocalService.dynamicQuery(
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

		return _productBundleProductsLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _productBundleProductsLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.provisioning.model.ProductBundleProducts
		fetchProductBundleProducts(
			com.liferay.osb.provisioning.service.persistence.
				ProductBundleProductsPK productBundleProductsPK) {

		return _productBundleProductsLocalService.fetchProductBundleProducts(
			productBundleProductsPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _productBundleProductsLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _productBundleProductsLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _productBundleProductsLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productBundleProductsLocalService.getPersistedModel(
			primaryKeyObj);
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product>
				getProductBundleAssignedProducts(long productBundleId)
			throws Exception {

		return _productBundleProductsLocalService.
			getProductBundleAssignedProducts(productBundleId);
	}

	@Override
	public java.util.List
		<com.liferay.osb.provisioning.model.ProductBundleProducts>
			getProductBundleProducts(long productBundleId) {

		return _productBundleProductsLocalService.getProductBundleProducts(
			productBundleId);
	}

	/**
	 * Returns the product bundle products with the primary key.
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products
	 * @throws PortalException if a product bundle products with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.model.ProductBundleProducts
			getProductBundleProducts(
				com.liferay.osb.provisioning.service.persistence.
					ProductBundleProductsPK productBundleProductsPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productBundleProductsLocalService.getProductBundleProducts(
			productBundleProductsPK);
	}

	@Override
	public java.util.List
		<com.liferay.osb.provisioning.model.ProductBundleProducts>
			getProductBundleProducts(String productKey) {

		return _productBundleProductsLocalService.getProductBundleProducts(
			productKey);
	}

	@Override
	public int getProductBundleProductsCount(long productBundleId) {
		return _productBundleProductsLocalService.getProductBundleProductsCount(
			productBundleId);
	}

	@Override
	public int getProductBundleProductsCount(String productKey) {
		return _productBundleProductsLocalService.getProductBundleProductsCount(
			productKey);
	}

	/**
	 * Returns a range of all the product bundle productses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.model.impl.ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @return the range of product bundle productses
	 */
	@Override
	public java.util.List
		<com.liferay.osb.provisioning.model.ProductBundleProducts>
			getProductBundleProductses(int start, int end) {

		return _productBundleProductsLocalService.getProductBundleProductses(
			start, end);
	}

	/**
	 * Returns the number of product bundle productses.
	 *
	 * @return the number of product bundle productses
	 */
	@Override
	public int getProductBundleProductsesCount() {
		return _productBundleProductsLocalService.
			getProductBundleProductsesCount();
	}

	/**
	 * Updates the product bundle products in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductBundleProductsLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productBundleProducts the product bundle products
	 * @return the product bundle products that was updated
	 */
	@Override
	public com.liferay.osb.provisioning.model.ProductBundleProducts
		updateProductBundleProducts(
			com.liferay.osb.provisioning.model.ProductBundleProducts
				productBundleProducts) {

		return _productBundleProductsLocalService.updateProductBundleProducts(
			productBundleProducts);
	}

	@Override
	public ProductBundleProductsLocalService getWrappedService() {
		return _productBundleProductsLocalService;
	}

	@Override
	public void setWrappedService(
		ProductBundleProductsLocalService productBundleProductsLocalService) {

		_productBundleProductsLocalService = productBundleProductsLocalService;
	}

	private ProductBundleProductsLocalService
		_productBundleProductsLocalService;

}