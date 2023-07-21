/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service.persistence;

import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the entitlement definition service. This utility wraps <code>com.liferay.osb.koroneiki.phytohormone.service.persistence.impl.EntitlementDefinitionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementDefinitionPersistence
 * @generated
 */
public class EntitlementDefinitionUtil {

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
	public static void clearCache(EntitlementDefinition entitlementDefinition) {
		getPersistence().clearCache(entitlementDefinition);
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
	public static Map<Serializable, EntitlementDefinition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<EntitlementDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EntitlementDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EntitlementDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EntitlementDefinition update(
		EntitlementDefinition entitlementDefinition) {

		return getPersistence().update(entitlementDefinition);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EntitlementDefinition update(
		EntitlementDefinition entitlementDefinition,
		ServiceContext serviceContext) {

		return getPersistence().update(entitlementDefinition, serviceContext);
	}

	/**
	 * Returns all the entitlement definitions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the entitlement definitions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @return the range of matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the entitlement definitions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entitlement definitions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition findByUuid_First(
			String uuid,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition fetchByUuid_First(
		String uuid,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition findByUuid_Last(
			String uuid,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition fetchByUuid_Last(
		String uuid,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the entitlement definitions before and after the current entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param entitlementDefinitionId the primary key of the current entitlement definition
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	public static EntitlementDefinition[] findByUuid_PrevAndNext(
			long entitlementDefinitionId, String uuid,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByUuid_PrevAndNext(
			entitlementDefinitionId, uuid, orderByComparator);
	}

	/**
	 * Returns all the entitlement definitions that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching entitlement definitions that the user has permission to view
	 */
	public static List<EntitlementDefinition> filterFindByUuid(String uuid) {
		return getPersistence().filterFindByUuid(uuid);
	}

	/**
	 * Returns a range of all the entitlement definitions that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @return the range of matching entitlement definitions that the user has permission to view
	 */
	public static List<EntitlementDefinition> filterFindByUuid(
		String uuid, int start, int end) {

		return getPersistence().filterFindByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the entitlement definitions that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entitlement definitions that the user has permission to view
	 */
	public static List<EntitlementDefinition> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().filterFindByUuid(
			uuid, start, end, orderByComparator);
	}

	/**
	 * Returns the entitlement definitions before and after the current entitlement definition in the ordered set of entitlement definitions that the user has permission to view where uuid = &#63;.
	 *
	 * @param entitlementDefinitionId the primary key of the current entitlement definition
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	public static EntitlementDefinition[] filterFindByUuid_PrevAndNext(
			long entitlementDefinitionId, String uuid,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().filterFindByUuid_PrevAndNext(
			entitlementDefinitionId, uuid, orderByComparator);
	}

	/**
	 * Removes all the entitlement definitions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of entitlement definitions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching entitlement definitions
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the number of entitlement definitions that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching entitlement definitions that the user has permission to view
	 */
	public static int filterCountByUuid(String uuid) {
		return getPersistence().filterCountByUuid(uuid);
	}

	/**
	 * Returns all the entitlement definitions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the entitlement definitions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @return the range of matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the entitlement definitions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entitlement definitions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first entitlement definition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first entitlement definition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last entitlement definition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last entitlement definition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the entitlement definitions before and after the current entitlement definition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param entitlementDefinitionId the primary key of the current entitlement definition
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	public static EntitlementDefinition[] findByUuid_C_PrevAndNext(
			long entitlementDefinitionId, String uuid, long companyId,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByUuid_C_PrevAndNext(
			entitlementDefinitionId, uuid, companyId, orderByComparator);
	}

	/**
	 * Returns all the entitlement definitions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching entitlement definitions that the user has permission to view
	 */
	public static List<EntitlementDefinition> filterFindByUuid_C(
		String uuid, long companyId) {

		return getPersistence().filterFindByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the entitlement definitions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @return the range of matching entitlement definitions that the user has permission to view
	 */
	public static List<EntitlementDefinition> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().filterFindByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the entitlement definitions that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entitlement definitions that the user has permission to view
	 */
	public static List<EntitlementDefinition> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().filterFindByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the entitlement definitions before and after the current entitlement definition in the ordered set of entitlement definitions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param entitlementDefinitionId the primary key of the current entitlement definition
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	public static EntitlementDefinition[] filterFindByUuid_C_PrevAndNext(
			long entitlementDefinitionId, String uuid, long companyId,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().filterFindByUuid_C_PrevAndNext(
			entitlementDefinitionId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the entitlement definitions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of entitlement definitions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching entitlement definitions
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of entitlement definitions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching entitlement definitions that the user has permission to view
	 */
	public static int filterCountByUuid_C(String uuid, long companyId) {
		return getPersistence().filterCountByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the entitlement definition where entitlementDefinitionKey = &#63; or throws a <code>NoSuchEntitlementDefinitionException</code> if it could not be found.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @return the matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition findByEntitlementDefinitionKey(
			String entitlementDefinitionKey)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByEntitlementDefinitionKey(
			entitlementDefinitionKey);
	}

	/**
	 * Returns the entitlement definition where entitlementDefinitionKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition fetchByEntitlementDefinitionKey(
		String entitlementDefinitionKey) {

		return getPersistence().fetchByEntitlementDefinitionKey(
			entitlementDefinitionKey);
	}

	/**
	 * Returns the entitlement definition where entitlementDefinitionKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition fetchByEntitlementDefinitionKey(
		String entitlementDefinitionKey, boolean useFinderCache) {

		return getPersistence().fetchByEntitlementDefinitionKey(
			entitlementDefinitionKey, useFinderCache);
	}

	/**
	 * Removes the entitlement definition where entitlementDefinitionKey = &#63; from the database.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @return the entitlement definition that was removed
	 */
	public static EntitlementDefinition removeByEntitlementDefinitionKey(
			String entitlementDefinitionKey)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().removeByEntitlementDefinitionKey(
			entitlementDefinitionKey);
	}

	/**
	 * Returns the number of entitlement definitions where entitlementDefinitionKey = &#63;.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @return the number of matching entitlement definitions
	 */
	public static int countByEntitlementDefinitionKey(
		String entitlementDefinitionKey) {

		return getPersistence().countByEntitlementDefinitionKey(
			entitlementDefinitionKey);
	}

	/**
	 * Returns the entitlement definition where classNameId = &#63; and name = &#63; or throws a <code>NoSuchEntitlementDefinitionException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition findByC_N(long classNameId, String name)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByC_N(classNameId, name);
	}

	/**
	 * Returns the entitlement definition where classNameId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition fetchByC_N(
		long classNameId, String name) {

		return getPersistence().fetchByC_N(classNameId, name);
	}

	/**
	 * Returns the entitlement definition where classNameId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition fetchByC_N(
		long classNameId, String name, boolean useFinderCache) {

		return getPersistence().fetchByC_N(classNameId, name, useFinderCache);
	}

	/**
	 * Removes the entitlement definition where classNameId = &#63; and name = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the entitlement definition that was removed
	 */
	public static EntitlementDefinition removeByC_N(
			long classNameId, String name)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().removeByC_N(classNameId, name);
	}

	/**
	 * Returns the number of entitlement definitions where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the number of matching entitlement definitions
	 */
	public static int countByC_N(long classNameId, String name) {
		return getPersistence().countByC_N(classNameId, name);
	}

	/**
	 * Returns all the entitlement definitions where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByC_LikeN(
		long classNameId, String name) {

		return getPersistence().findByC_LikeN(classNameId, name);
	}

	/**
	 * Returns a range of all the entitlement definitions where classNameId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @return the range of matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByC_LikeN(
		long classNameId, String name, int start, int end) {

		return getPersistence().findByC_LikeN(classNameId, name, start, end);
	}

	/**
	 * Returns an ordered range of all the entitlement definitions where classNameId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByC_LikeN(
		long classNameId, String name, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().findByC_LikeN(
			classNameId, name, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entitlement definitions where classNameId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByC_LikeN(
		long classNameId, String name, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_LikeN(
			classNameId, name, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first entitlement definition in the ordered set where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition findByC_LikeN_First(
			long classNameId, String name,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByC_LikeN_First(
			classNameId, name, orderByComparator);
	}

	/**
	 * Returns the first entitlement definition in the ordered set where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition fetchByC_LikeN_First(
		long classNameId, String name,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().fetchByC_LikeN_First(
			classNameId, name, orderByComparator);
	}

	/**
	 * Returns the last entitlement definition in the ordered set where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition findByC_LikeN_Last(
			long classNameId, String name,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByC_LikeN_Last(
			classNameId, name, orderByComparator);
	}

	/**
	 * Returns the last entitlement definition in the ordered set where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition fetchByC_LikeN_Last(
		long classNameId, String name,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().fetchByC_LikeN_Last(
			classNameId, name, orderByComparator);
	}

	/**
	 * Returns the entitlement definitions before and after the current entitlement definition in the ordered set where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param entitlementDefinitionId the primary key of the current entitlement definition
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	public static EntitlementDefinition[] findByC_LikeN_PrevAndNext(
			long entitlementDefinitionId, long classNameId, String name,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByC_LikeN_PrevAndNext(
			entitlementDefinitionId, classNameId, name, orderByComparator);
	}

	/**
	 * Returns all the entitlement definitions that the user has permission to view where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching entitlement definitions that the user has permission to view
	 */
	public static List<EntitlementDefinition> filterFindByC_LikeN(
		long classNameId, String name) {

		return getPersistence().filterFindByC_LikeN(classNameId, name);
	}

	/**
	 * Returns a range of all the entitlement definitions that the user has permission to view where classNameId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @return the range of matching entitlement definitions that the user has permission to view
	 */
	public static List<EntitlementDefinition> filterFindByC_LikeN(
		long classNameId, String name, int start, int end) {

		return getPersistence().filterFindByC_LikeN(
			classNameId, name, start, end);
	}

	/**
	 * Returns an ordered range of all the entitlement definitions that the user has permissions to view where classNameId = &#63; and name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entitlement definitions that the user has permission to view
	 */
	public static List<EntitlementDefinition> filterFindByC_LikeN(
		long classNameId, String name, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().filterFindByC_LikeN(
			classNameId, name, start, end, orderByComparator);
	}

	/**
	 * Returns the entitlement definitions before and after the current entitlement definition in the ordered set of entitlement definitions that the user has permission to view where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param entitlementDefinitionId the primary key of the current entitlement definition
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	public static EntitlementDefinition[] filterFindByC_LikeN_PrevAndNext(
			long entitlementDefinitionId, long classNameId, String name,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().filterFindByC_LikeN_PrevAndNext(
			entitlementDefinitionId, classNameId, name, orderByComparator);
	}

	/**
	 * Removes all the entitlement definitions where classNameId = &#63; and name LIKE &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 */
	public static void removeByC_LikeN(long classNameId, String name) {
		getPersistence().removeByC_LikeN(classNameId, name);
	}

	/**
	 * Returns the number of entitlement definitions where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the number of matching entitlement definitions
	 */
	public static int countByC_LikeN(long classNameId, String name) {
		return getPersistence().countByC_LikeN(classNameId, name);
	}

	/**
	 * Returns the number of entitlement definitions that the user has permission to view where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the number of matching entitlement definitions that the user has permission to view
	 */
	public static int filterCountByC_LikeN(long classNameId, String name) {
		return getPersistence().filterCountByC_LikeN(classNameId, name);
	}

	/**
	 * Returns all the entitlement definitions where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @return the matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByC_S(
		long classNameId, int status) {

		return getPersistence().findByC_S(classNameId, status);
	}

	/**
	 * Returns a range of all the entitlement definitions where classNameId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @return the range of matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByC_S(
		long classNameId, int status, int start, int end) {

		return getPersistence().findByC_S(classNameId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the entitlement definitions where classNameId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByC_S(
		long classNameId, int status, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().findByC_S(
			classNameId, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entitlement definitions where classNameId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entitlement definitions
	 */
	public static List<EntitlementDefinition> findByC_S(
		long classNameId, int status, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_S(
			classNameId, status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first entitlement definition in the ordered set where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition findByC_S_First(
			long classNameId, int status,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByC_S_First(
			classNameId, status, orderByComparator);
	}

	/**
	 * Returns the first entitlement definition in the ordered set where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition fetchByC_S_First(
		long classNameId, int status,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().fetchByC_S_First(
			classNameId, status, orderByComparator);
	}

	/**
	 * Returns the last entitlement definition in the ordered set where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition findByC_S_Last(
			long classNameId, int status,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByC_S_Last(
			classNameId, status, orderByComparator);
	}

	/**
	 * Returns the last entitlement definition in the ordered set where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public static EntitlementDefinition fetchByC_S_Last(
		long classNameId, int status,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().fetchByC_S_Last(
			classNameId, status, orderByComparator);
	}

	/**
	 * Returns the entitlement definitions before and after the current entitlement definition in the ordered set where classNameId = &#63; and status = &#63;.
	 *
	 * @param entitlementDefinitionId the primary key of the current entitlement definition
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	public static EntitlementDefinition[] findByC_S_PrevAndNext(
			long entitlementDefinitionId, long classNameId, int status,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByC_S_PrevAndNext(
			entitlementDefinitionId, classNameId, status, orderByComparator);
	}

	/**
	 * Returns all the entitlement definitions that the user has permission to view where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @return the matching entitlement definitions that the user has permission to view
	 */
	public static List<EntitlementDefinition> filterFindByC_S(
		long classNameId, int status) {

		return getPersistence().filterFindByC_S(classNameId, status);
	}

	/**
	 * Returns a range of all the entitlement definitions that the user has permission to view where classNameId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @return the range of matching entitlement definitions that the user has permission to view
	 */
	public static List<EntitlementDefinition> filterFindByC_S(
		long classNameId, int status, int start, int end) {

		return getPersistence().filterFindByC_S(
			classNameId, status, start, end);
	}

	/**
	 * Returns an ordered range of all the entitlement definitions that the user has permissions to view where classNameId = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entitlement definitions that the user has permission to view
	 */
	public static List<EntitlementDefinition> filterFindByC_S(
		long classNameId, int status, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().filterFindByC_S(
			classNameId, status, start, end, orderByComparator);
	}

	/**
	 * Returns the entitlement definitions before and after the current entitlement definition in the ordered set of entitlement definitions that the user has permission to view where classNameId = &#63; and status = &#63;.
	 *
	 * @param entitlementDefinitionId the primary key of the current entitlement definition
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	public static EntitlementDefinition[] filterFindByC_S_PrevAndNext(
			long entitlementDefinitionId, long classNameId, int status,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().filterFindByC_S_PrevAndNext(
			entitlementDefinitionId, classNameId, status, orderByComparator);
	}

	/**
	 * Removes all the entitlement definitions where classNameId = &#63; and status = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 */
	public static void removeByC_S(long classNameId, int status) {
		getPersistence().removeByC_S(classNameId, status);
	}

	/**
	 * Returns the number of entitlement definitions where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @return the number of matching entitlement definitions
	 */
	public static int countByC_S(long classNameId, int status) {
		return getPersistence().countByC_S(classNameId, status);
	}

	/**
	 * Returns the number of entitlement definitions that the user has permission to view where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @return the number of matching entitlement definitions that the user has permission to view
	 */
	public static int filterCountByC_S(long classNameId, int status) {
		return getPersistence().filterCountByC_S(classNameId, status);
	}

	/**
	 * Caches the entitlement definition in the entity cache if it is enabled.
	 *
	 * @param entitlementDefinition the entitlement definition
	 */
	public static void cacheResult(
		EntitlementDefinition entitlementDefinition) {

		getPersistence().cacheResult(entitlementDefinition);
	}

	/**
	 * Caches the entitlement definitions in the entity cache if it is enabled.
	 *
	 * @param entitlementDefinitions the entitlement definitions
	 */
	public static void cacheResult(
		List<EntitlementDefinition> entitlementDefinitions) {

		getPersistence().cacheResult(entitlementDefinitions);
	}

	/**
	 * Creates a new entitlement definition with the primary key. Does not add the entitlement definition to the database.
	 *
	 * @param entitlementDefinitionId the primary key for the new entitlement definition
	 * @return the new entitlement definition
	 */
	public static EntitlementDefinition create(long entitlementDefinitionId) {
		return getPersistence().create(entitlementDefinitionId);
	}

	/**
	 * Removes the entitlement definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition that was removed
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	public static EntitlementDefinition remove(long entitlementDefinitionId)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().remove(entitlementDefinitionId);
	}

	public static EntitlementDefinition updateImpl(
		EntitlementDefinition entitlementDefinition) {

		return getPersistence().updateImpl(entitlementDefinition);
	}

	/**
	 * Returns the entitlement definition with the primary key or throws a <code>NoSuchEntitlementDefinitionException</code> if it could not be found.
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	public static EntitlementDefinition findByPrimaryKey(
			long entitlementDefinitionId)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementDefinitionException {

		return getPersistence().findByPrimaryKey(entitlementDefinitionId);
	}

	/**
	 * Returns the entitlement definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition, or <code>null</code> if a entitlement definition with the primary key could not be found
	 */
	public static EntitlementDefinition fetchByPrimaryKey(
		long entitlementDefinitionId) {

		return getPersistence().fetchByPrimaryKey(entitlementDefinitionId);
	}

	/**
	 * Returns all the entitlement definitions.
	 *
	 * @return the entitlement definitions
	 */
	public static List<EntitlementDefinition> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the entitlement definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @return the range of entitlement definitions
	 */
	public static List<EntitlementDefinition> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the entitlement definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of entitlement definitions
	 */
	public static List<EntitlementDefinition> findAll(
		int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entitlement definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementDefinitionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of entitlement definitions
	 * @param end the upper bound of the range of entitlement definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of entitlement definitions
	 */
	public static List<EntitlementDefinition> findAll(
		int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the entitlement definitions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of entitlement definitions.
	 *
	 * @return the number of entitlement definitions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EntitlementDefinitionPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		EntitlementDefinitionPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile EntitlementDefinitionPersistence _persistence;

}