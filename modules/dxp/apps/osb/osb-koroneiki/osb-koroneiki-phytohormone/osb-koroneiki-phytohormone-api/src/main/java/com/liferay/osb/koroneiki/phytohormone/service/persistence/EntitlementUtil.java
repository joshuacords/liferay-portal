/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service.persistence;

import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the entitlement service. This utility wraps <code>com.liferay.osb.koroneiki.phytohormone.service.persistence.impl.EntitlementPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementPersistence
 * @generated
 */
public class EntitlementUtil {

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
	public static void clearCache(Entitlement entitlement) {
		getPersistence().clearCache(entitlement);
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
	public static Map<Serializable, Entitlement> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Entitlement> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Entitlement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Entitlement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Entitlement> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Entitlement update(Entitlement entitlement) {
		return getPersistence().update(entitlement);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Entitlement update(
		Entitlement entitlement, ServiceContext serviceContext) {

		return getPersistence().update(entitlement, serviceContext);
	}

	/**
	 * Returns all the entitlements where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @return the matching entitlements
	 */
	public static List<Entitlement> findByEntitlementDefinitionId(
		long entitlementDefinitionId) {

		return getPersistence().findByEntitlementDefinitionId(
			entitlementDefinitionId);
	}

	/**
	 * Returns a range of all the entitlements where entitlementDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @return the range of matching entitlements
	 */
	public static List<Entitlement> findByEntitlementDefinitionId(
		long entitlementDefinitionId, int start, int end) {

		return getPersistence().findByEntitlementDefinitionId(
			entitlementDefinitionId, start, end);
	}

	/**
	 * Returns an ordered range of all the entitlements where entitlementDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entitlements
	 */
	public static List<Entitlement> findByEntitlementDefinitionId(
		long entitlementDefinitionId, int start, int end,
		OrderByComparator<Entitlement> orderByComparator) {

		return getPersistence().findByEntitlementDefinitionId(
			entitlementDefinitionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entitlements where entitlementDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entitlements
	 */
	public static List<Entitlement> findByEntitlementDefinitionId(
		long entitlementDefinitionId, int start, int end,
		OrderByComparator<Entitlement> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByEntitlementDefinitionId(
			entitlementDefinitionId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement
	 * @throws NoSuchEntitlementException if a matching entitlement could not be found
	 */
	public static Entitlement findByEntitlementDefinitionId_First(
			long entitlementDefinitionId,
			OrderByComparator<Entitlement> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementException {

		return getPersistence().findByEntitlementDefinitionId_First(
			entitlementDefinitionId, orderByComparator);
	}

	/**
	 * Returns the first entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement, or <code>null</code> if a matching entitlement could not be found
	 */
	public static Entitlement fetchByEntitlementDefinitionId_First(
		long entitlementDefinitionId,
		OrderByComparator<Entitlement> orderByComparator) {

		return getPersistence().fetchByEntitlementDefinitionId_First(
			entitlementDefinitionId, orderByComparator);
	}

	/**
	 * Returns the last entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement
	 * @throws NoSuchEntitlementException if a matching entitlement could not be found
	 */
	public static Entitlement findByEntitlementDefinitionId_Last(
			long entitlementDefinitionId,
			OrderByComparator<Entitlement> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementException {

		return getPersistence().findByEntitlementDefinitionId_Last(
			entitlementDefinitionId, orderByComparator);
	}

	/**
	 * Returns the last entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement, or <code>null</code> if a matching entitlement could not be found
	 */
	public static Entitlement fetchByEntitlementDefinitionId_Last(
		long entitlementDefinitionId,
		OrderByComparator<Entitlement> orderByComparator) {

		return getPersistence().fetchByEntitlementDefinitionId_Last(
			entitlementDefinitionId, orderByComparator);
	}

	/**
	 * Returns the entitlements before and after the current entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementId the primary key of the current entitlement
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement
	 * @throws NoSuchEntitlementException if a entitlement with the primary key could not be found
	 */
	public static Entitlement[] findByEntitlementDefinitionId_PrevAndNext(
			long entitlementId, long entitlementDefinitionId,
			OrderByComparator<Entitlement> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementException {

		return getPersistence().findByEntitlementDefinitionId_PrevAndNext(
			entitlementId, entitlementDefinitionId, orderByComparator);
	}

	/**
	 * Removes all the entitlements where entitlementDefinitionId = &#63; from the database.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 */
	public static void removeByEntitlementDefinitionId(
		long entitlementDefinitionId) {

		getPersistence().removeByEntitlementDefinitionId(
			entitlementDefinitionId);
	}

	/**
	 * Returns the number of entitlements where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @return the number of matching entitlements
	 */
	public static int countByEntitlementDefinitionId(
		long entitlementDefinitionId) {

		return getPersistence().countByEntitlementDefinitionId(
			entitlementDefinitionId);
	}

	/**
	 * Returns all the entitlements where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching entitlements
	 */
	public static List<Entitlement> findByC_C(long classNameId, long classPK) {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	 * Returns a range of all the entitlements where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @return the range of matching entitlements
	 */
	public static List<Entitlement> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the entitlements where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entitlements
	 */
	public static List<Entitlement> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<Entitlement> orderByComparator) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entitlements where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entitlements
	 */
	public static List<Entitlement> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<Entitlement> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first entitlement in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement
	 * @throws NoSuchEntitlementException if a matching entitlement could not be found
	 */
	public static Entitlement findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<Entitlement> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementException {

		return getPersistence().findByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first entitlement in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement, or <code>null</code> if a matching entitlement could not be found
	 */
	public static Entitlement fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<Entitlement> orderByComparator) {

		return getPersistence().fetchByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last entitlement in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement
	 * @throws NoSuchEntitlementException if a matching entitlement could not be found
	 */
	public static Entitlement findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<Entitlement> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementException {

		return getPersistence().findByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last entitlement in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement, or <code>null</code> if a matching entitlement could not be found
	 */
	public static Entitlement fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<Entitlement> orderByComparator) {

		return getPersistence().fetchByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the entitlements before and after the current entitlement in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param entitlementId the primary key of the current entitlement
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement
	 * @throws NoSuchEntitlementException if a entitlement with the primary key could not be found
	 */
	public static Entitlement[] findByC_C_PrevAndNext(
			long entitlementId, long classNameId, long classPK,
			OrderByComparator<Entitlement> orderByComparator)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementException {

		return getPersistence().findByC_C_PrevAndNext(
			entitlementId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Removes all the entitlements where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	 * Returns the number of entitlements where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching entitlements
	 */
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	 * Caches the entitlement in the entity cache if it is enabled.
	 *
	 * @param entitlement the entitlement
	 */
	public static void cacheResult(Entitlement entitlement) {
		getPersistence().cacheResult(entitlement);
	}

	/**
	 * Caches the entitlements in the entity cache if it is enabled.
	 *
	 * @param entitlements the entitlements
	 */
	public static void cacheResult(List<Entitlement> entitlements) {
		getPersistence().cacheResult(entitlements);
	}

	/**
	 * Creates a new entitlement with the primary key. Does not add the entitlement to the database.
	 *
	 * @param entitlementId the primary key for the new entitlement
	 * @return the new entitlement
	 */
	public static Entitlement create(long entitlementId) {
		return getPersistence().create(entitlementId);
	}

	/**
	 * Removes the entitlement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entitlementId the primary key of the entitlement
	 * @return the entitlement that was removed
	 * @throws NoSuchEntitlementException if a entitlement with the primary key could not be found
	 */
	public static Entitlement remove(long entitlementId)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementException {

		return getPersistence().remove(entitlementId);
	}

	public static Entitlement updateImpl(Entitlement entitlement) {
		return getPersistence().updateImpl(entitlement);
	}

	/**
	 * Returns the entitlement with the primary key or throws a <code>NoSuchEntitlementException</code> if it could not be found.
	 *
	 * @param entitlementId the primary key of the entitlement
	 * @return the entitlement
	 * @throws NoSuchEntitlementException if a entitlement with the primary key could not be found
	 */
	public static Entitlement findByPrimaryKey(long entitlementId)
		throws com.liferay.osb.koroneiki.phytohormone.exception.
			NoSuchEntitlementException {

		return getPersistence().findByPrimaryKey(entitlementId);
	}

	/**
	 * Returns the entitlement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entitlementId the primary key of the entitlement
	 * @return the entitlement, or <code>null</code> if a entitlement with the primary key could not be found
	 */
	public static Entitlement fetchByPrimaryKey(long entitlementId) {
		return getPersistence().fetchByPrimaryKey(entitlementId);
	}

	/**
	 * Returns all the entitlements.
	 *
	 * @return the entitlements
	 */
	public static List<Entitlement> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the entitlements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @return the range of entitlements
	 */
	public static List<Entitlement> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the entitlements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of entitlements
	 */
	public static List<Entitlement> findAll(
		int start, int end, OrderByComparator<Entitlement> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the entitlements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of entitlements
	 */
	public static List<Entitlement> findAll(
		int start, int end, OrderByComparator<Entitlement> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the entitlements from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of entitlements.
	 *
	 * @return the number of entitlements
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EntitlementPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(EntitlementPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile EntitlementPersistence _persistence;

}