/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service.persistence;

import com.liferay.osb.koroneiki.phytohormone.exception.NoSuchEntitlementDefinitionException;
import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the entitlement definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntitlementDefinitionUtil
 * @generated
 */
@ProviderType
public interface EntitlementDefinitionPersistence
	extends BasePersistence<EntitlementDefinition> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EntitlementDefinitionUtil} to access the entitlement definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the entitlement definitions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching entitlement definitions
	 */
	public java.util.List<EntitlementDefinition> findByUuid(String uuid);

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
	public java.util.List<EntitlementDefinition> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<EntitlementDefinition> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

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
	public java.util.List<EntitlementDefinition> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns the first entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

	/**
	 * Returns the last entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns the last entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

	/**
	 * Returns the entitlement definitions before and after the current entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param entitlementDefinitionId the primary key of the current entitlement definition
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	public EntitlementDefinition[] findByUuid_PrevAndNext(
			long entitlementDefinitionId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns all the entitlement definitions that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching entitlement definitions that the user has permission to view
	 */
	public java.util.List<EntitlementDefinition> filterFindByUuid(String uuid);

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
	public java.util.List<EntitlementDefinition> filterFindByUuid(
		String uuid, int start, int end);

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
	public java.util.List<EntitlementDefinition> filterFindByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

	/**
	 * Returns the entitlement definitions before and after the current entitlement definition in the ordered set of entitlement definitions that the user has permission to view where uuid = &#63;.
	 *
	 * @param entitlementDefinitionId the primary key of the current entitlement definition
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	public EntitlementDefinition[] filterFindByUuid_PrevAndNext(
			long entitlementDefinitionId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Removes all the entitlement definitions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of entitlement definitions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching entitlement definitions
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the number of entitlement definitions that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching entitlement definitions that the user has permission to view
	 */
	public int filterCountByUuid(String uuid);

	/**
	 * Returns all the entitlement definitions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching entitlement definitions
	 */
	public java.util.List<EntitlementDefinition> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<EntitlementDefinition> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<EntitlementDefinition> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

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
	public java.util.List<EntitlementDefinition> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first entitlement definition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns the first entitlement definition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

	/**
	 * Returns the last entitlement definition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns the last entitlement definition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

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
	public EntitlementDefinition[] findByUuid_C_PrevAndNext(
			long entitlementDefinitionId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns all the entitlement definitions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching entitlement definitions that the user has permission to view
	 */
	public java.util.List<EntitlementDefinition> filterFindByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<EntitlementDefinition> filterFindByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<EntitlementDefinition> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

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
	public EntitlementDefinition[] filterFindByUuid_C_PrevAndNext(
			long entitlementDefinitionId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Removes all the entitlement definitions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of entitlement definitions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching entitlement definitions
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of entitlement definitions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching entitlement definitions that the user has permission to view
	 */
	public int filterCountByUuid_C(String uuid, long companyId);

	/**
	 * Returns the entitlement definition where entitlementDefinitionKey = &#63; or throws a <code>NoSuchEntitlementDefinitionException</code> if it could not be found.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @return the matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition findByEntitlementDefinitionKey(
			String entitlementDefinitionKey)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns the entitlement definition where entitlementDefinitionKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition fetchByEntitlementDefinitionKey(
		String entitlementDefinitionKey);

	/**
	 * Returns the entitlement definition where entitlementDefinitionKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition fetchByEntitlementDefinitionKey(
		String entitlementDefinitionKey, boolean useFinderCache);

	/**
	 * Removes the entitlement definition where entitlementDefinitionKey = &#63; from the database.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @return the entitlement definition that was removed
	 */
	public EntitlementDefinition removeByEntitlementDefinitionKey(
			String entitlementDefinitionKey)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns the number of entitlement definitions where entitlementDefinitionKey = &#63;.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @return the number of matching entitlement definitions
	 */
	public int countByEntitlementDefinitionKey(String entitlementDefinitionKey);

	/**
	 * Returns the entitlement definition where classNameId = &#63; and name = &#63; or throws a <code>NoSuchEntitlementDefinitionException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition findByC_N(long classNameId, String name)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns the entitlement definition where classNameId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition fetchByC_N(long classNameId, String name);

	/**
	 * Returns the entitlement definition where classNameId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition fetchByC_N(
		long classNameId, String name, boolean useFinderCache);

	/**
	 * Removes the entitlement definition where classNameId = &#63; and name = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the entitlement definition that was removed
	 */
	public EntitlementDefinition removeByC_N(long classNameId, String name)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns the number of entitlement definitions where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the number of matching entitlement definitions
	 */
	public int countByC_N(long classNameId, String name);

	/**
	 * Returns all the entitlement definitions where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching entitlement definitions
	 */
	public java.util.List<EntitlementDefinition> findByC_LikeN(
		long classNameId, String name);

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
	public java.util.List<EntitlementDefinition> findByC_LikeN(
		long classNameId, String name, int start, int end);

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
	public java.util.List<EntitlementDefinition> findByC_LikeN(
		long classNameId, String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

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
	public java.util.List<EntitlementDefinition> findByC_LikeN(
		long classNameId, String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first entitlement definition in the ordered set where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition findByC_LikeN_First(
			long classNameId, String name,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns the first entitlement definition in the ordered set where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition fetchByC_LikeN_First(
		long classNameId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

	/**
	 * Returns the last entitlement definition in the ordered set where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition findByC_LikeN_Last(
			long classNameId, String name,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns the last entitlement definition in the ordered set where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition fetchByC_LikeN_Last(
		long classNameId, String name,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

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
	public EntitlementDefinition[] findByC_LikeN_PrevAndNext(
			long entitlementDefinitionId, long classNameId, String name,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns all the entitlement definitions that the user has permission to view where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching entitlement definitions that the user has permission to view
	 */
	public java.util.List<EntitlementDefinition> filterFindByC_LikeN(
		long classNameId, String name);

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
	public java.util.List<EntitlementDefinition> filterFindByC_LikeN(
		long classNameId, String name, int start, int end);

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
	public java.util.List<EntitlementDefinition> filterFindByC_LikeN(
		long classNameId, String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

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
	public EntitlementDefinition[] filterFindByC_LikeN_PrevAndNext(
			long entitlementDefinitionId, long classNameId, String name,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Removes all the entitlement definitions where classNameId = &#63; and name LIKE &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 */
	public void removeByC_LikeN(long classNameId, String name);

	/**
	 * Returns the number of entitlement definitions where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the number of matching entitlement definitions
	 */
	public int countByC_LikeN(long classNameId, String name);

	/**
	 * Returns the number of entitlement definitions that the user has permission to view where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the number of matching entitlement definitions that the user has permission to view
	 */
	public int filterCountByC_LikeN(long classNameId, String name);

	/**
	 * Returns all the entitlement definitions where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @return the matching entitlement definitions
	 */
	public java.util.List<EntitlementDefinition> findByC_S(
		long classNameId, int status);

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
	public java.util.List<EntitlementDefinition> findByC_S(
		long classNameId, int status, int start, int end);

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
	public java.util.List<EntitlementDefinition> findByC_S(
		long classNameId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

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
	public java.util.List<EntitlementDefinition> findByC_S(
		long classNameId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first entitlement definition in the ordered set where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition findByC_S_First(
			long classNameId, int status,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns the first entitlement definition in the ordered set where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition fetchByC_S_First(
		long classNameId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

	/**
	 * Returns the last entitlement definition in the ordered set where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition findByC_S_Last(
			long classNameId, int status,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns the last entitlement definition in the ordered set where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	public EntitlementDefinition fetchByC_S_Last(
		long classNameId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

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
	public EntitlementDefinition[] findByC_S_PrevAndNext(
			long entitlementDefinitionId, long classNameId, int status,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns all the entitlement definitions that the user has permission to view where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @return the matching entitlement definitions that the user has permission to view
	 */
	public java.util.List<EntitlementDefinition> filterFindByC_S(
		long classNameId, int status);

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
	public java.util.List<EntitlementDefinition> filterFindByC_S(
		long classNameId, int status, int start, int end);

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
	public java.util.List<EntitlementDefinition> filterFindByC_S(
		long classNameId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

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
	public EntitlementDefinition[] filterFindByC_S_PrevAndNext(
			long entitlementDefinitionId, long classNameId, int status,
			com.liferay.portal.kernel.util.OrderByComparator
				<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Removes all the entitlement definitions where classNameId = &#63; and status = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 */
	public void removeByC_S(long classNameId, int status);

	/**
	 * Returns the number of entitlement definitions where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @return the number of matching entitlement definitions
	 */
	public int countByC_S(long classNameId, int status);

	/**
	 * Returns the number of entitlement definitions that the user has permission to view where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @return the number of matching entitlement definitions that the user has permission to view
	 */
	public int filterCountByC_S(long classNameId, int status);

	/**
	 * Caches the entitlement definition in the entity cache if it is enabled.
	 *
	 * @param entitlementDefinition the entitlement definition
	 */
	public void cacheResult(EntitlementDefinition entitlementDefinition);

	/**
	 * Caches the entitlement definitions in the entity cache if it is enabled.
	 *
	 * @param entitlementDefinitions the entitlement definitions
	 */
	public void cacheResult(
		java.util.List<EntitlementDefinition> entitlementDefinitions);

	/**
	 * Creates a new entitlement definition with the primary key. Does not add the entitlement definition to the database.
	 *
	 * @param entitlementDefinitionId the primary key for the new entitlement definition
	 * @return the new entitlement definition
	 */
	public EntitlementDefinition create(long entitlementDefinitionId);

	/**
	 * Removes the entitlement definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition that was removed
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	public EntitlementDefinition remove(long entitlementDefinitionId)
		throws NoSuchEntitlementDefinitionException;

	public EntitlementDefinition updateImpl(
		EntitlementDefinition entitlementDefinition);

	/**
	 * Returns the entitlement definition with the primary key or throws a <code>NoSuchEntitlementDefinitionException</code> if it could not be found.
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	public EntitlementDefinition findByPrimaryKey(long entitlementDefinitionId)
		throws NoSuchEntitlementDefinitionException;

	/**
	 * Returns the entitlement definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition, or <code>null</code> if a entitlement definition with the primary key could not be found
	 */
	public EntitlementDefinition fetchByPrimaryKey(
		long entitlementDefinitionId);

	/**
	 * Returns all the entitlement definitions.
	 *
	 * @return the entitlement definitions
	 */
	public java.util.List<EntitlementDefinition> findAll();

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
	public java.util.List<EntitlementDefinition> findAll(int start, int end);

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
	public java.util.List<EntitlementDefinition> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator);

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
	public java.util.List<EntitlementDefinition> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntitlementDefinition>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the entitlement definitions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of entitlement definitions.
	 *
	 * @return the number of entitlement definitions
	 */
	public int countAll();

}