/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.service.persistence;

import com.liferay.osb.provisioning.model.ProductBundle;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the product bundle service. This utility wraps <code>com.liferay.osb.provisioning.service.persistence.impl.ProductBundlePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundlePersistence
 * @generated
 */
public class ProductBundleUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ProductBundle productBundle) {
		getPersistence().clearCache(productBundle);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, ProductBundle> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ProductBundle> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProductBundle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProductBundle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProductBundle> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProductBundle update(ProductBundle productBundle) {
		return getPersistence().update(productBundle);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProductBundle update(
		ProductBundle productBundle, ServiceContext serviceContext) {

		return getPersistence().update(productBundle, serviceContext);
	}

	/**
	 * Returns all the product bundles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product bundles
	 */
	public static List<ProductBundle> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the product bundles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @return the range of matching product bundles
	 */
	public static List<ProductBundle> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the product bundles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product bundles
	 */
	public static List<ProductBundle> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ProductBundle> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product bundles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product bundles
	 */
	public static List<ProductBundle> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ProductBundle> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first product bundle in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle
	 * @throws NoSuchProductBundleException if a matching product bundle could not be found
	 */
	public static ProductBundle findByUuid_First(
			String uuid, OrderByComparator<ProductBundle> orderByComparator)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first product bundle in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle, or <code>null</code> if a matching product bundle could not be found
	 */
	public static ProductBundle fetchByUuid_First(
		String uuid, OrderByComparator<ProductBundle> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last product bundle in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle
	 * @throws NoSuchProductBundleException if a matching product bundle could not be found
	 */
	public static ProductBundle findByUuid_Last(
			String uuid, OrderByComparator<ProductBundle> orderByComparator)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last product bundle in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle, or <code>null</code> if a matching product bundle could not be found
	 */
	public static ProductBundle fetchByUuid_Last(
		String uuid, OrderByComparator<ProductBundle> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the product bundles before and after the current product bundle in the ordered set where uuid = &#63;.
	 *
	 * @param productBundleId the primary key of the current product bundle
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product bundle
	 * @throws NoSuchProductBundleException if a product bundle with the primary key could not be found
	 */
	public static ProductBundle[] findByUuid_PrevAndNext(
			long productBundleId, String uuid,
			OrderByComparator<ProductBundle> orderByComparator)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleException {

		return getPersistence().findByUuid_PrevAndNext(
			productBundleId, uuid, orderByComparator);
	}

	/**
	 * Removes all the product bundles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of product bundles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product bundles
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the product bundles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product bundles
	 */
	public static List<ProductBundle> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the product bundles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @return the range of matching product bundles
	 */
	public static List<ProductBundle> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the product bundles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product bundles
	 */
	public static List<ProductBundle> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProductBundle> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product bundles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product bundles
	 */
	public static List<ProductBundle> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProductBundle> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first product bundle in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle
	 * @throws NoSuchProductBundleException if a matching product bundle could not be found
	 */
	public static ProductBundle findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<ProductBundle> orderByComparator)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first product bundle in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle, or <code>null</code> if a matching product bundle could not be found
	 */
	public static ProductBundle fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<ProductBundle> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last product bundle in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle
	 * @throws NoSuchProductBundleException if a matching product bundle could not be found
	 */
	public static ProductBundle findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<ProductBundle> orderByComparator)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last product bundle in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle, or <code>null</code> if a matching product bundle could not be found
	 */
	public static ProductBundle fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<ProductBundle> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the product bundles before and after the current product bundle in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param productBundleId the primary key of the current product bundle
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product bundle
	 * @throws NoSuchProductBundleException if a product bundle with the primary key could not be found
	 */
	public static ProductBundle[] findByUuid_C_PrevAndNext(
			long productBundleId, String uuid, long companyId,
			OrderByComparator<ProductBundle> orderByComparator)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleException {

		return getPersistence().findByUuid_C_PrevAndNext(
			productBundleId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the product bundles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of product bundles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product bundles
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the product bundle where name = &#63; or throws a <code>NoSuchProductBundleException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching product bundle
	 * @throws NoSuchProductBundleException if a matching product bundle could not be found
	 */
	public static ProductBundle findByName(String name)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleException {

		return getPersistence().findByName(name);
	}

	/**
	 * Returns the product bundle where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching product bundle, or <code>null</code> if a matching product bundle could not be found
	 */
	public static ProductBundle fetchByName(String name) {
		return getPersistence().fetchByName(name);
	}

	/**
	 * Returns the product bundle where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product bundle, or <code>null</code> if a matching product bundle could not be found
	 */
	public static ProductBundle fetchByName(
		String name, boolean useFinderCache) {

		return getPersistence().fetchByName(name, useFinderCache);
	}

	/**
	 * Removes the product bundle where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the product bundle that was removed
	 */
	public static ProductBundle removeByName(String name)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleException {

		return getPersistence().removeByName(name);
	}

	/**
	 * Returns the number of product bundles where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching product bundles
	 */
	public static int countByName(String name) {
		return getPersistence().countByName(name);
	}

	/**
	 * Caches the product bundle in the entity cache if it is enabled.
	 *
	 * @param productBundle the product bundle
	 */
	public static void cacheResult(ProductBundle productBundle) {
		getPersistence().cacheResult(productBundle);
	}

	/**
	 * Caches the product bundles in the entity cache if it is enabled.
	 *
	 * @param productBundles the product bundles
	 */
	public static void cacheResult(List<ProductBundle> productBundles) {
		getPersistence().cacheResult(productBundles);
	}

	/**
	 * Creates a new product bundle with the primary key. Does not add the product bundle to the database.
	 *
	 * @param productBundleId the primary key for the new product bundle
	 * @return the new product bundle
	 */
	public static ProductBundle create(long productBundleId) {
		return getPersistence().create(productBundleId);
	}

	/**
	 * Removes the product bundle with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productBundleId the primary key of the product bundle
	 * @return the product bundle that was removed
	 * @throws NoSuchProductBundleException if a product bundle with the primary key could not be found
	 */
	public static ProductBundle remove(long productBundleId)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleException {

		return getPersistence().remove(productBundleId);
	}

	public static ProductBundle updateImpl(ProductBundle productBundle) {
		return getPersistence().updateImpl(productBundle);
	}

	/**
	 * Returns the product bundle with the primary key or throws a <code>NoSuchProductBundleException</code> if it could not be found.
	 *
	 * @param productBundleId the primary key of the product bundle
	 * @return the product bundle
	 * @throws NoSuchProductBundleException if a product bundle with the primary key could not be found
	 */
	public static ProductBundle findByPrimaryKey(long productBundleId)
		throws com.liferay.osb.provisioning.exception.
			NoSuchProductBundleException {

		return getPersistence().findByPrimaryKey(productBundleId);
	}

	/**
	 * Returns the product bundle with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productBundleId the primary key of the product bundle
	 * @return the product bundle, or <code>null</code> if a product bundle with the primary key could not be found
	 */
	public static ProductBundle fetchByPrimaryKey(long productBundleId) {
		return getPersistence().fetchByPrimaryKey(productBundleId);
	}

	/**
	 * Returns all the product bundles.
	 *
	 * @return the product bundles
	 */
	public static List<ProductBundle> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the product bundles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @return the range of product bundles
	 */
	public static List<ProductBundle> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the product bundles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product bundles
	 */
	public static List<ProductBundle> findAll(
		int start, int end,
		OrderByComparator<ProductBundle> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product bundles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of product bundles
	 */
	public static List<ProductBundle> findAll(
		int start, int end, OrderByComparator<ProductBundle> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the product bundles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of product bundles.
	 *
	 * @return the number of product bundles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ProductBundlePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(ProductBundlePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile ProductBundlePersistence _persistence;

}