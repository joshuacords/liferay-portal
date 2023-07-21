/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.service.persistence.impl;

import com.liferay.osb.provisioning.license.exception.NoSuchLicenseEntryException;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.license.model.impl.LicenseEntryImpl;
import com.liferay.osb.provisioning.license.model.impl.LicenseEntryModelImpl;
import com.liferay.osb.provisioning.license.service.persistence.LicenseEntryPersistence;
import com.liferay.osb.provisioning.license.service.persistence.LicenseEntryUtil;
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
 * The persistence implementation for the license entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = LicenseEntryPersistence.class)
public class LicenseEntryPersistenceImpl
	extends BasePersistenceImpl<LicenseEntry>
	implements LicenseEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LicenseEntryUtil</code> to access the license entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LicenseEntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByProductKey;
	private FinderPath _finderPathWithoutPaginationFindByProductKey;
	private FinderPath _finderPathCountByProductKey;

	/**
	 * Returns all the license entries where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @return the matching license entries
	 */
	@Override
	public List<LicenseEntry> findByProductKey(String productKey) {
		return findByProductKey(
			productKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license entries where productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productKey the product key
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByProductKey(
		String productKey, int start, int end) {

		return findByProductKey(productKey, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license entries where productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productKey the product key
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByProductKey(
		String productKey, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator) {

		return findByProductKey(
			productKey, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license entries where productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productKey the product key
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByProductKey(
		String productKey, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator,
		boolean useFinderCache) {

		productKey = Objects.toString(productKey, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByProductKey;
				finderArgs = new Object[] {productKey};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByProductKey;
			finderArgs = new Object[] {
				productKey, start, end, orderByComparator
			};
		}

		List<LicenseEntry> list = null;

		if (useFinderCache) {
			list = (List<LicenseEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseEntry licenseEntry : list) {
					if (!productKey.equals(licenseEntry.getProductKey())) {
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

			sb.append(_SQL_SELECT_LICENSEENTRY_WHERE);

			boolean bindProductKey = false;

			if (productKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PRODUCTKEY_PRODUCTKEY_3);
			}
			else {
				bindProductKey = true;

				sb.append(_FINDER_COLUMN_PRODUCTKEY_PRODUCTKEY_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductKey) {
					queryPos.add(productKey);
				}

				list = (List<LicenseEntry>)QueryUtil.list(
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
	 * Returns the first license entry in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry findByProductKey_First(
			String productKey,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {

		LicenseEntry licenseEntry = fetchByProductKey_First(
			productKey, orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productKey=");
		sb.append(productKey);

		sb.append("}");

		throw new NoSuchLicenseEntryException(sb.toString());
	}

	/**
	 * Returns the first license entry in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry fetchByProductKey_First(
		String productKey, OrderByComparator<LicenseEntry> orderByComparator) {

		List<LicenseEntry> list = findByProductKey(
			productKey, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license entry in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry findByProductKey_Last(
			String productKey,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {

		LicenseEntry licenseEntry = fetchByProductKey_Last(
			productKey, orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productKey=");
		sb.append(productKey);

		sb.append("}");

		throw new NoSuchLicenseEntryException(sb.toString());
	}

	/**
	 * Returns the last license entry in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry fetchByProductKey_Last(
		String productKey, OrderByComparator<LicenseEntry> orderByComparator) {

		int count = countByProductKey(productKey);

		if (count == 0) {
			return null;
		}

		List<LicenseEntry> list = findByProductKey(
			productKey, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where productKey = &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry[] findByProductKey_PrevAndNext(
			long licenseEntryId, String productKey,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {

		productKey = Objects.toString(productKey, "");

		LicenseEntry licenseEntry = findByPrimaryKey(licenseEntryId);

		Session session = null;

		try {
			session = openSession();

			LicenseEntry[] array = new LicenseEntryImpl[3];

			array[0] = getByProductKey_PrevAndNext(
				session, licenseEntry, productKey, orderByComparator, true);

			array[1] = licenseEntry;

			array[2] = getByProductKey_PrevAndNext(
				session, licenseEntry, productKey, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseEntry getByProductKey_PrevAndNext(
		Session session, LicenseEntry licenseEntry, String productKey,
		OrderByComparator<LicenseEntry> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_LICENSEENTRY_WHERE);

		boolean bindProductKey = false;

		if (productKey.isEmpty()) {
			sb.append(_FINDER_COLUMN_PRODUCTKEY_PRODUCTKEY_3);
		}
		else {
			bindProductKey = true;

			sb.append(_FINDER_COLUMN_PRODUCTKEY_PRODUCTKEY_2);
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
			sb.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindProductKey) {
			queryPos.add(productKey);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license entries where productKey = &#63; from the database.
	 *
	 * @param productKey the product key
	 */
	@Override
	public void removeByProductKey(String productKey) {
		for (LicenseEntry licenseEntry :
				findByProductKey(
					productKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseEntry);
		}
	}

	/**
	 * Returns the number of license entries where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @return the number of matching license entries
	 */
	@Override
	public int countByProductKey(String productKey) {
		productKey = Objects.toString(productKey, "");

		FinderPath finderPath = _finderPathCountByProductKey;

		Object[] finderArgs = new Object[] {productKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LICENSEENTRY_WHERE);

			boolean bindProductKey = false;

			if (productKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PRODUCTKEY_PRODUCTKEY_3);
			}
			else {
				bindProductKey = true;

				sb.append(_FINDER_COLUMN_PRODUCTKEY_PRODUCTKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductKey) {
					queryPos.add(productKey);
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

	private static final String _FINDER_COLUMN_PRODUCTKEY_PRODUCTKEY_2 =
		"licenseEntry.productKey = ?";

	private static final String _FINDER_COLUMN_PRODUCTKEY_PRODUCTKEY_3 =
		"(licenseEntry.productKey IS NULL OR licenseEntry.productKey = '')";

	private FinderPath _finderPathWithPaginationFindByLikeName;
	private FinderPath _finderPathWithPaginationCountByLikeName;

	/**
	 * Returns all the license entries where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the matching license entries
	 */
	@Override
	public List<LicenseEntry> findByLikeName(String name) {
		return findByLikeName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license entries where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByLikeName(String name, int start, int end) {
		return findByLikeName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license entries where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByLikeName(
		String name, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator) {

		return findByLikeName(name, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license entries where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByLikeName(
		String name, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator,
		boolean useFinderCache) {

		name = Objects.toString(name, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = _finderPathWithPaginationFindByLikeName;
		finderArgs = new Object[] {name, start, end, orderByComparator};

		List<LicenseEntry> list = null;

		if (useFinderCache) {
			list = (List<LicenseEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseEntry licenseEntry : list) {
					if (!StringUtil.wildcardMatches(
							licenseEntry.getName(), name, '_', '%', '\\',
							true)) {

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

			sb.append(_SQL_SELECT_LICENSEENTRY_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_LIKENAME_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_LIKENAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindName) {
					queryPos.add(name);
				}

				list = (List<LicenseEntry>)QueryUtil.list(
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
	 * Returns the first license entry in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry findByLikeName_First(
			String name, OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {

		LicenseEntry licenseEntry = fetchByLikeName_First(
			name, orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("nameLIKE");
		sb.append(name);

		sb.append("}");

		throw new NoSuchLicenseEntryException(sb.toString());
	}

	/**
	 * Returns the first license entry in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry fetchByLikeName_First(
		String name, OrderByComparator<LicenseEntry> orderByComparator) {

		List<LicenseEntry> list = findByLikeName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license entry in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry findByLikeName_Last(
			String name, OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {

		LicenseEntry licenseEntry = fetchByLikeName_Last(
			name, orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("nameLIKE");
		sb.append(name);

		sb.append("}");

		throw new NoSuchLicenseEntryException(sb.toString());
	}

	/**
	 * Returns the last license entry in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry fetchByLikeName_Last(
		String name, OrderByComparator<LicenseEntry> orderByComparator) {

		int count = countByLikeName(name);

		if (count == 0) {
			return null;
		}

		List<LicenseEntry> list = findByLikeName(
			name, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where name LIKE &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry[] findByLikeName_PrevAndNext(
			long licenseEntryId, String name,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {

		name = Objects.toString(name, "");

		LicenseEntry licenseEntry = findByPrimaryKey(licenseEntryId);

		Session session = null;

		try {
			session = openSession();

			LicenseEntry[] array = new LicenseEntryImpl[3];

			array[0] = getByLikeName_PrevAndNext(
				session, licenseEntry, name, orderByComparator, true);

			array[1] = licenseEntry;

			array[2] = getByLikeName_PrevAndNext(
				session, licenseEntry, name, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseEntry getByLikeName_PrevAndNext(
		Session session, LicenseEntry licenseEntry, String name,
		OrderByComparator<LicenseEntry> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_LICENSEENTRY_WHERE);

		boolean bindName = false;

		if (name.isEmpty()) {
			sb.append(_FINDER_COLUMN_LIKENAME_NAME_3);
		}
		else {
			bindName = true;

			sb.append(_FINDER_COLUMN_LIKENAME_NAME_2);
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
			sb.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindName) {
			queryPos.add(name);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license entries where name LIKE &#63; from the database.
	 *
	 * @param name the name
	 */
	@Override
	public void removeByLikeName(String name) {
		for (LicenseEntry licenseEntry :
				findByLikeName(
					name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseEntry);
		}
	}

	/**
	 * Returns the number of license entries where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the number of matching license entries
	 */
	@Override
	public int countByLikeName(String name) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathWithPaginationCountByLikeName;

		Object[] finderArgs = new Object[] {name};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LICENSEENTRY_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				sb.append(_FINDER_COLUMN_LIKENAME_NAME_3);
			}
			else {
				bindName = true;

				sb.append(_FINDER_COLUMN_LIKENAME_NAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

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

	private static final String _FINDER_COLUMN_LIKENAME_NAME_2 =
		"licenseEntry.name LIKE ?";

	private static final String _FINDER_COLUMN_LIKENAME_NAME_3 =
		"(licenseEntry.name IS NULL OR licenseEntry.name LIKE '')";

	private FinderPath _finderPathWithPaginationFindByType;
	private FinderPath _finderPathWithoutPaginationFindByType;
	private FinderPath _finderPathCountByType;

	/**
	 * Returns all the license entries where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching license entries
	 */
	@Override
	public List<LicenseEntry> findByType(String type) {
		return findByType(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license entries where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByType(String type, int start, int end) {
		return findByType(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the license entries where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByType(
		String type, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator) {

		return findByType(type, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license entries where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license entries
	 */
	@Override
	public List<LicenseEntry> findByType(
		String type, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator,
		boolean useFinderCache) {

		type = Objects.toString(type, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByType;
				finderArgs = new Object[] {type};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByType;
			finderArgs = new Object[] {type, start, end, orderByComparator};
		}

		List<LicenseEntry> list = null;

		if (useFinderCache) {
			list = (List<LicenseEntry>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (LicenseEntry licenseEntry : list) {
					if (!type.equals(licenseEntry.getType())) {
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

			sb.append(_SQL_SELECT_LICENSEENTRY_WHERE);

			boolean bindType = false;

			if (type.isEmpty()) {
				sb.append(_FINDER_COLUMN_TYPE_TYPE_3);
			}
			else {
				bindType = true;

				sb.append(_FINDER_COLUMN_TYPE_TYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindType) {
					queryPos.add(type);
				}

				list = (List<LicenseEntry>)QueryUtil.list(
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
	 * Returns the first license entry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry findByType_First(
			String type, OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {

		LicenseEntry licenseEntry = fetchByType_First(type, orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchLicenseEntryException(sb.toString());
	}

	/**
	 * Returns the first license entry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry fetchByType_First(
		String type, OrderByComparator<LicenseEntry> orderByComparator) {

		List<LicenseEntry> list = findByType(type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last license entry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry findByType_Last(
			String type, OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {

		LicenseEntry licenseEntry = fetchByType_Last(type, orderByComparator);

		if (licenseEntry != null) {
			return licenseEntry;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("type=");
		sb.append(type);

		sb.append("}");

		throw new NoSuchLicenseEntryException(sb.toString());
	}

	/**
	 * Returns the last license entry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry fetchByType_Last(
		String type, OrderByComparator<LicenseEntry> orderByComparator) {

		int count = countByType(type);

		if (count == 0) {
			return null;
		}

		List<LicenseEntry> list = findByType(
			type, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where type = &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry[] findByType_PrevAndNext(
			long licenseEntryId, String type,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws NoSuchLicenseEntryException {

		type = Objects.toString(type, "");

		LicenseEntry licenseEntry = findByPrimaryKey(licenseEntryId);

		Session session = null;

		try {
			session = openSession();

			LicenseEntry[] array = new LicenseEntryImpl[3];

			array[0] = getByType_PrevAndNext(
				session, licenseEntry, type, orderByComparator, true);

			array[1] = licenseEntry;

			array[2] = getByType_PrevAndNext(
				session, licenseEntry, type, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected LicenseEntry getByType_PrevAndNext(
		Session session, LicenseEntry licenseEntry, String type,
		OrderByComparator<LicenseEntry> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_LICENSEENTRY_WHERE);

		boolean bindType = false;

		if (type.isEmpty()) {
			sb.append(_FINDER_COLUMN_TYPE_TYPE_3);
		}
		else {
			bindType = true;

			sb.append(_FINDER_COLUMN_TYPE_TYPE_2);
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
			sb.append(LicenseEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindType) {
			queryPos.add(type);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(licenseEntry)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<LicenseEntry> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the license entries where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	@Override
	public void removeByType(String type) {
		for (LicenseEntry licenseEntry :
				findByType(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(licenseEntry);
		}
	}

	/**
	 * Returns the number of license entries where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching license entries
	 */
	@Override
	public int countByType(String type) {
		type = Objects.toString(type, "");

		FinderPath finderPath = _finderPathCountByType;

		Object[] finderArgs = new Object[] {type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_LICENSEENTRY_WHERE);

			boolean bindType = false;

			if (type.isEmpty()) {
				sb.append(_FINDER_COLUMN_TYPE_TYPE_3);
			}
			else {
				bindType = true;

				sb.append(_FINDER_COLUMN_TYPE_TYPE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindType) {
					queryPos.add(type);
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

	private static final String _FINDER_COLUMN_TYPE_TYPE_2 =
		"licenseEntry.type = ?";

	private static final String _FINDER_COLUMN_TYPE_TYPE_3 =
		"(licenseEntry.type IS NULL OR licenseEntry.type = '')";

	private FinderPath _finderPathFetchByPK_T;
	private FinderPath _finderPathCountByPK_T;

	/**
	 * Returns the license entry where productKey = &#63; and type = &#63; or throws a <code>NoSuchLicenseEntryException</code> if it could not be found.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @return the matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry findByPK_T(String productKey, String type)
		throws NoSuchLicenseEntryException {

		LicenseEntry licenseEntry = fetchByPK_T(productKey, type);

		if (licenseEntry == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("productKey=");
			sb.append(productKey);

			sb.append(", type=");
			sb.append(type);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchLicenseEntryException(sb.toString());
		}

		return licenseEntry;
	}

	/**
	 * Returns the license entry where productKey = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry fetchByPK_T(String productKey, String type) {
		return fetchByPK_T(productKey, type, true);
	}

	/**
	 * Returns the license entry where productKey = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	@Override
	public LicenseEntry fetchByPK_T(
		String productKey, String type, boolean useFinderCache) {

		productKey = Objects.toString(productKey, "");
		type = Objects.toString(type, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {productKey, type};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByPK_T, finderArgs, this);
		}

		if (result instanceof LicenseEntry) {
			LicenseEntry licenseEntry = (LicenseEntry)result;

			if (!Objects.equals(productKey, licenseEntry.getProductKey()) ||
				!Objects.equals(type, licenseEntry.getType())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_LICENSEENTRY_WHERE);

			boolean bindProductKey = false;

			if (productKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PK_T_PRODUCTKEY_3);
			}
			else {
				bindProductKey = true;

				sb.append(_FINDER_COLUMN_PK_T_PRODUCTKEY_2);
			}

			boolean bindType = false;

			if (type.isEmpty()) {
				sb.append(_FINDER_COLUMN_PK_T_TYPE_3);
			}
			else {
				bindType = true;

				sb.append(_FINDER_COLUMN_PK_T_TYPE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductKey) {
					queryPos.add(productKey);
				}

				if (bindType) {
					queryPos.add(type);
				}

				List<LicenseEntry> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByPK_T, finderArgs, list);
					}
				}
				else {
					LicenseEntry licenseEntry = list.get(0);

					result = licenseEntry;

					cacheResult(licenseEntry);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByPK_T, finderArgs);
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
			return (LicenseEntry)result;
		}
	}

	/**
	 * Removes the license entry where productKey = &#63; and type = &#63; from the database.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @return the license entry that was removed
	 */
	@Override
	public LicenseEntry removeByPK_T(String productKey, String type)
		throws NoSuchLicenseEntryException {

		LicenseEntry licenseEntry = findByPK_T(productKey, type);

		return remove(licenseEntry);
	}

	/**
	 * Returns the number of license entries where productKey = &#63; and type = &#63;.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @return the number of matching license entries
	 */
	@Override
	public int countByPK_T(String productKey, String type) {
		productKey = Objects.toString(productKey, "");
		type = Objects.toString(type, "");

		FinderPath finderPath = _finderPathCountByPK_T;

		Object[] finderArgs = new Object[] {productKey, type};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_LICENSEENTRY_WHERE);

			boolean bindProductKey = false;

			if (productKey.isEmpty()) {
				sb.append(_FINDER_COLUMN_PK_T_PRODUCTKEY_3);
			}
			else {
				bindProductKey = true;

				sb.append(_FINDER_COLUMN_PK_T_PRODUCTKEY_2);
			}

			boolean bindType = false;

			if (type.isEmpty()) {
				sb.append(_FINDER_COLUMN_PK_T_TYPE_3);
			}
			else {
				bindType = true;

				sb.append(_FINDER_COLUMN_PK_T_TYPE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductKey) {
					queryPos.add(productKey);
				}

				if (bindType) {
					queryPos.add(type);
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

	private static final String _FINDER_COLUMN_PK_T_PRODUCTKEY_2 =
		"licenseEntry.productKey = ? AND ";

	private static final String _FINDER_COLUMN_PK_T_PRODUCTKEY_3 =
		"(licenseEntry.productKey IS NULL OR licenseEntry.productKey = '') AND ";

	private static final String _FINDER_COLUMN_PK_T_TYPE_2 =
		"licenseEntry.type = ?";

	private static final String _FINDER_COLUMN_PK_T_TYPE_3 =
		"(licenseEntry.type IS NULL OR licenseEntry.type = '')";

	public LicenseEntryPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(LicenseEntry.class);

		setModelImplClass(LicenseEntryImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the license entry in the entity cache if it is enabled.
	 *
	 * @param licenseEntry the license entry
	 */
	@Override
	public void cacheResult(LicenseEntry licenseEntry) {
		entityCache.putResult(
			entityCacheEnabled, LicenseEntryImpl.class,
			licenseEntry.getPrimaryKey(), licenseEntry);

		finderCache.putResult(
			_finderPathFetchByPK_T,
			new Object[] {licenseEntry.getProductKey(), licenseEntry.getType()},
			licenseEntry);

		licenseEntry.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the license entries in the entity cache if it is enabled.
	 *
	 * @param licenseEntries the license entries
	 */
	@Override
	public void cacheResult(List<LicenseEntry> licenseEntries) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (licenseEntries.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (LicenseEntry licenseEntry : licenseEntries) {
			if (entityCache.getResult(
					entityCacheEnabled, LicenseEntryImpl.class,
					licenseEntry.getPrimaryKey()) == null) {

				cacheResult(licenseEntry);
			}
			else {
				licenseEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all license entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LicenseEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the license entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(LicenseEntry licenseEntry) {
		entityCache.removeResult(
			entityCacheEnabled, LicenseEntryImpl.class,
			licenseEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LicenseEntryModelImpl)licenseEntry, true);
	}

	@Override
	public void clearCache(List<LicenseEntry> licenseEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (LicenseEntry licenseEntry : licenseEntries) {
			entityCache.removeResult(
				entityCacheEnabled, LicenseEntryImpl.class,
				licenseEntry.getPrimaryKey());

			clearUniqueFindersCache((LicenseEntryModelImpl)licenseEntry, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, LicenseEntryImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		LicenseEntryModelImpl licenseEntryModelImpl) {

		Object[] args = new Object[] {
			licenseEntryModelImpl.getProductKey(),
			licenseEntryModelImpl.getType()
		};

		finderCache.putResult(
			_finderPathCountByPK_T, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByPK_T, args, licenseEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LicenseEntryModelImpl licenseEntryModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				licenseEntryModelImpl.getProductKey(),
				licenseEntryModelImpl.getType()
			};

			finderCache.removeResult(_finderPathCountByPK_T, args);
			finderCache.removeResult(_finderPathFetchByPK_T, args);
		}

		if ((licenseEntryModelImpl.getColumnBitmask() &
			 _finderPathFetchByPK_T.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				licenseEntryModelImpl.getOriginalProductKey(),
				licenseEntryModelImpl.getOriginalType()
			};

			finderCache.removeResult(_finderPathCountByPK_T, args);
			finderCache.removeResult(_finderPathFetchByPK_T, args);
		}
	}

	/**
	 * Creates a new license entry with the primary key. Does not add the license entry to the database.
	 *
	 * @param licenseEntryId the primary key for the new license entry
	 * @return the new license entry
	 */
	@Override
	public LicenseEntry create(long licenseEntryId) {
		LicenseEntry licenseEntry = new LicenseEntryImpl();

		licenseEntry.setNew(true);
		licenseEntry.setPrimaryKey(licenseEntryId);

		return licenseEntry;
	}

	/**
	 * Removes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry that was removed
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry remove(long licenseEntryId)
		throws NoSuchLicenseEntryException {

		return remove((Serializable)licenseEntryId);
	}

	/**
	 * Removes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the license entry
	 * @return the license entry that was removed
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry remove(Serializable primaryKey)
		throws NoSuchLicenseEntryException {

		Session session = null;

		try {
			session = openSession();

			LicenseEntry licenseEntry = (LicenseEntry)session.get(
				LicenseEntryImpl.class, primaryKey);

			if (licenseEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLicenseEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(licenseEntry);
		}
		catch (NoSuchLicenseEntryException noSuchEntityException) {
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
	protected LicenseEntry removeImpl(LicenseEntry licenseEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(licenseEntry)) {
				licenseEntry = (LicenseEntry)session.get(
					LicenseEntryImpl.class, licenseEntry.getPrimaryKeyObj());
			}

			if (licenseEntry != null) {
				session.delete(licenseEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (licenseEntry != null) {
			clearCache(licenseEntry);
		}

		return licenseEntry;
	}

	@Override
	public LicenseEntry updateImpl(LicenseEntry licenseEntry) {
		boolean isNew = licenseEntry.isNew();

		if (!(licenseEntry instanceof LicenseEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(licenseEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					licenseEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in licenseEntry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom LicenseEntry implementation " +
					licenseEntry.getClass());
		}

		LicenseEntryModelImpl licenseEntryModelImpl =
			(LicenseEntryModelImpl)licenseEntry;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (licenseEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				licenseEntry.setCreateDate(date);
			}
			else {
				licenseEntry.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!licenseEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				licenseEntry.setModifiedDate(date);
			}
			else {
				licenseEntry.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(licenseEntry);

				licenseEntry.setNew(false);
			}
			else {
				licenseEntry = (LicenseEntry)session.merge(licenseEntry);
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
				licenseEntryModelImpl.getProductKey()
			};

			finderCache.removeResult(_finderPathCountByProductKey, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByProductKey, args);

			args = new Object[] {licenseEntryModelImpl.getType()};

			finderCache.removeResult(_finderPathCountByType, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByType, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((licenseEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByProductKey.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					licenseEntryModelImpl.getOriginalProductKey()
				};

				finderCache.removeResult(_finderPathCountByProductKey, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductKey, args);

				args = new Object[] {licenseEntryModelImpl.getProductKey()};

				finderCache.removeResult(_finderPathCountByProductKey, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductKey, args);
			}

			if ((licenseEntryModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByType.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					licenseEntryModelImpl.getOriginalType()
				};

				finderCache.removeResult(_finderPathCountByType, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByType, args);

				args = new Object[] {licenseEntryModelImpl.getType()};

				finderCache.removeResult(_finderPathCountByType, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByType, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, LicenseEntryImpl.class,
			licenseEntry.getPrimaryKey(), licenseEntry, false);

		clearUniqueFindersCache(licenseEntryModelImpl, false);
		cacheUniqueFindersCache(licenseEntryModelImpl);

		licenseEntry.resetOriginalValues();

		return licenseEntry;
	}

	/**
	 * Returns the license entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the license entry
	 * @return the license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLicenseEntryException {

		LicenseEntry licenseEntry = fetchByPrimaryKey(primaryKey);

		if (licenseEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLicenseEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return licenseEntry;
	}

	/**
	 * Returns the license entry with the primary key or throws a <code>NoSuchLicenseEntryException</code> if it could not be found.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry findByPrimaryKey(long licenseEntryId)
		throws NoSuchLicenseEntryException {

		return findByPrimaryKey((Serializable)licenseEntryId);
	}

	/**
	 * Returns the license entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry, or <code>null</code> if a license entry with the primary key could not be found
	 */
	@Override
	public LicenseEntry fetchByPrimaryKey(long licenseEntryId) {
		return fetchByPrimaryKey((Serializable)licenseEntryId);
	}

	/**
	 * Returns all the license entries.
	 *
	 * @return the license entries
	 */
	@Override
	public List<LicenseEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of license entries
	 */
	@Override
	public List<LicenseEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of license entries
	 */
	@Override
	public List<LicenseEntry> findAll(
		int start, int end, OrderByComparator<LicenseEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of license entries
	 */
	@Override
	public List<LicenseEntry> findAll(
		int start, int end, OrderByComparator<LicenseEntry> orderByComparator,
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

		List<LicenseEntry> list = null;

		if (useFinderCache) {
			list = (List<LicenseEntry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_LICENSEENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_LICENSEENTRY;

				sql = sql.concat(LicenseEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<LicenseEntry>)QueryUtil.list(
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
	 * Removes all the license entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (LicenseEntry licenseEntry : findAll()) {
			remove(licenseEntry);
		}
	}

	/**
	 * Returns the number of license entries.
	 *
	 * @return the number of license entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_LICENSEENTRY);

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
		return "licenseEntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LICENSEENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LicenseEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the license entry persistence.
	 */
	@Activate
	public void activate() {
		LicenseEntryModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		LicenseEntryModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByProductKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProductKey",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByProductKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProductKey",
			new String[] {String.class.getName()},
			LicenseEntryModelImpl.PRODUCTKEY_COLUMN_BITMASK |
			LicenseEntryModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByProductKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProductKey",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByLikeName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLikeName",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithPaginationCountByLikeName = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLikeName",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByType = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByType",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByType = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByType",
			new String[] {String.class.getName()},
			LicenseEntryModelImpl.TYPE_COLUMN_BITMASK |
			LicenseEntryModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByType = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByType",
			new String[] {String.class.getName()});

		_finderPathFetchByPK_T = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LicenseEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByPK_T",
			new String[] {String.class.getName(), String.class.getName()},
			LicenseEntryModelImpl.PRODUCTKEY_COLUMN_BITMASK |
			LicenseEntryModelImpl.TYPE_COLUMN_BITMASK);

		_finderPathCountByPK_T = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPK_T",
			new String[] {String.class.getName(), String.class.getName()});

		LicenseEntryUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		LicenseEntryUtil.setPersistence(null);

		entityCache.removeCache(LicenseEntryImpl.class.getName());

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
				"value.object.column.bitmask.enabled.com.liferay.osb.provisioning.license.model.LicenseEntry"),
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

	private static final String _SQL_SELECT_LICENSEENTRY =
		"SELECT licenseEntry FROM LicenseEntry licenseEntry";

	private static final String _SQL_SELECT_LICENSEENTRY_WHERE =
		"SELECT licenseEntry FROM LicenseEntry licenseEntry WHERE ";

	private static final String _SQL_COUNT_LICENSEENTRY =
		"SELECT COUNT(licenseEntry) FROM LicenseEntry licenseEntry";

	private static final String _SQL_COUNT_LICENSEENTRY_WHERE =
		"SELECT COUNT(licenseEntry) FROM LicenseEntry licenseEntry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "licenseEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No LicenseEntry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No LicenseEntry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseEntryPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

}