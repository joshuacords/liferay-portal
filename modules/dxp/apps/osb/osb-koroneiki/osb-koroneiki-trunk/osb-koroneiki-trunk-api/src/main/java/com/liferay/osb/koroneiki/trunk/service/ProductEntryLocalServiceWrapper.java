/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProductEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryLocalService
 * @generated
 */
public class ProductEntryLocalServiceWrapper
	implements ProductEntryLocalService,
			   ServiceWrapper<ProductEntryLocalService> {

	public ProductEntryLocalServiceWrapper(
		ProductEntryLocalService productEntryLocalService) {

		_productEntryLocalService = productEntryLocalService;
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry addProductEntry(
			long userId, String name,
			java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductField>
				productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.addProductEntry(
			userId, name, productFields);
	}

	/**
	 * Adds the product entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productEntry the product entry
	 * @return the product entry that was added
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry addProductEntry(
		com.liferay.osb.koroneiki.trunk.model.ProductEntry productEntry) {

		return _productEntryLocalService.addProductEntry(productEntry);
	}

	/**
	 * Creates a new product entry with the primary key. Does not add the product entry to the database.
	 *
	 * @param productEntryId the primary key for the new product entry
	 * @return the new product entry
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
		createProductEntry(long productEntryId) {

		return _productEntryLocalService.createProductEntry(productEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the product entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry that was removed
	 * @throws PortalException if a product entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
			deleteProductEntry(long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.deleteProductEntry(productEntryId);
	}

	/**
	 * Deletes the product entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productEntry the product entry
	 * @return the product entry that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
			deleteProductEntry(
				com.liferay.osb.koroneiki.trunk.model.ProductEntry productEntry)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.deleteProductEntry(productEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _productEntryLocalService.dynamicQuery();
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

		return _productEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductEntryModelImpl</code>.
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

		return _productEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductEntryModelImpl</code>.
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

		return _productEntryLocalService.dynamicQuery(
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

		return _productEntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _productEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry fetchProductEntry(
		long productEntryId) {

		return _productEntryLocalService.fetchProductEntry(productEntryId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
		fetchProductEntryByName(String name) {

		return _productEntryLocalService.fetchProductEntryByName(name);
	}

	/**
	 * Returns the product entry with the matching UUID and company.
	 *
	 * @param uuid the product entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
		fetchProductEntryByUuidAndCompanyId(String uuid, long companyId) {

		return _productEntryLocalService.fetchProductEntryByUuidAndCompanyId(
			uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _productEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _productEntryLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _productEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _productEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the product entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @return the range of product entries
	 */
	@Override
	public java.util.List<com.liferay.osb.koroneiki.trunk.model.ProductEntry>
		getProductEntries(int start, int end) {

		return _productEntryLocalService.getProductEntries(start, end);
	}

	/**
	 * Returns the number of product entries.
	 *
	 * @return the number of product entries
	 */
	@Override
	public int getProductEntriesCount() {
		return _productEntryLocalService.getProductEntriesCount();
	}

	/**
	 * Returns the product entry with the primary key.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry
	 * @throws PortalException if a product entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry getProductEntry(
			long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.getProductEntry(productEntryId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry getProductEntry(
			String productEntryKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.getProductEntry(productEntryKey);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
			getProductEntryByName(String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.getProductEntryByName(name);
	}

	/**
	 * Returns the product entry with the matching UUID and company.
	 *
	 * @param uuid the product entry's UUID
	 * @param companyId the primary key of the company
	 * @return the matching product entry
	 * @throws PortalException if a matching product entry could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
			getProductEntryByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.getProductEntryByUuidAndCompanyId(
			uuid, companyId);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry reindex(
			long productEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.reindex(productEntryId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(
			long companyId, String keywords, int start, int end,
			com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.search(
			companyId, keywords, start, end, sort);
	}

	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
			updateProductEntry(
				long userId, long productEntryId, String name,
				java.util.List
					<com.liferay.osb.koroneiki.trunk.model.ProductField>
						productFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _productEntryLocalService.updateProductEntry(
			userId, productEntryId, name, productFields);
	}

	/**
	 * Updates the product entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productEntry the product entry
	 * @return the product entry that was updated
	 */
	@Override
	public com.liferay.osb.koroneiki.trunk.model.ProductEntry
		updateProductEntry(
			com.liferay.osb.koroneiki.trunk.model.ProductEntry productEntry) {

		return _productEntryLocalService.updateProductEntry(productEntry);
	}

	@Override
	public ProductEntryLocalService getWrappedService() {
		return _productEntryLocalService;
	}

	@Override
	public void setWrappedService(
		ProductEntryLocalService productEntryLocalService) {

		_productEntryLocalService = productEntryLocalService;
	}

	private ProductEntryLocalService _productEntryLocalService;

}