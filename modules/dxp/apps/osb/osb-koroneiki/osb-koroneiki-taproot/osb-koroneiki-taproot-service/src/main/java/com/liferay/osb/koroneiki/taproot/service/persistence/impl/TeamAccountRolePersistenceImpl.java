/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence.impl;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchTeamAccountRoleException;
import com.liferay.osb.koroneiki.taproot.model.TeamAccountRole;
import com.liferay.osb.koroneiki.taproot.model.impl.TeamAccountRoleImpl;
import com.liferay.osb.koroneiki.taproot.model.impl.TeamAccountRoleModelImpl;
import com.liferay.osb.koroneiki.taproot.service.persistence.TeamAccountRolePK;
import com.liferay.osb.koroneiki.taproot.service.persistence.TeamAccountRolePersistence;
import com.liferay.osb.koroneiki.taproot.service.persistence.TeamAccountRoleUtil;
import com.liferay.osb.koroneiki.taproot.service.persistence.impl.constants.KoroneikiPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the team account role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = TeamAccountRolePersistence.class)
public class TeamAccountRolePersistenceImpl
	extends BasePersistenceImpl<TeamAccountRole>
	implements TeamAccountRolePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>TeamAccountRoleUtil</code> to access the team account role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		TeamAccountRoleImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByTeamId;
	private FinderPath _finderPathWithoutPaginationFindByTeamId;
	private FinderPath _finderPathCountByTeamId;

	/**
	 * Returns all the team account roles where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @return the matching team account roles
	 */
	@Override
	public List<TeamAccountRole> findByTeamId(long teamId) {
		return findByTeamId(teamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TeamAccountRole> findByTeamId(long teamId, int start, int end) {
		return findByTeamId(teamId, start, end, null);
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
	@Override
	public List<TeamAccountRole> findByTeamId(
		long teamId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		return findByTeamId(teamId, start, end, orderByComparator, true);
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
	@Override
	public List<TeamAccountRole> findByTeamId(
		long teamId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByTeamId;
				finderArgs = new Object[] {teamId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByTeamId;
			finderArgs = new Object[] {teamId, start, end, orderByComparator};
		}

		List<TeamAccountRole> list = null;

		if (useFinderCache) {
			list = (List<TeamAccountRole>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TeamAccountRole teamAccountRole : list) {
					if (teamId != teamAccountRole.getTeamId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_TEAMACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_TEAMID_TEAMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TeamAccountRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teamId);

				list = (List<TeamAccountRole>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first team account role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	@Override
	public TeamAccountRole findByTeamId_First(
			long teamId, OrderByComparator<TeamAccountRole> orderByComparator)
		throws NoSuchTeamAccountRoleException {

		TeamAccountRole teamAccountRole = fetchByTeamId_First(
			teamId, orderByComparator);

		if (teamAccountRole != null) {
			return teamAccountRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teamId=");
		sb.append(teamId);

		sb.append("}");

		throw new NoSuchTeamAccountRoleException(sb.toString());
	}

	/**
	 * Returns the first team account role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	@Override
	public TeamAccountRole fetchByTeamId_First(
		long teamId, OrderByComparator<TeamAccountRole> orderByComparator) {

		List<TeamAccountRole> list = findByTeamId(
			teamId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last team account role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	@Override
	public TeamAccountRole findByTeamId_Last(
			long teamId, OrderByComparator<TeamAccountRole> orderByComparator)
		throws NoSuchTeamAccountRoleException {

		TeamAccountRole teamAccountRole = fetchByTeamId_Last(
			teamId, orderByComparator);

		if (teamAccountRole != null) {
			return teamAccountRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teamId=");
		sb.append(teamId);

		sb.append("}");

		throw new NoSuchTeamAccountRoleException(sb.toString());
	}

	/**
	 * Returns the last team account role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	@Override
	public TeamAccountRole fetchByTeamId_Last(
		long teamId, OrderByComparator<TeamAccountRole> orderByComparator) {

		int count = countByTeamId(teamId);

		if (count == 0) {
			return null;
		}

		List<TeamAccountRole> list = findByTeamId(
			teamId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TeamAccountRole[] findByTeamId_PrevAndNext(
			TeamAccountRolePK teamAccountRolePK, long teamId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws NoSuchTeamAccountRoleException {

		TeamAccountRole teamAccountRole = findByPrimaryKey(teamAccountRolePK);

		Session session = null;

		try {
			session = openSession();

			TeamAccountRole[] array = new TeamAccountRoleImpl[3];

			array[0] = getByTeamId_PrevAndNext(
				session, teamAccountRole, teamId, orderByComparator, true);

			array[1] = teamAccountRole;

			array[2] = getByTeamId_PrevAndNext(
				session, teamAccountRole, teamId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TeamAccountRole getByTeamId_PrevAndNext(
		Session session, TeamAccountRole teamAccountRole, long teamId,
		OrderByComparator<TeamAccountRole> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_TEAMACCOUNTROLE_WHERE);

		sb.append(_FINDER_COLUMN_TEAMID_TEAMID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(TeamAccountRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(teamId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						teamAccountRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TeamAccountRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the team account roles where teamId = &#63; from the database.
	 *
	 * @param teamId the team ID
	 */
	@Override
	public void removeByTeamId(long teamId) {
		for (TeamAccountRole teamAccountRole :
				findByTeamId(
					teamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(teamAccountRole);
		}
	}

	/**
	 * Returns the number of team account roles where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @return the number of matching team account roles
	 */
	@Override
	public int countByTeamId(long teamId) {
		FinderPath finderPath = _finderPathCountByTeamId;

		Object[] finderArgs = new Object[] {teamId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TEAMACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_TEAMID_TEAMID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teamId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TEAMID_TEAMID_2 =
		"teamAccountRole.id.teamId = ?";

	private FinderPath _finderPathWithPaginationFindByAccountId;
	private FinderPath _finderPathWithoutPaginationFindByAccountId;
	private FinderPath _finderPathCountByAccountId;

	/**
	 * Returns all the team account roles where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching team account roles
	 */
	@Override
	public List<TeamAccountRole> findByAccountId(long accountId) {
		return findByAccountId(
			accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TeamAccountRole> findByAccountId(
		long accountId, int start, int end) {

		return findByAccountId(accountId, start, end, null);
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
	@Override
	public List<TeamAccountRole> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		return findByAccountId(accountId, start, end, orderByComparator, true);
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
	@Override
	public List<TeamAccountRole> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByAccountId;
				finderArgs = new Object[] {accountId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByAccountId;
			finderArgs = new Object[] {
				accountId, start, end, orderByComparator
			};
		}

		List<TeamAccountRole> list = null;

		if (useFinderCache) {
			list = (List<TeamAccountRole>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TeamAccountRole teamAccountRole : list) {
					if (accountId != teamAccountRole.getAccountId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_TEAMACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TeamAccountRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

				list = (List<TeamAccountRole>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first team account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	@Override
	public TeamAccountRole findByAccountId_First(
			long accountId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws NoSuchTeamAccountRoleException {

		TeamAccountRole teamAccountRole = fetchByAccountId_First(
			accountId, orderByComparator);

		if (teamAccountRole != null) {
			return teamAccountRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchTeamAccountRoleException(sb.toString());
	}

	/**
	 * Returns the first team account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	@Override
	public TeamAccountRole fetchByAccountId_First(
		long accountId, OrderByComparator<TeamAccountRole> orderByComparator) {

		List<TeamAccountRole> list = findByAccountId(
			accountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last team account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	@Override
	public TeamAccountRole findByAccountId_Last(
			long accountId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws NoSuchTeamAccountRoleException {

		TeamAccountRole teamAccountRole = fetchByAccountId_Last(
			accountId, orderByComparator);

		if (teamAccountRole != null) {
			return teamAccountRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchTeamAccountRoleException(sb.toString());
	}

	/**
	 * Returns the last team account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	@Override
	public TeamAccountRole fetchByAccountId_Last(
		long accountId, OrderByComparator<TeamAccountRole> orderByComparator) {

		int count = countByAccountId(accountId);

		if (count == 0) {
			return null;
		}

		List<TeamAccountRole> list = findByAccountId(
			accountId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TeamAccountRole[] findByAccountId_PrevAndNext(
			TeamAccountRolePK teamAccountRolePK, long accountId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws NoSuchTeamAccountRoleException {

		TeamAccountRole teamAccountRole = findByPrimaryKey(teamAccountRolePK);

		Session session = null;

		try {
			session = openSession();

			TeamAccountRole[] array = new TeamAccountRoleImpl[3];

			array[0] = getByAccountId_PrevAndNext(
				session, teamAccountRole, accountId, orderByComparator, true);

			array[1] = teamAccountRole;

			array[2] = getByAccountId_PrevAndNext(
				session, teamAccountRole, accountId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TeamAccountRole getByAccountId_PrevAndNext(
		Session session, TeamAccountRole teamAccountRole, long accountId,
		OrderByComparator<TeamAccountRole> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_TEAMACCOUNTROLE_WHERE);

		sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(TeamAccountRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(accountId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						teamAccountRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TeamAccountRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the team account roles where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	@Override
	public void removeByAccountId(long accountId) {
		for (TeamAccountRole teamAccountRole :
				findByAccountId(
					accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(teamAccountRole);
		}
	}

	/**
	 * Returns the number of team account roles where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching team account roles
	 */
	@Override
	public int countByAccountId(long accountId) {
		FinderPath finderPath = _finderPathCountByAccountId;

		Object[] finderArgs = new Object[] {accountId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TEAMACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2 =
		"teamAccountRole.id.accountId = ?";

	private FinderPath _finderPathWithPaginationFindByTeamRoleId;
	private FinderPath _finderPathWithoutPaginationFindByTeamRoleId;
	private FinderPath _finderPathCountByTeamRoleId;

	/**
	 * Returns all the team account roles where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @return the matching team account roles
	 */
	@Override
	public List<TeamAccountRole> findByTeamRoleId(long teamRoleId) {
		return findByTeamRoleId(
			teamRoleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TeamAccountRole> findByTeamRoleId(
		long teamRoleId, int start, int end) {

		return findByTeamRoleId(teamRoleId, start, end, null);
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
	@Override
	public List<TeamAccountRole> findByTeamRoleId(
		long teamRoleId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		return findByTeamRoleId(
			teamRoleId, start, end, orderByComparator, true);
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
	@Override
	public List<TeamAccountRole> findByTeamRoleId(
		long teamRoleId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByTeamRoleId;
				finderArgs = new Object[] {teamRoleId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByTeamRoleId;
			finderArgs = new Object[] {
				teamRoleId, start, end, orderByComparator
			};
		}

		List<TeamAccountRole> list = null;

		if (useFinderCache) {
			list = (List<TeamAccountRole>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TeamAccountRole teamAccountRole : list) {
					if (teamRoleId != teamAccountRole.getTeamRoleId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_TEAMACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_TEAMROLEID_TEAMROLEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TeamAccountRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teamRoleId);

				list = (List<TeamAccountRole>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first team account role in the ordered set where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	@Override
	public TeamAccountRole findByTeamRoleId_First(
			long teamRoleId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws NoSuchTeamAccountRoleException {

		TeamAccountRole teamAccountRole = fetchByTeamRoleId_First(
			teamRoleId, orderByComparator);

		if (teamAccountRole != null) {
			return teamAccountRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teamRoleId=");
		sb.append(teamRoleId);

		sb.append("}");

		throw new NoSuchTeamAccountRoleException(sb.toString());
	}

	/**
	 * Returns the first team account role in the ordered set where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	@Override
	public TeamAccountRole fetchByTeamRoleId_First(
		long teamRoleId, OrderByComparator<TeamAccountRole> orderByComparator) {

		List<TeamAccountRole> list = findByTeamRoleId(
			teamRoleId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last team account role in the ordered set where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role
	 * @throws NoSuchTeamAccountRoleException if a matching team account role could not be found
	 */
	@Override
	public TeamAccountRole findByTeamRoleId_Last(
			long teamRoleId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws NoSuchTeamAccountRoleException {

		TeamAccountRole teamAccountRole = fetchByTeamRoleId_Last(
			teamRoleId, orderByComparator);

		if (teamAccountRole != null) {
			return teamAccountRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teamRoleId=");
		sb.append(teamRoleId);

		sb.append("}");

		throw new NoSuchTeamAccountRoleException(sb.toString());
	}

	/**
	 * Returns the last team account role in the ordered set where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	@Override
	public TeamAccountRole fetchByTeamRoleId_Last(
		long teamRoleId, OrderByComparator<TeamAccountRole> orderByComparator) {

		int count = countByTeamRoleId(teamRoleId);

		if (count == 0) {
			return null;
		}

		List<TeamAccountRole> list = findByTeamRoleId(
			teamRoleId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TeamAccountRole[] findByTeamRoleId_PrevAndNext(
			TeamAccountRolePK teamAccountRolePK, long teamRoleId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws NoSuchTeamAccountRoleException {

		TeamAccountRole teamAccountRole = findByPrimaryKey(teamAccountRolePK);

		Session session = null;

		try {
			session = openSession();

			TeamAccountRole[] array = new TeamAccountRoleImpl[3];

			array[0] = getByTeamRoleId_PrevAndNext(
				session, teamAccountRole, teamRoleId, orderByComparator, true);

			array[1] = teamAccountRole;

			array[2] = getByTeamRoleId_PrevAndNext(
				session, teamAccountRole, teamRoleId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TeamAccountRole getByTeamRoleId_PrevAndNext(
		Session session, TeamAccountRole teamAccountRole, long teamRoleId,
		OrderByComparator<TeamAccountRole> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_TEAMACCOUNTROLE_WHERE);

		sb.append(_FINDER_COLUMN_TEAMROLEID_TEAMROLEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(TeamAccountRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(teamRoleId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						teamAccountRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TeamAccountRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the team account roles where teamRoleId = &#63; from the database.
	 *
	 * @param teamRoleId the team role ID
	 */
	@Override
	public void removeByTeamRoleId(long teamRoleId) {
		for (TeamAccountRole teamAccountRole :
				findByTeamRoleId(
					teamRoleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(teamAccountRole);
		}
	}

	/**
	 * Returns the number of team account roles where teamRoleId = &#63;.
	 *
	 * @param teamRoleId the team role ID
	 * @return the number of matching team account roles
	 */
	@Override
	public int countByTeamRoleId(long teamRoleId) {
		FinderPath finderPath = _finderPathCountByTeamRoleId;

		Object[] finderArgs = new Object[] {teamRoleId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_TEAMACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_TEAMROLEID_TEAMROLEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teamRoleId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TEAMROLEID_TEAMROLEID_2 =
		"teamAccountRole.id.teamRoleId = ?";

	private FinderPath _finderPathWithPaginationFindByTI_AI;
	private FinderPath _finderPathWithoutPaginationFindByTI_AI;
	private FinderPath _finderPathCountByTI_AI;

	/**
	 * Returns all the team account roles where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @return the matching team account roles
	 */
	@Override
	public List<TeamAccountRole> findByTI_AI(long teamId, long accountId) {
		return findByTI_AI(
			teamId, accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TeamAccountRole> findByTI_AI(
		long teamId, long accountId, int start, int end) {

		return findByTI_AI(teamId, accountId, start, end, null);
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
	@Override
	public List<TeamAccountRole> findByTI_AI(
		long teamId, long accountId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		return findByTI_AI(
			teamId, accountId, start, end, orderByComparator, true);
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
	@Override
	public List<TeamAccountRole> findByTI_AI(
		long teamId, long accountId, int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByTI_AI;
				finderArgs = new Object[] {teamId, accountId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByTI_AI;
			finderArgs = new Object[] {
				teamId, accountId, start, end, orderByComparator
			};
		}

		List<TeamAccountRole> list = null;

		if (useFinderCache) {
			list = (List<TeamAccountRole>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (TeamAccountRole teamAccountRole : list) {
					if ((teamId != teamAccountRole.getTeamId()) ||
						(accountId != teamAccountRole.getAccountId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_TEAMACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_TI_AI_TEAMID_2);

			sb.append(_FINDER_COLUMN_TI_AI_ACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(TeamAccountRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teamId);

				queryPos.add(accountId);

				list = (List<TeamAccountRole>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public TeamAccountRole findByTI_AI_First(
			long teamId, long accountId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws NoSuchTeamAccountRoleException {

		TeamAccountRole teamAccountRole = fetchByTI_AI_First(
			teamId, accountId, orderByComparator);

		if (teamAccountRole != null) {
			return teamAccountRole;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teamId=");
		sb.append(teamId);

		sb.append(", accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchTeamAccountRoleException(sb.toString());
	}

	/**
	 * Returns the first team account role in the ordered set where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	@Override
	public TeamAccountRole fetchByTI_AI_First(
		long teamId, long accountId,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		List<TeamAccountRole> list = findByTI_AI(
			teamId, accountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TeamAccountRole findByTI_AI_Last(
			long teamId, long accountId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws NoSuchTeamAccountRoleException {

		TeamAccountRole teamAccountRole = fetchByTI_AI_Last(
			teamId, accountId, orderByComparator);

		if (teamAccountRole != null) {
			return teamAccountRole;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teamId=");
		sb.append(teamId);

		sb.append(", accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchTeamAccountRoleException(sb.toString());
	}

	/**
	 * Returns the last team account role in the ordered set where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching team account role, or <code>null</code> if a matching team account role could not be found
	 */
	@Override
	public TeamAccountRole fetchByTI_AI_Last(
		long teamId, long accountId,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		int count = countByTI_AI(teamId, accountId);

		if (count == 0) {
			return null;
		}

		List<TeamAccountRole> list = findByTI_AI(
			teamId, accountId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public TeamAccountRole[] findByTI_AI_PrevAndNext(
			TeamAccountRolePK teamAccountRolePK, long teamId, long accountId,
			OrderByComparator<TeamAccountRole> orderByComparator)
		throws NoSuchTeamAccountRoleException {

		TeamAccountRole teamAccountRole = findByPrimaryKey(teamAccountRolePK);

		Session session = null;

		try {
			session = openSession();

			TeamAccountRole[] array = new TeamAccountRoleImpl[3];

			array[0] = getByTI_AI_PrevAndNext(
				session, teamAccountRole, teamId, accountId, orderByComparator,
				true);

			array[1] = teamAccountRole;

			array[2] = getByTI_AI_PrevAndNext(
				session, teamAccountRole, teamId, accountId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected TeamAccountRole getByTI_AI_PrevAndNext(
		Session session, TeamAccountRole teamAccountRole, long teamId,
		long accountId, OrderByComparator<TeamAccountRole> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_TEAMACCOUNTROLE_WHERE);

		sb.append(_FINDER_COLUMN_TI_AI_TEAMID_2);

		sb.append(_FINDER_COLUMN_TI_AI_ACCOUNTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(TeamAccountRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(teamId);

		queryPos.add(accountId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						teamAccountRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<TeamAccountRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the team account roles where teamId = &#63; and accountId = &#63; from the database.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 */
	@Override
	public void removeByTI_AI(long teamId, long accountId) {
		for (TeamAccountRole teamAccountRole :
				findByTI_AI(
					teamId, accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(teamAccountRole);
		}
	}

	/**
	 * Returns the number of team account roles where teamId = &#63; and accountId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param accountId the account ID
	 * @return the number of matching team account roles
	 */
	@Override
	public int countByTI_AI(long teamId, long accountId) {
		FinderPath finderPath = _finderPathCountByTI_AI;

		Object[] finderArgs = new Object[] {teamId, accountId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_TEAMACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_TI_AI_TEAMID_2);

			sb.append(_FINDER_COLUMN_TI_AI_ACCOUNTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teamId);

				queryPos.add(accountId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TI_AI_TEAMID_2 =
		"teamAccountRole.id.teamId = ? AND ";

	private static final String _FINDER_COLUMN_TI_AI_ACCOUNTID_2 =
		"teamAccountRole.id.accountId = ?";

	public TeamAccountRolePersistenceImpl() {
		setModelClass(TeamAccountRole.class);

		setModelImplClass(TeamAccountRoleImpl.class);
		setModelPKClass(TeamAccountRolePK.class);
	}

	/**
	 * Caches the team account role in the entity cache if it is enabled.
	 *
	 * @param teamAccountRole the team account role
	 */
	@Override
	public void cacheResult(TeamAccountRole teamAccountRole) {
		entityCache.putResult(
			entityCacheEnabled, TeamAccountRoleImpl.class,
			teamAccountRole.getPrimaryKey(), teamAccountRole);

		teamAccountRole.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the team account roles in the entity cache if it is enabled.
	 *
	 * @param teamAccountRoles the team account roles
	 */
	@Override
	public void cacheResult(List<TeamAccountRole> teamAccountRoles) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (teamAccountRoles.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (TeamAccountRole teamAccountRole : teamAccountRoles) {
			if (entityCache.getResult(
					entityCacheEnabled, TeamAccountRoleImpl.class,
					teamAccountRole.getPrimaryKey()) == null) {

				cacheResult(teamAccountRole);
			}
			else {
				teamAccountRole.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all team account roles.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TeamAccountRoleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the team account role.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TeamAccountRole teamAccountRole) {
		entityCache.removeResult(
			entityCacheEnabled, TeamAccountRoleImpl.class,
			teamAccountRole.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<TeamAccountRole> teamAccountRoles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TeamAccountRole teamAccountRole : teamAccountRoles) {
			entityCache.removeResult(
				entityCacheEnabled, TeamAccountRoleImpl.class,
				teamAccountRole.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, TeamAccountRoleImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new team account role with the primary key. Does not add the team account role to the database.
	 *
	 * @param teamAccountRolePK the primary key for the new team account role
	 * @return the new team account role
	 */
	@Override
	public TeamAccountRole create(TeamAccountRolePK teamAccountRolePK) {
		TeamAccountRole teamAccountRole = new TeamAccountRoleImpl();

		teamAccountRole.setNew(true);
		teamAccountRole.setPrimaryKey(teamAccountRolePK);

		return teamAccountRole;
	}

	/**
	 * Removes the team account role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param teamAccountRolePK the primary key of the team account role
	 * @return the team account role that was removed
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	@Override
	public TeamAccountRole remove(TeamAccountRolePK teamAccountRolePK)
		throws NoSuchTeamAccountRoleException {

		return remove((Serializable)teamAccountRolePK);
	}

	/**
	 * Removes the team account role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the team account role
	 * @return the team account role that was removed
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	@Override
	public TeamAccountRole remove(Serializable primaryKey)
		throws NoSuchTeamAccountRoleException {

		Session session = null;

		try {
			session = openSession();

			TeamAccountRole teamAccountRole = (TeamAccountRole)session.get(
				TeamAccountRoleImpl.class, primaryKey);

			if (teamAccountRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTeamAccountRoleException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(teamAccountRole);
		}
		catch (NoSuchTeamAccountRoleException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected TeamAccountRole removeImpl(TeamAccountRole teamAccountRole) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(teamAccountRole)) {
				teamAccountRole = (TeamAccountRole)session.get(
					TeamAccountRoleImpl.class,
					teamAccountRole.getPrimaryKeyObj());
			}

			if (teamAccountRole != null) {
				session.delete(teamAccountRole);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (teamAccountRole != null) {
			clearCache(teamAccountRole);
		}

		return teamAccountRole;
	}

	@Override
	public TeamAccountRole updateImpl(TeamAccountRole teamAccountRole) {
		boolean isNew = teamAccountRole.isNew();

		if (!(teamAccountRole instanceof TeamAccountRoleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(teamAccountRole.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					teamAccountRole);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in teamAccountRole proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom TeamAccountRole implementation " +
					teamAccountRole.getClass());
		}

		TeamAccountRoleModelImpl teamAccountRoleModelImpl =
			(TeamAccountRoleModelImpl)teamAccountRole;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(teamAccountRole);

				teamAccountRole.setNew(false);
			}
			else {
				teamAccountRole = (TeamAccountRole)session.merge(
					teamAccountRole);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {teamAccountRoleModelImpl.getTeamId()};

			finderCache.removeResult(_finderPathCountByTeamId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByTeamId, args);

			args = new Object[] {teamAccountRoleModelImpl.getAccountId()};

			finderCache.removeResult(_finderPathCountByAccountId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAccountId, args);

			args = new Object[] {teamAccountRoleModelImpl.getTeamRoleId()};

			finderCache.removeResult(_finderPathCountByTeamRoleId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByTeamRoleId, args);

			args = new Object[] {
				teamAccountRoleModelImpl.getTeamId(),
				teamAccountRoleModelImpl.getAccountId()
			};

			finderCache.removeResult(_finderPathCountByTI_AI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByTI_AI, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((teamAccountRoleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByTeamId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					teamAccountRoleModelImpl.getOriginalTeamId()
				};

				finderCache.removeResult(_finderPathCountByTeamId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTeamId, args);

				args = new Object[] {teamAccountRoleModelImpl.getTeamId()};

				finderCache.removeResult(_finderPathCountByTeamId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTeamId, args);
			}

			if ((teamAccountRoleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByAccountId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					teamAccountRoleModelImpl.getOriginalAccountId()
				};

				finderCache.removeResult(_finderPathCountByAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountId, args);

				args = new Object[] {teamAccountRoleModelImpl.getAccountId()};

				finderCache.removeResult(_finderPathCountByAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountId, args);
			}

			if ((teamAccountRoleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByTeamRoleId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					teamAccountRoleModelImpl.getOriginalTeamRoleId()
				};

				finderCache.removeResult(_finderPathCountByTeamRoleId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTeamRoleId, args);

				args = new Object[] {teamAccountRoleModelImpl.getTeamRoleId()};

				finderCache.removeResult(_finderPathCountByTeamRoleId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTeamRoleId, args);
			}

			if ((teamAccountRoleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByTI_AI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					teamAccountRoleModelImpl.getOriginalTeamId(),
					teamAccountRoleModelImpl.getOriginalAccountId()
				};

				finderCache.removeResult(_finderPathCountByTI_AI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTI_AI, args);

				args = new Object[] {
					teamAccountRoleModelImpl.getTeamId(),
					teamAccountRoleModelImpl.getAccountId()
				};

				finderCache.removeResult(_finderPathCountByTI_AI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTI_AI, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, TeamAccountRoleImpl.class,
			teamAccountRole.getPrimaryKey(), teamAccountRole, false);

		teamAccountRole.resetOriginalValues();

		return teamAccountRole;
	}

	/**
	 * Returns the team account role with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the team account role
	 * @return the team account role
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	@Override
	public TeamAccountRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTeamAccountRoleException {

		TeamAccountRole teamAccountRole = fetchByPrimaryKey(primaryKey);

		if (teamAccountRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTeamAccountRoleException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return teamAccountRole;
	}

	/**
	 * Returns the team account role with the primary key or throws a <code>NoSuchTeamAccountRoleException</code> if it could not be found.
	 *
	 * @param teamAccountRolePK the primary key of the team account role
	 * @return the team account role
	 * @throws NoSuchTeamAccountRoleException if a team account role with the primary key could not be found
	 */
	@Override
	public TeamAccountRole findByPrimaryKey(TeamAccountRolePK teamAccountRolePK)
		throws NoSuchTeamAccountRoleException {

		return findByPrimaryKey((Serializable)teamAccountRolePK);
	}

	/**
	 * Returns the team account role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param teamAccountRolePK the primary key of the team account role
	 * @return the team account role, or <code>null</code> if a team account role with the primary key could not be found
	 */
	@Override
	public TeamAccountRole fetchByPrimaryKey(
		TeamAccountRolePK teamAccountRolePK) {

		return fetchByPrimaryKey((Serializable)teamAccountRolePK);
	}

	/**
	 * Returns all the team account roles.
	 *
	 * @return the team account roles
	 */
	@Override
	public List<TeamAccountRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<TeamAccountRole> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<TeamAccountRole> findAll(
		int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<TeamAccountRole> findAll(
		int start, int end,
		OrderByComparator<TeamAccountRole> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<TeamAccountRole> list = null;

		if (useFinderCache) {
			list = (List<TeamAccountRole>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_TEAMACCOUNTROLE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_TEAMACCOUNTROLE;

				sql = sql.concat(TeamAccountRoleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<TeamAccountRole>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the team account roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TeamAccountRole teamAccountRole : findAll()) {
			remove(teamAccountRole);
		}
	}

	/**
	 * Returns the number of team account roles.
	 *
	 * @return the number of team account roles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_TEAMACCOUNTROLE);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "teamAccountRolePK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_TEAMACCOUNTROLE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return TeamAccountRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the team account role persistence.
	 */
	@Activate
	public void activate() {
		TeamAccountRoleModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		TeamAccountRoleModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TeamAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TeamAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByTeamId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TeamAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTeamId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByTeamId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TeamAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTeamId",
			new String[] {Long.class.getName()},
			TeamAccountRoleModelImpl.TEAMID_COLUMN_BITMASK);

		_finderPathCountByTeamId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTeamId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TeamAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TeamAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountId",
			new String[] {Long.class.getName()},
			TeamAccountRoleModelImpl.ACCOUNTID_COLUMN_BITMASK);

		_finderPathCountByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByTeamRoleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TeamAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTeamRoleId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByTeamRoleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TeamAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTeamRoleId",
			new String[] {Long.class.getName()},
			TeamAccountRoleModelImpl.TEAMROLEID_COLUMN_BITMASK);

		_finderPathCountByTeamRoleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTeamRoleId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByTI_AI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TeamAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTI_AI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByTI_AI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, TeamAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTI_AI",
			new String[] {Long.class.getName(), Long.class.getName()},
			TeamAccountRoleModelImpl.TEAMID_COLUMN_BITMASK |
			TeamAccountRoleModelImpl.ACCOUNTID_COLUMN_BITMASK);

		_finderPathCountByTI_AI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTI_AI",
			new String[] {Long.class.getName(), Long.class.getName()});

		TeamAccountRoleUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		TeamAccountRoleUtil.setPersistence(null);

		entityCache.removeCache(TeamAccountRoleImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = KoroneikiPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.osb.koroneiki.taproot.model.TeamAccountRole"),
			true);
	}

	@Override
	@Reference(
		target = KoroneikiPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = KoroneikiPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_TEAMACCOUNTROLE =
		"SELECT teamAccountRole FROM TeamAccountRole teamAccountRole";

	private static final String _SQL_SELECT_TEAMACCOUNTROLE_WHERE =
		"SELECT teamAccountRole FROM TeamAccountRole teamAccountRole WHERE ";

	private static final String _SQL_COUNT_TEAMACCOUNTROLE =
		"SELECT COUNT(teamAccountRole) FROM TeamAccountRole teamAccountRole";

	private static final String _SQL_COUNT_TEAMACCOUNTROLE_WHERE =
		"SELECT COUNT(teamAccountRole) FROM TeamAccountRole teamAccountRole WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "teamAccountRole.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No TeamAccountRole exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No TeamAccountRole exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		TeamAccountRolePersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"teamId", "accountId", "teamRoleId"});

}