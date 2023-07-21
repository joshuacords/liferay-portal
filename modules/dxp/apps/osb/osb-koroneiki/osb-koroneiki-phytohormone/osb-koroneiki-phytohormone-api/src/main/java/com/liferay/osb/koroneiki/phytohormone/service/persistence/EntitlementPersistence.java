/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service.persistence;

import com.liferay.osb.koroneiki.phytohormone.exception.NoSuchEntitlementException;
import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the entitlement service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementUtil
 * @generated
 */
@ProviderType
public interface EntitlementPersistence extends BasePersistence<Entitlement> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EntitlementUtil} to access the entitlement persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the entitlements where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @return the matching entitlements
	 */
	public java.util.List<Entitlement> findByEntitlementDefinitionId(
		long entitlementDefinitionId);

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
	public java.util.List<Entitlement> findByEntitlementDefinitionId(
		long entitlementDefinitionId, int start, int end);

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
	public java.util.List<Entitlement> findByEntitlementDefinitionId(
		long entitlementDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
			orderByComparator);

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
	public java.util.List<Entitlement> findByEntitlementDefinitionId(
		long entitlementDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement
	 * @throws NoSuchEntitlementException if a matching entitlement could not be found
	 */
	public Entitlement findByEntitlementDefinitionId_First(
			long entitlementDefinitionId,
			com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
				orderByComparator)
		throws NoSuchEntitlementException;

	/**
	 * Returns the first entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement, or <code>null</code> if a matching entitlement could not be found
	 */
	public Entitlement fetchByEntitlementDefinitionId_First(
		long entitlementDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
			orderByComparator);

	/**
	 * Returns the last entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement
	 * @throws NoSuchEntitlementException if a matching entitlement could not be found
	 */
	public Entitlement findByEntitlementDefinitionId_Last(
			long entitlementDefinitionId,
			com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
				orderByComparator)
		throws NoSuchEntitlementException;

	/**
	 * Returns the last entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement, or <code>null</code> if a matching entitlement could not be found
	 */
	public Entitlement fetchByEntitlementDefinitionId_Last(
		long entitlementDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
			orderByComparator);

	/**
	 * Returns the entitlements before and after the current entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementId the primary key of the current entitlement
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement
	 * @throws NoSuchEntitlementException if a entitlement with the primary key could not be found
	 */
	public Entitlement[] findByEntitlementDefinitionId_PrevAndNext(
			long entitlementId, long entitlementDefinitionId,
			com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
				orderByComparator)
		throws NoSuchEntitlementException;

	/**
	 * Removes all the entitlements where entitlementDefinitionId = &#63; from the database.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 */
	public void removeByEntitlementDefinitionId(long entitlementDefinitionId);

	/**
	 * Returns the number of entitlements where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @return the number of matching entitlements
	 */
	public int countByEntitlementDefinitionId(long entitlementDefinitionId);

	/**
	 * Returns all the entitlements where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching entitlements
	 */
	public java.util.List<Entitlement> findByC_C(
		long classNameId, long classPK);

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
	public java.util.List<Entitlement> findByC_C(
		long classNameId, long classPK, int start, int end);

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
	public java.util.List<Entitlement> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
			orderByComparator);

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
	public java.util.List<Entitlement> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first entitlement in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement
	 * @throws NoSuchEntitlementException if a matching entitlement could not be found
	 */
	public Entitlement findByC_C_First(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
				orderByComparator)
		throws NoSuchEntitlementException;

	/**
	 * Returns the first entitlement in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement, or <code>null</code> if a matching entitlement could not be found
	 */
	public Entitlement fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
			orderByComparator);

	/**
	 * Returns the last entitlement in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement
	 * @throws NoSuchEntitlementException if a matching entitlement could not be found
	 */
	public Entitlement findByC_C_Last(
			long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
				orderByComparator)
		throws NoSuchEntitlementException;

	/**
	 * Returns the last entitlement in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement, or <code>null</code> if a matching entitlement could not be found
	 */
	public Entitlement fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
			orderByComparator);

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
	public Entitlement[] findByC_C_PrevAndNext(
			long entitlementId, long classNameId, long classPK,
			com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
				orderByComparator)
		throws NoSuchEntitlementException;

	/**
	 * Removes all the entitlements where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	public void removeByC_C(long classNameId, long classPK);

	/**
	 * Returns the number of entitlements where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching entitlements
	 */
	public int countByC_C(long classNameId, long classPK);

	/**
	 * Caches the entitlement in the entity cache if it is enabled.
	 *
	 * @param entitlement the entitlement
	 */
	public void cacheResult(Entitlement entitlement);

	/**
	 * Caches the entitlements in the entity cache if it is enabled.
	 *
	 * @param entitlements the entitlements
	 */
	public void cacheResult(java.util.List<Entitlement> entitlements);

	/**
	 * Creates a new entitlement with the primary key. Does not add the entitlement to the database.
	 *
	 * @param entitlementId the primary key for the new entitlement
	 * @return the new entitlement
	 */
	public Entitlement create(long entitlementId);

	/**
	 * Removes the entitlement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entitlementId the primary key of the entitlement
	 * @return the entitlement that was removed
	 * @throws NoSuchEntitlementException if a entitlement with the primary key could not be found
	 */
	public Entitlement remove(long entitlementId)
		throws NoSuchEntitlementException;

	public Entitlement updateImpl(Entitlement entitlement);

	/**
	 * Returns the entitlement with the primary key or throws a <code>NoSuchEntitlementException</code> if it could not be found.
	 *
	 * @param entitlementId the primary key of the entitlement
	 * @return the entitlement
	 * @throws NoSuchEntitlementException if a entitlement with the primary key could not be found
	 */
	public Entitlement findByPrimaryKey(long entitlementId)
		throws NoSuchEntitlementException;

	/**
	 * Returns the entitlement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entitlementId the primary key of the entitlement
	 * @return the entitlement, or <code>null</code> if a entitlement with the primary key could not be found
	 */
	public Entitlement fetchByPrimaryKey(long entitlementId);

	/**
	 * Returns all the entitlements.
	 *
	 * @return the entitlements
	 */
	public java.util.List<Entitlement> findAll();

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
	public java.util.List<Entitlement> findAll(int start, int end);

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
	public java.util.List<Entitlement> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
			orderByComparator);

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
	public java.util.List<Entitlement> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Entitlement>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the entitlements from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of entitlements.
	 *
	 * @return the number of entitlements
	 */
	public int countAll();

}