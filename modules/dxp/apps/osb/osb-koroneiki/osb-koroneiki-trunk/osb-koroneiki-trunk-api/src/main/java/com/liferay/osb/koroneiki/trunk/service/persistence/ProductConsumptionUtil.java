/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.persistence;

import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the product consumption service. This utility wraps <code>com.liferay.osb.koroneiki.trunk.service.persistence.impl.ProductConsumptionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductConsumptionPersistence
 * @generated
 */
public class ProductConsumptionUtil {

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
	public static void clearCache(ProductConsumption productConsumption) {
		getPersistence().clearCache(productConsumption);
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
	public static Map<Serializable, ProductConsumption> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ProductConsumption> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProductConsumption> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProductConsumption> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProductConsumption update(
		ProductConsumption productConsumption) {

		return getPersistence().update(productConsumption);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProductConsumption update(
		ProductConsumption productConsumption, ServiceContext serviceContext) {

		return getPersistence().update(productConsumption, serviceContext);
	}

	/**
	 * Returns all the product consumptions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product consumptions
	 */
	public static List<ProductConsumption> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the product consumptions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions
	 */
	public static List<ProductConsumption> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions
	 */
	public static List<ProductConsumption> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product consumptions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product consumptions
	 */
	public static List<ProductConsumption> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByUuid_First(
			String uuid,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByUuid_First(
		String uuid, OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByUuid_Last(
			String uuid,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByUuid_Last(
		String uuid, OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption[] findByUuid_PrevAndNext(
			long productConsumptionId, String uuid,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByUuid_PrevAndNext(
			productConsumptionId, uuid, orderByComparator);
	}

	/**
	 * Returns all the product consumptions that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByUuid(String uuid) {
		return getPersistence().filterFindByUuid(uuid);
	}

	/**
	 * Returns a range of all the product consumptions that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByUuid(
		String uuid, int start, int end) {

		return getPersistence().filterFindByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().filterFindByUuid(
			uuid, start, end, orderByComparator);
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where uuid = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption[] filterFindByUuid_PrevAndNext(
			long productConsumptionId, String uuid,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().filterFindByUuid_PrevAndNext(
			productConsumptionId, uuid, orderByComparator);
	}

	/**
	 * Removes all the product consumptions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of product consumptions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product consumptions
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the number of product consumptions that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	public static int filterCountByUuid(String uuid) {
		return getPersistence().filterCountByUuid(uuid);
	}

	/**
	 * Returns all the product consumptions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product consumptions
	 */
	public static List<ProductConsumption> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the product consumptions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions
	 */
	public static List<ProductConsumption> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions
	 */
	public static List<ProductConsumption> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product consumptions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product consumptions
	 */
	public static List<ProductConsumption> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first product consumption in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first product consumption in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last product consumption in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last product consumption in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption[] findByUuid_C_PrevAndNext(
			long productConsumptionId, String uuid, long companyId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByUuid_C_PrevAndNext(
			productConsumptionId, uuid, companyId, orderByComparator);
	}

	/**
	 * Returns all the product consumptions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByUuid_C(
		String uuid, long companyId) {

		return getPersistence().filterFindByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the product consumptions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().filterFindByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().filterFindByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption[] filterFindByUuid_C_PrevAndNext(
			long productConsumptionId, String uuid, long companyId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().filterFindByUuid_C_PrevAndNext(
			productConsumptionId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the product consumptions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of product consumptions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product consumptions
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of product consumptions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	public static int filterCountByUuid_C(String uuid, long companyId) {
		return getPersistence().filterCountByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the product consumption where productConsumptionKey = &#63; or throws a <code>NoSuchProductConsumptionException</code> if it could not be found.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @return the matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByProductConsumptionKey(
			String productConsumptionKey)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByProductConsumptionKey(
			productConsumptionKey);
	}

	/**
	 * Returns the product consumption where productConsumptionKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @return the matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByProductConsumptionKey(
		String productConsumptionKey) {

		return getPersistence().fetchByProductConsumptionKey(
			productConsumptionKey);
	}

	/**
	 * Returns the product consumption where productConsumptionKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByProductConsumptionKey(
		String productConsumptionKey, boolean useFinderCache) {

		return getPersistence().fetchByProductConsumptionKey(
			productConsumptionKey, useFinderCache);
	}

	/**
	 * Removes the product consumption where productConsumptionKey = &#63; from the database.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @return the product consumption that was removed
	 */
	public static ProductConsumption removeByProductConsumptionKey(
			String productConsumptionKey)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().removeByProductConsumptionKey(
			productConsumptionKey);
	}

	/**
	 * Returns the number of product consumptions where productConsumptionKey = &#63;.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @return the number of matching product consumptions
	 */
	public static int countByProductConsumptionKey(
		String productConsumptionKey) {

		return getPersistence().countByProductConsumptionKey(
			productConsumptionKey);
	}

	/**
	 * Returns all the product consumptions where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching product consumptions
	 */
	public static List<ProductConsumption> findByAccountId(long accountId) {
		return getPersistence().findByAccountId(accountId);
	}

	/**
	 * Returns a range of all the product consumptions where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions
	 */
	public static List<ProductConsumption> findByAccountId(
		long accountId, int start, int end) {

		return getPersistence().findByAccountId(accountId, start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions
	 */
	public static List<ProductConsumption> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().findByAccountId(
			accountId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product consumptions where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product consumptions
	 */
	public static List<ProductConsumption> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAccountId(
			accountId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByAccountId_First(
			long accountId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByAccountId_First(
			accountId, orderByComparator);
	}

	/**
	 * Returns the first product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByAccountId_First(
		long accountId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().fetchByAccountId_First(
			accountId, orderByComparator);
	}

	/**
	 * Returns the last product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByAccountId_Last(
			long accountId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByAccountId_Last(
			accountId, orderByComparator);
	}

	/**
	 * Returns the last product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByAccountId_Last(
		long accountId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().fetchByAccountId_Last(
			accountId, orderByComparator);
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption[] findByAccountId_PrevAndNext(
			long productConsumptionId, long accountId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByAccountId_PrevAndNext(
			productConsumptionId, accountId, orderByComparator);
	}

	/**
	 * Returns all the product consumptions that the user has permission to view where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByAccountId(
		long accountId) {

		return getPersistence().filterFindByAccountId(accountId);
	}

	/**
	 * Returns a range of all the product consumptions that the user has permission to view where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByAccountId(
		long accountId, int start, int end) {

		return getPersistence().filterFindByAccountId(accountId, start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions that the user has permissions to view where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().filterFindByAccountId(
			accountId, start, end, orderByComparator);
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where accountId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption[] filterFindByAccountId_PrevAndNext(
			long productConsumptionId, long accountId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().filterFindByAccountId_PrevAndNext(
			productConsumptionId, accountId, orderByComparator);
	}

	/**
	 * Removes all the product consumptions where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	public static void removeByAccountId(long accountId) {
		getPersistence().removeByAccountId(accountId);
	}

	/**
	 * Returns the number of product consumptions where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching product consumptions
	 */
	public static int countByAccountId(long accountId) {
		return getPersistence().countByAccountId(accountId);
	}

	/**
	 * Returns the number of product consumptions that the user has permission to view where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	public static int filterCountByAccountId(long accountId) {
		return getPersistence().filterCountByAccountId(accountId);
	}

	/**
	 * Returns all the product consumptions where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions
	 */
	public static List<ProductConsumption> findByProductEntryId(
		long productEntryId) {

		return getPersistence().findByProductEntryId(productEntryId);
	}

	/**
	 * Returns a range of all the product consumptions where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions
	 */
	public static List<ProductConsumption> findByProductEntryId(
		long productEntryId, int start, int end) {

		return getPersistence().findByProductEntryId(
			productEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions
	 */
	public static List<ProductConsumption> findByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().findByProductEntryId(
			productEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product consumptions where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product consumptions
	 */
	public static List<ProductConsumption> findByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByProductEntryId(
			productEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByProductEntryId_First(
			long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByProductEntryId_First(
			productEntryId, orderByComparator);
	}

	/**
	 * Returns the first product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByProductEntryId_First(
		long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().fetchByProductEntryId_First(
			productEntryId, orderByComparator);
	}

	/**
	 * Returns the last product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByProductEntryId_Last(
			long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByProductEntryId_Last(
			productEntryId, orderByComparator);
	}

	/**
	 * Returns the last product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByProductEntryId_Last(
		long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().fetchByProductEntryId_Last(
			productEntryId, orderByComparator);
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption[] findByProductEntryId_PrevAndNext(
			long productConsumptionId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByProductEntryId_PrevAndNext(
			productConsumptionId, productEntryId, orderByComparator);
	}

	/**
	 * Returns all the product consumptions that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByProductEntryId(
		long productEntryId) {

		return getPersistence().filterFindByProductEntryId(productEntryId);
	}

	/**
	 * Returns a range of all the product consumptions that the user has permission to view where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByProductEntryId(
		long productEntryId, int start, int end) {

		return getPersistence().filterFindByProductEntryId(
			productEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions that the user has permissions to view where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().filterFindByProductEntryId(
			productEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption[] filterFindByProductEntryId_PrevAndNext(
			long productConsumptionId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().filterFindByProductEntryId_PrevAndNext(
			productConsumptionId, productEntryId, orderByComparator);
	}

	/**
	 * Removes all the product consumptions where productEntryId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 */
	public static void removeByProductEntryId(long productEntryId) {
		getPersistence().removeByProductEntryId(productEntryId);
	}

	/**
	 * Returns the number of product consumptions where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions
	 */
	public static int countByProductEntryId(long productEntryId) {
		return getPersistence().countByProductEntryId(productEntryId);
	}

	/**
	 * Returns the number of product consumptions that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	public static int filterCountByProductEntryId(long productEntryId) {
		return getPersistence().filterCountByProductEntryId(productEntryId);
	}

	/**
	 * Returns all the product consumptions where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @return the matching product consumptions
	 */
	public static List<ProductConsumption> findByProductPurchaseId(
		long productPurchaseId) {

		return getPersistence().findByProductPurchaseId(productPurchaseId);
	}

	/**
	 * Returns a range of all the product consumptions where productPurchaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions
	 */
	public static List<ProductConsumption> findByProductPurchaseId(
		long productPurchaseId, int start, int end) {

		return getPersistence().findByProductPurchaseId(
			productPurchaseId, start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions where productPurchaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions
	 */
	public static List<ProductConsumption> findByProductPurchaseId(
		long productPurchaseId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().findByProductPurchaseId(
			productPurchaseId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product consumptions where productPurchaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product consumptions
	 */
	public static List<ProductConsumption> findByProductPurchaseId(
		long productPurchaseId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByProductPurchaseId(
			productPurchaseId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByProductPurchaseId_First(
			long productPurchaseId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByProductPurchaseId_First(
			productPurchaseId, orderByComparator);
	}

	/**
	 * Returns the first product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByProductPurchaseId_First(
		long productPurchaseId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().fetchByProductPurchaseId_First(
			productPurchaseId, orderByComparator);
	}

	/**
	 * Returns the last product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByProductPurchaseId_Last(
			long productPurchaseId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByProductPurchaseId_Last(
			productPurchaseId, orderByComparator);
	}

	/**
	 * Returns the last product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByProductPurchaseId_Last(
		long productPurchaseId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().fetchByProductPurchaseId_Last(
			productPurchaseId, orderByComparator);
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption[] findByProductPurchaseId_PrevAndNext(
			long productConsumptionId, long productPurchaseId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByProductPurchaseId_PrevAndNext(
			productConsumptionId, productPurchaseId, orderByComparator);
	}

	/**
	 * Returns all the product consumptions that the user has permission to view where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByProductPurchaseId(
		long productPurchaseId) {

		return getPersistence().filterFindByProductPurchaseId(
			productPurchaseId);
	}

	/**
	 * Returns a range of all the product consumptions that the user has permission to view where productPurchaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByProductPurchaseId(
		long productPurchaseId, int start, int end) {

		return getPersistence().filterFindByProductPurchaseId(
			productPurchaseId, start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions that the user has permissions to view where productPurchaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByProductPurchaseId(
		long productPurchaseId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().filterFindByProductPurchaseId(
			productPurchaseId, start, end, orderByComparator);
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where productPurchaseId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption[]
			filterFindByProductPurchaseId_PrevAndNext(
				long productConsumptionId, long productPurchaseId,
				OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().filterFindByProductPurchaseId_PrevAndNext(
			productConsumptionId, productPurchaseId, orderByComparator);
	}

	/**
	 * Removes all the product consumptions where productPurchaseId = &#63; from the database.
	 *
	 * @param productPurchaseId the product purchase ID
	 */
	public static void removeByProductPurchaseId(long productPurchaseId) {
		getPersistence().removeByProductPurchaseId(productPurchaseId);
	}

	/**
	 * Returns the number of product consumptions where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @return the number of matching product consumptions
	 */
	public static int countByProductPurchaseId(long productPurchaseId) {
		return getPersistence().countByProductPurchaseId(productPurchaseId);
	}

	/**
	 * Returns the number of product consumptions that the user has permission to view where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	public static int filterCountByProductPurchaseId(long productPurchaseId) {
		return getPersistence().filterCountByProductPurchaseId(
			productPurchaseId);
	}

	/**
	 * Returns all the product consumptions where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions
	 */
	public static List<ProductConsumption> findByAI_PEI(
		long accountId, long productEntryId) {

		return getPersistence().findByAI_PEI(accountId, productEntryId);
	}

	/**
	 * Returns a range of all the product consumptions where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions
	 */
	public static List<ProductConsumption> findByAI_PEI(
		long accountId, long productEntryId, int start, int end) {

		return getPersistence().findByAI_PEI(
			accountId, productEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions
	 */
	public static List<ProductConsumption> findByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().findByAI_PEI(
			accountId, productEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product consumptions where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product consumptions
	 */
	public static List<ProductConsumption> findByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAI_PEI(
			accountId, productEntryId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first product consumption in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByAI_PEI_First(
			long accountId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByAI_PEI_First(
			accountId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the first product consumption in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByAI_PEI_First(
		long accountId, long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().fetchByAI_PEI_First(
			accountId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the last product consumption in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByAI_PEI_Last(
			long accountId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByAI_PEI_Last(
			accountId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the last product consumption in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByAI_PEI_Last(
		long accountId, long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().fetchByAI_PEI_Last(
			accountId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption[] findByAI_PEI_PrevAndNext(
			long productConsumptionId, long accountId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByAI_PEI_PrevAndNext(
			productConsumptionId, accountId, productEntryId, orderByComparator);
	}

	/**
	 * Returns all the product consumptions that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByAI_PEI(
		long accountId, long productEntryId) {

		return getPersistence().filterFindByAI_PEI(accountId, productEntryId);
	}

	/**
	 * Returns a range of all the product consumptions that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByAI_PEI(
		long accountId, long productEntryId, int start, int end) {

		return getPersistence().filterFindByAI_PEI(
			accountId, productEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions that the user has permissions to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().filterFindByAI_PEI(
			accountId, productEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption[] filterFindByAI_PEI_PrevAndNext(
			long productConsumptionId, long accountId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().filterFindByAI_PEI_PrevAndNext(
			productConsumptionId, accountId, productEntryId, orderByComparator);
	}

	/**
	 * Removes all the product consumptions where accountId = &#63; and productEntryId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 */
	public static void removeByAI_PEI(long accountId, long productEntryId) {
		getPersistence().removeByAI_PEI(accountId, productEntryId);
	}

	/**
	 * Returns the number of product consumptions where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions
	 */
	public static int countByAI_PEI(long accountId, long productEntryId) {
		return getPersistence().countByAI_PEI(accountId, productEntryId);
	}

	/**
	 * Returns the number of product consumptions that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	public static int filterCountByAI_PEI(long accountId, long productEntryId) {
		return getPersistence().filterCountByAI_PEI(accountId, productEntryId);
	}

	/**
	 * Returns all the product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions
	 */
	public static List<ProductConsumption> findByU_AI_PEI(
		long userId, long accountId, long productEntryId) {

		return getPersistence().findByU_AI_PEI(
			userId, accountId, productEntryId);
	}

	/**
	 * Returns a range of all the product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions
	 */
	public static List<ProductConsumption> findByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end) {

		return getPersistence().findByU_AI_PEI(
			userId, accountId, productEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions
	 */
	public static List<ProductConsumption> findByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().findByU_AI_PEI(
			userId, accountId, productEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product consumptions
	 */
	public static List<ProductConsumption> findByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByU_AI_PEI(
			userId, accountId, productEntryId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first product consumption in the ordered set where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByU_AI_PEI_First(
			long userId, long accountId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByU_AI_PEI_First(
			userId, accountId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the first product consumption in the ordered set where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByU_AI_PEI_First(
		long userId, long accountId, long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().fetchByU_AI_PEI_First(
			userId, accountId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the last product consumption in the ordered set where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public static ProductConsumption findByU_AI_PEI_Last(
			long userId, long accountId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByU_AI_PEI_Last(
			userId, accountId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the last product consumption in the ordered set where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public static ProductConsumption fetchByU_AI_PEI_Last(
		long userId, long accountId, long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().fetchByU_AI_PEI_Last(
			userId, accountId, productEntryId, orderByComparator);
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption[] findByU_AI_PEI_PrevAndNext(
			long productConsumptionId, long userId, long accountId,
			long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByU_AI_PEI_PrevAndNext(
			productConsumptionId, userId, accountId, productEntryId,
			orderByComparator);
	}

	/**
	 * Returns all the product consumptions that the user has permission to view where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByU_AI_PEI(
		long userId, long accountId, long productEntryId) {

		return getPersistence().filterFindByU_AI_PEI(
			userId, accountId, productEntryId);
	}

	/**
	 * Returns a range of all the product consumptions that the user has permission to view where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end) {

		return getPersistence().filterFindByU_AI_PEI(
			userId, accountId, productEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions that the user has permissions to view where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions that the user has permission to view
	 */
	public static List<ProductConsumption> filterFindByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().filterFindByU_AI_PEI(
			userId, accountId, productEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption[] filterFindByU_AI_PEI_PrevAndNext(
			long productConsumptionId, long userId, long accountId,
			long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().filterFindByU_AI_PEI_PrevAndNext(
			productConsumptionId, userId, accountId, productEntryId,
			orderByComparator);
	}

	/**
	 * Removes all the product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 */
	public static void removeByU_AI_PEI(
		long userId, long accountId, long productEntryId) {

		getPersistence().removeByU_AI_PEI(userId, accountId, productEntryId);
	}

	/**
	 * Returns the number of product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions
	 */
	public static int countByU_AI_PEI(
		long userId, long accountId, long productEntryId) {

		return getPersistence().countByU_AI_PEI(
			userId, accountId, productEntryId);
	}

	/**
	 * Returns the number of product consumptions that the user has permission to view where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	public static int filterCountByU_AI_PEI(
		long userId, long accountId, long productEntryId) {

		return getPersistence().filterCountByU_AI_PEI(
			userId, accountId, productEntryId);
	}

	/**
	 * Caches the product consumption in the entity cache if it is enabled.
	 *
	 * @param productConsumption the product consumption
	 */
	public static void cacheResult(ProductConsumption productConsumption) {
		getPersistence().cacheResult(productConsumption);
	}

	/**
	 * Caches the product consumptions in the entity cache if it is enabled.
	 *
	 * @param productConsumptions the product consumptions
	 */
	public static void cacheResult(
		List<ProductConsumption> productConsumptions) {

		getPersistence().cacheResult(productConsumptions);
	}

	/**
	 * Creates a new product consumption with the primary key. Does not add the product consumption to the database.
	 *
	 * @param productConsumptionId the primary key for the new product consumption
	 * @return the new product consumption
	 */
	public static ProductConsumption create(long productConsumptionId) {
		return getPersistence().create(productConsumptionId);
	}

	/**
	 * Removes the product consumption with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productConsumptionId the primary key of the product consumption
	 * @return the product consumption that was removed
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption remove(long productConsumptionId)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().remove(productConsumptionId);
	}

	public static ProductConsumption updateImpl(
		ProductConsumption productConsumption) {

		return getPersistence().updateImpl(productConsumption);
	}

	/**
	 * Returns the product consumption with the primary key or throws a <code>NoSuchProductConsumptionException</code> if it could not be found.
	 *
	 * @param productConsumptionId the primary key of the product consumption
	 * @return the product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption findByPrimaryKey(long productConsumptionId)
		throws com.liferay.osb.koroneiki.trunk.exception.
			NoSuchProductConsumptionException {

		return getPersistence().findByPrimaryKey(productConsumptionId);
	}

	/**
	 * Returns the product consumption with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productConsumptionId the primary key of the product consumption
	 * @return the product consumption, or <code>null</code> if a product consumption with the primary key could not be found
	 */
	public static ProductConsumption fetchByPrimaryKey(
		long productConsumptionId) {

		return getPersistence().fetchByPrimaryKey(productConsumptionId);
	}

	/**
	 * Returns all the product consumptions.
	 *
	 * @return the product consumptions
	 */
	public static List<ProductConsumption> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the product consumptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of product consumptions
	 */
	public static List<ProductConsumption> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the product consumptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product consumptions
	 */
	public static List<ProductConsumption> findAll(
		int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product consumptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of product consumptions
	 */
	public static List<ProductConsumption> findAll(
		int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the product consumptions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of product consumptions.
	 *
	 * @return the number of product consumptions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ProductConsumptionPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		ProductConsumptionPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile ProductConsumptionPersistence _persistence;

}