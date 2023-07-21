/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.service;

import com.liferay.osb.provisioning.model.ProductBundle;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for ProductBundle. This utility wraps
 * <code>com.liferay.osb.provisioning.service.impl.ProductBundleLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleLocalService
 * @generated
 */
public class ProductBundleLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.provisioning.service.impl.ProductBundleLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static ProductBundle addProductBundle(long userId, String name)
		throws PortalException {

		return getService().addProductBundle(userId, name);
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
	public static ProductBundle addProductBundle(ProductBundle productBundle) {
		return getService().addProductBundle(productBundle);
	}

	/**
	 * Creates a new product bundle with the primary key. Does not add the product bundle to the database.
	 *
	 * @param productBundleId the primary key for the new product bundle
	 * @return the new product bundle
	 */
	public static ProductBundle createProductBundle(long productBundleId) {
		return getService().createProductBundle(productBundleId);
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
	public static ProductBundle deleteProductBundle(long productBundleId)
		throws PortalException {

		return getService().deleteProductBundle(productBundleId);
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
	public static ProductBundle deleteProductBundle(
		ProductBundle productBundle) {

		return getService().deleteProductBundle(productBundle);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.model.impl.ProductBundleModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.provisioning.model.impl.ProductBundleModelImpl</code>.
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

	public static ProductBundle fetchProductBundle(long productBundleId) {
		return getService().fetchProductBundle(productBundleId);
	}

	/**
	 * Returns the product bundle with the matching UUID and company.
	 *
	 * @param uuid the product bundle's UUID
	 * @param companyId the primary key of the company
	 * @return the matching product bundle, or <code>null</code> if a matching product bundle could not be found
	 */
	public static ProductBundle fetchProductBundleByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchProductBundleByUuidAndCompanyId(
			uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
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
	 * Returns the product bundle with the primary key.
	 *
	 * @param productBundleId the primary key of the product bundle
	 * @return the product bundle
	 * @throws PortalException if a product bundle with the primary key could not be found
	 */
	public static ProductBundle getProductBundle(long productBundleId)
		throws PortalException {

		return getService().getProductBundle(productBundleId);
	}

	/**
	 * Returns the product bundle with the matching UUID and company.
	 *
	 * @param uuid the product bundle's UUID
	 * @param companyId the primary key of the company
	 * @return the matching product bundle
	 * @throws PortalException if a matching product bundle could not be found
	 */
	public static ProductBundle getProductBundleByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getProductBundleByUuidAndCompanyId(uuid, companyId);
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
	public static List<ProductBundle> getProductBundles(int start, int end) {
		return getService().getProductBundles(start, end);
	}

	/**
	 * Returns the number of product bundles.
	 *
	 * @return the number of product bundles
	 */
	public static int getProductBundlesCount() {
		return getService().getProductBundlesCount();
	}

	public static com.liferay.portal.kernel.search.Hits search(
			long companyId, String keywords, int start, int end,
			com.liferay.portal.kernel.search.Sort sort)
		throws PortalException {

		return getService().search(companyId, keywords, start, end, sort);
	}

	public static ProductBundle updateProductBundle(
			long productBundleId, String name)
		throws PortalException {

		return getService().updateProductBundle(productBundleId, name);
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
	public static ProductBundle updateProductBundle(
		ProductBundle productBundle) {

		return getService().updateProductBundle(productBundle);
	}

	public static ProductBundleLocalService getService() {
		return _service;
	}

	public static void setService(ProductBundleLocalService service) {
		_service = service;
	}

	private static volatile ProductBundleLocalService _service;

}