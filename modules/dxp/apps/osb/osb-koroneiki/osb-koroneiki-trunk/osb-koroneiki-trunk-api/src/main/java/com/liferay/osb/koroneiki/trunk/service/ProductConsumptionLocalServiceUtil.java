/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service;

import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ProductConsumption. This utility wraps
 * <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductConsumptionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ProductConsumptionLocalService
 * @generated
 */
public class ProductConsumptionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.trunk.service.impl.ProductConsumptionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ProductConsumption addProductConsumption(
			long userId, long accountId, long productEntryId,
			long productPurchaseId, java.util.Date startDate,
			java.util.Date endDate,
			List<com.liferay.osb.koroneiki.trunk.model.ProductField>
				productFields)
		throws PortalException {

		return getService().addProductConsumption(
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
	public static ProductConsumption addProductConsumption(
		ProductConsumption productConsumption) {

		return getService().addProductConsumption(productConsumption);
	}

	/**
	 * Creates a new product consumption with the primary key. Does not add the product consumption to the database.
	 *
	 * @param productConsumptionId the primary key for the new product consumption
	 * @return the new product consumption
	 */
	public static ProductConsumption createProductConsumption(
		long productConsumptionId) {

		return getService().createProductConsumption(productConsumptionId);
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
	public static ProductConsumption deleteProductConsumption(
			long productConsumptionId)
		throws PortalException {

		return getService().deleteProductConsumption(productConsumptionId);
	}

	public static ProductConsumption deleteProductConsumption(
			long userId, long accountId, long productEntryId)
		throws PortalException {

		return getService().deleteProductConsumption(
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
	public static ProductConsumption deleteProductConsumption(
			ProductConsumption productConsumption)
		throws PortalException {

		return getService().deleteProductConsumption(productConsumption);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductConsumptionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.trunk.model.impl.ProductConsumptionModelImpl</code>.
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

	public static ProductConsumption fetchProductConsumption(
		long productConsumptionId) {

		return getService().fetchProductConsumption(productConsumptionId);
	}

	/**
	 * Returns the product consumption with the matching UUID and company.
	 *
	 * @param uuid the product consumption's UUID
	 * @param companyId the primary key of the company
	 * @return the matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchProductConsumptionByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchProductConsumptionByUuidAndCompanyId(
			uuid, companyId);
	}

	public static List<ProductConsumption> getAccountProductConsumptions(
			long accountId, int start, int end)
		throws PortalException {

		return getService().getAccountProductConsumptions(
			accountId, start, end);
	}

	public static int getAccountProductConsumptionsCount(long accountId)
		throws PortalException {

		return getService().getAccountProductConsumptionsCount(accountId);
	}

	public static List<ProductConsumption>
		getAccountProductEntryProductConsumptions(
			long accountId, long productEntryId) {

		return getService().getAccountProductEntryProductConsumptions(
			accountId, productEntryId);
	}

	public static int getAccountProductEntryProductConsumptionsCount(
		long accountId, long productEntryId) {

		return getService().getAccountProductEntryProductConsumptionsCount(
			accountId, productEntryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<ProductConsumption> getContactProductConsumptions(
		long contactId, int start, int end) {

		return getService().getContactProductConsumptions(
			contactId, start, end);
	}

	public static int getContactProductConsumptionsCount(long contactId) {
		return getService().getContactProductConsumptionsCount(contactId);
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

	/**
	 * Returns the product consumption with the primary key.
	 *
	 * @param productConsumptionId the primary key of the product consumption
	 * @return the product consumption
	 * @throws PortalException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption getProductConsumption(
			long productConsumptionId)
		throws PortalException {

		return getService().getProductConsumption(productConsumptionId);
	}

	public static ProductConsumption getProductConsumption(
			String productConsumptionKey)
		throws PortalException {

		return getService().getProductConsumption(productConsumptionKey);
	}

	/**
	 * Returns the product consumption with the matching UUID and company.
	 *
	 * @param uuid the product consumption's UUID
	 * @param companyId the primary key of the company
	 * @return the matching product consumption
	 * @throws PortalException if a matching product consumption could not be found
	 */
	public static ProductConsumption getProductConsumptionByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getProductConsumptionByUuidAndCompanyId(
			uuid, companyId);
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
	public static List<ProductConsumption> getProductConsumptions(
		int start, int end) {

		return getService().getProductConsumptions(start, end);
	}

	public static List<ProductConsumption> getProductConsumptions(
			long userId, long accountId, long productEntryId)
		throws PortalException {

		return getService().getProductConsumptions(
			userId, accountId, productEntryId);
	}

	/**
	 * Returns the number of product consumptions.
	 *
	 * @return the number of product consumptions
	 */
	public static int getProductConsumptionsCount() {
		return getService().getProductConsumptionsCount();
	}

	public static int getProductEntryProductConsumptionsCount(
			long productEntryId)
		throws PortalException {

		return getService().getProductEntryProductConsumptionsCount(
			productEntryId);
	}

	public static int getProductPurchaseProductConsumptionsCount(
			long productPurchaseId)
		throws PortalException {

		return getService().getProductPurchaseProductConsumptionsCount(
			productPurchaseId);
	}

	public static ProductConsumption reindex(long productConsumptionId)
		throws PortalException {

		return getService().reindex(productConsumptionId);
	}

	public static com.liferay.portal.kernel.search.Hits search(
			long companyId, String keywords, int start, int end,
			com.liferay.portal.kernel.search.Sort sort)
		throws PortalException {

		return getService().search(companyId, keywords, start, end, sort);
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
	public static ProductConsumption updateProductConsumption(
		ProductConsumption productConsumption) {

		return getService().updateProductConsumption(productConsumption);
	}

	public static ProductConsumptionLocalService getService() {
		return _service;
	}

	public static void setService(ProductConsumptionLocalService service) {
		_service = service;
	}

	private static volatile ProductConsumptionLocalService _service;

}