/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.taproot.service.persistence.impl;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchAccountNoteException;
import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.osb.koroneiki.taproot.model.impl.AccountNoteImpl;
import com.liferay.osb.koroneiki.taproot.model.impl.AccountNoteModelImpl;
import com.liferay.osb.koroneiki.taproot.service.persistence.AccountNotePersistence;
import com.liferay.osb.koroneiki.taproot.service.persistence.AccountNoteUtil;
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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ArrayUtil;
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

import java.lang.reflect.Field;
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
 * The persistence implementation for the account note service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = AccountNotePersistence.class)
public class AccountNotePersistenceImpl
	extends BasePersistenceImpl<AccountNote> implements AccountNotePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AccountNoteUtil</code> to access the account note persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AccountNoteImpl.class.getName();

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
	 * Returns all the account notes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching account notes
	 */
	@Override
	public List<AccountNote> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account notes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @return the range of matching account notes
	 */
	@Override
	public List<AccountNote> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account notes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account notes
	 */
	@Override
	public List<AccountNote> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AccountNote> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account notes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account notes
	 */
	@Override
	public List<AccountNote> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AccountNote> orderByComparator,
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

		List<AccountNote> list = null;

		if (useFinderCache) {
			list = (List<AccountNote>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountNote accountNote : list) {
					if (!uuid.equals(accountNote.getUuid())) {
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

			sb.append(_SQL_SELECT_ACCOUNTNOTE_WHERE);

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
				sb.append(AccountNoteModelImpl.ORDER_BY_JPQL);
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

				list = (List<AccountNote>)QueryUtil.list(
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
	 * Returns the first account note in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	@Override
	public AccountNote findByUuid_First(
			String uuid, OrderByComparator<AccountNote> orderByComparator)
		throws NoSuchAccountNoteException {

		AccountNote accountNote = fetchByUuid_First(uuid, orderByComparator);

		if (accountNote != null) {
			return accountNote;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchAccountNoteException(sb.toString());
	}

	/**
	 * Returns the first account note in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note, or <code>null</code> if a matching account note could not be found
	 */
	@Override
	public AccountNote fetchByUuid_First(
		String uuid, OrderByComparator<AccountNote> orderByComparator) {

		List<AccountNote> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account note in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	@Override
	public AccountNote findByUuid_Last(
			String uuid, OrderByComparator<AccountNote> orderByComparator)
		throws NoSuchAccountNoteException {

		AccountNote accountNote = fetchByUuid_Last(uuid, orderByComparator);

		if (accountNote != null) {
			return accountNote;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchAccountNoteException(sb.toString());
	}

	/**
	 * Returns the last account note in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note, or <code>null</code> if a matching account note could not be found
	 */
	@Override
	public AccountNote fetchByUuid_Last(
		String uuid, OrderByComparator<AccountNote> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<AccountNote> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account notes before and after the current account note in the ordered set where uuid = &#63;.
	 *
	 * @param accountNoteId the primary key of the current account note
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account note
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	@Override
	public AccountNote[] findByUuid_PrevAndNext(
			long accountNoteId, String uuid,
			OrderByComparator<AccountNote> orderByComparator)
		throws NoSuchAccountNoteException {

		uuid = Objects.toString(uuid, "");

		AccountNote accountNote = findByPrimaryKey(accountNoteId);

		Session session = null;

		try {
			session = openSession();

			AccountNote[] array = new AccountNoteImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, accountNote, uuid, orderByComparator, true);

			array[1] = accountNote;

			array[2] = getByUuid_PrevAndNext(
				session, accountNote, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountNote getByUuid_PrevAndNext(
		Session session, AccountNote accountNote, String uuid,
		OrderByComparator<AccountNote> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ACCOUNTNOTE_WHERE);

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
			sb.append(AccountNoteModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(accountNote)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AccountNote> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account notes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (AccountNote accountNote :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(accountNote);
		}
	}

	/**
	 * Returns the number of account notes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching account notes
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ACCOUNTNOTE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"accountNote.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(accountNote.uuid IS NULL OR accountNote.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the account notes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching account notes
	 */
	@Override
	public List<AccountNote> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account notes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @return the range of matching account notes
	 */
	@Override
	public List<AccountNote> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account notes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account notes
	 */
	@Override
	public List<AccountNote> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AccountNote> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account notes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account notes
	 */
	@Override
	public List<AccountNote> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AccountNote> orderByComparator,
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

		List<AccountNote> list = null;

		if (useFinderCache) {
			list = (List<AccountNote>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountNote accountNote : list) {
					if (!uuid.equals(accountNote.getUuid()) ||
						(companyId != accountNote.getCompanyId())) {

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

			sb.append(_SQL_SELECT_ACCOUNTNOTE_WHERE);

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
				sb.append(AccountNoteModelImpl.ORDER_BY_JPQL);
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

				list = (List<AccountNote>)QueryUtil.list(
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
	 * Returns the first account note in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	@Override
	public AccountNote findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<AccountNote> orderByComparator)
		throws NoSuchAccountNoteException {

		AccountNote accountNote = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (accountNote != null) {
			return accountNote;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchAccountNoteException(sb.toString());
	}

	/**
	 * Returns the first account note in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note, or <code>null</code> if a matching account note could not be found
	 */
	@Override
	public AccountNote fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<AccountNote> orderByComparator) {

		List<AccountNote> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account note in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	@Override
	public AccountNote findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<AccountNote> orderByComparator)
		throws NoSuchAccountNoteException {

		AccountNote accountNote = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (accountNote != null) {
			return accountNote;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchAccountNoteException(sb.toString());
	}

	/**
	 * Returns the last account note in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note, or <code>null</code> if a matching account note could not be found
	 */
	@Override
	public AccountNote fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<AccountNote> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<AccountNote> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account notes before and after the current account note in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param accountNoteId the primary key of the current account note
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account note
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	@Override
	public AccountNote[] findByUuid_C_PrevAndNext(
			long accountNoteId, String uuid, long companyId,
			OrderByComparator<AccountNote> orderByComparator)
		throws NoSuchAccountNoteException {

		uuid = Objects.toString(uuid, "");

		AccountNote accountNote = findByPrimaryKey(accountNoteId);

		Session session = null;

		try {
			session = openSession();

			AccountNote[] array = new AccountNoteImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, accountNote, uuid, companyId, orderByComparator, true);

			array[1] = accountNote;

			array[2] = getByUuid_C_PrevAndNext(
				session, accountNote, uuid, companyId, orderByComparator,
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

	protected AccountNote getByUuid_C_PrevAndNext(
		Session session, AccountNote accountNote, String uuid, long companyId,
		OrderByComparator<AccountNote> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_ACCOUNTNOTE_WHERE);

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
			sb.append(AccountNoteModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(accountNote)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AccountNote> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account notes where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (AccountNote accountNote :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(accountNote);
		}
	}

	/**
	 * Returns the number of account notes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching account notes
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ACCOUNTNOTE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"accountNote.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(accountNote.uuid IS NULL OR accountNote.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"accountNote.companyId = ?";

	private FinderPath _finderPathFetchByAccountNoteKey;
	private FinderPath _finderPathCountByAccountNoteKey;

	/**
	 * Returns the account note where accountNoteKey = &#63; or throws a <code>NoSuchAccountNoteException</code> if it could not be found.
	 *
	 * @param accountNoteKey the account note key
	 * @return the matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	@Override
	public AccountNote findByAccountNoteKey(String accountNoteKey)
		throws NoSuchAccountNoteException {

		AccountNote accountNote = fetchByAccountNoteKey(accountNoteKey);

		if (accountNote == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("accountNoteKey=");
			sb.append(accountNoteKey);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchAccountNoteException(sb.toString());
		}

		return accountNote;
	}

	/**
	 * Returns the account note where accountNoteKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountNoteKey the account note key
	 * @return the matching account note, or <code>null</code> if a matching account note could not be found
	 */
	@Override
	public AccountNote fetchByAccountNoteKey(String accountNoteKey) {
		return fetchByAccountNoteKey(accountNoteKey, true);
	}

	/**
	 * Returns the account note where accountNoteKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountNoteKey the account note key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account note, or <code>null</code> if a matching account note could not be found
	 */
	@Override
	public AccountNote fetchByAccountNoteKey(
		String accountNoteKey, boolean useFinderCache) {

		accountNoteKey = Objects.toString(accountNoteKey, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {accountNoteKey};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByAccountNoteKey, finderArgs, this);
		}

		if (result instanceof AccountNote) {
			AccountNote accountNote = (AccountNote)result;

			if (!Objects.equals(
					accountNoteKey, accountNote.getAccountNoteKey())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_ACCOUNTNOTE_WHERE);

			boolean bindAccountNoteKey = false;

			if (accountNoteKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_ACCOUNTNOTEKEY_ACCOUNTNOTEKEY_3);
			}
			else {
				bindAccountNoteKey = true;

				sb.append(_FINDER_COLUMN_ACCOUNTNOTEKEY_ACCOUNTNOTEKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAccountNoteKey) {
					queryPos.add(accountNoteKey);
				}

				List<AccountNote> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByAccountNoteKey, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {accountNoteKey};
							}

							_log.warn(
								"AccountNotePersistenceImpl.fetchByAccountNoteKey(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					AccountNote accountNote = list.get(0);

					result = accountNote;

					cacheResult(accountNote);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByAccountNoteKey, finderArgs);
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
			return (AccountNote)result;
		}
	}

	/**
	 * Removes the account note where accountNoteKey = &#63; from the database.
	 *
	 * @param accountNoteKey the account note key
	 * @return the account note that was removed
	 */
	@Override
	public AccountNote removeByAccountNoteKey(String accountNoteKey)
		throws NoSuchAccountNoteException {

		AccountNote accountNote = findByAccountNoteKey(accountNoteKey);

		return remove(accountNote);
	}

	/**
	 * Returns the number of account notes where accountNoteKey = &#63;.
	 *
	 * @param accountNoteKey the account note key
	 * @return the number of matching account notes
	 */
	@Override
	public int countByAccountNoteKey(String accountNoteKey) {
		accountNoteKey = Objects.toString(accountNoteKey, "");

		FinderPath finderPath = _finderPathCountByAccountNoteKey;

		Object[] finderArgs = new Object[] {accountNoteKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ACCOUNTNOTE_WHERE);

			boolean bindAccountNoteKey = false;

			if (accountNoteKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_ACCOUNTNOTEKEY_ACCOUNTNOTEKEY_3);
			}
			else {
				bindAccountNoteKey = true;

				sb.append(_FINDER_COLUMN_ACCOUNTNOTEKEY_ACCOUNTNOTEKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAccountNoteKey) {
					queryPos.add(accountNoteKey);
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

	private static final String _FINDER_COLUMN_ACCOUNTNOTEKEY_ACCOUNTNOTEKEY_2 =
		"accountNote.accountNoteKey = ?";

	private static final String _FINDER_COLUMN_ACCOUNTNOTEKEY_ACCOUNTNOTEKEY_3 =
		"(accountNote.accountNoteKey IS NULL OR accountNote.accountNoteKey = '')";

	private FinderPath _finderPathWithPaginationFindByAccountId;
	private FinderPath _finderPathWithoutPaginationFindByAccountId;
	private FinderPath _finderPathCountByAccountId;

	/**
	 * Returns all the account notes where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching account notes
	 */
	@Override
	public List<AccountNote> findByAccountId(long accountId) {
		return findByAccountId(
			accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account notes where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @return the range of matching account notes
	 */
	@Override
	public List<AccountNote> findByAccountId(
		long accountId, int start, int end) {

		return findByAccountId(accountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account notes where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account notes
	 */
	@Override
	public List<AccountNote> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<AccountNote> orderByComparator) {

		return findByAccountId(accountId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account notes where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account notes
	 */
	@Override
	public List<AccountNote> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<AccountNote> orderByComparator,
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

		List<AccountNote> list = null;

		if (useFinderCache) {
			list = (List<AccountNote>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountNote accountNote : list) {
					if (accountId != accountNote.getAccountId()) {
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

			sb.append(_SQL_SELECT_ACCOUNTNOTE_WHERE);

			sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AccountNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

				list = (List<AccountNote>)QueryUtil.list(
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
	 * Returns the first account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	@Override
	public AccountNote findByAccountId_First(
			long accountId, OrderByComparator<AccountNote> orderByComparator)
		throws NoSuchAccountNoteException {

		AccountNote accountNote = fetchByAccountId_First(
			accountId, orderByComparator);

		if (accountNote != null) {
			return accountNote;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchAccountNoteException(sb.toString());
	}

	/**
	 * Returns the first account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note, or <code>null</code> if a matching account note could not be found
	 */
	@Override
	public AccountNote fetchByAccountId_First(
		long accountId, OrderByComparator<AccountNote> orderByComparator) {

		List<AccountNote> list = findByAccountId(
			accountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	@Override
	public AccountNote findByAccountId_Last(
			long accountId, OrderByComparator<AccountNote> orderByComparator)
		throws NoSuchAccountNoteException {

		AccountNote accountNote = fetchByAccountId_Last(
			accountId, orderByComparator);

		if (accountNote != null) {
			return accountNote;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchAccountNoteException(sb.toString());
	}

	/**
	 * Returns the last account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note, or <code>null</code> if a matching account note could not be found
	 */
	@Override
	public AccountNote fetchByAccountId_Last(
		long accountId, OrderByComparator<AccountNote> orderByComparator) {

		int count = countByAccountId(accountId);

		if (count == 0) {
			return null;
		}

		List<AccountNote> list = findByAccountId(
			accountId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account notes before and after the current account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountNoteId the primary key of the current account note
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account note
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	@Override
	public AccountNote[] findByAccountId_PrevAndNext(
			long accountNoteId, long accountId,
			OrderByComparator<AccountNote> orderByComparator)
		throws NoSuchAccountNoteException {

		AccountNote accountNote = findByPrimaryKey(accountNoteId);

		Session session = null;

		try {
			session = openSession();

			AccountNote[] array = new AccountNoteImpl[3];

			array[0] = getByAccountId_PrevAndNext(
				session, accountNote, accountId, orderByComparator, true);

			array[1] = accountNote;

			array[2] = getByAccountId_PrevAndNext(
				session, accountNote, accountId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AccountNote getByAccountId_PrevAndNext(
		Session session, AccountNote accountNote, long accountId,
		OrderByComparator<AccountNote> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ACCOUNTNOTE_WHERE);

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
			sb.append(AccountNoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(accountId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(accountNote)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AccountNote> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the account notes where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	@Override
	public void removeByAccountId(long accountId) {
		for (AccountNote accountNote :
				findByAccountId(
					accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(accountNote);
		}
	}

	/**
	 * Returns the number of account notes where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching account notes
	 */
	@Override
	public int countByAccountId(long accountId) {
		FinderPath finderPath = _finderPathCountByAccountId;

		Object[] finderArgs = new Object[] {accountId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ACCOUNTNOTE_WHERE);

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
		"accountNote.accountId = ?";

	private FinderPath _finderPathWithPaginationFindByAI_T_P_S;
	private FinderPath _finderPathWithoutPaginationFindByAI_T_P_S;
	private FinderPath _finderPathCountByAI_T_P_S;
	private FinderPath _finderPathWithPaginationCountByAI_T_P_S;

	/**
	 * Returns all the account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @return the matching account notes
	 */
	@Override
	public List<AccountNote> findByAI_T_P_S(
		long accountId, String type, int priority, String status) {

		return findByAI_T_P_S(
			accountId, type, priority, status, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @return the range of matching account notes
	 */
	@Override
	public List<AccountNote> findByAI_T_P_S(
		long accountId, String type, int priority, String status, int start,
		int end) {

		return findByAI_T_P_S(
			accountId, type, priority, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account notes
	 */
	@Override
	public List<AccountNote> findByAI_T_P_S(
		long accountId, String type, int priority, String status, int start,
		int end, OrderByComparator<AccountNote> orderByComparator) {

		return findByAI_T_P_S(
			accountId, type, priority, status, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account notes
	 */
	@Override
	public List<AccountNote> findByAI_T_P_S(
		long accountId, String type, int priority, String status, int start,
		int end, OrderByComparator<AccountNote> orderByComparator,
		boolean useFinderCache) {

		type = Objects.toString(type, "");
		status = Objects.toString(status, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByAI_T_P_S;
				finderArgs = new Object[] {accountId, type, priority, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByAI_T_P_S;
			finderArgs = new Object[] {
				accountId, type, priority, status, start, end, orderByComparator
			};
		}

		List<AccountNote> list = null;

		if (useFinderCache) {
			list = (List<AccountNote>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountNote accountNote : list) {
					if ((accountId != accountNote.getAccountId()) ||
						!type.equals(accountNote.getType()) ||
						(priority != accountNote.getPriority()) ||
						!status.equals(accountNote.getStatus())) {

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

			sb.append(_SQL_SELECT_ACCOUNTNOTE_WHERE);

			sb.append(_FINDER_COLUMN_AI_T_P_S_ACCOUNTID_2);

			boolean bindType = false;

			if (type.isEmpty()) {
				sb.append(_FINDER_COLUMN_AI_T_P_S_TYPE_3);
			}
			else {
				bindType = true;

				sb.append(_FINDER_COLUMN_AI_T_P_S_TYPE_2);
			}

			sb.append(_FINDER_COLUMN_AI_T_P_S_PRIORITY_2);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_AI_T_P_S_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_AI_T_P_S_STATUS_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AccountNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

				if (bindType) {
					queryPos.add(type);
				}

				queryPos.add(priority);

				if (bindStatus) {
					queryPos.add(status);
				}

				list = (List<AccountNote>)QueryUtil.list(
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
	 * Returns the first account note in the ordered set where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	@Override
	public AccountNote findByAI_T_P_S_First(
			long accountId, String type, int priority, String status,
			OrderByComparator<AccountNote> orderByComparator)
		throws NoSuchAccountNoteException {

		AccountNote accountNote = fetchByAI_T_P_S_First(
			accountId, type, priority, status, orderByComparator);

		if (accountNote != null) {
			return accountNote;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append(", type=");
		sb.append(type);

		sb.append(", priority=");
		sb.append(priority);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchAccountNoteException(sb.toString());
	}

	/**
	 * Returns the first account note in the ordered set where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note, or <code>null</code> if a matching account note could not be found
	 */
	@Override
	public AccountNote fetchByAI_T_P_S_First(
		long accountId, String type, int priority, String status,
		OrderByComparator<AccountNote> orderByComparator) {

		List<AccountNote> list = findByAI_T_P_S(
			accountId, type, priority, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last account note in the ordered set where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	@Override
	public AccountNote findByAI_T_P_S_Last(
			long accountId, String type, int priority, String status,
			OrderByComparator<AccountNote> orderByComparator)
		throws NoSuchAccountNoteException {

		AccountNote accountNote = fetchByAI_T_P_S_Last(
			accountId, type, priority, status, orderByComparator);

		if (accountNote != null) {
			return accountNote;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append(", type=");
		sb.append(type);

		sb.append(", priority=");
		sb.append(priority);

		sb.append(", status=");
		sb.append(status);

		sb.append("}");

		throw new NoSuchAccountNoteException(sb.toString());
	}

	/**
	 * Returns the last account note in the ordered set where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note, or <code>null</code> if a matching account note could not be found
	 */
	@Override
	public AccountNote fetchByAI_T_P_S_Last(
		long accountId, String type, int priority, String status,
		OrderByComparator<AccountNote> orderByComparator) {

		int count = countByAI_T_P_S(accountId, type, priority, status);

		if (count == 0) {
			return null;
		}

		List<AccountNote> list = findByAI_T_P_S(
			accountId, type, priority, status, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the account notes before and after the current account note in the ordered set where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountNoteId the primary key of the current account note
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account note
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	@Override
	public AccountNote[] findByAI_T_P_S_PrevAndNext(
			long accountNoteId, long accountId, String type, int priority,
			String status, OrderByComparator<AccountNote> orderByComparator)
		throws NoSuchAccountNoteException {

		type = Objects.toString(type, "");
		status = Objects.toString(status, "");

		AccountNote accountNote = findByPrimaryKey(accountNoteId);

		Session session = null;

		try {
			session = openSession();

			AccountNote[] array = new AccountNoteImpl[3];

			array[0] = getByAI_T_P_S_PrevAndNext(
				session, accountNote, accountId, type, priority, status,
				orderByComparator, true);

			array[1] = accountNote;

			array[2] = getByAI_T_P_S_PrevAndNext(
				session, accountNote, accountId, type, priority, status,
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

	protected AccountNote getByAI_T_P_S_PrevAndNext(
		Session session, AccountNote accountNote, long accountId, String type,
		int priority, String status,
		OrderByComparator<AccountNote> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				7 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(6);
		}

		sb.append(_SQL_SELECT_ACCOUNTNOTE_WHERE);

		sb.append(_FINDER_COLUMN_AI_T_P_S_ACCOUNTID_2);

		boolean bindType = false;

		if (type.isEmpty()) {
			sb.append(_FINDER_COLUMN_AI_T_P_S_TYPE_3);
		}
		else {
			bindType = true;

			sb.append(_FINDER_COLUMN_AI_T_P_S_TYPE_2);
		}

		sb.append(_FINDER_COLUMN_AI_T_P_S_PRIORITY_2);

		boolean bindStatus = false;

		if (status.isEmpty()) {
			sb.append(_FINDER_COLUMN_AI_T_P_S_STATUS_3);
		}
		else {
			bindStatus = true;

			sb.append(_FINDER_COLUMN_AI_T_P_S_STATUS_2);
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
			sb.append(AccountNoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(accountId);

		if (bindType) {
			queryPos.add(type);
		}

		queryPos.add(priority);

		if (bindStatus) {
			queryPos.add(status);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(accountNote)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AccountNote> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the account notes where accountId = &#63; and type = any &#63; and priority = any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param types the types
	 * @param priorities the priorities
	 * @param statuses the statuses
	 * @return the matching account notes
	 */
	@Override
	public List<AccountNote> findByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses) {

		return findByAI_T_P_S(
			accountId, types, priorities, statuses, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account notes where accountId = &#63; and type = any &#63; and priority = any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param types the types
	 * @param priorities the priorities
	 * @param statuses the statuses
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @return the range of matching account notes
	 */
	@Override
	public List<AccountNote> findByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses,
		int start, int end) {

		return findByAI_T_P_S(
			accountId, types, priorities, statuses, start, end, null);
	}

	/**
	 * Returns an ordered range of all the account notes where accountId = &#63; and type = any &#63; and priority = any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param types the types
	 * @param priorities the priorities
	 * @param statuses the statuses
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account notes
	 */
	@Override
	public List<AccountNote> findByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses,
		int start, int end, OrderByComparator<AccountNote> orderByComparator) {

		return findByAI_T_P_S(
			accountId, types, priorities, statuses, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param types the types
	 * @param priorities the priorities
	 * @param statuses the statuses
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account notes
	 */
	@Override
	public List<AccountNote> findByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses,
		int start, int end, OrderByComparator<AccountNote> orderByComparator,
		boolean useFinderCache) {

		if (types == null) {
			types = new String[0];
		}
		else if (types.length > 1) {
			for (int i = 0; i < types.length; i++) {
				types[i] = Objects.toString(types[i], "");
			}

			types = ArrayUtil.sortedUnique(types);
		}

		if (priorities == null) {
			priorities = new int[0];
		}
		else if (priorities.length > 1) {
			priorities = ArrayUtil.sortedUnique(priorities);
		}

		if (statuses == null) {
			statuses = new String[0];
		}
		else if (statuses.length > 1) {
			for (int i = 0; i < statuses.length; i++) {
				statuses[i] = Objects.toString(statuses[i], "");
			}

			statuses = ArrayUtil.sortedUnique(statuses);
		}

		if ((types.length == 1) && (priorities.length == 1) &&
			(statuses.length == 1)) {

			return findByAI_T_P_S(
				accountId, types[0], priorities[0], statuses[0], start, end,
				orderByComparator);
		}

		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderArgs = new Object[] {
					accountId, StringUtil.merge(types),
					StringUtil.merge(priorities), StringUtil.merge(statuses)
				};
			}
		}
		else if (useFinderCache) {
			finderArgs = new Object[] {
				accountId, StringUtil.merge(types),
				StringUtil.merge(priorities), StringUtil.merge(statuses), start,
				end, orderByComparator
			};
		}

		List<AccountNote> list = null;

		if (useFinderCache) {
			list = (List<AccountNote>)finderCache.getResult(
				_finderPathWithPaginationFindByAI_T_P_S, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AccountNote accountNote : list) {
					if ((accountId != accountNote.getAccountId()) ||
						!ArrayUtil.contains(types, accountNote.getType()) ||
						!ArrayUtil.contains(
							priorities, accountNote.getPriority()) ||
						!ArrayUtil.contains(
							statuses, accountNote.getStatus())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = new StringBundler();

			sb.append(_SQL_SELECT_ACCOUNTNOTE_WHERE);

			sb.append(_FINDER_COLUMN_AI_T_P_S_ACCOUNTID_2);

			if (types.length > 0) {
				sb.append("(");

				for (int i = 0; i < types.length; i++) {
					String type = types[i];

					if (type.isEmpty()) {
						sb.append(_FINDER_COLUMN_AI_T_P_S_TYPE_6);
					}
					else {
						sb.append(_FINDER_COLUMN_AI_T_P_S_TYPE_5);
					}

					if ((i + 1) < types.length) {
						sb.append(WHERE_OR);
					}
				}

				sb.append(")");

				sb.append(WHERE_AND);
			}

			if (priorities.length > 0) {
				sb.append("(");

				sb.append(_FINDER_COLUMN_AI_T_P_S_PRIORITY_7);

				sb.append(StringUtil.merge(priorities));

				sb.append(")");

				sb.append(")");

				sb.append(WHERE_AND);
			}

			if (statuses.length > 0) {
				sb.append("(");

				for (int i = 0; i < statuses.length; i++) {
					String status = statuses[i];

					if (status.isEmpty()) {
						sb.append(_FINDER_COLUMN_AI_T_P_S_STATUS_3);
					}
					else {
						sb.append(_FINDER_COLUMN_AI_T_P_S_STATUS_2);
					}

					if ((i + 1) < statuses.length) {
						sb.append(WHERE_OR);
					}
				}

				sb.append(")");
			}

			sb.setStringAt(
				removeConjunction(sb.stringAt(sb.index() - 1)), sb.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AccountNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

				for (String type : types) {
					if ((type != null) && !type.isEmpty()) {
						queryPos.add(type);
					}
				}

				for (String status : statuses) {
					if ((status != null) && !status.isEmpty()) {
						queryPos.add(status);
					}
				}

				list = (List<AccountNote>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(
						_finderPathWithPaginationFindByAI_T_P_S, finderArgs,
						list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathWithPaginationFindByAI_T_P_S, finderArgs);
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
	 * Removes all the account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63; from the database.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 */
	@Override
	public void removeByAI_T_P_S(
		long accountId, String type, int priority, String status) {

		for (AccountNote accountNote :
				findByAI_T_P_S(
					accountId, type, priority, status, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(accountNote);
		}
	}

	/**
	 * Returns the number of account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @return the number of matching account notes
	 */
	@Override
	public int countByAI_T_P_S(
		long accountId, String type, int priority, String status) {

		type = Objects.toString(type, "");
		status = Objects.toString(status, "");

		FinderPath finderPath = _finderPathCountByAI_T_P_S;

		Object[] finderArgs = new Object[] {accountId, type, priority, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_COUNT_ACCOUNTNOTE_WHERE);

			sb.append(_FINDER_COLUMN_AI_T_P_S_ACCOUNTID_2);

			boolean bindType = false;

			if (type.isEmpty()) {
				sb.append(_FINDER_COLUMN_AI_T_P_S_TYPE_3);
			}
			else {
				bindType = true;

				sb.append(_FINDER_COLUMN_AI_T_P_S_TYPE_2);
			}

			sb.append(_FINDER_COLUMN_AI_T_P_S_PRIORITY_2);

			boolean bindStatus = false;

			if (status.isEmpty()) {
				sb.append(_FINDER_COLUMN_AI_T_P_S_STATUS_3);
			}
			else {
				bindStatus = true;

				sb.append(_FINDER_COLUMN_AI_T_P_S_STATUS_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

				if (bindType) {
					queryPos.add(type);
				}

				queryPos.add(priority);

				if (bindStatus) {
					queryPos.add(status);
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
	 * Returns the number of account notes where accountId = &#63; and type = any &#63; and priority = any &#63; and status = any &#63;.
	 *
	 * @param accountId the account ID
	 * @param types the types
	 * @param priorities the priorities
	 * @param statuses the statuses
	 * @return the number of matching account notes
	 */
	@Override
	public int countByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses) {

		if (types == null) {
			types = new String[0];
		}
		else if (types.length > 1) {
			for (int i = 0; i < types.length; i++) {
				types[i] = Objects.toString(types[i], "");
			}

			types = ArrayUtil.sortedUnique(types);
		}

		if (priorities == null) {
			priorities = new int[0];
		}
		else if (priorities.length > 1) {
			priorities = ArrayUtil.sortedUnique(priorities);
		}

		if (statuses == null) {
			statuses = new String[0];
		}
		else if (statuses.length > 1) {
			for (int i = 0; i < statuses.length; i++) {
				statuses[i] = Objects.toString(statuses[i], "");
			}

			statuses = ArrayUtil.sortedUnique(statuses);
		}

		Object[] finderArgs = new Object[] {
			accountId, StringUtil.merge(types), StringUtil.merge(priorities),
			StringUtil.merge(statuses)
		};

		Long count = (Long)finderCache.getResult(
			_finderPathWithPaginationCountByAI_T_P_S, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler();

			sb.append(_SQL_COUNT_ACCOUNTNOTE_WHERE);

			sb.append(_FINDER_COLUMN_AI_T_P_S_ACCOUNTID_2);

			if (types.length > 0) {
				sb.append("(");

				for (int i = 0; i < types.length; i++) {
					String type = types[i];

					if (type.isEmpty()) {
						sb.append(_FINDER_COLUMN_AI_T_P_S_TYPE_6);
					}
					else {
						sb.append(_FINDER_COLUMN_AI_T_P_S_TYPE_5);
					}

					if ((i + 1) < types.length) {
						sb.append(WHERE_OR);
					}
				}

				sb.append(")");

				sb.append(WHERE_AND);
			}

			if (priorities.length > 0) {
				sb.append("(");

				sb.append(_FINDER_COLUMN_AI_T_P_S_PRIORITY_7);

				sb.append(StringUtil.merge(priorities));

				sb.append(")");

				sb.append(")");

				sb.append(WHERE_AND);
			}

			if (statuses.length > 0) {
				sb.append("(");

				for (int i = 0; i < statuses.length; i++) {
					String status = statuses[i];

					if (status.isEmpty()) {
						sb.append(_FINDER_COLUMN_AI_T_P_S_STATUS_3);
					}
					else {
						sb.append(_FINDER_COLUMN_AI_T_P_S_STATUS_2);
					}

					if ((i + 1) < statuses.length) {
						sb.append(WHERE_OR);
					}
				}

				sb.append(")");
			}

			sb.setStringAt(
				removeConjunction(sb.stringAt(sb.index() - 1)), sb.index() - 1);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

				for (String type : types) {
					if ((type != null) && !type.isEmpty()) {
						queryPos.add(type);
					}
				}

				for (String status : statuses) {
					if ((status != null) && !status.isEmpty()) {
						queryPos.add(status);
					}
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathWithPaginationCountByAI_T_P_S, finderArgs,
					count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathWithPaginationCountByAI_T_P_S, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_AI_T_P_S_ACCOUNTID_2 =
		"accountNote.accountId = ? AND ";

	private static final String _FINDER_COLUMN_AI_T_P_S_TYPE_2 =
		"accountNote.type = ? AND ";

	private static final String _FINDER_COLUMN_AI_T_P_S_TYPE_3 =
		"(accountNote.type IS NULL OR accountNote.type = '') AND ";

	private static final String _FINDER_COLUMN_AI_T_P_S_TYPE_5 =
		"(" + removeConjunction(_FINDER_COLUMN_AI_T_P_S_TYPE_2) + ")";

	private static final String _FINDER_COLUMN_AI_T_P_S_TYPE_6 =
		"(" + removeConjunction(_FINDER_COLUMN_AI_T_P_S_TYPE_3) + ")";

	private static final String _FINDER_COLUMN_AI_T_P_S_PRIORITY_2 =
		"accountNote.priority = ? AND ";

	private static final String _FINDER_COLUMN_AI_T_P_S_PRIORITY_7 =
		"accountNote.priority IN (";

	private static final String _FINDER_COLUMN_AI_T_P_S_STATUS_2 =
		"accountNote.status = ?";

	private static final String _FINDER_COLUMN_AI_T_P_S_STATUS_3 =
		"(accountNote.status IS NULL OR accountNote.status = '')";

	public AccountNotePersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(AccountNote.class);

		setModelImplClass(AccountNoteImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the account note in the entity cache if it is enabled.
	 *
	 * @param accountNote the account note
	 */
	@Override
	public void cacheResult(AccountNote accountNote) {
		entityCache.putResult(
			entityCacheEnabled, AccountNoteImpl.class,
			accountNote.getPrimaryKey(), accountNote);

		finderCache.putResult(
			_finderPathFetchByAccountNoteKey,
			new Object[] {accountNote.getAccountNoteKey()}, accountNote);

		accountNote.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the account notes in the entity cache if it is enabled.
	 *
	 * @param accountNotes the account notes
	 */
	@Override
	public void cacheResult(List<AccountNote> accountNotes) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (accountNotes.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (AccountNote accountNote : accountNotes) {
			if (entityCache.getResult(
					entityCacheEnabled, AccountNoteImpl.class,
					accountNote.getPrimaryKey()) == null) {

				cacheResult(accountNote);
			}
			else {
				accountNote.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all account notes.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccountNoteImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the account note.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccountNote accountNote) {
		entityCache.removeResult(
			entityCacheEnabled, AccountNoteImpl.class,
			accountNote.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((AccountNoteModelImpl)accountNote, true);
	}

	@Override
	public void clearCache(List<AccountNote> accountNotes) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccountNote accountNote : accountNotes) {
			entityCache.removeResult(
				entityCacheEnabled, AccountNoteImpl.class,
				accountNote.getPrimaryKey());

			clearUniqueFindersCache((AccountNoteModelImpl)accountNote, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, AccountNoteImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		AccountNoteModelImpl accountNoteModelImpl) {

		Object[] args = new Object[] {accountNoteModelImpl.getAccountNoteKey()};

		finderCache.putResult(
			_finderPathCountByAccountNoteKey, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByAccountNoteKey, args, accountNoteModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		AccountNoteModelImpl accountNoteModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				accountNoteModelImpl.getAccountNoteKey()
			};

			finderCache.removeResult(_finderPathCountByAccountNoteKey, args);
			finderCache.removeResult(_finderPathFetchByAccountNoteKey, args);
		}

		if ((accountNoteModelImpl.getColumnBitmask() &
			 _finderPathFetchByAccountNoteKey.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				accountNoteModelImpl.getOriginalAccountNoteKey()
			};

			finderCache.removeResult(_finderPathCountByAccountNoteKey, args);
			finderCache.removeResult(_finderPathFetchByAccountNoteKey, args);
		}
	}

	/**
	 * Creates a new account note with the primary key. Does not add the account note to the database.
	 *
	 * @param accountNoteId the primary key for the new account note
	 * @return the new account note
	 */
	@Override
	public AccountNote create(long accountNoteId) {
		AccountNote accountNote = new AccountNoteImpl();

		accountNote.setNew(true);
		accountNote.setPrimaryKey(accountNoteId);

		String uuid = PortalUUIDUtil.generate();

		accountNote.setUuid(uuid);

		accountNote.setCompanyId(CompanyThreadLocal.getCompanyId());

		return accountNote;
	}

	/**
	 * Removes the account note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountNoteId the primary key of the account note
	 * @return the account note that was removed
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	@Override
	public AccountNote remove(long accountNoteId)
		throws NoSuchAccountNoteException {

		return remove((Serializable)accountNoteId);
	}

	/**
	 * Removes the account note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the account note
	 * @return the account note that was removed
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	@Override
	public AccountNote remove(Serializable primaryKey)
		throws NoSuchAccountNoteException {

		Session session = null;

		try {
			session = openSession();

			AccountNote accountNote = (AccountNote)session.get(
				AccountNoteImpl.class, primaryKey);

			if (accountNote == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountNoteException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(accountNote);
		}
		catch (NoSuchAccountNoteException noSuchEntityException) {
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
	protected AccountNote removeImpl(AccountNote accountNote) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accountNote)) {
				accountNote = (AccountNote)session.get(
					AccountNoteImpl.class, accountNote.getPrimaryKeyObj());
			}

			if (accountNote != null) {
				session.delete(accountNote);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (accountNote != null) {
			clearCache(accountNote);
		}

		return accountNote;
	}

	@Override
	public AccountNote updateImpl(AccountNote accountNote) {
		boolean isNew = accountNote.isNew();

		if (!(accountNote instanceof AccountNoteModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(accountNote.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(accountNote);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in accountNote proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AccountNote implementation " +
					accountNote.getClass());
		}

		AccountNoteModelImpl accountNoteModelImpl =
			(AccountNoteModelImpl)accountNote;

		if (Validator.isNull(accountNote.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			accountNote.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (accountNote.getCreateDate() == null)) {
			if (serviceContext == null) {
				accountNote.setCreateDate(date);
			}
			else {
				accountNote.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!accountNoteModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				accountNote.setModifiedDate(date);
			}
			else {
				accountNote.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(accountNote);

				accountNote.setNew(false);
			}
			else {
				accountNote = (AccountNote)session.merge(accountNote);
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
			Object[] args = new Object[] {accountNoteModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				accountNoteModelImpl.getUuid(),
				accountNoteModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {accountNoteModelImpl.getAccountId()};

			finderCache.removeResult(_finderPathCountByAccountId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAccountId, args);

			args = new Object[] {
				accountNoteModelImpl.getAccountId(),
				accountNoteModelImpl.getType(),
				accountNoteModelImpl.getPriority(),
				accountNoteModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByAI_T_P_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAI_T_P_S, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((accountNoteModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					accountNoteModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {accountNoteModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((accountNoteModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					accountNoteModelImpl.getOriginalUuid(),
					accountNoteModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					accountNoteModelImpl.getUuid(),
					accountNoteModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((accountNoteModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByAccountId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					accountNoteModelImpl.getOriginalAccountId()
				};

				finderCache.removeResult(_finderPathCountByAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountId, args);

				args = new Object[] {accountNoteModelImpl.getAccountId()};

				finderCache.removeResult(_finderPathCountByAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountId, args);
			}

			if ((accountNoteModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByAI_T_P_S.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					accountNoteModelImpl.getOriginalAccountId(),
					accountNoteModelImpl.getOriginalType(),
					accountNoteModelImpl.getOriginalPriority(),
					accountNoteModelImpl.getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByAI_T_P_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAI_T_P_S, args);

				args = new Object[] {
					accountNoteModelImpl.getAccountId(),
					accountNoteModelImpl.getType(),
					accountNoteModelImpl.getPriority(),
					accountNoteModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByAI_T_P_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAI_T_P_S, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, AccountNoteImpl.class,
			accountNote.getPrimaryKey(), accountNote, false);

		clearUniqueFindersCache(accountNoteModelImpl, false);
		cacheUniqueFindersCache(accountNoteModelImpl);

		accountNote.resetOriginalValues();

		return accountNote;
	}

	/**
	 * Returns the account note with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the account note
	 * @return the account note
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	@Override
	public AccountNote findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccountNoteException {

		AccountNote accountNote = fetchByPrimaryKey(primaryKey);

		if (accountNote == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccountNoteException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return accountNote;
	}

	/**
	 * Returns the account note with the primary key or throws a <code>NoSuchAccountNoteException</code> if it could not be found.
	 *
	 * @param accountNoteId the primary key of the account note
	 * @return the account note
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	@Override
	public AccountNote findByPrimaryKey(long accountNoteId)
		throws NoSuchAccountNoteException {

		return findByPrimaryKey((Serializable)accountNoteId);
	}

	/**
	 * Returns the account note with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountNoteId the primary key of the account note
	 * @return the account note, or <code>null</code> if a account note with the primary key could not be found
	 */
	@Override
	public AccountNote fetchByPrimaryKey(long accountNoteId) {
		return fetchByPrimaryKey((Serializable)accountNoteId);
	}

	/**
	 * Returns all the account notes.
	 *
	 * @return the account notes
	 */
	@Override
	public List<AccountNote> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the account notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @return the range of account notes
	 */
	@Override
	public List<AccountNote> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the account notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account notes
	 */
	@Override
	public List<AccountNote> findAll(
		int start, int end, OrderByComparator<AccountNote> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the account notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of account notes
	 */
	@Override
	public List<AccountNote> findAll(
		int start, int end, OrderByComparator<AccountNote> orderByComparator,
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

		List<AccountNote> list = null;

		if (useFinderCache) {
			list = (List<AccountNote>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ACCOUNTNOTE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ACCOUNTNOTE;

				sql = sql.concat(AccountNoteModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AccountNote>)QueryUtil.list(
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
	 * Removes all the account notes from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccountNote accountNote : findAll()) {
			remove(accountNote);
		}
	}

	/**
	 * Returns the number of account notes.
	 *
	 * @return the number of account notes
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ACCOUNTNOTE);

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
		return "accountNoteId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ACCOUNTNOTE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AccountNoteModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the account note persistence.
	 */
	@Activate
	public void activate() {
		AccountNoteModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		AccountNoteModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			AccountNoteModelImpl.UUID_COLUMN_BITMASK |
			AccountNoteModelImpl.PRIORITY_COLUMN_BITMASK |
			AccountNoteModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			AccountNoteModelImpl.UUID_COLUMN_BITMASK |
			AccountNoteModelImpl.COMPANYID_COLUMN_BITMASK |
			AccountNoteModelImpl.PRIORITY_COLUMN_BITMASK |
			AccountNoteModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathFetchByAccountNoteKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountNoteImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByAccountNoteKey",
			new String[] {String.class.getName()},
			AccountNoteModelImpl.ACCOUNTNOTEKEY_COLUMN_BITMASK);

		_finderPathCountByAccountNoteKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountNoteKey",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountId",
			new String[] {Long.class.getName()},
			AccountNoteModelImpl.ACCOUNTID_COLUMN_BITMASK |
			AccountNoteModelImpl.PRIORITY_COLUMN_BITMASK |
			AccountNoteModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByAI_T_P_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAI_T_P_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAI_T_P_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, AccountNoteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAI_T_P_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName()
			},
			AccountNoteModelImpl.ACCOUNTID_COLUMN_BITMASK |
			AccountNoteModelImpl.TYPE_COLUMN_BITMASK |
			AccountNoteModelImpl.PRIORITY_COLUMN_BITMASK |
			AccountNoteModelImpl.STATUS_COLUMN_BITMASK |
			AccountNoteModelImpl.CREATEDATE_COLUMN_BITMASK);

		_finderPathCountByAI_T_P_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAI_T_P_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName()
			});

		_finderPathWithPaginationCountByAI_T_P_S = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByAI_T_P_S",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), String.class.getName()
			});

		_setAccountNoteUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setAccountNoteUtilPersistence(null);

		entityCache.removeCache(AccountNoteImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private void _setAccountNoteUtilPersistence(
		AccountNotePersistence accountNotePersistence) {

		try {
			Field field = AccountNoteUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, accountNotePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
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
				"value.object.column.bitmask.enabled.com.liferay.osb.koroneiki.taproot.model.AccountNote"),
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

	private static final String _SQL_SELECT_ACCOUNTNOTE =
		"SELECT accountNote FROM AccountNote accountNote";

	private static final String _SQL_SELECT_ACCOUNTNOTE_WHERE =
		"SELECT accountNote FROM AccountNote accountNote WHERE ";

	private static final String _SQL_COUNT_ACCOUNTNOTE =
		"SELECT COUNT(accountNote) FROM AccountNote accountNote";

	private static final String _SQL_COUNT_ACCOUNTNOTE_WHERE =
		"SELECT COUNT(accountNote) FROM AccountNote accountNote WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "accountNote.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AccountNote exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AccountNote exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AccountNotePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "type"});

}