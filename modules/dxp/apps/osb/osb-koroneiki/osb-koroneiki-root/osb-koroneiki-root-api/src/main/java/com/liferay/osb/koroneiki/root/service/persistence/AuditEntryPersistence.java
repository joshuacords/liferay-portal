/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service.persistence;

import com.liferay.osb.koroneiki.root.exception.NoSuchAuditEntryException;
import com.liferay.osb.koroneiki.root.model.AuditEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the audit entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryUtil
 * @generated
 */
@ProviderType
public interface AuditEntryPersistence extends BasePersistence<AuditEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AuditEntryUtil} to access the audit entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the audit entry where auditEntryKey = &#63; or throws a <code>NoSuchAuditEntryException</code> if it could not be found.
	 *
	 * @param auditEntryKey the audit entry key
	 * @return the matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	public AuditEntry findByAuditEntryKey(String auditEntryKey)
		throws NoSuchAuditEntryException;

	/**
	 * Returns the audit entry where auditEntryKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param auditEntryKey the audit entry key
	 * @return the matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public AuditEntry fetchByAuditEntryKey(String auditEntryKey);

	/**
	 * Returns the audit entry where auditEntryKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param auditEntryKey the audit entry key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public AuditEntry fetchByAuditEntryKey(
		String auditEntryKey, boolean useFinderCache);

	/**
	 * Removes the audit entry where auditEntryKey = &#63; from the database.
	 *
	 * @param auditEntryKey the audit entry key
	 * @return the audit entry that was removed
	 */
	public AuditEntry removeByAuditEntryKey(String auditEntryKey)
		throws NoSuchAuditEntryException;

	/**
	 * Returns the number of audit entries where auditEntryKey = &#63;.
	 *
	 * @param auditEntryKey the audit entry key
	 * @return the number of matching audit entries
	 */
	public int countByAuditEntryKey(String auditEntryKey);

	/**
	 * Returns all the audit entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching audit entries
	 */
	public java.util.List<AuditEntry> findByC_C(long classNameId, long classPK);

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
	public java.util.List<AuditEntry> findByC_C(
		long classNameId, long classPK, int start, int end);

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
	public java.util.List<AuditEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
			orderByComparator);

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
	public java.util.List<AuditEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	public AuditEntry findByC_C_First(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
				orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public AuditEntry fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
			orderByComparator);

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	public AuditEntry findByC_C_Last(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
				orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public AuditEntry fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
			orderByComparator);

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
	public AuditEntry[] findByC_C_PrevAndNext(
			long auditEntryId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
				orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	 * Removes all the audit entries where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByC_C(long classNameId, long classPK);

	/**
	 * Returns the number of audit entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching audit entries
	 */
	public int countByC_C(long classNameId, long classPK);

	/**
	 * Returns all the audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @return the matching audit entries
	 */
	public java.util.List<AuditEntry> findByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK);

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
	public java.util.List<AuditEntry> findByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK, int start, int end);

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
	public java.util.List<AuditEntry> findByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
			orderByComparator);

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
	public java.util.List<AuditEntry> findByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
			orderByComparator,
		boolean useFinderCache);

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
	public AuditEntry findByC_C_FC_FC_First(
			long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK,
			com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
				orderByComparator)
		throws NoSuchAuditEntryException;

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
	public AuditEntry fetchByC_C_FC_FC_First(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
			orderByComparator);

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
	public AuditEntry findByC_C_FC_FC_Last(
			long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK,
			com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
				orderByComparator)
		throws NoSuchAuditEntryException;

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
	public AuditEntry fetchByC_C_FC_FC_Last(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
			orderByComparator);

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
	public AuditEntry[] findByC_C_FC_FC_PrevAndNext(
			long auditEntryId, long classNameId, long classPK,
			long fieldClassNameId, long fieldClassPK,
			com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
				orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	 * Removes all the audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 */
	public void removeByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK);

	/**
	 * Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @return the number of matching audit entries
	 */
	public int countByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK);

	/**
	 * Caches the audit entry in the entity cache if it is enabled.
	 *
	 * @param auditEntry the audit entry
	 */
	public void cacheResult(AuditEntry auditEntry);

	/**
	 * Caches the audit entries in the entity cache if it is enabled.
	 *
	 * @param auditEntries the audit entries
	 */
	public void cacheResult(java.util.List<AuditEntry> auditEntries);

	/**
	 * Creates a new audit entry with the primary key. Does not add the audit entry to the database.
	 *
	 * @param auditEntryId the primary key for the new audit entry
	 * @return the new audit entry
	 */
	public AuditEntry create(long auditEntryId);

	/**
	 * Removes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry that was removed
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	public AuditEntry remove(long auditEntryId)
		throws NoSuchAuditEntryException;

	public AuditEntry updateImpl(AuditEntry auditEntry);

	/**
	 * Returns the audit entry with the primary key or throws a <code>NoSuchAuditEntryException</code> if it could not be found.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	public AuditEntry findByPrimaryKey(long auditEntryId)
		throws NoSuchAuditEntryException;

	/**
	 * Returns the audit entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry, or <code>null</code> if a audit entry with the primary key could not be found
	 */
	public AuditEntry fetchByPrimaryKey(long auditEntryId);

	/**
	 * Returns all the audit entries.
	 *
	 * @return the audit entries
	 */
	public java.util.List<AuditEntry> findAll();

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
	public java.util.List<AuditEntry> findAll(int start, int end);

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
	public java.util.List<AuditEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
			orderByComparator);

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
	public java.util.List<AuditEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the audit entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of audit entries.
	 *
	 * @return the number of audit entries
	 */
	public int countAll();

}