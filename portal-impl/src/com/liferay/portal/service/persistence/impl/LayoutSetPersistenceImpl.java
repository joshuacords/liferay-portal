/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.NoSuchLayoutSetException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.LayoutSetPersistence;
import com.liferay.portal.kernel.service.persistence.LayoutSetUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.impl.LayoutSetImpl;
import com.liferay.portal.model.impl.LayoutSetModelImpl;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the layout set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LayoutSetPersistenceImpl
	extends BasePersistenceImpl<LayoutSet> implements LayoutSetPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LayoutSetUtil</code> to access the layout set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LayoutSetImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the layout sets where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching layout sets
	 */
	@Override
	public List<LayoutSet> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<LayoutSet> list = null;

		if (useFinderCache) {
			list = (List<LayoutSet>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutSet layoutSet : list) {
					if (groupId != layoutSet.getGroupId()) {
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

			sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<LayoutSet>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
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
	 * Returns the first layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByGroupId_First(
			long groupId, OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByGroupId_First(groupId, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the first layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByGroupId_First(
		long groupId, OrderByComparator<LayoutSet> orderByComparator) {

		List<LayoutSet> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByGroupId_Last(
			long groupId, OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByGroupId_Last(groupId, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the last layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByGroupId_Last(
		long groupId, OrderByComparator<LayoutSet> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<LayoutSet> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout sets before and after the current layout set in the ordered set where groupId = &#63;.
	 *
	 * @param layoutSetId the primary key of the current layout set
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet[] findByGroupId_PrevAndNext(
			long layoutSetId, long groupId,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = findByPrimaryKey(layoutSetId);

		Session session = null;

		try {
			session = openSession();

			LayoutSet[] array = new LayoutSetImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, layoutSet, groupId, orderByComparator, true);

			array[1] = layoutSet;

			array[2] = getByGroupId_PrevAndNext(
				session, layoutSet, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutSet getByGroupId_PrevAndNext(
		Session session, LayoutSet layoutSet, long groupId,
		OrderByComparator<LayoutSet> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(layoutSet)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutSet> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout sets where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (LayoutSet layoutSet :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"layoutSet.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId_Head;
	private FinderPath _finderPathWithoutPaginationFindByGroupId_Head;
	private FinderPath _finderPathCountByGroupId_Head;

	/**
	 * Returns all the layout sets where groupId = &#63; and head = &#63;.
	 *
	 * @param groupId the group ID
	 * @param head the head
	 * @return the matching layout sets
	 */
	@Override
	public List<LayoutSet> findByGroupId_Head(long groupId, boolean head) {
		return findByGroupId_Head(
			groupId, head, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets where groupId = &#63; and head = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param head the head
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByGroupId_Head(
		long groupId, boolean head, int start, int end) {

		return findByGroupId_Head(groupId, head, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets where groupId = &#63; and head = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param head the head
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByGroupId_Head(
		long groupId, boolean head, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator) {

		return findByGroupId_Head(
			groupId, head, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout sets where groupId = &#63; and head = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param head the head
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByGroupId_Head(
		long groupId, boolean head, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId_Head;
				finderArgs = new Object[] {groupId, head};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId_Head;
			finderArgs = new Object[] {
				groupId, head, start, end, orderByComparator
			};
		}

		List<LayoutSet> list = null;

		if (useFinderCache) {
			list = (List<LayoutSet>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutSet layoutSet : list) {
					if ((groupId != layoutSet.getGroupId()) ||
						(head != layoutSet.isHead())) {

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

			sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_HEAD_GROUPID_2);

			sb.append(_FINDER_COLUMN_GROUPID_HEAD_HEAD_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(head);

				list = (List<LayoutSet>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
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
	 * Returns the first layout set in the ordered set where groupId = &#63; and head = &#63;.
	 *
	 * @param groupId the group ID
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByGroupId_Head_First(
			long groupId, boolean head,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByGroupId_Head_First(
			groupId, head, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", head=");
		sb.append(head);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the first layout set in the ordered set where groupId = &#63; and head = &#63;.
	 *
	 * @param groupId the group ID
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByGroupId_Head_First(
		long groupId, boolean head,
		OrderByComparator<LayoutSet> orderByComparator) {

		List<LayoutSet> list = findByGroupId_Head(
			groupId, head, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout set in the ordered set where groupId = &#63; and head = &#63;.
	 *
	 * @param groupId the group ID
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByGroupId_Head_Last(
			long groupId, boolean head,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByGroupId_Head_Last(
			groupId, head, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", head=");
		sb.append(head);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the last layout set in the ordered set where groupId = &#63; and head = &#63;.
	 *
	 * @param groupId the group ID
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByGroupId_Head_Last(
		long groupId, boolean head,
		OrderByComparator<LayoutSet> orderByComparator) {

		int count = countByGroupId_Head(groupId, head);

		if (count == 0) {
			return null;
		}

		List<LayoutSet> list = findByGroupId_Head(
			groupId, head, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout sets before and after the current layout set in the ordered set where groupId = &#63; and head = &#63;.
	 *
	 * @param layoutSetId the primary key of the current layout set
	 * @param groupId the group ID
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet[] findByGroupId_Head_PrevAndNext(
			long layoutSetId, long groupId, boolean head,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = findByPrimaryKey(layoutSetId);

		Session session = null;

		try {
			session = openSession();

			LayoutSet[] array = new LayoutSetImpl[3];

			array[0] = getByGroupId_Head_PrevAndNext(
				session, layoutSet, groupId, head, orderByComparator, true);

			array[1] = layoutSet;

			array[2] = getByGroupId_Head_PrevAndNext(
				session, layoutSet, groupId, head, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutSet getByGroupId_Head_PrevAndNext(
		Session session, LayoutSet layoutSet, long groupId, boolean head,
		OrderByComparator<LayoutSet> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_HEAD_GROUPID_2);

		sb.append(_FINDER_COLUMN_GROUPID_HEAD_HEAD_2);

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
			sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(head);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(layoutSet)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutSet> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout sets where groupId = &#63; and head = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param head the head
	 */
	@Override
	public void removeByGroupId_Head(long groupId, boolean head) {
		for (LayoutSet layoutSet :
				findByGroupId_Head(
					groupId, head, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets where groupId = &#63; and head = &#63;.
	 *
	 * @param groupId the group ID
	 * @param head the head
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByGroupId_Head(long groupId, boolean head) {
		FinderPath finderPath = _finderPathCountByGroupId_Head;

		Object[] finderArgs = new Object[] {groupId, head};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_HEAD_GROUPID_2);

			sb.append(_FINDER_COLUMN_GROUPID_HEAD_HEAD_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(head);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_HEAD_GROUPID_2 =
		"layoutSet.groupId = ? AND ";

	private static final String _FINDER_COLUMN_GROUPID_HEAD_HEAD_2 =
		"layoutSet.head = ?";

	private FinderPath _finderPathWithPaginationFindByLayoutSetPrototypeUuid;
	private FinderPath _finderPathWithoutPaginationFindByLayoutSetPrototypeUuid;
	private FinderPath _finderPathCountByLayoutSetPrototypeUuid;

	/**
	 * Returns all the layout sets where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @return the matching layout sets
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid(
		String layoutSetPrototypeUuid) {

		return findByLayoutSetPrototypeUuid(
			layoutSetPrototypeUuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets where layoutSetPrototypeUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid(
		String layoutSetPrototypeUuid, int start, int end) {

		return findByLayoutSetPrototypeUuid(
			layoutSetPrototypeUuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets where layoutSetPrototypeUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid(
		String layoutSetPrototypeUuid, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator) {

		return findByLayoutSetPrototypeUuid(
			layoutSetPrototypeUuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout sets where layoutSetPrototypeUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid(
		String layoutSetPrototypeUuid, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator,
		boolean useFinderCache) {

		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid;
				finderArgs = new Object[] {layoutSetPrototypeUuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByLayoutSetPrototypeUuid;
			finderArgs = new Object[] {
				layoutSetPrototypeUuid, start, end, orderByComparator
			};
		}

		List<LayoutSet> list = null;

		if (useFinderCache) {
			list = (List<LayoutSet>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutSet layoutSet : list) {
					if (!layoutSetPrototypeUuid.equals(
							layoutSet.getLayoutSetPrototypeUuid())) {

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

			sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

			boolean bindLayoutSetPrototypeUuid = false;

			if (layoutSetPrototypeUuid.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_3);
			}
			else {
				bindLayoutSetPrototypeUuid = true;

				sb.append(
					_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindLayoutSetPrototypeUuid) {
					queryPos.add(layoutSetPrototypeUuid);
				}

				list = (List<LayoutSet>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
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
	 * Returns the first layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByLayoutSetPrototypeUuid_First(
			String layoutSetPrototypeUuid,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByLayoutSetPrototypeUuid_First(
			layoutSetPrototypeUuid, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("layoutSetPrototypeUuid=");
		sb.append(layoutSetPrototypeUuid);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the first layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByLayoutSetPrototypeUuid_First(
		String layoutSetPrototypeUuid,
		OrderByComparator<LayoutSet> orderByComparator) {

		List<LayoutSet> list = findByLayoutSetPrototypeUuid(
			layoutSetPrototypeUuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByLayoutSetPrototypeUuid_Last(
			String layoutSetPrototypeUuid,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByLayoutSetPrototypeUuid_Last(
			layoutSetPrototypeUuid, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("layoutSetPrototypeUuid=");
		sb.append(layoutSetPrototypeUuid);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the last layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByLayoutSetPrototypeUuid_Last(
		String layoutSetPrototypeUuid,
		OrderByComparator<LayoutSet> orderByComparator) {

		int count = countByLayoutSetPrototypeUuid(layoutSetPrototypeUuid);

		if (count == 0) {
			return null;
		}

		List<LayoutSet> list = findByLayoutSetPrototypeUuid(
			layoutSetPrototypeUuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout sets before and after the current layout set in the ordered set where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetId the primary key of the current layout set
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet[] findByLayoutSetPrototypeUuid_PrevAndNext(
			long layoutSetId, String layoutSetPrototypeUuid,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		LayoutSet layoutSet = findByPrimaryKey(layoutSetId);

		Session session = null;

		try {
			session = openSession();

			LayoutSet[] array = new LayoutSetImpl[3];

			array[0] = getByLayoutSetPrototypeUuid_PrevAndNext(
				session, layoutSet, layoutSetPrototypeUuid, orderByComparator,
				true);

			array[1] = layoutSet;

			array[2] = getByLayoutSetPrototypeUuid_PrevAndNext(
				session, layoutSet, layoutSetPrototypeUuid, orderByComparator,
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

	protected LayoutSet getByLayoutSetPrototypeUuid_PrevAndNext(
		Session session, LayoutSet layoutSet, String layoutSetPrototypeUuid,
		OrderByComparator<LayoutSet> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

		boolean bindLayoutSetPrototypeUuid = false;

		if (layoutSetPrototypeUuid.isEmpty()) {
			sb.append(
				_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_3);
		}
		else {
			bindLayoutSetPrototypeUuid = true;

			sb.append(
				_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_2);
		}

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
			sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindLayoutSetPrototypeUuid) {
			queryPos.add(layoutSetPrototypeUuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(layoutSet)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutSet> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout sets where layoutSetPrototypeUuid = &#63; from the database.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 */
	@Override
	public void removeByLayoutSetPrototypeUuid(String layoutSetPrototypeUuid) {
		for (LayoutSet layoutSet :
				findByLayoutSetPrototypeUuid(
					layoutSetPrototypeUuid, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets where layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByLayoutSetPrototypeUuid(String layoutSetPrototypeUuid) {
		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		FinderPath finderPath = _finderPathCountByLayoutSetPrototypeUuid;

		Object[] finderArgs = new Object[] {layoutSetPrototypeUuid};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LAYOUTSET_WHERE);

			boolean bindLayoutSetPrototypeUuid = false;

			if (layoutSetPrototypeUuid.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_3);
			}
			else {
				bindLayoutSetPrototypeUuid = true;

				sb.append(
					_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindLayoutSetPrototypeUuid) {
					queryPos.add(layoutSetPrototypeUuid);
				}

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_2 =
			"layoutSet.layoutSetPrototypeUuid = ?";

	private static final String
		_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_LAYOUTSETPROTOTYPEUUID_3 =
			"(layoutSet.layoutSetPrototypeUuid IS NULL OR layoutSet.layoutSetPrototypeUuid = '')";

	private FinderPath
		_finderPathWithPaginationFindByLayoutSetPrototypeUuid_Head;
	private FinderPath
		_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid_Head;
	private FinderPath _finderPathCountByLayoutSetPrototypeUuid_Head;

	/**
	 * Returns all the layout sets where layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @return the matching layout sets
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid_Head(
		String layoutSetPrototypeUuid, boolean head) {

		return findByLayoutSetPrototypeUuid_Head(
			layoutSetPrototypeUuid, head, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the layout sets where layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid_Head(
		String layoutSetPrototypeUuid, boolean head, int start, int end) {

		return findByLayoutSetPrototypeUuid_Head(
			layoutSetPrototypeUuid, head, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets where layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid_Head(
		String layoutSetPrototypeUuid, boolean head, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator) {

		return findByLayoutSetPrototypeUuid_Head(
			layoutSetPrototypeUuid, head, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout sets where layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByLayoutSetPrototypeUuid_Head(
		String layoutSetPrototypeUuid, boolean head, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator,
		boolean useFinderCache) {

		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid_Head;
				finderArgs = new Object[] {layoutSetPrototypeUuid, head};
			}
		}
		else if (useFinderCache) {
			finderPath =
				_finderPathWithPaginationFindByLayoutSetPrototypeUuid_Head;
			finderArgs = new Object[] {
				layoutSetPrototypeUuid, head, start, end, orderByComparator
			};
		}

		List<LayoutSet> list = null;

		if (useFinderCache) {
			list = (List<LayoutSet>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutSet layoutSet : list) {
					if (!layoutSetPrototypeUuid.equals(
							layoutSet.getLayoutSetPrototypeUuid()) ||
						(head != layoutSet.isHead())) {

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

			sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

			boolean bindLayoutSetPrototypeUuid = false;

			if (layoutSetPrototypeUuid.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_HEAD_LAYOUTSETPROTOTYPEUUID_3);
			}
			else {
				bindLayoutSetPrototypeUuid = true;

				sb.append(
					_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_HEAD_LAYOUTSETPROTOTYPEUUID_2);
			}

			sb.append(_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_HEAD_HEAD_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindLayoutSetPrototypeUuid) {
					queryPos.add(layoutSetPrototypeUuid);
				}

				queryPos.add(head);

				list = (List<LayoutSet>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
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
	 * Returns the first layout set in the ordered set where layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByLayoutSetPrototypeUuid_Head_First(
			String layoutSetPrototypeUuid, boolean head,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByLayoutSetPrototypeUuid_Head_First(
			layoutSetPrototypeUuid, head, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("layoutSetPrototypeUuid=");
		sb.append(layoutSetPrototypeUuid);

		sb.append(", head=");
		sb.append(head);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the first layout set in the ordered set where layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByLayoutSetPrototypeUuid_Head_First(
		String layoutSetPrototypeUuid, boolean head,
		OrderByComparator<LayoutSet> orderByComparator) {

		List<LayoutSet> list = findByLayoutSetPrototypeUuid_Head(
			layoutSetPrototypeUuid, head, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout set in the ordered set where layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByLayoutSetPrototypeUuid_Head_Last(
			String layoutSetPrototypeUuid, boolean head,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByLayoutSetPrototypeUuid_Head_Last(
			layoutSetPrototypeUuid, head, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("layoutSetPrototypeUuid=");
		sb.append(layoutSetPrototypeUuid);

		sb.append(", head=");
		sb.append(head);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the last layout set in the ordered set where layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByLayoutSetPrototypeUuid_Head_Last(
		String layoutSetPrototypeUuid, boolean head,
		OrderByComparator<LayoutSet> orderByComparator) {

		int count = countByLayoutSetPrototypeUuid_Head(
			layoutSetPrototypeUuid, head);

		if (count == 0) {
			return null;
		}

		List<LayoutSet> list = findByLayoutSetPrototypeUuid_Head(
			layoutSetPrototypeUuid, head, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout sets before and after the current layout set in the ordered set where layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * @param layoutSetId the primary key of the current layout set
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet[] findByLayoutSetPrototypeUuid_Head_PrevAndNext(
			long layoutSetId, String layoutSetPrototypeUuid, boolean head,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		LayoutSet layoutSet = findByPrimaryKey(layoutSetId);

		Session session = null;

		try {
			session = openSession();

			LayoutSet[] array = new LayoutSetImpl[3];

			array[0] = getByLayoutSetPrototypeUuid_Head_PrevAndNext(
				session, layoutSet, layoutSetPrototypeUuid, head,
				orderByComparator, true);

			array[1] = layoutSet;

			array[2] = getByLayoutSetPrototypeUuid_Head_PrevAndNext(
				session, layoutSet, layoutSetPrototypeUuid, head,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutSet getByLayoutSetPrototypeUuid_Head_PrevAndNext(
		Session session, LayoutSet layoutSet, String layoutSetPrototypeUuid,
		boolean head, OrderByComparator<LayoutSet> orderByComparator,
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

		sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

		boolean bindLayoutSetPrototypeUuid = false;

		if (layoutSetPrototypeUuid.isEmpty()) {
			sb.append(
				_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_HEAD_LAYOUTSETPROTOTYPEUUID_3);
		}
		else {
			bindLayoutSetPrototypeUuid = true;

			sb.append(
				_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_HEAD_LAYOUTSETPROTOTYPEUUID_2);
		}

		sb.append(_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_HEAD_HEAD_2);

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
			sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindLayoutSetPrototypeUuid) {
			queryPos.add(layoutSetPrototypeUuid);
		}

		queryPos.add(head);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(layoutSet)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutSet> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout sets where layoutSetPrototypeUuid = &#63; and head = &#63; from the database.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 */
	@Override
	public void removeByLayoutSetPrototypeUuid_Head(
		String layoutSetPrototypeUuid, boolean head) {

		for (LayoutSet layoutSet :
				findByLayoutSetPrototypeUuid_Head(
					layoutSetPrototypeUuid, head, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets where layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByLayoutSetPrototypeUuid_Head(
		String layoutSetPrototypeUuid, boolean head) {

		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		FinderPath finderPath = _finderPathCountByLayoutSetPrototypeUuid_Head;

		Object[] finderArgs = new Object[] {layoutSetPrototypeUuid, head};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LAYOUTSET_WHERE);

			boolean bindLayoutSetPrototypeUuid = false;

			if (layoutSetPrototypeUuid.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_HEAD_LAYOUTSETPROTOTYPEUUID_3);
			}
			else {
				bindLayoutSetPrototypeUuid = true;

				sb.append(
					_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_HEAD_LAYOUTSETPROTOTYPEUUID_2);
			}

			sb.append(_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_HEAD_HEAD_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindLayoutSetPrototypeUuid) {
					queryPos.add(layoutSetPrototypeUuid);
				}

				queryPos.add(head);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_HEAD_LAYOUTSETPROTOTYPEUUID_2 =
			"layoutSet.layoutSetPrototypeUuid = ? AND ";

	private static final String
		_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_HEAD_LAYOUTSETPROTOTYPEUUID_3 =
			"(layoutSet.layoutSetPrototypeUuid IS NULL OR layoutSet.layoutSetPrototypeUuid = '') AND ";

	private static final String
		_FINDER_COLUMN_LAYOUTSETPROTOTYPEUUID_HEAD_HEAD_2 =
			"layoutSet.head = ?";

	private FinderPath _finderPathWithPaginationFindByG_P;
	private FinderPath _finderPathWithoutPaginationFindByG_P;
	private FinderPath _finderPathCountByG_P;

	/**
	 * Returns all the layout sets where groupId = &#63; and privateLayout = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @return the matching layout sets
	 */
	@Override
	public List<LayoutSet> findByG_P(long groupId, boolean privateLayout) {
		return findByG_P(
			groupId, privateLayout, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets where groupId = &#63; and privateLayout = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByG_P(
		long groupId, boolean privateLayout, int start, int end) {

		return findByG_P(groupId, privateLayout, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets where groupId = &#63; and privateLayout = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByG_P(
		long groupId, boolean privateLayout, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator) {

		return findByG_P(
			groupId, privateLayout, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout sets where groupId = &#63; and privateLayout = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByG_P(
		long groupId, boolean privateLayout, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_P;
				finderArgs = new Object[] {groupId, privateLayout};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_P;
			finderArgs = new Object[] {
				groupId, privateLayout, start, end, orderByComparator
			};
		}

		List<LayoutSet> list = null;

		if (useFinderCache) {
			list = (List<LayoutSet>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutSet layoutSet : list) {
					if ((groupId != layoutSet.getGroupId()) ||
						(privateLayout != layoutSet.isPrivateLayout())) {

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

			sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_G_P_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_P_PRIVATELAYOUT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(privateLayout);

				list = (List<LayoutSet>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
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
	 * Returns the first layout set in the ordered set where groupId = &#63; and privateLayout = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByG_P_First(
			long groupId, boolean privateLayout,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByG_P_First(
			groupId, privateLayout, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", privateLayout=");
		sb.append(privateLayout);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the first layout set in the ordered set where groupId = &#63; and privateLayout = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByG_P_First(
		long groupId, boolean privateLayout,
		OrderByComparator<LayoutSet> orderByComparator) {

		List<LayoutSet> list = findByG_P(
			groupId, privateLayout, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout set in the ordered set where groupId = &#63; and privateLayout = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByG_P_Last(
			long groupId, boolean privateLayout,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByG_P_Last(
			groupId, privateLayout, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", privateLayout=");
		sb.append(privateLayout);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the last layout set in the ordered set where groupId = &#63; and privateLayout = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByG_P_Last(
		long groupId, boolean privateLayout,
		OrderByComparator<LayoutSet> orderByComparator) {

		int count = countByG_P(groupId, privateLayout);

		if (count == 0) {
			return null;
		}

		List<LayoutSet> list = findByG_P(
			groupId, privateLayout, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout sets before and after the current layout set in the ordered set where groupId = &#63; and privateLayout = &#63;.
	 *
	 * @param layoutSetId the primary key of the current layout set
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet[] findByG_P_PrevAndNext(
			long layoutSetId, long groupId, boolean privateLayout,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = findByPrimaryKey(layoutSetId);

		Session session = null;

		try {
			session = openSession();

			LayoutSet[] array = new LayoutSetImpl[3];

			array[0] = getByG_P_PrevAndNext(
				session, layoutSet, groupId, privateLayout, orderByComparator,
				true);

			array[1] = layoutSet;

			array[2] = getByG_P_PrevAndNext(
				session, layoutSet, groupId, privateLayout, orderByComparator,
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

	protected LayoutSet getByG_P_PrevAndNext(
		Session session, LayoutSet layoutSet, long groupId,
		boolean privateLayout, OrderByComparator<LayoutSet> orderByComparator,
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

		sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

		sb.append(_FINDER_COLUMN_G_P_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_P_PRIVATELAYOUT_2);

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
			sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(privateLayout);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(layoutSet)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutSet> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout sets where groupId = &#63; and privateLayout = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 */
	@Override
	public void removeByG_P(long groupId, boolean privateLayout) {
		for (LayoutSet layoutSet :
				findByG_P(
					groupId, privateLayout, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets where groupId = &#63; and privateLayout = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByG_P(long groupId, boolean privateLayout) {
		FinderPath finderPath = _finderPathCountByG_P;

		Object[] finderArgs = new Object[] {groupId, privateLayout};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_G_P_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_P_PRIVATELAYOUT_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(privateLayout);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_P_GROUPID_2 =
		"layoutSet.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_P_PRIVATELAYOUT_2 =
		"layoutSet.privateLayout = ?";

	private FinderPath _finderPathFetchByG_P_Head;
	private FinderPath _finderPathCountByG_P_Head;

	/**
	 * Returns the layout set where groupId = &#63; and privateLayout = &#63; and head = &#63; or throws a <code>NoSuchLayoutSetException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param head the head
	 * @return the matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByG_P_Head(
			long groupId, boolean privateLayout, boolean head)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByG_P_Head(groupId, privateLayout, head);

		if (layoutSet == null) {
			StringBundler sb = new StringBundler(8);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("groupId=");
			sb.append(groupId);

			sb.append(", privateLayout=");
			sb.append(privateLayout);

			sb.append(", head=");
			sb.append(head);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchLayoutSetException(sb.toString());
		}

		return layoutSet;
	}

	/**
	 * Returns the layout set where groupId = &#63; and privateLayout = &#63; and head = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param head the head
	 * @return the matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByG_P_Head(
		long groupId, boolean privateLayout, boolean head) {

		return fetchByG_P_Head(groupId, privateLayout, head, true);
	}

	/**
	 * Returns the layout set where groupId = &#63; and privateLayout = &#63; and head = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param head the head
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByG_P_Head(
		long groupId, boolean privateLayout, boolean head,
		boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {groupId, privateLayout, head};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByG_P_Head, finderArgs, this);
		}

		if (result instanceof LayoutSet) {
			LayoutSet layoutSet = (LayoutSet)result;

			if ((groupId != layoutSet.getGroupId()) ||
				(privateLayout != layoutSet.isPrivateLayout()) ||
				(head != layoutSet.isHead())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_G_P_HEAD_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_P_HEAD_PRIVATELAYOUT_2);

			sb.append(_FINDER_COLUMN_G_P_HEAD_HEAD_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(privateLayout);

				queryPos.add(head);

				List<LayoutSet> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByG_P_Head, finderArgs, list);
					}
				}
				else {
					LayoutSet layoutSet = list.get(0);

					result = layoutSet;

					cacheResult(layoutSet);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(
						_finderPathFetchByG_P_Head, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (LayoutSet)result;
		}
	}

	/**
	 * Removes the layout set where groupId = &#63; and privateLayout = &#63; and head = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param head the head
	 * @return the layout set that was removed
	 */
	@Override
	public LayoutSet removeByG_P_Head(
			long groupId, boolean privateLayout, boolean head)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = findByG_P_Head(groupId, privateLayout, head);

		return remove(layoutSet);
	}

	/**
	 * Returns the number of layout sets where groupId = &#63; and privateLayout = &#63; and head = &#63;.
	 *
	 * @param groupId the group ID
	 * @param privateLayout the private layout
	 * @param head the head
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByG_P_Head(
		long groupId, boolean privateLayout, boolean head) {

		FinderPath finderPath = _finderPathCountByG_P_Head;

		Object[] finderArgs = new Object[] {groupId, privateLayout, head};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_G_P_HEAD_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_P_HEAD_PRIVATELAYOUT_2);

			sb.append(_FINDER_COLUMN_G_P_HEAD_HEAD_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(privateLayout);

				queryPos.add(head);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_P_HEAD_GROUPID_2 =
		"layoutSet.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_P_HEAD_PRIVATELAYOUT_2 =
		"layoutSet.privateLayout = ? AND ";

	private static final String _FINDER_COLUMN_G_P_HEAD_HEAD_2 =
		"layoutSet.head = ?";

	private FinderPath _finderPathWithPaginationFindByC_L;
	private FinderPath _finderPathWithoutPaginationFindByC_L;
	private FinderPath _finderPathCountByC_L;

	/**
	 * Returns all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @return the matching layout sets
	 */
	@Override
	public List<LayoutSet> findByC_L(
		long companyId, String layoutSetPrototypeUuid) {

		return findByC_L(
			companyId, layoutSetPrototypeUuid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByC_L(
		long companyId, String layoutSetPrototypeUuid, int start, int end) {

		return findByC_L(companyId, layoutSetPrototypeUuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByC_L(
		long companyId, String layoutSetPrototypeUuid, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator) {

		return findByC_L(
			companyId, layoutSetPrototypeUuid, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByC_L(
		long companyId, String layoutSetPrototypeUuid, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator,
		boolean useFinderCache) {

		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_L;
				finderArgs = new Object[] {companyId, layoutSetPrototypeUuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_L;
			finderArgs = new Object[] {
				companyId, layoutSetPrototypeUuid, start, end, orderByComparator
			};
		}

		List<LayoutSet> list = null;

		if (useFinderCache) {
			list = (List<LayoutSet>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutSet layoutSet : list) {
					if ((companyId != layoutSet.getCompanyId()) ||
						!layoutSetPrototypeUuid.equals(
							layoutSet.getLayoutSetPrototypeUuid())) {

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

			sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_C_L_COMPANYID_2);

			boolean bindLayoutSetPrototypeUuid = false;

			if (layoutSetPrototypeUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_3);
			}
			else {
				bindLayoutSetPrototypeUuid = true;

				sb.append(_FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindLayoutSetPrototypeUuid) {
					queryPos.add(layoutSetPrototypeUuid);
				}

				list = (List<LayoutSet>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
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
	 * Returns the first layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByC_L_First(
			long companyId, String layoutSetPrototypeUuid,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByC_L_First(
			companyId, layoutSetPrototypeUuid, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", layoutSetPrototypeUuid=");
		sb.append(layoutSetPrototypeUuid);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the first layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByC_L_First(
		long companyId, String layoutSetPrototypeUuid,
		OrderByComparator<LayoutSet> orderByComparator) {

		List<LayoutSet> list = findByC_L(
			companyId, layoutSetPrototypeUuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByC_L_Last(
			long companyId, String layoutSetPrototypeUuid,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByC_L_Last(
			companyId, layoutSetPrototypeUuid, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", layoutSetPrototypeUuid=");
		sb.append(layoutSetPrototypeUuid);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the last layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByC_L_Last(
		long companyId, String layoutSetPrototypeUuid,
		OrderByComparator<LayoutSet> orderByComparator) {

		int count = countByC_L(companyId, layoutSetPrototypeUuid);

		if (count == 0) {
			return null;
		}

		List<LayoutSet> list = findByC_L(
			companyId, layoutSetPrototypeUuid, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout sets before and after the current layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * @param layoutSetId the primary key of the current layout set
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet[] findByC_L_PrevAndNext(
			long layoutSetId, long companyId, String layoutSetPrototypeUuid,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		LayoutSet layoutSet = findByPrimaryKey(layoutSetId);

		Session session = null;

		try {
			session = openSession();

			LayoutSet[] array = new LayoutSetImpl[3];

			array[0] = getByC_L_PrevAndNext(
				session, layoutSet, companyId, layoutSetPrototypeUuid,
				orderByComparator, true);

			array[1] = layoutSet;

			array[2] = getByC_L_PrevAndNext(
				session, layoutSet, companyId, layoutSetPrototypeUuid,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutSet getByC_L_PrevAndNext(
		Session session, LayoutSet layoutSet, long companyId,
		String layoutSetPrototypeUuid,
		OrderByComparator<LayoutSet> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

		sb.append(_FINDER_COLUMN_C_L_COMPANYID_2);

		boolean bindLayoutSetPrototypeUuid = false;

		if (layoutSetPrototypeUuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_3);
		}
		else {
			bindLayoutSetPrototypeUuid = true;

			sb.append(_FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_2);
		}

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
			sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (bindLayoutSetPrototypeUuid) {
			queryPos.add(layoutSetPrototypeUuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(layoutSet)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutSet> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 */
	@Override
	public void removeByC_L(long companyId, String layoutSetPrototypeUuid) {
		for (LayoutSet layoutSet :
				findByC_L(
					companyId, layoutSetPrototypeUuid, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByC_L(long companyId, String layoutSetPrototypeUuid) {
		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		FinderPath finderPath = _finderPathCountByC_L;

		Object[] finderArgs = new Object[] {companyId, layoutSetPrototypeUuid};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_C_L_COMPANYID_2);

			boolean bindLayoutSetPrototypeUuid = false;

			if (layoutSetPrototypeUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_3);
			}
			else {
				bindLayoutSetPrototypeUuid = true;

				sb.append(_FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindLayoutSetPrototypeUuid) {
					queryPos.add(layoutSetPrototypeUuid);
				}

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_L_COMPANYID_2 =
		"layoutSet.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_2 =
		"layoutSet.layoutSetPrototypeUuid = ?";

	private static final String _FINDER_COLUMN_C_L_LAYOUTSETPROTOTYPEUUID_3 =
		"(layoutSet.layoutSetPrototypeUuid IS NULL OR layoutSet.layoutSetPrototypeUuid = '')";

	private FinderPath _finderPathWithPaginationFindByC_L_Head;
	private FinderPath _finderPathWithoutPaginationFindByC_L_Head;
	private FinderPath _finderPathCountByC_L_Head;

	/**
	 * Returns all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @return the matching layout sets
	 */
	@Override
	public List<LayoutSet> findByC_L_Head(
		long companyId, String layoutSetPrototypeUuid, boolean head) {

		return findByC_L_Head(
			companyId, layoutSetPrototypeUuid, head, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByC_L_Head(
		long companyId, String layoutSetPrototypeUuid, boolean head, int start,
		int end) {

		return findByC_L_Head(
			companyId, layoutSetPrototypeUuid, head, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByC_L_Head(
		long companyId, String layoutSetPrototypeUuid, boolean head, int start,
		int end, OrderByComparator<LayoutSet> orderByComparator) {

		return findByC_L_Head(
			companyId, layoutSetPrototypeUuid, head, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByC_L_Head(
		long companyId, String layoutSetPrototypeUuid, boolean head, int start,
		int end, OrderByComparator<LayoutSet> orderByComparator,
		boolean useFinderCache) {

		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_L_Head;
				finderArgs = new Object[] {
					companyId, layoutSetPrototypeUuid, head
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_L_Head;
			finderArgs = new Object[] {
				companyId, layoutSetPrototypeUuid, head, start, end,
				orderByComparator
			};
		}

		List<LayoutSet> list = null;

		if (useFinderCache) {
			list = (List<LayoutSet>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutSet layoutSet : list) {
					if ((companyId != layoutSet.getCompanyId()) ||
						!layoutSetPrototypeUuid.equals(
							layoutSet.getLayoutSetPrototypeUuid()) ||
						(head != layoutSet.isHead())) {

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
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(5);
			}

			sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_C_L_HEAD_COMPANYID_2);

			boolean bindLayoutSetPrototypeUuid = false;

			if (layoutSetPrototypeUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_L_HEAD_LAYOUTSETPROTOTYPEUUID_3);
			}
			else {
				bindLayoutSetPrototypeUuid = true;

				sb.append(_FINDER_COLUMN_C_L_HEAD_LAYOUTSETPROTOTYPEUUID_2);
			}

			sb.append(_FINDER_COLUMN_C_L_HEAD_HEAD_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindLayoutSetPrototypeUuid) {
					queryPos.add(layoutSetPrototypeUuid);
				}

				queryPos.add(head);

				list = (List<LayoutSet>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
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
	 * Returns the first layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByC_L_Head_First(
			long companyId, String layoutSetPrototypeUuid, boolean head,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByC_L_Head_First(
			companyId, layoutSetPrototypeUuid, head, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", layoutSetPrototypeUuid=");
		sb.append(layoutSetPrototypeUuid);

		sb.append(", head=");
		sb.append(head);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the first layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByC_L_Head_First(
		long companyId, String layoutSetPrototypeUuid, boolean head,
		OrderByComparator<LayoutSet> orderByComparator) {

		List<LayoutSet> list = findByC_L_Head(
			companyId, layoutSetPrototypeUuid, head, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByC_L_Head_Last(
			long companyId, String layoutSetPrototypeUuid, boolean head,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByC_L_Head_Last(
			companyId, layoutSetPrototypeUuid, head, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", layoutSetPrototypeUuid=");
		sb.append(layoutSetPrototypeUuid);

		sb.append(", head=");
		sb.append(head);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the last layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByC_L_Head_Last(
		long companyId, String layoutSetPrototypeUuid, boolean head,
		OrderByComparator<LayoutSet> orderByComparator) {

		int count = countByC_L_Head(companyId, layoutSetPrototypeUuid, head);

		if (count == 0) {
			return null;
		}

		List<LayoutSet> list = findByC_L_Head(
			companyId, layoutSetPrototypeUuid, head, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout sets before and after the current layout set in the ordered set where companyId = &#63; and layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * @param layoutSetId the primary key of the current layout set
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet[] findByC_L_Head_PrevAndNext(
			long layoutSetId, long companyId, String layoutSetPrototypeUuid,
			boolean head, OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		LayoutSet layoutSet = findByPrimaryKey(layoutSetId);

		Session session = null;

		try {
			session = openSession();

			LayoutSet[] array = new LayoutSetImpl[3];

			array[0] = getByC_L_Head_PrevAndNext(
				session, layoutSet, companyId, layoutSetPrototypeUuid, head,
				orderByComparator, true);

			array[1] = layoutSet;

			array[2] = getByC_L_Head_PrevAndNext(
				session, layoutSet, companyId, layoutSetPrototypeUuid, head,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutSet getByC_L_Head_PrevAndNext(
		Session session, LayoutSet layoutSet, long companyId,
		String layoutSetPrototypeUuid, boolean head,
		OrderByComparator<LayoutSet> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

		sb.append(_FINDER_COLUMN_C_L_HEAD_COMPANYID_2);

		boolean bindLayoutSetPrototypeUuid = false;

		if (layoutSetPrototypeUuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_L_HEAD_LAYOUTSETPROTOTYPEUUID_3);
		}
		else {
			bindLayoutSetPrototypeUuid = true;

			sb.append(_FINDER_COLUMN_C_L_HEAD_LAYOUTSETPROTOTYPEUUID_2);
		}

		sb.append(_FINDER_COLUMN_C_L_HEAD_HEAD_2);

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
			sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(companyId);

		if (bindLayoutSetPrototypeUuid) {
			queryPos.add(layoutSetPrototypeUuid);
		}

		queryPos.add(head);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(layoutSet)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutSet> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63; and head = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 */
	@Override
	public void removeByC_L_Head(
		long companyId, String layoutSetPrototypeUuid, boolean head) {

		for (LayoutSet layoutSet :
				findByC_L_Head(
					companyId, layoutSetPrototypeUuid, head, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets where companyId = &#63; and layoutSetPrototypeUuid = &#63; and head = &#63;.
	 *
	 * @param companyId the company ID
	 * @param layoutSetPrototypeUuid the layout set prototype uuid
	 * @param head the head
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByC_L_Head(
		long companyId, String layoutSetPrototypeUuid, boolean head) {

		layoutSetPrototypeUuid = Objects.toString(layoutSetPrototypeUuid, "");

		FinderPath finderPath = _finderPathCountByC_L_Head;

		Object[] finderArgs = new Object[] {
			companyId, layoutSetPrototypeUuid, head
		};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_C_L_HEAD_COMPANYID_2);

			boolean bindLayoutSetPrototypeUuid = false;

			if (layoutSetPrototypeUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_L_HEAD_LAYOUTSETPROTOTYPEUUID_3);
			}
			else {
				bindLayoutSetPrototypeUuid = true;

				sb.append(_FINDER_COLUMN_C_L_HEAD_LAYOUTSETPROTOTYPEUUID_2);
			}

			sb.append(_FINDER_COLUMN_C_L_HEAD_HEAD_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindLayoutSetPrototypeUuid) {
					queryPos.add(layoutSetPrototypeUuid);
				}

				queryPos.add(head);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_L_HEAD_COMPANYID_2 =
		"layoutSet.companyId = ? AND ";

	private static final String
		_FINDER_COLUMN_C_L_HEAD_LAYOUTSETPROTOTYPEUUID_2 =
			"layoutSet.layoutSetPrototypeUuid = ? AND ";

	private static final String
		_FINDER_COLUMN_C_L_HEAD_LAYOUTSETPROTOTYPEUUID_3 =
			"(layoutSet.layoutSetPrototypeUuid IS NULL OR layoutSet.layoutSetPrototypeUuid = '') AND ";

	private static final String _FINDER_COLUMN_C_L_HEAD_HEAD_2 =
		"layoutSet.head = ?";

	private FinderPath _finderPathWithPaginationFindByP_L;
	private FinderPath _finderPathWithoutPaginationFindByP_L;
	private FinderPath _finderPathCountByP_L;

	/**
	 * Returns all the layout sets where privateLayout = &#63; and logoId = &#63;.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @return the matching layout sets
	 */
	@Override
	public List<LayoutSet> findByP_L(boolean privateLayout, long logoId) {
		return findByP_L(
			privateLayout, logoId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets where privateLayout = &#63; and logoId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByP_L(
		boolean privateLayout, long logoId, int start, int end) {

		return findByP_L(privateLayout, logoId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets where privateLayout = &#63; and logoId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByP_L(
		boolean privateLayout, long logoId, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator) {

		return findByP_L(
			privateLayout, logoId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout sets where privateLayout = &#63; and logoId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching layout sets
	 */
	@Override
	public List<LayoutSet> findByP_L(
		boolean privateLayout, long logoId, int start, int end,
		OrderByComparator<LayoutSet> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByP_L;
				finderArgs = new Object[] {privateLayout, logoId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByP_L;
			finderArgs = new Object[] {
				privateLayout, logoId, start, end, orderByComparator
			};
		}

		List<LayoutSet> list = null;

		if (useFinderCache) {
			list = (List<LayoutSet>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LayoutSet layoutSet : list) {
					if ((privateLayout != layoutSet.isPrivateLayout()) ||
						(logoId != layoutSet.getLogoId())) {

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

			sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_P_L_PRIVATELAYOUT_2);

			sb.append(_FINDER_COLUMN_P_L_LOGOID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(privateLayout);

				queryPos.add(logoId);

				list = (List<LayoutSet>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
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
	 * Returns the first layout set in the ordered set where privateLayout = &#63; and logoId = &#63;.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByP_L_First(
			boolean privateLayout, long logoId,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByP_L_First(
			privateLayout, logoId, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("privateLayout=");
		sb.append(privateLayout);

		sb.append(", logoId=");
		sb.append(logoId);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the first layout set in the ordered set where privateLayout = &#63; and logoId = &#63;.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByP_L_First(
		boolean privateLayout, long logoId,
		OrderByComparator<LayoutSet> orderByComparator) {

		List<LayoutSet> list = findByP_L(
			privateLayout, logoId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last layout set in the ordered set where privateLayout = &#63; and logoId = &#63;.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByP_L_Last(
			boolean privateLayout, long logoId,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByP_L_Last(
			privateLayout, logoId, orderByComparator);

		if (layoutSet != null) {
			return layoutSet;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("privateLayout=");
		sb.append(privateLayout);

		sb.append(", logoId=");
		sb.append(logoId);

		sb.append("}");

		throw new NoSuchLayoutSetException(sb.toString());
	}

	/**
	 * Returns the last layout set in the ordered set where privateLayout = &#63; and logoId = &#63;.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByP_L_Last(
		boolean privateLayout, long logoId,
		OrderByComparator<LayoutSet> orderByComparator) {

		int count = countByP_L(privateLayout, logoId);

		if (count == 0) {
			return null;
		}

		List<LayoutSet> list = findByP_L(
			privateLayout, logoId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the layout sets before and after the current layout set in the ordered set where privateLayout = &#63; and logoId = &#63;.
	 *
	 * @param layoutSetId the primary key of the current layout set
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet[] findByP_L_PrevAndNext(
			long layoutSetId, boolean privateLayout, long logoId,
			OrderByComparator<LayoutSet> orderByComparator)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = findByPrimaryKey(layoutSetId);

		Session session = null;

		try {
			session = openSession();

			LayoutSet[] array = new LayoutSetImpl[3];

			array[0] = getByP_L_PrevAndNext(
				session, layoutSet, privateLayout, logoId, orderByComparator,
				true);

			array[1] = layoutSet;

			array[2] = getByP_L_PrevAndNext(
				session, layoutSet, privateLayout, logoId, orderByComparator,
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

	protected LayoutSet getByP_L_PrevAndNext(
		Session session, LayoutSet layoutSet, boolean privateLayout,
		long logoId, OrderByComparator<LayoutSet> orderByComparator,
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

		sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

		sb.append(_FINDER_COLUMN_P_L_PRIVATELAYOUT_2);

		sb.append(_FINDER_COLUMN_P_L_LOGOID_2);

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
			sb.append(LayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(privateLayout);

		queryPos.add(logoId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(layoutSet)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LayoutSet> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the layout sets where privateLayout = &#63; and logoId = &#63; from the database.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 */
	@Override
	public void removeByP_L(boolean privateLayout, long logoId) {
		for (LayoutSet layoutSet :
				findByP_L(
					privateLayout, logoId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets where privateLayout = &#63; and logoId = &#63;.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByP_L(boolean privateLayout, long logoId) {
		FinderPath finderPath = _finderPathCountByP_L;

		Object[] finderArgs = new Object[] {privateLayout, logoId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_P_L_PRIVATELAYOUT_2);

			sb.append(_FINDER_COLUMN_P_L_LOGOID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(privateLayout);

				queryPos.add(logoId);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_P_L_PRIVATELAYOUT_2 =
		"layoutSet.privateLayout = ? AND ";

	private static final String _FINDER_COLUMN_P_L_LOGOID_2 =
		"layoutSet.logoId = ?";

	private FinderPath _finderPathFetchByP_L_Head;
	private FinderPath _finderPathCountByP_L_Head;

	/**
	 * Returns the layout set where privateLayout = &#63; and logoId = &#63; and head = &#63; or throws a <code>NoSuchLayoutSetException</code> if it could not be found.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @param head the head
	 * @return the matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByP_L_Head(
			boolean privateLayout, long logoId, boolean head)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByP_L_Head(privateLayout, logoId, head);

		if (layoutSet == null) {
			StringBundler sb = new StringBundler(8);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("privateLayout=");
			sb.append(privateLayout);

			sb.append(", logoId=");
			sb.append(logoId);

			sb.append(", head=");
			sb.append(head);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchLayoutSetException(sb.toString());
		}

		return layoutSet;
	}

	/**
	 * Returns the layout set where privateLayout = &#63; and logoId = &#63; and head = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @param head the head
	 * @return the matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByP_L_Head(
		boolean privateLayout, long logoId, boolean head) {

		return fetchByP_L_Head(privateLayout, logoId, head, true);
	}

	/**
	 * Returns the layout set where privateLayout = &#63; and logoId = &#63; and head = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @param head the head
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByP_L_Head(
		boolean privateLayout, long logoId, boolean head,
		boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {privateLayout, logoId, head};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByP_L_Head, finderArgs, this);
		}

		if (result instanceof LayoutSet) {
			LayoutSet layoutSet = (LayoutSet)result;

			if ((privateLayout != layoutSet.isPrivateLayout()) ||
				(logoId != layoutSet.getLogoId()) ||
				(head != layoutSet.isHead())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_P_L_HEAD_PRIVATELAYOUT_2);

			sb.append(_FINDER_COLUMN_P_L_HEAD_LOGOID_2);

			sb.append(_FINDER_COLUMN_P_L_HEAD_HEAD_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(privateLayout);

				queryPos.add(logoId);

				queryPos.add(head);

				List<LayoutSet> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByP_L_Head, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									privateLayout, logoId, head
								};
							}

							_log.warn(
								"LayoutSetPersistenceImpl.fetchByP_L_Head(boolean, long, boolean, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					LayoutSet layoutSet = list.get(0);

					result = layoutSet;

					cacheResult(layoutSet);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(
						_finderPathFetchByP_L_Head, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (LayoutSet)result;
		}
	}

	/**
	 * Removes the layout set where privateLayout = &#63; and logoId = &#63; and head = &#63; from the database.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @param head the head
	 * @return the layout set that was removed
	 */
	@Override
	public LayoutSet removeByP_L_Head(
			boolean privateLayout, long logoId, boolean head)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = findByP_L_Head(privateLayout, logoId, head);

		return remove(layoutSet);
	}

	/**
	 * Returns the number of layout sets where privateLayout = &#63; and logoId = &#63; and head = &#63;.
	 *
	 * @param privateLayout the private layout
	 * @param logoId the logo ID
	 * @param head the head
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByP_L_Head(
		boolean privateLayout, long logoId, boolean head) {

		FinderPath finderPath = _finderPathCountByP_L_Head;

		Object[] finderArgs = new Object[] {privateLayout, logoId, head};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_P_L_HEAD_PRIVATELAYOUT_2);

			sb.append(_FINDER_COLUMN_P_L_HEAD_LOGOID_2);

			sb.append(_FINDER_COLUMN_P_L_HEAD_HEAD_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(privateLayout);

				queryPos.add(logoId);

				queryPos.add(head);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_P_L_HEAD_PRIVATELAYOUT_2 =
		"layoutSet.privateLayout = ? AND ";

	private static final String _FINDER_COLUMN_P_L_HEAD_LOGOID_2 =
		"layoutSet.logoId = ? AND ";

	private static final String _FINDER_COLUMN_P_L_HEAD_HEAD_2 =
		"layoutSet.head = ?";

	private FinderPath _finderPathFetchByHeadId;
	private FinderPath _finderPathCountByHeadId;

	/**
	 * Returns the layout set where headId = &#63; or throws a <code>NoSuchLayoutSetException</code> if it could not be found.
	 *
	 * @param headId the head ID
	 * @return the matching layout set
	 * @throws NoSuchLayoutSetException if a matching layout set could not be found
	 */
	@Override
	public LayoutSet findByHeadId(long headId) throws NoSuchLayoutSetException {
		LayoutSet layoutSet = fetchByHeadId(headId);

		if (layoutSet == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("headId=");
			sb.append(headId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchLayoutSetException(sb.toString());
		}

		return layoutSet;
	}

	/**
	 * Returns the layout set where headId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param headId the head ID
	 * @return the matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByHeadId(long headId) {
		return fetchByHeadId(headId, true);
	}

	/**
	 * Returns the layout set where headId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param headId the head ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching layout set, or <code>null</code> if a matching layout set could not be found
	 */
	@Override
	public LayoutSet fetchByHeadId(long headId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {headId};
		}

		Object result = null;

		if (useFinderCache) {
			result = FinderCacheUtil.getResult(
				_finderPathFetchByHeadId, finderArgs, this);
		}

		if (result instanceof LayoutSet) {
			LayoutSet layoutSet = (LayoutSet)result;

			if (headId != layoutSet.getHeadId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_HEADID_HEADID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(headId);

				List<LayoutSet> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						FinderCacheUtil.putResult(
							_finderPathFetchByHeadId, finderArgs, list);
					}
				}
				else {
					LayoutSet layoutSet = list.get(0);

					result = layoutSet;

					cacheResult(layoutSet);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(
						_finderPathFetchByHeadId, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (LayoutSet)result;
		}
	}

	/**
	 * Removes the layout set where headId = &#63; from the database.
	 *
	 * @param headId the head ID
	 * @return the layout set that was removed
	 */
	@Override
	public LayoutSet removeByHeadId(long headId)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = findByHeadId(headId);

		return remove(layoutSet);
	}

	/**
	 * Returns the number of layout sets where headId = &#63;.
	 *
	 * @param headId the head ID
	 * @return the number of matching layout sets
	 */
	@Override
	public int countByHeadId(long headId) {
		FinderPath finderPath = _finderPathCountByHeadId;

		Object[] finderArgs = new Object[] {headId};

		Long count = (Long)FinderCacheUtil.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LAYOUTSET_WHERE);

			sb.append(_FINDER_COLUMN_HEADID_HEADID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(headId);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_HEADID_HEADID_2 =
		"layoutSet.headId = ?";

	public LayoutSetPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("settings", "settings_");

		setDBColumnNames(dbColumnNames);

		setModelClass(LayoutSet.class);

		setModelImplClass(LayoutSetImpl.class);
		setModelPKClass(long.class);
		setEntityCacheEnabled(LayoutSetModelImpl.ENTITY_CACHE_ENABLED);
	}

	/**
	 * Caches the layout set in the entity cache if it is enabled.
	 *
	 * @param layoutSet the layout set
	 */
	@Override
	public void cacheResult(LayoutSet layoutSet) {
		EntityCacheUtil.putResult(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED, LayoutSetImpl.class,
			layoutSet.getPrimaryKey(), layoutSet);

		FinderCacheUtil.putResult(
			_finderPathFetchByG_P_Head,
			new Object[] {
				layoutSet.getGroupId(), layoutSet.isPrivateLayout(),
				layoutSet.isHead()
			},
			layoutSet);

		FinderCacheUtil.putResult(
			_finderPathFetchByP_L_Head,
			new Object[] {
				layoutSet.isPrivateLayout(), layoutSet.getLogoId(),
				layoutSet.isHead()
			},
			layoutSet);

		FinderCacheUtil.putResult(
			_finderPathFetchByHeadId, new Object[] {layoutSet.getHeadId()},
			layoutSet);

		layoutSet.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the layout sets in the entity cache if it is enabled.
	 *
	 * @param layoutSets the layout sets
	 */
	@Override
	public void cacheResult(List<LayoutSet> layoutSets) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (layoutSets.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (LayoutSet layoutSet : layoutSets) {
			LayoutSet cachedLayoutSet = (LayoutSet)EntityCacheUtil.getResult(
				LayoutSetModelImpl.ENTITY_CACHE_ENABLED, LayoutSetImpl.class,
				layoutSet.getPrimaryKey());

			if (cachedLayoutSet == null) {
				cacheResult(layoutSet);
			}
			else {
				LayoutSetModelImpl layoutSetModelImpl =
					(LayoutSetModelImpl)layoutSet;
				LayoutSetModelImpl cachedLayoutSetModelImpl =
					(LayoutSetModelImpl)cachedLayoutSet;

				layoutSetModelImpl.setCompanyFallbackVirtualHostname(
					cachedLayoutSetModelImpl.
						getCompanyFallbackVirtualHostname());

				layoutSetModelImpl.setVirtualHostnames(
					cachedLayoutSetModelImpl.getVirtualHostnames());
			}
		}
	}

	/**
	 * Clears the cache for all layout sets.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(LayoutSetImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the layout set.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>com.liferay.portal.kernel.dao.orm.FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LayoutSet layoutSet) {
		EntityCacheUtil.removeResult(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED, LayoutSetImpl.class,
			layoutSet.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LayoutSetModelImpl)layoutSet, true);
	}

	@Override
	public void clearCache(List<LayoutSet> layoutSets) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LayoutSet layoutSet : layoutSets) {
			EntityCacheUtil.removeResult(
				LayoutSetModelImpl.ENTITY_CACHE_ENABLED, LayoutSetImpl.class,
				layoutSet.getPrimaryKey());

			clearUniqueFindersCache((LayoutSetModelImpl)layoutSet, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			EntityCacheUtil.removeResult(
				LayoutSetModelImpl.ENTITY_CACHE_ENABLED, LayoutSetImpl.class,
				primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		LayoutSetModelImpl layoutSetModelImpl) {

		Object[] args = new Object[] {
			layoutSetModelImpl.getGroupId(),
			layoutSetModelImpl.isPrivateLayout(), layoutSetModelImpl.isHead()
		};

		FinderCacheUtil.putResult(
			_finderPathCountByG_P_Head, args, Long.valueOf(1), false);
		FinderCacheUtil.putResult(
			_finderPathFetchByG_P_Head, args, layoutSetModelImpl, false);

		args = new Object[] {
			layoutSetModelImpl.isPrivateLayout(),
			layoutSetModelImpl.getLogoId(), layoutSetModelImpl.isHead()
		};

		FinderCacheUtil.putResult(
			_finderPathCountByP_L_Head, args, Long.valueOf(1), false);
		FinderCacheUtil.putResult(
			_finderPathFetchByP_L_Head, args, layoutSetModelImpl, false);

		args = new Object[] {layoutSetModelImpl.getHeadId()};

		FinderCacheUtil.putResult(
			_finderPathCountByHeadId, args, Long.valueOf(1), false);
		FinderCacheUtil.putResult(
			_finderPathFetchByHeadId, args, layoutSetModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LayoutSetModelImpl layoutSetModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				layoutSetModelImpl.getGroupId(),
				layoutSetModelImpl.isPrivateLayout(),
				layoutSetModelImpl.isHead()
			};

			FinderCacheUtil.removeResult(_finderPathCountByG_P_Head, args);
			FinderCacheUtil.removeResult(_finderPathFetchByG_P_Head, args);
		}

		if ((layoutSetModelImpl.getColumnBitmask() &
			 _finderPathFetchByG_P_Head.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				layoutSetModelImpl.getOriginalGroupId(),
				layoutSetModelImpl.getOriginalPrivateLayout(),
				layoutSetModelImpl.getOriginalHead()
			};

			FinderCacheUtil.removeResult(_finderPathCountByG_P_Head, args);
			FinderCacheUtil.removeResult(_finderPathFetchByG_P_Head, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				layoutSetModelImpl.isPrivateLayout(),
				layoutSetModelImpl.getLogoId(), layoutSetModelImpl.isHead()
			};

			FinderCacheUtil.removeResult(_finderPathCountByP_L_Head, args);
			FinderCacheUtil.removeResult(_finderPathFetchByP_L_Head, args);
		}

		if ((layoutSetModelImpl.getColumnBitmask() &
			 _finderPathFetchByP_L_Head.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				layoutSetModelImpl.getOriginalPrivateLayout(),
				layoutSetModelImpl.getOriginalLogoId(),
				layoutSetModelImpl.getOriginalHead()
			};

			FinderCacheUtil.removeResult(_finderPathCountByP_L_Head, args);
			FinderCacheUtil.removeResult(_finderPathFetchByP_L_Head, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {layoutSetModelImpl.getHeadId()};

			FinderCacheUtil.removeResult(_finderPathCountByHeadId, args);
			FinderCacheUtil.removeResult(_finderPathFetchByHeadId, args);
		}

		if ((layoutSetModelImpl.getColumnBitmask() &
			 _finderPathFetchByHeadId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				layoutSetModelImpl.getOriginalHeadId()
			};

			FinderCacheUtil.removeResult(_finderPathCountByHeadId, args);
			FinderCacheUtil.removeResult(_finderPathFetchByHeadId, args);
		}
	}

	/**
	 * Creates a new layout set with the primary key. Does not add the layout set to the database.
	 *
	 * @param layoutSetId the primary key for the new layout set
	 * @return the new layout set
	 */
	@Override
	public LayoutSet create(long layoutSetId) {
		LayoutSet layoutSet = new LayoutSetImpl();

		layoutSet.setNew(true);
		layoutSet.setPrimaryKey(layoutSetId);

		layoutSet.setCompanyId(CompanyThreadLocal.getCompanyId());

		return layoutSet;
	}

	/**
	 * Removes the layout set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutSetId the primary key of the layout set
	 * @return the layout set that was removed
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet remove(long layoutSetId) throws NoSuchLayoutSetException {
		return remove((Serializable)layoutSetId);
	}

	/**
	 * Removes the layout set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the layout set
	 * @return the layout set that was removed
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet remove(Serializable primaryKey)
		throws NoSuchLayoutSetException {

		Session session = null;

		try {
			session = openSession();

			LayoutSet layoutSet = (LayoutSet)session.get(
				LayoutSetImpl.class, primaryKey);

			if (layoutSet == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLayoutSetException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(layoutSet);
		}
		catch (NoSuchLayoutSetException noSuchEntityException) {
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
	protected LayoutSet removeImpl(LayoutSet layoutSet) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(layoutSet)) {
				layoutSet = (LayoutSet)session.get(
					LayoutSetImpl.class, layoutSet.getPrimaryKeyObj());
			}

			if (layoutSet != null) {
				session.delete(layoutSet);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (layoutSet != null) {
			clearCache(layoutSet);
		}

		return layoutSet;
	}

	@Override
	public LayoutSet updateImpl(LayoutSet layoutSet) {
		boolean isNew = layoutSet.isNew();

		if (!(layoutSet instanceof LayoutSetModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(layoutSet.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(layoutSet);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in layoutSet proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LayoutSet implementation " +
					layoutSet.getClass());
		}

		LayoutSetModelImpl layoutSetModelImpl = (LayoutSetModelImpl)layoutSet;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (layoutSet.getCreateDate() == null)) {
			if (serviceContext == null) {
				layoutSet.setCreateDate(date);
			}
			else {
				layoutSet.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!layoutSetModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				layoutSet.setModifiedDate(date);
			}
			else {
				layoutSet.setModifiedDate(serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(layoutSet);

				layoutSet.setNew(false);
			}
			else {
				layoutSet = (LayoutSet)session.merge(layoutSet);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!LayoutSetModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {layoutSetModelImpl.getGroupId()};

			FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {
				layoutSetModelImpl.getGroupId(), layoutSetModelImpl.isHead()
			};

			FinderCacheUtil.removeResult(_finderPathCountByGroupId_Head, args);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindByGroupId_Head, args);

			args = new Object[] {
				layoutSetModelImpl.getLayoutSetPrototypeUuid()
			};

			FinderCacheUtil.removeResult(
				_finderPathCountByLayoutSetPrototypeUuid, args);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid, args);

			args = new Object[] {
				layoutSetModelImpl.getLayoutSetPrototypeUuid(),
				layoutSetModelImpl.isHead()
			};

			FinderCacheUtil.removeResult(
				_finderPathCountByLayoutSetPrototypeUuid_Head, args);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid_Head,
				args);

			args = new Object[] {
				layoutSetModelImpl.getGroupId(),
				layoutSetModelImpl.isPrivateLayout()
			};

			FinderCacheUtil.removeResult(_finderPathCountByG_P, args);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindByG_P, args);

			args = new Object[] {
				layoutSetModelImpl.getCompanyId(),
				layoutSetModelImpl.getLayoutSetPrototypeUuid()
			};

			FinderCacheUtil.removeResult(_finderPathCountByC_L, args);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindByC_L, args);

			args = new Object[] {
				layoutSetModelImpl.getCompanyId(),
				layoutSetModelImpl.getLayoutSetPrototypeUuid(),
				layoutSetModelImpl.isHead()
			};

			FinderCacheUtil.removeResult(_finderPathCountByC_L_Head, args);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindByC_L_Head, args);

			args = new Object[] {
				layoutSetModelImpl.isPrivateLayout(),
				layoutSetModelImpl.getLogoId()
			};

			FinderCacheUtil.removeResult(_finderPathCountByP_L, args);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindByP_L, args);

			FinderCacheUtil.removeResult(
				_finderPathCountAll, FINDER_ARGS_EMPTY);
			FinderCacheUtil.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((layoutSetModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					layoutSetModelImpl.getOriginalGroupId()
				};

				FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {layoutSetModelImpl.getGroupId()};

				FinderCacheUtil.removeResult(_finderPathCountByGroupId, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((layoutSetModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId_Head.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					layoutSetModelImpl.getOriginalGroupId(),
					layoutSetModelImpl.getOriginalHead()
				};

				FinderCacheUtil.removeResult(
					_finderPathCountByGroupId_Head, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByGroupId_Head, args);

				args = new Object[] {
					layoutSetModelImpl.getGroupId(), layoutSetModelImpl.isHead()
				};

				FinderCacheUtil.removeResult(
					_finderPathCountByGroupId_Head, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByGroupId_Head, args);
			}

			if ((layoutSetModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByLayoutSetPrototypeUuid.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					layoutSetModelImpl.getOriginalLayoutSetPrototypeUuid()
				};

				FinderCacheUtil.removeResult(
					_finderPathCountByLayoutSetPrototypeUuid, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid,
					args);

				args = new Object[] {
					layoutSetModelImpl.getLayoutSetPrototypeUuid()
				};

				FinderCacheUtil.removeResult(
					_finderPathCountByLayoutSetPrototypeUuid, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid,
					args);
			}

			if ((layoutSetModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByLayoutSetPrototypeUuid_Head.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					layoutSetModelImpl.getOriginalLayoutSetPrototypeUuid(),
					layoutSetModelImpl.getOriginalHead()
				};

				FinderCacheUtil.removeResult(
					_finderPathCountByLayoutSetPrototypeUuid_Head, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid_Head,
					args);

				args = new Object[] {
					layoutSetModelImpl.getLayoutSetPrototypeUuid(),
					layoutSetModelImpl.isHead()
				};

				FinderCacheUtil.removeResult(
					_finderPathCountByLayoutSetPrototypeUuid_Head, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid_Head,
					args);
			}

			if ((layoutSetModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByG_P.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					layoutSetModelImpl.getOriginalGroupId(),
					layoutSetModelImpl.getOriginalPrivateLayout()
				};

				FinderCacheUtil.removeResult(_finderPathCountByG_P, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByG_P, args);

				args = new Object[] {
					layoutSetModelImpl.getGroupId(),
					layoutSetModelImpl.isPrivateLayout()
				};

				FinderCacheUtil.removeResult(_finderPathCountByG_P, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByG_P, args);
			}

			if ((layoutSetModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_L.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					layoutSetModelImpl.getOriginalCompanyId(),
					layoutSetModelImpl.getOriginalLayoutSetPrototypeUuid()
				};

				FinderCacheUtil.removeResult(_finderPathCountByC_L, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByC_L, args);

				args = new Object[] {
					layoutSetModelImpl.getCompanyId(),
					layoutSetModelImpl.getLayoutSetPrototypeUuid()
				};

				FinderCacheUtil.removeResult(_finderPathCountByC_L, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByC_L, args);
			}

			if ((layoutSetModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_L_Head.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					layoutSetModelImpl.getOriginalCompanyId(),
					layoutSetModelImpl.getOriginalLayoutSetPrototypeUuid(),
					layoutSetModelImpl.getOriginalHead()
				};

				FinderCacheUtil.removeResult(_finderPathCountByC_L_Head, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByC_L_Head, args);

				args = new Object[] {
					layoutSetModelImpl.getCompanyId(),
					layoutSetModelImpl.getLayoutSetPrototypeUuid(),
					layoutSetModelImpl.isHead()
				};

				FinderCacheUtil.removeResult(_finderPathCountByC_L_Head, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByC_L_Head, args);
			}

			if ((layoutSetModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByP_L.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					layoutSetModelImpl.getOriginalPrivateLayout(),
					layoutSetModelImpl.getOriginalLogoId()
				};

				FinderCacheUtil.removeResult(_finderPathCountByP_L, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByP_L, args);

				args = new Object[] {
					layoutSetModelImpl.isPrivateLayout(),
					layoutSetModelImpl.getLogoId()
				};

				FinderCacheUtil.removeResult(_finderPathCountByP_L, args);
				FinderCacheUtil.removeResult(
					_finderPathWithoutPaginationFindByP_L, args);
			}
		}

		EntityCacheUtil.putResult(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED, LayoutSetImpl.class,
			layoutSet.getPrimaryKey(), layoutSet, false);

		clearUniqueFindersCache(layoutSetModelImpl, false);
		cacheUniqueFindersCache(layoutSetModelImpl);

		layoutSet.resetOriginalValues();

		return layoutSet;
	}

	/**
	 * Returns the layout set with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the layout set
	 * @return the layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLayoutSetException {

		LayoutSet layoutSet = fetchByPrimaryKey(primaryKey);

		if (layoutSet == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLayoutSetException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return layoutSet;
	}

	/**
	 * Returns the layout set with the primary key or throws a <code>NoSuchLayoutSetException</code> if it could not be found.
	 *
	 * @param layoutSetId the primary key of the layout set
	 * @return the layout set
	 * @throws NoSuchLayoutSetException if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet findByPrimaryKey(long layoutSetId)
		throws NoSuchLayoutSetException {

		return findByPrimaryKey((Serializable)layoutSetId);
	}

	/**
	 * Returns the layout set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param layoutSetId the primary key of the layout set
	 * @return the layout set, or <code>null</code> if a layout set with the primary key could not be found
	 */
	@Override
	public LayoutSet fetchByPrimaryKey(long layoutSetId) {
		return fetchByPrimaryKey((Serializable)layoutSetId);
	}

	/**
	 * Returns all the layout sets.
	 *
	 * @return the layout sets
	 */
	@Override
	public List<LayoutSet> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the layout sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @return the range of layout sets
	 */
	@Override
	public List<LayoutSet> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the layout sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of layout sets
	 */
	@Override
	public List<LayoutSet> findAll(
		int start, int end, OrderByComparator<LayoutSet> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the layout sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LayoutSetModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout sets
	 * @param end the upper bound of the range of layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of layout sets
	 */
	@Override
	public List<LayoutSet> findAll(
		int start, int end, OrderByComparator<LayoutSet> orderByComparator,
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

		List<LayoutSet> list = null;

		if (useFinderCache) {
			list = (List<LayoutSet>)FinderCacheUtil.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_LAYOUTSET);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_LAYOUTSET;

				sql = sql.concat(LayoutSetModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<LayoutSet>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
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
	 * Removes all the layout sets from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LayoutSet layoutSet : findAll()) {
			remove(layoutSet);
		}
	}

	/**
	 * Returns the number of layout sets.
	 *
	 * @return the number of layout sets
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_LAYOUTSET);

				count = (Long)query.uniqueResult();

				FinderCacheUtil.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				FinderCacheUtil.removeResult(
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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return EntityCacheUtil.getEntityCache();
	}

	@Override
	protected String getPKDBName() {
		return "layoutSetId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LAYOUTSET;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LayoutSetModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the layout set persistence.
	 */
	public void afterPropertiesSet() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			LayoutSetModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByGroupId_Head = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId_Head",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId_Head = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId_Head",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			LayoutSetModelImpl.GROUPID_COLUMN_BITMASK |
			LayoutSetModelImpl.HEAD_COLUMN_BITMASK);

		_finderPathCountByGroupId_Head = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId_Head",
			new String[] {Long.class.getName(), Boolean.class.getName()});

		_finderPathWithPaginationFindByLayoutSetPrototypeUuid = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLayoutSetPrototypeUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid =
			new FinderPath(
				LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
				LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByLayoutSetPrototypeUuid",
				new String[] {String.class.getName()},
				LayoutSetModelImpl.LAYOUTSETPROTOTYPEUUID_COLUMN_BITMASK);

		_finderPathCountByLayoutSetPrototypeUuid = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLayoutSetPrototypeUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByLayoutSetPrototypeUuid_Head =
			new FinderPath(
				LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
				LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByLayoutSetPrototypeUuid_Head",
				new String[] {
					String.class.getName(), Boolean.class.getName(),
					Integer.class.getName(), Integer.class.getName(),
					OrderByComparator.class.getName()
				});

		_finderPathWithoutPaginationFindByLayoutSetPrototypeUuid_Head =
			new FinderPath(
				LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
				LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByLayoutSetPrototypeUuid_Head",
				new String[] {String.class.getName(), Boolean.class.getName()},
				LayoutSetModelImpl.LAYOUTSETPROTOTYPEUUID_COLUMN_BITMASK |
				LayoutSetModelImpl.HEAD_COLUMN_BITMASK);

		_finderPathCountByLayoutSetPrototypeUuid_Head = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLayoutSetPrototypeUuid_Head",
			new String[] {String.class.getName(), Boolean.class.getName()});

		_finderPathWithPaginationFindByG_P = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByG_P = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P",
			new String[] {Long.class.getName(), Boolean.class.getName()},
			LayoutSetModelImpl.GROUPID_COLUMN_BITMASK |
			LayoutSetModelImpl.PRIVATELAYOUT_COLUMN_BITMASK);

		_finderPathCountByG_P = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P",
			new String[] {Long.class.getName(), Boolean.class.getName()});

		_finderPathFetchByG_P_Head = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_P_Head",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			LayoutSetModelImpl.GROUPID_COLUMN_BITMASK |
			LayoutSetModelImpl.PRIVATELAYOUT_COLUMN_BITMASK |
			LayoutSetModelImpl.HEAD_COLUMN_BITMASK);

		_finderPathCountByG_P_Head = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_Head",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

		_finderPathWithPaginationFindByC_L = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_L",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_L = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_L",
			new String[] {Long.class.getName(), String.class.getName()},
			LayoutSetModelImpl.COMPANYID_COLUMN_BITMASK |
			LayoutSetModelImpl.LAYOUTSETPROTOTYPEUUID_COLUMN_BITMASK);

		_finderPathCountByC_L = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_L",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByC_L_Head = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_L_Head",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_L_Head = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_L_Head",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			LayoutSetModelImpl.COMPANYID_COLUMN_BITMASK |
			LayoutSetModelImpl.LAYOUTSETPROTOTYPEUUID_COLUMN_BITMASK |
			LayoutSetModelImpl.HEAD_COLUMN_BITMASK);

		_finderPathCountByC_L_Head = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_L_Head",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

		_finderPathWithPaginationFindByP_L = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByP_L",
			new String[] {
				Boolean.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByP_L = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByP_L",
			new String[] {Boolean.class.getName(), Long.class.getName()},
			LayoutSetModelImpl.PRIVATELAYOUT_COLUMN_BITMASK |
			LayoutSetModelImpl.LOGOID_COLUMN_BITMASK);

		_finderPathCountByP_L = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_L",
			new String[] {Boolean.class.getName(), Long.class.getName()});

		_finderPathFetchByP_L_Head = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByP_L_Head",
			new String[] {
				Boolean.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			},
			LayoutSetModelImpl.PRIVATELAYOUT_COLUMN_BITMASK |
			LayoutSetModelImpl.LOGOID_COLUMN_BITMASK |
			LayoutSetModelImpl.HEAD_COLUMN_BITMASK);

		_finderPathCountByP_L_Head = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByP_L_Head",
			new String[] {
				Boolean.class.getName(), Long.class.getName(),
				Boolean.class.getName()
			});

		_finderPathFetchByHeadId = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, LayoutSetImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByHeadId",
			new String[] {Long.class.getName()},
			LayoutSetModelImpl.HEADID_COLUMN_BITMASK);

		_finderPathCountByHeadId = new FinderPath(
			LayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByHeadId",
			new String[] {Long.class.getName()});

		LayoutSetUtil.setPersistence(this);
	}

	public void destroy() {
		LayoutSetUtil.setPersistence(null);

		EntityCacheUtil.removeCache(LayoutSetImpl.class.getName());

		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_LAYOUTSET =
		"SELECT layoutSet FROM LayoutSet layoutSet";

	private static final String _SQL_SELECT_LAYOUTSET_WHERE =
		"SELECT layoutSet FROM LayoutSet layoutSet WHERE ";

	private static final String _SQL_COUNT_LAYOUTSET =
		"SELECT COUNT(layoutSet) FROM LayoutSet layoutSet";

	private static final String _SQL_COUNT_LAYOUTSET_WHERE =
		"SELECT COUNT(layoutSet) FROM LayoutSet layoutSet WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "layoutSet.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LayoutSet exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LayoutSet exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LayoutSetPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"settings"});

}