/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.service.persistence.impl;

import com.liferay.osb.koroneiki.root.exception.NoSuchAuditEntryException;
import com.liferay.osb.koroneiki.root.model.AuditEntry;
import com.liferay.osb.koroneiki.root.model.impl.AuditEntryImpl;
import com.liferay.osb.koroneiki.root.model.impl.AuditEntryModelImpl;
import com.liferay.osb.koroneiki.root.service.persistence.AuditEntryPersistence;
import com.liferay.osb.koroneiki.root.service.persistence.AuditEntryUtil;
import com.liferay.osb.koroneiki.root.service.persistence.impl.constants.KoroneikiPersistenceConstants;
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
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the audit entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = AuditEntryPersistence.class)
public class AuditEntryPersistenceImpl
	extends BasePersistenceImpl<AuditEntry> implements AuditEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AuditEntryUtil</code> to access the audit entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AuditEntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByAuditEntryKey;
	private FinderPath _finderPathCountByAuditEntryKey;

	/**
	 * Returns the audit entry where auditEntryKey = &#63; or throws a <code>NoSuchAuditEntryException</code> if it could not be found.
	 *
	 * @param auditEntryKey the audit entry key
	 * @return the matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	@Override
	public AuditEntry findByAuditEntryKey(String auditEntryKey)
		throws NoSuchAuditEntryException {

		AuditEntry auditEntry = fetchByAuditEntryKey(auditEntryKey);

		if (auditEntry == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("auditEntryKey=");
			sb.append(auditEntryKey);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchAuditEntryException(sb.toString());
		}

		return auditEntry;
	}

	/**
	 * Returns the audit entry where auditEntryKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param auditEntryKey the audit entry key
	 * @return the matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	@Override
	public AuditEntry fetchByAuditEntryKey(String auditEntryKey) {
		return fetchByAuditEntryKey(auditEntryKey, true);
	}

	/**
	 * Returns the audit entry where auditEntryKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param auditEntryKey the audit entry key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	@Override
	public AuditEntry fetchByAuditEntryKey(
		String auditEntryKey, boolean useFinderCache) {

		auditEntryKey = Objects.toString(auditEntryKey, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {auditEntryKey};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByAuditEntryKey, finderArgs, this);
		}

		if (result instanceof AuditEntry) {
			AuditEntry auditEntry = (AuditEntry)result;

			if (!Objects.equals(auditEntryKey, auditEntry.getAuditEntryKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_AUDITENTRY_WHERE);

			boolean bindAuditEntryKey = false;

			if (auditEntryKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_AUDITENTRYKEY_AUDITENTRYKEY_3);
			}
			else {
				bindAuditEntryKey = true;

				sb.append(_FINDER_COLUMN_AUDITENTRYKEY_AUDITENTRYKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAuditEntryKey) {
					queryPos.add(auditEntryKey);
				}

				List<AuditEntry> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByAuditEntryKey, finderArgs, list);
					}
				}
				else {
					AuditEntry auditEntry = list.get(0);

					result = auditEntry;

					cacheResult(auditEntry);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByAuditEntryKey, finderArgs);
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
			return (AuditEntry)result;
		}
	}

	/**
	 * Removes the audit entry where auditEntryKey = &#63; from the database.
	 *
	 * @param auditEntryKey the audit entry key
	 * @return the audit entry that was removed
	 */
	@Override
	public AuditEntry removeByAuditEntryKey(String auditEntryKey)
		throws NoSuchAuditEntryException {

		AuditEntry auditEntry = findByAuditEntryKey(auditEntryKey);

		return remove(auditEntry);
	}

	/**
	 * Returns the number of audit entries where auditEntryKey = &#63;.
	 *
	 * @param auditEntryKey the audit entry key
	 * @return the number of matching audit entries
	 */
	@Override
	public int countByAuditEntryKey(String auditEntryKey) {
		auditEntryKey = Objects.toString(auditEntryKey, "");

		FinderPath finderPath = _finderPathCountByAuditEntryKey;

		Object[] finderArgs = new Object[] {auditEntryKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_AUDITENTRY_WHERE);

			boolean bindAuditEntryKey = false;

			if (auditEntryKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_AUDITENTRYKEY_AUDITENTRYKEY_3);
			}
			else {
				bindAuditEntryKey = true;

				sb.append(_FINDER_COLUMN_AUDITENTRYKEY_AUDITENTRYKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAuditEntryKey) {
					queryPos.add(auditEntryKey);
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

	private static final String _FINDER_COLUMN_AUDITENTRYKEY_AUDITENTRYKEY_2 =
		"auditEntry.auditEntryKey = ?";

	private static final String _FINDER_COLUMN_AUDITENTRYKEY_AUDITENTRYKEY_3 =
		"(auditEntry.auditEntryKey IS NULL OR auditEntry.auditEntryKey = '')";

	private FinderPath _finderPathWithPaginationFindByC_C;
	private FinderPath _finderPathWithoutPaginationFindByC_C;
	private FinderPath _finderPathCountByC_C;

	/**
	 * Returns all the audit entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching audit entries
	 */
	@Override
	public List<AuditEntry> findByC_C(long classNameId, long classPK) {
		return findByC_C(
			classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of matching audit entries
	 */
	@Override
	public List<AuditEntry> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit entries
	 */
	@Override
	public List<AuditEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator) {

		return findByC_C(
			classNameId, classPK, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching audit entries
	 */
	@Override
	public List<AuditEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator,
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

		List<AuditEntry> list = null;

		if (useFinderCache) {
			list = (List<AuditEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AuditEntry auditEntry : list) {
					if ((classNameId != auditEntry.getClassNameId()) ||
						(classPK != auditEntry.getClassPK())) {

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

			sb.append(_SQL_SELECT_AUDITENTRY_WHERE);

			sb.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AuditEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				queryPos.add(classPK);

				list = (List<AuditEntry>)QueryUtil.list(
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
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	@Override
	public AuditEntry findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException {

		AuditEntry auditEntry = fetchByC_C_First(
			classNameId, classPK, orderByComparator);

		if (auditEntry != null) {
			return auditEntry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchAuditEntryException(sb.toString());
	}

	/**
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	@Override
	public AuditEntry fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<AuditEntry> orderByComparator) {

		List<AuditEntry> list = findByC_C(
			classNameId, classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	@Override
	public AuditEntry findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException {

		AuditEntry auditEntry = fetchByC_C_Last(
			classNameId, classPK, orderByComparator);

		if (auditEntry != null) {
			return auditEntry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchAuditEntryException(sb.toString());
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	@Override
	public AuditEntry fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<AuditEntry> orderByComparator) {

		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<AuditEntry> list = findByC_C(
			classNameId, classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit entries before and after the current audit entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param auditEntryId the primary key of the current audit entry
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit entry
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	@Override
	public AuditEntry[] findByC_C_PrevAndNext(
			long auditEntryId, long classNameId, long classPK,
			OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException {

		AuditEntry auditEntry = findByPrimaryKey(auditEntryId);

		Session session = null;

		try {
			session = openSession();

			AuditEntry[] array = new AuditEntryImpl[3];

			array[0] = getByC_C_PrevAndNext(
				session, auditEntry, classNameId, classPK, orderByComparator,
				true);

			array[1] = auditEntry;

			array[2] = getByC_C_PrevAndNext(
				session, auditEntry, classNameId, classPK, orderByComparator,
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

	protected AuditEntry getByC_C_PrevAndNext(
		Session session, AuditEntry auditEntry, long classNameId, long classPK,
		OrderByComparator<AuditEntry> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_AUDITENTRY_WHERE);

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
			sb.append(AuditEntryModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(auditEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AuditEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the audit entries where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (AuditEntry auditEntry :
				findByC_C(
					classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(auditEntry);
		}
	}

	/**
	 * Returns the number of audit entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching audit entries
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = _finderPathCountByC_C;

		Object[] finderArgs = new Object[] {classNameId, classPK};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_AUDITENTRY_WHERE);

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
		"auditEntry.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 =
		"auditEntry.classPK = ?";

	private FinderPath _finderPathWithPaginationFindByC_C_FC_FC;
	private FinderPath _finderPathWithoutPaginationFindByC_C_FC_FC;
	private FinderPath _finderPathCountByC_C_FC_FC;

	/**
	 * Returns all the audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @return the matching audit entries
	 */
	@Override
	public List<AuditEntry> findByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK) {

		return findByC_C_FC_FC(
			classNameId, classPK, fieldClassNameId, fieldClassPK,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of matching audit entries
	 */
	@Override
	public List<AuditEntry> findByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK, int start, int end) {

		return findByC_C_FC_FC(
			classNameId, classPK, fieldClassNameId, fieldClassPK, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit entries
	 */
	@Override
	public List<AuditEntry> findByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator) {

		return findByC_C_FC_FC(
			classNameId, classPK, fieldClassNameId, fieldClassPK, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching audit entries
	 */
	@Override
	public List<AuditEntry> findByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_C_FC_FC;
				finderArgs = new Object[] {
					classNameId, classPK, fieldClassNameId, fieldClassPK
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_C_FC_FC;
			finderArgs = new Object[] {
				classNameId, classPK, fieldClassNameId, fieldClassPK, start,
				end, orderByComparator
			};
		}

		List<AuditEntry> list = null;

		if (useFinderCache) {
			list = (List<AuditEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AuditEntry auditEntry : list) {
					if ((classNameId != auditEntry.getClassNameId()) ||
						(classPK != auditEntry.getClassPK()) ||
						(fieldClassNameId !=
							auditEntry.getFieldClassNameId()) ||
						(fieldClassPK != auditEntry.getFieldClassPK())) {

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
					6 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(6);
			}

			sb.append(_SQL_SELECT_AUDITENTRY_WHERE);

			sb.append(_FINDER_COLUMN_C_C_FC_FC_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_C_FC_FC_CLASSPK_2);

			sb.append(_FINDER_COLUMN_C_C_FC_FC_FIELDCLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_C_FC_FC_FIELDCLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AuditEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				queryPos.add(classPK);

				queryPos.add(fieldClassNameId);

				queryPos.add(fieldClassPK);

				list = (List<AuditEntry>)QueryUtil.list(
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
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	@Override
	public AuditEntry findByC_C_FC_FC_First(
			long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK, OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException {

		AuditEntry auditEntry = fetchByC_C_FC_FC_First(
			classNameId, classPK, fieldClassNameId, fieldClassPK,
			orderByComparator);

		if (auditEntry != null) {
			return auditEntry;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append(", fieldClassNameId=");
		sb.append(fieldClassNameId);

		sb.append(", fieldClassPK=");
		sb.append(fieldClassPK);

		sb.append("}");

		throw new NoSuchAuditEntryException(sb.toString());
	}

	/**
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	@Override
	public AuditEntry fetchByC_C_FC_FC_First(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK, OrderByComparator<AuditEntry> orderByComparator) {

		List<AuditEntry> list = findByC_C_FC_FC(
			classNameId, classPK, fieldClassNameId, fieldClassPK, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	@Override
	public AuditEntry findByC_C_FC_FC_Last(
			long classNameId, long classPK, long fieldClassNameId,
			long fieldClassPK, OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException {

		AuditEntry auditEntry = fetchByC_C_FC_FC_Last(
			classNameId, classPK, fieldClassNameId, fieldClassPK,
			orderByComparator);

		if (auditEntry != null) {
			return auditEntry;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append(", fieldClassNameId=");
		sb.append(fieldClassNameId);

		sb.append(", fieldClassPK=");
		sb.append(fieldClassPK);

		sb.append("}");

		throw new NoSuchAuditEntryException(sb.toString());
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	@Override
	public AuditEntry fetchByC_C_FC_FC_Last(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK, OrderByComparator<AuditEntry> orderByComparator) {

		int count = countByC_C_FC_FC(
			classNameId, classPK, fieldClassNameId, fieldClassPK);

		if (count == 0) {
			return null;
		}

		List<AuditEntry> list = findByC_C_FC_FC(
			classNameId, classPK, fieldClassNameId, fieldClassPK, count - 1,
			count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit entries before and after the current audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param auditEntryId the primary key of the current audit entry
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit entry
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	@Override
	public AuditEntry[] findByC_C_FC_FC_PrevAndNext(
			long auditEntryId, long classNameId, long classPK,
			long fieldClassNameId, long fieldClassPK,
			OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException {

		AuditEntry auditEntry = findByPrimaryKey(auditEntryId);

		Session session = null;

		try {
			session = openSession();

			AuditEntry[] array = new AuditEntryImpl[3];

			array[0] = getByC_C_FC_FC_PrevAndNext(
				session, auditEntry, classNameId, classPK, fieldClassNameId,
				fieldClassPK, orderByComparator, true);

			array[1] = auditEntry;

			array[2] = getByC_C_FC_FC_PrevAndNext(
				session, auditEntry, classNameId, classPK, fieldClassNameId,
				fieldClassPK, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AuditEntry getByC_C_FC_FC_PrevAndNext(
		Session session, AuditEntry auditEntry, long classNameId, long classPK,
		long fieldClassNameId, long fieldClassPK,
		OrderByComparator<AuditEntry> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				7 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(6);
		}

		sb.append(_SQL_SELECT_AUDITENTRY_WHERE);

		sb.append(_FINDER_COLUMN_C_C_FC_FC_CLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_C_C_FC_FC_CLASSPK_2);

		sb.append(_FINDER_COLUMN_C_C_FC_FC_FIELDCLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_C_C_FC_FC_FIELDCLASSPK_2);

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
			sb.append(AuditEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(classNameId);

		queryPos.add(classPK);

		queryPos.add(fieldClassNameId);

		queryPos.add(fieldClassPK);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(auditEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AuditEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 */
	@Override
	public void removeByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK) {

		for (AuditEntry auditEntry :
				findByC_C_FC_FC(
					classNameId, classPK, fieldClassNameId, fieldClassPK,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(auditEntry);
		}
	}

	/**
	 * Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and fieldClassNameId = &#63; and fieldClassPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @return the number of matching audit entries
	 */
	@Override
	public int countByC_C_FC_FC(
		long classNameId, long classPK, long fieldClassNameId,
		long fieldClassPK) {

		FinderPath finderPath = _finderPathCountByC_C_FC_FC;

		Object[] finderArgs = new Object[] {
			classNameId, classPK, fieldClassNameId, fieldClassPK
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_COUNT_AUDITENTRY_WHERE);

			sb.append(_FINDER_COLUMN_C_C_FC_FC_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_C_FC_FC_CLASSPK_2);

			sb.append(_FINDER_COLUMN_C_C_FC_FC_FIELDCLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_C_FC_FC_FIELDCLASSPK_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				queryPos.add(classPK);

				queryPos.add(fieldClassNameId);

				queryPos.add(fieldClassPK);

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

	private static final String _FINDER_COLUMN_C_C_FC_FC_CLASSNAMEID_2 =
		"auditEntry.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_FC_FC_CLASSPK_2 =
		"auditEntry.classPK = ? AND ";

	private static final String _FINDER_COLUMN_C_C_FC_FC_FIELDCLASSNAMEID_2 =
		"auditEntry.fieldClassNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_FC_FC_FIELDCLASSPK_2 =
		"auditEntry.fieldClassPK = ?";

	public AuditEntryPersistenceImpl() {
		setModelClass(AuditEntry.class);

		setModelImplClass(AuditEntryImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the audit entry in the entity cache if it is enabled.
	 *
	 * @param auditEntry the audit entry
	 */
	@Override
	public void cacheResult(AuditEntry auditEntry) {
		entityCache.putResult(
			entityCacheEnabled, AuditEntryImpl.class,
			auditEntry.getPrimaryKey(), auditEntry);

		finderCache.putResult(
			_finderPathFetchByAuditEntryKey,
			new Object[] {auditEntry.getAuditEntryKey()}, auditEntry);

		auditEntry.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the audit entries in the entity cache if it is enabled.
	 *
	 * @param auditEntries the audit entries
	 */
	@Override
	public void cacheResult(List<AuditEntry> auditEntries) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (auditEntries.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (AuditEntry auditEntry : auditEntries) {
			if (entityCache.getResult(
					entityCacheEnabled, AuditEntryImpl.class,
					auditEntry.getPrimaryKey()) == null) {

				cacheResult(auditEntry);
			}
			else {
				auditEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all audit entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AuditEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the audit entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AuditEntry auditEntry) {
		entityCache.removeResult(
			entityCacheEnabled, AuditEntryImpl.class,
			auditEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AuditEntryModelImpl)auditEntry, true);
	}

	@Override
	public void clearCache(List<AuditEntry> auditEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AuditEntry auditEntry : auditEntries) {
			entityCache.removeResult(
				entityCacheEnabled, AuditEntryImpl.class,
				auditEntry.getPrimaryKey());

			clearUniqueFindersCache((AuditEntryModelImpl)auditEntry, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, AuditEntryImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		AuditEntryModelImpl auditEntryModelImpl) {

		Object[] args = new Object[] {auditEntryModelImpl.getAuditEntryKey()};

		finderCache.putResult(
			_finderPathCountByAuditEntryKey, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByAuditEntryKey, args, auditEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		AuditEntryModelImpl auditEntryModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				auditEntryModelImpl.getAuditEntryKey()
			};

			finderCache.removeResult(_finderPathCountByAuditEntryKey, args);
			finderCache.removeResult(_finderPathFetchByAuditEntryKey, args);
		}

		if ((auditEntryModelImpl.getColumnBitmask() &
			 _finderPathFetchByAuditEntryKey.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				auditEntryModelImpl.getOriginalAuditEntryKey()
			};

			finderCache.removeResult(_finderPathCountByAuditEntryKey, args);
			finderCache.removeResult(_finderPathFetchByAuditEntryKey, args);
		}
	}

	/**
	 * Creates a new audit entry with the primary key. Does not add the audit entry to the database.
	 *
	 * @param auditEntryId the primary key for the new audit entry
	 * @return the new audit entry
	 */
	@Override
	public AuditEntry create(long auditEntryId) {
		AuditEntry auditEntry = new AuditEntryImpl();

		auditEntry.setNew(true);
		auditEntry.setPrimaryKey(auditEntryId);

		auditEntry.setCompanyId(CompanyThreadLocal.getCompanyId());

		return auditEntry;
	}

	/**
	 * Removes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry that was removed
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	@Override
	public AuditEntry remove(long auditEntryId)
		throws NoSuchAuditEntryException {

		return remove((Serializable)auditEntryId);
	}

	/**
	 * Removes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the audit entry
	 * @return the audit entry that was removed
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	@Override
	public AuditEntry remove(Serializable primaryKey)
		throws NoSuchAuditEntryException {

		Session session = null;

		try {
			session = openSession();

			AuditEntry auditEntry = (AuditEntry)session.get(
				AuditEntryImpl.class, primaryKey);

			if (auditEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAuditEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(auditEntry);
		}
		catch (NoSuchAuditEntryException noSuchEntityException) {
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
	protected AuditEntry removeImpl(AuditEntry auditEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(auditEntry)) {
				auditEntry = (AuditEntry)session.get(
					AuditEntryImpl.class, auditEntry.getPrimaryKeyObj());
			}

			if (auditEntry != null) {
				session.delete(auditEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (auditEntry != null) {
			clearCache(auditEntry);
		}

		return auditEntry;
	}

	@Override
	public AuditEntry updateImpl(AuditEntry auditEntry) {
		boolean isNew = auditEntry.isNew();

		if (!(auditEntry instanceof AuditEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(auditEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(auditEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in auditEntry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AuditEntry implementation " +
					auditEntry.getClass());
		}

		AuditEntryModelImpl auditEntryModelImpl =
			(AuditEntryModelImpl)auditEntry;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (auditEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				auditEntry.setCreateDate(date);
			}
			else {
				auditEntry.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!auditEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				auditEntry.setModifiedDate(date);
			}
			else {
				auditEntry.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(auditEntry);

				auditEntry.setNew(false);
			}
			else {
				auditEntry = (AuditEntry)session.merge(auditEntry);
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
				auditEntryModelImpl.getClassNameId(),
				auditEntryModelImpl.getClassPK()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_C, args);

			args = new Object[] {
				auditEntryModelImpl.getClassNameId(),
				auditEntryModelImpl.getClassPK(),
				auditEntryModelImpl.getFieldClassNameId(),
				auditEntryModelImpl.getFieldClassPK()
			};

			finderCache.removeResult(_finderPathCountByC_C_FC_FC, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_C_FC_FC, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((auditEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					auditEntryModelImpl.getOriginalClassNameId(),
					auditEntryModelImpl.getOriginalClassPK()
				};

				finderCache.removeResult(_finderPathCountByC_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_C, args);

				args = new Object[] {
					auditEntryModelImpl.getClassNameId(),
					auditEntryModelImpl.getClassPK()
				};

				finderCache.removeResult(_finderPathCountByC_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_C, args);
			}

			if ((auditEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_C_FC_FC.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					auditEntryModelImpl.getOriginalClassNameId(),
					auditEntryModelImpl.getOriginalClassPK(),
					auditEntryModelImpl.getOriginalFieldClassNameId(),
					auditEntryModelImpl.getOriginalFieldClassPK()
				};

				finderCache.removeResult(_finderPathCountByC_C_FC_FC, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_C_FC_FC, args);

				args = new Object[] {
					auditEntryModelImpl.getClassNameId(),
					auditEntryModelImpl.getClassPK(),
					auditEntryModelImpl.getFieldClassNameId(),
					auditEntryModelImpl.getFieldClassPK()
				};

				finderCache.removeResult(_finderPathCountByC_C_FC_FC, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_C_FC_FC, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, AuditEntryImpl.class,
			auditEntry.getPrimaryKey(), auditEntry, false);

		clearUniqueFindersCache(auditEntryModelImpl, false);
		cacheUniqueFindersCache(auditEntryModelImpl);

		auditEntry.resetOriginalValues();

		return auditEntry;
	}

	/**
	 * Returns the audit entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit entry
	 * @return the audit entry
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	@Override
	public AuditEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAuditEntryException {

		AuditEntry auditEntry = fetchByPrimaryKey(primaryKey);

		if (auditEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAuditEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return auditEntry;
	}

	/**
	 * Returns the audit entry with the primary key or throws a <code>NoSuchAuditEntryException</code> if it could not be found.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	@Override
	public AuditEntry findByPrimaryKey(long auditEntryId)
		throws NoSuchAuditEntryException {

		return findByPrimaryKey((Serializable)auditEntryId);
	}

	/**
	 * Returns the audit entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry, or <code>null</code> if a audit entry with the primary key could not be found
	 */
	@Override
	public AuditEntry fetchByPrimaryKey(long auditEntryId) {
		return fetchByPrimaryKey((Serializable)auditEntryId);
	}

	/**
	 * Returns all the audit entries.
	 *
	 * @return the audit entries
	 */
	@Override
	public List<AuditEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of audit entries
	 */
	@Override
	public List<AuditEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of audit entries
	 */
	@Override
	public List<AuditEntry> findAll(
		int start, int end, OrderByComparator<AuditEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of audit entries
	 */
	@Override
	public List<AuditEntry> findAll(
		int start, int end, OrderByComparator<AuditEntry> orderByComparator,
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

		List<AuditEntry> list = null;

		if (useFinderCache) {
			list = (List<AuditEntry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_AUDITENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_AUDITENTRY;

				sql = sql.concat(AuditEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AuditEntry>)QueryUtil.list(
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
	 * Removes all the audit entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AuditEntry auditEntry : findAll()) {
			remove(auditEntry);
		}
	}

	/**
	 * Returns the number of audit entries.
	 *
	 * @return the number of audit entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_AUDITENTRY);

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
		return "auditEntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_AUDITENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AuditEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the audit entry persistence.
	 */
	@Activate
	public void activate() {
		AuditEntryModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		AuditEntryModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathFetchByAuditEntryKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AuditEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByAuditEntryKey",
			new String[] {String.class.getName()},
			AuditEntryModelImpl.AUDITENTRYKEY_COLUMN_BITMASK);

		_finderPathCountByAuditEntryKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAuditEntryKey",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByC_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			AuditEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AuditEntryModelImpl.CLASSPK_COLUMN_BITMASK |
			AuditEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByC_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByC_C_FC_FC = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C_FC_FC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_C_FC_FC = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AuditEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C_FC_FC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName()
			},
			AuditEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AuditEntryModelImpl.CLASSPK_COLUMN_BITMASK |
			AuditEntryModelImpl.FIELDCLASSNAMEID_COLUMN_BITMASK |
			AuditEntryModelImpl.FIELDCLASSPK_COLUMN_BITMASK |
			AuditEntryModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByC_C_FC_FC = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_FC_FC",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName()
			});

		AuditEntryUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		AuditEntryUtil.setPersistence(null);

		entityCache.removeCache(AuditEntryImpl.class.getName());

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
				"value.object.column.bitmask.enabled.com.liferay.osb.koroneiki.root.model.AuditEntry"),
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

	private static final String _SQL_SELECT_AUDITENTRY =
		"SELECT auditEntry FROM AuditEntry auditEntry";

	private static final String _SQL_SELECT_AUDITENTRY_WHERE =
		"SELECT auditEntry FROM AuditEntry auditEntry WHERE ";

	private static final String _SQL_COUNT_AUDITENTRY =
		"SELECT COUNT(auditEntry) FROM AuditEntry auditEntry";

	private static final String _SQL_COUNT_AUDITENTRY_WHERE =
		"SELECT COUNT(auditEntry) FROM AuditEntry auditEntry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "auditEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AuditEntry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AuditEntry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AuditEntryPersistenceImpl.class);

}