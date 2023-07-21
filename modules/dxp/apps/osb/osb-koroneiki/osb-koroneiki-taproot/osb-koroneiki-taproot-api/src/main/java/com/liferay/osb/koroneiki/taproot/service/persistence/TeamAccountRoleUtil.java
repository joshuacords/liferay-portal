/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the team account role service. This utility wraps <code>com.liferay.osb.koroneiki.taproot.service.persistence.impl.TeamAccountRolePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamAccountRolePersistence
 * @generated
 */
public class TeamAccountRoleUtil {

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
	public static void clearCache(TeamAccountRole teamAccountRole) {
		getPersistence().clearCache(teamAccountRole);
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
	public static Map<Serializable, TeamAccountRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TeamAccountRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TeamAccountRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TeamAccountRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TeamAccountRole update(TeamAccountRole teamAccountRole) {
		return getPersistence().update(teamAccountRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TeamAccountRole update(
		TeamAccountRole teamAccountRole, ServiceContext serviceContext) {

		return getPersistence().update(teamAccountRole, serviceContext);
	}

	/**
	 * Returns all the team account roles where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @return the matching team account roles
	 */
	public static List<TeamAccountRole> findByTeamId(long teamId) {
		return getPersistence().findByTeamId(teamId);
	}

	/**
	 * Returns a range of all the team account roles where teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @return the range of matching team account roles
	 */
	public static List<TeamAccountRole> findByTeamId(
		long teamId, int start, int end) {

		return getPersistence().findByTeamId(teamId, start, end);
	}

	/**
	 * Returns an ordered range of all the team account roles where teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching team account roles
	 */
	public static List<TeamAccountRole> findByTeamId(
		long teamId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		return getPersistence().findByTeamId(
			teamId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the team account roles where teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching team account roles
	 */
	public static List<TeamAccountRole> findByTeamId(
		long teamId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByTeamId(
			teamId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first team account role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public static TeamAccountRole findByTeamId_First(
			long teamId, OrderByComparator<TeamAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamAccountRoleException {

		return getPersistence().findByTeamId_First(teamId, orderByComparator);
	}

	/**
	 * Returns the first team account role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public static TeamAccountRole fetchByTeamId_First(
		long teamId, OrderByComparator<TeamAccountRole> orderByComparator) {

		return getPersistence().fetchByTeamId_First(teamId, orderByComparator);
	}

	/**
	 * Returns the last team account role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public static TeamAccountRole findByTeamId_Last(
			long teamId, OrderByComparator<TeamAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamAccountRoleException {

		return getPersistence().findByTeamId_Last(teamId, orderByComparator);
	}

	/**
	 * Returns the last team account role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public static TeamAccountRole fetchByTeamId_Last(
		long teamId, OrderByComparator<TeamAccountRole> orderByComparator) {

		return getPersistence().fetchByTeamId_Last(teamId, orderByComparator);
	}

	/**
	 * Returns the team account roles before and after the current team account role in the ordered set where teamId = &#63;.
	 *
	 * @param teamAccountRolePK the primary key of the current team account role
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team account role
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	public static TeamAccountRole[] findByTeamId_PrevAndNext(
			TeamAccountRolePK teamAccountRolePK, long teamId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamAccountRoleException {

		return getPersistence().findByTeamId_PrevAndNext(
			teamAccountRolePK, teamId, orderByComparator);
	}

	/**
	 * Removes all the team account roles where teamId = &#63; from the database.
	 *
	 * @param teamId the team ID
	 */
	public static void removeByTeamId(long teamId) {
		getPersistence().removeByTeamId(teamId);
	}

	/**
	 * Returns the number of team account roles where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @return the number of matching team account roles
	 */
	public static int countByTeamId(long teamId) {
		return getPersistence().countByTeamId(teamId);
	}

	/**
	 * Returns all the team account roles where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching team account roles
	 */
	public static List<TeamAccountRole> findByAccountId(long accountId) {
		return getPersistence().findByAccountId(accountId);
	}

	/**
	 * Returns a range of all the team account roles where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @return the range of matching team account roles
	 */
	public static List<TeamAccountRole> findByAccountId(
		long accountId, int start, int end) {

		return getPersistence().findByAccountId(accountId, start, end);
	}

	/**
	 * Returns an ordered range of all the team account roles where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching team account roles
	 */
	public static List<TeamAccountRole> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		return getPersistence().findByAccountId(
			accountId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the team account roles where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching team account roles
	 */
	public static List<TeamAccountRole> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAccountId(
			accountId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first team account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public static TeamAccountRole findByAccountId_First(
			long accountId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamAccountRoleException {

		return getPersistence().findByAccountId_First(
			accountId, orderByComparator);
	}

	/**
	 * Returns the first team account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public static TeamAccountRole fetchByAccountId_First(
		long accountId, OrderByComparator<TeamAccountRole> orderByComparator) {

		return getPersistence().fetchByAccountId_First(
			accountId, orderByComparator);
	}

	/**
	 * Returns the last team account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public static TeamAccountRole findByAccountId_Last(
			long accountId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamAccountRoleException {

		return getPersistence().findByAccountId_Last(
			accountId, orderByComparator);
	}

	/**
	 * Returns the last team account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public static TeamAccountRole fetchByAccountId_Last(
		long accountId, OrderByComparator<TeamAccountRole> orderByComparator) {

		return getPersistence().fetchByAccountId_Last(
			accountId, orderByComparator);
	}

	/**
	 * Returns the team account roles before and after the current team account role in the ordered set where accountId = &#63;.
	 *
	 * @param teamAccountRolePK the primary key of the current team account role
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team account role
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	public static TeamAccountRole[] findByAccountId_PrevAndNext(
			TeamAccountRolePK teamAccountRolePK, long accountId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamAccountRoleException {

		return getPersistence().findByAccountId_PrevAndNext(
			teamAccountRolePK, accountId, orderByComparator);
	}

	/**
	 * Removes all the team account roles where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	public static void removeByAccountId(long accountId) {
		getPersistence().removeByAccountId(accountId);
	}

	/**
	 * Returns the number of team account roles where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching team account roles
	 */
	public static int countByAccountId(long accountId) {
		return getPersistence().countByAccountId(accountId);
	}

	/**
	 * Returns all the team account roles where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @return the matching team account roles
	 */
	public static List<TeamAccountRole> findByTeamRoleId(long teamRoleId) {
		return getPersistence().findByTeamRoleId(teamRoleId);
	}

	/**
	 * Returns a range of all the team account roles where teamRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamRoleId the team role ID
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @return the range of matching team account roles
	 */
	public static List<TeamAccountRole> findByTeamRoleId(
		long teamRoleId, int start, int end) {

		return getPersistence().findByTeamRoleId(teamRoleId, start, end);
	}

	/**
	 * Returns an ordered range of all the team account roles where teamRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamRoleId the team role ID
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching team account roles
	 */
	public static List<TeamAccountRole> findByTeamRoleId(
		long teamRoleId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		return getPersistence().findByTeamRoleId(
			teamRoleId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the team account roles where teamRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamRoleId the team role ID
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching team account roles
	 */
	public static List<TeamAccountRole> findByTeamRoleId(
		long teamRoleId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByTeamRoleId(
			teamRoleId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first team account role in the ordered set where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public static TeamAccountRole findByTeamRoleId_First(
			long teamRoleId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamAccountRoleException {

		return getPersistence().findByTeamRoleId_First(
			teamRoleId, orderByComparator);
	}

	/**
	 * Returns the first team account role in the ordered set where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public static TeamAccountRole fetchByTeamRoleId_First(
		long teamRoleId, OrderByComparator<TeamAccountRole> orderByComparator) {

		return getPersistence().fetchByTeamRoleId_First(
			teamRoleId, orderByComparator);
	}

	/**
	 * Returns the last team account role in the ordered set where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public static TeamAccountRole findByTeamRoleId_Last(
			long teamRoleId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamAccountRoleException {

		return getPersistence().findByTeamRoleId_Last(
			teamRoleId, orderByComparator);
	}

	/**
	 * Returns the last team account role in the ordered set where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public static TeamAccountRole fetchByTeamRoleId_Last(
		long teamRoleId, OrderByComparator<TeamAccountRole> orderByComparator) {

		return getPersistence().fetchByTeamRoleId_Last(
			teamRoleId, orderByComparator);
	}

	/**
	 * Returns the team account roles before and after the current team account role in the ordered set where teamRoleId = &#63;.
	 *
	 * @param teamAccountRolePK the primary key of the current team account role
	 * @param teamRoleId the team role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team account role
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	public static TeamAccountRole[] findByTeamRoleId_PrevAndNext(
			TeamAccountRolePK teamAccountRolePK, long teamRoleId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamAccountRoleException {

		return getPersistence().findByTeamRoleId_PrevAndNext(
			teamAccountRolePK, teamRoleId, orderByComparator);
	}

	/**
	 * Removes all the team account roles where teamRoleId = &#63; from the database.
	 *
	 * @param teamRoleId the team role ID
	 */
	public static void removeByTeamRoleId(long teamRoleId) {
		getPersistence().removeByTeamRoleId(teamRoleId);
	}

	/**
	 * Returns the number of team account roles where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @return the number of matching team account roles
	 */
	public static int countByTeamRoleId(long teamRoleId) {
		return getPersistence().countByTeamRoleId(teamRoleId);
	}

	/**
	 * Returns all the team account roles where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @return the matching team account roles
	 */
	public static List<TeamAccountRole> findByTI_AI(
		long teamId, long accountId) {

		return getPersistence().findByTI_AI(teamId, accountId);
	}

	/**
	 * Returns a range of all the team account roles where teamId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @return the range of matching team account roles
	 */
	public static List<TeamAccountRole> findByTI_AI(
		long teamId, long accountId, int start, int end) {

		return getPersistence().findByTI_AI(teamId, accountId, start, end);
	}

	/**
	 * Returns an ordered range of all the team account roles where teamId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching team account roles
	 */
	public static List<TeamAccountRole> findByTI_AI(
		long teamId, long accountId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		return getPersistence().findByTI_AI(
			teamId, accountId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the team account roles where teamId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching team account roles
	 */
	public static List<TeamAccountRole> findByTI_AI(
		long teamId, long accountId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByTI_AI(
			teamId, accountId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first team account role in the ordered set where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public static TeamAccountRole findByTI_AI_First(
			long teamId, long accountId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamAccountRoleException {

		return getPersistence().findByTI_AI_First(
			teamId, accountId, orderByComparator);
	}

	/**
	 * Returns the first team account role in the ordered set where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public static TeamAccountRole fetchByTI_AI_First(
		long teamId, long accountId,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		return getPersistence().fetchByTI_AI_First(
			teamId, accountId, orderByComparator);
	}

	/**
	 * Returns the last team account role in the ordered set where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public static TeamAccountRole findByTI_AI_Last(
			long teamId, long accountId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamAccountRoleException {

		return getPersistence().findByTI_AI_Last(
			teamId, accountId, orderByComparator);
	}

	/**
	 * Returns the last team account role in the ordered set where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public static TeamAccountRole fetchByTI_AI_Last(
		long teamId, long accountId,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		return getPersistence().fetchByTI_AI_Last(
			teamId, accountId, orderByComparator);
	}

	/**
	 * Returns the team account roles before and after the current team account role in the ordered set where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamAccountRolePK the primary key of the current team account role
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team account role
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	public static TeamAccountRole[] findByTI_AI_PrevAndNext(
			TeamAccountRolePK teamAccountRolePK, long teamId, long accountId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamAccountRoleException {

		return getPersistence().findByTI_AI_PrevAndNext(
			teamAccountRolePK, teamId, accountId, orderByComparator);
	}

	/**
	 * Removes all the team account roles where teamId = &#63; and accountId = &#63; from the database.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 */
	public static void removeByTI_AI(long teamId, long accountId) {
		getPersistence().removeByTI_AI(teamId, accountId);
	}

	/**
	 * Returns the number of team account roles where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @return the number of matching team account roles
	 */
	public static int countByTI_AI(long teamId, long accountId) {
		return getPersistence().countByTI_AI(teamId, accountId);
	}

	/**
	 * Caches the team account role in the entity cache if it is enabled.
	 *
	 * @param teamAccountRole the team account role
	 */
	public static void cacheResult(TeamAccountRole teamAccountRole) {
		getPersistence().cacheResult(teamAccountRole);
	}

	/**
	 * Caches the team account roles in the entity cache if it is enabled.
	 *
	 * @param teamAccountRoles the team account roles
	 */
	public static void cacheResult(List<TeamAccountRole> teamAccountRoles) {
		getPersistence().cacheResult(teamAccountRoles);
	}

	/**
	 * Creates a new team account role with the primary key. Does not add the team account role to the database.
	 *
	 * @param teamAccountRolePK the primary key for the new team account role
	 * @return the new team account role
	 */
	public static TeamAccountRole create(TeamAccountRolePK teamAccountRolePK) {
		return getPersistence().create(teamAccountRolePK);
	}

	/**
	 * Removes the team account role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param teamAccountRolePK the primary key of the team account role
	 * @return the team account role that was removed
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	public static TeamAccountRole remove(TeamAccountRolePK teamAccountRolePK)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamAccountRoleException {

		return getPersistence().remove(teamAccountRolePK);
	}

	public static TeamAccountRole updateImpl(TeamAccountRole teamAccountRole) {
		return getPersistence().updateImpl(teamAccountRole);
	}

	/**
	 * Returns the team account role with the primary key or throws a <code>NoSuchTeamAccountRoleException</code> if it could not be found.
	 *
	 * @param teamAccountRolePK the primary key of the team account role
	 * @return the team account role
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	public static TeamAccountRole findByPrimaryKey(
			TeamAccountRolePK teamAccountRolePK)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamAccountRoleException {

		return getPersistence().findByPrimaryKey(teamAccountRolePK);
	}

	/**
	 * Returns the team account role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param teamAccountRolePK the primary key of the team account role
	 * @return the team account role, or <code>null</code> if a team account role with the primary key could not be found
	 */
	public static TeamAccountRole fetchByPrimaryKey(
		TeamAccountRolePK teamAccountRolePK) {

		return getPersistence().fetchByPrimaryKey(teamAccountRolePK);
	}

	/**
	 * Returns all the team account roles.
	 *
	 * @return the team account roles
	 */
	public static List<TeamAccountRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the team account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @return the range of team account roles
	 */
	public static List<TeamAccountRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the team account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of team account roles
	 */
	public static List<TeamAccountRole> findAll(
		int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the team account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of team account roles
	 * @param end the upper bound of the range of team account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of team account roles
	 */
	public static List<TeamAccountRole> findAll(
		int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the team account roles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of team account roles.
	 *
	 * @return the number of team account roles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static TeamAccountRolePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(TeamAccountRolePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile TeamAccountRolePersistence _persistence;

}