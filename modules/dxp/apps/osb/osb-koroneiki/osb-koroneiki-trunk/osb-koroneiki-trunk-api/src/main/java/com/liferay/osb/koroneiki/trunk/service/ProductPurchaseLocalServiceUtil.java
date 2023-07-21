/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service;

import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ProductPurchase. This utility wraps
 * <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductPurchaseLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ProductPurchaseLocalService
 * @generated
 */
public class ProductPurchaseLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductPurchaseLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ProductPurchase addProductPurchase(
			long userId, long accountId, long productEntryId,
			java.util.Date startDate, java.util.Date endDate,
			java.util.Date originalEndDate, int quantity, int status,
			List<com.liferay.osb.koroneiki.trunk.model.ProductField>
				productFields)
		throws PortalException {

		return getService().addProductPurchase(
			userId, accountId, productEntryId, startDate, endDate,
			originalEndDate, quantity, status, productFields);
	}

	/**
	 * Adds the product purchase to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductPurchaseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productPurchase the product purchase
	 * @return the product purchase that was added
	 */
	public static ProductPurchase addProductPurchase(
		ProductPurchase productPurchase) {

		return getService().addProductPurchase(productPurchase);
	}

	/**
	 * Creates a new product purchase with the primary key. Does not add the product purchase to the database.
	 *
	 * @param productPurchaseId the primary key for the new product purchase
	 * @return the new product purchase
	 */
	public static ProductPurchase createProductPurchase(
		long productPurchaseId) {

		return getService().createProductPurchase(productPurchaseId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the product purchase with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductPurchaseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productPurchaseId the primary key of the product purchase
	 * @return the product purchase that was removed
	 * @throws PortalException if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase deleteProductPurchase(long productPurchaseId)
		throws PortalException {

		return getService().deleteProductPurchase(productPurchaseId);
	}

	/**
	 * Deletes the product purchase from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductPurchaseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productPurchase the product purchase
	 * @return the product purchase that was removed
	 */
	public static ProductPurchase deleteProductPurchase(
		ProductPurchase productPurchase) {

		return getService().deleteProductPurchase(productPurchase);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static ProductPurchase fetchProductPurchase(long productPurchaseId) {
		return getService().fetchProductPurchase(productPurchaseId);
	}

	/**
	 * Returns the product purchase with the matching UUID and company.
	 *
	 * @param uuid the product purchase's UUID
	 * @param companyId the primary key of the company
	 * @return the matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public static ProductPurchase fetchProductPurchaseByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchProductPurchaseByUuidAndCompanyId(
			uuid, companyId);
	}

	public static List<ProductPurchase> getAccountProductEntryProductPurchases(
		long accountId, long productEntryId, int start, int end) {

		return getService().getAccountProductEntryProductPurchases(
			accountId, productEntryId, start, end);
	}

	public static int getAccountProductEntryProductPurchasesCount(
		long accountId, long productEntryId) {

		return getService().getAccountProductEntryProductPurchasesCount(
			accountId, productEntryId);
	}

	public static List<ProductPurchase> getAccountProductPurchases(
		long accountId, int start, int end) {

		return getService().getAccountProductPurchases(accountId, start, end);
	}

	public static int getAccountProductPurchasesCount(long accountId) {
		return getService().getAccountProductPurchasesCount(accountId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<ProductPurchase> getContactProductPurchases(
		long contactId, int start, int end) {

		return getService().getContactProductPurchases(contactId, start, end);
	}

	public static int getContactProductPurchasesCount(long contactId) {
		return getService().getContactProductPurchasesCount(contactId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static List<ProductPurchase> getProductEntryProductPurchases(
			long productEntryId)
		throws PortalException {

		return getService().getProductEntryProductPurchases(productEntryId);
	}

	public static int getProductEntryProductPurchasesCount(long productEntryId)
		throws PortalException {

		return getService().getProductEntryProductPurchasesCount(
			productEntryId);
	}

	/**
	 * Returns the product purchase with the primary key.
	 *
	 * @param productPurchaseId the primary key of the product purchase
	 * @return the product purchase
	 * @throws PortalException if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase getProductPurchase(long productPurchaseId)
		throws PortalException {

		return getService().getProductPurchase(productPurchaseId);
	}

	public static ProductPurchase getProductPurchase(String productPurchaseKey)
		throws PortalException {

		return getService().getProductPurchase(productPurchaseKey);
	}

	/**
	 * Returns the product purchase with the matching UUID and company.
	 *
	 * @param uuid the product purchase's UUID
	 * @param companyId the primary key of the company
	 * @return the matching product purchase
	 * @throws PortalException if a matching product purchase could not be found
	 */
	public static ProductPurchase getProductPurchaseByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getProductPurchaseByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of all the product purchases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of product purchases
	 */
	public static List<ProductPurchase> getProductPurchases(
		int start, int end) {

		return getService().getProductPurchases(start, end);
	}

	/**
	 * Returns the number of product purchases.
	 *
	 * @return the number of product purchases
	 */
	public static int getProductPurchasesCount() {
		return getService().getProductPurchasesCount();
	}

	public static ProductPurchase reindex(long productPurchaseId)
		throws PortalException {

		return getService().reindex(productPurchaseId);
	}

	public static com.liferay.portal.kernel.search.Hits search(
			long companyId, String keywords, int start, int end,
			com.liferay.portal.kernel.search.Sort sort)
		throws PortalException {

		return getService().search(companyId, keywords, start, end, sort);
	}

	public static ProductPurchase updateProductPurchase(
			long userId, long productPurchaseId, java.util.Date startDate,
			java.util.Date endDate, java.util.Date originalEndDate,
			int quantity, int status,
			List<com.liferay.osb.koroneiki.trunk.model.ProductField>
				productFields)
		throws PortalException {

		return getService().updateProductPurchase(
			userId, productPurchaseId, startDate, endDate, originalEndDate,
			quantity, status, productFields);
	}

	/**
	 * Updates the product purchase in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProductPurchaseLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param productPurchase the product purchase
	 * @return the product purchase that was updated
	 */
	public static ProductPurchase updateProductPurchase(
		ProductPurchase productPurchase) {

		return getService().updateProductPurchase(productPurchase);
	}

	public static ProductPurchaseLocalService getService() {
		return _service;
	}

	public static void setService(ProductPurchaseLocalService service) {
		_service = service;
	}

	private static volatile ProductPurchaseLocalService _service;

}