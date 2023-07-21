/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.persistence;

import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the product purchase service. This utility wraps <code>com.liferay.osb.koroneiki.trunk.service.persistence.impl.ProductPurchasePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductPurchasePersistence
 * @generated
 */
public class ProductPurchaseUtil {

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
	public static void clearCache(ProductPurchase productPurchase) {
		getPersistence().clearCache(productPurchase);
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
	public static Map<Serializable, ProductPurchase> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ProductPurchase> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProductPurchase> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProductPurchase> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProductPurchase update(ProductPurchase productPurchase) {
		return getPersistence().update(productPurchase);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProductPurchase update(
		ProductPurchase productPurchase, ServiceContext serviceContext) {

		return getPersistence().update(productPurchase, serviceContext);
	}

	/**
	 * Returns all the product purchases where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product purchases
	 */
	public static List<ProductPurchase> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the product purchases where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases
	 */
	public static List<ProductPurchase> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the product purchases where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases
	 */
	public static List<ProductPurchase> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product purchases where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product purchases
	 */
	public static List<ProductPurchase> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public static ProductPurchase findByUuid_First(
			String uuid, OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public static ProductPurchase fetchByUuid_First(
		String uuid, OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public static ProductPurchase findByUuid_Last(
			String uuid, OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public static ProductPurchase fetchByUuid_Last(
		String uuid, OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase[] findByUuid_PrevAndNext(
			long productPurchaseId, String uuid,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByUuid_PrevAndNext(
			productPurchaseId, uuid, orderByComparator);
	}

	/**
	 * Returns all the product purchases that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByUuid(String uuid) {
		return getPersistence().filterFindByUuid(uuid);
	}

	/**
	 * Returns a range of all the product purchases that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByUuid(
		String uuid, int start, int end) {

		return getPersistence().filterFindByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the product purchases that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().filterFindByUuid(
			uuid, start, end, orderByComparator);
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set of product purchases that the user has permission to view where uuid = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase[] filterFindByUuid_PrevAndNext(
			long productPurchaseId, String uuid,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().filterFindByUuid_PrevAndNext(
			productPurchaseId, uuid, orderByComparator);
	}

	/**
	 * Removes all the product purchases where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of product purchases where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product purchases
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the number of product purchases that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product purchases that the user has permission to view
	 */
	public static int filterCountByUuid(String uuid) {
		return getPersistence().filterCountByUuid(uuid);
	}

	/**
	 * Returns all the product purchases where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product purchases
	 */
	public static List<ProductPurchase> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the product purchases where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases
	 */
	public static List<ProductPurchase> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the product purchases where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases
	 */
	public static List<ProductPurchase> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product purchases where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product purchases
	 */
	public static List<ProductPurchase> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first product purchase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public static ProductPurchase findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first product purchase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public static ProductPurchase fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last product purchase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public static ProductPurchase findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last product purchase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public static ProductPurchase fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase[] findByUuid_C_PrevAndNext(
			long productPurchaseId, String uuid, long companyId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByUuid_C_PrevAndNext(
			productPurchaseId, uuid, companyId, orderByComparator);
	}

	/**
	 * Returns all the product purchases that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByUuid_C(
		String uuid, long companyId) {

		return getPersistence().filterFindByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the product purchases that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().filterFindByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the product purchases that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().filterFindByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set of product purchases that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase[] filterFindByUuid_C_PrevAndNext(
			long productPurchaseId, String uuid, long companyId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().filterFindByUuid_C_PrevAndNext(
			productPurchaseId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the product purchases where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of product purchases where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product purchases
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of product purchases that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product purchases that the user has permission to view
	 */
	public static int filterCountByUuid_C(String uuid, long companyId) {
		return getPersistence().filterCountByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the product purchase where productPurchaseKey = &#63; or throws a <code>NoSuchProductPurchaseException</code> if it could not be found.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public static ProductPurchase findByProductPurchaseKey(
			String productPurchaseKey)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByProductPurchaseKey(productPurchaseKey);
	}

	/**
	 * Returns the product purchase where productPurchaseKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public static ProductPurchase fetchByProductPurchaseKey(
		String productPurchaseKey) {

		return getPersistence().fetchByProductPurchaseKey(productPurchaseKey);
	}

	/**
	 * Returns the product purchase where productPurchaseKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public static ProductPurchase fetchByProductPurchaseKey(
		String productPurchaseKey, boolean useFinderCache) {

		return getPersistence().fetchByProductPurchaseKey(
			productPurchaseKey, useFinderCache);
	}

	/**
	 * Removes the product purchase where productPurchaseKey = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the product purchase that was removed
	 */
	public static ProductPurchase removeByProductPurchaseKey(
			String productPurchaseKey)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().removeByProductPurchaseKey(productPurchaseKey);
	}

	/**
	 * Returns the number of product purchases where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the number of matching product purchases
	 */
	public static int countByProductPurchaseKey(String productPurchaseKey) {
		return getPersistence().countByProductPurchaseKey(productPurchaseKey);
	}

	/**
	 * Returns all the product purchases where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching product purchases
	 */
	public static List<ProductPurchase> findByAccountId(long accountId) {
		return getPersistence().findByAccountId(accountId);
	}

	/**
	 * Returns a range of all the product purchases where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases
	 */
	public static List<ProductPurchase> findByAccountId(
		long accountId, int start, int end) {

		return getPersistence().findByAccountId(accountId, start, end);
	}

	/**
	 * Returns an ordered range of all the product purchases where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases
	 */
	public static List<ProductPurchase> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().findByAccountId(
			accountId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product purchases where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product purchases
	 */
	public static List<ProductPurchase> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAccountId(
			accountId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public static ProductPurchase findByAccountId_First(
			long accountId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByAccountId_First(
			accountId, orderByComparator);
	}

	/**
	 * Returns the first product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public static ProductPurchase fetchByAccountId_First(
		long accountId, OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().fetchByAccountId_First(
			accountId, orderByComparator);
	}

	/**
	 * Returns the last product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public static ProductPurchase findByAccountId_Last(
			long accountId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByAccountId_Last(
			accountId, orderByComparator);
	}

	/**
	 * Returns the last product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public static ProductPurchase fetchByAccountId_Last(
		long accountId, OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().fetchByAccountId_Last(
			accountId, orderByComparator);
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase[] findByAccountId_PrevAndNext(
			long productPurchaseId, long accountId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByAccountId_PrevAndNext(
			productPurchaseId, accountId, orderByComparator);
	}

	/**
	 * Returns all the product purchases that the user has permission to view where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByAccountId(long accountId) {
		return getPersistence().filterFindByAccountId(accountId);
	}

	/**
	 * Returns a range of all the product purchases that the user has permission to view where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByAccountId(
		long accountId, int start, int end) {

		return getPersistence().filterFindByAccountId(accountId, start, end);
	}

	/**
	 * Returns an ordered range of all the product purchases that the user has permissions to view where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().filterFindByAccountId(
			accountId, start, end, orderByComparator);
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set of product purchases that the user has permission to view where accountId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase[] filterFindByAccountId_PrevAndNext(
			long productPurchaseId, long accountId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().filterFindByAccountId_PrevAndNext(
			productPurchaseId, accountId, orderByComparator);
	}

	/**
	 * Removes all the product purchases where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	public static void removeByAccountId(long accountId) {
		getPersistence().removeByAccountId(accountId);
	}

	/**
	 * Returns the number of product purchases where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching product purchases
	 */
	public static int countByAccountId(long accountId) {
		return getPersistence().countByAccountId(accountId);
	}

	/**
	 * Returns the number of product purchases that the user has permission to view where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching product purchases that the user has permission to view
	 */
	public static int filterCountByAccountId(long accountId) {
		return getPersistence().filterCountByAccountId(accountId);
	}

	/**
	 * Returns all the product purchases where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching product purchases
	 */
	public static List<ProductPurchase> findByProductEntryId(
		long productEntryId) {

		return getPersistence().findByProductEntryId(productEntryId);
	}

	/**
	 * Returns a range of all the product purchases where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases
	 */
	public static List<ProductPurchase> findByProductEntryId(
		long productEntryId, int start, int end) {

		return getPersistence().findByProductEntryId(
			productEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the product purchases where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases
	 */
	public static List<ProductPurchase> findByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().findByProductEntryId(
			productEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product purchases where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product purchases
	 */
	public static List<ProductPurchase> findByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByProductEntryId(
			productEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public static ProductPurchase findByProductEntryId_First(
			long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByProductEntryId_First(
			productEntryId, orderByComparator);
	}

	/**
	 * Returns the first product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public static ProductPurchase fetchByProductEntryId_First(
		long productEntryId,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().fetchByProductEntryId_First(
			productEntryId, orderByComparator);
	}

	/**
	 * Returns the last product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public static ProductPurchase findByProductEntryId_Last(
			long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByProductEntryId_Last(
			productEntryId, orderByComparator);
	}

	/**
	 * Returns the last product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public static ProductPurchase fetchByProductEntryId_Last(
		long productEntryId,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().fetchByProductEntryId_Last(
			productEntryId, orderByComparator);
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase[] findByProductEntryId_PrevAndNext(
			long productPurchaseId, long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByProductEntryId_PrevAndNext(
			productPurchaseId, productEntryId, orderByComparator);
	}

	/**
	 * Returns all the product purchases that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByProductEntryId(
		long productEntryId) {

		return getPersistence().filterFindByProductEntryId(productEntryId);
	}

	/**
	 * Returns a range of all the product purchases that the user has permission to view where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByProductEntryId(
		long productEntryId, int start, int end) {

		return getPersistence().filterFindByProductEntryId(
			productEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the product purchases that the user has permissions to view where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().filterFindByProductEntryId(
			productEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set of product purchases that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase[] filterFindByProductEntryId_PrevAndNext(
			long productPurchaseId, long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().filterFindByProductEntryId_PrevAndNext(
			productPurchaseId, productEntryId, orderByComparator);
	}

	/**
	 * Removes all the product purchases where productEntryId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 */
	public static void removeByProductEntryId(long productEntryId) {
		getPersistence().removeByProductEntryId(productEntryId);
	}

	/**
	 * Returns the number of product purchases where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching product purchases
	 */
	public static int countByProductEntryId(long productEntryId) {
		return getPersistence().countByProductEntryId(productEntryId);
	}

	/**
	 * Returns the number of product purchases that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching product purchases that the user has permission to view
	 */
	public static int filterCountByProductEntryId(long productEntryId) {
		return getPersistence().filterCountByProductEntryId(productEntryId);
	}

	/**
	 * Returns all the product purchases where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product purchases
	 */
	public static List<ProductPurchase> findByAI_PEI(
		long accountId, long productEntryId) {

		return getPersistence().findByAI_PEI(accountId, productEntryId);
	}

	/**
	 * Returns a range of all the product purchases where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases
	 */
	public static List<ProductPurchase> findByAI_PEI(
		long accountId, long productEntryId, int start, int end) {

		return getPersistence().findByAI_PEI(
			accountId, productEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the product purchases where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases
	 */
	public static List<ProductPurchase> findByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().findByAI_PEI(
			accountId, productEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product purchases where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product purchases
	 */
	public static List<ProductPurchase> findByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAI_PEI(
			accountId, productEntryId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first product purchase in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public static ProductPurchase findByAI_PEI_First(
			long accountId, long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByAI_PEI_First(
			accountId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the first product purchase in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public static ProductPurchase fetchByAI_PEI_First(
		long accountId, long productEntryId,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().fetchByAI_PEI_First(
			accountId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the last product purchase in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public static ProductPurchase findByAI_PEI_Last(
			long accountId, long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByAI_PEI_Last(
			accountId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the last product purchase in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public static ProductPurchase fetchByAI_PEI_Last(
		long accountId, long productEntryId,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().fetchByAI_PEI_Last(
			accountId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase[] findByAI_PEI_PrevAndNext(
			long productPurchaseId, long accountId, long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByAI_PEI_PrevAndNext(
			productPurchaseId, accountId, productEntryId, orderByComparator);
	}

	/**
	 * Returns all the product purchases that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByAI_PEI(
		long accountId, long productEntryId) {

		return getPersistence().filterFindByAI_PEI(accountId, productEntryId);
	}

	/**
	 * Returns a range of all the product purchases that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByAI_PEI(
		long accountId, long productEntryId, int start, int end) {

		return getPersistence().filterFindByAI_PEI(
			accountId, productEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the product purchases that the user has permissions to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases that the user has permission to view
	 */
	public static List<ProductPurchase> filterFindByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().filterFindByAI_PEI(
			accountId, productEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set of product purchases that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase[] filterFindByAI_PEI_PrevAndNext(
			long productPurchaseId, long accountId, long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().filterFindByAI_PEI_PrevAndNext(
			productPurchaseId, accountId, productEntryId, orderByComparator);
	}

	/**
	 * Removes all the product purchases where accountId = &#63; and productEntryId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 */
	public static void removeByAI_PEI(long accountId, long productEntryId) {
		getPersistence().removeByAI_PEI(accountId, productEntryId);
	}

	/**
	 * Returns the number of product purchases where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product purchases
	 */
	public static int countByAI_PEI(long accountId, long productEntryId) {
		return getPersistence().countByAI_PEI(accountId, productEntryId);
	}

	/**
	 * Returns the number of product purchases that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product purchases that the user has permission to view
	 */
	public static int filterCountByAI_PEI(long accountId, long productEntryId) {
		return getPersistence().filterCountByAI_PEI(accountId, productEntryId);
	}

	/**
	 * Caches the product purchase in the entity cache if it is enabled.
	 *
	 * @param productPurchase the product purchase
	 */
	public static void cacheResult(ProductPurchase productPurchase) {
		getPersistence().cacheResult(productPurchase);
	}

	/**
	 * Caches the product purchases in the entity cache if it is enabled.
	 *
	 * @param productPurchases the product purchases
	 */
	public static void cacheResult(List<ProductPurchase> productPurchases) {
		getPersistence().cacheResult(productPurchases);
	}

	/**
	 * Creates a new product purchase with the primary key. Does not add the product purchase to the database.
	 *
	 * @param productPurchaseId the primary key for the new product purchase
	 * @return the new product purchase
	 */
	public static ProductPurchase create(long productPurchaseId) {
		return getPersistence().create(productPurchaseId);
	}

	/**
	 * Removes the product purchase with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productPurchaseId the primary key of the product purchase
	 * @return the product purchase that was removed
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase remove(long productPurchaseId)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().remove(productPurchaseId);
	}

	public static ProductPurchase updateImpl(ProductPurchase productPurchase) {
		return getPersistence().updateImpl(productPurchase);
	}

	/**
	 * Returns the product purchase with the primary key or throws a <code>NoSuchProductPurchaseException</code> if it could not be found.
	 *
	 * @param productPurchaseId the primary key of the product purchase
	 * @return the product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase findByPrimaryKey(long productPurchaseId)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductPurchaseException {

		return getPersistence().findByPrimaryKey(productPurchaseId);
	}

	/**
	 * Returns the product purchase with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productPurchaseId the primary key of the product purchase
	 * @return the product purchase, or <code>null</code> if a product purchase with the primary key could not be found
	 */
	public static ProductPurchase fetchByPrimaryKey(long productPurchaseId) {
		return getPersistence().fetchByPrimaryKey(productPurchaseId);
	}

	/**
	 * Returns all the product purchases.
	 *
	 * @return the product purchases
	 */
	public static List<ProductPurchase> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the product purchases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of product purchases
	 */
	public static List<ProductPurchase> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the product purchases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product purchases
	 */
	public static List<ProductPurchase> findAll(
		int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product purchases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of product purchases
	 */
	public static List<ProductPurchase> findAll(
		int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the product purchases from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of product purchases.
	 *
	 * @return the number of product purchases
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ProductPurchasePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(ProductPurchasePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile ProductPurchasePersistence _persistence;

}