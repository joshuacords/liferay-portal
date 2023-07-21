/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.service.persistence.impl;

import com.liferay.osb.koroneiki.phytohormone.exception.NoSuchEntitlementException;
import com.liferay.osb.koroneiki.phytohormone.model.Entitlement;
import com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementImpl;
import com.liferay.osb.koroneiki.phytohormone.model.impl.EntitlementModelImpl;
import com.liferay.osb.koroneiki.phytohormone.service.persistence.EntitlementPersistence;
import com.liferay.osb.koroneiki.phytohormone.service.persistence.EntitlementUtil;
import com.liferay.osb.koroneiki.phytohormone.service.persistence.impl.constants.KoroneikiPersistenceConstants;
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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the entitlement service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = EntitlementPersistence.class)
public class EntitlementPersistenceImpl
	extends BasePersistenceImpl<Entitlement> implements EntitlementPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>EntitlementUtil</code> to access the entitlement persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		EntitlementImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByEntitlementDefinitionId;
	private FinderPath
		_finderPathWithoutPaginationFindByEntitlementDefinitionId;
	private FinderPath _finderPathCountByEntitlementDefinitionId;

	/**
	 * Returns all the entitlements where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @return the matching entitlements
	 */
	@Override
	public List<Entitlement> findByEntitlementDefinitionId(
		long entitlementDefinitionId) {

		return findByEntitlementDefinitionId(
			entitlementDefinitionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the entitlements where entitlementDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @return the range of matching entitlements
	 */
	@Override
	public List<Entitlement> findByEntitlementDefinitionId(
		long entitlementDefinitionId, int start, int end) {

		return findByEntitlementDefinitionId(
			entitlementDefinitionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entitlements where entitlementDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entitlements
	 */
	@Override
	public List<Entitlement> findByEntitlementDefinitionId(
		long entitlementDefinitionId, int start, int end,
		OrderByComparator<Entitlement> orderByComparator) {

		return findByEntitlementDefinitionId(
			entitlementDefinitionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the entitlements where entitlementDefinitionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entitlements
	 */
	@Override
	public List<Entitlement> findByEntitlementDefinitionId(
		long entitlementDefinitionId, int start, int end,
		OrderByComparator<Entitlement> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByEntitlementDefinitionId;
				finderArgs = new Object[] {entitlementDefinitionId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByEntitlementDefinitionId;
			finderArgs = new Object[] {
				entitlementDefinitionId, start, end, orderByComparator
			};
		}

		List<Entitlement> list = null;

		if (useFinderCache) {
			list = (List<Entitlement>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Entitlement entitlement : list) {
					if (entitlementDefinitionId !=
							entitlement.getEntitlementDefinitionId()) {

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

			sb.append(_SQL_SELECT_ENTITLEMENT_WHERE);

			sb.append(
				_FINDER_COLUMN_ENTITLEMENTDEFINITIONID_ENTITLEMENTDEFINITIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EntitlementModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(entitlementDefinitionId);

				list = (List<Entitlement>)QueryUtil.list(
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
	 * Returns the first entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement
	 * @throws NoSuchEntitlementException if a matching entitlement could not be found
	 */
	@Override
	public Entitlement findByEntitlementDefinitionId_First(
			long entitlementDefinitionId,
			OrderByComparator<Entitlement> orderByComparator)
		throws NoSuchEntitlementException {

		Entitlement entitlement = fetchByEntitlementDefinitionId_First(
			entitlementDefinitionId, orderByComparator);

		if (entitlement != null) {
			return entitlement;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("entitlementDefinitionId=");
		sb.append(entitlementDefinitionId);

		sb.append("}");

		throw new NoSuchEntitlementException(sb.toString());
	}

	/**
	 * Returns the first entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement, or <code>null</code> if a matching entitlement could not be found
	 */
	@Override
	public Entitlement fetchByEntitlementDefinitionId_First(
		long entitlementDefinitionId,
		OrderByComparator<Entitlement> orderByComparator) {

		List<Entitlement> list = findByEntitlementDefinitionId(
			entitlementDefinitionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement
	 * @throws NoSuchEntitlementException if a matching entitlement could not be found
	 */
	@Override
	public Entitlement findByEntitlementDefinitionId_Last(
			long entitlementDefinitionId,
			OrderByComparator<Entitlement> orderByComparator)
		throws NoSuchEntitlementException {

		Entitlement entitlement = fetchByEntitlementDefinitionId_Last(
			entitlementDefinitionId, orderByComparator);

		if (entitlement != null) {
			return entitlement;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("entitlementDefinitionId=");
		sb.append(entitlementDefinitionId);

		sb.append("}");

		throw new NoSuchEntitlementException(sb.toString());
	}

	/**
	 * Returns the last entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement, or <code>null</code> if a matching entitlement could not be found
	 */
	@Override
	public Entitlement fetchByEntitlementDefinitionId_Last(
		long entitlementDefinitionId,
		OrderByComparator<Entitlement> orderByComparator) {

		int count = countByEntitlementDefinitionId(entitlementDefinitionId);

		if (count == 0) {
			return null;
		}

		List<Entitlement> list = findByEntitlementDefinitionId(
			entitlementDefinitionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entitlements before and after the current entitlement in the ordered set where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementId the primary key of the current entitlement
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement
	 * @throws NoSuchEntitlementException if a entitlement with the primary key could not be found
	 */
	@Override
	public Entitlement[] findByEntitlementDefinitionId_PrevAndNext(
			long entitlementId, long entitlementDefinitionId,
			OrderByComparator<Entitlement> orderByComparator)
		throws NoSuchEntitlementException {

		Entitlement entitlement = findByPrimaryKey(entitlementId);

		Session session = null;

		try {
			session = openSession();

			Entitlement[] array = new EntitlementImpl[3];

			array[0] = getByEntitlementDefinitionId_PrevAndNext(
				session, entitlement, entitlementDefinitionId,
				orderByComparator, true);

			array[1] = entitlement;

			array[2] = getByEntitlementDefinitionId_PrevAndNext(
				session, entitlement, entitlementDefinitionId,
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

	protected Entitlement getByEntitlementDefinitionId_PrevAndNext(
		Session session, Entitlement entitlement, long entitlementDefinitionId,
		OrderByComparator<Entitlement> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ENTITLEMENT_WHERE);

		sb.append(
			_FINDER_COLUMN_ENTITLEMENTDEFINITIONID_ENTITLEMENTDEFINITIONID_2);

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
			sb.append(EntitlementModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(entitlementDefinitionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(entitlement)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Entitlement> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entitlements where entitlementDefinitionId = &#63; from the database.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 */
	@Override
	public void removeByEntitlementDefinitionId(long entitlementDefinitionId) {
		for (Entitlement entitlement :
				findByEntitlementDefinitionId(
					entitlementDefinitionId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(entitlement);
		}
	}

	/**
	 * Returns the number of entitlements where entitlementDefinitionId = &#63;.
	 *
	 * @param entitlementDefinitionId the entitlement definition ID
	 * @return the number of matching entitlements
	 */
	@Override
	public int countByEntitlementDefinitionId(long entitlementDefinitionId) {
		FinderPath finderPath = _finderPathCountByEntitlementDefinitionId;

		Object[] finderArgs = new Object[] {entitlementDefinitionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ENTITLEMENT_WHERE);

			sb.append(
				_FINDER_COLUMN_ENTITLEMENTDEFINITIONID_ENTITLEMENTDEFINITIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(entitlementDefinitionId);

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
		_FINDER_COLUMN_ENTITLEMENTDEFINITIONID_ENTITLEMENTDEFINITIONID_2 =
			"entitlement.entitlementDefinitionId = ?";

	private FinderPath _finderPathWithPaginationFindByC_C;
	private FinderPath _finderPathWithoutPaginationFindByC_C;
	private FinderPath _finderPathCountByC_C;

	/**
	 * Returns all the entitlements where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching entitlements
	 */
	@Override
	public List<Entitlement> findByC_C(long classNameId, long classPK) {
		return findByC_C(
			classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entitlements where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @return the range of matching entitlements
	 */
	@Override
	public List<Entitlement> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the entitlements where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching entitlements
	 */
	@Override
	public List<Entitlement> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<Entitlement> orderByComparator) {

		return findByC_C(
			classNameId, classPK, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the entitlements where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching entitlements
	 */
	@Override
	public List<Entitlement> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<Entitlement> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_C;
				finderArgs = new Object[] {classNameId, classPK};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_C;
			finderArgs = new Object[] {
				classNameId, classPK, start, end, orderByComparator
			};
		}

		List<Entitlement> list = null;

		if (useFinderCache) {
			list = (List<Entitlement>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Entitlement entitlement : list) {
					if ((classNameId != entitlement.getClassNameId()) ||
						(classPK != entitlement.getClassPK())) {

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

			sb.append(_SQL_SELECT_ENTITLEMENT_WHERE);

			sb.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(EntitlementModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				queryPos.add(classPK);

				list = (List<Entitlement>)QueryUtil.list(
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
	 * Returns the first entitlement in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement
	 * @throws NoSuchEntitlementException if a matching entitlement could not be found
	 */
	@Override
	public Entitlement findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<Entitlement> orderByComparator)
		throws NoSuchEntitlementException {

		Entitlement entitlement = fetchByC_C_First(
			classNameId, classPK, orderByComparator);

		if (entitlement != null) {
			return entitlement;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchEntitlementException(sb.toString());
	}

	/**
	 * Returns the first entitlement in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entitlement, or <code>null</code> if a matching entitlement could not be found
	 */
	@Override
	public Entitlement fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<Entitlement> orderByComparator) {

		List<Entitlement> list = findByC_C(
			classNameId, classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entitlement in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement
	 * @throws NoSuchEntitlementException if a matching entitlement could not be found
	 */
	@Override
	public Entitlement findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<Entitlement> orderByComparator)
		throws NoSuchEntitlementException {

		Entitlement entitlement = fetchByC_C_Last(
			classNameId, classPK, orderByComparator);

		if (entitlement != null) {
			return entitlement;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchEntitlementException(sb.toString());
	}

	/**
	 * Returns the last entitlement in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entitlement, or <code>null</code> if a matching entitlement could not be found
	 */
	@Override
	public Entitlement fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<Entitlement> orderByComparator) {

		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<Entitlement> list = findByC_C(
			classNameId, classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entitlements before and after the current entitlement in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param entitlementId the primary key of the current entitlement
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entitlement
	 * @throws NoSuchEntitlementException if a entitlement with the primary key could not be found
	 */
	@Override
	public Entitlement[] findByC_C_PrevAndNext(
			long entitlementId, long classNameId, long classPK,
			OrderByComparator<Entitlement> orderByComparator)
		throws NoSuchEntitlementException {

		Entitlement entitlement = findByPrimaryKey(entitlementId);

		Session session = null;

		try {
			session = openSession();

			Entitlement[] array = new EntitlementImpl[3];

			array[0] = getByC_C_PrevAndNext(
				session, entitlement, classNameId, classPK, orderByComparator,
				true);

			array[1] = entitlement;

			array[2] = getByC_C_PrevAndNext(
				session, entitlement, classNameId, classPK, orderByComparator,
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

	protected Entitlement getByC_C_PrevAndNext(
		Session session, Entitlement entitlement, long classNameId,
		long classPK, OrderByComparator<Entitlement> orderByComparator,
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

		sb.append(_SQL_SELECT_ENTITLEMENT_WHERE);

		sb.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_C_C_CLASSPK_2);

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
			sb.append(EntitlementModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(classNameId);

		queryPos.add(classPK);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(entitlement)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<Entitlement> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entitlements where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (Entitlement entitlement :
				findByC_C(
					classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(entitlement);
		}
	}

	/**
	 * Returns the number of entitlements where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching entitlements
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = _finderPathCountByC_C;

		Object[] finderArgs = new Object[] {classNameId, classPK};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ENTITLEMENT_WHERE);

			sb.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				queryPos.add(classPK);

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

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 =
		"entitlement.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 =
		"entitlement.classPK = ?";

	public EntitlementPersistenceImpl() {
		setModelClass(Entitlement.class);

		setModelImplClass(EntitlementImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the entitlement in the entity cache if it is enabled.
	 *
	 * @param entitlement the entitlement
	 */
	@Override
	public void cacheResult(Entitlement entitlement) {
		entityCache.putResult(
			entityCacheEnabled, EntitlementImpl.class,
			entitlement.getPrimaryKey(), entitlement);

		entitlement.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the entitlements in the entity cache if it is enabled.
	 *
	 * @param entitlements the entitlements
	 */
	@Override
	public void cacheResult(List<Entitlement> entitlements) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (entitlements.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Entitlement entitlement : entitlements) {
			if (entityCache.getResult(
					entityCacheEnabled, EntitlementImpl.class,
					entitlement.getPrimaryKey()) == null) {

				cacheResult(entitlement);
			}
			else {
				entitlement.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all entitlements.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EntitlementImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the entitlement.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Entitlement entitlement) {
		entityCache.removeResult(
			entityCacheEnabled, EntitlementImpl.class,
			entitlement.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Entitlement> entitlements) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Entitlement entitlement : entitlements) {
			entityCache.removeResult(
				entityCacheEnabled, EntitlementImpl.class,
				entitlement.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, EntitlementImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new entitlement with the primary key. Does not add the entitlement to the database.
	 *
	 * @param entitlementId the primary key for the new entitlement
	 * @return the new entitlement
	 */
	@Override
	public Entitlement create(long entitlementId) {
		Entitlement entitlement = new EntitlementImpl();

		entitlement.setNew(true);
		entitlement.setPrimaryKey(entitlementId);

		entitlement.setCompanyId(CompanyThreadLocal.getCompanyId());

		return entitlement;
	}

	/**
	 * Removes the entitlement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entitlementId the primary key of the entitlement
	 * @return the entitlement that was removed
	 * @throws NoSuchEntitlementException if a entitlement with the primary key could not be found
	 */
	@Override
	public Entitlement remove(long entitlementId)
		throws NoSuchEntitlementException {

		return remove((Serializable)entitlementId);
	}

	/**
	 * Removes the entitlement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the entitlement
	 * @return the entitlement that was removed
	 * @throws NoSuchEntitlementException if a entitlement with the primary key could not be found
	 */
	@Override
	public Entitlement remove(Serializable primaryKey)
		throws NoSuchEntitlementException {

		Session session = null;

		try {
			session = openSession();

			Entitlement entitlement = (Entitlement)session.get(
				EntitlementImpl.class, primaryKey);

			if (entitlement == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntitlementException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(entitlement);
		}
		catch (NoSuchEntitlementException noSuchEntityException) {
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
	protected Entitlement removeImpl(Entitlement entitlement) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(entitlement)) {
				entitlement = (Entitlement)session.get(
					EntitlementImpl.class, entitlement.getPrimaryKeyObj());
			}

			if (entitlement != null) {
				session.delete(entitlement);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (entitlement != null) {
			clearCache(entitlement);
		}

		return entitlement;
	}

	@Override
	public Entitlement updateImpl(Entitlement entitlement) {
		boolean isNew = entitlement.isNew();

		if (!(entitlement instanceof EntitlementModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(entitlement.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(entitlement);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in entitlement proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Entitlement implementation " +
					entitlement.getClass());
		}

		EntitlementModelImpl entitlementModelImpl =
			(EntitlementModelImpl)entitlement;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (entitlement.getCreateDate() == null)) {
			if (serviceContext == null) {
				entitlement.setCreateDate(date);
			}
			else {
				entitlement.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!entitlementModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				entitlement.setModifiedDate(date);
			}
			else {
				entitlement.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(entitlement);

				entitlement.setNew(false);
			}
			else {
				entitlement = (Entitlement)session.merge(entitlement);
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
				entitlementModelImpl.getEntitlementDefinitionId()
			};

			finderCache.removeResult(
				_finderPathCountByEntitlementDefinitionId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByEntitlementDefinitionId,
				args);

			args = new Object[] {
				entitlementModelImpl.getClassNameId(),
				entitlementModelImpl.getClassPK()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_C, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((entitlementModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByEntitlementDefinitionId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					entitlementModelImpl.getOriginalEntitlementDefinitionId()
				};

				finderCache.removeResult(
					_finderPathCountByEntitlementDefinitionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByEntitlementDefinitionId,
					args);

				args = new Object[] {
					entitlementModelImpl.getEntitlementDefinitionId()
				};

				finderCache.removeResult(
					_finderPathCountByEntitlementDefinitionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByEntitlementDefinitionId,
					args);
			}

			if ((entitlementModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					entitlementModelImpl.getOriginalClassNameId(),
					entitlementModelImpl.getOriginalClassPK()
				};

				finderCache.removeResult(_finderPathCountByC_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_C, args);

				args = new Object[] {
					entitlementModelImpl.getClassNameId(),
					entitlementModelImpl.getClassPK()
				};

				finderCache.removeResult(_finderPathCountByC_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_C, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, EntitlementImpl.class,
			entitlement.getPrimaryKey(), entitlement, false);

		entitlement.resetOriginalValues();

		return entitlement;
	}

	/**
	 * Returns the entitlement with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the entitlement
	 * @return the entitlement
	 * @throws NoSuchEntitlementException if a entitlement with the primary key could not be found
	 */
	@Override
	public Entitlement findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntitlementException {

		Entitlement entitlement = fetchByPrimaryKey(primaryKey);

		if (entitlement == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntitlementException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return entitlement;
	}

	/**
	 * Returns the entitlement with the primary key or throws a <code>NoSuchEntitlementException</code> if it could not be found.
	 *
	 * @param entitlementId the primary key of the entitlement
	 * @return the entitlement
	 * @throws NoSuchEntitlementException if a entitlement with the primary key could not be found
	 */
	@Override
	public Entitlement findByPrimaryKey(long entitlementId)
		throws NoSuchEntitlementException {

		return findByPrimaryKey((Serializable)entitlementId);
	}

	/**
	 * Returns the entitlement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entitlementId the primary key of the entitlement
	 * @return the entitlement, or <code>null</code> if a entitlement with the primary key could not be found
	 */
	@Override
	public Entitlement fetchByPrimaryKey(long entitlementId) {
		return fetchByPrimaryKey((Serializable)entitlementId);
	}

	/**
	 * Returns all the entitlements.
	 *
	 * @return the entitlements
	 */
	@Override
	public List<Entitlement> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the entitlements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @return the range of entitlements
	 */
	@Override
	public List<Entitlement> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the entitlements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of entitlements
	 */
	@Override
	public List<Entitlement> findAll(
		int start, int end, OrderByComparator<Entitlement> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the entitlements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EntitlementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of entitlements
	 * @param end the upper bound of the range of entitlements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of entitlements
	 */
	@Override
	public List<Entitlement> findAll(
		int start, int end, OrderByComparator<Entitlement> orderByComparator,
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

		List<Entitlement> list = null;

		if (useFinderCache) {
			list = (List<Entitlement>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ENTITLEMENT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ENTITLEMENT;

				sql = sql.concat(EntitlementModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Entitlement>)QueryUtil.list(
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
	 * Removes all the entitlements from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Entitlement entitlement : findAll()) {
			remove(entitlement);
		}
	}

	/**
	 * Returns the number of entitlements.
	 *
	 * @return the number of entitlements
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ENTITLEMENT);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "entitlementId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ENTITLEMENT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EntitlementModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the entitlement persistence.
	 */
	@Activate
	public void activate() {
		EntitlementModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		EntitlementModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntitlementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntitlementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByEntitlementDefinitionId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntitlementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByEntitlementDefinitionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByEntitlementDefinitionId =
			new FinderPath(
				entityCacheEnabled, finderCacheEnabled, EntitlementImpl.class,
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByEntitlementDefinitionId",
				new String[] {Long.class.getName()},
				EntitlementModelImpl.ENTITLEMENTDEFINITIONID_COLUMN_BITMASK |
				EntitlementModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByEntitlementDefinitionId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByEntitlementDefinitionId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByC_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntitlementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, EntitlementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			EntitlementModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			EntitlementModelImpl.CLASSPK_COLUMN_BITMASK |
			EntitlementModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByC_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] {Long.class.getName(), Long.class.getName()});

		EntitlementUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		EntitlementUtil.setPersistence(null);

		entityCache.removeCache(EntitlementImpl.class.getName());

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
				"value.object.column.bitmask.enabled.com.liferay.osb.koroneiki.phytohormone.model.Entitlement"),
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

	private static final String _SQL_SELECT_ENTITLEMENT =
		"SELECT entitlement FROM Entitlement entitlement";

	private static final String _SQL_SELECT_ENTITLEMENT_WHERE =
		"SELECT entitlement FROM Entitlement entitlement WHERE ";

	private static final String _SQL_COUNT_ENTITLEMENT =
		"SELECT COUNT(entitlement) FROM Entitlement entitlement";

	private static final String _SQL_COUNT_ENTITLEMENT_WHERE =
		"SELECT COUNT(entitlement) FROM Entitlement entitlement WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "entitlement.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Entitlement exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Entitlement exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		EntitlementPersistenceImpl.class);

}