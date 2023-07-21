/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProductBundleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleLocalService
 * @generated
 */
public class ProductBundleLocalServiceWrapper
	implements ProductBundleLocalService,
			   ServiceWrapper<ProductBundleLocalService> {

	public ProductBundleLocalServiceWrapper(
		ProductBundleLocalService productBundleLocalService) {

		_productBundleLocalService = productBundleLocalService;
	}

	@Override
	public com.liferay.osb.provisioning.model.ProductBundle addProductBundle(
			long userId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productBundleLocalService.addProductBundle(userId, name);
	}

	/**
	 * Adds the product bundle to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductBundleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productBundle the product bundle
	 * @return the product bundle that was added
	 */
	@Override
	public com.liferay.osb.provisioning.model.ProductBundle addProductBundle(
		com.liferay.osb.provisioning.model.ProductBundle productBundle) {

		return _productBundleLocalService.addProductBundle(productBundle);
	}

	/**
	 * Creates a new product bundle with the primary key. Does not add the product bundle to the database.
	 *
	 * @param productBundleId the primary key for the new product bundle
	 * @return the new product bundle
	 */
	@Override
	public com.liferay.osb.provisioning.model.ProductBundle createProductBundle(
		long productBundleId) {

		return _productBundleLocalService.createProductBundle(productBundleId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productBundleLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the product bundle with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductBundleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productBundleId the primary key of the product bundle
	 * @return the product bundle that was removed
	 * @throws PortalException if a product bundle with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.model.ProductBundle deleteProductBundle(
			long productBundleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productBundleLocalService.deleteProductBundle(productBundleId);
	}

	/**
	 * Deletes the product bundle from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductBundleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productBundle the product bundle
	 * @return the product bundle that was removed
	 */
	@Override
	public com.liferay.osb.provisioning.model.ProductBundle deleteProductBundle(
		com.liferay.osb.provisioning.model.ProductBundle productBundle) {

		return _productBundleLocalService.deleteProductBundle(productBundle);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _productBundleLocalService.dynamicQuery();
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

		return _productBundleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.model.impl.ProductBundleModelImpl</code>.
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

		return _productBundleLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.model.impl.ProductBundleModelImpl</code>.
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

		return _productBundleLocalService.dynamicQuery(
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

		return _productBundleLocalService.dynamicQueryCount(dynamicQuery);
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

		return _productBundleLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.provisioning.model.ProductBundle fetchProductBundle(
		long productBundleId) {

		return _productBundleLocalService.fetchProductBundle(productBundleId);
	}

	/**
	 * Returns the product bundle with the matching UUID and company.
	 *
	 * @param uuid the product bundle's UUID
	 * @param companyId the primary key of the company
	 * @return the matching product bundle, or <code>null</code> if a matching product bundle could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.model.ProductBundle
		fetchProductBundleByUuidAndCompanyId(String uuid, long companyId) {

		return _productBundleLocalService.fetchProductBundleByUuidAndCompanyId(
			uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _productBundleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _productBundleLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _productBundleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _productBundleLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productBundleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the product bundle with the primary key.
	 *
	 * @param productBundleId the primary key of the product bundle
	 * @return the product bundle
	 * @throws PortalException if a product bundle with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.model.ProductBundle getProductBundle(
			long productBundleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productBundleLocalService.getProductBundle(productBundleId);
	}

	/**
	 * Returns the product bundle with the matching UUID and company.
	 *
	 * @param uuid the product bundle's UUID
	 * @param companyId the primary key of the company
	 * @return the matching product bundle
	 * @throws PortalException if a matching product bundle could not be found
	 */
	@Override
	public com.liferay.osb.provisioning.model.ProductBundle
			getProductBundleByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productBundleLocalService.getProductBundleByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of all the product bundles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.model.impl.ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @return the range of product bundles
	 */
	@Override
	public java.util.List<com.liferay.osb.provisioning.model.ProductBundle>
		getProductBundles(int start, int end) {

		return _productBundleLocalService.getProductBundles(start, end);
	}

	/**
	 * Returns the number of product bundles.
	 *
	 * @return the number of product bundles
	 */
	@Override
	public int getProductBundlesCount() {
		return _productBundleLocalService.getProductBundlesCount();
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(
			long companyId, String keywords, int start, int end,
			com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productBundleLocalService.search(
			companyId, keywords, start, end, sort);
	}

	@Override
	public com.liferay.osb.provisioning.model.ProductBundle updateProductBundle(
			long productBundleId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productBundleLocalService.updateProductBundle(
			productBundleId, name);
	}

	/**
	 * Updates the product bundle in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductBundleLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productBundle the product bundle
	 * @return the product bundle that was updated
	 */
	@Override
	public com.liferay.osb.provisioning.model.ProductBundle updateProductBundle(
		com.liferay.osb.provisioning.model.ProductBundle productBundle) {

		return _productBundleLocalService.updateProductBundle(productBundle);
	}

	@Override
	public ProductBundleLocalService getWrappedService() {
		return _productBundleLocalService;
	}

	@Override
	public void setWrappedService(
		ProductBundleLocalService productBundleLocalService) {

		_productBundleLocalService = productBundleLocalService;
	}

	private ProductBundleLocalService _productBundleLocalService;

}