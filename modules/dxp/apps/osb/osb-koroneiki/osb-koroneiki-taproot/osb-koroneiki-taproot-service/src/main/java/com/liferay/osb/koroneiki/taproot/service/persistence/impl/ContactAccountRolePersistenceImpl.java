/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence.impl;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchContactAccountRoleException;
import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.osb.koroneiki.taproot.model.impl.ContactAccountRoleImpl;
import com.liferay.osb.koroneiki.taproot.model.impl.ContactAccountRoleModelImpl;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactAccountRolePK;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactAccountRolePersistence;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactAccountRoleUtil;
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
 * The persistence implementation for the contact account role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ContactAccountRolePersistence.class)
public class ContactAccountRolePersistenceImpl
	extends BasePersistenceImpl<ContactAccountRole>
	implements ContactAccountRolePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ContactAccountRoleUtil</code> to access the contact account role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ContactAccountRoleImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByContactId;
	private FinderPath _finderPathWithoutPaginationFindByContactId;
	private FinderPath _finderPathCountByContactId;

	/**
	 * Returns all the contact account roles where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByContactId(long contactId) {
		return findByContactId(
			contactId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contact account roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByContactId(
		long contactId, int start, int end) {

		return findByContactId(contactId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contact account roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByContactId(
		long contactId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return findByContactId(contactId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contact account roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByContactId(
		long contactId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByContactId;
				finderArgs = new Object[] {contactId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByContactId;
			finderArgs = new Object[] {
				contactId, start, end, orderByComparator
			};
		}

		List<ContactAccountRole> list = null;

		if (useFinderCache) {
			list = (List<ContactAccountRole>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContactAccountRole contactAccountRole : list) {
					if (contactId != contactAccountRole.getContactId()) {
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

			sb.append(_SQL_SELECT_CONTACTACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_CONTACTID_CONTACTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ContactAccountRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(contactId);

				list = (List<ContactAccountRole>)QueryUtil.list(
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
	 * Returns the first contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole findByContactId_First(
			long contactId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws NoSuchContactAccountRoleException {

		ContactAccountRole contactAccountRole = fetchByContactId_First(
			contactId, orderByComparator);

		if (contactAccountRole != null) {
			return contactAccountRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("contactId=");
		sb.append(contactId);

		sb.append("}");

		throw new NoSuchContactAccountRoleException(sb.toString());
	}

	/**
	 * Returns the first contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole fetchByContactId_First(
		long contactId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		List<ContactAccountRole> list = findByContactId(
			contactId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole findByContactId_Last(
			long contactId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws NoSuchContactAccountRoleException {

		ContactAccountRole contactAccountRole = fetchByContactId_Last(
			contactId, orderByComparator);

		if (contactAccountRole != null) {
			return contactAccountRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("contactId=");
		sb.append(contactId);

		sb.append("}");

		throw new NoSuchContactAccountRoleException(sb.toString());
	}

	/**
	 * Returns the last contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole fetchByContactId_Last(
		long contactId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		int count = countByContactId(contactId);

		if (count == 0) {
			return null;
		}

		List<ContactAccountRole> list = findByContactId(
			contactId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contact account roles before and after the current contact account role in the ordered set where contactId = &#63;.
	 *
	 * @param contactAccountRolePK the primary key of the current contact account role
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	@Override
	public ContactAccountRole[] findByContactId_PrevAndNext(
			ContactAccountRolePK contactAccountRolePK, long contactId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws NoSuchContactAccountRoleException {

		ContactAccountRole contactAccountRole = findByPrimaryKey(
			contactAccountRolePK);

		Session session = null;

		try {
			session = openSession();

			ContactAccountRole[] array = new ContactAccountRoleImpl[3];

			array[0] = getByContactId_PrevAndNext(
				session, contactAccountRole, contactId, orderByComparator,
				true);

			array[1] = contactAccountRole;

			array[2] = getByContactId_PrevAndNext(
				session, contactAccountRole, contactId, orderByComparator,
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

	protected ContactAccountRole getByContactId_PrevAndNext(
		Session session, ContactAccountRole contactAccountRole, long contactId,
		OrderByComparator<ContactAccountRole> orderByComparator,
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

		sb.append(_SQL_SELECT_CONTACTACCOUNTROLE_WHERE);

		sb.append(_FINDER_COLUMN_CONTACTID_CONTACTID_2);

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
			sb.append(ContactAccountRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(contactId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						contactAccountRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ContactAccountRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contact account roles where contactId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 */
	@Override
	public void removeByContactId(long contactId) {
		for (ContactAccountRole contactAccountRole :
				findByContactId(
					contactId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(contactAccountRole);
		}
	}

	/**
	 * Returns the number of contact account roles where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the number of matching contact account roles
	 */
	@Override
	public int countByContactId(long contactId) {
		FinderPath finderPath = _finderPathCountByContactId;

		Object[] finderArgs = new Object[] {contactId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CONTACTACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_CONTACTID_CONTACTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(contactId);

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

	private static final String _FINDER_COLUMN_CONTACTID_CONTACTID_2 =
		"contactAccountRole.id.contactId = ?";

	private FinderPath _finderPathWithPaginationFindByAccountId;
	private FinderPath _finderPathWithoutPaginationFindByAccountId;
	private FinderPath _finderPathCountByAccountId;

	/**
	 * Returns all the contact account roles where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByAccountId(long accountId) {
		return findByAccountId(
			accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contact account roles where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByAccountId(
		long accountId, int start, int end) {

		return findByAccountId(accountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contact account roles where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return findByAccountId(accountId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contact account roles where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator,
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

		List<ContactAccountRole> list = null;

		if (useFinderCache) {
			list = (List<ContactAccountRole>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContactAccountRole contactAccountRole : list) {
					if (accountId != contactAccountRole.getAccountId()) {
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

			sb.append(_SQL_SELECT_CONTACTACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ContactAccountRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

				list = (List<ContactAccountRole>)QueryUtil.list(
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
	 * Returns the first contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole findByAccountId_First(
			long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws NoSuchContactAccountRoleException {

		ContactAccountRole contactAccountRole = fetchByAccountId_First(
			accountId, orderByComparator);

		if (contactAccountRole != null) {
			return contactAccountRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchContactAccountRoleException(sb.toString());
	}

	/**
	 * Returns the first contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole fetchByAccountId_First(
		long accountId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		List<ContactAccountRole> list = findByAccountId(
			accountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole findByAccountId_Last(
			long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws NoSuchContactAccountRoleException {

		ContactAccountRole contactAccountRole = fetchByAccountId_Last(
			accountId, orderByComparator);

		if (contactAccountRole != null) {
			return contactAccountRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchContactAccountRoleException(sb.toString());
	}

	/**
	 * Returns the last contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole fetchByAccountId_Last(
		long accountId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		int count = countByAccountId(accountId);

		if (count == 0) {
			return null;
		}

		List<ContactAccountRole> list = findByAccountId(
			accountId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contact account roles before and after the current contact account role in the ordered set where accountId = &#63;.
	 *
	 * @param contactAccountRolePK the primary key of the current contact account role
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	@Override
	public ContactAccountRole[] findByAccountId_PrevAndNext(
			ContactAccountRolePK contactAccountRolePK, long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws NoSuchContactAccountRoleException {

		ContactAccountRole contactAccountRole = findByPrimaryKey(
			contactAccountRolePK);

		Session session = null;

		try {
			session = openSession();

			ContactAccountRole[] array = new ContactAccountRoleImpl[3];

			array[0] = getByAccountId_PrevAndNext(
				session, contactAccountRole, accountId, orderByComparator,
				true);

			array[1] = contactAccountRole;

			array[2] = getByAccountId_PrevAndNext(
				session, contactAccountRole, accountId, orderByComparator,
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

	protected ContactAccountRole getByAccountId_PrevAndNext(
		Session session, ContactAccountRole contactAccountRole, long accountId,
		OrderByComparator<ContactAccountRole> orderByComparator,
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

		sb.append(_SQL_SELECT_CONTACTACCOUNTROLE_WHERE);

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
			sb.append(ContactAccountRoleModelImpl.ORDER_BY_JPQL);
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
						contactAccountRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ContactAccountRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contact account roles where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	@Override
	public void removeByAccountId(long accountId) {
		for (ContactAccountRole contactAccountRole :
				findByAccountId(
					accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(contactAccountRole);
		}
	}

	/**
	 * Returns the number of contact account roles where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching contact account roles
	 */
	@Override
	public int countByAccountId(long accountId) {
		FinderPath finderPath = _finderPathCountByAccountId;

		Object[] finderArgs = new Object[] {accountId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CONTACTACCOUNTROLE_WHERE);

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
		"contactAccountRole.id.accountId = ?";

	private FinderPath _finderPathWithPaginationFindByContactRoleId;
	private FinderPath _finderPathWithoutPaginationFindByContactRoleId;
	private FinderPath _finderPathCountByContactRoleId;

	/**
	 * Returns all the contact account roles where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @return the matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByContactRoleId(long contactRoleId) {
		return findByContactRoleId(
			contactRoleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contact account roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByContactRoleId(
		long contactRoleId, int start, int end) {

		return findByContactRoleId(contactRoleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contact account roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByContactRoleId(
		long contactRoleId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return findByContactRoleId(
			contactRoleId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contact account roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByContactRoleId(
		long contactRoleId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByContactRoleId;
				finderArgs = new Object[] {contactRoleId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByContactRoleId;
			finderArgs = new Object[] {
				contactRoleId, start, end, orderByComparator
			};
		}

		List<ContactAccountRole> list = null;

		if (useFinderCache) {
			list = (List<ContactAccountRole>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContactAccountRole contactAccountRole : list) {
					if (contactRoleId !=
							contactAccountRole.getContactRoleId()) {

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

			sb.append(_SQL_SELECT_CONTACTACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_CONTACTROLEID_CONTACTROLEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ContactAccountRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(contactRoleId);

				list = (List<ContactAccountRole>)QueryUtil.list(
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
	 * Returns the first contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole findByContactRoleId_First(
			long contactRoleId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws NoSuchContactAccountRoleException {

		ContactAccountRole contactAccountRole = fetchByContactRoleId_First(
			contactRoleId, orderByComparator);

		if (contactAccountRole != null) {
			return contactAccountRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("contactRoleId=");
		sb.append(contactRoleId);

		sb.append("}");

		throw new NoSuchContactAccountRoleException(sb.toString());
	}

	/**
	 * Returns the first contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole fetchByContactRoleId_First(
		long contactRoleId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		List<ContactAccountRole> list = findByContactRoleId(
			contactRoleId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole findByContactRoleId_Last(
			long contactRoleId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws NoSuchContactAccountRoleException {

		ContactAccountRole contactAccountRole = fetchByContactRoleId_Last(
			contactRoleId, orderByComparator);

		if (contactAccountRole != null) {
			return contactAccountRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("contactRoleId=");
		sb.append(contactRoleId);

		sb.append("}");

		throw new NoSuchContactAccountRoleException(sb.toString());
	}

	/**
	 * Returns the last contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole fetchByContactRoleId_Last(
		long contactRoleId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		int count = countByContactRoleId(contactRoleId);

		if (count == 0) {
			return null;
		}

		List<ContactAccountRole> list = findByContactRoleId(
			contactRoleId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contact account roles before and after the current contact account role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactAccountRolePK the primary key of the current contact account role
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	@Override
	public ContactAccountRole[] findByContactRoleId_PrevAndNext(
			ContactAccountRolePK contactAccountRolePK, long contactRoleId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws NoSuchContactAccountRoleException {

		ContactAccountRole contactAccountRole = findByPrimaryKey(
			contactAccountRolePK);

		Session session = null;

		try {
			session = openSession();

			ContactAccountRole[] array = new ContactAccountRoleImpl[3];

			array[0] = getByContactRoleId_PrevAndNext(
				session, contactAccountRole, contactRoleId, orderByComparator,
				true);

			array[1] = contactAccountRole;

			array[2] = getByContactRoleId_PrevAndNext(
				session, contactAccountRole, contactRoleId, orderByComparator,
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

	protected ContactAccountRole getByContactRoleId_PrevAndNext(
		Session session, ContactAccountRole contactAccountRole,
		long contactRoleId,
		OrderByComparator<ContactAccountRole> orderByComparator,
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

		sb.append(_SQL_SELECT_CONTACTACCOUNTROLE_WHERE);

		sb.append(_FINDER_COLUMN_CONTACTROLEID_CONTACTROLEID_2);

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
			sb.append(ContactAccountRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(contactRoleId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						contactAccountRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ContactAccountRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contact account roles where contactRoleId = &#63; from the database.
	 *
	 * @param contactRoleId the contact role ID
	 */
	@Override
	public void removeByContactRoleId(long contactRoleId) {
		for (ContactAccountRole contactAccountRole :
				findByContactRoleId(
					contactRoleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(contactAccountRole);
		}
	}

	/**
	 * Returns the number of contact account roles where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @return the number of matching contact account roles
	 */
	@Override
	public int countByContactRoleId(long contactRoleId) {
		FinderPath finderPath = _finderPathCountByContactRoleId;

		Object[] finderArgs = new Object[] {contactRoleId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CONTACTACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_CONTACTROLEID_CONTACTROLEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(contactRoleId);

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

	private static final String _FINDER_COLUMN_CONTACTROLEID_CONTACTROLEID_2 =
		"contactAccountRole.id.contactRoleId = ?";

	private FinderPath _finderPathWithPaginationFindByCI_AI;
	private FinderPath _finderPathWithoutPaginationFindByCI_AI;
	private FinderPath _finderPathCountByCI_AI;

	/**
	 * Returns all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @return the matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByCI_AI(
		long contactId, long accountId) {

		return findByCI_AI(
			contactId, accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByCI_AI(
		long contactId, long accountId, int start, int end) {

		return findByCI_AI(contactId, accountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByCI_AI(
		long contactId, long accountId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return findByCI_AI(
			contactId, accountId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact account roles
	 */
	@Override
	public List<ContactAccountRole> findByCI_AI(
		long contactId, long accountId, int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCI_AI;
				finderArgs = new Object[] {contactId, accountId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCI_AI;
			finderArgs = new Object[] {
				contactId, accountId, start, end, orderByComparator
			};
		}

		List<ContactAccountRole> list = null;

		if (useFinderCache) {
			list = (List<ContactAccountRole>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContactAccountRole contactAccountRole : list) {
					if ((contactId != contactAccountRole.getContactId()) ||
						(accountId != contactAccountRole.getAccountId())) {

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

			sb.append(_SQL_SELECT_CONTACTACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_CI_AI_CONTACTID_2);

			sb.append(_FINDER_COLUMN_CI_AI_ACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ContactAccountRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(contactId);

				queryPos.add(accountId);

				list = (List<ContactAccountRole>)QueryUtil.list(
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
	 * Returns the first contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole findByCI_AI_First(
			long contactId, long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws NoSuchContactAccountRoleException {

		ContactAccountRole contactAccountRole = fetchByCI_AI_First(
			contactId, accountId, orderByComparator);

		if (contactAccountRole != null) {
			return contactAccountRole;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("contactId=");
		sb.append(contactId);

		sb.append(", accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchContactAccountRoleException(sb.toString());
	}

	/**
	 * Returns the first contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole fetchByCI_AI_First(
		long contactId, long accountId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		List<ContactAccountRole> list = findByCI_AI(
			contactId, accountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role
	 * @throws NoSuchContactAccountRoleException if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole findByCI_AI_Last(
			long contactId, long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws NoSuchContactAccountRoleException {

		ContactAccountRole contactAccountRole = fetchByCI_AI_Last(
			contactId, accountId, orderByComparator);

		if (contactAccountRole != null) {
			return contactAccountRole;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("contactId=");
		sb.append(contactId);

		sb.append(", accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchContactAccountRoleException(sb.toString());
	}

	/**
	 * Returns the last contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact account role, or <code>null</code> if a matching contact account role could not be found
	 */
	@Override
	public ContactAccountRole fetchByCI_AI_Last(
		long contactId, long accountId,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		int count = countByCI_AI(contactId, accountId);

		if (count == 0) {
			return null;
		}

		List<ContactAccountRole> list = findByCI_AI(
			contactId, accountId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contact account roles before and after the current contact account role in the ordered set where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactAccountRolePK the primary key of the current contact account role
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	@Override
	public ContactAccountRole[] findByCI_AI_PrevAndNext(
			ContactAccountRolePK contactAccountRolePK, long contactId,
			long accountId,
			OrderByComparator<ContactAccountRole> orderByComparator)
		throws NoSuchContactAccountRoleException {

		ContactAccountRole contactAccountRole = findByPrimaryKey(
			contactAccountRolePK);

		Session session = null;

		try {
			session = openSession();

			ContactAccountRole[] array = new ContactAccountRoleImpl[3];

			array[0] = getByCI_AI_PrevAndNext(
				session, contactAccountRole, contactId, accountId,
				orderByComparator, true);

			array[1] = contactAccountRole;

			array[2] = getByCI_AI_PrevAndNext(
				session, contactAccountRole, contactId, accountId,
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

	protected ContactAccountRole getByCI_AI_PrevAndNext(
		Session session, ContactAccountRole contactAccountRole, long contactId,
		long accountId, OrderByComparator<ContactAccountRole> orderByComparator,
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

		sb.append(_SQL_SELECT_CONTACTACCOUNTROLE_WHERE);

		sb.append(_FINDER_COLUMN_CI_AI_CONTACTID_2);

		sb.append(_FINDER_COLUMN_CI_AI_ACCOUNTID_2);

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
			sb.append(ContactAccountRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(contactId);

		queryPos.add(accountId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						contactAccountRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ContactAccountRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contact account roles where contactId = &#63; and accountId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 */
	@Override
	public void removeByCI_AI(long contactId, long accountId) {
		for (ContactAccountRole contactAccountRole :
				findByCI_AI(
					contactId, accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(contactAccountRole);
		}
	}

	/**
	 * Returns the number of contact account roles where contactId = &#63; and accountId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param accountId the account ID
	 * @return the number of matching contact account roles
	 */
	@Override
	public int countByCI_AI(long contactId, long accountId) {
		FinderPath finderPath = _finderPathCountByCI_AI;

		Object[] finderArgs = new Object[] {contactId, accountId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_CONTACTACCOUNTROLE_WHERE);

			sb.append(_FINDER_COLUMN_CI_AI_CONTACTID_2);

			sb.append(_FINDER_COLUMN_CI_AI_ACCOUNTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(contactId);

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

	private static final String _FINDER_COLUMN_CI_AI_CONTACTID_2 =
		"contactAccountRole.id.contactId = ? AND ";

	private static final String _FINDER_COLUMN_CI_AI_ACCOUNTID_2 =
		"contactAccountRole.id.accountId = ?";

	public ContactAccountRolePersistenceImpl() {
		setModelClass(ContactAccountRole.class);

		setModelImplClass(ContactAccountRoleImpl.class);
		setModelPKClass(ContactAccountRolePK.class);
	}

	/**
	 * Caches the contact account role in the entity cache if it is enabled.
	 *
	 * @param contactAccountRole the contact account role
	 */
	@Override
	public void cacheResult(ContactAccountRole contactAccountRole) {
		entityCache.putResult(
			entityCacheEnabled, ContactAccountRoleImpl.class,
			contactAccountRole.getPrimaryKey(), contactAccountRole);

		contactAccountRole.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the contact account roles in the entity cache if it is enabled.
	 *
	 * @param contactAccountRoles the contact account roles
	 */
	@Override
	public void cacheResult(List<ContactAccountRole> contactAccountRoles) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (contactAccountRoles.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ContactAccountRole contactAccountRole : contactAccountRoles) {
			if (entityCache.getResult(
					entityCacheEnabled, ContactAccountRoleImpl.class,
					contactAccountRole.getPrimaryKey()) == null) {

				cacheResult(contactAccountRole);
			}
			else {
				contactAccountRole.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all contact account roles.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ContactAccountRoleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the contact account role.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ContactAccountRole contactAccountRole) {
		entityCache.removeResult(
			entityCacheEnabled, ContactAccountRoleImpl.class,
			contactAccountRole.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ContactAccountRole> contactAccountRoles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ContactAccountRole contactAccountRole : contactAccountRoles) {
			entityCache.removeResult(
				entityCacheEnabled, ContactAccountRoleImpl.class,
				contactAccountRole.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, ContactAccountRoleImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new contact account role with the primary key. Does not add the contact account role to the database.
	 *
	 * @param contactAccountRolePK the primary key for the new contact account role
	 * @return the new contact account role
	 */
	@Override
	public ContactAccountRole create(
		ContactAccountRolePK contactAccountRolePK) {

		ContactAccountRole contactAccountRole = new ContactAccountRoleImpl();

		contactAccountRole.setNew(true);
		contactAccountRole.setPrimaryKey(contactAccountRolePK);

		return contactAccountRole;
	}

	/**
	 * Removes the contact account role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactAccountRolePK the primary key of the contact account role
	 * @return the contact account role that was removed
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	@Override
	public ContactAccountRole remove(ContactAccountRolePK contactAccountRolePK)
		throws NoSuchContactAccountRoleException {

		return remove((Serializable)contactAccountRolePK);
	}

	/**
	 * Removes the contact account role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the contact account role
	 * @return the contact account role that was removed
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	@Override
	public ContactAccountRole remove(Serializable primaryKey)
		throws NoSuchContactAccountRoleException {

		Session session = null;

		try {
			session = openSession();

			ContactAccountRole contactAccountRole =
				(ContactAccountRole)session.get(
					ContactAccountRoleImpl.class, primaryKey);

			if (contactAccountRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContactAccountRoleException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(contactAccountRole);
		}
		catch (NoSuchContactAccountRoleException noSuchEntityException) {
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
	protected ContactAccountRole removeImpl(
		ContactAccountRole contactAccountRole) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(contactAccountRole)) {
				contactAccountRole = (ContactAccountRole)session.get(
					ContactAccountRoleImpl.class,
					contactAccountRole.getPrimaryKeyObj());
			}

			if (contactAccountRole != null) {
				session.delete(contactAccountRole);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (contactAccountRole != null) {
			clearCache(contactAccountRole);
		}

		return contactAccountRole;
	}

	@Override
	public ContactAccountRole updateImpl(
		ContactAccountRole contactAccountRole) {

		boolean isNew = contactAccountRole.isNew();

		if (!(contactAccountRole instanceof ContactAccountRoleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(contactAccountRole.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					contactAccountRole);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in contactAccountRole proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ContactAccountRole implementation " +
					contactAccountRole.getClass());
		}

		ContactAccountRoleModelImpl contactAccountRoleModelImpl =
			(ContactAccountRoleModelImpl)contactAccountRole;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(contactAccountRole);

				contactAccountRole.setNew(false);
			}
			else {
				contactAccountRole = (ContactAccountRole)session.merge(
					contactAccountRole);
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
				contactAccountRoleModelImpl.getContactId()
			};

			finderCache.removeResult(_finderPathCountByContactId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByContactId, args);

			args = new Object[] {contactAccountRoleModelImpl.getAccountId()};

			finderCache.removeResult(_finderPathCountByAccountId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAccountId, args);

			args = new Object[] {
				contactAccountRoleModelImpl.getContactRoleId()
			};

			finderCache.removeResult(_finderPathCountByContactRoleId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByContactRoleId, args);

			args = new Object[] {
				contactAccountRoleModelImpl.getContactId(),
				contactAccountRoleModelImpl.getAccountId()
			};

			finderCache.removeResult(_finderPathCountByCI_AI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCI_AI, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((contactAccountRoleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByContactId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					contactAccountRoleModelImpl.getOriginalContactId()
				};

				finderCache.removeResult(_finderPathCountByContactId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByContactId, args);

				args = new Object[] {
					contactAccountRoleModelImpl.getContactId()
				};

				finderCache.removeResult(_finderPathCountByContactId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByContactId, args);
			}

			if ((contactAccountRoleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByAccountId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					contactAccountRoleModelImpl.getOriginalAccountId()
				};

				finderCache.removeResult(_finderPathCountByAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountId, args);

				args = new Object[] {
					contactAccountRoleModelImpl.getAccountId()
				};

				finderCache.removeResult(_finderPathCountByAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountId, args);
			}

			if ((contactAccountRoleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByContactRoleId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					contactAccountRoleModelImpl.getOriginalContactRoleId()
				};

				finderCache.removeResult(_finderPathCountByContactRoleId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByContactRoleId, args);

				args = new Object[] {
					contactAccountRoleModelImpl.getContactRoleId()
				};

				finderCache.removeResult(_finderPathCountByContactRoleId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByContactRoleId, args);
			}

			if ((contactAccountRoleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCI_AI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					contactAccountRoleModelImpl.getOriginalContactId(),
					contactAccountRoleModelImpl.getOriginalAccountId()
				};

				finderCache.removeResult(_finderPathCountByCI_AI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCI_AI, args);

				args = new Object[] {
					contactAccountRoleModelImpl.getContactId(),
					contactAccountRoleModelImpl.getAccountId()
				};

				finderCache.removeResult(_finderPathCountByCI_AI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCI_AI, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, ContactAccountRoleImpl.class,
			contactAccountRole.getPrimaryKey(), contactAccountRole, false);

		contactAccountRole.resetOriginalValues();

		return contactAccountRole;
	}

	/**
	 * Returns the contact account role with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the contact account role
	 * @return the contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	@Override
	public ContactAccountRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContactAccountRoleException {

		ContactAccountRole contactAccountRole = fetchByPrimaryKey(primaryKey);

		if (contactAccountRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContactAccountRoleException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return contactAccountRole;
	}

	/**
	 * Returns the contact account role with the primary key or throws a <code>NoSuchContactAccountRoleException</code> if it could not be found.
	 *
	 * @param contactAccountRolePK the primary key of the contact account role
	 * @return the contact account role
	 * @throws NoSuchContactAccountRoleException if a contact account role with the primary key could not be found
	 */
	@Override
	public ContactAccountRole findByPrimaryKey(
			ContactAccountRolePK contactAccountRolePK)
		throws NoSuchContactAccountRoleException {

		return findByPrimaryKey((Serializable)contactAccountRolePK);
	}

	/**
	 * Returns the contact account role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactAccountRolePK the primary key of the contact account role
	 * @return the contact account role, or <code>null</code> if a contact account role with the primary key could not be found
	 */
	@Override
	public ContactAccountRole fetchByPrimaryKey(
		ContactAccountRolePK contactAccountRolePK) {

		return fetchByPrimaryKey((Serializable)contactAccountRolePK);
	}

	/**
	 * Returns all the contact account roles.
	 *
	 * @return the contact account roles
	 */
	@Override
	public List<ContactAccountRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contact account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @return the range of contact account roles
	 */
	@Override
	public List<ContactAccountRole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the contact account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contact account roles
	 */
	@Override
	public List<ContactAccountRole> findAll(
		int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contact account roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactAccountRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact account roles
	 * @param end the upper bound of the range of contact account roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of contact account roles
	 */
	@Override
	public List<ContactAccountRole> findAll(
		int start, int end,
		OrderByComparator<ContactAccountRole> orderByComparator,
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

		List<ContactAccountRole> list = null;

		if (useFinderCache) {
			list = (List<ContactAccountRole>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CONTACTACCOUNTROLE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CONTACTACCOUNTROLE;

				sql = sql.concat(ContactAccountRoleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ContactAccountRole>)QueryUtil.list(
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
	 * Removes all the contact account roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ContactAccountRole contactAccountRole : findAll()) {
			remove(contactAccountRole);
		}
	}

	/**
	 * Returns the number of contact account roles.
	 *
	 * @return the number of contact account roles
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
					_SQL_COUNT_CONTACTACCOUNTROLE);

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
		return "contactAccountRolePK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CONTACTACCOUNTROLE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ContactAccountRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the contact account role persistence.
	 */
	@Activate
	public void activate() {
		ContactAccountRoleModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		ContactAccountRoleModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ContactAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ContactAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByContactId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ContactAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContactId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByContactId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ContactAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContactId",
			new String[] {Long.class.getName()},
			ContactAccountRoleModelImpl.CONTACTID_COLUMN_BITMASK);

		_finderPathCountByContactId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContactId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ContactAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ContactAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountId",
			new String[] {Long.class.getName()},
			ContactAccountRoleModelImpl.ACCOUNTID_COLUMN_BITMASK);

		_finderPathCountByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByContactRoleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ContactAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContactRoleId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByContactRoleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ContactAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContactRoleId",
			new String[] {Long.class.getName()},
			ContactAccountRoleModelImpl.CONTACTROLEID_COLUMN_BITMASK);

		_finderPathCountByContactRoleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContactRoleId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCI_AI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ContactAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCI_AI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCI_AI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ContactAccountRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCI_AI",
			new String[] {Long.class.getName(), Long.class.getName()},
			ContactAccountRoleModelImpl.CONTACTID_COLUMN_BITMASK |
			ContactAccountRoleModelImpl.ACCOUNTID_COLUMN_BITMASK);

		_finderPathCountByCI_AI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCI_AI",
			new String[] {Long.class.getName(), Long.class.getName()});

		ContactAccountRoleUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		ContactAccountRoleUtil.setPersistence(null);

		entityCache.removeCache(ContactAccountRoleImpl.class.getName());

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
				"value.object.column.bitmask.enabled.com.liferay.osb.koroneiki.taproot.model.ContactAccountRole"),
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

	private static final String _SQL_SELECT_CONTACTACCOUNTROLE =
		"SELECT contactAccountRole FROM ContactAccountRole contactAccountRole";

	private static final String _SQL_SELECT_CONTACTACCOUNTROLE_WHERE =
		"SELECT contactAccountRole FROM ContactAccountRole contactAccountRole WHERE ";

	private static final String _SQL_COUNT_CONTACTACCOUNTROLE =
		"SELECT COUNT(contactAccountRole) FROM ContactAccountRole contactAccountRole";

	private static final String _SQL_COUNT_CONTACTACCOUNTROLE_WHERE =
		"SELECT COUNT(contactAccountRole) FROM ContactAccountRole contactAccountRole WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "contactAccountRole.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ContactAccountRole exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ContactAccountRole exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ContactAccountRolePersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"contactId", "accountId", "contactRoleId"});

}