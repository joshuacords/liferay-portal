/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.service.persistence.impl;

import com.liferay.osb.provisioning.license.exception.NoSuchCommonLicenseKeyException;
import com.liferay.osb.provisioning.license.model.CommonLicenseKey;
import com.liferay.osb.provisioning.license.model.impl.CommonLicenseKeyImpl;
import com.liferay.osb.provisioning.license.model.impl.CommonLicenseKeyModelImpl;
import com.liferay.osb.provisioning.license.service.persistence.CommonLicenseKeyPersistence;
import com.liferay.osb.provisioning.license.service.persistence.CommonLicenseKeyUtil;
import com.liferay.osb.provisioning.license.service.persistence.impl.constants.ProvisioningPersistenceConstants;
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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Timestamp;

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
 * The persistence implementation for the common license key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = CommonLicenseKeyPersistence.class)
public class CommonLicenseKeyPersistenceImpl
	extends BasePersistenceImpl<CommonLicenseKey>
	implements CommonLicenseKeyPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommonLicenseKeyUtil</code> to access the common license key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommonLicenseKeyImpl.class.getName();

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
	 * Returns all the common license keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the common license keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @return the range of matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the common license keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the common license keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator,
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

		List<CommonLicenseKey> list = null;

		if (useFinderCache) {
			list = (List<CommonLicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommonLicenseKey commonLicenseKey : list) {
					if (!uuid.equals(commonLicenseKey.getUuid())) {
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

			sb.append(_SQL_SELECT_COMMONLICENSEKEY_WHERE);

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
				sb.append(CommonLicenseKeyModelImpl.ORDER_BY_JPQL);
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

				list = (List<CommonLicenseKey>)QueryUtil.list(
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
	 * Returns the first common license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey findByUuid_First(
			String uuid, OrderByComparator<CommonLicenseKey> orderByComparator)
		throws NoSuchCommonLicenseKeyException {

		CommonLicenseKey commonLicenseKey = fetchByUuid_First(
			uuid, orderByComparator);

		if (commonLicenseKey != null) {
			return commonLicenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchCommonLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first common license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey fetchByUuid_First(
		String uuid, OrderByComparator<CommonLicenseKey> orderByComparator) {

		List<CommonLicenseKey> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last common license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey findByUuid_Last(
			String uuid, OrderByComparator<CommonLicenseKey> orderByComparator)
		throws NoSuchCommonLicenseKeyException {

		CommonLicenseKey commonLicenseKey = fetchByUuid_Last(
			uuid, orderByComparator);

		if (commonLicenseKey != null) {
			return commonLicenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchCommonLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last common license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey fetchByUuid_Last(
		String uuid, OrderByComparator<CommonLicenseKey> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CommonLicenseKey> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the common license keies before and after the current common license key in the ordered set where uuid = &#63;.
	 *
	 * @param commonLicenseKeyId the primary key of the current common license key
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next common license key
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	@Override
	public CommonLicenseKey[] findByUuid_PrevAndNext(
			long commonLicenseKeyId, String uuid,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws NoSuchCommonLicenseKeyException {

		uuid = Objects.toString(uuid, "");

		CommonLicenseKey commonLicenseKey = findByPrimaryKey(
			commonLicenseKeyId);

		Session session = null;

		try {
			session = openSession();

			CommonLicenseKey[] array = new CommonLicenseKeyImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, commonLicenseKey, uuid, orderByComparator, true);

			array[1] = commonLicenseKey;

			array[2] = getByUuid_PrevAndNext(
				session, commonLicenseKey, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommonLicenseKey getByUuid_PrevAndNext(
		Session session, CommonLicenseKey commonLicenseKey, String uuid,
		OrderByComparator<CommonLicenseKey> orderByComparator,
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

		sb.append(_SQL_SELECT_COMMONLICENSEKEY_WHERE);

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
			sb.append(CommonLicenseKeyModelImpl.ORDER_BY_JPQL);
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
						commonLicenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommonLicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the common license keies where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CommonLicenseKey commonLicenseKey :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commonLicenseKey);
		}
	}

	/**
	 * Returns the number of common license keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching common license keies
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMONLICENSEKEY_WHERE);

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
		"commonLicenseKey.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(commonLicenseKey.uuid IS NULL OR commonLicenseKey.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the common license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the common license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @return the range of matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the common license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the common license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator,
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

		List<CommonLicenseKey> list = null;

		if (useFinderCache) {
			list = (List<CommonLicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommonLicenseKey commonLicenseKey : list) {
					if (!uuid.equals(commonLicenseKey.getUuid()) ||
						(companyId != commonLicenseKey.getCompanyId())) {

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

			sb.append(_SQL_SELECT_COMMONLICENSEKEY_WHERE);

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
				sb.append(CommonLicenseKeyModelImpl.ORDER_BY_JPQL);
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

				list = (List<CommonLicenseKey>)QueryUtil.list(
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
	 * Returns the first common license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws NoSuchCommonLicenseKeyException {

		CommonLicenseKey commonLicenseKey = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (commonLicenseKey != null) {
			return commonLicenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchCommonLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first common license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		List<CommonLicenseKey> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last common license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws NoSuchCommonLicenseKeyException {

		CommonLicenseKey commonLicenseKey = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (commonLicenseKey != null) {
			return commonLicenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchCommonLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last common license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CommonLicenseKey> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the common license keies before and after the current common license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commonLicenseKeyId the primary key of the current common license key
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next common license key
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	@Override
	public CommonLicenseKey[] findByUuid_C_PrevAndNext(
			long commonLicenseKeyId, String uuid, long companyId,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws NoSuchCommonLicenseKeyException {

		uuid = Objects.toString(uuid, "");

		CommonLicenseKey commonLicenseKey = findByPrimaryKey(
			commonLicenseKeyId);

		Session session = null;

		try {
			session = openSession();

			CommonLicenseKey[] array = new CommonLicenseKeyImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, commonLicenseKey, uuid, companyId, orderByComparator,
				true);

			array[1] = commonLicenseKey;

			array[2] = getByUuid_C_PrevAndNext(
				session, commonLicenseKey, uuid, companyId, orderByComparator,
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

	protected CommonLicenseKey getByUuid_C_PrevAndNext(
		Session session, CommonLicenseKey commonLicenseKey, String uuid,
		long companyId, OrderByComparator<CommonLicenseKey> orderByComparator,
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

		sb.append(_SQL_SELECT_COMMONLICENSEKEY_WHERE);

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
			sb.append(CommonLicenseKeyModelImpl.ORDER_BY_JPQL);
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
						commonLicenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommonLicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the common license keies where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CommonLicenseKey commonLicenseKey :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(commonLicenseKey);
		}
	}

	/**
	 * Returns the number of common license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching common license keies
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_COMMONLICENSEKEY_WHERE);

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
		"commonLicenseKey.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(commonLicenseKey.uuid IS NULL OR commonLicenseKey.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"commonLicenseKey.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByProductGroup;
	private FinderPath _finderPathWithoutPaginationFindByProductGroup;
	private FinderPath _finderPathCountByProductGroup;

	/**
	 * Returns all the common license keies where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @return the matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByProductGroup(String productGroup) {
		return findByProductGroup(
			productGroup, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the common license keies where productGroup = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productGroup the product group
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @return the range of matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByProductGroup(
		String productGroup, int start, int end) {

		return findByProductGroup(productGroup, start, end, null);
	}

	/**
	 * Returns an ordered range of all the common license keies where productGroup = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productGroup the product group
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByProductGroup(
		String productGroup, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return findByProductGroup(
			productGroup, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the common license keies where productGroup = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productGroup the product group
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByProductGroup(
		String productGroup, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator,
		boolean useFinderCache) {

		productGroup = Objects.toString(productGroup, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByProductGroup;
				finderArgs = new Object[] {productGroup};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByProductGroup;
			finderArgs = new Object[] {
				productGroup, start, end, orderByComparator
			};
		}

		List<CommonLicenseKey> list = null;

		if (useFinderCache) {
			list = (List<CommonLicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommonLicenseKey commonLicenseKey : list) {
					if (!productGroup.equals(
							commonLicenseKey.getProductGroup())) {

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

			sb.append(_SQL_SELECT_COMMONLICENSEKEY_WHERE);

			boolean bindProductGroup = false;

			if (productGroup.isEmpty()) {
				sb.append(_FINDER_COLUMN_PRODUCTGROUP_PRODUCTGROUP_3);
			}
			else {
				bindProductGroup = true;

				sb.append(_FINDER_COLUMN_PRODUCTGROUP_PRODUCTGROUP_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommonLicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductGroup) {
					queryPos.add(productGroup);
				}

				list = (List<CommonLicenseKey>)QueryUtil.list(
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
	 * Returns the first common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey findByProductGroup_First(
			String productGroup,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws NoSuchCommonLicenseKeyException {

		CommonLicenseKey commonLicenseKey = fetchByProductGroup_First(
			productGroup, orderByComparator);

		if (commonLicenseKey != null) {
			return commonLicenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productGroup=");
		sb.append(productGroup);

		sb.append("}");

		throw new NoSuchCommonLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey fetchByProductGroup_First(
		String productGroup,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		List<CommonLicenseKey> list = findByProductGroup(
			productGroup, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey findByProductGroup_Last(
			String productGroup,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws NoSuchCommonLicenseKeyException {

		CommonLicenseKey commonLicenseKey = fetchByProductGroup_Last(
			productGroup, orderByComparator);

		if (commonLicenseKey != null) {
			return commonLicenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productGroup=");
		sb.append(productGroup);

		sb.append("}");

		throw new NoSuchCommonLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey fetchByProductGroup_Last(
		String productGroup,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		int count = countByProductGroup(productGroup);

		if (count == 0) {
			return null;
		}

		List<CommonLicenseKey> list = findByProductGroup(
			productGroup, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the common license keies before and after the current common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param commonLicenseKeyId the primary key of the current common license key
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next common license key
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	@Override
	public CommonLicenseKey[] findByProductGroup_PrevAndNext(
			long commonLicenseKeyId, String productGroup,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws NoSuchCommonLicenseKeyException {

		productGroup = Objects.toString(productGroup, "");

		CommonLicenseKey commonLicenseKey = findByPrimaryKey(
			commonLicenseKeyId);

		Session session = null;

		try {
			session = openSession();

			CommonLicenseKey[] array = new CommonLicenseKeyImpl[3];

			array[0] = getByProductGroup_PrevAndNext(
				session, commonLicenseKey, productGroup, orderByComparator,
				true);

			array[1] = commonLicenseKey;

			array[2] = getByProductGroup_PrevAndNext(
				session, commonLicenseKey, productGroup, orderByComparator,
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

	protected CommonLicenseKey getByProductGroup_PrevAndNext(
		Session session, CommonLicenseKey commonLicenseKey, String productGroup,
		OrderByComparator<CommonLicenseKey> orderByComparator,
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

		sb.append(_SQL_SELECT_COMMONLICENSEKEY_WHERE);

		boolean bindProductGroup = false;

		if (productGroup.isEmpty()) {
			sb.append(_FINDER_COLUMN_PRODUCTGROUP_PRODUCTGROUP_3);
		}
		else {
			bindProductGroup = true;

			sb.append(_FINDER_COLUMN_PRODUCTGROUP_PRODUCTGROUP_2);
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
			sb.append(CommonLicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindProductGroup) {
			queryPos.add(productGroup);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commonLicenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommonLicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the common license keies where productGroup = &#63; from the database.
	 *
	 * @param productGroup the product group
	 */
	@Override
	public void removeByProductGroup(String productGroup) {
		for (CommonLicenseKey commonLicenseKey :
				findByProductGroup(
					productGroup, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commonLicenseKey);
		}
	}

	/**
	 * Returns the number of common license keies where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @return the number of matching common license keies
	 */
	@Override
	public int countByProductGroup(String productGroup) {
		productGroup = Objects.toString(productGroup, "");

		FinderPath finderPath = _finderPathCountByProductGroup;

		Object[] finderArgs = new Object[] {productGroup};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMONLICENSEKEY_WHERE);

			boolean bindProductGroup = false;

			if (productGroup.isEmpty()) {
				sb.append(_FINDER_COLUMN_PRODUCTGROUP_PRODUCTGROUP_3);
			}
			else {
				bindProductGroup = true;

				sb.append(_FINDER_COLUMN_PRODUCTGROUP_PRODUCTGROUP_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductGroup) {
					queryPos.add(productGroup);
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

	private static final String _FINDER_COLUMN_PRODUCTGROUP_PRODUCTGROUP_2 =
		"commonLicenseKey.productGroup = ?";

	private static final String _FINDER_COLUMN_PRODUCTGROUP_PRODUCTGROUP_3 =
		"(commonLicenseKey.productGroup IS NULL OR commonLicenseKey.productGroup = '')";

	private FinderPath _finderPathFetchByFileName;
	private FinderPath _finderPathCountByFileName;

	/**
	 * Returns the common license key where fileName = &#63; or throws a <code>NoSuchCommonLicenseKeyException</code> if it could not be found.
	 *
	 * @param fileName the file name
	 * @return the matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey findByFileName(String fileName)
		throws NoSuchCommonLicenseKeyException {

		CommonLicenseKey commonLicenseKey = fetchByFileName(fileName);

		if (commonLicenseKey == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("fileName=");
			sb.append(fileName);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCommonLicenseKeyException(sb.toString());
		}

		return commonLicenseKey;
	}

	/**
	 * Returns the common license key where fileName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fileName the file name
	 * @return the matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey fetchByFileName(String fileName) {
		return fetchByFileName(fileName, true);
	}

	/**
	 * Returns the common license key where fileName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fileName the file name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey fetchByFileName(
		String fileName, boolean useFinderCache) {

		fileName = Objects.toString(fileName, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {fileName};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByFileName, finderArgs, this);
		}

		if (result instanceof CommonLicenseKey) {
			CommonLicenseKey commonLicenseKey = (CommonLicenseKey)result;

			if (!Objects.equals(fileName, commonLicenseKey.getFileName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_COMMONLICENSEKEY_WHERE);

			boolean bindFileName = false;

			if (fileName.isEmpty()) {
				sb.append(_FINDER_COLUMN_FILENAME_FILENAME_3);
			}
			else {
				bindFileName = true;

				sb.append(_FINDER_COLUMN_FILENAME_FILENAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindFileName) {
					queryPos.add(fileName);
				}

				List<CommonLicenseKey> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByFileName, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {fileName};
							}

							_log.warn(
								"CommonLicenseKeyPersistenceImpl.fetchByFileName(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					CommonLicenseKey commonLicenseKey = list.get(0);

					result = commonLicenseKey;

					cacheResult(commonLicenseKey);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByFileName, finderArgs);
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
			return (CommonLicenseKey)result;
		}
	}

	/**
	 * Removes the common license key where fileName = &#63; from the database.
	 *
	 * @param fileName the file name
	 * @return the common license key that was removed
	 */
	@Override
	public CommonLicenseKey removeByFileName(String fileName)
		throws NoSuchCommonLicenseKeyException {

		CommonLicenseKey commonLicenseKey = findByFileName(fileName);

		return remove(commonLicenseKey);
	}

	/**
	 * Returns the number of common license keies where fileName = &#63;.
	 *
	 * @param fileName the file name
	 * @return the number of matching common license keies
	 */
	@Override
	public int countByFileName(String fileName) {
		fileName = Objects.toString(fileName, "");

		FinderPath finderPath = _finderPathCountByFileName;

		Object[] finderArgs = new Object[] {fileName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMONLICENSEKEY_WHERE);

			boolean bindFileName = false;

			if (fileName.isEmpty()) {
				sb.append(_FINDER_COLUMN_FILENAME_FILENAME_3);
			}
			else {
				bindFileName = true;

				sb.append(_FINDER_COLUMN_FILENAME_FILENAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindFileName) {
					queryPos.add(fileName);
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

	private static final String _FINDER_COLUMN_FILENAME_FILENAME_2 =
		"commonLicenseKey.fileName = ?";

	private static final String _FINDER_COLUMN_FILENAME_FILENAME_3 =
		"(commonLicenseKey.fileName IS NULL OR commonLicenseKey.fileName = '')";

	private FinderPath _finderPathWithPaginationFindByPG_PE_PV_gtS_ltE;
	private FinderPath _finderPathWithPaginationCountByPG_PE_PV_gtS_ltE;

	/**
	 * Returns all the common license keies where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate) {

		return findByPG_PE_PV_gtS_ltE(
			productGroup, productEnvironment, productVersion, startDate,
			endDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the common license keies where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @return the range of matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate, int start, int end) {

		return findByPG_PE_PV_gtS_ltE(
			productGroup, productEnvironment, productVersion, startDate,
			endDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the common license keies where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return findByPG_PE_PV_gtS_ltE(
			productGroup, productEnvironment, productVersion, startDate,
			endDate, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the common license keies where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching common license keies
	 */
	@Override
	public List<CommonLicenseKey> findByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator,
		boolean useFinderCache) {

		productGroup = Objects.toString(productGroup, "");
		productEnvironment = Objects.toString(productEnvironment, "");
		productVersion = Objects.toString(productVersion, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByPG_PE_PV_gtS_ltE;
		finderArgs = new Object[] {
			productGroup, productEnvironment, productVersion,
			_getTime(startDate), _getTime(endDate), start, end,
			orderByComparator
		};

		List<CommonLicenseKey> list = null;

		if (useFinderCache) {
			list = (List<CommonLicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CommonLicenseKey commonLicenseKey : list) {
					if (!productGroup.equals(
							commonLicenseKey.getProductGroup()) ||
						!productEnvironment.equals(
							commonLicenseKey.getProductEnvironment()) ||
						!productVersion.equals(
							commonLicenseKey.getProductVersion()) ||
						(startDate.getTime() <= commonLicenseKey.getStartDate(
						).getTime()) ||
						(endDate.getTime() >= commonLicenseKey.getEndDate(
						).getTime())) {

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
					7 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(7);
			}

			sb.append(_SQL_SELECT_COMMONLICENSEKEY_WHERE);

			boolean bindProductGroup = false;

			if (productGroup.isEmpty()) {
				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTGROUP_3);
			}
			else {
				bindProductGroup = true;

				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTGROUP_2);
			}

			boolean bindProductEnvironment = false;

			if (productEnvironment.isEmpty()) {
				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTENVIRONMENT_3);
			}
			else {
				bindProductEnvironment = true;

				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTENVIRONMENT_2);
			}

			boolean bindProductVersion = false;

			if (productVersion.isEmpty()) {
				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTVERSION_3);
			}
			else {
				bindProductVersion = true;

				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTVERSION_2);
			}

			boolean bindStartDate = false;

			if (startDate == null) {
				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_STARTDATE_1);
			}
			else {
				bindStartDate = true;

				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_STARTDATE_2);
			}

			boolean bindEndDate = false;

			if (endDate == null) {
				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_ENDDATE_1);
			}
			else {
				bindEndDate = true;

				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_ENDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommonLicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductGroup) {
					queryPos.add(productGroup);
				}

				if (bindProductEnvironment) {
					queryPos.add(productEnvironment);
				}

				if (bindProductVersion) {
					queryPos.add(productVersion);
				}

				if (bindStartDate) {
					queryPos.add(new Timestamp(startDate.getTime()));
				}

				if (bindEndDate) {
					queryPos.add(new Timestamp(endDate.getTime()));
				}

				list = (List<CommonLicenseKey>)QueryUtil.list(
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
	 * Returns the first common license key in the ordered set where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey findByPG_PE_PV_gtS_ltE_First(
			String productGroup, String productEnvironment,
			String productVersion, Date startDate, Date endDate,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws NoSuchCommonLicenseKeyException {

		CommonLicenseKey commonLicenseKey = fetchByPG_PE_PV_gtS_ltE_First(
			productGroup, productEnvironment, productVersion, startDate,
			endDate, orderByComparator);

		if (commonLicenseKey != null) {
			return commonLicenseKey;
		}

		StringBundler sb = new StringBundler(12);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productGroup=");
		sb.append(productGroup);

		sb.append(", productEnvironment=");
		sb.append(productEnvironment);

		sb.append(", productVersion=");
		sb.append(productVersion);

		sb.append(", startDate<");
		sb.append(startDate);

		sb.append(", endDate>");
		sb.append(endDate);

		sb.append("}");

		throw new NoSuchCommonLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first common license key in the ordered set where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey fetchByPG_PE_PV_gtS_ltE_First(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		List<CommonLicenseKey> list = findByPG_PE_PV_gtS_ltE(
			productGroup, productEnvironment, productVersion, startDate,
			endDate, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last common license key in the ordered set where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey findByPG_PE_PV_gtS_ltE_Last(
			String productGroup, String productEnvironment,
			String productVersion, Date startDate, Date endDate,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws NoSuchCommonLicenseKeyException {

		CommonLicenseKey commonLicenseKey = fetchByPG_PE_PV_gtS_ltE_Last(
			productGroup, productEnvironment, productVersion, startDate,
			endDate, orderByComparator);

		if (commonLicenseKey != null) {
			return commonLicenseKey;
		}

		StringBundler sb = new StringBundler(12);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productGroup=");
		sb.append(productGroup);

		sb.append(", productEnvironment=");
		sb.append(productEnvironment);

		sb.append(", productVersion=");
		sb.append(productVersion);

		sb.append(", startDate<");
		sb.append(startDate);

		sb.append(", endDate>");
		sb.append(endDate);

		sb.append("}");

		throw new NoSuchCommonLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last common license key in the ordered set where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	@Override
	public CommonLicenseKey fetchByPG_PE_PV_gtS_ltE_Last(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		int count = countByPG_PE_PV_gtS_ltE(
			productGroup, productEnvironment, productVersion, startDate,
			endDate);

		if (count == 0) {
			return null;
		}

		List<CommonLicenseKey> list = findByPG_PE_PV_gtS_ltE(
			productGroup, productEnvironment, productVersion, startDate,
			endDate, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the common license keies before and after the current common license key in the ordered set where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param commonLicenseKeyId the primary key of the current common license key
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next common license key
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	@Override
	public CommonLicenseKey[] findByPG_PE_PV_gtS_ltE_PrevAndNext(
			long commonLicenseKeyId, String productGroup,
			String productEnvironment, String productVersion, Date startDate,
			Date endDate, OrderByComparator<CommonLicenseKey> orderByComparator)
		throws NoSuchCommonLicenseKeyException {

		productGroup = Objects.toString(productGroup, "");
		productEnvironment = Objects.toString(productEnvironment, "");
		productVersion = Objects.toString(productVersion, "");

		CommonLicenseKey commonLicenseKey = findByPrimaryKey(
			commonLicenseKeyId);

		Session session = null;

		try {
			session = openSession();

			CommonLicenseKey[] array = new CommonLicenseKeyImpl[3];

			array[0] = getByPG_PE_PV_gtS_ltE_PrevAndNext(
				session, commonLicenseKey, productGroup, productEnvironment,
				productVersion, startDate, endDate, orderByComparator, true);

			array[1] = commonLicenseKey;

			array[2] = getByPG_PE_PV_gtS_ltE_PrevAndNext(
				session, commonLicenseKey, productGroup, productEnvironment,
				productVersion, startDate, endDate, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommonLicenseKey getByPG_PE_PV_gtS_ltE_PrevAndNext(
		Session session, CommonLicenseKey commonLicenseKey, String productGroup,
		String productEnvironment, String productVersion, Date startDate,
		Date endDate, OrderByComparator<CommonLicenseKey> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				8 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(7);
		}

		sb.append(_SQL_SELECT_COMMONLICENSEKEY_WHERE);

		boolean bindProductGroup = false;

		if (productGroup.isEmpty()) {
			sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTGROUP_3);
		}
		else {
			bindProductGroup = true;

			sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTGROUP_2);
		}

		boolean bindProductEnvironment = false;

		if (productEnvironment.isEmpty()) {
			sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTENVIRONMENT_3);
		}
		else {
			bindProductEnvironment = true;

			sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTENVIRONMENT_2);
		}

		boolean bindProductVersion = false;

		if (productVersion.isEmpty()) {
			sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTVERSION_3);
		}
		else {
			bindProductVersion = true;

			sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTVERSION_2);
		}

		boolean bindStartDate = false;

		if (startDate == null) {
			sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_STARTDATE_1);
		}
		else {
			bindStartDate = true;

			sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_STARTDATE_2);
		}

		boolean bindEndDate = false;

		if (endDate == null) {
			sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_ENDDATE_1);
		}
		else {
			bindEndDate = true;

			sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_ENDDATE_2);
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
			sb.append(CommonLicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindProductGroup) {
			queryPos.add(productGroup);
		}

		if (bindProductEnvironment) {
			queryPos.add(productEnvironment);
		}

		if (bindProductVersion) {
			queryPos.add(productVersion);
		}

		if (bindStartDate) {
			queryPos.add(new Timestamp(startDate.getTime()));
		}

		if (bindEndDate) {
			queryPos.add(new Timestamp(endDate.getTime()));
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commonLicenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommonLicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the common license keies where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63; from the database.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 */
	@Override
	public void removeByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate) {

		for (CommonLicenseKey commonLicenseKey :
				findByPG_PE_PV_gtS_ltE(
					productGroup, productEnvironment, productVersion, startDate,
					endDate, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commonLicenseKey);
		}
	}

	/**
	 * Returns the number of common license keies where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the number of matching common license keies
	 */
	@Override
	public int countByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate) {

		productGroup = Objects.toString(productGroup, "");
		productEnvironment = Objects.toString(productEnvironment, "");
		productVersion = Objects.toString(productVersion, "");

		FinderPath finderPath =
			_finderPathWithPaginationCountByPG_PE_PV_gtS_ltE;

		Object[] finderArgs = new Object[] {
			productGroup, productEnvironment, productVersion,
			_getTime(startDate), _getTime(endDate)
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_SQL_COUNT_COMMONLICENSEKEY_WHERE);

			boolean bindProductGroup = false;

			if (productGroup.isEmpty()) {
				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTGROUP_3);
			}
			else {
				bindProductGroup = true;

				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTGROUP_2);
			}

			boolean bindProductEnvironment = false;

			if (productEnvironment.isEmpty()) {
				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTENVIRONMENT_3);
			}
			else {
				bindProductEnvironment = true;

				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTENVIRONMENT_2);
			}

			boolean bindProductVersion = false;

			if (productVersion.isEmpty()) {
				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTVERSION_3);
			}
			else {
				bindProductVersion = true;

				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTVERSION_2);
			}

			boolean bindStartDate = false;

			if (startDate == null) {
				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_STARTDATE_1);
			}
			else {
				bindStartDate = true;

				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_STARTDATE_2);
			}

			boolean bindEndDate = false;

			if (endDate == null) {
				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_ENDDATE_1);
			}
			else {
				bindEndDate = true;

				sb.append(_FINDER_COLUMN_PG_PE_PV_GTS_LTE_ENDDATE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductGroup) {
					queryPos.add(productGroup);
				}

				if (bindProductEnvironment) {
					queryPos.add(productEnvironment);
				}

				if (bindProductVersion) {
					queryPos.add(productVersion);
				}

				if (bindStartDate) {
					queryPos.add(new Timestamp(startDate.getTime()));
				}

				if (bindEndDate) {
					queryPos.add(new Timestamp(endDate.getTime()));
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

	private static final String _FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTGROUP_2 =
		"commonLicenseKey.productGroup = ? AND ";

	private static final String _FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTGROUP_3 =
		"(commonLicenseKey.productGroup IS NULL OR commonLicenseKey.productGroup = '') AND ";

	private static final String
		_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTENVIRONMENT_2 =
			"commonLicenseKey.productEnvironment = ? AND ";

	private static final String
		_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTENVIRONMENT_3 =
			"(commonLicenseKey.productEnvironment IS NULL OR commonLicenseKey.productEnvironment = '') AND ";

	private static final String
		_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTVERSION_2 =
			"commonLicenseKey.productVersion = ? AND ";

	private static final String
		_FINDER_COLUMN_PG_PE_PV_GTS_LTE_PRODUCTVERSION_3 =
			"(commonLicenseKey.productVersion IS NULL OR commonLicenseKey.productVersion = '') AND ";

	private static final String _FINDER_COLUMN_PG_PE_PV_GTS_LTE_STARTDATE_1 =
		"commonLicenseKey.startDate IS NULL AND ";

	private static final String _FINDER_COLUMN_PG_PE_PV_GTS_LTE_STARTDATE_2 =
		"commonLicenseKey.startDate < ? AND ";

	private static final String _FINDER_COLUMN_PG_PE_PV_GTS_LTE_ENDDATE_1 =
		"commonLicenseKey.endDate IS NULL";

	private static final String _FINDER_COLUMN_PG_PE_PV_GTS_LTE_ENDDATE_2 =
		"commonLicenseKey.endDate > ?";

	public CommonLicenseKeyPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(CommonLicenseKey.class);

		setModelImplClass(CommonLicenseKeyImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the common license key in the entity cache if it is enabled.
	 *
	 * @param commonLicenseKey the common license key
	 */
	@Override
	public void cacheResult(CommonLicenseKey commonLicenseKey) {
		entityCache.putResult(
			entityCacheEnabled, CommonLicenseKeyImpl.class,
			commonLicenseKey.getPrimaryKey(), commonLicenseKey);

		finderCache.putResult(
			_finderPathFetchByFileName,
			new Object[] {commonLicenseKey.getFileName()}, commonLicenseKey);

		commonLicenseKey.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the common license keies in the entity cache if it is enabled.
	 *
	 * @param commonLicenseKeies the common license keies
	 */
	@Override
	public void cacheResult(List<CommonLicenseKey> commonLicenseKeies) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (commonLicenseKeies.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CommonLicenseKey commonLicenseKey : commonLicenseKeies) {
			if (entityCache.getResult(
					entityCacheEnabled, CommonLicenseKeyImpl.class,
					commonLicenseKey.getPrimaryKey()) == null) {

				cacheResult(commonLicenseKey);
			}
			else {
				commonLicenseKey.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all common license keies.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommonLicenseKeyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the common license key.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommonLicenseKey commonLicenseKey) {
		entityCache.removeResult(
			entityCacheEnabled, CommonLicenseKeyImpl.class,
			commonLicenseKey.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CommonLicenseKeyModelImpl)commonLicenseKey, true);
	}

	@Override
	public void clearCache(List<CommonLicenseKey> commonLicenseKeies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CommonLicenseKey commonLicenseKey : commonLicenseKeies) {
			entityCache.removeResult(
				entityCacheEnabled, CommonLicenseKeyImpl.class,
				commonLicenseKey.getPrimaryKey());

			clearUniqueFindersCache(
				(CommonLicenseKeyModelImpl)commonLicenseKey, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, CommonLicenseKeyImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CommonLicenseKeyModelImpl commonLicenseKeyModelImpl) {

		Object[] args = new Object[] {commonLicenseKeyModelImpl.getFileName()};

		finderCache.putResult(
			_finderPathCountByFileName, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByFileName, args, commonLicenseKeyModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CommonLicenseKeyModelImpl commonLicenseKeyModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				commonLicenseKeyModelImpl.getFileName()
			};

			finderCache.removeResult(_finderPathCountByFileName, args);
			finderCache.removeResult(_finderPathFetchByFileName, args);
		}

		if ((commonLicenseKeyModelImpl.getColumnBitmask() &
			 _finderPathFetchByFileName.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				commonLicenseKeyModelImpl.getOriginalFileName()
			};

			finderCache.removeResult(_finderPathCountByFileName, args);
			finderCache.removeResult(_finderPathFetchByFileName, args);
		}
	}

	/**
	 * Creates a new common license key with the primary key. Does not add the common license key to the database.
	 *
	 * @param commonLicenseKeyId the primary key for the new common license key
	 * @return the new common license key
	 */
	@Override
	public CommonLicenseKey create(long commonLicenseKeyId) {
		CommonLicenseKey commonLicenseKey = new CommonLicenseKeyImpl();

		commonLicenseKey.setNew(true);
		commonLicenseKey.setPrimaryKey(commonLicenseKeyId);

		String uuid = PortalUUIDUtil.generate();

		commonLicenseKey.setUuid(uuid);

		commonLicenseKey.setCompanyId(CompanyThreadLocal.getCompanyId());

		return commonLicenseKey;
	}

	/**
	 * Removes the common license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commonLicenseKeyId the primary key of the common license key
	 * @return the common license key that was removed
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	@Override
	public CommonLicenseKey remove(long commonLicenseKeyId)
		throws NoSuchCommonLicenseKeyException {

		return remove((Serializable)commonLicenseKeyId);
	}

	/**
	 * Removes the common license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the common license key
	 * @return the common license key that was removed
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	@Override
	public CommonLicenseKey remove(Serializable primaryKey)
		throws NoSuchCommonLicenseKeyException {

		Session session = null;

		try {
			session = openSession();

			CommonLicenseKey commonLicenseKey = (CommonLicenseKey)session.get(
				CommonLicenseKeyImpl.class, primaryKey);

			if (commonLicenseKey == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCommonLicenseKeyException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commonLicenseKey);
		}
		catch (NoSuchCommonLicenseKeyException noSuchEntityException) {
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
	protected CommonLicenseKey removeImpl(CommonLicenseKey commonLicenseKey) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commonLicenseKey)) {
				commonLicenseKey = (CommonLicenseKey)session.get(
					CommonLicenseKeyImpl.class,
					commonLicenseKey.getPrimaryKeyObj());
			}

			if (commonLicenseKey != null) {
				session.delete(commonLicenseKey);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (commonLicenseKey != null) {
			clearCache(commonLicenseKey);
		}

		return commonLicenseKey;
	}

	@Override
	public CommonLicenseKey updateImpl(CommonLicenseKey commonLicenseKey) {
		boolean isNew = commonLicenseKey.isNew();

		if (!(commonLicenseKey instanceof CommonLicenseKeyModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commonLicenseKey.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commonLicenseKey);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commonLicenseKey proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommonLicenseKey implementation " +
					commonLicenseKey.getClass());
		}

		CommonLicenseKeyModelImpl commonLicenseKeyModelImpl =
			(CommonLicenseKeyModelImpl)commonLicenseKey;

		if (Validator.isNull(commonLicenseKey.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			commonLicenseKey.setUuid(uuid);
		}

		if (isNew && (commonLicenseKey.getCreateDate() == null)) {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			Date date = new Date();

			if (serviceContext == null) {
				commonLicenseKey.setCreateDate(date);
			}
			else {
				commonLicenseKey.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(commonLicenseKey);

				commonLicenseKey.setNew(false);
			}
			else {
				commonLicenseKey = (CommonLicenseKey)session.merge(
					commonLicenseKey);
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
			Object[] args = new Object[] {commonLicenseKeyModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				commonLicenseKeyModelImpl.getUuid(),
				commonLicenseKeyModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {commonLicenseKeyModelImpl.getProductGroup()};

			finderCache.removeResult(_finderPathCountByProductGroup, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByProductGroup, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((commonLicenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commonLicenseKeyModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {commonLicenseKeyModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((commonLicenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					commonLicenseKeyModelImpl.getOriginalUuid(),
					commonLicenseKeyModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					commonLicenseKeyModelImpl.getUuid(),
					commonLicenseKeyModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((commonLicenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByProductGroup.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					commonLicenseKeyModelImpl.getOriginalProductGroup()
				};

				finderCache.removeResult(_finderPathCountByProductGroup, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductGroup, args);

				args = new Object[] {
					commonLicenseKeyModelImpl.getProductGroup()
				};

				finderCache.removeResult(_finderPathCountByProductGroup, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductGroup, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, CommonLicenseKeyImpl.class,
			commonLicenseKey.getPrimaryKey(), commonLicenseKey, false);

		clearUniqueFindersCache(commonLicenseKeyModelImpl, false);
		cacheUniqueFindersCache(commonLicenseKeyModelImpl);

		commonLicenseKey.resetOriginalValues();

		return commonLicenseKey;
	}

	/**
	 * Returns the common license key with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the common license key
	 * @return the common license key
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	@Override
	public CommonLicenseKey findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCommonLicenseKeyException {

		CommonLicenseKey commonLicenseKey = fetchByPrimaryKey(primaryKey);

		if (commonLicenseKey == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCommonLicenseKeyException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commonLicenseKey;
	}

	/**
	 * Returns the common license key with the primary key or throws a <code>NoSuchCommonLicenseKeyException</code> if it could not be found.
	 *
	 * @param commonLicenseKeyId the primary key of the common license key
	 * @return the common license key
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	@Override
	public CommonLicenseKey findByPrimaryKey(long commonLicenseKeyId)
		throws NoSuchCommonLicenseKeyException {

		return findByPrimaryKey((Serializable)commonLicenseKeyId);
	}

	/**
	 * Returns the common license key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commonLicenseKeyId the primary key of the common license key
	 * @return the common license key, or <code>null</code> if a common license key with the primary key could not be found
	 */
	@Override
	public CommonLicenseKey fetchByPrimaryKey(long commonLicenseKeyId) {
		return fetchByPrimaryKey((Serializable)commonLicenseKeyId);
	}

	/**
	 * Returns all the common license keies.
	 *
	 * @return the common license keies
	 */
	@Override
	public List<CommonLicenseKey> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the common license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @return the range of common license keies
	 */
	@Override
	public List<CommonLicenseKey> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the common license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of common license keies
	 */
	@Override
	public List<CommonLicenseKey> findAll(
		int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the common license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of common license keies
	 */
	@Override
	public List<CommonLicenseKey> findAll(
		int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator,
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

		List<CommonLicenseKey> list = null;

		if (useFinderCache) {
			list = (List<CommonLicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMMONLICENSEKEY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMMONLICENSEKEY;

				sql = sql.concat(CommonLicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CommonLicenseKey>)QueryUtil.list(
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
	 * Removes all the common license keies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommonLicenseKey commonLicenseKey : findAll()) {
			remove(commonLicenseKey);
		}
	}

	/**
	 * Returns the number of common license keies.
	 *
	 * @return the number of common license keies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_COMMONLICENSEKEY);

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
		return "commonLicenseKeyId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMMONLICENSEKEY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CommonLicenseKeyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the common license key persistence.
	 */
	@Activate
	public void activate() {
		CommonLicenseKeyModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		CommonLicenseKeyModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CommonLicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CommonLicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CommonLicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CommonLicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			CommonLicenseKeyModelImpl.UUID_COLUMN_BITMASK |
			CommonLicenseKeyModelImpl.ENDDATE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CommonLicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CommonLicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			CommonLicenseKeyModelImpl.UUID_COLUMN_BITMASK |
			CommonLicenseKeyModelImpl.COMPANYID_COLUMN_BITMASK |
			CommonLicenseKeyModelImpl.ENDDATE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByProductGroup = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CommonLicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProductGroup",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByProductGroup = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CommonLicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProductGroup",
			new String[] {String.class.getName()},
			CommonLicenseKeyModelImpl.PRODUCTGROUP_COLUMN_BITMASK |
			CommonLicenseKeyModelImpl.ENDDATE_COLUMN_BITMASK);

		_finderPathCountByProductGroup = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProductGroup",
			new String[] {String.class.getName()});

		_finderPathFetchByFileName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CommonLicenseKeyImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByFileName",
			new String[] {String.class.getName()},
			CommonLicenseKeyModelImpl.FILENAME_COLUMN_BITMASK);

		_finderPathCountByFileName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFileName",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByPG_PE_PV_gtS_ltE = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CommonLicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPG_PE_PV_gtS_ltE",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), Date.class.getName(),
				Date.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByPG_PE_PV_gtS_ltE = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByPG_PE_PV_gtS_ltE",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), Date.class.getName(),
				Date.class.getName()
			});

		CommonLicenseKeyUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		CommonLicenseKeyUtil.setPersistence(null);

		entityCache.removeCache(CommonLicenseKeyImpl.class.getName());

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
				"value.object.column.bitmask.enabled.com.liferay.osb.provisioning.license.model.CommonLicenseKey"),
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

	private static Long _getTime(Date date) {
		if (date == null) {
			return null;
		}

		return date.getTime();
	}

	private static final String _SQL_SELECT_COMMONLICENSEKEY =
		"SELECT commonLicenseKey FROM CommonLicenseKey commonLicenseKey";

	private static final String _SQL_SELECT_COMMONLICENSEKEY_WHERE =
		"SELECT commonLicenseKey FROM CommonLicenseKey commonLicenseKey WHERE ";

	private static final String _SQL_COUNT_COMMONLICENSEKEY =
		"SELECT COUNT(commonLicenseKey) FROM CommonLicenseKey commonLicenseKey";

	private static final String _SQL_COUNT_COMMONLICENSEKEY_WHERE =
		"SELECT COUNT(commonLicenseKey) FROM CommonLicenseKey commonLicenseKey WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "commonLicenseKey.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommonLicenseKey exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommonLicenseKey exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommonLicenseKeyPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}