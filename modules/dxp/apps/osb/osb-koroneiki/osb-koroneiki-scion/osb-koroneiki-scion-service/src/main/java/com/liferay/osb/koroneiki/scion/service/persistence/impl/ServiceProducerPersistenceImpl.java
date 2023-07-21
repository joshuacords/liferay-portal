/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.service.persistence.impl;

import com.liferay.osb.koroneiki.scion.exception.NoSuchServiceProducerException;
import com.liferay.osb.koroneiki.scion.model.ServiceProducer;
import com.liferay.osb.koroneiki.scion.model.impl.ServiceProducerImpl;
import com.liferay.osb.koroneiki.scion.model.impl.ServiceProducerModelImpl;
import com.liferay.osb.koroneiki.scion.service.persistence.ServiceProducerPersistence;
import com.liferay.osb.koroneiki.scion.service.persistence.ServiceProducerUtil;
import com.liferay.osb.koroneiki.scion.service.persistence.impl.constants.KoroneikiPersistenceConstants;
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
 * The persistence implementation for the service producer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ServiceProducerPersistence.class)
public class ServiceProducerPersistenceImpl
	extends BasePersistenceImpl<ServiceProducer>
	implements ServiceProducerPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ServiceProducerUtil</code> to access the service producer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ServiceProducerImpl.class.getName();

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
	 * Returns all the service producers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching service producers
	 */
	@Override
	public List<ServiceProducer> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service producers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @return the range of matching service producers
	 */
	@Override
	public List<ServiceProducer> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service producers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service producers
	 */
	@Override
	public List<ServiceProducer> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ServiceProducer> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service producers where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching service producers
	 */
	@Override
	public List<ServiceProducer> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ServiceProducer> orderByComparator,
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

		List<ServiceProducer> list = null;

		if (useFinderCache) {
			list = (List<ServiceProducer>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceProducer serviceProducer : list) {
					if (!uuid.equals(serviceProducer.getUuid())) {
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

			sb.append(_SQL_SELECT_SERVICEPRODUCER_WHERE);

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
				sb.append(ServiceProducerModelImpl.ORDER_BY_JPQL);
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

				list = (List<ServiceProducer>)QueryUtil.list(
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
	 * Returns the first service producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	@Override
	public ServiceProducer findByUuid_First(
			String uuid, OrderByComparator<ServiceProducer> orderByComparator)
		throws NoSuchServiceProducerException {

		ServiceProducer serviceProducer = fetchByUuid_First(
			uuid, orderByComparator);

		if (serviceProducer != null) {
			return serviceProducer;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchServiceProducerException(sb.toString());
	}

	/**
	 * Returns the first service producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	@Override
	public ServiceProducer fetchByUuid_First(
		String uuid, OrderByComparator<ServiceProducer> orderByComparator) {

		List<ServiceProducer> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	@Override
	public ServiceProducer findByUuid_Last(
			String uuid, OrderByComparator<ServiceProducer> orderByComparator)
		throws NoSuchServiceProducerException {

		ServiceProducer serviceProducer = fetchByUuid_Last(
			uuid, orderByComparator);

		if (serviceProducer != null) {
			return serviceProducer;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchServiceProducerException(sb.toString());
	}

	/**
	 * Returns the last service producer in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	@Override
	public ServiceProducer fetchByUuid_Last(
		String uuid, OrderByComparator<ServiceProducer> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ServiceProducer> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service producers before and after the current service producer in the ordered set where uuid = &#63;.
	 *
	 * @param serviceProducerId the primary key of the current service producer
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service producer
	 * @throws NoSuchServiceProducerException if a service producer with the primary key could not be found
	 */
	@Override
	public ServiceProducer[] findByUuid_PrevAndNext(
			long serviceProducerId, String uuid,
			OrderByComparator<ServiceProducer> orderByComparator)
		throws NoSuchServiceProducerException {

		uuid = Objects.toString(uuid, "");

		ServiceProducer serviceProducer = findByPrimaryKey(serviceProducerId);

		Session session = null;

		try {
			session = openSession();

			ServiceProducer[] array = new ServiceProducerImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, serviceProducer, uuid, orderByComparator, true);

			array[1] = serviceProducer;

			array[2] = getByUuid_PrevAndNext(
				session, serviceProducer, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ServiceProducer getByUuid_PrevAndNext(
		Session session, ServiceProducer serviceProducer, String uuid,
		OrderByComparator<ServiceProducer> orderByComparator,
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

		sb.append(_SQL_SELECT_SERVICEPRODUCER_WHERE);

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
			sb.append(ServiceProducerModelImpl.ORDER_BY_JPQL);
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
						serviceProducer)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ServiceProducer> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service producers where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ServiceProducer serviceProducer :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(serviceProducer);
		}
	}

	/**
	 * Returns the number of service producers where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching service producers
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SERVICEPRODUCER_WHERE);

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
		"serviceProducer.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(serviceProducer.uuid IS NULL OR serviceProducer.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the service producers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching service producers
	 */
	@Override
	public List<ServiceProducer> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service producers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @return the range of matching service producers
	 */
	@Override
	public List<ServiceProducer> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the service producers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service producers
	 */
	@Override
	public List<ServiceProducer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ServiceProducer> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service producers where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching service producers
	 */
	@Override
	public List<ServiceProducer> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ServiceProducer> orderByComparator,
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

		List<ServiceProducer> list = null;

		if (useFinderCache) {
			list = (List<ServiceProducer>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ServiceProducer serviceProducer : list) {
					if (!uuid.equals(serviceProducer.getUuid()) ||
						(companyId != serviceProducer.getCompanyId())) {

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

			sb.append(_SQL_SELECT_SERVICEPRODUCER_WHERE);

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
				sb.append(ServiceProducerModelImpl.ORDER_BY_JPQL);
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

				list = (List<ServiceProducer>)QueryUtil.list(
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
	 * Returns the first service producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	@Override
	public ServiceProducer findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<ServiceProducer> orderByComparator)
		throws NoSuchServiceProducerException {

		ServiceProducer serviceProducer = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (serviceProducer != null) {
			return serviceProducer;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchServiceProducerException(sb.toString());
	}

	/**
	 * Returns the first service producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	@Override
	public ServiceProducer fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<ServiceProducer> orderByComparator) {

		List<ServiceProducer> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last service producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	@Override
	public ServiceProducer findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<ServiceProducer> orderByComparator)
		throws NoSuchServiceProducerException {

		ServiceProducer serviceProducer = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (serviceProducer != null) {
			return serviceProducer;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchServiceProducerException(sb.toString());
	}

	/**
	 * Returns the last service producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	@Override
	public ServiceProducer fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<ServiceProducer> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ServiceProducer> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the service producers before and after the current service producer in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param serviceProducerId the primary key of the current service producer
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service producer
	 * @throws NoSuchServiceProducerException if a service producer with the primary key could not be found
	 */
	@Override
	public ServiceProducer[] findByUuid_C_PrevAndNext(
			long serviceProducerId, String uuid, long companyId,
			OrderByComparator<ServiceProducer> orderByComparator)
		throws NoSuchServiceProducerException {

		uuid = Objects.toString(uuid, "");

		ServiceProducer serviceProducer = findByPrimaryKey(serviceProducerId);

		Session session = null;

		try {
			session = openSession();

			ServiceProducer[] array = new ServiceProducerImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, serviceProducer, uuid, companyId, orderByComparator,
				true);

			array[1] = serviceProducer;

			array[2] = getByUuid_C_PrevAndNext(
				session, serviceProducer, uuid, companyId, orderByComparator,
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

	protected ServiceProducer getByUuid_C_PrevAndNext(
		Session session, ServiceProducer serviceProducer, String uuid,
		long companyId, OrderByComparator<ServiceProducer> orderByComparator,
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

		sb.append(_SQL_SELECT_SERVICEPRODUCER_WHERE);

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
			sb.append(ServiceProducerModelImpl.ORDER_BY_JPQL);
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
						serviceProducer)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ServiceProducer> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the service producers where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ServiceProducer serviceProducer :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(serviceProducer);
		}
	}

	/**
	 * Returns the number of service producers where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching service producers
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_SERVICEPRODUCER_WHERE);

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
		"serviceProducer.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(serviceProducer.uuid IS NULL OR serviceProducer.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"serviceProducer.companyId = ?";

	private FinderPath _finderPathFetchByAuthorizationUserId;
	private FinderPath _finderPathCountByAuthorizationUserId;

	/**
	 * Returns the service producer where authorizationUserId = &#63; or throws a <code>NoSuchServiceProducerException</code> if it could not be found.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @return the matching service producer
	 * @throws NoSuchServiceProducerException if a matching service producer could not be found
	 */
	@Override
	public ServiceProducer findByAuthorizationUserId(long authorizationUserId)
		throws NoSuchServiceProducerException {

		ServiceProducer serviceProducer = fetchByAuthorizationUserId(
			authorizationUserId);

		if (serviceProducer == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("authorizationUserId=");
			sb.append(authorizationUserId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchServiceProducerException(sb.toString());
		}

		return serviceProducer;
	}

	/**
	 * Returns the service producer where authorizationUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @return the matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	@Override
	public ServiceProducer fetchByAuthorizationUserId(
		long authorizationUserId) {

		return fetchByAuthorizationUserId(authorizationUserId, true);
	}

	/**
	 * Returns the service producer where authorizationUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching service producer, or <code>null</code> if a matching service producer could not be found
	 */
	@Override
	public ServiceProducer fetchByAuthorizationUserId(
		long authorizationUserId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {authorizationUserId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByAuthorizationUserId, finderArgs, this);
		}

		if (result instanceof ServiceProducer) {
			ServiceProducer serviceProducer = (ServiceProducer)result;

			if (authorizationUserId !=
					serviceProducer.getAuthorizationUserId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_SERVICEPRODUCER_WHERE);

			sb.append(_FINDER_COLUMN_AUTHORIZATIONUSERID_AUTHORIZATIONUSERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(authorizationUserId);

				List<ServiceProducer> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByAuthorizationUserId, finderArgs,
							list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {authorizationUserId};
							}

							_log.warn(
								"ServiceProducerPersistenceImpl.fetchByAuthorizationUserId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					ServiceProducer serviceProducer = list.get(0);

					result = serviceProducer;

					cacheResult(serviceProducer);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByAuthorizationUserId, finderArgs);
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
			return (ServiceProducer)result;
		}
	}

	/**
	 * Removes the service producer where authorizationUserId = &#63; from the database.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @return the service producer that was removed
	 */
	@Override
	public ServiceProducer removeByAuthorizationUserId(long authorizationUserId)
		throws NoSuchServiceProducerException {

		ServiceProducer serviceProducer = findByAuthorizationUserId(
			authorizationUserId);

		return remove(serviceProducer);
	}

	/**
	 * Returns the number of service producers where authorizationUserId = &#63;.
	 *
	 * @param authorizationUserId the authorization user ID
	 * @return the number of matching service producers
	 */
	@Override
	public int countByAuthorizationUserId(long authorizationUserId) {
		FinderPath finderPath = _finderPathCountByAuthorizationUserId;

		Object[] finderArgs = new Object[] {authorizationUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_SERVICEPRODUCER_WHERE);

			sb.append(_FINDER_COLUMN_AUTHORIZATIONUSERID_AUTHORIZATIONUSERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(authorizationUserId);

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
		_FINDER_COLUMN_AUTHORIZATIONUSERID_AUTHORIZATIONUSERID_2 =
			"serviceProducer.authorizationUserId = ?";

	public ServiceProducerPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(ServiceProducer.class);

		setModelImplClass(ServiceProducerImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the service producer in the entity cache if it is enabled.
	 *
	 * @param serviceProducer the service producer
	 */
	@Override
	public void cacheResult(ServiceProducer serviceProducer) {
		entityCache.putResult(
			entityCacheEnabled, ServiceProducerImpl.class,
			serviceProducer.getPrimaryKey(), serviceProducer);

		finderCache.putResult(
			_finderPathFetchByAuthorizationUserId,
			new Object[] {serviceProducer.getAuthorizationUserId()},
			serviceProducer);

		serviceProducer.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the service producers in the entity cache if it is enabled.
	 *
	 * @param serviceProducers the service producers
	 */
	@Override
	public void cacheResult(List<ServiceProducer> serviceProducers) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (serviceProducers.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ServiceProducer serviceProducer : serviceProducers) {
			if (entityCache.getResult(
					entityCacheEnabled, ServiceProducerImpl.class,
					serviceProducer.getPrimaryKey()) == null) {

				cacheResult(serviceProducer);
			}
			else {
				serviceProducer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all service producers.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ServiceProducerImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the service producer.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ServiceProducer serviceProducer) {
		entityCache.removeResult(
			entityCacheEnabled, ServiceProducerImpl.class,
			serviceProducer.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(ServiceProducerModelImpl)serviceProducer, true);
	}

	@Override
	public void clearCache(List<ServiceProducer> serviceProducers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ServiceProducer serviceProducer : serviceProducers) {
			entityCache.removeResult(
				entityCacheEnabled, ServiceProducerImpl.class,
				serviceProducer.getPrimaryKey());

			clearUniqueFindersCache(
				(ServiceProducerModelImpl)serviceProducer, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, ServiceProducerImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ServiceProducerModelImpl serviceProducerModelImpl) {

		Object[] args = new Object[] {
			serviceProducerModelImpl.getAuthorizationUserId()
		};

		finderCache.putResult(
			_finderPathCountByAuthorizationUserId, args, Long.valueOf(1),
			false);
		finderCache.putResult(
			_finderPathFetchByAuthorizationUserId, args,
			serviceProducerModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ServiceProducerModelImpl serviceProducerModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				serviceProducerModelImpl.getAuthorizationUserId()
			};

			finderCache.removeResult(
				_finderPathCountByAuthorizationUserId, args);
			finderCache.removeResult(
				_finderPathFetchByAuthorizationUserId, args);
		}

		if ((serviceProducerModelImpl.getColumnBitmask() &
			 _finderPathFetchByAuthorizationUserId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				serviceProducerModelImpl.getOriginalAuthorizationUserId()
			};

			finderCache.removeResult(
				_finderPathCountByAuthorizationUserId, args);
			finderCache.removeResult(
				_finderPathFetchByAuthorizationUserId, args);
		}
	}

	/**
	 * Creates a new service producer with the primary key. Does not add the service producer to the database.
	 *
	 * @param serviceProducerId the primary key for the new service producer
	 * @return the new service producer
	 */
	@Override
	public ServiceProducer create(long serviceProducerId) {
		ServiceProducer serviceProducer = new ServiceProducerImpl();

		serviceProducer.setNew(true);
		serviceProducer.setPrimaryKey(serviceProducerId);

		String uuid = PortalUUIDUtil.generate();

		serviceProducer.setUuid(uuid);

		serviceProducer.setCompanyId(CompanyThreadLocal.getCompanyId());

		return serviceProducer;
	}

	/**
	 * Removes the service producer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceProducerId the primary key of the service producer
	 * @return the service producer that was removed
	 * @throws NoSuchServiceProducerException if a service producer with the primary key could not be found
	 */
	@Override
	public ServiceProducer remove(long serviceProducerId)
		throws NoSuchServiceProducerException {

		return remove((Serializable)serviceProducerId);
	}

	/**
	 * Removes the service producer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the service producer
	 * @return the service producer that was removed
	 * @throws NoSuchServiceProducerException if a service producer with the primary key could not be found
	 */
	@Override
	public ServiceProducer remove(Serializable primaryKey)
		throws NoSuchServiceProducerException {

		Session session = null;

		try {
			session = openSession();

			ServiceProducer serviceProducer = (ServiceProducer)session.get(
				ServiceProducerImpl.class, primaryKey);

			if (serviceProducer == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchServiceProducerException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(serviceProducer);
		}
		catch (NoSuchServiceProducerException noSuchEntityException) {
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
	protected ServiceProducer removeImpl(ServiceProducer serviceProducer) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(serviceProducer)) {
				serviceProducer = (ServiceProducer)session.get(
					ServiceProducerImpl.class,
					serviceProducer.getPrimaryKeyObj());
			}

			if (serviceProducer != null) {
				session.delete(serviceProducer);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (serviceProducer != null) {
			clearCache(serviceProducer);
		}

		return serviceProducer;
	}

	@Override
	public ServiceProducer updateImpl(ServiceProducer serviceProducer) {
		boolean isNew = serviceProducer.isNew();

		if (!(serviceProducer instanceof ServiceProducerModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(serviceProducer.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					serviceProducer);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in serviceProducer proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ServiceProducer implementation " +
					serviceProducer.getClass());
		}

		ServiceProducerModelImpl serviceProducerModelImpl =
			(ServiceProducerModelImpl)serviceProducer;

		if (Validator.isNull(serviceProducer.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			serviceProducer.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(serviceProducer);

				serviceProducer.setNew(false);
			}
			else {
				serviceProducer = (ServiceProducer)session.merge(
					serviceProducer);
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
			Object[] args = new Object[] {serviceProducerModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				serviceProducerModelImpl.getUuid(),
				serviceProducerModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((serviceProducerModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					serviceProducerModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {serviceProducerModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((serviceProducerModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					serviceProducerModelImpl.getOriginalUuid(),
					serviceProducerModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					serviceProducerModelImpl.getUuid(),
					serviceProducerModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, ServiceProducerImpl.class,
			serviceProducer.getPrimaryKey(), serviceProducer, false);

		clearUniqueFindersCache(serviceProducerModelImpl, false);
		cacheUniqueFindersCache(serviceProducerModelImpl);

		serviceProducer.resetOriginalValues();

		return serviceProducer;
	}

	/**
	 * Returns the service producer with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the service producer
	 * @return the service producer
	 * @throws NoSuchServiceProducerException if a service producer with the primary key could not be found
	 */
	@Override
	public ServiceProducer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchServiceProducerException {

		ServiceProducer serviceProducer = fetchByPrimaryKey(primaryKey);

		if (serviceProducer == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchServiceProducerException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return serviceProducer;
	}

	/**
	 * Returns the service producer with the primary key or throws a <code>NoSuchServiceProducerException</code> if it could not be found.
	 *
	 * @param serviceProducerId the primary key of the service producer
	 * @return the service producer
	 * @throws NoSuchServiceProducerException if a service producer with the primary key could not be found
	 */
	@Override
	public ServiceProducer findByPrimaryKey(long serviceProducerId)
		throws NoSuchServiceProducerException {

		return findByPrimaryKey((Serializable)serviceProducerId);
	}

	/**
	 * Returns the service producer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param serviceProducerId the primary key of the service producer
	 * @return the service producer, or <code>null</code> if a service producer with the primary key could not be found
	 */
	@Override
	public ServiceProducer fetchByPrimaryKey(long serviceProducerId) {
		return fetchByPrimaryKey((Serializable)serviceProducerId);
	}

	/**
	 * Returns all the service producers.
	 *
	 * @return the service producers
	 */
	@Override
	public List<ServiceProducer> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the service producers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @return the range of service producers
	 */
	@Override
	public List<ServiceProducer> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the service producers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of service producers
	 */
	@Override
	public List<ServiceProducer> findAll(
		int start, int end,
		OrderByComparator<ServiceProducer> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the service producers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceProducerModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of service producers
	 * @param end the upper bound of the range of service producers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of service producers
	 */
	@Override
	public List<ServiceProducer> findAll(
		int start, int end,
		OrderByComparator<ServiceProducer> orderByComparator,
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

		List<ServiceProducer> list = null;

		if (useFinderCache) {
			list = (List<ServiceProducer>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_SERVICEPRODUCER);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_SERVICEPRODUCER;

				sql = sql.concat(ServiceProducerModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ServiceProducer>)QueryUtil.list(
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
	 * Removes all the service producers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ServiceProducer serviceProducer : findAll()) {
			remove(serviceProducer);
		}
	}

	/**
	 * Returns the number of service producers.
	 *
	 * @return the number of service producers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_SERVICEPRODUCER);

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
		return "serviceProducerId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SERVICEPRODUCER;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ServiceProducerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the service producer persistence.
	 */
	@Activate
	public void activate() {
		ServiceProducerModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		ServiceProducerModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ServiceProducerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ServiceProducerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ServiceProducerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ServiceProducerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			ServiceProducerModelImpl.UUID_COLUMN_BITMASK |
			ServiceProducerModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ServiceProducerImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ServiceProducerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			ServiceProducerModelImpl.UUID_COLUMN_BITMASK |
			ServiceProducerModelImpl.COMPANYID_COLUMN_BITMASK |
			ServiceProducerModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathFetchByAuthorizationUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ServiceProducerImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByAuthorizationUserId",
			new String[] {Long.class.getName()},
			ServiceProducerModelImpl.AUTHORIZATIONUSERID_COLUMN_BITMASK);

		_finderPathCountByAuthorizationUserId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByAuthorizationUserId", new String[] {Long.class.getName()});

		ServiceProducerUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		ServiceProducerUtil.setPersistence(null);

		entityCache.removeCache(ServiceProducerImpl.class.getName());

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
				"value.object.column.bitmask.enabled.com.liferay.osb.koroneiki.scion.model.ServiceProducer"),
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

	private static final String _SQL_SELECT_SERVICEPRODUCER =
		"SELECT serviceProducer FROM ServiceProducer serviceProducer";

	private static final String _SQL_SELECT_SERVICEPRODUCER_WHERE =
		"SELECT serviceProducer FROM ServiceProducer serviceProducer WHERE ";

	private static final String _SQL_COUNT_SERVICEPRODUCER =
		"SELECT COUNT(serviceProducer) FROM ServiceProducer serviceProducer";

	private static final String _SQL_COUNT_SERVICEPRODUCER_WHERE =
		"SELECT COUNT(serviceProducer) FROM ServiceProducer serviceProducer WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "serviceProducer.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ServiceProducer exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ServiceProducer exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ServiceProducerPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}