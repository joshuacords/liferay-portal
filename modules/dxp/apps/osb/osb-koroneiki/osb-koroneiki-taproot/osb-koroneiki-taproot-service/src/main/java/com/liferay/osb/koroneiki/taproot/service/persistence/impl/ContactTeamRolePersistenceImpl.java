/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence.impl;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchContactTeamRoleException;
import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.osb.koroneiki.taproot.model.impl.ContactTeamRoleImpl;
import com.liferay.osb.koroneiki.taproot.model.impl.ContactTeamRoleModelImpl;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactTeamRolePK;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactTeamRolePersistence;
import com.liferay.osb.koroneiki.taproot.service.persistence.ContactTeamRoleUtil;
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
 * The persistence implementation for the contact team role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ContactTeamRolePersistence.class)
public class ContactTeamRolePersistenceImpl
	extends BasePersistenceImpl<ContactTeamRole>
	implements ContactTeamRolePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ContactTeamRoleUtil</code> to access the contact team role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ContactTeamRoleImpl.class.getName();

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
	 * Returns all the contact team roles where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByContactId(long contactId) {
		return findByContactId(
			contactId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contact team roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByContactId(
		long contactId, int start, int end) {

		return findByContactId(contactId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contact team roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByContactId(
		long contactId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return findByContactId(contactId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contact team roles where contactId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByContactId(
		long contactId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator,
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

		List<ContactTeamRole> list = null;

		if (useFinderCache) {
			list = (List<ContactTeamRole>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContactTeamRole contactTeamRole : list) {
					if (contactId != contactTeamRole.getContactId()) {
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

			sb.append(_SQL_SELECT_CONTACTTEAMROLE_WHERE);

			sb.append(_FINDER_COLUMN_CONTACTID_CONTACTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ContactTeamRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(contactId);

				list = (List<ContactTeamRole>)QueryUtil.list(
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
	 * Returns the first contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole findByContactId_First(
			long contactId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws NoSuchContactTeamRoleException {

		ContactTeamRole contactTeamRole = fetchByContactId_First(
			contactId, orderByComparator);

		if (contactTeamRole != null) {
			return contactTeamRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("contactId=");
		sb.append(contactId);

		sb.append("}");

		throw new NoSuchContactTeamRoleException(sb.toString());
	}

	/**
	 * Returns the first contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole fetchByContactId_First(
		long contactId, OrderByComparator<ContactTeamRole> orderByComparator) {

		List<ContactTeamRole> list = findByContactId(
			contactId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole findByContactId_Last(
			long contactId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws NoSuchContactTeamRoleException {

		ContactTeamRole contactTeamRole = fetchByContactId_Last(
			contactId, orderByComparator);

		if (contactTeamRole != null) {
			return contactTeamRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("contactId=");
		sb.append(contactId);

		sb.append("}");

		throw new NoSuchContactTeamRoleException(sb.toString());
	}

	/**
	 * Returns the last contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole fetchByContactId_Last(
		long contactId, OrderByComparator<ContactTeamRole> orderByComparator) {

		int count = countByContactId(contactId);

		if (count == 0) {
			return null;
		}

		List<ContactTeamRole> list = findByContactId(
			contactId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contact team roles before and after the current contact team role in the ordered set where contactId = &#63;.
	 *
	 * @param contactTeamRolePK the primary key of the current contact team role
	 * @param contactId the contact ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	@Override
	public ContactTeamRole[] findByContactId_PrevAndNext(
			ContactTeamRolePK contactTeamRolePK, long contactId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws NoSuchContactTeamRoleException {

		ContactTeamRole contactTeamRole = findByPrimaryKey(contactTeamRolePK);

		Session session = null;

		try {
			session = openSession();

			ContactTeamRole[] array = new ContactTeamRoleImpl[3];

			array[0] = getByContactId_PrevAndNext(
				session, contactTeamRole, contactId, orderByComparator, true);

			array[1] = contactTeamRole;

			array[2] = getByContactId_PrevAndNext(
				session, contactTeamRole, contactId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ContactTeamRole getByContactId_PrevAndNext(
		Session session, ContactTeamRole contactTeamRole, long contactId,
		OrderByComparator<ContactTeamRole> orderByComparator,
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

		sb.append(_SQL_SELECT_CONTACTTEAMROLE_WHERE);

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
			sb.append(ContactTeamRoleModelImpl.ORDER_BY_JPQL);
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
						contactTeamRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ContactTeamRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contact team roles where contactId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 */
	@Override
	public void removeByContactId(long contactId) {
		for (ContactTeamRole contactTeamRole :
				findByContactId(
					contactId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(contactTeamRole);
		}
	}

	/**
	 * Returns the number of contact team roles where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the number of matching contact team roles
	 */
	@Override
	public int countByContactId(long contactId) {
		FinderPath finderPath = _finderPathCountByContactId;

		Object[] finderArgs = new Object[] {contactId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CONTACTTEAMROLE_WHERE);

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
		"contactTeamRole.id.contactId = ?";

	private FinderPath _finderPathWithPaginationFindByTeamId;
	private FinderPath _finderPathWithoutPaginationFindByTeamId;
	private FinderPath _finderPathCountByTeamId;

	/**
	 * Returns all the contact team roles where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @return the matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByTeamId(long teamId) {
		return findByTeamId(teamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contact team roles where teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByTeamId(long teamId, int start, int end) {
		return findByTeamId(teamId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contact team roles where teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByTeamId(
		long teamId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return findByTeamId(teamId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contact team roles where teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByTeamId(
		long teamId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator,
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

		List<ContactTeamRole> list = null;

		if (useFinderCache) {
			list = (List<ContactTeamRole>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContactTeamRole contactTeamRole : list) {
					if (teamId != contactTeamRole.getTeamId()) {
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

			sb.append(_SQL_SELECT_CONTACTTEAMROLE_WHERE);

			sb.append(_FINDER_COLUMN_TEAMID_TEAMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ContactTeamRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(teamId);

				list = (List<ContactTeamRole>)QueryUtil.list(
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
	 * Returns the first contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole findByTeamId_First(
			long teamId, OrderByComparator<ContactTeamRole> orderByComparator)
		throws NoSuchContactTeamRoleException {

		ContactTeamRole contactTeamRole = fetchByTeamId_First(
			teamId, orderByComparator);

		if (contactTeamRole != null) {
			return contactTeamRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teamId=");
		sb.append(teamId);

		sb.append("}");

		throw new NoSuchContactTeamRoleException(sb.toString());
	}

	/**
	 * Returns the first contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole fetchByTeamId_First(
		long teamId, OrderByComparator<ContactTeamRole> orderByComparator) {

		List<ContactTeamRole> list = findByTeamId(
			teamId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole findByTeamId_Last(
			long teamId, OrderByComparator<ContactTeamRole> orderByComparator)
		throws NoSuchContactTeamRoleException {

		ContactTeamRole contactTeamRole = fetchByTeamId_Last(
			teamId, orderByComparator);

		if (contactTeamRole != null) {
			return contactTeamRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("teamId=");
		sb.append(teamId);

		sb.append("}");

		throw new NoSuchContactTeamRoleException(sb.toString());
	}

	/**
	 * Returns the last contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole fetchByTeamId_Last(
		long teamId, OrderByComparator<ContactTeamRole> orderByComparator) {

		int count = countByTeamId(teamId);

		if (count == 0) {
			return null;
		}

		List<ContactTeamRole> list = findByTeamId(
			teamId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contact team roles before and after the current contact team role in the ordered set where teamId = &#63;.
	 *
	 * @param contactTeamRolePK the primary key of the current contact team role
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	@Override
	public ContactTeamRole[] findByTeamId_PrevAndNext(
			ContactTeamRolePK contactTeamRolePK, long teamId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws NoSuchContactTeamRoleException {

		ContactTeamRole contactTeamRole = findByPrimaryKey(contactTeamRolePK);

		Session session = null;

		try {
			session = openSession();

			ContactTeamRole[] array = new ContactTeamRoleImpl[3];

			array[0] = getByTeamId_PrevAndNext(
				session, contactTeamRole, teamId, orderByComparator, true);

			array[1] = contactTeamRole;

			array[2] = getByTeamId_PrevAndNext(
				session, contactTeamRole, teamId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ContactTeamRole getByTeamId_PrevAndNext(
		Session session, ContactTeamRole contactTeamRole, long teamId,
		OrderByComparator<ContactTeamRole> orderByComparator,
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

		sb.append(_SQL_SELECT_CONTACTTEAMROLE_WHERE);

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
			sb.append(ContactTeamRoleModelImpl.ORDER_BY_JPQL);
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
						contactTeamRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ContactTeamRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contact team roles where teamId = &#63; from the database.
	 *
	 * @param teamId the team ID
	 */
	@Override
	public void removeByTeamId(long teamId) {
		for (ContactTeamRole contactTeamRole :
				findByTeamId(
					teamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(contactTeamRole);
		}
	}

	/**
	 * Returns the number of contact team roles where teamId = &#63;.
	 *
	 * @param teamId the team ID
	 * @return the number of matching contact team roles
	 */
	@Override
	public int countByTeamId(long teamId) {
		FinderPath finderPath = _finderPathCountByTeamId;

		Object[] finderArgs = new Object[] {teamId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CONTACTTEAMROLE_WHERE);

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
		"contactTeamRole.id.teamId = ?";

	private FinderPath _finderPathWithPaginationFindByContactRoleId;
	private FinderPath _finderPathWithoutPaginationFindByContactRoleId;
	private FinderPath _finderPathCountByContactRoleId;

	/**
	 * Returns all the contact team roles where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @return the matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByContactRoleId(long contactRoleId) {
		return findByContactRoleId(
			contactRoleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contact team roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByContactRoleId(
		long contactRoleId, int start, int end) {

		return findByContactRoleId(contactRoleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contact team roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByContactRoleId(
		long contactRoleId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return findByContactRoleId(
			contactRoleId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contact team roles where contactRoleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactRoleId the contact role ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByContactRoleId(
		long contactRoleId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator,
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

		List<ContactTeamRole> list = null;

		if (useFinderCache) {
			list = (List<ContactTeamRole>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContactTeamRole contactTeamRole : list) {
					if (contactRoleId != contactTeamRole.getContactRoleId()) {
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

			sb.append(_SQL_SELECT_CONTACTTEAMROLE_WHERE);

			sb.append(_FINDER_COLUMN_CONTACTROLEID_CONTACTROLEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ContactTeamRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(contactRoleId);

				list = (List<ContactTeamRole>)QueryUtil.list(
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
	 * Returns the first contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole findByContactRoleId_First(
			long contactRoleId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws NoSuchContactTeamRoleException {

		ContactTeamRole contactTeamRole = fetchByContactRoleId_First(
			contactRoleId, orderByComparator);

		if (contactTeamRole != null) {
			return contactTeamRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("contactRoleId=");
		sb.append(contactRoleId);

		sb.append("}");

		throw new NoSuchContactTeamRoleException(sb.toString());
	}

	/**
	 * Returns the first contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole fetchByContactRoleId_First(
		long contactRoleId,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		List<ContactTeamRole> list = findByContactRoleId(
			contactRoleId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole findByContactRoleId_Last(
			long contactRoleId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws NoSuchContactTeamRoleException {

		ContactTeamRole contactTeamRole = fetchByContactRoleId_Last(
			contactRoleId, orderByComparator);

		if (contactTeamRole != null) {
			return contactTeamRole;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("contactRoleId=");
		sb.append(contactRoleId);

		sb.append("}");

		throw new NoSuchContactTeamRoleException(sb.toString());
	}

	/**
	 * Returns the last contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole fetchByContactRoleId_Last(
		long contactRoleId,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		int count = countByContactRoleId(contactRoleId);

		if (count == 0) {
			return null;
		}

		List<ContactTeamRole> list = findByContactRoleId(
			contactRoleId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contact team roles before and after the current contact team role in the ordered set where contactRoleId = &#63;.
	 *
	 * @param contactTeamRolePK the primary key of the current contact team role
	 * @param contactRoleId the contact role ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	@Override
	public ContactTeamRole[] findByContactRoleId_PrevAndNext(
			ContactTeamRolePK contactTeamRolePK, long contactRoleId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws NoSuchContactTeamRoleException {

		ContactTeamRole contactTeamRole = findByPrimaryKey(contactTeamRolePK);

		Session session = null;

		try {
			session = openSession();

			ContactTeamRole[] array = new ContactTeamRoleImpl[3];

			array[0] = getByContactRoleId_PrevAndNext(
				session, contactTeamRole, contactRoleId, orderByComparator,
				true);

			array[1] = contactTeamRole;

			array[2] = getByContactRoleId_PrevAndNext(
				session, contactTeamRole, contactRoleId, orderByComparator,
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

	protected ContactTeamRole getByContactRoleId_PrevAndNext(
		Session session, ContactTeamRole contactTeamRole, long contactRoleId,
		OrderByComparator<ContactTeamRole> orderByComparator,
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

		sb.append(_SQL_SELECT_CONTACTTEAMROLE_WHERE);

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
			sb.append(ContactTeamRoleModelImpl.ORDER_BY_JPQL);
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
						contactTeamRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ContactTeamRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contact team roles where contactRoleId = &#63; from the database.
	 *
	 * @param contactRoleId the contact role ID
	 */
	@Override
	public void removeByContactRoleId(long contactRoleId) {
		for (ContactTeamRole contactTeamRole :
				findByContactRoleId(
					contactRoleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(contactTeamRole);
		}
	}

	/**
	 * Returns the number of contact team roles where contactRoleId = &#63;.
	 *
	 * @param contactRoleId the contact role ID
	 * @return the number of matching contact team roles
	 */
	@Override
	public int countByContactRoleId(long contactRoleId) {
		FinderPath finderPath = _finderPathCountByContactRoleId;

		Object[] finderArgs = new Object[] {contactRoleId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_CONTACTTEAMROLE_WHERE);

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
		"contactTeamRole.id.contactRoleId = ?";

	private FinderPath _finderPathWithPaginationFindByCI_TI;
	private FinderPath _finderPathWithoutPaginationFindByCI_TI;
	private FinderPath _finderPathCountByCI_TI;

	/**
	 * Returns all the contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @return the matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByCI_TI(long contactId, long teamId) {
		return findByCI_TI(
			contactId, teamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByCI_TI(
		long contactId, long teamId, int start, int end) {

		return findByCI_TI(contactId, teamId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByCI_TI(
		long contactId, long teamId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return findByCI_TI(
			contactId, teamId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching contact team roles
	 */
	@Override
	public List<ContactTeamRole> findByCI_TI(
		long contactId, long teamId, int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCI_TI;
				finderArgs = new Object[] {contactId, teamId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCI_TI;
			finderArgs = new Object[] {
				contactId, teamId, start, end, orderByComparator
			};
		}

		List<ContactTeamRole> list = null;

		if (useFinderCache) {
			list = (List<ContactTeamRole>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ContactTeamRole contactTeamRole : list) {
					if ((contactId != contactTeamRole.getContactId()) ||
						(teamId != contactTeamRole.getTeamId())) {

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

			sb.append(_SQL_SELECT_CONTACTTEAMROLE_WHERE);

			sb.append(_FINDER_COLUMN_CI_TI_CONTACTID_2);

			sb.append(_FINDER_COLUMN_CI_TI_TEAMID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ContactTeamRoleModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(contactId);

				queryPos.add(teamId);

				list = (List<ContactTeamRole>)QueryUtil.list(
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
	 * Returns the first contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole findByCI_TI_First(
			long contactId, long teamId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws NoSuchContactTeamRoleException {

		ContactTeamRole contactTeamRole = fetchByCI_TI_First(
			contactId, teamId, orderByComparator);

		if (contactTeamRole != null) {
			return contactTeamRole;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("contactId=");
		sb.append(contactId);

		sb.append(", teamId=");
		sb.append(teamId);

		sb.append("}");

		throw new NoSuchContactTeamRoleException(sb.toString());
	}

	/**
	 * Returns the first contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole fetchByCI_TI_First(
		long contactId, long teamId,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		List<ContactTeamRole> list = findByCI_TI(
			contactId, teamId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role
	 * @throws NoSuchContactTeamRoleException if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole findByCI_TI_Last(
			long contactId, long teamId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws NoSuchContactTeamRoleException {

		ContactTeamRole contactTeamRole = fetchByCI_TI_Last(
			contactId, teamId, orderByComparator);

		if (contactTeamRole != null) {
			return contactTeamRole;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("contactId=");
		sb.append(contactId);

		sb.append(", teamId=");
		sb.append(teamId);

		sb.append("}");

		throw new NoSuchContactTeamRoleException(sb.toString());
	}

	/**
	 * Returns the last contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching contact team role, or <code>null</code> if a matching contact team role could not be found
	 */
	@Override
	public ContactTeamRole fetchByCI_TI_Last(
		long contactId, long teamId,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		int count = countByCI_TI(contactId, teamId);

		if (count == 0) {
			return null;
		}

		List<ContactTeamRole> list = findByCI_TI(
			contactId, teamId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the contact team roles before and after the current contact team role in the ordered set where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactTeamRolePK the primary key of the current contact team role
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	@Override
	public ContactTeamRole[] findByCI_TI_PrevAndNext(
			ContactTeamRolePK contactTeamRolePK, long contactId, long teamId,
			OrderByComparator<ContactTeamRole> orderByComparator)
		throws NoSuchContactTeamRoleException {

		ContactTeamRole contactTeamRole = findByPrimaryKey(contactTeamRolePK);

		Session session = null;

		try {
			session = openSession();

			ContactTeamRole[] array = new ContactTeamRoleImpl[3];

			array[0] = getByCI_TI_PrevAndNext(
				session, contactTeamRole, contactId, teamId, orderByComparator,
				true);

			array[1] = contactTeamRole;

			array[2] = getByCI_TI_PrevAndNext(
				session, contactTeamRole, contactId, teamId, orderByComparator,
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

	protected ContactTeamRole getByCI_TI_PrevAndNext(
		Session session, ContactTeamRole contactTeamRole, long contactId,
		long teamId, OrderByComparator<ContactTeamRole> orderByComparator,
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

		sb.append(_SQL_SELECT_CONTACTTEAMROLE_WHERE);

		sb.append(_FINDER_COLUMN_CI_TI_CONTACTID_2);

		sb.append(_FINDER_COLUMN_CI_TI_TEAMID_2);

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
			sb.append(ContactTeamRoleModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(contactId);

		queryPos.add(teamId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						contactTeamRole)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ContactTeamRole> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the contact team roles where contactId = &#63; and teamId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 */
	@Override
	public void removeByCI_TI(long contactId, long teamId) {
		for (ContactTeamRole contactTeamRole :
				findByCI_TI(
					contactId, teamId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(contactTeamRole);
		}
	}

	/**
	 * Returns the number of contact team roles where contactId = &#63; and teamId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @param teamId the team ID
	 * @return the number of matching contact team roles
	 */
	@Override
	public int countByCI_TI(long contactId, long teamId) {
		FinderPath finderPath = _finderPathCountByCI_TI;

		Object[] finderArgs = new Object[] {contactId, teamId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_CONTACTTEAMROLE_WHERE);

			sb.append(_FINDER_COLUMN_CI_TI_CONTACTID_2);

			sb.append(_FINDER_COLUMN_CI_TI_TEAMID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(contactId);

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

	private static final String _FINDER_COLUMN_CI_TI_CONTACTID_2 =
		"contactTeamRole.id.contactId = ? AND ";

	private static final String _FINDER_COLUMN_CI_TI_TEAMID_2 =
		"contactTeamRole.id.teamId = ?";

	public ContactTeamRolePersistenceImpl() {
		setModelClass(ContactTeamRole.class);

		setModelImplClass(ContactTeamRoleImpl.class);
		setModelPKClass(ContactTeamRolePK.class);
	}

	/**
	 * Caches the contact team role in the entity cache if it is enabled.
	 *
	 * @param contactTeamRole the contact team role
	 */
	@Override
	public void cacheResult(ContactTeamRole contactTeamRole) {
		entityCache.putResult(
			entityCacheEnabled, ContactTeamRoleImpl.class,
			contactTeamRole.getPrimaryKey(), contactTeamRole);

		contactTeamRole.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the contact team roles in the entity cache if it is enabled.
	 *
	 * @param contactTeamRoles the contact team roles
	 */
	@Override
	public void cacheResult(List<ContactTeamRole> contactTeamRoles) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (contactTeamRoles.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ContactTeamRole contactTeamRole : contactTeamRoles) {
			if (entityCache.getResult(
					entityCacheEnabled, ContactTeamRoleImpl.class,
					contactTeamRole.getPrimaryKey()) == null) {

				cacheResult(contactTeamRole);
			}
			else {
				contactTeamRole.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all contact team roles.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ContactTeamRoleImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the contact team role.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ContactTeamRole contactTeamRole) {
		entityCache.removeResult(
			entityCacheEnabled, ContactTeamRoleImpl.class,
			contactTeamRole.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ContactTeamRole> contactTeamRoles) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ContactTeamRole contactTeamRole : contactTeamRoles) {
			entityCache.removeResult(
				entityCacheEnabled, ContactTeamRoleImpl.class,
				contactTeamRole.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, ContactTeamRoleImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new contact team role with the primary key. Does not add the contact team role to the database.
	 *
	 * @param contactTeamRolePK the primary key for the new contact team role
	 * @return the new contact team role
	 */
	@Override
	public ContactTeamRole create(ContactTeamRolePK contactTeamRolePK) {
		ContactTeamRole contactTeamRole = new ContactTeamRoleImpl();

		contactTeamRole.setNew(true);
		contactTeamRole.setPrimaryKey(contactTeamRolePK);

		return contactTeamRole;
	}

	/**
	 * Removes the contact team role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contactTeamRolePK the primary key of the contact team role
	 * @return the contact team role that was removed
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	@Override
	public ContactTeamRole remove(ContactTeamRolePK contactTeamRolePK)
		throws NoSuchContactTeamRoleException {

		return remove((Serializable)contactTeamRolePK);
	}

	/**
	 * Removes the contact team role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the contact team role
	 * @return the contact team role that was removed
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	@Override
	public ContactTeamRole remove(Serializable primaryKey)
		throws NoSuchContactTeamRoleException {

		Session session = null;

		try {
			session = openSession();

			ContactTeamRole contactTeamRole = (ContactTeamRole)session.get(
				ContactTeamRoleImpl.class, primaryKey);

			if (contactTeamRole == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContactTeamRoleException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(contactTeamRole);
		}
		catch (NoSuchContactTeamRoleException noSuchEntityException) {
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
	protected ContactTeamRole removeImpl(ContactTeamRole contactTeamRole) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(contactTeamRole)) {
				contactTeamRole = (ContactTeamRole)session.get(
					ContactTeamRoleImpl.class,
					contactTeamRole.getPrimaryKeyObj());
			}

			if (contactTeamRole != null) {
				session.delete(contactTeamRole);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (contactTeamRole != null) {
			clearCache(contactTeamRole);
		}

		return contactTeamRole;
	}

	@Override
	public ContactTeamRole updateImpl(ContactTeamRole contactTeamRole) {
		boolean isNew = contactTeamRole.isNew();

		if (!(contactTeamRole instanceof ContactTeamRoleModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(contactTeamRole.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					contactTeamRole);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in contactTeamRole proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ContactTeamRole implementation " +
					contactTeamRole.getClass());
		}

		ContactTeamRoleModelImpl contactTeamRoleModelImpl =
			(ContactTeamRoleModelImpl)contactTeamRole;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(contactTeamRole);

				contactTeamRole.setNew(false);
			}
			else {
				contactTeamRole = (ContactTeamRole)session.merge(
					contactTeamRole);
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
				contactTeamRoleModelImpl.getContactId()
			};

			finderCache.removeResult(_finderPathCountByContactId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByContactId, args);

			args = new Object[] {contactTeamRoleModelImpl.getTeamId()};

			finderCache.removeResult(_finderPathCountByTeamId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByTeamId, args);

			args = new Object[] {contactTeamRoleModelImpl.getContactRoleId()};

			finderCache.removeResult(_finderPathCountByContactRoleId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByContactRoleId, args);

			args = new Object[] {
				contactTeamRoleModelImpl.getContactId(),
				contactTeamRoleModelImpl.getTeamId()
			};

			finderCache.removeResult(_finderPathCountByCI_TI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByCI_TI, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((contactTeamRoleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByContactId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					contactTeamRoleModelImpl.getOriginalContactId()
				};

				finderCache.removeResult(_finderPathCountByContactId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByContactId, args);

				args = new Object[] {contactTeamRoleModelImpl.getContactId()};

				finderCache.removeResult(_finderPathCountByContactId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByContactId, args);
			}

			if ((contactTeamRoleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByTeamId.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					contactTeamRoleModelImpl.getOriginalTeamId()
				};

				finderCache.removeResult(_finderPathCountByTeamId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTeamId, args);

				args = new Object[] {contactTeamRoleModelImpl.getTeamId()};

				finderCache.removeResult(_finderPathCountByTeamId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTeamId, args);
			}

			if ((contactTeamRoleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByContactRoleId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					contactTeamRoleModelImpl.getOriginalContactRoleId()
				};

				finderCache.removeResult(_finderPathCountByContactRoleId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByContactRoleId, args);

				args = new Object[] {
					contactTeamRoleModelImpl.getContactRoleId()
				};

				finderCache.removeResult(_finderPathCountByContactRoleId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByContactRoleId, args);
			}

			if ((contactTeamRoleModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByCI_TI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					contactTeamRoleModelImpl.getOriginalContactId(),
					contactTeamRoleModelImpl.getOriginalTeamId()
				};

				finderCache.removeResult(_finderPathCountByCI_TI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCI_TI, args);

				args = new Object[] {
					contactTeamRoleModelImpl.getContactId(),
					contactTeamRoleModelImpl.getTeamId()
				};

				finderCache.removeResult(_finderPathCountByCI_TI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByCI_TI, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, ContactTeamRoleImpl.class,
			contactTeamRole.getPrimaryKey(), contactTeamRole, false);

		contactTeamRole.resetOriginalValues();

		return contactTeamRole;
	}

	/**
	 * Returns the contact team role with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the contact team role
	 * @return the contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	@Override
	public ContactTeamRole findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContactTeamRoleException {

		ContactTeamRole contactTeamRole = fetchByPrimaryKey(primaryKey);

		if (contactTeamRole == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContactTeamRoleException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return contactTeamRole;
	}

	/**
	 * Returns the contact team role with the primary key or throws a <code>NoSuchContactTeamRoleException</code> if it could not be found.
	 *
	 * @param contactTeamRolePK the primary key of the contact team role
	 * @return the contact team role
	 * @throws NoSuchContactTeamRoleException if a contact team role with the primary key could not be found
	 */
	@Override
	public ContactTeamRole findByPrimaryKey(ContactTeamRolePK contactTeamRolePK)
		throws NoSuchContactTeamRoleException {

		return findByPrimaryKey((Serializable)contactTeamRolePK);
	}

	/**
	 * Returns the contact team role with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contactTeamRolePK the primary key of the contact team role
	 * @return the contact team role, or <code>null</code> if a contact team role with the primary key could not be found
	 */
	@Override
	public ContactTeamRole fetchByPrimaryKey(
		ContactTeamRolePK contactTeamRolePK) {

		return fetchByPrimaryKey((Serializable)contactTeamRolePK);
	}

	/**
	 * Returns all the contact team roles.
	 *
	 * @return the contact team roles
	 */
	@Override
	public List<ContactTeamRole> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contact team roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @return the range of contact team roles
	 */
	@Override
	public List<ContactTeamRole> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the contact team roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contact team roles
	 */
	@Override
	public List<ContactTeamRole> findAll(
		int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contact team roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ContactTeamRoleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contact team roles
	 * @param end the upper bound of the range of contact team roles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of contact team roles
	 */
	@Override
	public List<ContactTeamRole> findAll(
		int start, int end,
		OrderByComparator<ContactTeamRole> orderByComparator,
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

		List<ContactTeamRole> list = null;

		if (useFinderCache) {
			list = (List<ContactTeamRole>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_CONTACTTEAMROLE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_CONTACTTEAMROLE;

				sql = sql.concat(ContactTeamRoleModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ContactTeamRole>)QueryUtil.list(
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
	 * Removes all the contact team roles from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ContactTeamRole contactTeamRole : findAll()) {
			remove(contactTeamRole);
		}
	}

	/**
	 * Returns the number of contact team roles.
	 *
	 * @return the number of contact team roles
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_CONTACTTEAMROLE);

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
		return "contactTeamRolePK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CONTACTTEAMROLE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ContactTeamRoleModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the contact team role persistence.
	 */
	@Activate
	public void activate() {
		ContactTeamRoleModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		ContactTeamRoleModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ContactTeamRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ContactTeamRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByContactId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ContactTeamRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContactId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByContactId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ContactTeamRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContactId",
			new String[] {Long.class.getName()},
			ContactTeamRoleModelImpl.CONTACTID_COLUMN_BITMASK);

		_finderPathCountByContactId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContactId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByTeamId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ContactTeamRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTeamId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByTeamId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ContactTeamRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTeamId",
			new String[] {Long.class.getName()},
			ContactTeamRoleModelImpl.TEAMID_COLUMN_BITMASK);

		_finderPathCountByTeamId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTeamId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByContactRoleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ContactTeamRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContactRoleId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByContactRoleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ContactTeamRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContactRoleId",
			new String[] {Long.class.getName()},
			ContactTeamRoleModelImpl.CONTACTROLEID_COLUMN_BITMASK);

		_finderPathCountByContactRoleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContactRoleId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByCI_TI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ContactTeamRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCI_TI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByCI_TI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ContactTeamRoleImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCI_TI",
			new String[] {Long.class.getName(), Long.class.getName()},
			ContactTeamRoleModelImpl.CONTACTID_COLUMN_BITMASK |
			ContactTeamRoleModelImpl.TEAMID_COLUMN_BITMASK);

		_finderPathCountByCI_TI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCI_TI",
			new String[] {Long.class.getName(), Long.class.getName()});

		ContactTeamRoleUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		ContactTeamRoleUtil.setPersistence(null);

		entityCache.removeCache(ContactTeamRoleImpl.class.getName());

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
				"value.object.column.bitmask.enabled.com.liferay.osb.koroneiki.taproot.model.ContactTeamRole"),
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

	private static final String _SQL_SELECT_CONTACTTEAMROLE =
		"SELECT contactTeamRole FROM ContactTeamRole contactTeamRole";

	private static final String _SQL_SELECT_CONTACTTEAMROLE_WHERE =
		"SELECT contactTeamRole FROM ContactTeamRole contactTeamRole WHERE ";

	private static final String _SQL_COUNT_CONTACTTEAMROLE =
		"SELECT COUNT(contactTeamRole) FROM ContactTeamRole contactTeamRole";

	private static final String _SQL_COUNT_CONTACTTEAMROLE_WHERE =
		"SELECT COUNT(contactTeamRole) FROM ContactTeamRole contactTeamRole WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "contactTeamRole.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ContactTeamRole exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ContactTeamRole exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ContactTeamRolePersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"contactId", "teamId", "contactRoleId"});

}