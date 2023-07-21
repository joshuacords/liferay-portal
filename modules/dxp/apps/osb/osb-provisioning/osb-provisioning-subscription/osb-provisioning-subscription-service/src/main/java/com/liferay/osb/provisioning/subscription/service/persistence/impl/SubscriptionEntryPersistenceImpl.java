/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.subscription.service.persistence.impl;

import com.liferay.osb.provisioning.subscription.exception.NoSuchSubscriptionEntryException;
import com.liferay.osb.provisioning.subscription.model.SubscriptionEntry;
import com.liferay.osb.provisioning.subscription.model.impl.SubscriptionEntryImpl;
import com.liferay.osb.provisioning.subscription.model.impl.SubscriptionEntryModelImpl;
import com.liferay.osb.provisioning.subscription.service.persistence.SubscriptionEntryPersistence;
import com.liferay.osb.provisioning.subscription.service.persistence.SubscriptionEntryUtil;
import com.liferay.osb.provisioning.subscription.service.persistence.impl.constants.ProvisioningPersistenceConstants;
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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
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
 * The persistence implementation for the subscription entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = SubscriptionEntryPersistence.class)
public class SubscriptionEntryPersistenceImpl
	extends BasePersistenceImpl<SubscriptionEntry>
	implements SubscriptionEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SubscriptionEntryUtil</code> to access the subscription entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SubscriptionEntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByContactUuid;
	private FinderPath _finderPathWithoutPaginationFindByContactUuid;
	private FinderPath _finderPathCountByContactUuid;

	/**
	 * Returns all the subscription entries where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @return the matching subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findByContactUuid(String contactUuid) {
		return findByContactUuid(
			contactUuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the subscription entries where contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @return the range of matching subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findByContactUuid(
		String contactUuid, int start, int end) {

		return findByContactUuid(contactUuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the subscription entries where contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findByContactUuid(
		String contactUuid, int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		return findByContactUuid(
			contactUuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the subscription entries where contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findByContactUuid(
		String contactUuid, int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator,
		boolean useFinderCache) {

		contactUuid = Objects.toString(contactUuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByContactUuid;
				finderArgs = new Object[] {contactUuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByContactUuid;
			finderArgs = new Object[] {
				contactUuid, start, end, orderByComparator
			};
		}

		List<SubscriptionEntry> list = null;

		if (useFinderCache) {
			list = (List<SubscriptionEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SubscriptionEntry subscriptionEntry : list) {
					if (!contactUuid.equals(
							subscriptionEntry.getContactUuid())) {

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

			sb.append(_SQL_SELECT_SUBSCRIPTIONENTRY_WHERE);

			boolean bindContactUuid = false;

			if (contactUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_CONTACTUUID_CONTACTUUID_3);
			}
			else {
				bindContactUuid = true;

				sb.append(_FINDER_COLUMN_CONTACTUUID_CONTACTUUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindContactUuid) {
					queryPos.add(contactUuid);
				}

				list = (List<SubscriptionEntry>)QueryUtil.list(
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
	 * Returns the first subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry findByContactUuid_First(
			String contactUuid,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		SubscriptionEntry subscriptionEntry = fetchByContactUuid_First(
			contactUuid, orderByComparator);

		if (subscriptionEntry != null) {
			return subscriptionEntry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("contactUuid=");
		sb.append(contactUuid);

		sb.append("}");

		throw new NoSuchSubscriptionEntryException(sb.toString());
	}

	/**
	 * Returns the first subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry fetchByContactUuid_First(
		String contactUuid,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		List<SubscriptionEntry> list = findByContactUuid(
			contactUuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry findByContactUuid_Last(
			String contactUuid,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		SubscriptionEntry subscriptionEntry = fetchByContactUuid_Last(
			contactUuid, orderByComparator);

		if (subscriptionEntry != null) {
			return subscriptionEntry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("contactUuid=");
		sb.append(contactUuid);

		sb.append("}");

		throw new NoSuchSubscriptionEntryException(sb.toString());
	}

	/**
	 * Returns the last subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry fetchByContactUuid_Last(
		String contactUuid,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		int count = countByContactUuid(contactUuid);

		if (count == 0) {
			return null;
		}

		List<SubscriptionEntry> list = findByContactUuid(
			contactUuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the subscription entries before and after the current subscription entry in the ordered set where contactUuid = &#63;.
	 *
	 * @param subscriptionEntryId the primary key of the current subscription entry
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subscription entry
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	@Override
	public SubscriptionEntry[] findByContactUuid_PrevAndNext(
			long subscriptionEntryId, String contactUuid,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		contactUuid = Objects.toString(contactUuid, "");

		SubscriptionEntry subscriptionEntry = findByPrimaryKey(
			subscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			SubscriptionEntry[] array = new SubscriptionEntryImpl[3];

			array[0] = getByContactUuid_PrevAndNext(
				session, subscriptionEntry, contactUuid, orderByComparator,
				true);

			array[1] = subscriptionEntry;

			array[2] = getByContactUuid_PrevAndNext(
				session, subscriptionEntry, contactUuid, orderByComparator,
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

	protected SubscriptionEntry getByContactUuid_PrevAndNext(
		Session session, SubscriptionEntry subscriptionEntry,
		String contactUuid,
		OrderByComparator<SubscriptionEntry> orderByComparator,
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

		sb.append(_SQL_SELECT_SUBSCRIPTIONENTRY_WHERE);

		boolean bindContactUuid = false;

		if (contactUuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_CONTACTUUID_CONTACTUUID_3);
		}
		else {
			bindContactUuid = true;

			sb.append(_FINDER_COLUMN_CONTACTUUID_CONTACTUUID_2);
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
			sb.append(SubscriptionEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindContactUuid) {
			queryPos.add(contactUuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						subscriptionEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SubscriptionEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the subscription entries where contactUuid = &#63; from the database.
	 *
	 * @param contactUuid the contact uuid
	 */
	@Override
	public void removeByContactUuid(String contactUuid) {
		for (SubscriptionEntry subscriptionEntry :
				findByContactUuid(
					contactUuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(subscriptionEntry);
		}
	}

	/**
	 * Returns the number of subscription entries where contactUuid = &#63;.
	 *
	 * @param contactUuid the contact uuid
	 * @return the number of matching subscription entries
	 */
	@Override
	public int countByContactUuid(String contactUuid) {
		contactUuid = Objects.toString(contactUuid, "");

		FinderPath finderPath = _finderPathCountByContactUuid;

		Object[] finderArgs = new Object[] {contactUuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SUBSCRIPTIONENTRY_WHERE);

			boolean bindContactUuid = false;

			if (contactUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_CONTACTUUID_CONTACTUUID_3);
			}
			else {
				bindContactUuid = true;

				sb.append(_FINDER_COLUMN_CONTACTUUID_CONTACTUUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindContactUuid) {
					queryPos.add(contactUuid);
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

	private static final String _FINDER_COLUMN_CONTACTUUID_CONTACTUUID_2 =
		"subscriptionEntry.contactUuid = ?";

	private static final String _FINDER_COLUMN_CONTACTUUID_CONTACTUUID_3 =
		"(subscriptionEntry.contactUuid IS NULL OR subscriptionEntry.contactUuid = '')";

	private FinderPath _finderPathWithPaginationFindByC_C;
	private FinderPath _finderPathWithoutPaginationFindByC_C;
	private FinderPath _finderPathCountByC_C;

	/**
	 * Returns all the subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the matching subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findByC_C(long classNameId, long classPK) {
		return findByC_C(
			classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @return the range of matching subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findByC_C(
		long classNameId, long classPK, int start, int end) {

		return findByC_C(classNameId, classPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		return findByC_C(
			classNameId, classPK, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findByC_C(
		long classNameId, long classPK, int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator,
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

		List<SubscriptionEntry> list = null;

		if (useFinderCache) {
			list = (List<SubscriptionEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SubscriptionEntry subscriptionEntry : list) {
					if ((classNameId != subscriptionEntry.getClassNameId()) ||
						(classPK != subscriptionEntry.getClassPK())) {

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

			sb.append(_SQL_SELECT_SUBSCRIPTIONENTRY_WHERE);

			sb.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				queryPos.add(classPK);

				list = (List<SubscriptionEntry>)QueryUtil.list(
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
	 * Returns the first subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry findByC_C_First(
			long classNameId, long classPK,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		SubscriptionEntry subscriptionEntry = fetchByC_C_First(
			classNameId, classPK, orderByComparator);

		if (subscriptionEntry != null) {
			return subscriptionEntry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchSubscriptionEntryException(sb.toString());
	}

	/**
	 * Returns the first subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry fetchByC_C_First(
		long classNameId, long classPK,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		List<SubscriptionEntry> list = findByC_C(
			classNameId, classPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry findByC_C_Last(
			long classNameId, long classPK,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		SubscriptionEntry subscriptionEntry = fetchByC_C_Last(
			classNameId, classPK, orderByComparator);

		if (subscriptionEntry != null) {
			return subscriptionEntry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", classPK=");
		sb.append(classPK);

		sb.append("}");

		throw new NoSuchSubscriptionEntryException(sb.toString());
	}

	/**
	 * Returns the last subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry fetchByC_C_Last(
		long classNameId, long classPK,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		int count = countByC_C(classNameId, classPK);

		if (count == 0) {
			return null;
		}

		List<SubscriptionEntry> list = findByC_C(
			classNameId, classPK, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the subscription entries before and after the current subscription entry in the ordered set where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param subscriptionEntryId the primary key of the current subscription entry
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subscription entry
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	@Override
	public SubscriptionEntry[] findByC_C_PrevAndNext(
			long subscriptionEntryId, long classNameId, long classPK,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		SubscriptionEntry subscriptionEntry = findByPrimaryKey(
			subscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			SubscriptionEntry[] array = new SubscriptionEntryImpl[3];

			array[0] = getByC_C_PrevAndNext(
				session, subscriptionEntry, classNameId, classPK,
				orderByComparator, true);

			array[1] = subscriptionEntry;

			array[2] = getByC_C_PrevAndNext(
				session, subscriptionEntry, classNameId, classPK,
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

	protected SubscriptionEntry getByC_C_PrevAndNext(
		Session session, SubscriptionEntry subscriptionEntry, long classNameId,
		long classPK, OrderByComparator<SubscriptionEntry> orderByComparator,
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

		sb.append(_SQL_SELECT_SUBSCRIPTIONENTRY_WHERE);

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
			sb.append(SubscriptionEntryModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(
						subscriptionEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SubscriptionEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the subscription entries where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 */
	@Override
	public void removeByC_C(long classNameId, long classPK) {
		for (SubscriptionEntry subscriptionEntry :
				findByC_C(
					classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(subscriptionEntry);
		}
	}

	/**
	 * Returns the number of subscription entries where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @return the number of matching subscription entries
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = _finderPathCountByC_C;

		Object[] finderArgs = new Object[] {classNameId, classPK};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SUBSCRIPTIONENTRY_WHERE);

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
		"subscriptionEntry.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 =
		"subscriptionEntry.classPK = ?";

	private FinderPath _finderPathWithPaginationFindByC_CU;
	private FinderPath _finderPathWithoutPaginationFindByC_CU;
	private FinderPath _finderPathCountByC_CU;

	/**
	 * Returns all the subscription entries where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @return the matching subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findByC_CU(
		long classNameId, String contactUuid) {

		return findByC_CU(
			classNameId, contactUuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the subscription entries where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @return the range of matching subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findByC_CU(
		long classNameId, String contactUuid, int start, int end) {

		return findByC_CU(classNameId, contactUuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the subscription entries where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findByC_CU(
		long classNameId, String contactUuid, int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		return findByC_CU(
			classNameId, contactUuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the subscription entries where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findByC_CU(
		long classNameId, String contactUuid, int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator,
		boolean useFinderCache) {

		contactUuid = Objects.toString(contactUuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByC_CU;
				finderArgs = new Object[] {classNameId, contactUuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByC_CU;
			finderArgs = new Object[] {
				classNameId, contactUuid, start, end, orderByComparator
			};
		}

		List<SubscriptionEntry> list = null;

		if (useFinderCache) {
			list = (List<SubscriptionEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SubscriptionEntry subscriptionEntry : list) {
					if ((classNameId != subscriptionEntry.getClassNameId()) ||
						!contactUuid.equals(
							subscriptionEntry.getContactUuid())) {

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

			sb.append(_SQL_SELECT_SUBSCRIPTIONENTRY_WHERE);

			sb.append(_FINDER_COLUMN_C_CU_CLASSNAMEID_2);

			boolean bindContactUuid = false;

			if (contactUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_CU_CONTACTUUID_3);
			}
			else {
				bindContactUuid = true;

				sb.append(_FINDER_COLUMN_C_CU_CONTACTUUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(SubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				if (bindContactUuid) {
					queryPos.add(contactUuid);
				}

				list = (List<SubscriptionEntry>)QueryUtil.list(
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
	 * Returns the first subscription entry in the ordered set where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry findByC_CU_First(
			long classNameId, String contactUuid,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		SubscriptionEntry subscriptionEntry = fetchByC_CU_First(
			classNameId, contactUuid, orderByComparator);

		if (subscriptionEntry != null) {
			return subscriptionEntry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", contactUuid=");
		sb.append(contactUuid);

		sb.append("}");

		throw new NoSuchSubscriptionEntryException(sb.toString());
	}

	/**
	 * Returns the first subscription entry in the ordered set where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry fetchByC_CU_First(
		long classNameId, String contactUuid,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		List<SubscriptionEntry> list = findByC_CU(
			classNameId, contactUuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last subscription entry in the ordered set where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry findByC_CU_Last(
			long classNameId, String contactUuid,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		SubscriptionEntry subscriptionEntry = fetchByC_CU_Last(
			classNameId, contactUuid, orderByComparator);

		if (subscriptionEntry != null) {
			return subscriptionEntry;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("classNameId=");
		sb.append(classNameId);

		sb.append(", contactUuid=");
		sb.append(contactUuid);

		sb.append("}");

		throw new NoSuchSubscriptionEntryException(sb.toString());
	}

	/**
	 * Returns the last subscription entry in the ordered set where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry fetchByC_CU_Last(
		long classNameId, String contactUuid,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		int count = countByC_CU(classNameId, contactUuid);

		if (count == 0) {
			return null;
		}

		List<SubscriptionEntry> list = findByC_CU(
			classNameId, contactUuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the subscription entries before and after the current subscription entry in the ordered set where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * @param subscriptionEntryId the primary key of the current subscription entry
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next subscription entry
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	@Override
	public SubscriptionEntry[] findByC_CU_PrevAndNext(
			long subscriptionEntryId, long classNameId, String contactUuid,
			OrderByComparator<SubscriptionEntry> orderByComparator)
		throws NoSuchSubscriptionEntryException {

		contactUuid = Objects.toString(contactUuid, "");

		SubscriptionEntry subscriptionEntry = findByPrimaryKey(
			subscriptionEntryId);

		Session session = null;

		try {
			session = openSession();

			SubscriptionEntry[] array = new SubscriptionEntryImpl[3];

			array[0] = getByC_CU_PrevAndNext(
				session, subscriptionEntry, classNameId, contactUuid,
				orderByComparator, true);

			array[1] = subscriptionEntry;

			array[2] = getByC_CU_PrevAndNext(
				session, subscriptionEntry, classNameId, contactUuid,
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

	protected SubscriptionEntry getByC_CU_PrevAndNext(
		Session session, SubscriptionEntry subscriptionEntry, long classNameId,
		String contactUuid,
		OrderByComparator<SubscriptionEntry> orderByComparator,
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

		sb.append(_SQL_SELECT_SUBSCRIPTIONENTRY_WHERE);

		sb.append(_FINDER_COLUMN_C_CU_CLASSNAMEID_2);

		boolean bindContactUuid = false;

		if (contactUuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_C_CU_CONTACTUUID_3);
		}
		else {
			bindContactUuid = true;

			sb.append(_FINDER_COLUMN_C_CU_CONTACTUUID_2);
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
			sb.append(SubscriptionEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(classNameId);

		if (bindContactUuid) {
			queryPos.add(contactUuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						subscriptionEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<SubscriptionEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the subscription entries where classNameId = &#63; and contactUuid = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 */
	@Override
	public void removeByC_CU(long classNameId, String contactUuid) {
		for (SubscriptionEntry subscriptionEntry :
				findByC_CU(
					classNameId, contactUuid, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(subscriptionEntry);
		}
	}

	/**
	 * Returns the number of subscription entries where classNameId = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param contactUuid the contact uuid
	 * @return the number of matching subscription entries
	 */
	@Override
	public int countByC_CU(long classNameId, String contactUuid) {
		contactUuid = Objects.toString(contactUuid, "");

		FinderPath finderPath = _finderPathCountByC_CU;

		Object[] finderArgs = new Object[] {classNameId, contactUuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SUBSCRIPTIONENTRY_WHERE);

			sb.append(_FINDER_COLUMN_C_CU_CLASSNAMEID_2);

			boolean bindContactUuid = false;

			if (contactUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_CU_CONTACTUUID_3);
			}
			else {
				bindContactUuid = true;

				sb.append(_FINDER_COLUMN_C_CU_CONTACTUUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				if (bindContactUuid) {
					queryPos.add(contactUuid);
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

	private static final String _FINDER_COLUMN_C_CU_CLASSNAMEID_2 =
		"subscriptionEntry.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_CU_CONTACTUUID_2 =
		"subscriptionEntry.contactUuid = ?";

	private static final String _FINDER_COLUMN_C_CU_CONTACTUUID_3 =
		"(subscriptionEntry.contactUuid IS NULL OR subscriptionEntry.contactUuid = '')";

	private FinderPath _finderPathFetchByC_C_CU;
	private FinderPath _finderPathCountByC_C_CU;

	/**
	 * Returns the subscription entry where classNameId = &#63; and classPK = &#63; and contactUuid = &#63; or throws a <code>NoSuchSubscriptionEntryException</code> if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @return the matching subscription entry
	 * @throws NoSuchSubscriptionEntryException if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry findByC_C_CU(
			long classNameId, long classPK, String contactUuid)
		throws NoSuchSubscriptionEntryException {

		SubscriptionEntry subscriptionEntry = fetchByC_C_CU(
			classNameId, classPK, contactUuid);

		if (subscriptionEntry == null) {
			StringBundler sb = new StringBundler(8);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("classNameId=");
			sb.append(classNameId);

			sb.append(", classPK=");
			sb.append(classPK);

			sb.append(", contactUuid=");
			sb.append(contactUuid);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchSubscriptionEntryException(sb.toString());
		}

		return subscriptionEntry;
	}

	/**
	 * Returns the subscription entry where classNameId = &#63; and classPK = &#63; and contactUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @return the matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry fetchByC_C_CU(
		long classNameId, long classPK, String contactUuid) {

		return fetchByC_C_CU(classNameId, classPK, contactUuid, true);
	}

	/**
	 * Returns the subscription entry where classNameId = &#63; and classPK = &#63; and contactUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching subscription entry, or <code>null</code> if a matching subscription entry could not be found
	 */
	@Override
	public SubscriptionEntry fetchByC_C_CU(
		long classNameId, long classPK, String contactUuid,
		boolean useFinderCache) {

		contactUuid = Objects.toString(contactUuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {classNameId, classPK, contactUuid};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByC_C_CU, finderArgs, this);
		}

		if (result instanceof SubscriptionEntry) {
			SubscriptionEntry subscriptionEntry = (SubscriptionEntry)result;

			if ((classNameId != subscriptionEntry.getClassNameId()) ||
				(classPK != subscriptionEntry.getClassPK()) ||
				!Objects.equals(
					contactUuid, subscriptionEntry.getContactUuid())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_SELECT_SUBSCRIPTIONENTRY_WHERE);

			sb.append(_FINDER_COLUMN_C_C_CU_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_C_CU_CLASSPK_2);

			boolean bindContactUuid = false;

			if (contactUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_C_CU_CONTACTUUID_3);
			}
			else {
				bindContactUuid = true;

				sb.append(_FINDER_COLUMN_C_C_CU_CONTACTUUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				queryPos.add(classPK);

				if (bindContactUuid) {
					queryPos.add(contactUuid);
				}

				List<SubscriptionEntry> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_C_CU, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									classNameId, classPK, contactUuid
								};
							}

							_log.warn(
								"SubscriptionEntryPersistenceImpl.fetchByC_C_CU(long, long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					SubscriptionEntry subscriptionEntry = list.get(0);

					result = subscriptionEntry;

					cacheResult(subscriptionEntry);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByC_C_CU, finderArgs);
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
			return (SubscriptionEntry)result;
		}
	}

	/**
	 * Removes the subscription entry where classNameId = &#63; and classPK = &#63; and contactUuid = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @return the subscription entry that was removed
	 */
	@Override
	public SubscriptionEntry removeByC_C_CU(
			long classNameId, long classPK, String contactUuid)
		throws NoSuchSubscriptionEntryException {

		SubscriptionEntry subscriptionEntry = findByC_C_CU(
			classNameId, classPK, contactUuid);

		return remove(subscriptionEntry);
	}

	/**
	 * Returns the number of subscription entries where classNameId = &#63; and classPK = &#63; and contactUuid = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param contactUuid the contact uuid
	 * @return the number of matching subscription entries
	 */
	@Override
	public int countByC_C_CU(
		long classNameId, long classPK, String contactUuid) {

		contactUuid = Objects.toString(contactUuid, "");

		FinderPath finderPath = _finderPathCountByC_C_CU;

		Object[] finderArgs = new Object[] {classNameId, classPK, contactUuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_SUBSCRIPTIONENTRY_WHERE);

			sb.append(_FINDER_COLUMN_C_C_CU_CLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_C_C_CU_CLASSPK_2);

			boolean bindContactUuid = false;

			if (contactUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_C_CU_CONTACTUUID_3);
			}
			else {
				bindContactUuid = true;

				sb.append(_FINDER_COLUMN_C_C_CU_CONTACTUUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(classNameId);

				queryPos.add(classPK);

				if (bindContactUuid) {
					queryPos.add(contactUuid);
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

	private static final String _FINDER_COLUMN_C_C_CU_CLASSNAMEID_2 =
		"subscriptionEntry.classNameId = ? AND ";

	private static final String _FINDER_COLUMN_C_C_CU_CLASSPK_2 =
		"subscriptionEntry.classPK = ? AND ";

	private static final String _FINDER_COLUMN_C_C_CU_CONTACTUUID_2 =
		"subscriptionEntry.contactUuid = ?";

	private static final String _FINDER_COLUMN_C_C_CU_CONTACTUUID_3 =
		"(subscriptionEntry.contactUuid IS NULL OR subscriptionEntry.contactUuid = '')";

	public SubscriptionEntryPersistenceImpl() {
		setModelClass(SubscriptionEntry.class);

		setModelImplClass(SubscriptionEntryImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the subscription entry in the entity cache if it is enabled.
	 *
	 * @param subscriptionEntry the subscription entry
	 */
	@Override
	public void cacheResult(SubscriptionEntry subscriptionEntry) {
		entityCache.putResult(
			entityCacheEnabled, SubscriptionEntryImpl.class,
			subscriptionEntry.getPrimaryKey(), subscriptionEntry);

		finderCache.putResult(
			_finderPathFetchByC_C_CU,
			new Object[] {
				subscriptionEntry.getClassNameId(),
				subscriptionEntry.getClassPK(),
				subscriptionEntry.getContactUuid()
			},
			subscriptionEntry);

		subscriptionEntry.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the subscription entries in the entity cache if it is enabled.
	 *
	 * @param subscriptionEntries the subscription entries
	 */
	@Override
	public void cacheResult(List<SubscriptionEntry> subscriptionEntries) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (subscriptionEntries.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (SubscriptionEntry subscriptionEntry : subscriptionEntries) {
			if (entityCache.getResult(
					entityCacheEnabled, SubscriptionEntryImpl.class,
					subscriptionEntry.getPrimaryKey()) == null) {

				cacheResult(subscriptionEntry);
			}
			else {
				subscriptionEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all subscription entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SubscriptionEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the subscription entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SubscriptionEntry subscriptionEntry) {
		entityCache.removeResult(
			entityCacheEnabled, SubscriptionEntryImpl.class,
			subscriptionEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(SubscriptionEntryModelImpl)subscriptionEntry, true);
	}

	@Override
	public void clearCache(List<SubscriptionEntry> subscriptionEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SubscriptionEntry subscriptionEntry : subscriptionEntries) {
			entityCache.removeResult(
				entityCacheEnabled, SubscriptionEntryImpl.class,
				subscriptionEntry.getPrimaryKey());

			clearUniqueFindersCache(
				(SubscriptionEntryModelImpl)subscriptionEntry, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, SubscriptionEntryImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		SubscriptionEntryModelImpl subscriptionEntryModelImpl) {

		Object[] args = new Object[] {
			subscriptionEntryModelImpl.getClassNameId(),
			subscriptionEntryModelImpl.getClassPK(),
			subscriptionEntryModelImpl.getContactUuid()
		};

		finderCache.putResult(
			_finderPathCountByC_C_CU, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByC_C_CU, args, subscriptionEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SubscriptionEntryModelImpl subscriptionEntryModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				subscriptionEntryModelImpl.getClassNameId(),
				subscriptionEntryModelImpl.getClassPK(),
				subscriptionEntryModelImpl.getContactUuid()
			};

			finderCache.removeResult(_finderPathCountByC_C_CU, args);
			finderCache.removeResult(_finderPathFetchByC_C_CU, args);
		}

		if ((subscriptionEntryModelImpl.getColumnBitmask() &
			 _finderPathFetchByC_C_CU.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				subscriptionEntryModelImpl.getOriginalClassNameId(),
				subscriptionEntryModelImpl.getOriginalClassPK(),
				subscriptionEntryModelImpl.getOriginalContactUuid()
			};

			finderCache.removeResult(_finderPathCountByC_C_CU, args);
			finderCache.removeResult(_finderPathFetchByC_C_CU, args);
		}
	}

	/**
	 * Creates a new subscription entry with the primary key. Does not add the subscription entry to the database.
	 *
	 * @param subscriptionEntryId the primary key for the new subscription entry
	 * @return the new subscription entry
	 */
	@Override
	public SubscriptionEntry create(long subscriptionEntryId) {
		SubscriptionEntry subscriptionEntry = new SubscriptionEntryImpl();

		subscriptionEntry.setNew(true);
		subscriptionEntry.setPrimaryKey(subscriptionEntryId);

		return subscriptionEntry;
	}

	/**
	 * Removes the subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param subscriptionEntryId the primary key of the subscription entry
	 * @return the subscription entry that was removed
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	@Override
	public SubscriptionEntry remove(long subscriptionEntryId)
		throws NoSuchSubscriptionEntryException {

		return remove((Serializable)subscriptionEntryId);
	}

	/**
	 * Removes the subscription entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the subscription entry
	 * @return the subscription entry that was removed
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	@Override
	public SubscriptionEntry remove(Serializable primaryKey)
		throws NoSuchSubscriptionEntryException {

		Session session = null;

		try {
			session = openSession();

			SubscriptionEntry subscriptionEntry =
				(SubscriptionEntry)session.get(
					SubscriptionEntryImpl.class, primaryKey);

			if (subscriptionEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSubscriptionEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(subscriptionEntry);
		}
		catch (NoSuchSubscriptionEntryException noSuchEntityException) {
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
	protected SubscriptionEntry removeImpl(
		SubscriptionEntry subscriptionEntry) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(subscriptionEntry)) {
				subscriptionEntry = (SubscriptionEntry)session.get(
					SubscriptionEntryImpl.class,
					subscriptionEntry.getPrimaryKeyObj());
			}

			if (subscriptionEntry != null) {
				session.delete(subscriptionEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (subscriptionEntry != null) {
			clearCache(subscriptionEntry);
		}

		return subscriptionEntry;
	}

	@Override
	public SubscriptionEntry updateImpl(SubscriptionEntry subscriptionEntry) {
		boolean isNew = subscriptionEntry.isNew();

		if (!(subscriptionEntry instanceof SubscriptionEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(subscriptionEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					subscriptionEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in subscriptionEntry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom SubscriptionEntry implementation " +
					subscriptionEntry.getClass());
		}

		SubscriptionEntryModelImpl subscriptionEntryModelImpl =
			(SubscriptionEntryModelImpl)subscriptionEntry;

		if (isNew && (subscriptionEntry.getCreateDate() == null)) {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			Date date = new Date();

			if (serviceContext == null) {
				subscriptionEntry.setCreateDate(date);
			}
			else {
				subscriptionEntry.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(subscriptionEntry);

				subscriptionEntry.setNew(false);
			}
			else {
				subscriptionEntry = (SubscriptionEntry)session.merge(
					subscriptionEntry);
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
				subscriptionEntryModelImpl.getContactUuid()
			};

			finderCache.removeResult(_finderPathCountByContactUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByContactUuid, args);

			args = new Object[] {
				subscriptionEntryModelImpl.getClassNameId(),
				subscriptionEntryModelImpl.getClassPK()
			};

			finderCache.removeResult(_finderPathCountByC_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_C, args);

			args = new Object[] {
				subscriptionEntryModelImpl.getClassNameId(),
				subscriptionEntryModelImpl.getContactUuid()
			};

			finderCache.removeResult(_finderPathCountByC_CU, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByC_CU, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((subscriptionEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByContactUuid.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					subscriptionEntryModelImpl.getOriginalContactUuid()
				};

				finderCache.removeResult(_finderPathCountByContactUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByContactUuid, args);

				args = new Object[] {
					subscriptionEntryModelImpl.getContactUuid()
				};

				finderCache.removeResult(_finderPathCountByContactUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByContactUuid, args);
			}

			if ((subscriptionEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					subscriptionEntryModelImpl.getOriginalClassNameId(),
					subscriptionEntryModelImpl.getOriginalClassPK()
				};

				finderCache.removeResult(_finderPathCountByC_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_C, args);

				args = new Object[] {
					subscriptionEntryModelImpl.getClassNameId(),
					subscriptionEntryModelImpl.getClassPK()
				};

				finderCache.removeResult(_finderPathCountByC_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_C, args);
			}

			if ((subscriptionEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByC_CU.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					subscriptionEntryModelImpl.getOriginalClassNameId(),
					subscriptionEntryModelImpl.getOriginalContactUuid()
				};

				finderCache.removeResult(_finderPathCountByC_CU, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_CU, args);

				args = new Object[] {
					subscriptionEntryModelImpl.getClassNameId(),
					subscriptionEntryModelImpl.getContactUuid()
				};

				finderCache.removeResult(_finderPathCountByC_CU, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByC_CU, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, SubscriptionEntryImpl.class,
			subscriptionEntry.getPrimaryKey(), subscriptionEntry, false);

		clearUniqueFindersCache(subscriptionEntryModelImpl, false);
		cacheUniqueFindersCache(subscriptionEntryModelImpl);

		subscriptionEntry.resetOriginalValues();

		return subscriptionEntry;
	}

	/**
	 * Returns the subscription entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the subscription entry
	 * @return the subscription entry
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	@Override
	public SubscriptionEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSubscriptionEntryException {

		SubscriptionEntry subscriptionEntry = fetchByPrimaryKey(primaryKey);

		if (subscriptionEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSubscriptionEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return subscriptionEntry;
	}

	/**
	 * Returns the subscription entry with the primary key or throws a <code>NoSuchSubscriptionEntryException</code> if it could not be found.
	 *
	 * @param subscriptionEntryId the primary key of the subscription entry
	 * @return the subscription entry
	 * @throws NoSuchSubscriptionEntryException if a subscription entry with the primary key could not be found
	 */
	@Override
	public SubscriptionEntry findByPrimaryKey(long subscriptionEntryId)
		throws NoSuchSubscriptionEntryException {

		return findByPrimaryKey((Serializable)subscriptionEntryId);
	}

	/**
	 * Returns the subscription entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param subscriptionEntryId the primary key of the subscription entry
	 * @return the subscription entry, or <code>null</code> if a subscription entry with the primary key could not be found
	 */
	@Override
	public SubscriptionEntry fetchByPrimaryKey(long subscriptionEntryId) {
		return fetchByPrimaryKey((Serializable)subscriptionEntryId);
	}

	/**
	 * Returns all the subscription entries.
	 *
	 * @return the subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @return the range of subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findAll(
		int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the subscription entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SubscriptionEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of subscription entries
	 * @param end the upper bound of the range of subscription entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of subscription entries
	 */
	@Override
	public List<SubscriptionEntry> findAll(
		int start, int end,
		OrderByComparator<SubscriptionEntry> orderByComparator,
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

		List<SubscriptionEntry> list = null;

		if (useFinderCache) {
			list = (List<SubscriptionEntry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SUBSCRIPTIONENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SUBSCRIPTIONENTRY;

				sql = sql.concat(SubscriptionEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<SubscriptionEntry>)QueryUtil.list(
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
	 * Removes all the subscription entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SubscriptionEntry subscriptionEntry : findAll()) {
			remove(subscriptionEntry);
		}
	}

	/**
	 * Returns the number of subscription entries.
	 *
	 * @return the number of subscription entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SUBSCRIPTIONENTRY);

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
		return "subscriptionEntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SUBSCRIPTIONENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SubscriptionEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the subscription entry persistence.
	 */
	@Activate
	public void activate() {
		SubscriptionEntryModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		SubscriptionEntryModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, SubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, SubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByContactUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, SubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByContactUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByContactUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, SubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByContactUuid",
			new String[] {String.class.getName()},
			SubscriptionEntryModelImpl.CONTACTUUID_COLUMN_BITMASK);

		_finderPathCountByContactUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContactUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByC_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, SubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_C",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, SubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_C",
			new String[] {Long.class.getName(), Long.class.getName()},
			SubscriptionEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SubscriptionEntryModelImpl.CLASSPK_COLUMN_BITMASK);

		_finderPathCountByC_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByC_CU = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, SubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_CU",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByC_CU = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, SubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_CU",
			new String[] {Long.class.getName(), String.class.getName()},
			SubscriptionEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SubscriptionEntryModelImpl.CONTACTUUID_COLUMN_BITMASK);

		_finderPathCountByC_CU = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_CU",
			new String[] {Long.class.getName(), String.class.getName()});

		_finderPathFetchByC_C_CU = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, SubscriptionEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_C_CU",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			SubscriptionEntryModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			SubscriptionEntryModelImpl.CLASSPK_COLUMN_BITMASK |
			SubscriptionEntryModelImpl.CONTACTUUID_COLUMN_BITMASK);

		_finderPathCountByC_C_CU = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C_CU",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

		SubscriptionEntryUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		SubscriptionEntryUtil.setPersistence(null);

		entityCache.removeCache(SubscriptionEntryImpl.class.getName());

		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = ProvisioningPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.osb.provisioning.subscription.model.SubscriptionEntry"),
			true);
	}

	@Override
	@Reference(
		target = ProvisioningPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ProvisioningPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
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

	private static final String _SQL_SELECT_SUBSCRIPTIONENTRY =
		"SELECT subscriptionEntry FROM SubscriptionEntry subscriptionEntry";

	private static final String _SQL_SELECT_SUBSCRIPTIONENTRY_WHERE =
		"SELECT subscriptionEntry FROM SubscriptionEntry subscriptionEntry WHERE ";

	private static final String _SQL_COUNT_SUBSCRIPTIONENTRY =
		"SELECT COUNT(subscriptionEntry) FROM SubscriptionEntry subscriptionEntry";

	private static final String _SQL_COUNT_SUBSCRIPTIONENTRY_WHERE =
		"SELECT COUNT(subscriptionEntry) FROM SubscriptionEntry subscriptionEntry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "subscriptionEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No SubscriptionEntry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No SubscriptionEntry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		SubscriptionEntryPersistenceImpl.class);

}