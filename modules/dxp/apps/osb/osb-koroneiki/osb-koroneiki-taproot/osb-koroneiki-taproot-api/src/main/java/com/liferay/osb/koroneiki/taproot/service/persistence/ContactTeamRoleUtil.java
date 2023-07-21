/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the contact team role service. This utility wraps <code>com.liferay.osb.koroneiki.taproot.service.persistence.impl.ContactTeamRolePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactTeamRolePersistence
 * @generated
 */
public class ContactTeamRoleUtil {

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
	public static void clearCache(ContactTeamRole contactTeamRole) {
		getPersistence().clearCache(contactTeamRole);
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
	public static Map<Serializable, ContactTeamRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ContactTeamRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContactTeamRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContactTeamRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ContactTeamRole update(ContactTeamRole contactTeamRole) {
		return getPersistence().update(contactTeamRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ContactTeamRole update(
		ContactTeamRole contactTeamRole, ServiceContext serviceContext) {

		return getPersistence().update(contactTeamRole, serviceContext);
	}

	/**
	 * Returns all the contact team roles where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the matching contact team roles
	 */
	public static List<ContactTeamRole> findByContactId(long contactId) {
		return getPersistence().findByContactId(contactId);
	}

	/**
	 * Returns a range of all the contact team roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of matching contact team roles
	 */
	public static List<ContactTeamRole> findByContactId(
		long contactId, int start, int end) {

		return getPersistence().findByContactId(contactId, start, end);
	}

	/**
	 * Returns an ordered range of all the contact team roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact team roles
	 */
	public static List<ContactTeamRole> findByContactId(
		long contactId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return getPersistence().findByContactId(
			contactId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact team roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact team roles
	 */
	public static List<ContactTeamRole> findByContactId(
		long contactId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByContactId(
			contactId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public static ContactTeamRole findByContactId_First(
			long contactId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactTeamRoleException {

		return getPersistence().findByContactId_First(
			contactId, orderByComparator);
	}

	/**
	 * Returns the first contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public static ContactTeamRole fetchByContactId_First(
		long contactId, OrderByComparator<ContactTeamRole> orderByComparator) {

		return getPersistence().fetchByContactId_First(
			contactId, orderByComparator);
	}

	/**
	 * Returns the last contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public static ContactTeamRole findByContactId_Last(
			long contactId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactTeamRoleException {

		return getPersistence().findByContactId_Last(
			contactId, orderByComparator);
	}

	/**
	 * Returns the last contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public static ContactTeamRole fetchByContactId_Last(
		long contactId, OrderByComparator<ContactTeamRole> orderByComparator) {

		return getPersistence().fetchByContactId_Last(
			contactId, orderByComparator);
	}

	/**
	 * Returns the contact team roles before and after the current contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactTeamRolePK the primary key of the current contact team role
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	public static ContactTeamRole[] findByContactId_PrevAndNext(
			ContactTeamRolePK contactTeamRolePK, long contactId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactTeamRoleException {

		return getPersistence().findByContactId_PrevAndNext(
			contactTeamRolePK, contactId, orderByComparator);
	}

	/**
	 * Removes all the contact team roles where contactId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 */
	public static void removeByContactId(long contactId) {
		getPersistence().removeByContactId(contactId);
	}

	/**
	 * Returns the number of contact team roles where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the number of matching contact team roles
	 */
	public static int countByContactId(long contactId) {
		return getPersistence().countByContactId(contactId);
	}

	/**
	 * Returns all the contact team roles where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @return the matching contact team roles
	 */
	public static List<ContactTeamRole> findByTeamId(long teamId) {
		return getPersistence().findByTeamId(teamId);
	}

	/**
	 * Returns a range of all the contact team roles where teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of matching contact team roles
	 */
	public static List<ContactTeamRole> findByTeamId(
		long teamId, int start, int end) {

		return getPersistence().findByTeamId(teamId, start, end);
	}

	/**
	 * Returns an ordered range of all the contact team roles where teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact team roles
	 */
	public static List<ContactTeamRole> findByTeamId(
		long teamId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return getPersistence().findByTeamId(
			teamId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact team roles where teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact team roles
	 */
	public static List<ContactTeamRole> findByTeamId(
		long teamId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByTeamId(
			teamId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public static ContactTeamRole findByTeamId_First(
			long teamId, OrderByComparator<ContactTeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactTeamRoleException {

		return getPersistence().findByTeamId_First(teamId, orderByComparator);
	}

	/**
	 * Returns the first contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public static ContactTeamRole fetchByTeamId_First(
		long teamId, OrderByComparator<ContactTeamRole> orderByComparator) {

		return getPersistence().fetchByTeamId_First(teamId, orderByComparator);
	}

	/**
	 * Returns the last contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public static ContactTeamRole findByTeamId_Last(
			long teamId, OrderByComparator<ContactTeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactTeamRoleException {

		return getPersistence().findByTeamId_Last(teamId, orderByComparator);
	}

	/**
	 * Returns the last contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public static ContactTeamRole fetchByTeamId_Last(
		long teamId, OrderByComparator<ContactTeamRole> orderByComparator) {

		return getPersistence().fetchByTeamId_Last(teamId, orderByComparator);
	}

	/**
	 * Returns the contact team roles before and after the current contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param contactTeamRolePK the primary key of the current contact team role
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	public static ContactTeamRole[] findByTeamId_PrevAndNext(
			ContactTeamRolePK contactTeamRolePK, long teamId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactTeamRoleException {

		return getPersistence().findByTeamId_PrevAndNext(
			contactTeamRolePK, teamId, orderByComparator);
	}

	/**
	 * Removes all the contact team roles where teamId = &#63; from the database.
	 *
	 * @param teamId the team ID
	 */
	public static void removeByTeamId(long teamId) {
		getPersistence().removeByTeamId(teamId);
	}

	/**
	 * Returns the number of contact team roles where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @return the number of matching contact team roles
	 */
	public static int countByTeamId(long teamId) {
		return getPersistence().countByTeamId(teamId);
	}

	/**
	 * Returns all the contact team roles where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @return the matching contact team roles
	 */
	public static List<ContactTeamRole> findByContactRoleId(
		long contactRoleId) {

		return getPersistence().findByContactRoleId(contactRoleId);
	}

	/**
	 * Returns a range of all the contact team roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of matching contact team roles
	 */
	public static List<ContactTeamRole> findByContactRoleId(
		long contactRoleId, int start, int end) {

		return getPersistence().findByContactRoleId(contactRoleId, start, end);
	}

	/**
	 * Returns an ordered range of all the contact team roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact team roles
	 */
	public static List<ContactTeamRole> findByContactRoleId(
		long contactRoleId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return getPersistence().findByContactRoleId(
			contactRoleId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact team roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact team roles
	 */
	public static List<ContactTeamRole> findByContactRoleId(
		long contactRoleId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByContactRoleId(
			contactRoleId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public static ContactTeamRole findByContactRoleId_First(
			long contactRoleId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactTeamRoleException {

		return getPersistence().findByContactRoleId_First(
			contactRoleId, orderByComparator);
	}

	/**
	 * Returns the first contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public static ContactTeamRole fetchByContactRoleId_First(
		long contactRoleId,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return getPersistence().fetchByContactRoleId_First(
			contactRoleId, orderByComparator);
	}

	/**
	 * Returns the last contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public static ContactTeamRole findByContactRoleId_Last(
			long contactRoleId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactTeamRoleException {

		return getPersistence().findByContactRoleId_Last(
			contactRoleId, orderByComparator);
	}

	/**
	 * Returns the last contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public static ContactTeamRole fetchByContactRoleId_Last(
		long contactRoleId,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return getPersistence().fetchByContactRoleId_Last(
			contactRoleId, orderByComparator);
	}

	/**
	 * Returns the contact team roles before and after the current contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactTeamRolePK the primary key of the current contact team role
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	public static ContactTeamRole[] findByContactRoleId_PrevAndNext(
			ContactTeamRolePK contactTeamRolePK, long contactRoleId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactTeamRoleException {

		return getPersistence().findByContactRoleId_PrevAndNext(
			contactTeamRolePK, contactRoleId, orderByComparator);
	}

	/**
	 * Removes all the contact team roles where contactRoleId = &#63; from the database.
	 *
	 * @param contactRoleId the contact role ID
	 */
	public static void removeByContactRoleId(long contactRoleId) {
		getPersistence().removeByContactRoleId(contactRoleId);
	}

	/**
	 * Returns the number of contact team roles where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @return the number of matching contact team roles
	 */
	public static int countByContactRoleId(long contactRoleId) {
		return getPersistence().countByContactRoleId(contactRoleId);
	}

	/**
	 * Returns all the contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @return the matching contact team roles
	 */
	public static List<ContactTeamRole> findByCI_TI(
		long contactId, long teamId) {

		return getPersistence().findByCI_TI(contactId, teamId);
	}

	/**
	 * Returns a range of all the contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of matching contact team roles
	 */
	public static List<ContactTeamRole> findByCI_TI(
		long contactId, long teamId, int start, int end) {

		return getPersistence().findByCI_TI(contactId, teamId, start, end);
	}

	/**
	 * Returns an ordered range of all the contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact team roles
	 */
	public static List<ContactTeamRole> findByCI_TI(
		long contactId, long teamId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return getPersistence().findByCI_TI(
			contactId, teamId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact team roles
	 */
	public static List<ContactTeamRole> findByCI_TI(
		long contactId, long teamId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCI_TI(
			contactId, teamId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public static ContactTeamRole findByCI_TI_First(
			long contactId, long teamId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactTeamRoleException {

		return getPersistence().findByCI_TI_First(
			contactId, teamId, orderByComparator);
	}

	/**
	 * Returns the first contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public static ContactTeamRole fetchByCI_TI_First(
		long contactId, long teamId,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return getPersistence().fetchByCI_TI_First(
			contactId, teamId, orderByComparator);
	}

	/**
	 * Returns the last contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	public static ContactTeamRole findByCI_TI_Last(
			long contactId, long teamId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactTeamRoleException {

		return getPersistence().findByCI_TI_Last(
			contactId, teamId, orderByComparator);
	}

	/**
	 * Returns the last contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	public static ContactTeamRole fetchByCI_TI_Last(
		long contactId, long teamId,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return getPersistence().fetchByCI_TI_Last(
			contactId, teamId, orderByComparator);
	}

	/**
	 * Returns the contact team roles before and after the current contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactTeamRolePK the primary key of the current contact team role
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	public static ContactTeamRole[] findByCI_TI_PrevAndNext(
			ContactTeamRolePK contactTeamRolePK, long contactId, long teamId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactTeamRoleException {

		return getPersistence().findByCI_TI_PrevAndNext(
			contactTeamRolePK, contactId, teamId, orderByComparator);
	}

	/**
	 * Removes all the contact team roles where contactId = &#63; and teamId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 */
	public static void removeByCI_TI(long contactId, long teamId) {
		getPersistence().removeByCI_TI(contactId, teamId);
	}

	/**
	 * Returns the number of contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @return the number of matching contact team roles
	 */
	public static int countByCI_TI(long contactId, long teamId) {
		return getPersistence().countByCI_TI(contactId, teamId);
	}

	/**
	 * Caches the contact team role in the entity cache if it is enabled.
	 *
	 * @param contactTeamRole the contact team role
	 */
	public static void cacheResult(ContactTeamRole contactTeamRole) {
		getPersistence().cacheResult(contactTeamRole);
	}

	/**
	 * Caches the contact team roles in the entity cache if it is enabled.
	 *
	 * @param contactTeamRoles the contact team roles
	 */
	public static void cacheResult(List<ContactTeamRole> contactTeamRoles) {
		getPersistence().cacheResult(contactTeamRoles);
	}

	/**
	 * Creates a new contact team role with the primary key. Does not add the contact team role to the database.
	 *
	 * @param contactTeamRolePK the primary key for the new contact team role
	 * @return the new contact team role
	 */
	public static ContactTeamRole create(ContactTeamRolePK contactTeamRolePK) {
		return getPersistence().create(contactTeamRolePK);
	}

	/**
	 * Removes the contact team role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactTeamRolePK the primary key of the contact team role
	 * @return the contact team role that was removed
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	public static ContactTeamRole remove(ContactTeamRolePK contactTeamRolePK)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactTeamRoleException {

		return getPersistence().remove(contactTeamRolePK);
	}

	public static ContactTeamRole updateImpl(ContactTeamRole contactTeamRole) {
		return getPersistence().updateImpl(contactTeamRole);
	}

	/**
	 * Returns the contact team role with the primary key or throws a <code>NoSuchContactTeamRoleException</code> if it could not be found.
	 *
	 * @param contactTeamRolePK the primary key of the contact team role
	 * @return the contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	public static ContactTeamRole findByPrimaryKey(
			ContactTeamRolePK contactTeamRolePK)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchContactTeamRoleException {

		return getPersistence().findByPrimaryKey(contactTeamRolePK);
	}

	/**
	 * Returns the contact team role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactTeamRolePK the primary key of the contact team role
	 * @return the contact team role, or <code>null</code> if a contact team role with the primary key could not be found
	 */
	public static ContactTeamRole fetchByPrimaryKey(
		ContactTeamRolePK contactTeamRolePK) {

		return getPersistence().fetchByPrimaryKey(contactTeamRolePK);
	}

	/**
	 * Returns all the contact team roles.
	 *
	 * @return the contact team roles
	 */
	public static List<ContactTeamRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the contact team roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of contact team roles
	 */
	public static List<ContactTeamRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the contact team roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contact team roles
	 */
	public static List<ContactTeamRole> findAll(
		int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the contact team roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of contact team roles
	 */
	public static List<ContactTeamRole> findAll(
		int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the contact team roles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of contact team roles.
	 *
	 * @return the number of contact team roles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static ContactTeamRolePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(ContactTeamRolePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile ContactTeamRolePersistence _persistence;

}