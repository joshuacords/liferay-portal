/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchTeamException;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the team service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamUtil
 * @generated
 */
@ProviderType
public interface TeamPersistence extends BasePersistence<Team> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TeamUtil} to access the team persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the teams where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching teams
	 */
	public java.util.List<Team> findByUuid(String uuid);

	/**
	 * Returns a range of all the teams where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of matching teams
	 */
	public java.util.List<Team> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the teams where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teams
	 */
	public java.util.List<Team> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator);

	/**
	 * Returns an ordered range of all the teams where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching teams
	 */
	public java.util.List<Team> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first team in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	public Team findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Team>
				orderByComparator)
		throws NoSuchTeamException;

	/**
	 * Returns the first team in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team, or <code>null</code> if a matching team could not be found
	 */
	public Team fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator);

	/**
	 * Returns the last team in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	public Team findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Team>
				orderByComparator)
		throws NoSuchTeamException;

	/**
	 * Returns the last team in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team, or <code>null</code> if a matching team could not be found
	 */
	public Team fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator);

	/**
	 * Returns the teams before and after the current team in the ordered set where uuid = &#63;.
	 *
	 * @param teamId the primary key of the current team
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	public Team[] findByUuid_PrevAndNext(
			long teamId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Team>
				orderByComparator)
		throws NoSuchTeamException;

	/**
	 * Returns all the teams that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching teams that the user has permission to view
	 */
	public java.util.List<Team> filterFindByUuid(String uuid);

	/**
	 * Returns a range of all the teams that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of matching teams that the user has permission to view
	 */
	public java.util.List<Team> filterFindByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the teams that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teams that the user has permission to view
	 */
	public java.util.List<Team> filterFindByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator);

	/**
	 * Returns the teams before and after the current team in the ordered set of teams that the user has permission to view where uuid = &#63;.
	 *
	 * @param teamId the primary key of the current team
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	public Team[] filterFindByUuid_PrevAndNext(
			long teamId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Team>
				orderByComparator)
		throws NoSuchTeamException;

	/**
	 * Removes all the teams where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of teams where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching teams
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the number of teams that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching teams that the user has permission to view
	 */
	public int filterCountByUuid(String uuid);

	/**
	 * Returns all the teams where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching teams
	 */
	public java.util.List<Team> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the teams where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of matching teams
	 */
	public java.util.List<Team> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the teams where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teams
	 */
	public java.util.List<Team> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator);

	/**
	 * Returns an ordered range of all the teams where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching teams
	 */
	public java.util.List<Team> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	public Team findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Team>
				orderByComparator)
		throws NoSuchTeamException;

	/**
	 * Returns the first team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team, or <code>null</code> if a matching team could not be found
	 */
	public Team fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator);

	/**
	 * Returns the last team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	public Team findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Team>
				orderByComparator)
		throws NoSuchTeamException;

	/**
	 * Returns the last team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team, or <code>null</code> if a matching team could not be found
	 */
	public Team fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator);

	/**
	 * Returns the teams before and after the current team in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param teamId the primary key of the current team
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	public Team[] findByUuid_C_PrevAndNext(
			long teamId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Team>
				orderByComparator)
		throws NoSuchTeamException;

	/**
	 * Returns all the teams that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching teams that the user has permission to view
	 */
	public java.util.List<Team> filterFindByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the teams that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of matching teams that the user has permission to view
	 */
	public java.util.List<Team> filterFindByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the teams that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teams that the user has permission to view
	 */
	public java.util.List<Team> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator);

	/**
	 * Returns the teams before and after the current team in the ordered set of teams that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param teamId the primary key of the current team
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	public Team[] filterFindByUuid_C_PrevAndNext(
			long teamId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Team>
				orderByComparator)
		throws NoSuchTeamException;

	/**
	 * Removes all the teams where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of teams where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching teams
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of teams that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching teams that the user has permission to view
	 */
	public int filterCountByUuid_C(String uuid, long companyId);

	/**
	 * Returns the team where teamKey = &#63; or throws a <code>NoSuchTeamException</code> if it could not be found.
	 *
	 * @param teamKey the team key
	 * @return the matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	public Team findByTeamKey(String teamKey) throws NoSuchTeamException;

	/**
	 * Returns the team where teamKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param teamKey the team key
	 * @return the matching team, or <code>null</code> if a matching team could not be found
	 */
	public Team fetchByTeamKey(String teamKey);

	/**
	 * Returns the team where teamKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param teamKey the team key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching team, or <code>null</code> if a matching team could not be found
	 */
	public Team fetchByTeamKey(String teamKey, boolean useFinderCache);

	/**
	 * Removes the team where teamKey = &#63; from the database.
	 *
	 * @param teamKey the team key
	 * @return the team that was removed
	 */
	public Team removeByTeamKey(String teamKey) throws NoSuchTeamException;

	/**
	 * Returns the number of teams where teamKey = &#63;.
	 *
	 * @param teamKey the team key
	 * @return the number of matching teams
	 */
	public int countByTeamKey(String teamKey);

	/**
	 * Returns all the teams where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching teams
	 */
	public java.util.List<Team> findByAccountId(long accountId);

	/**
	 * Returns a range of all the teams where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of matching teams
	 */
	public java.util.List<Team> findByAccountId(
		long accountId, int start, int end);

	/**
	 * Returns an ordered range of all the teams where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teams
	 */
	public java.util.List<Team> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator);

	/**
	 * Returns an ordered range of all the teams where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching teams
	 */
	public java.util.List<Team> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first team in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	public Team findByAccountId_First(
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<Team>
				orderByComparator)
		throws NoSuchTeamException;

	/**
	 * Returns the first team in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team, or <code>null</code> if a matching team could not be found
	 */
	public Team fetchByAccountId_First(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator);

	/**
	 * Returns the last team in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	public Team findByAccountId_Last(
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<Team>
				orderByComparator)
		throws NoSuchTeamException;

	/**
	 * Returns the last team in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team, or <code>null</code> if a matching team could not be found
	 */
	public Team fetchByAccountId_Last(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator);

	/**
	 * Returns the teams before and after the current team in the ordered set where accountId = &#63;.
	 *
	 * @param teamId the primary key of the current team
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	public Team[] findByAccountId_PrevAndNext(
			long teamId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<Team>
				orderByComparator)
		throws NoSuchTeamException;

	/**
	 * Returns all the teams that the user has permission to view where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching teams that the user has permission to view
	 */
	public java.util.List<Team> filterFindByAccountId(long accountId);

	/**
	 * Returns a range of all the teams that the user has permission to view where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of matching teams that the user has permission to view
	 */
	public java.util.List<Team> filterFindByAccountId(
		long accountId, int start, int end);

	/**
	 * Returns an ordered range of all the teams that the user has permissions to view where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching teams that the user has permission to view
	 */
	public java.util.List<Team> filterFindByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator);

	/**
	 * Returns the teams before and after the current team in the ordered set of teams that the user has permission to view where accountId = &#63;.
	 *
	 * @param teamId the primary key of the current team
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	public Team[] filterFindByAccountId_PrevAndNext(
			long teamId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<Team>
				orderByComparator)
		throws NoSuchTeamException;

	/**
	 * Removes all the teams where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	public void removeByAccountId(long accountId);

	/**
	 * Returns the number of teams where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching teams
	 */
	public int countByAccountId(long accountId);

	/**
	 * Returns the number of teams that the user has permission to view where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching teams that the user has permission to view
	 */
	public int filterCountByAccountId(long accountId);

	/**
	 * Returns the team where accountId = &#63; and name = &#63; or throws a <code>NoSuchTeamException</code> if it could not be found.
	 *
	 * @param accountId the account ID
	 * @param name the name
	 * @return the matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	public Team findByAI_N(long accountId, String name)
		throws NoSuchTeamException;

	/**
	 * Returns the team where accountId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountId the account ID
	 * @param name the name
	 * @return the matching team, or <code>null</code> if a matching team could not be found
	 */
	public Team fetchByAI_N(long accountId, String name);

	/**
	 * Returns the team where accountId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountId the account ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching team, or <code>null</code> if a matching team could not be found
	 */
	public Team fetchByAI_N(
		long accountId, String name, boolean useFinderCache);

	/**
	 * Removes the team where accountId = &#63; and name = &#63; from the database.
	 *
	 * @param accountId the account ID
	 * @param name the name
	 * @return the team that was removed
	 */
	public Team removeByAI_N(long accountId, String name)
		throws NoSuchTeamException;

	/**
	 * Returns the number of teams where accountId = &#63; and name = &#63;.
	 *
	 * @param accountId the account ID
	 * @param name the name
	 * @return the number of matching teams
	 */
	public int countByAI_N(long accountId, String name);

	/**
	 * Returns the team where accountId = &#63; and system = &#63; or throws a <code>NoSuchTeamException</code> if it could not be found.
	 *
	 * @param accountId the account ID
	 * @param system the system
	 * @return the matching team
	 * @throws NoSuchTeamException if a matching team could not be found
	 */
	public Team findByAI_S(long accountId, boolean system)
		throws NoSuchTeamException;

	/**
	 * Returns the team where accountId = &#63; and system = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountId the account ID
	 * @param system the system
	 * @return the matching team, or <code>null</code> if a matching team could not be found
	 */
	public Team fetchByAI_S(long accountId, boolean system);

	/**
	 * Returns the team where accountId = &#63; and system = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountId the account ID
	 * @param system the system
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching team, or <code>null</code> if a matching team could not be found
	 */
	public Team fetchByAI_S(
		long accountId, boolean system, boolean useFinderCache);

	/**
	 * Removes the team where accountId = &#63; and system = &#63; from the database.
	 *
	 * @param accountId the account ID
	 * @param system the system
	 * @return the team that was removed
	 */
	public Team removeByAI_S(long accountId, boolean system)
		throws NoSuchTeamException;

	/**
	 * Returns the number of teams where accountId = &#63; and system = &#63;.
	 *
	 * @param accountId the account ID
	 * @param system the system
	 * @return the number of matching teams
	 */
	public int countByAI_S(long accountId, boolean system);

	/**
	 * Caches the team in the entity cache if it is enabled.
	 *
	 * @param team the team
	 */
	public void cacheResult(Team team);

	/**
	 * Caches the teams in the entity cache if it is enabled.
	 *
	 * @param teams the teams
	 */
	public void cacheResult(java.util.List<Team> teams);

	/**
	 * Creates a new team with the primary key. Does not add the team to the database.
	 *
	 * @param teamId the primary key for the new team
	 * @return the new team
	 */
	public Team create(long teamId);

	/**
	 * Removes the team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param teamId the primary key of the team
	 * @return the team that was removed
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	public Team remove(long teamId) throws NoSuchTeamException;

	public Team updateImpl(Team team);

	/**
	 * Returns the team with the primary key or throws a <code>NoSuchTeamException</code> if it could not be found.
	 *
	 * @param teamId the primary key of the team
	 * @return the team
	 * @throws NoSuchTeamException if a team with the primary key could not be found
	 */
	public Team findByPrimaryKey(long teamId) throws NoSuchTeamException;

	/**
	 * Returns the team with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param teamId the primary key of the team
	 * @return the team, or <code>null</code> if a team with the primary key could not be found
	 */
	public Team fetchByPrimaryKey(long teamId);

	/**
	 * Returns all the teams.
	 *
	 * @return the teams
	 */
	public java.util.List<Team> findAll();

	/**
	 * Returns a range of all the teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @return the range of teams
	 */
	public java.util.List<Team> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of teams
	 */
	public java.util.List<Team> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator);

	/**
	 * Returns an ordered range of all the teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TeamModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of teams
	 * @param end the upper bound of the range of teams (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of teams
	 */
	public java.util.List<Team> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Team>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the teams from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of teams.
	 *
	 * @return the number of teams
	 */
	public int countAll();

}