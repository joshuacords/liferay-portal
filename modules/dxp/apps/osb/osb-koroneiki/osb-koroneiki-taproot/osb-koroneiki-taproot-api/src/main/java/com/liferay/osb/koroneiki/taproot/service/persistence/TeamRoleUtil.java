/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the team role service. This utility wraps <code>com.liferay.osb.koroneiki.taproot.service.persistence.impl.TeamRolePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamRolePersistence
 * @generated
 */
public class TeamRoleUtil {

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
	public static void clearCache(TeamRole teamRole) {
		getPersistence().clearCache(teamRole);
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
	public static Map<Serializable, TeamRole> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TeamRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TeamRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TeamRole> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TeamRole> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TeamRole update(TeamRole teamRole) {
		return getPersistence().update(teamRole);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TeamRole update(
		TeamRole teamRole, ServiceContext serviceContext) {

		return getPersistence().update(teamRole, serviceContext);
	}

	/**
	 * Returns all the team roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching team roles
	 */
	public static List<TeamRole> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the team roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @return the range of matching team roles
	 */
	public static List<TeamRole> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the team roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching team roles
	 */
	public static List<TeamRole> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<TeamRole> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the team roles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching team roles
	 */
	public static List<TeamRole> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<TeamRole> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first team role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public static TeamRole findByUuid_First(
			String uuid, OrderByComparator<TeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first team role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public static TeamRole fetchByUuid_First(
		String uuid, OrderByComparator<TeamRole> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last team role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public static TeamRole findByUuid_Last(
			String uuid, OrderByComparator<TeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last team role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public static TeamRole fetchByUuid_Last(
		String uuid, OrderByComparator<TeamRole> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the team roles before and after the current team role in the ordered set where uuid = &#63;.
	 *
	 * @param teamRoleId the primary key of the current team role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team role
	 * @throws NoSuchTeamRoleException if a team role with the primary key could not be found
	 */
	public static TeamRole[] findByUuid_PrevAndNext(
			long teamRoleId, String uuid,
			OrderByComparator<TeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().findByUuid_PrevAndNext(
			teamRoleId, uuid, orderByComparator);
	}

	/**
	 * Returns all the team roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching team roles that the user has permission to view
	 */
	public static List<TeamRole> filterFindByUuid(String uuid) {
		return getPersistence().filterFindByUuid(uuid);
	}

	/**
	 * Returns a range of all the team roles that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @return the range of matching team roles that the user has permission to view
	 */
	public static List<TeamRole> filterFindByUuid(
		String uuid, int start, int end) {

		return getPersistence().filterFindByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the team roles that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching team roles that the user has permission to view
	 */
	public static List<TeamRole> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<TeamRole> orderByComparator) {

		return getPersistence().filterFindByUuid(
			uuid, start, end, orderByComparator);
	}

	/**
	 * Returns the team roles before and after the current team role in the ordered set of team roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param teamRoleId the primary key of the current team role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team role
	 * @throws NoSuchTeamRoleException if a team role with the primary key could not be found
	 */
	public static TeamRole[] filterFindByUuid_PrevAndNext(
			long teamRoleId, String uuid,
			OrderByComparator<TeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().filterFindByUuid_PrevAndNext(
			teamRoleId, uuid, orderByComparator);
	}

	/**
	 * Removes all the team roles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of team roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching team roles
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the number of team roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching team roles that the user has permission to view
	 */
	public static int filterCountByUuid(String uuid) {
		return getPersistence().filterCountByUuid(uuid);
	}

	/**
	 * Returns all the team roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching team roles
	 */
	public static List<TeamRole> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the team roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @return the range of matching team roles
	 */
	public static List<TeamRole> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the team roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching team roles
	 */
	public static List<TeamRole> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<TeamRole> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the team roles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching team roles
	 */
	public static List<TeamRole> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<TeamRole> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first team role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public static TeamRole findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<TeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first team role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public static TeamRole fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<TeamRole> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last team role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public static TeamRole findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<TeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last team role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public static TeamRole fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<TeamRole> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the team roles before and after the current team role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param teamRoleId the primary key of the current team role
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team role
	 * @throws NoSuchTeamRoleException if a team role with the primary key could not be found
	 */
	public static TeamRole[] findByUuid_C_PrevAndNext(
			long teamRoleId, String uuid, long companyId,
			OrderByComparator<TeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().findByUuid_C_PrevAndNext(
			teamRoleId, uuid, companyId, orderByComparator);
	}

	/**
	 * Returns all the team roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching team roles that the user has permission to view
	 */
	public static List<TeamRole> filterFindByUuid_C(
		String uuid, long companyId) {

		return getPersistence().filterFindByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the team roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @return the range of matching team roles that the user has permission to view
	 */
	public static List<TeamRole> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().filterFindByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the team roles that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching team roles that the user has permission to view
	 */
	public static List<TeamRole> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<TeamRole> orderByComparator) {

		return getPersistence().filterFindByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the team roles before and after the current team role in the ordered set of team roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param teamRoleId the primary key of the current team role
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team role
	 * @throws NoSuchTeamRoleException if a team role with the primary key could not be found
	 */
	public static TeamRole[] filterFindByUuid_C_PrevAndNext(
			long teamRoleId, String uuid, long companyId,
			OrderByComparator<TeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().filterFindByUuid_C_PrevAndNext(
			teamRoleId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the team roles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of team roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching team roles
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of team roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching team roles that the user has permission to view
	 */
	public static int filterCountByUuid_C(String uuid, long companyId) {
		return getPersistence().filterCountByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the team role where teamRoleKey = &#63; or throws a <code>NoSuchTeamRoleException</code> if it could not be found.
	 *
	 * @param teamRoleKey the team role key
	 * @return the matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public static TeamRole findByTeamRoleKey(String teamRoleKey)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().findByTeamRoleKey(teamRoleKey);
	}

	/**
	 * Returns the team role where teamRoleKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param teamRoleKey the team role key
	 * @return the matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public static TeamRole fetchByTeamRoleKey(String teamRoleKey) {
		return getPersistence().fetchByTeamRoleKey(teamRoleKey);
	}

	/**
	 * Returns the team role where teamRoleKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param teamRoleKey the team role key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public static TeamRole fetchByTeamRoleKey(
		String teamRoleKey, boolean useFinderCache) {

		return getPersistence().fetchByTeamRoleKey(teamRoleKey, useFinderCache);
	}

	/**
	 * Removes the team role where teamRoleKey = &#63; from the database.
	 *
	 * @param teamRoleKey the team role key
	 * @return the team role that was removed
	 */
	public static TeamRole removeByTeamRoleKey(String teamRoleKey)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().removeByTeamRoleKey(teamRoleKey);
	}

	/**
	 * Returns the number of team roles where teamRoleKey = &#63;.
	 *
	 * @param teamRoleKey the team role key
	 * @return the number of matching team roles
	 */
	public static int countByTeamRoleKey(String teamRoleKey) {
		return getPersistence().countByTeamRoleKey(teamRoleKey);
	}

	/**
	 * Returns all the team roles where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching team roles
	 */
	public static List<TeamRole> findByType(String type) {
		return getPersistence().findByType(type);
	}

	/**
	 * Returns a range of all the team roles where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @return the range of matching team roles
	 */
	public static List<TeamRole> findByType(String type, int start, int end) {
		return getPersistence().findByType(type, start, end);
	}

	/**
	 * Returns an ordered range of all the team roles where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching team roles
	 */
	public static List<TeamRole> findByType(
		String type, int start, int end,
		OrderByComparator<TeamRole> orderByComparator) {

		return getPersistence().findByType(type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the team roles where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching team roles
	 */
	public static List<TeamRole> findByType(
		String type, int start, int end,
		OrderByComparator<TeamRole> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByType(
			type, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first team role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public static TeamRole findByType_First(
			String type, OrderByComparator<TeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().findByType_First(type, orderByComparator);
	}

	/**
	 * Returns the first team role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public static TeamRole fetchByType_First(
		String type, OrderByComparator<TeamRole> orderByComparator) {

		return getPersistence().fetchByType_First(type, orderByComparator);
	}

	/**
	 * Returns the last team role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public static TeamRole findByType_Last(
			String type, OrderByComparator<TeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().findByType_Last(type, orderByComparator);
	}

	/**
	 * Returns the last team role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public static TeamRole fetchByType_Last(
		String type, OrderByComparator<TeamRole> orderByComparator) {

		return getPersistence().fetchByType_Last(type, orderByComparator);
	}

	/**
	 * Returns the team roles before and after the current team role in the ordered set where type = &#63;.
	 *
	 * @param teamRoleId the primary key of the current team role
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team role
	 * @throws NoSuchTeamRoleException if a team role with the primary key could not be found
	 */
	public static TeamRole[] findByType_PrevAndNext(
			long teamRoleId, String type,
			OrderByComparator<TeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().findByType_PrevAndNext(
			teamRoleId, type, orderByComparator);
	}

	/**
	 * Returns all the team roles that the user has permission to view where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching team roles that the user has permission to view
	 */
	public static List<TeamRole> filterFindByType(String type) {
		return getPersistence().filterFindByType(type);
	}

	/**
	 * Returns a range of all the team roles that the user has permission to view where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @return the range of matching team roles that the user has permission to view
	 */
	public static List<TeamRole> filterFindByType(
		String type, int start, int end) {

		return getPersistence().filterFindByType(type, start, end);
	}

	/**
	 * Returns an ordered range of all the team roles that the user has permissions to view where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching team roles that the user has permission to view
	 */
	public static List<TeamRole> filterFindByType(
		String type, int start, int end,
		OrderByComparator<TeamRole> orderByComparator) {

		return getPersistence().filterFindByType(
			type, start, end, orderByComparator);
	}

	/**
	 * Returns the team roles before and after the current team role in the ordered set of team roles that the user has permission to view where type = &#63;.
	 *
	 * @param teamRoleId the primary key of the current team role
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team role
	 * @throws NoSuchTeamRoleException if a team role with the primary key could not be found
	 */
	public static TeamRole[] filterFindByType_PrevAndNext(
			long teamRoleId, String type,
			OrderByComparator<TeamRole> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().filterFindByType_PrevAndNext(
			teamRoleId, type, orderByComparator);
	}

	/**
	 * Removes all the team roles where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	public static void removeByType(String type) {
		getPersistence().removeByType(type);
	}

	/**
	 * Returns the number of team roles where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching team roles
	 */
	public static int countByType(String type) {
		return getPersistence().countByType(type);
	}

	/**
	 * Returns the number of team roles that the user has permission to view where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching team roles that the user has permission to view
	 */
	public static int filterCountByType(String type) {
		return getPersistence().filterCountByType(type);
	}

	/**
	 * Returns the team role where name = &#63; and type = &#63; or throws a <code>NoSuchTeamRoleException</code> if it could not be found.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public static TeamRole findByN_T(String name, String type)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().findByN_T(name, type);
	}

	/**
	 * Returns the team role where name = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public static TeamRole fetchByN_T(String name, String type) {
		return getPersistence().fetchByN_T(name, type);
	}

	/**
	 * Returns the team role where name = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param type the type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public static TeamRole fetchByN_T(
		String name, String type, boolean useFinderCache) {

		return getPersistence().fetchByN_T(name, type, useFinderCache);
	}

	/**
	 * Removes the team role where name = &#63; and type = &#63; from the database.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the team role that was removed
	 */
	public static TeamRole removeByN_T(String name, String type)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().removeByN_T(name, type);
	}

	/**
	 * Returns the number of team roles where name = &#63; and type = &#63;.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the number of matching team roles
	 */
	public static int countByN_T(String name, String type) {
		return getPersistence().countByN_T(name, type);
	}

	/**
	 * Caches the team role in the entity cache if it is enabled.
	 *
	 * @param teamRole the team role
	 */
	public static void cacheResult(TeamRole teamRole) {
		getPersistence().cacheResult(teamRole);
	}

	/**
	 * Caches the team roles in the entity cache if it is enabled.
	 *
	 * @param teamRoles the team roles
	 */
	public static void cacheResult(List<TeamRole> teamRoles) {
		getPersistence().cacheResult(teamRoles);
	}

	/**
	 * Creates a new team role with the primary key. Does not add the team role to the database.
	 *
	 * @param teamRoleId the primary key for the new team role
	 * @return the new team role
	 */
	public static TeamRole create(long teamRoleId) {
		return getPersistence().create(teamRoleId);
	}

	/**
	 * Removes the team role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param teamRoleId the primary key of the team role
	 * @return the team role that was removed
	 * @throws NoSuchTeamRoleException if a team role with the primary key could not be found
	 */
	public static TeamRole remove(long teamRoleId)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().remove(teamRoleId);
	}

	public static TeamRole updateImpl(TeamRole teamRole) {
		return getPersistence().updateImpl(teamRole);
	}

	/**
	 * Returns the team role with the primary key or throws a <code>NoSuchTeamRoleException</code> if it could not be found.
	 *
	 * @param teamRoleId the primary key of the team role
	 * @return the team role
	 * @throws NoSuchTeamRoleException if a team role with the primary key could not be found
	 */
	public static TeamRole findByPrimaryKey(long teamRoleId)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchTeamRoleException {

		return getPersistence().findByPrimaryKey(teamRoleId);
	}

	/**
	 * Returns the team role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param teamRoleId the primary key of the team role
	 * @return the team role, or <code>null</code> if a team role with the primary key could not be found
	 */
	public static TeamRole fetchByPrimaryKey(long teamRoleId) {
		return getPersistence().fetchByPrimaryKey(teamRoleId);
	}

	/**
	 * Returns all the team roles.
	 *
	 * @return the team roles
	 */
	public static List<TeamRole> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the team roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @return the range of team roles
	 */
	public static List<TeamRole> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the team roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of team roles
	 */
	public static List<TeamRole> findAll(
		int start, int end, OrderByComparator<TeamRole> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the team roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of team roles
	 * @param end the upper bound of the range of team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of team roles
	 */
	public static List<TeamRole> findAll(
		int start, int end, OrderByComparator<TeamRole> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the team roles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of team roles.
	 *
	 * @return the number of team roles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static TeamRolePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(TeamRolePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile TeamRolePersistence _persistence;

}