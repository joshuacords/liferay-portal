/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service.persistence.impl;

import com.liferay.osb.koroneiki.phytohormone.exception.NoSuchEntitlementDefinitionException;
import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementDefinitionImpl;
import com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementDefinitionModelImpl;
import com.liferay.osb.koroneiki.phytohormone.service.persistence.EntitlementDefinitionPersistence;
import com.liferay.osb.koroneiki.phytohormone.service.persistence.EntitlementDefinitionUtil;
import com.liferay.osb.koroneiki.phytohormone.service.persistence.impl.constants.KoroneikiPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the entitlement definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = EntitlementDefinitionPersistence.class)
public class EntitlementDefinitionPersistenceImpl
	extends BasePersistenceImpl<EntitlementDefinition>
	implements EntitlementDefinitionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>EntitlementDefinitionUtil</code> to access the entitlement definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		EntitlementDefinitionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the entitlement definitions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching entitlement definitions
	 */
	@Override
	public List<EntitlementDefinition> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<EntitlementDefinition> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

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
	@Override
	public List<EntitlementDefinition> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

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
	@Override
	public List<EntitlementDefinition> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<EntitlementDefinition> list = null;

		if (useFinderCache) {
			list = (List<EntitlementDefinition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EntitlementDefinition entitlementDefinition : list) {
					if (!uuid.equals(entitlementDefinition.getUuid())) {
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

			sb.append(_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<EntitlementDefinition>)QueryUtil.list(
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
	 * Returns the first entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition findByUuid_First(
			String uuid,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		EntitlementDefinition entitlementDefinition = fetchByUuid_First(
			uuid, orderByComparator);

		if (entitlementDefinition != null) {
			return entitlementDefinition;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchEntitlementDefinitionException(sb.toString());
	}

	/**
	 * Returns the first entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition fetchByUuid_First(
		String uuid,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		List<EntitlementDefinition> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition findByUuid_Last(
			String uuid,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		EntitlementDefinition entitlementDefinition = fetchByUuid_Last(
			uuid, orderByComparator);

		if (entitlementDefinition != null) {
			return entitlementDefinition;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchEntitlementDefinitionException(sb.toString());
	}

	/**
	 * Returns the last entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition fetchByUuid_Last(
		String uuid,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<EntitlementDefinition> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entitlement definitions before and after the current entitlement definition in the ordered set where uuid = &#63;.
	 *
	 * @param entitlementDefinitionId the primary key of the current entitlement definition
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	@Override
	public EntitlementDefinition[] findByUuid_PrevAndNext(
			long entitlementDefinitionId, String uuid,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		uuid = Objects.toString(uuid, "");

		EntitlementDefinition entitlementDefinition = findByPrimaryKey(
			entitlementDefinitionId);

		Session session = null;

		try {
			session = openSession();

			EntitlementDefinition[] array = new EntitlementDefinitionImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, entitlementDefinition, uuid, orderByComparator, true);

			array[1] = entitlementDefinition;

			array[2] = getByUuid_PrevAndNext(
				session, entitlementDefinition, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected EntitlementDefinition getByUuid_PrevAndNext(
		Session session, EntitlementDefinition entitlementDefinition,
		String uuid, OrderByComparator<EntitlementDefinition> orderByComparator,
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

		sb.append(_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
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
			sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						entitlementDefinition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<EntitlementDefinition> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the entitlement definitions that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching entitlement definitions that the user has permission to view
	 */
	@Override
	public List<EntitlementDefinition> filterFindByUuid(String uuid) {
		return filterFindByUuid(
			uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<EntitlementDefinition> filterFindByUuid(
		String uuid, int start, int end) {

		return filterFindByUuid(uuid, start, end, null);
	}

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
	@Override
	public List<EntitlementDefinition> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid(uuid, start, end, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), EntitlementDefinition.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, EntitlementDefinitionImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, EntitlementDefinitionImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (bindUuid) {
				queryPos.add(uuid);
			}

			return (List<EntitlementDefinition>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the entitlement definitions before and after the current entitlement definition in the ordered set of entitlement definitions that the user has permission to view where uuid = &#63;.
	 *
	 * @param entitlementDefinitionId the primary key of the current entitlement definition
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	@Override
	public EntitlementDefinition[] filterFindByUuid_PrevAndNext(
			long entitlementDefinitionId, String uuid,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid_PrevAndNext(
				entitlementDefinitionId, uuid, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		EntitlementDefinition entitlementDefinition = findByPrimaryKey(
			entitlementDefinitionId);

		Session session = null;

		try {
			session = openSession();

			EntitlementDefinition[] array = new EntitlementDefinitionImpl[3];

			array[0] = filterGetByUuid_PrevAndNext(
				session, entitlementDefinition, uuid, orderByComparator, true);

			array[1] = entitlementDefinition;

			array[2] = filterGetByUuid_PrevAndNext(
				session, entitlementDefinition, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected EntitlementDefinition filterGetByUuid_PrevAndNext(
		Session session, EntitlementDefinition entitlementDefinition,
		String uuid, OrderByComparator<EntitlementDefinition> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), EntitlementDefinition.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(
				_FILTER_ENTITY_ALIAS, EntitlementDefinitionImpl.class);
		}
		else {
			sqlQuery.addEntity(
				_FILTER_ENTITY_TABLE, EntitlementDefinitionImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						entitlementDefinition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<EntitlementDefinition> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entitlement definitions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (EntitlementDefinition entitlementDefinition :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(entitlementDefinition);
		}
	}

	/**
	 * Returns the number of entitlement definitions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching entitlement definitions
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ENTITLEMENTDEFINITION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

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

	/**
	 * Returns the number of entitlement definitions that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching entitlement definitions that the user has permission to view
	 */
	@Override
	public int filterCountByUuid(String uuid) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUuid(uuid);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler sb = new StringBundler(2);

		sb.append(_FILTER_SQL_COUNT_ENTITLEMENTDEFINITION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), EntitlementDefinition.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (bindUuid) {
				queryPos.add(uuid);
			}

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"entitlementDefinition.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(entitlementDefinition.uuid IS NULL OR entitlementDefinition.uuid = '')";

	private static final String _FINDER_COLUMN_UUID_UUID_2_SQL =
		"entitlementDefinition.uuid_ = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3_SQL =
		"(entitlementDefinition.uuid_ IS NULL OR entitlementDefinition.uuid_ = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the entitlement definitions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching entitlement definitions
	 */
	@Override
	public List<EntitlementDefinition> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<EntitlementDefinition> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

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
	@Override
	public List<EntitlementDefinition> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

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
	@Override
	public List<EntitlementDefinition> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<EntitlementDefinition> list = null;

		if (useFinderCache) {
			list = (List<EntitlementDefinition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EntitlementDefinition entitlementDefinition : list) {
					if (!uuid.equals(entitlementDefinition.getUuid()) ||
						(companyId != entitlementDefinition.getCompanyId())) {

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

			sb.append(_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<EntitlementDefinition>)QueryUtil.list(
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
	 * Returns the first entitlement definition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		EntitlementDefinition entitlementDefinition = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (entitlementDefinition != null) {
			return entitlementDefinition;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchEntitlementDefinitionException(sb.toString());
	}

	/**
	 * Returns the first entitlement definition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		List<EntitlementDefinition> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entitlement definition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		EntitlementDefinition entitlementDefinition = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (entitlementDefinition != null) {
			return entitlementDefinition;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchEntitlementDefinitionException(sb.toString());
	}

	/**
	 * Returns the last entitlement definition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<EntitlementDefinition> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public EntitlementDefinition[] findByUuid_C_PrevAndNext(
			long entitlementDefinitionId, String uuid, long companyId,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		uuid = Objects.toString(uuid, "");

		EntitlementDefinition entitlementDefinition = findByPrimaryKey(
			entitlementDefinitionId);

		Session session = null;

		try {
			session = openSession();

			EntitlementDefinition[] array = new EntitlementDefinitionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, entitlementDefinition, uuid, companyId,
				orderByComparator, true);

			array[1] = entitlementDefinition;

			array[2] = getByUuid_C_PrevAndNext(
				session, entitlementDefinition, uuid, companyId,
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

	protected EntitlementDefinition getByUuid_C_PrevAndNext(
		Session session, EntitlementDefinition entitlementDefinition,
		String uuid, long companyId,
		OrderByComparator<EntitlementDefinition> orderByComparator,
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

		sb.append(_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						entitlementDefinition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<EntitlementDefinition> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the entitlement definitions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching entitlement definitions that the user has permission to view
	 */
	@Override
	public List<EntitlementDefinition> filterFindByUuid_C(
		String uuid, long companyId) {

		return filterFindByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<EntitlementDefinition> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return filterFindByUuid_C(uuid, companyId, start, end, null);
	}

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
	@Override
	public List<EntitlementDefinition> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByUuid_C(uuid, companyId, start, end, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), EntitlementDefinition.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, EntitlementDefinitionImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, EntitlementDefinitionImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (bindUuid) {
				queryPos.add(uuid);
			}

			queryPos.add(companyId);

			return (List<EntitlementDefinition>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

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
	@Override
	public EntitlementDefinition[] filterFindByUuid_C_PrevAndNext(
			long entitlementDefinitionId, String uuid, long companyId,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByUuid_C_PrevAndNext(
				entitlementDefinitionId, uuid, companyId, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		EntitlementDefinition entitlementDefinition = findByPrimaryKey(
			entitlementDefinitionId);

		Session session = null;

		try {
			session = openSession();

			EntitlementDefinition[] array = new EntitlementDefinitionImpl[3];

			array[0] = filterGetByUuid_C_PrevAndNext(
				session, entitlementDefinition, uuid, companyId,
				orderByComparator, true);

			array[1] = entitlementDefinition;

			array[2] = filterGetByUuid_C_PrevAndNext(
				session, entitlementDefinition, uuid, companyId,
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

	protected EntitlementDefinition filterGetByUuid_C_PrevAndNext(
		Session session, EntitlementDefinition entitlementDefinition,
		String uuid, long companyId,
		OrderByComparator<EntitlementDefinition> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), EntitlementDefinition.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(
				_FILTER_ENTITY_ALIAS, EntitlementDefinitionImpl.class);
		}
		else {
			sqlQuery.addEntity(
				_FILTER_ENTITY_TABLE, EntitlementDefinitionImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						entitlementDefinition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<EntitlementDefinition> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entitlement definitions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (EntitlementDefinition entitlementDefinition :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(entitlementDefinition);
		}
	}

	/**
	 * Returns the number of entitlement definitions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching entitlement definitions
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ENTITLEMENTDEFINITION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

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

	/**
	 * Returns the number of entitlement definitions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching entitlement definitions that the user has permission to view
	 */
	@Override
	public int filterCountByUuid_C(String uuid, long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByUuid_C(uuid, companyId);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler sb = new StringBundler(3);

		sb.append(_FILTER_SQL_COUNT_ENTITLEMENTDEFINITION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), EntitlementDefinition.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (bindUuid) {
				queryPos.add(uuid);
			}

			queryPos.add(companyId);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"entitlementDefinition.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(entitlementDefinition.uuid IS NULL OR entitlementDefinition.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_2_SQL =
		"entitlementDefinition.uuid_ = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3_SQL =
		"(entitlementDefinition.uuid_ IS NULL OR entitlementDefinition.uuid_ = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"entitlementDefinition.companyId = ?";

	private FinderPath _finderPathFetchByEntitlementDefinitionKey;
	private FinderPath _finderPathCountByEntitlementDefinitionKey;

	/**
	 * Returns the entitlement definition where entitlementDefinitionKey = &#63; or throws a <code>NoSuchEntitlementDefinitionException</code> if it could not be found.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @return the matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition findByEntitlementDefinitionKey(
			String entitlementDefinitionKey)
		throws NoSuchEntitlementDefinitionException {

		EntitlementDefinition entitlementDefinition =
			fetchByEntitlementDefinitionKey(entitlementDefinitionKey);

		if (entitlementDefinition == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("entitlementDefinitionKey=");
			sb.append(entitlementDefinitionKey);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchEntitlementDefinitionException(sb.toString());
		}

		return entitlementDefinition;
	}

	/**
	 * Returns the entitlement definition where entitlementDefinitionKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition fetchByEntitlementDefinitionKey(
		String entitlementDefinitionKey) {

		return fetchByEntitlementDefinitionKey(entitlementDefinitionKey, true);
	}

	/**
	 * Returns the entitlement definition where entitlementDefinitionKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition fetchByEntitlementDefinitionKey(
		String entitlementDefinitionKey, boolean useFinderCache) {

		entitlementDefinitionKey = Objects.toString(
			entitlementDefinitionKey, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {entitlementDefinitionKey};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByEntitlementDefinitionKey, finderArgs, this);
		}

		if (result instanceof EntitlementDefinition) {
			EntitlementDefinition entitlementDefinition =
				(EntitlementDefinition)result;

			if (!Objects.equals(
					entitlementDefinitionKey,
					entitlementDefinition.getEntitlementDefinitionKey())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);

			boolean bindEntitlementDefinitionKey = false;

			if (entitlementDefinitionKey.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_ENTITLEMENTDEFINITIONKEY_ENTITLEMENTDEFINITIONKEY_3);
			}
			else {
				bindEntitlementDefinitionKey = true;

				sb.append(
					_FINDER_COLUMN_ENTITLEMENTDEFINITIONKEY_ENTITLEMENTDEFINITIONKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEntitlementDefinitionKey) {
					queryPos.add(entitlementDefinitionKey);
				}

				List<EntitlementDefinition> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByEntitlementDefinitionKey,
							finderArgs, list);
					}
				}
				else {
					EntitlementDefinition entitlementDefinition = list.get(0);

					result = entitlementDefinition;

					cacheResult(entitlementDefinition);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByEntitlementDefinitionKey, finderArgs);
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
			return (EntitlementDefinition)result;
		}
	}

	/**
	 * Removes the entitlement definition where entitlementDefinitionKey = &#63; from the database.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @return the entitlement definition that was removed
	 */
	@Override
	public EntitlementDefinition removeByEntitlementDefinitionKey(
			String entitlementDefinitionKey)
		throws NoSuchEntitlementDefinitionException {

		EntitlementDefinition entitlementDefinition =
			findByEntitlementDefinitionKey(entitlementDefinitionKey);

		return remove(entitlementDefinition);
	}

	/**
	 * Returns the number of entitlement definitions where entitlementDefinitionKey = &#63;.
	 *
	 * @param entitlementDefinitionKey the entitlement definition key
	 * @return the number of matching entitlement definitions
	 */
	@Override
	public int countByEntitlementDefinitionKey(
		String entitlementDefinitionKey) {

		entitlementDefinitionKey = Objects.toString(
			entitlementDefinitionKey, "");

		FinderPath finderPath = _finderPathCountByEntitlementDefinitionKey;

		Object[] finderArgs = new Object[] {entitlementDefinitionKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ENTITLEMENTDEFINITION_WHERE);

			boolean bindEntitlementDefinitionKey = false;

			if (entitlementDefinitionKey.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_ENTITLEMENTDEFINITIONKEY_ENTITLEMENTDEFINITIONKEY_3);
			}
			else {
				bindEntitlementDefinitionKey = true;

				sb.append(
					_FINDER_COLUMN_ENTITLEMENTDEFINITIONKEY_ENTITLEMENTDEFINITIONKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEntitlementDefinitionKey) {
					queryPos.add(entitlementDefinitionKey);
				}

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

	private static final String
		_FINDER_COLUMN_ENTITLEMENTDEFINITIONKEY_ENTITLEMENTDEFINITIONKEY_2 =
			"entitlementDefinition.entitlementDefinitionKey = ?";

	private static final String
		_FINDER_COLUMN_ENTITLEMENTDEFINITIONKEY_ENTITLEMENTDEFINITIONKEY_3 =
			"(entitlementDefinition.entitlementDefinitionKey IS NULL OR entitlementDefinition.entitlementDefinitionKey = '')";

	private FinderPath _finderPathFetchByC_N;
	private FinderPath _finderPathCountByC_N;

	/**
	 * Returns the entitlement definition where classNameId = &#63; and name = &#63; or throws a <code>NoSuchEntitlementDefinitionException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition findByC_N(long classNameId, String name)
		throws NoSuchEntitlementDefinitionException {

		EntitlementDefinition entitlementDefinition = fetchByC_N(
			classNameId, name);

		if (entitlementDefinition == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("classNameId=");
			sb.append(classNameId);

			sb.append(", name=");
			sb.append(name);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchEntitlementDefinitionException(sb.toString());
		}

		return entitlementDefinition;
	}

	/**
	 * Returns the entitlement definition where classNameId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition fetchByC_N(long classNameId, String name) {
		return fetchByC_N(classNameId, name, true);
	}

	/**
	 * Returns the entitlement definition where classNameId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition fetchByC_N(
		long classNameId, String name, boolean useFinderCache) {

		name = Objects.toString(name, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {classNameId, name};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_N, finderArgs, this);
		}

		if (result instanceof EntitlementDefinition) {
			EntitlementDefinition entitlementDefinition =
				(EntitlementDefinition)result;

			if ((classNameId != entitlementDefinition.getClassNameId()) ||
				!Objects.equals(name, entitlementDefinition.getName())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);

			sb.append(_FINDER_COLUMN_C_N_CLASSNAMEID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_C_N_NAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				if (bindName) {
					queryPos.add(name);
				}

				List<EntitlementDefinition> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_N, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {classNameId, name};
							}

							_log.warn(
								"EntitlementDefinitionPersistenceImpl.fetchByC_N(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					EntitlementDefinition entitlementDefinition = list.get(0);

					result = entitlementDefinition;

					cacheResult(entitlementDefinition);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(_finderPathFetchByC_N, finderArgs);
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
			return (EntitlementDefinition)result;
		}
	}

	/**
	 * Removes the entitlement definition where classNameId = &#63; and name = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the entitlement definition that was removed
	 */
	@Override
	public EntitlementDefinition removeByC_N(long classNameId, String name)
		throws NoSuchEntitlementDefinitionException {

		EntitlementDefinition entitlementDefinition = findByC_N(
			classNameId, name);

		return remove(entitlementDefinition);
	}

	/**
	 * Returns the number of entitlement definitions where classNameId = &#63; and name = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the number of matching entitlement definitions
	 */
	@Override
	public int countByC_N(long classNameId, String name) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByC_N;

		Object[] finderArgs = new Object[] {classNameId, name};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ENTITLEMENTDEFINITION_WHERE);

			sb.append(_FINDER_COLUMN_C_N_CLASSNAMEID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_N_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_C_N_NAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				if (bindName) {
					queryPos.add(name);
				}

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

	private static final String _FINDER_COLUMN_C_N_CLASSNAMEID_2 =
		"entitlementDefinition.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_N_NAME_2 =
		"entitlementDefinition.name = ?";

	private static final String _FINDER_COLUMN_C_N_NAME_3 =
		"(entitlementDefinition.name IS NULL OR entitlementDefinition.name = '')";

	private FinderPath _finderPathWithPaginationFindByC_LikeN;
	private FinderPath _finderPathWithPaginationCountByC_LikeN;

	/**
	 * Returns all the entitlement definitions where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching entitlement definitions
	 */
	@Override
	public List<EntitlementDefinition> findByC_LikeN(
		long classNameId, String name) {

		return findByC_LikeN(
			classNameId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<EntitlementDefinition> findByC_LikeN(
		long classNameId, String name, int start, int end) {

		return findByC_LikeN(classNameId, name, start, end, null);
	}

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
	@Override
	public List<EntitlementDefinition> findByC_LikeN(
		long classNameId, String name, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return findByC_LikeN(
			classNameId, name, start, end, orderByComparator, true);
	}

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
	@Override
	public List<EntitlementDefinition> findByC_LikeN(
		long classNameId, String name, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator,
		boolean useFinderCache) {

		name = Objects.toString(name, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByC_LikeN;
		finderArgs = new Object[] {
			classNameId, name, start, end, orderByComparator
		};

		List<EntitlementDefinition> list = null;

		if (useFinderCache) {
			list = (List<EntitlementDefinition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EntitlementDefinition entitlementDefinition : list) {
					if ((classNameId !=
							entitlementDefinition.getClassNameId()) ||
						!StringUtil.wildcardMatches(
							entitlementDefinition.getName(), name, '_', '%',
							'\\', true)) {

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

			sb.append(_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);

			sb.append(_FINDER_COLUMN_C_LIKEN_CLASSNAMEID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_LIKEN_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_C_LIKEN_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				if (bindName) {
					queryPos.add(name);
				}

				list = (List<EntitlementDefinition>)QueryUtil.list(
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
	 * Returns the first entitlement definition in the ordered set where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition findByC_LikeN_First(
			long classNameId, String name,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		EntitlementDefinition entitlementDefinition = fetchByC_LikeN_First(
			classNameId, name, orderByComparator);

		if (entitlementDefinition != null) {
			return entitlementDefinition;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", nameLIKE");
		sb.append(name);

		sb.append("}");

		throw new NoSuchEntitlementDefinitionException(sb.toString());
	}

	/**
	 * Returns the first entitlement definition in the ordered set where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition fetchByC_LikeN_First(
		long classNameId, String name,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		List<EntitlementDefinition> list = findByC_LikeN(
			classNameId, name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entitlement definition in the ordered set where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition findByC_LikeN_Last(
			long classNameId, String name,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		EntitlementDefinition entitlementDefinition = fetchByC_LikeN_Last(
			classNameId, name, orderByComparator);

		if (entitlementDefinition != null) {
			return entitlementDefinition;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", nameLIKE");
		sb.append(name);

		sb.append("}");

		throw new NoSuchEntitlementDefinitionException(sb.toString());
	}

	/**
	 * Returns the last entitlement definition in the ordered set where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition fetchByC_LikeN_Last(
		long classNameId, String name,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		int count = countByC_LikeN(classNameId, name);

		if (count == 0) {
			return null;
		}

		List<EntitlementDefinition> list = findByC_LikeN(
			classNameId, name, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public EntitlementDefinition[] findByC_LikeN_PrevAndNext(
			long entitlementDefinitionId, long classNameId, String name,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		name = Objects.toString(name, "");

		EntitlementDefinition entitlementDefinition = findByPrimaryKey(
			entitlementDefinitionId);

		Session session = null;

		try {
			session = openSession();

			EntitlementDefinition[] array = new EntitlementDefinitionImpl[3];

			array[0] = getByC_LikeN_PrevAndNext(
				session, entitlementDefinition, classNameId, name,
				orderByComparator, true);

			array[1] = entitlementDefinition;

			array[2] = getByC_LikeN_PrevAndNext(
				session, entitlementDefinition, classNameId, name,
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

	protected EntitlementDefinition getByC_LikeN_PrevAndNext(
		Session session, EntitlementDefinition entitlementDefinition,
		long classNameId, String name,
		OrderByComparator<EntitlementDefinition> orderByComparator,
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

		sb.append(_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);

		sb.append(_FINDER_COLUMN_C_LIKEN_CLASSNAMEID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_LIKEN_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_C_LIKEN_NAME_2);
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
			sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(classNameId);

		if (bindName) {
			queryPos.add(name);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						entitlementDefinition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<EntitlementDefinition> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the entitlement definitions that the user has permission to view where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the matching entitlement definitions that the user has permission to view
	 */
	@Override
	public List<EntitlementDefinition> filterFindByC_LikeN(
		long classNameId, String name) {

		return filterFindByC_LikeN(
			classNameId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<EntitlementDefinition> filterFindByC_LikeN(
		long classNameId, String name, int start, int end) {

		return filterFindByC_LikeN(classNameId, name, start, end, null);
	}

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
	@Override
	public List<EntitlementDefinition> filterFindByC_LikeN(
		long classNameId, String name, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByC_LikeN(
				classNameId, name, start, end, orderByComparator);
		}

		name = Objects.toString(name, "");

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_C_LIKEN_CLASSNAMEID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_LIKEN_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_C_LIKEN_NAME_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), EntitlementDefinition.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, EntitlementDefinitionImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, EntitlementDefinitionImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(classNameId);

			if (bindName) {
				queryPos.add(name);
			}

			return (List<EntitlementDefinition>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

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
	@Override
	public EntitlementDefinition[] filterFindByC_LikeN_PrevAndNext(
			long entitlementDefinitionId, long classNameId, String name,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByC_LikeN_PrevAndNext(
				entitlementDefinitionId, classNameId, name, orderByComparator);
		}

		name = Objects.toString(name, "");

		EntitlementDefinition entitlementDefinition = findByPrimaryKey(
			entitlementDefinitionId);

		Session session = null;

		try {
			session = openSession();

			EntitlementDefinition[] array = new EntitlementDefinitionImpl[3];

			array[0] = filterGetByC_LikeN_PrevAndNext(
				session, entitlementDefinition, classNameId, name,
				orderByComparator, true);

			array[1] = entitlementDefinition;

			array[2] = filterGetByC_LikeN_PrevAndNext(
				session, entitlementDefinition, classNameId, name,
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

	protected EntitlementDefinition filterGetByC_LikeN_PrevAndNext(
		Session session, EntitlementDefinition entitlementDefinition,
		long classNameId, String name,
		OrderByComparator<EntitlementDefinition> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_C_LIKEN_CLASSNAMEID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_LIKEN_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_C_LIKEN_NAME_2);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), EntitlementDefinition.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(
				_FILTER_ENTITY_ALIAS, EntitlementDefinitionImpl.class);
		}
		else {
			sqlQuery.addEntity(
				_FILTER_ENTITY_TABLE, EntitlementDefinitionImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(classNameId);

		if (bindName) {
			queryPos.add(name);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						entitlementDefinition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<EntitlementDefinition> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entitlement definitions where classNameId = &#63; and name LIKE &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 */
	@Override
	public void removeByC_LikeN(long classNameId, String name) {
		for (EntitlementDefinition entitlementDefinition :
				findByC_LikeN(
					classNameId, name, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(entitlementDefinition);
		}
	}

	/**
	 * Returns the number of entitlement definitions where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the number of matching entitlement definitions
	 */
	@Override
	public int countByC_LikeN(long classNameId, String name) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathWithPaginationCountByC_LikeN;

		Object[] finderArgs = new Object[] {classNameId, name};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ENTITLEMENTDEFINITION_WHERE);

			sb.append(_FINDER_COLUMN_C_LIKEN_CLASSNAMEID_2);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_LIKEN_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_C_LIKEN_NAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				if (bindName) {
					queryPos.add(name);
				}

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

	/**
	 * Returns the number of entitlement definitions that the user has permission to view where classNameId = &#63; and name LIKE &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param name the name
	 * @return the number of matching entitlement definitions that the user has permission to view
	 */
	@Override
	public int filterCountByC_LikeN(long classNameId, String name) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByC_LikeN(classNameId, name);
		}

		name = Objects.toString(name, "");

		StringBundler sb = new StringBundler(3);

		sb.append(_FILTER_SQL_COUNT_ENTITLEMENTDEFINITION_WHERE);

		sb.append(_FINDER_COLUMN_C_LIKEN_CLASSNAMEID_2);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_LIKEN_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_C_LIKEN_NAME_2);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), EntitlementDefinition.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(classNameId);

			if (bindName) {
				queryPos.add(name);
			}

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_C_LIKEN_CLASSNAMEID_2 =
		"entitlementDefinition.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_LIKEN_NAME_2 =
		"entitlementDefinition.name LIKE ?";

	private static final String _FINDER_COLUMN_C_LIKEN_NAME_3 =
		"(entitlementDefinition.name IS NULL OR entitlementDefinition.name LIKE '')";

	private FinderPath _finderPathWithPaginationFindByC_S;
	private FinderPath _finderPathWithoutPaginationFindByC_S;
	private FinderPath _finderPathCountByC_S;

	/**
	 * Returns all the entitlement definitions where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @return the matching entitlement definitions
	 */
	@Override
	public List<EntitlementDefinition> findByC_S(long classNameId, int status) {
		return findByC_S(
			classNameId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<EntitlementDefinition> findByC_S(
		long classNameId, int status, int start, int end) {

		return findByC_S(classNameId, status, start, end, null);
	}

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
	@Override
	public List<EntitlementDefinition> findByC_S(
		long classNameId, int status, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return findByC_S(
			classNameId, status, start, end, orderByComparator, true);
	}

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
	@Override
	public List<EntitlementDefinition> findByC_S(
		long classNameId, int status, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_S;
				finderArgs = new Object[] {classNameId, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_S;
			finderArgs = new Object[] {
				classNameId, status, start, end, orderByComparator
			};
		}

		List<EntitlementDefinition> list = null;

		if (useFinderCache) {
			list = (List<EntitlementDefinition>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EntitlementDefinition entitlementDefinition : list) {
					if ((classNameId !=
							entitlementDefinition.getClassNameId()) ||
						(status != entitlementDefinition.getStatus())) {

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

			sb.append(_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);

			sb.append(_FINDER_COLUMN_C_S_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				queryPos.add(status);

				list = (List<EntitlementDefinition>)QueryUtil.list(
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
	 * Returns the first entitlement definition in the ordered set where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition findByC_S_First(
			long classNameId, int status,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		EntitlementDefinition entitlementDefinition = fetchByC_S_First(
			classNameId, status, orderByComparator);

		if (entitlementDefinition != null) {
			return entitlementDefinition;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchEntitlementDefinitionException(sb.toString());
	}

	/**
	 * Returns the first entitlement definition in the ordered set where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition fetchByC_S_First(
		long classNameId, int status,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		List<EntitlementDefinition> list = findByC_S(
			classNameId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entitlement definition in the ordered set where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition findByC_S_Last(
			long classNameId, int status,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		EntitlementDefinition entitlementDefinition = fetchByC_S_Last(
			classNameId, status, orderByComparator);

		if (entitlementDefinition != null) {
			return entitlementDefinition;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchEntitlementDefinitionException(sb.toString());
	}

	/**
	 * Returns the last entitlement definition in the ordered set where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement definition, or <code>null</code> if a matching entitlement definition could not be found
	 */
	@Override
	public EntitlementDefinition fetchByC_S_Last(
		long classNameId, int status,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		int count = countByC_S(classNameId, status);

		if (count == 0) {
			return null;
		}

		List<EntitlementDefinition> list = findByC_S(
			classNameId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public EntitlementDefinition[] findByC_S_PrevAndNext(
			long entitlementDefinitionId, long classNameId, int status,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		EntitlementDefinition entitlementDefinition = findByPrimaryKey(
			entitlementDefinitionId);

		Session session = null;

		try {
			session = openSession();

			EntitlementDefinition[] array = new EntitlementDefinitionImpl[3];

			array[0] = getByC_S_PrevAndNext(
				session, entitlementDefinition, classNameId, status,
				orderByComparator, true);

			array[1] = entitlementDefinition;

			array[2] = getByC_S_PrevAndNext(
				session, entitlementDefinition, classNameId, status,
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

	protected EntitlementDefinition getByC_S_PrevAndNext(
		Session session, EntitlementDefinition entitlementDefinition,
		long classNameId, int status,
		OrderByComparator<EntitlementDefinition> orderByComparator,
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

		sb.append(_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);

		sb.append(_FINDER_COLUMN_C_S_CLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_C_S_STATUS_2);

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
			sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(classNameId);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						entitlementDefinition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<EntitlementDefinition> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the entitlement definitions that the user has permission to view where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @return the matching entitlement definitions that the user has permission to view
	 */
	@Override
	public List<EntitlementDefinition> filterFindByC_S(
		long classNameId, int status) {

		return filterFindByC_S(
			classNameId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<EntitlementDefinition> filterFindByC_S(
		long classNameId, int status, int start, int end) {

		return filterFindByC_S(classNameId, status, start, end, null);
	}

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
	@Override
	public List<EntitlementDefinition> filterFindByC_S(
		long classNameId, int status, int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByC_S(
				classNameId, status, start, end, orderByComparator);
		}

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_C_S_CLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_C_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), EntitlementDefinition.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, EntitlementDefinitionImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, EntitlementDefinitionImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(classNameId);

			queryPos.add(status);

			return (List<EntitlementDefinition>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

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
	@Override
	public EntitlementDefinition[] filterFindByC_S_PrevAndNext(
			long entitlementDefinitionId, long classNameId, int status,
			OrderByComparator<EntitlementDefinition> orderByComparator)
		throws NoSuchEntitlementDefinitionException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByC_S_PrevAndNext(
				entitlementDefinitionId, classNameId, status,
				orderByComparator);
		}

		EntitlementDefinition entitlementDefinition = findByPrimaryKey(
			entitlementDefinitionId);

		Session session = null;

		try {
			session = openSession();

			EntitlementDefinition[] array = new EntitlementDefinitionImpl[3];

			array[0] = filterGetByC_S_PrevAndNext(
				session, entitlementDefinition, classNameId, status,
				orderByComparator, true);

			array[1] = entitlementDefinition;

			array[2] = filterGetByC_S_PrevAndNext(
				session, entitlementDefinition, classNameId, status,
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

	protected EntitlementDefinition filterGetByC_S_PrevAndNext(
		Session session, EntitlementDefinition entitlementDefinition,
		long classNameId, int status,
		OrderByComparator<EntitlementDefinition> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_C_S_CLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_C_S_STATUS_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(EntitlementDefinitionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), EntitlementDefinition.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(
				_FILTER_ENTITY_ALIAS, EntitlementDefinitionImpl.class);
		}
		else {
			sqlQuery.addEntity(
				_FILTER_ENTITY_TABLE, EntitlementDefinitionImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(classNameId);

		queryPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						entitlementDefinition)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<EntitlementDefinition> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entitlement definitions where classNameId = &#63; and status = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 */
	@Override
	public void removeByC_S(long classNameId, int status) {
		for (EntitlementDefinition entitlementDefinition :
				findByC_S(
					classNameId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(entitlementDefinition);
		}
	}

	/**
	 * Returns the number of entitlement definitions where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @return the number of matching entitlement definitions
	 */
	@Override
	public int countByC_S(long classNameId, int status) {
		FinderPath finderPath = _finderPathCountByC_S;

		Object[] finderArgs = new Object[] {classNameId, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ENTITLEMENTDEFINITION_WHERE);

			sb.append(_FINDER_COLUMN_C_S_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_S_STATUS_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				queryPos.add(status);

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

	/**
	 * Returns the number of entitlement definitions that the user has permission to view where classNameId = &#63; and status = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param status the status
	 * @return the number of matching entitlement definitions that the user has permission to view
	 */
	@Override
	public int filterCountByC_S(long classNameId, int status) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByC_S(classNameId, status);
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_FILTER_SQL_COUNT_ENTITLEMENTDEFINITION_WHERE);

		sb.append(_FINDER_COLUMN_C_S_CLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_C_S_STATUS_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), EntitlementDefinition.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(classNameId);

			queryPos.add(status);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_C_S_CLASSNAMEID_2 =
		"entitlementDefinition.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_S_STATUS_2 =
		"entitlementDefinition.status = ?";

	public EntitlementDefinitionPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(EntitlementDefinition.class);

		setModelImplClass(EntitlementDefinitionImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the entitlement definition in the entity cache if it is enabled.
	 *
	 * @param entitlementDefinition the entitlement definition
	 */
	@Override
	public void cacheResult(EntitlementDefinition entitlementDefinition) {
		entityCache.putResult(
			entityCacheEnabled, EntitlementDefinitionImpl.class,
			entitlementDefinition.getPrimaryKey(), entitlementDefinition);

		finderCache.putResult(
			_finderPathFetchByEntitlementDefinitionKey,
			new Object[] {entitlementDefinition.getEntitlementDefinitionKey()},
			entitlementDefinition);

		finderCache.putResult(
			_finderPathFetchByC_N,
			new Object[] {
				entitlementDefinition.getClassNameId(),
				entitlementDefinition.getName()
			},
			entitlementDefinition);

		entitlementDefinition.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the entitlement definitions in the entity cache if it is enabled.
	 *
	 * @param entitlementDefinitions the entitlement definitions
	 */
	@Override
	public void cacheResult(
		List<EntitlementDefinition> entitlementDefinitions) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (entitlementDefinitions.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (EntitlementDefinition entitlementDefinition :
				entitlementDefinitions) {

			if (entityCache.getResult(
					entityCacheEnabled, EntitlementDefinitionImpl.class,
					entitlementDefinition.getPrimaryKey()) == null) {

				cacheResult(entitlementDefinition);
			}
			else {
				entitlementDefinition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all entitlement definitions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EntitlementDefinitionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the entitlement definition.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EntitlementDefinition entitlementDefinition) {
		entityCache.removeResult(
			entityCacheEnabled, EntitlementDefinitionImpl.class,
			entitlementDefinition.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(EntitlementDefinitionModelImpl)entitlementDefinition, true);
	}

	@Override
	public void clearCache(List<EntitlementDefinition> entitlementDefinitions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EntitlementDefinition entitlementDefinition :
				entitlementDefinitions) {

			entityCache.removeResult(
				entityCacheEnabled, EntitlementDefinitionImpl.class,
				entitlementDefinition.getPrimaryKey());

			clearUniqueFindersCache(
				(EntitlementDefinitionModelImpl)entitlementDefinition, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, EntitlementDefinitionImpl.class,
				primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		EntitlementDefinitionModelImpl entitlementDefinitionModelImpl) {

		Object[] args = new Object[] {
			entitlementDefinitionModelImpl.getEntitlementDefinitionKey()
		};

		finderCache.putResult(
			_finderPathCountByEntitlementDefinitionKey, args, Long.valueOf(1),
			false);
		finderCache.putResult(
			_finderPathFetchByEntitlementDefinitionKey, args,
			entitlementDefinitionModelImpl, false);

		args = new Object[] {
			entitlementDefinitionModelImpl.getClassNameId(),
			entitlementDefinitionModelImpl.getName()
		};

		finderCache.putResult(
			_finderPathCountByC_N, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_N, args, entitlementDefinitionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		EntitlementDefinitionModelImpl entitlementDefinitionModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				entitlementDefinitionModelImpl.getEntitlementDefinitionKey()
			};

			finderCache.removeResult(
				_finderPathCountByEntitlementDefinitionKey, args);
			finderCache.removeResult(
				_finderPathFetchByEntitlementDefinitionKey, args);
		}

		if ((entitlementDefinitionModelImpl.getColumnBitmask() &
			 _finderPathFetchByEntitlementDefinitionKey.getColumnBitmask()) !=
				 0) {

			Object[] args = new Object[] {
				entitlementDefinitionModelImpl.
					getOriginalEntitlementDefinitionKey()
			};

			finderCache.removeResult(
				_finderPathCountByEntitlementDefinitionKey, args);
			finderCache.removeResult(
				_finderPathFetchByEntitlementDefinitionKey, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				entitlementDefinitionModelImpl.getClassNameId(),
				entitlementDefinitionModelImpl.getName()
			};

			finderCache.removeResult(_finderPathCountByC_N, args);
			finderCache.removeResult(_finderPathFetchByC_N, args);
		}

		if ((entitlementDefinitionModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_N.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				entitlementDefinitionModelImpl.getOriginalClassNameId(),
				entitlementDefinitionModelImpl.getOriginalName()
			};

			finderCache.removeResult(_finderPathCountByC_N, args);
			finderCache.removeResult(_finderPathFetchByC_N, args);
		}
	}

	/**
	 * Creates a new entitlement definition with the primary key. Does not add the entitlement definition to the database.
	 *
	 * @param entitlementDefinitionId the primary key for the new entitlement definition
	 * @return the new entitlement definition
	 */
	@Override
	public EntitlementDefinition create(long entitlementDefinitionId) {
		EntitlementDefinition entitlementDefinition =
			new EntitlementDefinitionImpl();

		entitlementDefinition.setNew(true);
		entitlementDefinition.setPrimaryKey(entitlementDefinitionId);

		String uuid = PortalUUIDUtil.generate();

		entitlementDefinition.setUuid(uuid);

		entitlementDefinition.setCompanyId(CompanyThreadLocal.getCompanyId());

		return entitlementDefinition;
	}

	/**
	 * Removes the entitlement definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition that was removed
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	@Override
	public EntitlementDefinition remove(long entitlementDefinitionId)
		throws NoSuchEntitlementDefinitionException {

		return remove((Serializable)entitlementDefinitionId);
	}

	/**
	 * Removes the entitlement definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the entitlement definition
	 * @return the entitlement definition that was removed
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	@Override
	public EntitlementDefinition remove(Serializable primaryKey)
		throws NoSuchEntitlementDefinitionException {

		Session session = null;

		try {
			session = openSession();

			EntitlementDefinition entitlementDefinition =
				(EntitlementDefinition)session.get(
					EntitlementDefinitionImpl.class, primaryKey);

			if (entitlementDefinition == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntitlementDefinitionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(entitlementDefinition);
		}
		catch (NoSuchEntitlementDefinitionException noSuchEntityException) {
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
	protected EntitlementDefinition removeImpl(
		EntitlementDefinition entitlementDefinition) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(entitlementDefinition)) {
				entitlementDefinition = (EntitlementDefinition)session.get(
					EntitlementDefinitionImpl.class,
					entitlementDefinition.getPrimaryKeyObj());
			}

			if (entitlementDefinition != null) {
				session.delete(entitlementDefinition);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (entitlementDefinition != null) {
			clearCache(entitlementDefinition);
		}

		return entitlementDefinition;
	}

	@Override
	public EntitlementDefinition updateImpl(
		EntitlementDefinition entitlementDefinition) {

		boolean isNew = entitlementDefinition.isNew();

		if (!(entitlementDefinition instanceof
				EntitlementDefinitionModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(entitlementDefinition.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					entitlementDefinition);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in entitlementDefinition proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom EntitlementDefinition implementation " +
					entitlementDefinition.getClass());
		}

		EntitlementDefinitionModelImpl entitlementDefinitionModelImpl =
			(EntitlementDefinitionModelImpl)entitlementDefinition;

		if (Validator.isNull(entitlementDefinition.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			entitlementDefinition.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (entitlementDefinition.getCreateDate() == null)) {
			if (serviceContext == null) {
				entitlementDefinition.setCreateDate(date);
			}
			else {
				entitlementDefinition.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!entitlementDefinitionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				entitlementDefinition.setModifiedDate(date);
			}
			else {
				entitlementDefinition.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(entitlementDefinition);

				entitlementDefinition.setNew(false);
			}
			else {
				entitlementDefinition = (EntitlementDefinition)session.merge(
					entitlementDefinition);
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
			Object[] args = new Object[] {
				entitlementDefinitionModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				entitlementDefinitionModelImpl.getUuid(),
				entitlementDefinitionModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {
				entitlementDefinitionModelImpl.getClassNameId(),
				entitlementDefinitionModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByC_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_S, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((entitlementDefinitionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					entitlementDefinitionModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {entitlementDefinitionModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((entitlementDefinitionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					entitlementDefinitionModelImpl.getOriginalUuid(),
					entitlementDefinitionModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					entitlementDefinitionModelImpl.getUuid(),
					entitlementDefinitionModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((entitlementDefinitionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_S.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					entitlementDefinitionModelImpl.getOriginalClassNameId(),
					entitlementDefinitionModelImpl.getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByC_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_S, args);

				args = new Object[] {
					entitlementDefinitionModelImpl.getClassNameId(),
					entitlementDefinitionModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByC_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_S, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, EntitlementDefinitionImpl.class,
			entitlementDefinition.getPrimaryKey(), entitlementDefinition,
			false);

		clearUniqueFindersCache(entitlementDefinitionModelImpl, false);
		cacheUniqueFindersCache(entitlementDefinitionModelImpl);

		entitlementDefinition.resetOriginalValues();

		return entitlementDefinition;
	}

	/**
	 * Returns the entitlement definition with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the entitlement definition
	 * @return the entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	@Override
	public EntitlementDefinition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntitlementDefinitionException {

		EntitlementDefinition entitlementDefinition = fetchByPrimaryKey(
			primaryKey);

		if (entitlementDefinition == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntitlementDefinitionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return entitlementDefinition;
	}

	/**
	 * Returns the entitlement definition with the primary key or throws a <code>NoSuchEntitlementDefinitionException</code> if it could not be found.
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition
	 * @throws NoSuchEntitlementDefinitionException if a entitlement definition with the primary key could not be found
	 */
	@Override
	public EntitlementDefinition findByPrimaryKey(long entitlementDefinitionId)
		throws NoSuchEntitlementDefinitionException {

		return findByPrimaryKey((Serializable)entitlementDefinitionId);
	}

	/**
	 * Returns the entitlement definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entitlementDefinitionId the primary key of the entitlement definition
	 * @return the entitlement definition, or <code>null</code> if a entitlement definition with the primary key could not be found
	 */
	@Override
	public EntitlementDefinition fetchByPrimaryKey(
		long entitlementDefinitionId) {

		return fetchByPrimaryKey((Serializable)entitlementDefinitionId);
	}

	/**
	 * Returns all the entitlement definitions.
	 *
	 * @return the entitlement definitions
	 */
	@Override
	public List<EntitlementDefinition> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<EntitlementDefinition> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<EntitlementDefinition> findAll(
		int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<EntitlementDefinition> findAll(
		int start, int end,
		OrderByComparator<EntitlementDefinition> orderByComparator,
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

		List<EntitlementDefinition> list = null;

		if (useFinderCache) {
			list = (List<EntitlementDefinition>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ENTITLEMENTDEFINITION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ENTITLEMENTDEFINITION;

				sql = sql.concat(EntitlementDefinitionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<EntitlementDefinition>)QueryUtil.list(
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
	 * Removes all the entitlement definitions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EntitlementDefinition entitlementDefinition : findAll()) {
			remove(entitlementDefinition);
		}
	}

	/**
	 * Returns the number of entitlement definitions.
	 *
	 * @return the number of entitlement definitions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_ENTITLEMENTDEFINITION);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "entitlementDefinitionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ENTITLEMENTDEFINITION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EntitlementDefinitionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the entitlement definition persistence.
	 */
	@Activate
	public void activate() {
		EntitlementDefinitionModelImpl.setEntityCacheEnabled(
			entityCacheEnabled);
		EntitlementDefinitionModelImpl.setFinderCacheEnabled(
			finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			EntitlementDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			EntitlementDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			EntitlementDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			EntitlementDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			EntitlementDefinitionModelImpl.UUID_COLUMN_BITMASK |
			EntitlementDefinitionModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			EntitlementDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			EntitlementDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			EntitlementDefinitionModelImpl.UUID_COLUMN_BITMASK |
			EntitlementDefinitionModelImpl.COMPANYID_COLUMN_BITMASK |
			EntitlementDefinitionModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathFetchByEntitlementDefinitionKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			EntitlementDefinitionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByEntitlementDefinitionKey",
			new String[] {String.class.getName()},
			EntitlementDefinitionModelImpl.
				ENTITLEMENTDEFINITIONKEY_COLUMN_BITMASK);

		_finderPathCountByEntitlementDefinitionKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByEntitlementDefinitionKey",
			new String[] {String.class.getName()});

		_finderPathFetchByC_N = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			EntitlementDefinitionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_N",
			new String[] {Long.class.getName(), String.class.getName()},
			EntitlementDefinitionModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			EntitlementDefinitionModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByC_N = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByC_LikeN = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			EntitlementDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_LikeN",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByC_LikeN = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_LikeN",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByC_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			EntitlementDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_S",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			EntitlementDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_S",
			new String[] {Long.class.getName(), Integer.class.getName()},
			EntitlementDefinitionModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			EntitlementDefinitionModelImpl.STATUS_COLUMN_BITMASK |
			EntitlementDefinitionModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByC_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_S",
			new String[] {Long.class.getName(), Integer.class.getName()});

		EntitlementDefinitionUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		EntitlementDefinitionUtil.setPersistence(null);

		entityCache.removeCache(EntitlementDefinitionImpl.class.getName());

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
				"value.object.column.bitmask.enabled.com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition"),
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

	private static final String _SQL_SELECT_ENTITLEMENTDEFINITION =
		"SELECT entitlementDefinition FROM EntitlementDefinition entitlementDefinition";

	private static final String _SQL_SELECT_ENTITLEMENTDEFINITION_WHERE =
		"SELECT entitlementDefinition FROM EntitlementDefinition entitlementDefinition WHERE ";

	private static final String _SQL_COUNT_ENTITLEMENTDEFINITION =
		"SELECT COUNT(entitlementDefinition) FROM EntitlementDefinition entitlementDefinition";

	private static final String _SQL_COUNT_ENTITLEMENTDEFINITION_WHERE =
		"SELECT COUNT(entitlementDefinition) FROM EntitlementDefinition entitlementDefinition WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"entitlementDefinition.entitlementDefinitionId";

	private static final String _FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_WHERE =
		"SELECT DISTINCT {entitlementDefinition.*} FROM Koroneiki_EntitlementDefinition entitlementDefinition WHERE ";

	private static final String
		_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {Koroneiki_EntitlementDefinition.*} FROM (SELECT DISTINCT entitlementDefinition.entitlementDefinitionId FROM Koroneiki_EntitlementDefinition entitlementDefinition WHERE ";

	private static final String
		_FILTER_SQL_SELECT_ENTITLEMENTDEFINITION_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN Koroneiki_EntitlementDefinition ON TEMP_TABLE.entitlementDefinitionId = Koroneiki_EntitlementDefinition.entitlementDefinitionId";

	private static final String _FILTER_SQL_COUNT_ENTITLEMENTDEFINITION_WHERE =
		"SELECT COUNT(DISTINCT entitlementDefinition.entitlementDefinitionId) AS COUNT_VALUE FROM Koroneiki_EntitlementDefinition entitlementDefinition WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "entitlementDefinition";

	private static final String _FILTER_ENTITY_TABLE =
		"Koroneiki_EntitlementDefinition";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"entitlementDefinition.";

	private static final String _ORDER_BY_ENTITY_TABLE =
		"Koroneiki_EntitlementDefinition.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No EntitlementDefinition exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No EntitlementDefinition exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		EntitlementDefinitionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}