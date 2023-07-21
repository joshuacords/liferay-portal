/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.service.persistence.impl;

import com.liferay.osb.provisioning.license.exception.NoSuchLicenseKeyException;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.model.impl.LicenseKeyImpl;
import com.liferay.osb.provisioning.license.model.impl.LicenseKeyModelImpl;
import com.liferay.osb.provisioning.license.service.persistence.LicenseKeyPersistence;
import com.liferay.osb.provisioning.license.service.persistence.LicenseKeyUtil;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

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
 * The persistence implementation for the license key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = LicenseKeyPersistence.class)
public class LicenseKeyPersistenceImpl
	extends BasePersistenceImpl<LicenseKey> implements LicenseKeyPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LicenseKeyUtil</code> to access the license key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LicenseKeyImpl.class.getName();

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
	 * Returns all the license keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
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

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!uuid.equals(licenseKey.getUuid())) {
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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

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
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
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

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByUuid_First(
			String uuid, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByUuid_First(uuid, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByUuid_First(
		String uuid, OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByUuid_Last(
			String uuid, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByUuid_Last(uuid, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByUuid_Last(
		String uuid, OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where uuid = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByUuid_PrevAndNext(
			long licenseKeyId, String uuid,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		uuid = Objects.toString(uuid, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, licenseKey, uuid, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByUuid_PrevAndNext(
				session, licenseKey, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByUuid_PrevAndNext(
		Session session, LicenseKey licenseKey, String uuid,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (LicenseKey licenseKey :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching license keies
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

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
		"licenseKey.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(licenseKey.uuid IS NULL OR licenseKey.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
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

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!uuid.equals(licenseKey.getUuid()) ||
						(companyId != licenseKey.getCompanyId())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

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
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
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

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByUuid_C_PrevAndNext(
			long licenseKeyId, String uuid, long companyId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		uuid = Objects.toString(uuid, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, licenseKey, uuid, companyId, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByUuid_C_PrevAndNext(
				session, licenseKey, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByUuid_C_PrevAndNext(
		Session session, LicenseKey licenseKey, String uuid, long companyId,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (LicenseKey licenseKey :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

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
		"licenseKey.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(licenseKey.uuid IS NULL OR licenseKey.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"licenseKey.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByARLU_A;
	private FinderPath _finderPathWithoutPaginationFindByARLU_A;
	private FinderPath _finderPathCountByARLU_A;

	/**
	 * Returns all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active) {

		return findByARLU_A(
			assetReceiptLicenseUuid, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active, int start, int end) {

		return findByARLU_A(assetReceiptLicenseUuid, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByARLU_A(
			assetReceiptLicenseUuid, active, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_A(
		String assetReceiptLicenseUuid, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByARLU_A;
				finderArgs = new Object[] {assetReceiptLicenseUuid, active};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByARLU_A;
			finderArgs = new Object[] {
				assetReceiptLicenseUuid, active, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!assetReceiptLicenseUuid.equals(
							licenseKey.getAssetReceiptLicenseUuid()) ||
						(active != licenseKey.isActive())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindAssetReceiptLicenseUuid = false;

			if (assetReceiptLicenseUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_3);
			}
			else {
				bindAssetReceiptLicenseUuid = true;

				sb.append(_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_2);
			}

			sb.append(_FINDER_COLUMN_ARLU_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAssetReceiptLicenseUuid) {
					queryPos.add(assetReceiptLicenseUuid);
				}

				queryPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByARLU_A_First(
			String assetReceiptLicenseUuid, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByARLU_A_First(
			assetReceiptLicenseUuid, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("assetReceiptLicenseUuid=");
		sb.append(assetReceiptLicenseUuid);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByARLU_A_First(
		String assetReceiptLicenseUuid, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByARLU_A(
			assetReceiptLicenseUuid, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByARLU_A_Last(
			String assetReceiptLicenseUuid, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByARLU_A_Last(
			assetReceiptLicenseUuid, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("assetReceiptLicenseUuid=");
		sb.append(assetReceiptLicenseUuid);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByARLU_A_Last(
		String assetReceiptLicenseUuid, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByARLU_A(assetReceiptLicenseUuid, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByARLU_A(
			assetReceiptLicenseUuid, active, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByARLU_A_PrevAndNext(
			long licenseKeyId, String assetReceiptLicenseUuid, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByARLU_A_PrevAndNext(
				session, licenseKey, assetReceiptLicenseUuid, active,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByARLU_A_PrevAndNext(
				session, licenseKey, assetReceiptLicenseUuid, active,
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

	protected LicenseKey getByARLU_A_PrevAndNext(
		Session session, LicenseKey licenseKey, String assetReceiptLicenseUuid,
		boolean active, OrderByComparator<LicenseKey> orderByComparator,
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

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindAssetReceiptLicenseUuid = false;

		if (assetReceiptLicenseUuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_3);
		}
		else {
			bindAssetReceiptLicenseUuid = true;

			sb.append(_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_2);
		}

		sb.append(_FINDER_COLUMN_ARLU_A_ACTIVE_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindAssetReceiptLicenseUuid) {
			queryPos.add(assetReceiptLicenseUuid);
		}

		queryPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 */
	@Override
	public void removeByARLU_A(String assetReceiptLicenseUuid, boolean active) {
		for (LicenseKey licenseKey :
				findByARLU_A(
					assetReceiptLicenseUuid, active, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByARLU_A(String assetReceiptLicenseUuid, boolean active) {
		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");

		FinderPath finderPath = _finderPathCountByARLU_A;

		Object[] finderArgs = new Object[] {assetReceiptLicenseUuid, active};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindAssetReceiptLicenseUuid = false;

			if (assetReceiptLicenseUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_3);
			}
			else {
				bindAssetReceiptLicenseUuid = true;

				sb.append(_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_2);
			}

			sb.append(_FINDER_COLUMN_ARLU_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAssetReceiptLicenseUuid) {
					queryPos.add(assetReceiptLicenseUuid);
				}

				queryPos.add(active);

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
		_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_2 =
			"licenseKey.assetReceiptLicenseUuid = ? AND ";

	private static final String
		_FINDER_COLUMN_ARLU_A_ASSETRECEIPTLICENSEUUID_3 =
			"(licenseKey.assetReceiptLicenseUuid IS NULL OR licenseKey.assetReceiptLicenseUuid = '') AND ";

	private static final String _FINDER_COLUMN_ARLU_A_ACTIVE_2 =
		"licenseKey.active = ?";

	private FinderPath _finderPathWithPaginationFindByPPK_CI;
	private FinderPath _finderPathWithoutPaginationFindByPPK_CI;
	private FinderPath _finderPathCountByPPK_CI;

	/**
	 * Returns all the license keies where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId) {

		return findByPPK_CI(
			productPurchaseKey, clusterId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the license keies where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId, int start, int end) {

		return findByPPK_CI(productPurchaseKey, clusterId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByPPK_CI(
			productPurchaseKey, clusterId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPPK_CI(
		String productPurchaseKey, long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPPK_CI;
				finderArgs = new Object[] {productPurchaseKey, clusterId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPPK_CI;
			finderArgs = new Object[] {
				productPurchaseKey, clusterId, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!productPurchaseKey.equals(
							licenseKey.getProductPurchaseKey()) ||
						(clusterId != licenseKey.getClusterId())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(_FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_2);
			}

			sb.append(_FINDER_COLUMN_PPK_CI_CLUSTERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductPurchaseKey) {
					queryPos.add(productPurchaseKey);
				}

				queryPos.add(clusterId);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPPK_CI_First(
			String productPurchaseKey, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPPK_CI_First(
			productPurchaseKey, clusterId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseKey=");
		sb.append(productPurchaseKey);

		sb.append(", clusterId=");
		sb.append(clusterId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPPK_CI_First(
		String productPurchaseKey, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByPPK_CI(
			productPurchaseKey, clusterId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPPK_CI_Last(
			String productPurchaseKey, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPPK_CI_Last(
			productPurchaseKey, clusterId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseKey=");
		sb.append(productPurchaseKey);

		sb.append(", clusterId=");
		sb.append(clusterId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPPK_CI_Last(
		String productPurchaseKey, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByPPK_CI(productPurchaseKey, clusterId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByPPK_CI(
			productPurchaseKey, clusterId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByPPK_CI_PrevAndNext(
			long licenseKeyId, String productPurchaseKey, long clusterId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPPK_CI_PrevAndNext(
				session, licenseKey, productPurchaseKey, clusterId,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByPPK_CI_PrevAndNext(
				session, licenseKey, productPurchaseKey, clusterId,
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

	protected LicenseKey getByPPK_CI_PrevAndNext(
		Session session, LicenseKey licenseKey, String productPurchaseKey,
		long clusterId, OrderByComparator<LicenseKey> orderByComparator,
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

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindProductPurchaseKey = false;

		if (productPurchaseKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_3);
		}
		else {
			bindProductPurchaseKey = true;

			sb.append(_FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_2);
		}

		sb.append(_FINDER_COLUMN_PPK_CI_CLUSTERID_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindProductPurchaseKey) {
			queryPos.add(productPurchaseKey);
		}

		queryPos.add(clusterId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where productPurchaseKey = &#63; and clusterId = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 */
	@Override
	public void removeByPPK_CI(String productPurchaseKey, long clusterId) {
		for (LicenseKey licenseKey :
				findByPPK_CI(
					productPurchaseKey, clusterId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where productPurchaseKey = &#63; and clusterId = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param clusterId the cluster ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByPPK_CI(String productPurchaseKey, long clusterId) {
		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		FinderPath finderPath = _finderPathCountByPPK_CI;

		Object[] finderArgs = new Object[] {productPurchaseKey, clusterId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(_FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_2);
			}

			sb.append(_FINDER_COLUMN_PPK_CI_CLUSTERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductPurchaseKey) {
					queryPos.add(productPurchaseKey);
				}

				queryPos.add(clusterId);

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

	private static final String _FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_2 =
		"licenseKey.productPurchaseKey = ? AND ";

	private static final String _FINDER_COLUMN_PPK_CI_PRODUCTPURCHASEKEY_3 =
		"(licenseKey.productPurchaseKey IS NULL OR licenseKey.productPurchaseKey = '') AND ";

	private static final String _FINDER_COLUMN_PPK_CI_CLUSTERID_2 =
		"licenseKey.clusterId = ?";

	private FinderPath _finderPathWithPaginationFindByPI_SI;
	private FinderPath _finderPathWithoutPaginationFindByPI_SI;
	private FinderPath _finderPathCountByPI_SI;

	/**
	 * Returns all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByPI_SI(String productId, String serverId) {
		return findByPI_SI(
			productId, serverId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPI_SI(
		String productId, String serverId, int start, int end) {

		return findByPI_SI(productId, serverId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPI_SI(
		String productId, String serverId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByPI_SI(
			productId, serverId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where productId = &#63; and serverId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPI_SI(
		String productId, String serverId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		productId = Objects.toString(productId, "");
		serverId = Objects.toString(serverId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPI_SI;
				finderArgs = new Object[] {productId, serverId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPI_SI;
			finderArgs = new Object[] {
				productId, serverId, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!productId.equals(licenseKey.getProductId()) ||
						!serverId.equals(licenseKey.getServerId())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindProductId = false;

			if (productId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PI_SI_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				sb.append(_FINDER_COLUMN_PI_SI_PRODUCTID_2);
			}

			boolean bindServerId = false;

			if (serverId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PI_SI_SERVERID_3);
			}
			else {
				bindServerId = true;

				sb.append(_FINDER_COLUMN_PI_SI_SERVERID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductId) {
					queryPos.add(productId);
				}

				if (bindServerId) {
					queryPos.add(serverId);
				}

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPI_SI_First(
			String productId, String serverId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPI_SI_First(
			productId, serverId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productId=");
		sb.append(productId);

		sb.append(", serverId=");
		sb.append(serverId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPI_SI_First(
		String productId, String serverId,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByPI_SI(
			productId, serverId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPI_SI_Last(
			String productId, String serverId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPI_SI_Last(
			productId, serverId, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productId=");
		sb.append(productId);

		sb.append(", serverId=");
		sb.append(serverId);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPI_SI_Last(
		String productId, String serverId,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByPI_SI(productId, serverId);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByPI_SI(
			productId, serverId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productId = &#63; and serverId = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByPI_SI_PrevAndNext(
			long licenseKeyId, String productId, String serverId,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		productId = Objects.toString(productId, "");
		serverId = Objects.toString(serverId, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPI_SI_PrevAndNext(
				session, licenseKey, productId, serverId, orderByComparator,
				true);

			array[1] = licenseKey;

			array[2] = getByPI_SI_PrevAndNext(
				session, licenseKey, productId, serverId, orderByComparator,
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

	protected LicenseKey getByPI_SI_PrevAndNext(
		Session session, LicenseKey licenseKey, String productId,
		String serverId, OrderByComparator<LicenseKey> orderByComparator,
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

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindProductId = false;

		if (productId.isEmpty()) {
			sb.append(_FINDER_COLUMN_PI_SI_PRODUCTID_3);
		}
		else {
			bindProductId = true;

			sb.append(_FINDER_COLUMN_PI_SI_PRODUCTID_2);
		}

		boolean bindServerId = false;

		if (serverId.isEmpty()) {
			sb.append(_FINDER_COLUMN_PI_SI_SERVERID_3);
		}
		else {
			bindServerId = true;

			sb.append(_FINDER_COLUMN_PI_SI_SERVERID_2);
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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindProductId) {
			queryPos.add(productId);
		}

		if (bindServerId) {
			queryPos.add(serverId);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where productId = &#63; and serverId = &#63; from the database.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 */
	@Override
	public void removeByPI_SI(String productId, String serverId) {
		for (LicenseKey licenseKey :
				findByPI_SI(
					productId, serverId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where productId = &#63; and serverId = &#63;.
	 *
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @return the number of matching license keies
	 */
	@Override
	public int countByPI_SI(String productId, String serverId) {
		productId = Objects.toString(productId, "");
		serverId = Objects.toString(serverId, "");

		FinderPath finderPath = _finderPathCountByPI_SI;

		Object[] finderArgs = new Object[] {productId, serverId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindProductId = false;

			if (productId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PI_SI_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				sb.append(_FINDER_COLUMN_PI_SI_PRODUCTID_2);
			}

			boolean bindServerId = false;

			if (serverId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PI_SI_SERVERID_3);
			}
			else {
				bindServerId = true;

				sb.append(_FINDER_COLUMN_PI_SI_SERVERID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductId) {
					queryPos.add(productId);
				}

				if (bindServerId) {
					queryPos.add(serverId);
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

	private static final String _FINDER_COLUMN_PI_SI_PRODUCTID_2 =
		"licenseKey.productId = ? AND ";

	private static final String _FINDER_COLUMN_PI_SI_PRODUCTID_3 =
		"(licenseKey.productId IS NULL OR licenseKey.productId = '') AND ";

	private static final String _FINDER_COLUMN_PI_SI_SERVERID_2 =
		"licenseKey.serverId = ?";

	private static final String _FINDER_COLUMN_PI_SI_SERVERID_3 =
		"(licenseKey.serverId IS NULL OR licenseKey.serverId = '')";

	private FinderPath _finderPathWithPaginationFindByARLU_C_A;
	private FinderPath _finderPathWithoutPaginationFindByARLU_C_A;
	private FinderPath _finderPathCountByARLU_C_A;

	/**
	 * Returns all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		int start, int end) {

		return findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {

		return findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByARLU_C_A;
				finderArgs = new Object[] {
					assetReceiptLicenseUuid, complimentary, active
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByARLU_C_A;
			finderArgs = new Object[] {
				assetReceiptLicenseUuid, complimentary, active, start, end,
				orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!assetReceiptLicenseUuid.equals(
							licenseKey.getAssetReceiptLicenseUuid()) ||
						(complimentary != licenseKey.isComplimentary()) ||
						(active != licenseKey.isActive())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindAssetReceiptLicenseUuid = false;

			if (assetReceiptLicenseUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_3);
			}
			else {
				bindAssetReceiptLicenseUuid = true;

				sb.append(_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_2);
			}

			sb.append(_FINDER_COLUMN_ARLU_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_ARLU_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAssetReceiptLicenseUuid) {
					queryPos.add(assetReceiptLicenseUuid);
				}

				queryPos.add(complimentary);

				queryPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByARLU_C_A_First(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByARLU_C_A_First(
			assetReceiptLicenseUuid, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("assetReceiptLicenseUuid=");
		sb.append(assetReceiptLicenseUuid);

		sb.append(", complimentary=");
		sb.append(complimentary);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByARLU_C_A_First(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByARLU_C_A_Last(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByARLU_C_A_Last(
			assetReceiptLicenseUuid, complimentary, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("assetReceiptLicenseUuid=");
		sb.append(assetReceiptLicenseUuid);

		sb.append(", complimentary=");
		sb.append(complimentary);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByARLU_C_A_Last(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByARLU_C_A_PrevAndNext(
			long licenseKeyId, String assetReceiptLicenseUuid,
			boolean complimentary, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByARLU_C_A_PrevAndNext(
				session, licenseKey, assetReceiptLicenseUuid, complimentary,
				active, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByARLU_C_A_PrevAndNext(
				session, licenseKey, assetReceiptLicenseUuid, complimentary,
				active, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByARLU_C_A_PrevAndNext(
		Session session, LicenseKey licenseKey, String assetReceiptLicenseUuid,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindAssetReceiptLicenseUuid = false;

		if (assetReceiptLicenseUuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_3);
		}
		else {
			bindAssetReceiptLicenseUuid = true;

			sb.append(_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_2);
		}

		sb.append(_FINDER_COLUMN_ARLU_C_A_COMPLIMENTARY_2);

		sb.append(_FINDER_COLUMN_ARLU_C_A_ACTIVE_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindAssetReceiptLicenseUuid) {
			queryPos.add(assetReceiptLicenseUuid);
		}

		queryPos.add(complimentary);

		queryPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 */
	@Override
	public void removeByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		for (LicenseKey licenseKey :
				findByARLU_C_A(
					assetReceiptLicenseUuid, complimentary, active,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param complimentary the complimentary
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByARLU_C_A(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");

		FinderPath finderPath = _finderPathCountByARLU_C_A;

		Object[] finderArgs = new Object[] {
			assetReceiptLicenseUuid, complimentary, active
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindAssetReceiptLicenseUuid = false;

			if (assetReceiptLicenseUuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_3);
			}
			else {
				bindAssetReceiptLicenseUuid = true;

				sb.append(_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_2);
			}

			sb.append(_FINDER_COLUMN_ARLU_C_A_COMPLIMENTARY_2);

			sb.append(_FINDER_COLUMN_ARLU_C_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAssetReceiptLicenseUuid) {
					queryPos.add(assetReceiptLicenseUuid);
				}

				queryPos.add(complimentary);

				queryPos.add(active);

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
		_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_2 =
			"licenseKey.assetReceiptLicenseUuid = ? AND ";

	private static final String
		_FINDER_COLUMN_ARLU_C_A_ASSETRECEIPTLICENSEUUID_3 =
			"(licenseKey.assetReceiptLicenseUuid IS NULL OR licenseKey.assetReceiptLicenseUuid = '') AND ";

	private static final String _FINDER_COLUMN_ARLU_C_A_COMPLIMENTARY_2 =
		"licenseKey.complimentary = ? AND ";

	private static final String _FINDER_COLUMN_ARLU_C_A_ACTIVE_2 =
		"licenseKey.active = ?";

	private FinderPath _finderPathWithPaginationFindByPN_SI_A;
	private FinderPath _finderPathWithoutPaginationFindByPN_SI_A;
	private FinderPath _finderPathCountByPN_SI_A;

	/**
	 * Returns all the license keies where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active) {

		return findByPN_SI_A(
			productName, serverId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the license keies where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active, int start,
		int end) {

		return findByPN_SI_A(productName, serverId, active, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByPN_SI_A(
			productName, serverId, active, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByPN_SI_A(
		String productName, String serverId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		productName = Objects.toString(productName, "");
		serverId = Objects.toString(serverId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByPN_SI_A;
				finderArgs = new Object[] {productName, serverId, active};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByPN_SI_A;
			finderArgs = new Object[] {
				productName, serverId, active, start, end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!productName.equals(licenseKey.getProductName()) ||
						!serverId.equals(licenseKey.getServerId()) ||
						(active != licenseKey.isActive())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindProductName = false;

			if (productName.isEmpty()) {
				sb.append(_FINDER_COLUMN_PN_SI_A_PRODUCTNAME_3);
			}
			else {
				bindProductName = true;

				sb.append(_FINDER_COLUMN_PN_SI_A_PRODUCTNAME_2);
			}

			boolean bindServerId = false;

			if (serverId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PN_SI_A_SERVERID_3);
			}
			else {
				bindServerId = true;

				sb.append(_FINDER_COLUMN_PN_SI_A_SERVERID_2);
			}

			sb.append(_FINDER_COLUMN_PN_SI_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductName) {
					queryPos.add(productName);
				}

				if (bindServerId) {
					queryPos.add(serverId);
				}

				queryPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPN_SI_A_First(
			String productName, String serverId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPN_SI_A_First(
			productName, serverId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productName=");
		sb.append(productName);

		sb.append(", serverId=");
		sb.append(serverId);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPN_SI_A_First(
		String productName, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByPN_SI_A(
			productName, serverId, active, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByPN_SI_A_Last(
			String productName, String serverId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPN_SI_A_Last(
			productName, serverId, active, orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productName=");
		sb.append(productName);

		sb.append(", serverId=");
		sb.append(serverId);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByPN_SI_A_Last(
		String productName, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByPN_SI_A(productName, serverId, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByPN_SI_A(
			productName, serverId, active, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByPN_SI_A_PrevAndNext(
			long licenseKeyId, String productName, String serverId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		productName = Objects.toString(productName, "");
		serverId = Objects.toString(serverId, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByPN_SI_A_PrevAndNext(
				session, licenseKey, productName, serverId, active,
				orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByPN_SI_A_PrevAndNext(
				session, licenseKey, productName, serverId, active,
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

	protected LicenseKey getByPN_SI_A_PrevAndNext(
		Session session, LicenseKey licenseKey, String productName,
		String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindProductName = false;

		if (productName.isEmpty()) {
			sb.append(_FINDER_COLUMN_PN_SI_A_PRODUCTNAME_3);
		}
		else {
			bindProductName = true;

			sb.append(_FINDER_COLUMN_PN_SI_A_PRODUCTNAME_2);
		}

		boolean bindServerId = false;

		if (serverId.isEmpty()) {
			sb.append(_FINDER_COLUMN_PN_SI_A_SERVERID_3);
		}
		else {
			bindServerId = true;

			sb.append(_FINDER_COLUMN_PN_SI_A_SERVERID_2);
		}

		sb.append(_FINDER_COLUMN_PN_SI_A_ACTIVE_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindProductName) {
			queryPos.add(productName);
		}

		if (bindServerId) {
			queryPos.add(serverId);
		}

		queryPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where productName = &#63; and serverId = &#63; and active = &#63; from the database.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 */
	@Override
	public void removeByPN_SI_A(
		String productName, String serverId, boolean active) {

		for (LicenseKey licenseKey :
				findByPN_SI_A(
					productName, serverId, active, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where productName = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param productName the product name
	 * @param serverId the server ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByPN_SI_A(
		String productName, String serverId, boolean active) {

		productName = Objects.toString(productName, "");
		serverId = Objects.toString(serverId, "");

		FinderPath finderPath = _finderPathCountByPN_SI_A;

		Object[] finderArgs = new Object[] {productName, serverId, active};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindProductName = false;

			if (productName.isEmpty()) {
				sb.append(_FINDER_COLUMN_PN_SI_A_PRODUCTNAME_3);
			}
			else {
				bindProductName = true;

				sb.append(_FINDER_COLUMN_PN_SI_A_PRODUCTNAME_2);
			}

			boolean bindServerId = false;

			if (serverId.isEmpty()) {
				sb.append(_FINDER_COLUMN_PN_SI_A_SERVERID_3);
			}
			else {
				bindServerId = true;

				sb.append(_FINDER_COLUMN_PN_SI_A_SERVERID_2);
			}

			sb.append(_FINDER_COLUMN_PN_SI_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductName) {
					queryPos.add(productName);
				}

				if (bindServerId) {
					queryPos.add(serverId);
				}

				queryPos.add(active);

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

	private static final String _FINDER_COLUMN_PN_SI_A_PRODUCTNAME_2 =
		"licenseKey.productName = ? AND ";

	private static final String _FINDER_COLUMN_PN_SI_A_PRODUCTNAME_3 =
		"(licenseKey.productName IS NULL OR licenseKey.productName = '') AND ";

	private static final String _FINDER_COLUMN_PN_SI_A_SERVERID_2 =
		"licenseKey.serverId = ? AND ";

	private static final String _FINDER_COLUMN_PN_SI_A_SERVERID_3 =
		"(licenseKey.serverId IS NULL OR licenseKey.serverId = '') AND ";

	private static final String _FINDER_COLUMN_PN_SI_A_ACTIVE_2 =
		"licenseKey.active = ?";

	private FinderPath _finderPathWithPaginationFindByARLU_PI_SI_A;
	private FinderPath _finderPathWithoutPaginationFindByARLU_PI_SI_A;
	private FinderPath _finderPathCountByARLU_PI_SI_A;

	/**
	 * Returns all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @return the matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active) {

		return findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end) {

		return findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {

		return findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license keies
	 */
	@Override
	public List<LicenseKey> findByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean useFinderCache) {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");
		productId = Objects.toString(productId, "");
		serverId = Objects.toString(serverId, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByARLU_PI_SI_A;
				finderArgs = new Object[] {
					assetReceiptLicenseUuid, productId, serverId, active
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByARLU_PI_SI_A;
			finderArgs = new Object[] {
				assetReceiptLicenseUuid, productId, serverId, active, start,
				end, orderByComparator
			};
		}

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseKey licenseKey : list) {
					if (!assetReceiptLicenseUuid.equals(
							licenseKey.getAssetReceiptLicenseUuid()) ||
						!productId.equals(licenseKey.getProductId()) ||
						!serverId.equals(licenseKey.getServerId()) ||
						(active != licenseKey.isActive())) {

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

			sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

			boolean bindAssetReceiptLicenseUuid = false;

			if (assetReceiptLicenseUuid.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_3);
			}
			else {
				bindAssetReceiptLicenseUuid = true;

				sb.append(
					_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_2);
			}

			boolean bindProductId = false;

			if (productId.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_2);
			}

			boolean bindServerId = false;

			if (serverId.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_3);
			}
			else {
				bindServerId = true;

				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_2);
			}

			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAssetReceiptLicenseUuid) {
					queryPos.add(assetReceiptLicenseUuid);
				}

				if (bindProductId) {
					queryPos.add(productId);
				}

				if (bindServerId) {
					queryPos.add(serverId);
				}

				queryPos.add(active);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByARLU_PI_SI_A_First(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByARLU_PI_SI_A_First(
			assetReceiptLicenseUuid, productId, serverId, active,
			orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("assetReceiptLicenseUuid=");
		sb.append(assetReceiptLicenseUuid);

		sb.append(", productId=");
		sb.append(productId);

		sb.append(", serverId=");
		sb.append(serverId);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByARLU_PI_SI_A_First(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {

		List<LicenseKey> list = findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key
	 * @throws NoSuchLicenseKeyException if a matching license key could not be found
	 */
	@Override
	public LicenseKey findByARLU_PI_SI_A_Last(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByARLU_PI_SI_A_Last(
			assetReceiptLicenseUuid, productId, serverId, active,
			orderByComparator);

		if (licenseKey != null) {
			return licenseKey;
		}

		StringBundler sb = new StringBundler(10);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("assetReceiptLicenseUuid=");
		sb.append(assetReceiptLicenseUuid);

		sb.append(", productId=");
		sb.append(productId);

		sb.append(", serverId=");
		sb.append(serverId);

		sb.append(", active=");
		sb.append(active);

		sb.append("}");

		throw new NoSuchLicenseKeyException(sb.toString());
	}

	/**
	 * Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license key, or <code>null</code> if a matching license key could not be found
	 */
	@Override
	public LicenseKey fetchByARLU_PI_SI_A_Last(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {

		int count = countByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active);

		if (count == 0) {
			return null;
		}

		List<LicenseKey> list = findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, count - 1,
			count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param licenseKeyId the primary key of the current license key
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey[] findByARLU_PI_SI_A_PrevAndNext(
			long licenseKeyId, String assetReceiptLicenseUuid, String productId,
			String serverId, boolean active,
			OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");
		productId = Objects.toString(productId, "");
		serverId = Objects.toString(serverId, "");

		LicenseKey licenseKey = findByPrimaryKey(licenseKeyId);

		Session session = null;

		try {
			session = openSession();

			LicenseKey[] array = new LicenseKeyImpl[3];

			array[0] = getByARLU_PI_SI_A_PrevAndNext(
				session, licenseKey, assetReceiptLicenseUuid, productId,
				serverId, active, orderByComparator, true);

			array[1] = licenseKey;

			array[2] = getByARLU_PI_SI_A_PrevAndNext(
				session, licenseKey, assetReceiptLicenseUuid, productId,
				serverId, active, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseKey getByARLU_PI_SI_A_PrevAndNext(
		Session session, LicenseKey licenseKey, String assetReceiptLicenseUuid,
		String productId, String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				7 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(6);
		}

		sb.append(_SQL_SELECT_LICENSEKEY_WHERE);

		boolean bindAssetReceiptLicenseUuid = false;

		if (assetReceiptLicenseUuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_3);
		}
		else {
			bindAssetReceiptLicenseUuid = true;

			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_2);
		}

		boolean bindProductId = false;

		if (productId.isEmpty()) {
			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_3);
		}
		else {
			bindProductId = true;

			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_2);
		}

		boolean bindServerId = false;

		if (serverId.isEmpty()) {
			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_3);
		}
		else {
			bindServerId = true;

			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_2);
		}

		sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_ACTIVE_2);

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
			sb.append(LicenseKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindAssetReceiptLicenseUuid) {
			queryPos.add(assetReceiptLicenseUuid);
		}

		if (bindProductId) {
			queryPos.add(productId);
		}

		if (bindServerId) {
			queryPos.add(serverId);
		}

		queryPos.add(active);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseKey)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseKey> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63; from the database.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 */
	@Override
	public void removeByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active) {

		for (LicenseKey licenseKey :
				findByARLU_PI_SI_A(
					assetReceiptLicenseUuid, productId, serverId, active,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	 *
	 * @param assetReceiptLicenseUuid the asset receipt license uuid
	 * @param productId the product ID
	 * @param serverId the server ID
	 * @param active the active
	 * @return the number of matching license keies
	 */
	@Override
	public int countByARLU_PI_SI_A(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active) {

		assetReceiptLicenseUuid = Objects.toString(assetReceiptLicenseUuid, "");
		productId = Objects.toString(productId, "");
		serverId = Objects.toString(serverId, "");

		FinderPath finderPath = _finderPathCountByARLU_PI_SI_A;

		Object[] finderArgs = new Object[] {
			assetReceiptLicenseUuid, productId, serverId, active
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_COUNT_LICENSEKEY_WHERE);

			boolean bindAssetReceiptLicenseUuid = false;

			if (assetReceiptLicenseUuid.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_3);
			}
			else {
				bindAssetReceiptLicenseUuid = true;

				sb.append(
					_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_2);
			}

			boolean bindProductId = false;

			if (productId.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_3);
			}
			else {
				bindProductId = true;

				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_2);
			}

			boolean bindServerId = false;

			if (serverId.isEmpty()) {
				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_3);
			}
			else {
				bindServerId = true;

				sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_2);
			}

			sb.append(_FINDER_COLUMN_ARLU_PI_SI_A_ACTIVE_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindAssetReceiptLicenseUuid) {
					queryPos.add(assetReceiptLicenseUuid);
				}

				if (bindProductId) {
					queryPos.add(productId);
				}

				if (bindServerId) {
					queryPos.add(serverId);
				}

				queryPos.add(active);

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
		_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_2 =
			"licenseKey.assetReceiptLicenseUuid = ? AND ";

	private static final String
		_FINDER_COLUMN_ARLU_PI_SI_A_ASSETRECEIPTLICENSEUUID_3 =
			"(licenseKey.assetReceiptLicenseUuid IS NULL OR licenseKey.assetReceiptLicenseUuid = '') AND ";

	private static final String _FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_2 =
		"licenseKey.productId = ? AND ";

	private static final String _FINDER_COLUMN_ARLU_PI_SI_A_PRODUCTID_3 =
		"(licenseKey.productId IS NULL OR licenseKey.productId = '') AND ";

	private static final String _FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_2 =
		"licenseKey.serverId = ? AND ";

	private static final String _FINDER_COLUMN_ARLU_PI_SI_A_SERVERID_3 =
		"(licenseKey.serverId IS NULL OR licenseKey.serverId = '') AND ";

	private static final String _FINDER_COLUMN_ARLU_PI_SI_A_ACTIVE_2 =
		"licenseKey.active = ?";

	public LicenseKeyPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("key", "key_");
		dbColumnNames.put("active", "active_");

		setDBColumnNames(dbColumnNames);

		setModelClass(LicenseKey.class);

		setModelImplClass(LicenseKeyImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the license key in the entity cache if it is enabled.
	 *
	 * @param licenseKey the license key
	 */
	@Override
	public void cacheResult(LicenseKey licenseKey) {
		entityCache.putResult(
			entityCacheEnabled, LicenseKeyImpl.class,
			licenseKey.getPrimaryKey(), licenseKey);

		licenseKey.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the license keies in the entity cache if it is enabled.
	 *
	 * @param licenseKeies the license keies
	 */
	@Override
	public void cacheResult(List<LicenseKey> licenseKeies) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (licenseKeies.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (LicenseKey licenseKey : licenseKeies) {
			if (entityCache.getResult(
					entityCacheEnabled, LicenseKeyImpl.class,
					licenseKey.getPrimaryKey()) == null) {

				cacheResult(licenseKey);
			}
			else {
				licenseKey.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all license keies.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LicenseKeyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the license key.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LicenseKey licenseKey) {
		entityCache.removeResult(
			entityCacheEnabled, LicenseKeyImpl.class,
			licenseKey.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<LicenseKey> licenseKeies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LicenseKey licenseKey : licenseKeies) {
			entityCache.removeResult(
				entityCacheEnabled, LicenseKeyImpl.class,
				licenseKey.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, LicenseKeyImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new license key with the primary key. Does not add the license key to the database.
	 *
	 * @param licenseKeyId the primary key for the new license key
	 * @return the new license key
	 */
	@Override
	public LicenseKey create(long licenseKeyId) {
		LicenseKey licenseKey = new LicenseKeyImpl();

		licenseKey.setNew(true);
		licenseKey.setPrimaryKey(licenseKeyId);

		String uuid = PortalUUIDUtil.generate();

		licenseKey.setUuid(uuid);

		licenseKey.setCompanyId(CompanyThreadLocal.getCompanyId());

		return licenseKey;
	}

	/**
	 * Removes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key that was removed
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey remove(long licenseKeyId)
		throws NoSuchLicenseKeyException {

		return remove((Serializable)licenseKeyId);
	}

	/**
	 * Removes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the license key
	 * @return the license key that was removed
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey remove(Serializable primaryKey)
		throws NoSuchLicenseKeyException {

		Session session = null;

		try {
			session = openSession();

			LicenseKey licenseKey = (LicenseKey)session.get(
				LicenseKeyImpl.class, primaryKey);

			if (licenseKey == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLicenseKeyException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(licenseKey);
		}
		catch (NoSuchLicenseKeyException noSuchEntityException) {
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
	protected LicenseKey removeImpl(LicenseKey licenseKey) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(licenseKey)) {
				licenseKey = (LicenseKey)session.get(
					LicenseKeyImpl.class, licenseKey.getPrimaryKeyObj());
			}

			if (licenseKey != null) {
				session.delete(licenseKey);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (licenseKey != null) {
			clearCache(licenseKey);
		}

		return licenseKey;
	}

	@Override
	public LicenseKey updateImpl(LicenseKey licenseKey) {
		boolean isNew = licenseKey.isNew();

		if (!(licenseKey instanceof LicenseKeyModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(licenseKey.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(licenseKey);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in licenseKey proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LicenseKey implementation " +
					licenseKey.getClass());
		}

		LicenseKeyModelImpl licenseKeyModelImpl =
			(LicenseKeyModelImpl)licenseKey;

		if (Validator.isNull(licenseKey.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			licenseKey.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (licenseKey.getCreateDate() == null)) {
			if (serviceContext == null) {
				licenseKey.setCreateDate(date);
			}
			else {
				licenseKey.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!licenseKeyModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				licenseKey.setModifiedDate(date);
			}
			else {
				licenseKey.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(licenseKey);

				licenseKey.setNew(false);
			}
			else {
				licenseKey = (LicenseKey)session.merge(licenseKey);
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
			Object[] args = new Object[] {licenseKeyModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				licenseKeyModelImpl.getUuid(),
				licenseKeyModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {
				licenseKeyModelImpl.getAssetReceiptLicenseUuid(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByARLU_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByARLU_A, args);

			args = new Object[] {
				licenseKeyModelImpl.getProductPurchaseKey(),
				licenseKeyModelImpl.getClusterId()
			};

			finderCache.removeResult(_finderPathCountByPPK_CI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPPK_CI, args);

			args = new Object[] {
				licenseKeyModelImpl.getProductId(),
				licenseKeyModelImpl.getServerId()
			};

			finderCache.removeResult(_finderPathCountByPI_SI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPI_SI, args);

			args = new Object[] {
				licenseKeyModelImpl.getAssetReceiptLicenseUuid(),
				licenseKeyModelImpl.isComplimentary(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByARLU_C_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByARLU_C_A, args);

			args = new Object[] {
				licenseKeyModelImpl.getProductName(),
				licenseKeyModelImpl.getServerId(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByPN_SI_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByPN_SI_A, args);

			args = new Object[] {
				licenseKeyModelImpl.getAssetReceiptLicenseUuid(),
				licenseKeyModelImpl.getProductId(),
				licenseKeyModelImpl.getServerId(),
				licenseKeyModelImpl.isActive()
			};

			finderCache.removeResult(_finderPathCountByARLU_PI_SI_A, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByARLU_PI_SI_A, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {licenseKeyModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalUuid(),
					licenseKeyModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					licenseKeyModelImpl.getUuid(),
					licenseKeyModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByARLU_A.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalAssetReceiptLicenseUuid(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByARLU_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByARLU_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getAssetReceiptLicenseUuid(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByARLU_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByARLU_A, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPPK_CI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalProductPurchaseKey(),
					licenseKeyModelImpl.getOriginalClusterId()
				};

				finderCache.removeResult(_finderPathCountByPPK_CI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPPK_CI, args);

				args = new Object[] {
					licenseKeyModelImpl.getProductPurchaseKey(),
					licenseKeyModelImpl.getClusterId()
				};

				finderCache.removeResult(_finderPathCountByPPK_CI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPPK_CI, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPI_SI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalProductId(),
					licenseKeyModelImpl.getOriginalServerId()
				};

				finderCache.removeResult(_finderPathCountByPI_SI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPI_SI, args);

				args = new Object[] {
					licenseKeyModelImpl.getProductId(),
					licenseKeyModelImpl.getServerId()
				};

				finderCache.removeResult(_finderPathCountByPI_SI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPI_SI, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByARLU_C_A.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalAssetReceiptLicenseUuid(),
					licenseKeyModelImpl.getOriginalComplimentary(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByARLU_C_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByARLU_C_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getAssetReceiptLicenseUuid(),
					licenseKeyModelImpl.isComplimentary(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByARLU_C_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByARLU_C_A, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByPN_SI_A.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalProductName(),
					licenseKeyModelImpl.getOriginalServerId(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByPN_SI_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPN_SI_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getProductName(),
					licenseKeyModelImpl.getServerId(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByPN_SI_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByPN_SI_A, args);
			}

			if ((licenseKeyModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByARLU_PI_SI_A.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseKeyModelImpl.getOriginalAssetReceiptLicenseUuid(),
					licenseKeyModelImpl.getOriginalProductId(),
					licenseKeyModelImpl.getOriginalServerId(),
					licenseKeyModelImpl.getOriginalActive()
				};

				finderCache.removeResult(_finderPathCountByARLU_PI_SI_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByARLU_PI_SI_A, args);

				args = new Object[] {
					licenseKeyModelImpl.getAssetReceiptLicenseUuid(),
					licenseKeyModelImpl.getProductId(),
					licenseKeyModelImpl.getServerId(),
					licenseKeyModelImpl.isActive()
				};

				finderCache.removeResult(_finderPathCountByARLU_PI_SI_A, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByARLU_PI_SI_A, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, LicenseKeyImpl.class,
			licenseKey.getPrimaryKey(), licenseKey, false);

		licenseKey.resetOriginalValues();

		return licenseKey;
	}

	/**
	 * Returns the license key with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the license key
	 * @return the license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLicenseKeyException {

		LicenseKey licenseKey = fetchByPrimaryKey(primaryKey);

		if (licenseKey == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLicenseKeyException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return licenseKey;
	}

	/**
	 * Returns the license key with the primary key or throws a <code>NoSuchLicenseKeyException</code> if it could not be found.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key
	 * @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey findByPrimaryKey(long licenseKeyId)
		throws NoSuchLicenseKeyException {

		return findByPrimaryKey((Serializable)licenseKeyId);
	}

	/**
	 * Returns the license key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseKeyId the primary key of the license key
	 * @return the license key, or <code>null</code> if a license key with the primary key could not be found
	 */
	@Override
	public LicenseKey fetchByPrimaryKey(long licenseKeyId) {
		return fetchByPrimaryKey((Serializable)licenseKeyId);
	}

	/**
	 * Returns all the license keies.
	 *
	 * @return the license keies
	 */
	@Override
	public List<LicenseKey> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @return the range of license keies
	 */
	@Override
	public List<LicenseKey> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of license keies
	 */
	@Override
	public List<LicenseKey> findAll(
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license keies
	 * @param end the upper bound of the range of license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of license keies
	 */
	@Override
	public List<LicenseKey> findAll(
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
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

		List<LicenseKey> list = null;

		if (useFinderCache) {
			list = (List<LicenseKey>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_LICENSEKEY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_LICENSEKEY;

				sql = sql.concat(LicenseKeyModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<LicenseKey>)QueryUtil.list(
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
	 * Removes all the license keies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LicenseKey licenseKey : findAll()) {
			remove(licenseKey);
		}
	}

	/**
	 * Returns the number of license keies.
	 *
	 * @return the number of license keies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_LICENSEKEY);

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
		return "licenseKeyId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LICENSEKEY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LicenseKeyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the license key persistence.
	 */
	@Activate
	public void activate() {
		LicenseKeyModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		LicenseKeyModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			LicenseKeyModelImpl.UUID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			LicenseKeyModelImpl.UUID_COLUMN_BITMASK |
			LicenseKeyModelImpl.COMPANYID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByARLU_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLU_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByARLU_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARLU_A",
			new String[] {String.class.getName(), Boolean.class.getName()},
			LicenseKeyModelImpl.ASSETRECEIPTLICENSEUUID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByARLU_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLU_A",
			new String[] {String.class.getName(), Boolean.class.getName()});

		_finderPathWithPaginationFindByPPK_CI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPPK_CI",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPPK_CI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPPK_CI",
			new String[] {String.class.getName(), Long.class.getName()},
			LicenseKeyModelImpl.PRODUCTPURCHASEKEY_COLUMN_BITMASK |
			LicenseKeyModelImpl.CLUSTERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByPPK_CI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPPK_CI",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByPI_SI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPI_SI",
			new String[] {
				String.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPI_SI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPI_SI",
			new String[] {String.class.getName(), String.class.getName()},
			LicenseKeyModelImpl.PRODUCTID_COLUMN_BITMASK |
			LicenseKeyModelImpl.SERVERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByPI_SI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPI_SI",
			new String[] {String.class.getName(), String.class.getName()});

		_finderPathWithPaginationFindByARLU_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLU_C_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByARLU_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARLU_C_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.ASSETRECEIPTLICENSEUUID_COLUMN_BITMASK |
			LicenseKeyModelImpl.COMPLIMENTARY_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByARLU_C_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLU_C_A",
			new String[] {
				String.class.getName(), Boolean.class.getName(),
				Boolean.class.getName()
			});

		_finderPathWithPaginationFindByPN_SI_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByPN_SI_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName()
			},
			LicenseKeyModelImpl.PRODUCTNAME_COLUMN_BITMASK |
			LicenseKeyModelImpl.SERVERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByPN_SI_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPN_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				Boolean.class.getName()
			});

		_finderPathWithPaginationFindByARLU_PI_SI_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByARLU_PI_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByARLU_PI_SI_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByARLU_PI_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName()
			},
			LicenseKeyModelImpl.ASSETRECEIPTLICENSEUUID_COLUMN_BITMASK |
			LicenseKeyModelImpl.PRODUCTID_COLUMN_BITMASK |
			LicenseKeyModelImpl.SERVERID_COLUMN_BITMASK |
			LicenseKeyModelImpl.ACTIVE_COLUMN_BITMASK);

		_finderPathCountByARLU_PI_SI_A = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByARLU_PI_SI_A",
			new String[] {
				String.class.getName(), String.class.getName(),
				String.class.getName(), Boolean.class.getName()
			});

		LicenseKeyUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		LicenseKeyUtil.setPersistence(null);

		entityCache.removeCache(LicenseKeyImpl.class.getName());

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
				"value.object.column.bitmask.enabled.com.liferay.osb.provisioning.license.model.LicenseKey"),
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

	private static final String _SQL_SELECT_LICENSEKEY =
		"SELECT licenseKey FROM LicenseKey licenseKey";

	private static final String _SQL_SELECT_LICENSEKEY_WHERE =
		"SELECT licenseKey FROM LicenseKey licenseKey WHERE ";

	private static final String _SQL_COUNT_LICENSEKEY =
		"SELECT COUNT(licenseKey) FROM LicenseKey licenseKey";

	private static final String _SQL_COUNT_LICENSEKEY_WHERE =
		"SELECT COUNT(licenseKey) FROM LicenseKey licenseKey WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "licenseKey.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LicenseKey exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LicenseKey exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseKeyPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "key", "active"});

}