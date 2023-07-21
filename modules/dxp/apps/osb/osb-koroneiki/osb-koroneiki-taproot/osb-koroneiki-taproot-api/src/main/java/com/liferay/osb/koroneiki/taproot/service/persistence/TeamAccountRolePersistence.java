/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchTeamAccountRoleException;
import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the team account role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamAccountRoleUtil
 * @generated
 */
@ProviderType
public interface TeamAccountRolePersistence
	extends BasePersistence<TeamAccountRole> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TeamAccountRoleUtil} to access the team account role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the team account roles where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @return the matching team account roles
	 */
	public java.util.List<TeamAccountRole> findByTeamId(long teamId);

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
	public java.util.List<TeamAccountRole> findByTeamId(
		long teamId, int start, int end);

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
	public java.util.List<TeamAccountRole> findByTeamId(
		long teamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator);

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
	public java.util.List<TeamAccountRole> findByTeamId(
		long teamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first team account role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public TeamAccountRole findByTeamId_First(
			long teamId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
				orderByComparator)
		throws NoSuchTeamAccountRoleException;

	/**
	 * Returns the first team account role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public TeamAccountRole fetchByTeamId_First(
		long teamId,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator);

	/**
	 * Returns the last team account role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public TeamAccountRole findByTeamId_Last(
			long teamId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
				orderByComparator)
		throws NoSuchTeamAccountRoleException;

	/**
	 * Returns the last team account role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public TeamAccountRole fetchByTeamId_Last(
		long teamId,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator);

	/**
	 * Returns the team account roles before and after the current team account role in the ordered set where teamId = &#63;.
	 *
	 * @param teamAccountRolePK the primary key of the current team account role
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team account role
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	public TeamAccountRole[] findByTeamId_PrevAndNext(
			TeamAccountRolePK teamAccountRolePK, long teamId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
				orderByComparator)
		throws NoSuchTeamAccountRoleException;

	/**
	 * Removes all the team account roles where teamId = &#63; from the database.
	 *
	 * @param teamId the team ID
	 */
	public void removeByTeamId(long teamId);

	/**
	 * Returns the number of team account roles where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @return the number of matching team account roles
	 */
	public int countByTeamId(long teamId);

	/**
	 * Returns all the team account roles where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching team account roles
	 */
	public java.util.List<TeamAccountRole> findByAccountId(long accountId);

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
	public java.util.List<TeamAccountRole> findByAccountId(
		long accountId, int start, int end);

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
	public java.util.List<TeamAccountRole> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator);

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
	public java.util.List<TeamAccountRole> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first team account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public TeamAccountRole findByAccountId_First(
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
				orderByComparator)
		throws NoSuchTeamAccountRoleException;

	/**
	 * Returns the first team account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public TeamAccountRole fetchByAccountId_First(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator);

	/**
	 * Returns the last team account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public TeamAccountRole findByAccountId_Last(
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
				orderByComparator)
		throws NoSuchTeamAccountRoleException;

	/**
	 * Returns the last team account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public TeamAccountRole fetchByAccountId_Last(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator);

	/**
	 * Returns the team account roles before and after the current team account role in the ordered set where accountId = &#63;.
	 *
	 * @param teamAccountRolePK the primary key of the current team account role
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team account role
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	public TeamAccountRole[] findByAccountId_PrevAndNext(
			TeamAccountRolePK teamAccountRolePK, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
				orderByComparator)
		throws NoSuchTeamAccountRoleException;

	/**
	 * Removes all the team account roles where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	public void removeByAccountId(long accountId);

	/**
	 * Returns the number of team account roles where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching team account roles
	 */
	public int countByAccountId(long accountId);

	/**
	 * Returns all the team account roles where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @return the matching team account roles
	 */
	public java.util.List<TeamAccountRole> findByTeamRoleId(long teamRoleId);

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
	public java.util.List<TeamAccountRole> findByTeamRoleId(
		long teamRoleId, int start, int end);

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
	public java.util.List<TeamAccountRole> findByTeamRoleId(
		long teamRoleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator);

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
	public java.util.List<TeamAccountRole> findByTeamRoleId(
		long teamRoleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first team account role in the ordered set where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public TeamAccountRole findByTeamRoleId_First(
			long teamRoleId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
				orderByComparator)
		throws NoSuchTeamAccountRoleException;

	/**
	 * Returns the first team account role in the ordered set where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public TeamAccountRole fetchByTeamRoleId_First(
		long teamRoleId,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator);

	/**
	 * Returns the last team account role in the ordered set where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public TeamAccountRole findByTeamRoleId_Last(
			long teamRoleId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
				orderByComparator)
		throws NoSuchTeamAccountRoleException;

	/**
	 * Returns the last team account role in the ordered set where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public TeamAccountRole fetchByTeamRoleId_Last(
		long teamRoleId,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator);

	/**
	 * Returns the team account roles before and after the current team account role in the ordered set where teamRoleId = &#63;.
	 *
	 * @param teamAccountRolePK the primary key of the current team account role
	 * @param teamRoleId the team role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next team account role
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	public TeamAccountRole[] findByTeamRoleId_PrevAndNext(
			TeamAccountRolePK teamAccountRolePK, long teamRoleId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
				orderByComparator)
		throws NoSuchTeamAccountRoleException;

	/**
	 * Removes all the team account roles where teamRoleId = &#63; from the database.
	 *
	 * @param teamRoleId the team role ID
	 */
	public void removeByTeamRoleId(long teamRoleId);

	/**
	 * Returns the number of team account roles where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @return the number of matching team account roles
	 */
	public int countByTeamRoleId(long teamRoleId);

	/**
	 * Returns all the team account roles where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @return the matching team account roles
	 */
	public java.util.List<TeamAccountRole> findByTI_AI(
		long teamId, long accountId);

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
	public java.util.List<TeamAccountRole> findByTI_AI(
		long teamId, long accountId, int start, int end);

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
	public java.util.List<TeamAccountRole> findByTI_AI(
		long teamId, long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator);

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
	public java.util.List<TeamAccountRole> findByTI_AI(
		long teamId, long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first team account role in the ordered set where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public TeamAccountRole findByTI_AI_First(
			long teamId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
				orderByComparator)
		throws NoSuchTeamAccountRoleException;

	/**
	 * Returns the first team account role in the ordered set where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public TeamAccountRole fetchByTI_AI_First(
		long teamId, long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator);

	/**
	 * Returns the last team account role in the ordered set where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	public TeamAccountRole findByTI_AI_Last(
			long teamId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
				orderByComparator)
		throws NoSuchTeamAccountRoleException;

	/**
	 * Returns the last team account role in the ordered set where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	public TeamAccountRole fetchByTI_AI_Last(
		long teamId, long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator);

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
	public TeamAccountRole[] findByTI_AI_PrevAndNext(
			TeamAccountRolePK teamAccountRolePK, long teamId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
				orderByComparator)
		throws NoSuchTeamAccountRoleException;

	/**
	 * Removes all the team account roles where teamId = &#63; and accountId = &#63; from the database.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 */
	public void removeByTI_AI(long teamId, long accountId);

	/**
	 * Returns the number of team account roles where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @return the number of matching team account roles
	 */
	public int countByTI_AI(long teamId, long accountId);

	/**
	 * Caches the team account role in the entity cache if it is enabled.
	 *
	 * @param teamAccountRole the team account role
	 */
	public void cacheResult(TeamAccountRole teamAccountRole);

	/**
	 * Caches the team account roles in the entity cache if it is enabled.
	 *
	 * @param teamAccountRoles the team account roles
	 */
	public void cacheResult(java.util.List<TeamAccountRole> teamAccountRoles);

	/**
	 * Creates a new team account role with the primary key. Does not add the team account role to the database.
	 *
	 * @param teamAccountRolePK the primary key for the new team account role
	 * @return the new team account role
	 */
	public TeamAccountRole create(TeamAccountRolePK teamAccountRolePK);

	/**
	 * Removes the team account role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param teamAccountRolePK the primary key of the team account role
	 * @return the team account role that was removed
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	public TeamAccountRole remove(TeamAccountRolePK teamAccountRolePK)
		throws NoSuchTeamAccountRoleException;

	public TeamAccountRole updateImpl(TeamAccountRole teamAccountRole);

	/**
	 * Returns the team account role with the primary key or throws a <code>NoSuchTeamAccountRoleException</code> if it could not be found.
	 *
	 * @param teamAccountRolePK the primary key of the team account role
	 * @return the team account role
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	public TeamAccountRole findByPrimaryKey(TeamAccountRolePK teamAccountRolePK)
		throws NoSuchTeamAccountRoleException;

	/**
	 * Returns the team account role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param teamAccountRolePK the primary key of the team account role
	 * @return the team account role, or <code>null</code> if a team account role with the primary key could not be found
	 */
	public TeamAccountRole fetchByPrimaryKey(
		TeamAccountRolePK teamAccountRolePK);

	/**
	 * Returns all the team account roles.
	 *
	 * @return the team account roles
	 */
	public java.util.List<TeamAccountRole> findAll();

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
	public java.util.List<TeamAccountRole> findAll(int start, int end);

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
	public java.util.List<TeamAccountRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator);

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
	public java.util.List<TeamAccountRole> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TeamAccountRole>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the team account roles from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of team account roles.
	 *
	 * @return the number of team account roles
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}