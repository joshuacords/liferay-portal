/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the contact account role service. This utility wraps <code>com.liferay.osb.koroneiki.taproot.service.persistence.impl.ContactAccountRolePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactAccountRolePersistence
 * @generated
 */
public class ContactAccountRoleUtil {

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
	public static void clearCache(ContactAccountRole contactAccountRole) {
		getPersistence().clearCache(contactAccountRole);
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
	public static Map<Serializable, ContactAccountRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ContactAccountRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContactAccountRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContactAccountRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ContactAccountRole update(
		ContactAccountRole contactAccountRole) {

		return getPersistence().update(contactAccountRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ContactAccountRole update(
		ContactAccountRole contactAccountRole, ServiceContext serviceContext) {

		return getPersistence().update(contactAccountRole, serviceContext);
	}

	/**
	 * Returns all the contact account roles where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the matching contact account roles
	 */
	public static List<ContactAccountRole> findByContactId(long contactId) {
		return getPersistence().findByContactId(contactId);
	}

	/**
	 * Returns a range of all the contact account roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByContactId(
		long contactId, int start, int end) {

		return getPersistence().findByContactId(contactId, start, end);
	}

	/**
	 * Returns an ordered range of all the contact account roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByContactId(
		long contactId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().findByContactId(
			contactId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact account roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByContactId(
		long contactId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByContactId(
			contactId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public static ContactAccountRole findByContactId_First(
			long contactId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByContactId_First(
			contactId, orderByComparator);
	}

	/**
	 * Returns the first contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public static ContactAccountRole fetchByContactId_First(
		long contactId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().fetchByContactId_First(
			contactId, orderByComparator);
	}

	/**
	 * Returns the last contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public static ContactAccountRole findByContactId_Last(
			long contactId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByContactId_Last(
			contactId, orderByComparator);
	}

	/**
	 * Returns the last contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public static ContactAccountRole fetchByContactId_Last(
		long contactId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().fetchByContactId_Last(
			contactId, orderByComparator);
	}

	/**
	 * Returns the contact account roles before and after the current contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactAccountRolePK the primary key of the current contact account role
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public static ContactAccountRole[] findByContactId_PrevAndNext(
			ContactAccountRolePK contactAccountRolePK, long contactId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByContactId_PrevAndNext(
			contactAccountRolePK, contactId, orderByComparator);
	}

	/**
	 * Removes all the contact account roles where contactId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 */
	public static void removeByContactId(long contactId) {
		getPersistence().removeByContactId(contactId);
	}

	/**
	 * Returns the number of contact account roles where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the number of matching contact account roles
	 */
	public static int countByContactId(long contactId) {
		return getPersistence().countByContactId(contactId);
	}

	/**
	 * Returns all the contact account roles where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching contact account roles
	 */
	public static List<ContactAccountRole> findByAccountId(long accountId) {
		return getPersistence().findByAccountId(accountId);
	}

	/**
	 * Returns a range of all the contact account roles where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByAccountId(
		long accountId, int start, int end) {

		return getPersistence().findByAccountId(accountId, start, end);
	}

	/**
	 * Returns an ordered range of all the contact account roles where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().findByAccountId(
			accountId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact account roles where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAccountId(
			accountId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public static ContactAccountRole findByAccountId_First(
			long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByAccountId_First(
			accountId, orderByComparator);
	}

	/**
	 * Returns the first contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public static ContactAccountRole fetchByAccountId_First(
		long accountId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().fetchByAccountId_First(
			accountId, orderByComparator);
	}

	/**
	 * Returns the last contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public static ContactAccountRole findByAccountId_Last(
			long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByAccountId_Last(
			accountId, orderByComparator);
	}

	/**
	 * Returns the last contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public static ContactAccountRole fetchByAccountId_Last(
		long accountId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().fetchByAccountId_Last(
			accountId, orderByComparator);
	}

	/**
	 * Returns the contact account roles before and after the current contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param contactAccountRolePK the primary key of the current contact account role
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public static ContactAccountRole[] findByAccountId_PrevAndNext(
			ContactAccountRolePK contactAccountRolePK, long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByAccountId_PrevAndNext(
			contactAccountRolePK, accountId, orderByComparator);
	}

	/**
	 * Removes all the contact account roles where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	public static void removeByAccountId(long accountId) {
		getPersistence().removeByAccountId(accountId);
	}

	/**
	 * Returns the number of contact account roles where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching contact account roles
	 */
	public static int countByAccountId(long accountId) {
		return getPersistence().countByAccountId(accountId);
	}

	/**
	 * Returns all the contact account roles where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @return the matching contact account roles
	 */
	public static List<ContactAccountRole> findByContactRoleId(
		long contactRoleId) {

		return getPersistence().findByContactRoleId(contactRoleId);
	}

	/**
	 * Returns a range of all the contact account roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByContactRoleId(
		long contactRoleId, int start, int end) {

		return getPersistence().findByContactRoleId(contactRoleId, start, end);
	}

	/**
	 * Returns an ordered range of all the contact account roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByContactRoleId(
		long contactRoleId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().findByContactRoleId(
			contactRoleId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact account roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByContactRoleId(
		long contactRoleId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByContactRoleId(
			contactRoleId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public static ContactAccountRole findByContactRoleId_First(
			long contactRoleId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByContactRoleId_First(
			contactRoleId, orderByComparator);
	}

	/**
	 * Returns the first contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public static ContactAccountRole fetchByContactRoleId_First(
		long contactRoleId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().fetchByContactRoleId_First(
			contactRoleId, orderByComparator);
	}

	/**
	 * Returns the last contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public static ContactAccountRole findByContactRoleId_Last(
			long contactRoleId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByContactRoleId_Last(
			contactRoleId, orderByComparator);
	}

	/**
	 * Returns the last contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public static ContactAccountRole fetchByContactRoleId_Last(
		long contactRoleId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().fetchByContactRoleId_Last(
			contactRoleId, orderByComparator);
	}

	/**
	 * Returns the contact account roles before and after the current contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactAccountRolePK the primary key of the current contact account role
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public static ContactAccountRole[] findByContactRoleId_PrevAndNext(
			ContactAccountRolePK contactAccountRolePK, long contactRoleId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByContactRoleId_PrevAndNext(
			contactAccountRolePK, contactRoleId, orderByComparator);
	}

	/**
	 * Removes all the contact account roles where contactRoleId = &#63; from the database.
	 *
	 * @param contactRoleId the contact role ID
	 */
	public static void removeByContactRoleId(long contactRoleId) {
		getPersistence().removeByContactRoleId(contactRoleId);
	}

	/**
	 * Returns the number of contact account roles where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @return the number of matching contact account roles
	 */
	public static int countByContactRoleId(long contactRoleId) {
		return getPersistence().countByContactRoleId(contactRoleId);
	}

	/**
	 * Returns all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @return the matching contact account roles
	 */
	public static List<ContactAccountRole> findByCI_AI(
		long contactId, long accountId) {

		return getPersistence().findByCI_AI(contactId, accountId);
	}

	/**
	 * Returns a range of all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByCI_AI(
		long contactId, long accountId, int start, int end) {

		return getPersistence().findByCI_AI(contactId, accountId, start, end);
	}

	/**
	 * Returns an ordered range of all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByCI_AI(
		long contactId, long accountId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().findByCI_AI(
			contactId, accountId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact account roles
	 */
	public static List<ContactAccountRole> findByCI_AI(
		long contactId, long accountId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCI_AI(
			contactId, accountId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public static ContactAccountRole findByCI_AI_First(
			long contactId, long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByCI_AI_First(
			contactId, accountId, orderByComparator);
	}

	/**
	 * Returns the first contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public static ContactAccountRole fetchByCI_AI_First(
		long contactId, long accountId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().fetchByCI_AI_First(
			contactId, accountId, orderByComparator);
	}

	/**
	 * Returns the last contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	public static ContactAccountRole findByCI_AI_Last(
			long contactId, long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByCI_AI_Last(
			contactId, accountId, orderByComparator);
	}

	/**
	 * Returns the last contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	public static ContactAccountRole fetchByCI_AI_Last(
		long contactId, long accountId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().fetchByCI_AI_Last(
			contactId, accountId, orderByComparator);
	}

	/**
	 * Returns the contact account roles before and after the current contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactAccountRolePK the primary key of the current contact account role
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public static ContactAccountRole[] findByCI_AI_PrevAndNext(
			ContactAccountRolePK contactAccountRolePK, long contactId,
			long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByCI_AI_PrevAndNext(
			contactAccountRolePK, contactId, accountId, orderByComparator);
	}

	/**
	 * Removes all the contact account roles where contactId = &#63; and accountId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 */
	public static void removeByCI_AI(long contactId, long accountId) {
		getPersistence().removeByCI_AI(contactId, accountId);
	}

	/**
	 * Returns the number of contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @return the number of matching contact account roles
	 */
	public static int countByCI_AI(long contactId, long accountId) {
		return getPersistence().countByCI_AI(contactId, accountId);
	}

	/**
	 * Caches the contact account role in the entity cache if it is enabled.
	 *
	 * @param contactAccountRole the contact account role
	 */
	public static void cacheResult(ContactAccountRole contactAccountRole) {
		getPersistence().cacheResult(contactAccountRole);
	}

	/**
	 * Caches the contact account roles in the entity cache if it is enabled.
	 *
	 * @param contactAccountRoles the contact account roles
	 */
	public static void cacheResult(
		List<ContactAccountRole> contactAccountRoles) {

		getPersistence().cacheResult(contactAccountRoles);
	}

	/**
	 * Creates a new contact account role with the primary key. Does not add the contact account role to the database.
	 *
	 * @param contactAccountRolePK the primary key for the new contact account role
	 * @return the new contact account role
	 */
	public static ContactAccountRole create(
		ContactAccountRolePK contactAccountRolePK) {

		return getPersistence().create(contactAccountRolePK);
	}

	/**
	 * Removes the contact account role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactAccountRolePK the primary key of the contact account role
	 * @return the contact account role that was removed
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public static ContactAccountRole remove(
			ContactAccountRolePK contactAccountRolePK)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().remove(contactAccountRolePK);
	}

	public static ContactAccountRole updateImpl(
		ContactAccountRole contactAccountRole) {

		return getPersistence().updateImpl(contactAccountRole);
	}

	/**
	 * Returns the contact account role with the primary key or throws a <code>NoSuchContactAccountRoleException</code> if it could not be found.
	 *
	 * @param contactAccountRolePK the primary key of the contact account role
	 * @return the contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	public static ContactAccountRole findByPrimaryKey(
			ContactAccountRolePK contactAccountRolePK)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactAccountRoleException {

		return getPersistence().findByPrimaryKey(contactAccountRolePK);
	}

	/**
	 * Returns the contact account role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactAccountRolePK the primary key of the contact account role
	 * @return the contact account role, or <code>null</code> if a contact account role with the primary key could not be found
	 */
	public static ContactAccountRole fetchByPrimaryKey(
		ContactAccountRolePK contactAccountRolePK) {

		return getPersistence().fetchByPrimaryKey(contactAccountRolePK);
	}

	/**
	 * Returns all the contact account roles.
	 *
	 * @return the contact account roles
	 */
	public static List<ContactAccountRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the contact account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of contact account roles
	 */
	public static List<ContactAccountRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the contact account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contact account roles
	 */
	public static List<ContactAccountRole> findAll(
		int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of contact account roles
	 */
	public static List<ContactAccountRole> findAll(
		int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the contact account roles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of contact account roles.
	 *
	 * @return the number of contact account roles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static ContactAccountRolePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		ContactAccountRolePersistence persistence) {

		_persistence = persistence;
	}

	private static volatile ContactAccountRolePersistence _persistence;

}