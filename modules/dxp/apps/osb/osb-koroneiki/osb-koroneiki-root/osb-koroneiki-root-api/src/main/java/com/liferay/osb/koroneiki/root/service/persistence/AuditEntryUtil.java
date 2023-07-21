/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service.persistence;

import com.liferay.osb.koroneiki.root.model.AuditEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the audit entry service. This utility wraps <code>com.liferay.osb.koroneiki.root.service.persistence.impl.AuditEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryPersistence
 * @generated
 */
public class AuditEntryUtil {

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
	public static void clearCache(AuditEntry auditEntry) {
		getPersistence().clearCache(auditEntry);
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
	public static Map<Serializable, AuditEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AuditEntry update(AuditEntry auditEntry) {
		return getPersistence().update(auditEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AuditEntry update(
		AuditEntry auditEntry, ServiceContext serviceContext) {

		return getPersistence().update(auditEntry, serviceContext);
	}

	/**
	 * Returns the audit entry where auditEntryKey = &#63; or throws a <code>NoSuchAuditEntryException</code> if it could not be found.
	 *
	 * @param auditEntryKey the audit entry key
	 * @return the matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	public static AuditEntry findByAuditEntryKey(String auditEntryKey)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByAuditEntryKey(auditEntryKey);
	}

	/**
	 * Returns the audit entry where auditEntryKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param auditEntryKey the audit entry key
	 * @return the matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public static AuditEntry fetchByAuditEntryKey(String auditEntryKey) {
		return getPersistence().fetchByAuditEntryKey(auditEntryKey);
	}

	/**
	 * Returns the audit entry where auditEntryKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param auditEntryKey the audit entry key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public static AuditEntry fetchByAuditEntryKey(
		String auditEntryKey, boolean useFinderCache) {

		return getPersistence().fetchByAuditEntryKey(
			auditEntryKey, useFinderCache);
	}

	/**
	 * Removes the audit entry where auditEntryKey = &#63; from the database.
	 *
	 * @param auditEntryKey the audit entry key
	 * @return the audit entry that was removed
	 */
	public static AuditEntry removeByAuditEntryKey(String auditEntryKey)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchAuditEntryException {

		return getPersistence().removeByAuditEntryKey(auditEntryKey);
	}

	/**
	 * Returns the number of audit entries where auditEntryKey = &#63;.
	 *
	 * @param auditEntryKey the audit entry key
	 * @return the number of matching audit entries
	 */
	public static int countByAuditEntryKey(String auditEntryKey) {
		return getPersistence().countByAuditEntryKey(auditEntryKey);
	}

	/**
	 * Returns all the audit entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching audit entries
	 */
	public static List<AuditEntry> findByC_C(long classNameId, long classPK) {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	 * Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of matching audit entries
	 */
	public static List<AuditEntry> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit entries
	 */
	public static List<AuditEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching audit entries
	 */
	public static List<AuditEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C(
			classNameId, classPK, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	public static AuditEntry findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public static AuditEntry fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().fetchByC_C_First(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	public static AuditEntry findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public static AuditEntry fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().fetchByC_C_Last(
			classNameId, classPK, orderByComparator);
	}

	/**
	 * Returns the audit entries before and after the current audit entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param auditEntryId the primary key of the current audit entry
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit entry
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	public static AuditEntry[] findByC_C_PrevAndNext(
			long auditEntryId, long classNameId, long classPK,
			OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByC_C_PrevAndNext(
			auditEntryId, classNameId, classPK, orderByComparator);
	}

	/**
	 * Removes all the audit entries where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public static void removeByC_C(long classNameId, long classPK) {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	 * Returns the number of audit entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching audit entries
	 */
	public static int countByC_C(long classNameId, long classPK) {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	 * Returns all the audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @return the matching audit entries
	 */
	public static List<AuditEntry> findByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK) {

		return getPersistence().findByC_C_FC_FC(
			classNameId, classPK, fieldClassNameId, fieldClassPK);
	}

	/**
	 * Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of matching audit entries
	 */
	public static List<AuditEntry> findByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK, int start, int end) {

		return getPersistence().findByC_C_FC_FC(
			classNameId, classPK, fieldClassNameId, fieldClassPK, start, end);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit entries
	 */
	public static List<AuditEntry> findByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().findByC_C_FC_FC(
			classNameId, classPK, fieldClassNameId, fieldClassPK, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching audit entries
	 */
	public static List<AuditEntry> findByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C_FC_FC(
			classNameId, classPK, fieldClassNameId, fieldClassPK, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	public static AuditEntry findByC_C_FC_FC_First(
			long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK, OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByC_C_FC_FC_First(
			classNameId, classPK, fieldClassNameId, fieldClassPK,
			orderByComparator);
	}

	/**
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public static AuditEntry fetchByC_C_FC_FC_First(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK, OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().fetchByC_C_FC_FC_First(
			classNameId, classPK, fieldClassNameId, fieldClassPK,
			orderByComparator);
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	public static AuditEntry findByC_C_FC_FC_Last(
			long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK, OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByC_C_FC_FC_Last(
			classNameId, classPK, fieldClassNameId, fieldClassPK,
			orderByComparator);
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public static AuditEntry fetchByC_C_FC_FC_Last(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK, OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().fetchByC_C_FC_FC_Last(
			classNameId, classPK, fieldClassNameId, fieldClassPK,
			orderByComparator);
	}

	/**
	 * Returns the audit entries before and after the current audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param auditEntryId the primary key of the current audit entry
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit entry
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	public static AuditEntry[] findByC_C_FC_FC_PrevAndNext(
			long auditEntryId, long classNameId, long classPK,
			long fieldClassNameId, long fieldClassPK,
			OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByC_C_FC_FC_PrevAndNext(
			auditEntryId, classNameId, classPK, fieldClassNameId, fieldClassPK,
			orderByComparator);
	}

	/**
	 * Removes all the audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 */
	public static void removeByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK) {

		getPersistence().removeByC_C_FC_FC(
			classNameId, classPK, fieldClassNameId, fieldClassPK);
	}

	/**
	 * Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @return the number of matching audit entries
	 */
	public static int countByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK) {

		return getPersistence().countByC_C_FC_FC(
			classNameId, classPK, fieldClassNameId, fieldClassPK);
	}

	/**
	 * Caches the audit entry in the entity cache if it is enabled.
	 *
	 * @param auditEntry the audit entry
	 */
	public static void cacheResult(AuditEntry auditEntry) {
		getPersistence().cacheResult(auditEntry);
	}

	/**
	 * Caches the audit entries in the entity cache if it is enabled.
	 *
	 * @param auditEntries the audit entries
	 */
	public static void cacheResult(List<AuditEntry> auditEntries) {
		getPersistence().cacheResult(auditEntries);
	}

	/**
	 * Creates a new audit entry with the primary key. Does not add the audit entry to the database.
	 *
	 * @param auditEntryId the primary key for the new audit entry
	 * @return the new audit entry
	 */
	public static AuditEntry create(long auditEntryId) {
		return getPersistence().create(auditEntryId);
	}

	/**
	 * Removes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry that was removed
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	public static AuditEntry remove(long auditEntryId)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchAuditEntryException {

		return getPersistence().remove(auditEntryId);
	}

	public static AuditEntry updateImpl(AuditEntry auditEntry) {
		return getPersistence().updateImpl(auditEntry);
	}

	/**
	 * Returns the audit entry with the primary key or throws a <code>NoSuchAuditEntryException</code> if it could not be found.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	public static AuditEntry findByPrimaryKey(long auditEntryId)
		throws com.liferay.osb.koroneiki.root.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByPrimaryKey(auditEntryId);
	}

	/**
	 * Returns the audit entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry, or <code>null</code> if a audit entry with the primary key could not be found
	 */
	public static AuditEntry fetchByPrimaryKey(long auditEntryId) {
		return getPersistence().fetchByPrimaryKey(auditEntryId);
	}

	/**
	 * Returns all the audit entries.
	 *
	 * @return the audit entries
	 */
	public static List<AuditEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of audit entries
	 */
	public static List<AuditEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of audit entries
	 */
	public static List<AuditEntry> findAll(
		int start, int end, OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of audit entries
	 */
	public static List<AuditEntry> findAll(
		int start, int end, OrderByComparator<AuditEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the audit entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of audit entries.
	 *
	 * @return the number of audit entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AuditEntryPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(AuditEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile AuditEntryPersistence _persistence;

}