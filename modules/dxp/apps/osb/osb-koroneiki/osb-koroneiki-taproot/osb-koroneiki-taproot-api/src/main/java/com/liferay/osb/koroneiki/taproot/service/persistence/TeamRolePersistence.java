/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchTeamRoleException;
import com.liferay.osb.koroneiki.taproot.model.TeamRole;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the team role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamRoleUtil
 * @generated
 */
@ProviderType
public interface TeamRolePersistence extends BasePersistence<TeamRole> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TeamRoleUtil} to access the team role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the team roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching team roles
	 */
	public java.util.List<TeamRole> findByUuid(String uuid);

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
	public java.util.List<TeamRole> findByUuid(String uuid, int start, int end);

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
	public java.util.List<TeamRole> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator);

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
	public java.util.List<TeamRole> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first team role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public TeamRole findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
				orderByComparator)
		throws NoSuchTeamRoleException;

	/**
	 * Returns the first team role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public TeamRole fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator);

	/**
	 * Returns the last team role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public TeamRole findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
				orderByComparator)
		throws NoSuchTeamRoleException;

	/**
	 * Returns the last team role in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public TeamRole fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator);

	/**
	 * Returns the team roles before and after the current team role in the ordered set where uuid = &#63;.
	 *
	 * @param teamRoleId the primary key of the current team role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team role
	 * @throws NoSuchTeamRoleException if a team role with the primary key could not be found
	 */
	public TeamRole[] findByUuid_PrevAndNext(
			long teamRoleId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
				orderByComparator)
		throws NoSuchTeamRoleException;

	/**
	 * Returns all the team roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching team roles that the user has permission to view
	 */
	public java.util.List<TeamRole> filterFindByUuid(String uuid);

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
	public java.util.List<TeamRole> filterFindByUuid(
		String uuid, int start, int end);

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
	public java.util.List<TeamRole> filterFindByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator);

	/**
	 * Returns the team roles before and after the current team role in the ordered set of team roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param teamRoleId the primary key of the current team role
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team role
	 * @throws NoSuchTeamRoleException if a team role with the primary key could not be found
	 */
	public TeamRole[] filterFindByUuid_PrevAndNext(
			long teamRoleId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
				orderByComparator)
		throws NoSuchTeamRoleException;

	/**
	 * Removes all the team roles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of team roles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching team roles
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the number of team roles that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching team roles that the user has permission to view
	 */
	public int filterCountByUuid(String uuid);

	/**
	 * Returns all the team roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching team roles
	 */
	public java.util.List<TeamRole> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<TeamRole> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<TeamRole> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator);

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
	public java.util.List<TeamRole> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first team role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public TeamRole findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
				orderByComparator)
		throws NoSuchTeamRoleException;

	/**
	 * Returns the first team role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public TeamRole fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator);

	/**
	 * Returns the last team role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public TeamRole findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
				orderByComparator)
		throws NoSuchTeamRoleException;

	/**
	 * Returns the last team role in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public TeamRole fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator);

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
	public TeamRole[] findByUuid_C_PrevAndNext(
			long teamRoleId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
				orderByComparator)
		throws NoSuchTeamRoleException;

	/**
	 * Returns all the team roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching team roles that the user has permission to view
	 */
	public java.util.List<TeamRole> filterFindByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<TeamRole> filterFindByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<TeamRole> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator);

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
	public TeamRole[] filterFindByUuid_C_PrevAndNext(
			long teamRoleId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
				orderByComparator)
		throws NoSuchTeamRoleException;

	/**
	 * Removes all the team roles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of team roles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching team roles
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of team roles that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching team roles that the user has permission to view
	 */
	public int filterCountByUuid_C(String uuid, long companyId);

	/**
	 * Returns the team role where teamRoleKey = &#63; or throws a <code>NoSuchTeamRoleException</code> if it could not be found.
	 *
	 * @param teamRoleKey the team role key
	 * @return the matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public TeamRole findByTeamRoleKey(String teamRoleKey)
		throws NoSuchTeamRoleException;

	/**
	 * Returns the team role where teamRoleKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param teamRoleKey the team role key
	 * @return the matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public TeamRole fetchByTeamRoleKey(String teamRoleKey);

	/**
	 * Returns the team role where teamRoleKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param teamRoleKey the team role key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public TeamRole fetchByTeamRoleKey(
		String teamRoleKey, boolean useFinderCache);

	/**
	 * Removes the team role where teamRoleKey = &#63; from the database.
	 *
	 * @param teamRoleKey the team role key
	 * @return the team role that was removed
	 */
	public TeamRole removeByTeamRoleKey(String teamRoleKey)
		throws NoSuchTeamRoleException;

	/**
	 * Returns the number of team roles where teamRoleKey = &#63;.
	 *
	 * @param teamRoleKey the team role key
	 * @return the number of matching team roles
	 */
	public int countByTeamRoleKey(String teamRoleKey);

	/**
	 * Returns all the team roles where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching team roles
	 */
	public java.util.List<TeamRole> findByType(String type);

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
	public java.util.List<TeamRole> findByType(String type, int start, int end);

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
	public java.util.List<TeamRole> findByType(
		String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator);

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
	public java.util.List<TeamRole> findByType(
		String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first team role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public TeamRole findByType_First(
			String type,
			com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
				orderByComparator)
		throws NoSuchTeamRoleException;

	/**
	 * Returns the first team role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public TeamRole fetchByType_First(
		String type,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator);

	/**
	 * Returns the last team role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public TeamRole findByType_Last(
			String type,
			com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
				orderByComparator)
		throws NoSuchTeamRoleException;

	/**
	 * Returns the last team role in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public TeamRole fetchByType_Last(
		String type,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator);

	/**
	 * Returns the team roles before and after the current team role in the ordered set where type = &#63;.
	 *
	 * @param teamRoleId the primary key of the current team role
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team role
	 * @throws NoSuchTeamRoleException if a team role with the primary key could not be found
	 */
	public TeamRole[] findByType_PrevAndNext(
			long teamRoleId, String type,
			com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
				orderByComparator)
		throws NoSuchTeamRoleException;

	/**
	 * Returns all the team roles that the user has permission to view where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching team roles that the user has permission to view
	 */
	public java.util.List<TeamRole> filterFindByType(String type);

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
	public java.util.List<TeamRole> filterFindByType(
		String type, int start, int end);

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
	public java.util.List<TeamRole> filterFindByType(
		String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator);

	/**
	 * Returns the team roles before and after the current team role in the ordered set of team roles that the user has permission to view where type = &#63;.
	 *
	 * @param teamRoleId the primary key of the current team role
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team role
	 * @throws NoSuchTeamRoleException if a team role with the primary key could not be found
	 */
	public TeamRole[] filterFindByType_PrevAndNext(
			long teamRoleId, String type,
			com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
				orderByComparator)
		throws NoSuchTeamRoleException;

	/**
	 * Removes all the team roles where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	public void removeByType(String type);

	/**
	 * Returns the number of team roles where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching team roles
	 */
	public int countByType(String type);

	/**
	 * Returns the number of team roles that the user has permission to view where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching team roles that the user has permission to view
	 */
	public int filterCountByType(String type);

	/**
	 * Returns the team role where name = &#63; and type = &#63; or throws a <code>NoSuchTeamRoleException</code> if it could not be found.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the matching team role
	 * @throws NoSuchTeamRoleException if a matching team role could not be found
	 */
	public TeamRole findByN_T(String name, String type)
		throws NoSuchTeamRoleException;

	/**
	 * Returns the team role where name = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public TeamRole fetchByN_T(String name, String type);

	/**
	 * Returns the team role where name = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param type the type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching team role, or <code>null</code> if a matching team role could not be found
	 */
	public TeamRole fetchByN_T(
		String name, String type, boolean useFinderCache);

	/**
	 * Removes the team role where name = &#63; and type = &#63; from the database.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the team role that was removed
	 */
	public TeamRole removeByN_T(String name, String type)
		throws NoSuchTeamRoleException;

	/**
	 * Returns the number of team roles where name = &#63; and type = &#63;.
	 *
	 * @param name the name
	 * @param type the type
	 * @return the number of matching team roles
	 */
	public int countByN_T(String name, String type);

	/**
	 * Caches the team role in the entity cache if it is enabled.
	 *
	 * @param teamRole the team role
	 */
	public void cacheResult(TeamRole teamRole);

	/**
	 * Caches the team roles in the entity cache if it is enabled.
	 *
	 * @param teamRoles the team roles
	 */
	public void cacheResult(java.util.List<TeamRole> teamRoles);

	/**
	 * Creates a new team role with the primary key. Does not add the team role to the database.
	 *
	 * @param teamRoleId the primary key for the new team role
	 * @return the new team role
	 */
	public TeamRole create(long teamRoleId);

	/**
	 * Removes the team role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param teamRoleId the primary key of the team role
	 * @return the team role that was removed
	 * @throws NoSuchTeamRoleException if a team role with the primary key could not be found
	 */
	public TeamRole remove(long teamRoleId) throws NoSuchTeamRoleException;

	public TeamRole updateImpl(TeamRole teamRole);

	/**
	 * Returns the team role with the primary key or throws a <code>NoSuchTeamRoleException</code> if it could not be found.
	 *
	 * @param teamRoleId the primary key of the team role
	 * @return the team role
	 * @throws NoSuchTeamRoleException if a team role with the primary key could not be found
	 */
	public TeamRole findByPrimaryKey(long teamRoleId)
		throws NoSuchTeamRoleException;

	/**
	 * Returns the team role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param teamRoleId the primary key of the team role
	 * @return the team role, or <code>null</code> if a team role with the primary key could not be found
	 */
	public TeamRole fetchByPrimaryKey(long teamRoleId);

	/**
	 * Returns all the team roles.
	 *
	 * @return the team roles
	 */
	public java.util.List<TeamRole> findAll();

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
	public java.util.List<TeamRole> findAll(int start, int end);

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
	public java.util.List<TeamRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator);

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
	public java.util.List<TeamRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the team roles from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of team roles.
	 *
	 * @return the number of team roles
	 */
	public int countAll();

}