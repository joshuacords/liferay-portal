/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.persistence.impl;

import com.liferay.osb.koroneiki.trunk.exception.NoSuchProductConsumptionException;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.osb.koroneiki.trunk.model.impl.ProductConsumptionImpl;
import com.liferay.osb.koroneiki.trunk.model.impl.ProductConsumptionModelImpl;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductConsumptionPersistence;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductConsumptionUtil;
import com.liferay.osb.koroneiki.trunk.service.persistence.impl.constants.KoroneikiPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
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
 * The persistence implementation for the product consumption service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ProductConsumptionPersistence.class)
public class ProductConsumptionPersistenceImpl
	extends BasePersistenceImpl<ProductConsumption>
	implements ProductConsumptionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ProductConsumptionUtil</code> to access the product consumption persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ProductConsumptionImpl.class.getName();

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
	 * Returns all the product consumptions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product consumptions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product consumptions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
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

		List<ProductConsumption> list = null;

		if (useFinderCache) {
			list = (List<ProductConsumption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductConsumption productConsumption : list) {
					if (!uuid.equals(productConsumption.getUuid())) {
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

			sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

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
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
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

				list = (List<ProductConsumption>)QueryUtil.list(
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
	 * Returns the first product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByUuid_First(
			String uuid,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByUuid_First(
			uuid, orderByComparator);

		if (productConsumption != null) {
			return productConsumption;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchProductConsumptionException(sb.toString());
	}

	/**
	 * Returns the first product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByUuid_First(
		String uuid, OrderByComparator<ProductConsumption> orderByComparator) {

		List<ProductConsumption> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByUuid_Last(
			String uuid,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByUuid_Last(
			uuid, orderByComparator);

		if (productConsumption != null) {
			return productConsumption;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchProductConsumptionException(sb.toString());
	}

	/**
	 * Returns the last product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByUuid_Last(
		String uuid, OrderByComparator<ProductConsumption> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ProductConsumption> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption[] findByUuid_PrevAndNext(
			long productConsumptionId, String uuid,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		uuid = Objects.toString(uuid, "");

		ProductConsumption productConsumption = findByPrimaryKey(
			productConsumptionId);

		Session session = null;

		try {
			session = openSession();

			ProductConsumption[] array = new ProductConsumptionImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, productConsumption, uuid, orderByComparator, true);

			array[1] = productConsumption;

			array[2] = getByUuid_PrevAndNext(
				session, productConsumption, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProductConsumption getByUuid_PrevAndNext(
		Session session, ProductConsumption productConsumption, String uuid,
		OrderByComparator<ProductConsumption> orderByComparator,
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

		sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

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
			sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
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
						productConsumption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductConsumption> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the product consumptions that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByUuid(String uuid) {
		return filterFindByUuid(
			uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product consumptions that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByUuid(
		String uuid, int start, int end) {

		return filterFindByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid(uuid, start, end, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, ProductConsumptionImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, ProductConsumptionImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (bindUuid) {
				queryPos.add(uuid);
			}

			return (List<ProductConsumption>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where uuid = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption[] filterFindByUuid_PrevAndNext(
			long productConsumptionId, String uuid,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid_PrevAndNext(
				productConsumptionId, uuid, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		ProductConsumption productConsumption = findByPrimaryKey(
			productConsumptionId);

		Session session = null;

		try {
			session = openSession();

			ProductConsumption[] array = new ProductConsumptionImpl[3];

			array[0] = filterGetByUuid_PrevAndNext(
				session, productConsumption, uuid, orderByComparator, true);

			array[1] = productConsumption;

			array[2] = filterGetByUuid_PrevAndNext(
				session, productConsumption, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProductConsumption filterGetByUuid_PrevAndNext(
		Session session, ProductConsumption productConsumption, String uuid,
		OrderByComparator<ProductConsumption> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(
				_FILTER_ENTITY_ALIAS, ProductConsumptionImpl.class);
		}
		else {
			sqlQuery.addEntity(
				_FILTER_ENTITY_TABLE, ProductConsumptionImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productConsumption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductConsumption> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product consumptions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ProductConsumption productConsumption :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(productConsumption);
		}
	}

	/**
	 * Returns the number of product consumptions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product consumptions
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

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

	/**
	 * Returns the number of product consumptions that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	@Override
	public int filterCountByUuid(String uuid) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUuid(uuid);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler sb = new StringBundler(2);

		sb.append(_FILTER_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (bindUuid) {
				queryPos.add(uuid);
			}

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"productConsumption.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(productConsumption.uuid IS NULL OR productConsumption.uuid = '')";

	private static final String _FINDER_COLUMN_UUID_UUID_2_SQL =
		"productConsumption.uuid_ = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3_SQL =
		"(productConsumption.uuid_ IS NULL OR productConsumption.uuid_ = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the product consumptions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product consumptions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product consumptions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
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

		List<ProductConsumption> list = null;

		if (useFinderCache) {
			list = (List<ProductConsumption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductConsumption productConsumption : list) {
					if (!uuid.equals(productConsumption.getUuid()) ||
						(companyId != productConsumption.getCompanyId())) {

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

			sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

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
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
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

				list = (List<ProductConsumption>)QueryUtil.list(
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
	 * Returns the first product consumption in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (productConsumption != null) {
			return productConsumption;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchProductConsumptionException(sb.toString());
	}

	/**
	 * Returns the first product consumption in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		List<ProductConsumption> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product consumption in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (productConsumption != null) {
			return productConsumption;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchProductConsumptionException(sb.toString());
	}

	/**
	 * Returns the last product consumption in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ProductConsumption> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption[] findByUuid_C_PrevAndNext(
			long productConsumptionId, String uuid, long companyId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		uuid = Objects.toString(uuid, "");

		ProductConsumption productConsumption = findByPrimaryKey(
			productConsumptionId);

		Session session = null;

		try {
			session = openSession();

			ProductConsumption[] array = new ProductConsumptionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, productConsumption, uuid, companyId, orderByComparator,
				true);

			array[1] = productConsumption;

			array[2] = getByUuid_C_PrevAndNext(
				session, productConsumption, uuid, companyId, orderByComparator,
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

	protected ProductConsumption getByUuid_C_PrevAndNext(
		Session session, ProductConsumption productConsumption, String uuid,
		long companyId, OrderByComparator<ProductConsumption> orderByComparator,
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

		sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

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
			sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
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
						productConsumption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductConsumption> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the product consumptions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByUuid_C(
		String uuid, long companyId) {

		return filterFindByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product consumptions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return filterFindByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByUuid_C(uuid, companyId, start, end, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, ProductConsumptionImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, ProductConsumptionImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (bindUuid) {
				queryPos.add(uuid);
			}

			queryPos.add(companyId);

			return (List<ProductConsumption>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption[] filterFindByUuid_C_PrevAndNext(
			long productConsumptionId, String uuid, long companyId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByUuid_C_PrevAndNext(
				productConsumptionId, uuid, companyId, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		ProductConsumption productConsumption = findByPrimaryKey(
			productConsumptionId);

		Session session = null;

		try {
			session = openSession();

			ProductConsumption[] array = new ProductConsumptionImpl[3];

			array[0] = filterGetByUuid_C_PrevAndNext(
				session, productConsumption, uuid, companyId, orderByComparator,
				true);

			array[1] = productConsumption;

			array[2] = filterGetByUuid_C_PrevAndNext(
				session, productConsumption, uuid, companyId, orderByComparator,
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

	protected ProductConsumption filterGetByUuid_C_PrevAndNext(
		Session session, ProductConsumption productConsumption, String uuid,
		long companyId, OrderByComparator<ProductConsumption> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(
				_FILTER_ENTITY_ALIAS, ProductConsumptionImpl.class);
		}
		else {
			sqlQuery.addEntity(
				_FILTER_ENTITY_TABLE, ProductConsumptionImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productConsumption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductConsumption> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product consumptions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ProductConsumption productConsumption :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(productConsumption);
		}
	}

	/**
	 * Returns the number of product consumptions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product consumptions
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

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

	/**
	 * Returns the number of product consumptions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	@Override
	public int filterCountByUuid_C(String uuid, long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByUuid_C(uuid, companyId);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler sb = new StringBundler(3);

		sb.append(_FILTER_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2_SQL);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (bindUuid) {
				queryPos.add(uuid);
			}

			queryPos.add(companyId);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"productConsumption.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(productConsumption.uuid IS NULL OR productConsumption.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_2_SQL =
		"productConsumption.uuid_ = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3_SQL =
		"(productConsumption.uuid_ IS NULL OR productConsumption.uuid_ = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"productConsumption.companyId = ?";

	private FinderPath _finderPathFetchByProductConsumptionKey;
	private FinderPath _finderPathCountByProductConsumptionKey;

	/**
	 * Returns the product consumption where productConsumptionKey = &#63; or throws a <code>NoSuchProductConsumptionException</code> if it could not be found.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @return the matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByProductConsumptionKey(
			String productConsumptionKey)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByProductConsumptionKey(
			productConsumptionKey);

		if (productConsumption == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("productConsumptionKey=");
			sb.append(productConsumptionKey);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchProductConsumptionException(sb.toString());
		}

		return productConsumption;
	}

	/**
	 * Returns the product consumption where productConsumptionKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @return the matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByProductConsumptionKey(
		String productConsumptionKey) {

		return fetchByProductConsumptionKey(productConsumptionKey, true);
	}

	/**
	 * Returns the product consumption where productConsumptionKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByProductConsumptionKey(
		String productConsumptionKey, boolean useFinderCache) {

		productConsumptionKey = Objects.toString(productConsumptionKey, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {productConsumptionKey};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByProductConsumptionKey, finderArgs, this);
		}

		if (result instanceof ProductConsumption) {
			ProductConsumption productConsumption = (ProductConsumption)result;

			if (!Objects.equals(
					productConsumptionKey,
					productConsumption.getProductConsumptionKey())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

			boolean bindProductConsumptionKey = false;

			if (productConsumptionKey.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_PRODUCTCONSUMPTIONKEY_PRODUCTCONSUMPTIONKEY_3);
			}
			else {
				bindProductConsumptionKey = true;

				sb.append(
					_FINDER_COLUMN_PRODUCTCONSUMPTIONKEY_PRODUCTCONSUMPTIONKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductConsumptionKey) {
					queryPos.add(productConsumptionKey);
				}

				List<ProductConsumption> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByProductConsumptionKey, finderArgs,
							list);
					}
				}
				else {
					ProductConsumption productConsumption = list.get(0);

					result = productConsumption;

					cacheResult(productConsumption);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByProductConsumptionKey, finderArgs);
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
			return (ProductConsumption)result;
		}
	}

	/**
	 * Removes the product consumption where productConsumptionKey = &#63; from the database.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @return the product consumption that was removed
	 */
	@Override
	public ProductConsumption removeByProductConsumptionKey(
			String productConsumptionKey)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = findByProductConsumptionKey(
			productConsumptionKey);

		return remove(productConsumption);
	}

	/**
	 * Returns the number of product consumptions where productConsumptionKey = &#63;.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @return the number of matching product consumptions
	 */
	@Override
	public int countByProductConsumptionKey(String productConsumptionKey) {
		productConsumptionKey = Objects.toString(productConsumptionKey, "");

		FinderPath finderPath = _finderPathCountByProductConsumptionKey;

		Object[] finderArgs = new Object[] {productConsumptionKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

			boolean bindProductConsumptionKey = false;

			if (productConsumptionKey.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_PRODUCTCONSUMPTIONKEY_PRODUCTCONSUMPTIONKEY_3);
			}
			else {
				bindProductConsumptionKey = true;

				sb.append(
					_FINDER_COLUMN_PRODUCTCONSUMPTIONKEY_PRODUCTCONSUMPTIONKEY_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindProductConsumptionKey) {
					queryPos.add(productConsumptionKey);
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

	private static final String
		_FINDER_COLUMN_PRODUCTCONSUMPTIONKEY_PRODUCTCONSUMPTIONKEY_2 =
			"productConsumption.productConsumptionKey = ?";

	private static final String
		_FINDER_COLUMN_PRODUCTCONSUMPTIONKEY_PRODUCTCONSUMPTIONKEY_3 =
			"(productConsumption.productConsumptionKey IS NULL OR productConsumption.productConsumptionKey = '')";

	private FinderPath _finderPathWithPaginationFindByAccountId;
	private FinderPath _finderPathWithoutPaginationFindByAccountId;
	private FinderPath _finderPathCountByAccountId;

	/**
	 * Returns all the product consumptions where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByAccountId(long accountId) {
		return findByAccountId(
			accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product consumptions where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByAccountId(
		long accountId, int start, int end) {

		return findByAccountId(accountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return findByAccountId(accountId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product consumptions where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
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

		List<ProductConsumption> list = null;

		if (useFinderCache) {
			list = (List<ProductConsumption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductConsumption productConsumption : list) {
					if (accountId != productConsumption.getAccountId()) {
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

			sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

			sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

				list = (List<ProductConsumption>)QueryUtil.list(
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
	 * Returns the first product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByAccountId_First(
			long accountId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByAccountId_First(
			accountId, orderByComparator);

		if (productConsumption != null) {
			return productConsumption;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchProductConsumptionException(sb.toString());
	}

	/**
	 * Returns the first product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByAccountId_First(
		long accountId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		List<ProductConsumption> list = findByAccountId(
			accountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByAccountId_Last(
			long accountId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByAccountId_Last(
			accountId, orderByComparator);

		if (productConsumption != null) {
			return productConsumption;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchProductConsumptionException(sb.toString());
	}

	/**
	 * Returns the last product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByAccountId_Last(
		long accountId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		int count = countByAccountId(accountId);

		if (count == 0) {
			return null;
		}

		List<ProductConsumption> list = findByAccountId(
			accountId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption[] findByAccountId_PrevAndNext(
			long productConsumptionId, long accountId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = findByPrimaryKey(
			productConsumptionId);

		Session session = null;

		try {
			session = openSession();

			ProductConsumption[] array = new ProductConsumptionImpl[3];

			array[0] = getByAccountId_PrevAndNext(
				session, productConsumption, accountId, orderByComparator,
				true);

			array[1] = productConsumption;

			array[2] = getByAccountId_PrevAndNext(
				session, productConsumption, accountId, orderByComparator,
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

	protected ProductConsumption getByAccountId_PrevAndNext(
		Session session, ProductConsumption productConsumption, long accountId,
		OrderByComparator<ProductConsumption> orderByComparator,
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

		sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

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
			sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
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
						productConsumption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductConsumption> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the product consumptions that the user has permission to view where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByAccountId(long accountId) {
		return filterFindByAccountId(
			accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product consumptions that the user has permission to view where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByAccountId(
		long accountId, int start, int end) {

		return filterFindByAccountId(accountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions that the user has permissions to view where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByAccountId(accountId, start, end, orderByComparator);
		}

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, ProductConsumptionImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, ProductConsumptionImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(accountId);

			return (List<ProductConsumption>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where accountId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption[] filterFindByAccountId_PrevAndNext(
			long productConsumptionId, long accountId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByAccountId_PrevAndNext(
				productConsumptionId, accountId, orderByComparator);
		}

		ProductConsumption productConsumption = findByPrimaryKey(
			productConsumptionId);

		Session session = null;

		try {
			session = openSession();

			ProductConsumption[] array = new ProductConsumptionImpl[3];

			array[0] = filterGetByAccountId_PrevAndNext(
				session, productConsumption, accountId, orderByComparator,
				true);

			array[1] = productConsumption;

			array[2] = filterGetByAccountId_PrevAndNext(
				session, productConsumption, accountId, orderByComparator,
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

	protected ProductConsumption filterGetByAccountId_PrevAndNext(
		Session session, ProductConsumption productConsumption, long accountId,
		OrderByComparator<ProductConsumption> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(
				_FILTER_ENTITY_ALIAS, ProductConsumptionImpl.class);
		}
		else {
			sqlQuery.addEntity(
				_FILTER_ENTITY_TABLE, ProductConsumptionImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(accountId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productConsumption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductConsumption> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product consumptions where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	@Override
	public void removeByAccountId(long accountId) {
		for (ProductConsumption productConsumption :
				findByAccountId(
					accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(productConsumption);
		}
	}

	/**
	 * Returns the number of product consumptions where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching product consumptions
	 */
	@Override
	public int countByAccountId(long accountId) {
		FinderPath finderPath = _finderPathCountByAccountId;

		Object[] finderArgs = new Object[] {accountId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

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

	/**
	 * Returns the number of product consumptions that the user has permission to view where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	@Override
	public int filterCountByAccountId(long accountId) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByAccountId(accountId);
		}

		StringBundler sb = new StringBundler(2);

		sb.append(_FILTER_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

		sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(accountId);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2 =
		"productConsumption.accountId = ?";

	private FinderPath _finderPathWithPaginationFindByProductEntryId;
	private FinderPath _finderPathWithoutPaginationFindByProductEntryId;
	private FinderPath _finderPathCountByProductEntryId;

	/**
	 * Returns all the product consumptions where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByProductEntryId(long productEntryId) {
		return findByProductEntryId(
			productEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product consumptions where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByProductEntryId(
		long productEntryId, int start, int end) {

		return findByProductEntryId(productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return findByProductEntryId(
			productEntryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product consumptions where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByProductEntryId;
				finderArgs = new Object[] {productEntryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByProductEntryId;
			finderArgs = new Object[] {
				productEntryId, start, end, orderByComparator
			};
		}

		List<ProductConsumption> list = null;

		if (useFinderCache) {
			list = (List<ProductConsumption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductConsumption productConsumption : list) {
					if (productEntryId !=
							productConsumption.getProductEntryId()) {

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

			sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

			sb.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(productEntryId);

				list = (List<ProductConsumption>)QueryUtil.list(
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
	 * Returns the first product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByProductEntryId_First(
			long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByProductEntryId_First(
			productEntryId, orderByComparator);

		if (productConsumption != null) {
			return productConsumption;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productEntryId=");
		sb.append(productEntryId);

		sb.append("}");

		throw new NoSuchProductConsumptionException(sb.toString());
	}

	/**
	 * Returns the first product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByProductEntryId_First(
		long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		List<ProductConsumption> list = findByProductEntryId(
			productEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByProductEntryId_Last(
			long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByProductEntryId_Last(
			productEntryId, orderByComparator);

		if (productConsumption != null) {
			return productConsumption;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productEntryId=");
		sb.append(productEntryId);

		sb.append("}");

		throw new NoSuchProductConsumptionException(sb.toString());
	}

	/**
	 * Returns the last product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByProductEntryId_Last(
		long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		int count = countByProductEntryId(productEntryId);

		if (count == 0) {
			return null;
		}

		List<ProductConsumption> list = findByProductEntryId(
			productEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption[] findByProductEntryId_PrevAndNext(
			long productConsumptionId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = findByPrimaryKey(
			productConsumptionId);

		Session session = null;

		try {
			session = openSession();

			ProductConsumption[] array = new ProductConsumptionImpl[3];

			array[0] = getByProductEntryId_PrevAndNext(
				session, productConsumption, productEntryId, orderByComparator,
				true);

			array[1] = productConsumption;

			array[2] = getByProductEntryId_PrevAndNext(
				session, productConsumption, productEntryId, orderByComparator,
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

	protected ProductConsumption getByProductEntryId_PrevAndNext(
		Session session, ProductConsumption productConsumption,
		long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator,
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

		sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

		sb.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

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
			sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(productEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productConsumption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductConsumption> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the product consumptions that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByProductEntryId(
		long productEntryId) {

		return filterFindByProductEntryId(
			productEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product consumptions that the user has permission to view where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByProductEntryId(
		long productEntryId, int start, int end) {

		return filterFindByProductEntryId(productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions that the user has permissions to view where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByProductEntryId(
				productEntryId, start, end, orderByComparator);
		}

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, ProductConsumptionImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, ProductConsumptionImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(productEntryId);

			return (List<ProductConsumption>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption[] filterFindByProductEntryId_PrevAndNext(
			long productConsumptionId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByProductEntryId_PrevAndNext(
				productConsumptionId, productEntryId, orderByComparator);
		}

		ProductConsumption productConsumption = findByPrimaryKey(
			productConsumptionId);

		Session session = null;

		try {
			session = openSession();

			ProductConsumption[] array = new ProductConsumptionImpl[3];

			array[0] = filterGetByProductEntryId_PrevAndNext(
				session, productConsumption, productEntryId, orderByComparator,
				true);

			array[1] = productConsumption;

			array[2] = filterGetByProductEntryId_PrevAndNext(
				session, productConsumption, productEntryId, orderByComparator,
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

	protected ProductConsumption filterGetByProductEntryId_PrevAndNext(
		Session session, ProductConsumption productConsumption,
		long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(
				_FILTER_ENTITY_ALIAS, ProductConsumptionImpl.class);
		}
		else {
			sqlQuery.addEntity(
				_FILTER_ENTITY_TABLE, ProductConsumptionImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(productEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productConsumption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductConsumption> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product consumptions where productEntryId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 */
	@Override
	public void removeByProductEntryId(long productEntryId) {
		for (ProductConsumption productConsumption :
				findByProductEntryId(
					productEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(productConsumption);
		}
	}

	/**
	 * Returns the number of product consumptions where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions
	 */
	@Override
	public int countByProductEntryId(long productEntryId) {
		FinderPath finderPath = _finderPathCountByProductEntryId;

		Object[] finderArgs = new Object[] {productEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

			sb.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(productEntryId);

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
	 * Returns the number of product consumptions that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	@Override
	public int filterCountByProductEntryId(long productEntryId) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByProductEntryId(productEntryId);
		}

		StringBundler sb = new StringBundler(2);

		sb.append(_FILTER_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

		sb.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(productEntryId);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2 =
		"productConsumption.productEntryId = ?";

	private FinderPath _finderPathWithPaginationFindByProductPurchaseId;
	private FinderPath _finderPathWithoutPaginationFindByProductPurchaseId;
	private FinderPath _finderPathCountByProductPurchaseId;

	/**
	 * Returns all the product consumptions where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @return the matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByProductPurchaseId(
		long productPurchaseId) {

		return findByProductPurchaseId(
			productPurchaseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product consumptions where productPurchaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByProductPurchaseId(
		long productPurchaseId, int start, int end) {

		return findByProductPurchaseId(productPurchaseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions where productPurchaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByProductPurchaseId(
		long productPurchaseId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return findByProductPurchaseId(
			productPurchaseId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product consumptions where productPurchaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByProductPurchaseId(
		long productPurchaseId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByProductPurchaseId;
				finderArgs = new Object[] {productPurchaseId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByProductPurchaseId;
			finderArgs = new Object[] {
				productPurchaseId, start, end, orderByComparator
			};
		}

		List<ProductConsumption> list = null;

		if (useFinderCache) {
			list = (List<ProductConsumption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductConsumption productConsumption : list) {
					if (productPurchaseId !=
							productConsumption.getProductPurchaseId()) {

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

			sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

			sb.append(_FINDER_COLUMN_PRODUCTPURCHASEID_PRODUCTPURCHASEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(productPurchaseId);

				list = (List<ProductConsumption>)QueryUtil.list(
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
	 * Returns the first product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByProductPurchaseId_First(
			long productPurchaseId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByProductPurchaseId_First(
			productPurchaseId, orderByComparator);

		if (productConsumption != null) {
			return productConsumption;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseId=");
		sb.append(productPurchaseId);

		sb.append("}");

		throw new NoSuchProductConsumptionException(sb.toString());
	}

	/**
	 * Returns the first product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByProductPurchaseId_First(
		long productPurchaseId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		List<ProductConsumption> list = findByProductPurchaseId(
			productPurchaseId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByProductPurchaseId_Last(
			long productPurchaseId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByProductPurchaseId_Last(
			productPurchaseId, orderByComparator);

		if (productConsumption != null) {
			return productConsumption;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productPurchaseId=");
		sb.append(productPurchaseId);

		sb.append("}");

		throw new NoSuchProductConsumptionException(sb.toString());
	}

	/**
	 * Returns the last product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByProductPurchaseId_Last(
		long productPurchaseId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		int count = countByProductPurchaseId(productPurchaseId);

		if (count == 0) {
			return null;
		}

		List<ProductConsumption> list = findByProductPurchaseId(
			productPurchaseId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption[] findByProductPurchaseId_PrevAndNext(
			long productConsumptionId, long productPurchaseId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = findByPrimaryKey(
			productConsumptionId);

		Session session = null;

		try {
			session = openSession();

			ProductConsumption[] array = new ProductConsumptionImpl[3];

			array[0] = getByProductPurchaseId_PrevAndNext(
				session, productConsumption, productPurchaseId,
				orderByComparator, true);

			array[1] = productConsumption;

			array[2] = getByProductPurchaseId_PrevAndNext(
				session, productConsumption, productPurchaseId,
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

	protected ProductConsumption getByProductPurchaseId_PrevAndNext(
		Session session, ProductConsumption productConsumption,
		long productPurchaseId,
		OrderByComparator<ProductConsumption> orderByComparator,
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

		sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

		sb.append(_FINDER_COLUMN_PRODUCTPURCHASEID_PRODUCTPURCHASEID_2);

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
			sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(productPurchaseId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productConsumption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductConsumption> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the product consumptions that the user has permission to view where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByProductPurchaseId(
		long productPurchaseId) {

		return filterFindByProductPurchaseId(
			productPurchaseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product consumptions that the user has permission to view where productPurchaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByProductPurchaseId(
		long productPurchaseId, int start, int end) {

		return filterFindByProductPurchaseId(
			productPurchaseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions that the user has permissions to view where productPurchaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByProductPurchaseId(
		long productPurchaseId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByProductPurchaseId(
				productPurchaseId, start, end, orderByComparator);
		}

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_PRODUCTPURCHASEID_PRODUCTPURCHASEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, ProductConsumptionImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, ProductConsumptionImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(productPurchaseId);

			return (List<ProductConsumption>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where productPurchaseId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption[] filterFindByProductPurchaseId_PrevAndNext(
			long productConsumptionId, long productPurchaseId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByProductPurchaseId_PrevAndNext(
				productConsumptionId, productPurchaseId, orderByComparator);
		}

		ProductConsumption productConsumption = findByPrimaryKey(
			productConsumptionId);

		Session session = null;

		try {
			session = openSession();

			ProductConsumption[] array = new ProductConsumptionImpl[3];

			array[0] = filterGetByProductPurchaseId_PrevAndNext(
				session, productConsumption, productPurchaseId,
				orderByComparator, true);

			array[1] = productConsumption;

			array[2] = filterGetByProductPurchaseId_PrevAndNext(
				session, productConsumption, productPurchaseId,
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

	protected ProductConsumption filterGetByProductPurchaseId_PrevAndNext(
		Session session, ProductConsumption productConsumption,
		long productPurchaseId,
		OrderByComparator<ProductConsumption> orderByComparator,
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

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_PRODUCTPURCHASEID_PRODUCTPURCHASEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(
				_FILTER_ENTITY_ALIAS, ProductConsumptionImpl.class);
		}
		else {
			sqlQuery.addEntity(
				_FILTER_ENTITY_TABLE, ProductConsumptionImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(productPurchaseId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productConsumption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductConsumption> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product consumptions where productPurchaseId = &#63; from the database.
	 *
	 * @param productPurchaseId the product purchase ID
	 */
	@Override
	public void removeByProductPurchaseId(long productPurchaseId) {
		for (ProductConsumption productConsumption :
				findByProductPurchaseId(
					productPurchaseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(productConsumption);
		}
	}

	/**
	 * Returns the number of product consumptions where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @return the number of matching product consumptions
	 */
	@Override
	public int countByProductPurchaseId(long productPurchaseId) {
		FinderPath finderPath = _finderPathCountByProductPurchaseId;

		Object[] finderArgs = new Object[] {productPurchaseId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

			sb.append(_FINDER_COLUMN_PRODUCTPURCHASEID_PRODUCTPURCHASEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(productPurchaseId);

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
	 * Returns the number of product consumptions that the user has permission to view where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	@Override
	public int filterCountByProductPurchaseId(long productPurchaseId) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByProductPurchaseId(productPurchaseId);
		}

		StringBundler sb = new StringBundler(2);

		sb.append(_FILTER_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

		sb.append(_FINDER_COLUMN_PRODUCTPURCHASEID_PRODUCTPURCHASEID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(productPurchaseId);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String
		_FINDER_COLUMN_PRODUCTPURCHASEID_PRODUCTPURCHASEID_2 =
			"productConsumption.productPurchaseId = ?";

	private FinderPath _finderPathWithPaginationFindByAI_PEI;
	private FinderPath _finderPathWithoutPaginationFindByAI_PEI;
	private FinderPath _finderPathCountByAI_PEI;

	/**
	 * Returns all the product consumptions where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByAI_PEI(
		long accountId, long productEntryId) {

		return findByAI_PEI(
			accountId, productEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the product consumptions where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByAI_PEI(
		long accountId, long productEntryId, int start, int end) {

		return findByAI_PEI(accountId, productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return findByAI_PEI(
			accountId, productEntryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product consumptions where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByAI_PEI;
				finderArgs = new Object[] {accountId, productEntryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByAI_PEI;
			finderArgs = new Object[] {
				accountId, productEntryId, start, end, orderByComparator
			};
		}

		List<ProductConsumption> list = null;

		if (useFinderCache) {
			list = (List<ProductConsumption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductConsumption productConsumption : list) {
					if ((accountId != productConsumption.getAccountId()) ||
						(productEntryId !=
							productConsumption.getProductEntryId())) {

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

			sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

			sb.append(_FINDER_COLUMN_AI_PEI_ACCOUNTID_2);

			sb.append(_FINDER_COLUMN_AI_PEI_PRODUCTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

				queryPos.add(productEntryId);

				list = (List<ProductConsumption>)QueryUtil.list(
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
	 * Returns the first product consumption in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByAI_PEI_First(
			long accountId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByAI_PEI_First(
			accountId, productEntryId, orderByComparator);

		if (productConsumption != null) {
			return productConsumption;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append(", productEntryId=");
		sb.append(productEntryId);

		sb.append("}");

		throw new NoSuchProductConsumptionException(sb.toString());
	}

	/**
	 * Returns the first product consumption in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByAI_PEI_First(
		long accountId, long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		List<ProductConsumption> list = findByAI_PEI(
			accountId, productEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product consumption in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByAI_PEI_Last(
			long accountId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByAI_PEI_Last(
			accountId, productEntryId, orderByComparator);

		if (productConsumption != null) {
			return productConsumption;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append(", productEntryId=");
		sb.append(productEntryId);

		sb.append("}");

		throw new NoSuchProductConsumptionException(sb.toString());
	}

	/**
	 * Returns the last product consumption in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByAI_PEI_Last(
		long accountId, long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		int count = countByAI_PEI(accountId, productEntryId);

		if (count == 0) {
			return null;
		}

		List<ProductConsumption> list = findByAI_PEI(
			accountId, productEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption[] findByAI_PEI_PrevAndNext(
			long productConsumptionId, long accountId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = findByPrimaryKey(
			productConsumptionId);

		Session session = null;

		try {
			session = openSession();

			ProductConsumption[] array = new ProductConsumptionImpl[3];

			array[0] = getByAI_PEI_PrevAndNext(
				session, productConsumption, accountId, productEntryId,
				orderByComparator, true);

			array[1] = productConsumption;

			array[2] = getByAI_PEI_PrevAndNext(
				session, productConsumption, accountId, productEntryId,
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

	protected ProductConsumption getByAI_PEI_PrevAndNext(
		Session session, ProductConsumption productConsumption, long accountId,
		long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator,
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

		sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

		sb.append(_FINDER_COLUMN_AI_PEI_ACCOUNTID_2);

		sb.append(_FINDER_COLUMN_AI_PEI_PRODUCTENTRYID_2);

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
			sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(accountId);

		queryPos.add(productEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productConsumption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductConsumption> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the product consumptions that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByAI_PEI(
		long accountId, long productEntryId) {

		return filterFindByAI_PEI(
			accountId, productEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the product consumptions that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByAI_PEI(
		long accountId, long productEntryId, int start, int end) {

		return filterFindByAI_PEI(accountId, productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions that the user has permissions to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByAI_PEI(
				accountId, productEntryId, start, end, orderByComparator);
		}

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_AI_PEI_ACCOUNTID_2);

		sb.append(_FINDER_COLUMN_AI_PEI_PRODUCTENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, ProductConsumptionImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, ProductConsumptionImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(accountId);

			queryPos.add(productEntryId);

			return (List<ProductConsumption>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption[] filterFindByAI_PEI_PrevAndNext(
			long productConsumptionId, long accountId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByAI_PEI_PrevAndNext(
				productConsumptionId, accountId, productEntryId,
				orderByComparator);
		}

		ProductConsumption productConsumption = findByPrimaryKey(
			productConsumptionId);

		Session session = null;

		try {
			session = openSession();

			ProductConsumption[] array = new ProductConsumptionImpl[3];

			array[0] = filterGetByAI_PEI_PrevAndNext(
				session, productConsumption, accountId, productEntryId,
				orderByComparator, true);

			array[1] = productConsumption;

			array[2] = filterGetByAI_PEI_PrevAndNext(
				session, productConsumption, accountId, productEntryId,
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

	protected ProductConsumption filterGetByAI_PEI_PrevAndNext(
		Session session, ProductConsumption productConsumption, long accountId,
		long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_AI_PEI_ACCOUNTID_2);

		sb.append(_FINDER_COLUMN_AI_PEI_PRODUCTENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(
				_FILTER_ENTITY_ALIAS, ProductConsumptionImpl.class);
		}
		else {
			sqlQuery.addEntity(
				_FILTER_ENTITY_TABLE, ProductConsumptionImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(accountId);

		queryPos.add(productEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productConsumption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductConsumption> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product consumptions where accountId = &#63; and productEntryId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 */
	@Override
	public void removeByAI_PEI(long accountId, long productEntryId) {
		for (ProductConsumption productConsumption :
				findByAI_PEI(
					accountId, productEntryId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(productConsumption);
		}
	}

	/**
	 * Returns the number of product consumptions where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions
	 */
	@Override
	public int countByAI_PEI(long accountId, long productEntryId) {
		FinderPath finderPath = _finderPathCountByAI_PEI;

		Object[] finderArgs = new Object[] {accountId, productEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

			sb.append(_FINDER_COLUMN_AI_PEI_ACCOUNTID_2);

			sb.append(_FINDER_COLUMN_AI_PEI_PRODUCTENTRYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

				queryPos.add(productEntryId);

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
	 * Returns the number of product consumptions that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	@Override
	public int filterCountByAI_PEI(long accountId, long productEntryId) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByAI_PEI(accountId, productEntryId);
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_FILTER_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

		sb.append(_FINDER_COLUMN_AI_PEI_ACCOUNTID_2);

		sb.append(_FINDER_COLUMN_AI_PEI_PRODUCTENTRYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(accountId);

			queryPos.add(productEntryId);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_AI_PEI_ACCOUNTID_2 =
		"productConsumption.accountId = ? AND ";

	private static final String _FINDER_COLUMN_AI_PEI_PRODUCTENTRYID_2 =
		"productConsumption.productEntryId = ?";

	private FinderPath _finderPathWithPaginationFindByU_AI_PEI;
	private FinderPath _finderPathWithoutPaginationFindByU_AI_PEI;
	private FinderPath _finderPathCountByU_AI_PEI;

	/**
	 * Returns all the product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByU_AI_PEI(
		long userId, long accountId, long productEntryId) {

		return findByU_AI_PEI(
			userId, accountId, productEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end) {

		return findByU_AI_PEI(
			userId, accountId, productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return findByU_AI_PEI(
			userId, accountId, productEntryId, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product consumptions
	 */
	@Override
	public List<ProductConsumption> findByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByU_AI_PEI;
				finderArgs = new Object[] {userId, accountId, productEntryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByU_AI_PEI;
			finderArgs = new Object[] {
				userId, accountId, productEntryId, start, end, orderByComparator
			};
		}

		List<ProductConsumption> list = null;

		if (useFinderCache) {
			list = (List<ProductConsumption>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductConsumption productConsumption : list) {
					if ((userId != productConsumption.getUserId()) ||
						(accountId != productConsumption.getAccountId()) ||
						(productEntryId !=
							productConsumption.getProductEntryId())) {

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

			sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

			sb.append(_FINDER_COLUMN_U_AI_PEI_USERID_2);

			sb.append(_FINDER_COLUMN_U_AI_PEI_ACCOUNTID_2);

			sb.append(_FINDER_COLUMN_U_AI_PEI_PRODUCTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(accountId);

				queryPos.add(productEntryId);

				list = (List<ProductConsumption>)QueryUtil.list(
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
	 * Returns the first product consumption in the ordered set where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByU_AI_PEI_First(
			long userId, long accountId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByU_AI_PEI_First(
			userId, accountId, productEntryId, orderByComparator);

		if (productConsumption != null) {
			return productConsumption;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", accountId=");
		sb.append(accountId);

		sb.append(", productEntryId=");
		sb.append(productEntryId);

		sb.append("}");

		throw new NoSuchProductConsumptionException(sb.toString());
	}

	/**
	 * Returns the first product consumption in the ordered set where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByU_AI_PEI_First(
		long userId, long accountId, long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		List<ProductConsumption> list = findByU_AI_PEI(
			userId, accountId, productEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product consumption in the ordered set where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption findByU_AI_PEI_Last(
			long userId, long accountId, long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByU_AI_PEI_Last(
			userId, accountId, productEntryId, orderByComparator);

		if (productConsumption != null) {
			return productConsumption;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", accountId=");
		sb.append(accountId);

		sb.append(", productEntryId=");
		sb.append(productEntryId);

		sb.append("}");

		throw new NoSuchProductConsumptionException(sb.toString());
	}

	/**
	 * Returns the last product consumption in the ordered set where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	@Override
	public ProductConsumption fetchByU_AI_PEI_Last(
		long userId, long accountId, long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator) {

		int count = countByU_AI_PEI(userId, accountId, productEntryId);

		if (count == 0) {
			return null;
		}

		List<ProductConsumption> list = findByU_AI_PEI(
			userId, accountId, productEntryId, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption[] findByU_AI_PEI_PrevAndNext(
			long productConsumptionId, long userId, long accountId,
			long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = findByPrimaryKey(
			productConsumptionId);

		Session session = null;

		try {
			session = openSession();

			ProductConsumption[] array = new ProductConsumptionImpl[3];

			array[0] = getByU_AI_PEI_PrevAndNext(
				session, productConsumption, userId, accountId, productEntryId,
				orderByComparator, true);

			array[1] = productConsumption;

			array[2] = getByU_AI_PEI_PrevAndNext(
				session, productConsumption, userId, accountId, productEntryId,
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

	protected ProductConsumption getByU_AI_PEI_PrevAndNext(
		Session session, ProductConsumption productConsumption, long userId,
		long accountId, long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);

		sb.append(_FINDER_COLUMN_U_AI_PEI_USERID_2);

		sb.append(_FINDER_COLUMN_U_AI_PEI_ACCOUNTID_2);

		sb.append(_FINDER_COLUMN_U_AI_PEI_PRODUCTENTRYID_2);

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
			sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		queryPos.add(accountId);

		queryPos.add(productEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productConsumption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductConsumption> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the product consumptions that the user has permission to view where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByU_AI_PEI(
		long userId, long accountId, long productEntryId) {

		return filterFindByU_AI_PEI(
			userId, accountId, productEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product consumptions that the user has permission to view where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end) {

		return filterFindByU_AI_PEI(
			userId, accountId, productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions that the user has permissions to view where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product consumptions that the user has permission to view
	 */
	@Override
	public List<ProductConsumption> filterFindByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByU_AI_PEI(
				userId, accountId, productEntryId, start, end,
				orderByComparator);
		}

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_U_AI_PEI_USERID_2);

		sb.append(_FINDER_COLUMN_U_AI_PEI_ACCOUNTID_2);

		sb.append(_FINDER_COLUMN_U_AI_PEI_PRODUCTENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, ProductConsumptionImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, ProductConsumptionImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(userId);

			queryPos.add(accountId);

			queryPos.add(productEntryId);

			return (List<ProductConsumption>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption[] filterFindByU_AI_PEI_PrevAndNext(
			long productConsumptionId, long userId, long accountId,
			long productEntryId,
			OrderByComparator<ProductConsumption> orderByComparator)
		throws NoSuchProductConsumptionException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByU_AI_PEI_PrevAndNext(
				productConsumptionId, userId, accountId, productEntryId,
				orderByComparator);
		}

		ProductConsumption productConsumption = findByPrimaryKey(
			productConsumptionId);

		Session session = null;

		try {
			session = openSession();

			ProductConsumption[] array = new ProductConsumptionImpl[3];

			array[0] = filterGetByU_AI_PEI_PrevAndNext(
				session, productConsumption, userId, accountId, productEntryId,
				orderByComparator, true);

			array[1] = productConsumption;

			array[2] = filterGetByU_AI_PEI_PrevAndNext(
				session, productConsumption, userId, accountId, productEntryId,
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

	protected ProductConsumption filterGetByU_AI_PEI_PrevAndNext(
		Session session, ProductConsumption productConsumption, long userId,
		long accountId, long productEntryId,
		OrderByComparator<ProductConsumption> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				7 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(6);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_U_AI_PEI_USERID_2);

		sb.append(_FINDER_COLUMN_U_AI_PEI_ACCOUNTID_2);

		sb.append(_FINDER_COLUMN_U_AI_PEI_PRODUCTENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductConsumptionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(
				_FILTER_ENTITY_ALIAS, ProductConsumptionImpl.class);
		}
		else {
			sqlQuery.addEntity(
				_FILTER_ENTITY_TABLE, ProductConsumptionImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(userId);

		queryPos.add(accountId);

		queryPos.add(productEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productConsumption)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductConsumption> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 */
	@Override
	public void removeByU_AI_PEI(
		long userId, long accountId, long productEntryId) {

		for (ProductConsumption productConsumption :
				findByU_AI_PEI(
					userId, accountId, productEntryId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(productConsumption);
		}
	}

	/**
	 * Returns the number of product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions
	 */
	@Override
	public int countByU_AI_PEI(
		long userId, long accountId, long productEntryId) {

		FinderPath finderPath = _finderPathCountByU_AI_PEI;

		Object[] finderArgs = new Object[] {userId, accountId, productEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

			sb.append(_FINDER_COLUMN_U_AI_PEI_USERID_2);

			sb.append(_FINDER_COLUMN_U_AI_PEI_ACCOUNTID_2);

			sb.append(_FINDER_COLUMN_U_AI_PEI_PRODUCTENTRYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(accountId);

				queryPos.add(productEntryId);

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
	 * Returns the number of product consumptions that the user has permission to view where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	@Override
	public int filterCountByU_AI_PEI(
		long userId, long accountId, long productEntryId) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByU_AI_PEI(userId, accountId, productEntryId);
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_FILTER_SQL_COUNT_PRODUCTCONSUMPTION_WHERE);

		sb.append(_FINDER_COLUMN_U_AI_PEI_USERID_2);

		sb.append(_FINDER_COLUMN_U_AI_PEI_ACCOUNTID_2);

		sb.append(_FINDER_COLUMN_U_AI_PEI_PRODUCTENTRYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductConsumption.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(userId);

			queryPos.add(accountId);

			queryPos.add(productEntryId);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_U_AI_PEI_USERID_2 =
		"productConsumption.userId = ? AND ";

	private static final String _FINDER_COLUMN_U_AI_PEI_ACCOUNTID_2 =
		"productConsumption.accountId = ? AND ";

	private static final String _FINDER_COLUMN_U_AI_PEI_PRODUCTENTRYID_2 =
		"productConsumption.productEntryId = ?";

	public ProductConsumptionPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(ProductConsumption.class);

		setModelImplClass(ProductConsumptionImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the product consumption in the entity cache if it is enabled.
	 *
	 * @param productConsumption the product consumption
	 */
	@Override
	public void cacheResult(ProductConsumption productConsumption) {
		entityCache.putResult(
			entityCacheEnabled, ProductConsumptionImpl.class,
			productConsumption.getPrimaryKey(), productConsumption);

		finderCache.putResult(
			_finderPathFetchByProductConsumptionKey,
			new Object[] {productConsumption.getProductConsumptionKey()},
			productConsumption);

		productConsumption.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the product consumptions in the entity cache if it is enabled.
	 *
	 * @param productConsumptions the product consumptions
	 */
	@Override
	public void cacheResult(List<ProductConsumption> productConsumptions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (productConsumptions.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ProductConsumption productConsumption : productConsumptions) {
			if (entityCache.getResult(
					entityCacheEnabled, ProductConsumptionImpl.class,
					productConsumption.getPrimaryKey()) == null) {

				cacheResult(productConsumption);
			}
			else {
				productConsumption.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all product consumptions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProductConsumptionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the product consumption.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProductConsumption productConsumption) {
		entityCache.removeResult(
			entityCacheEnabled, ProductConsumptionImpl.class,
			productConsumption.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(ProductConsumptionModelImpl)productConsumption, true);
	}

	@Override
	public void clearCache(List<ProductConsumption> productConsumptions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProductConsumption productConsumption : productConsumptions) {
			entityCache.removeResult(
				entityCacheEnabled, ProductConsumptionImpl.class,
				productConsumption.getPrimaryKey());

			clearUniqueFindersCache(
				(ProductConsumptionModelImpl)productConsumption, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, ProductConsumptionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ProductConsumptionModelImpl productConsumptionModelImpl) {

		Object[] args = new Object[] {
			productConsumptionModelImpl.getProductConsumptionKey()
		};

		finderCache.putResult(
			_finderPathCountByProductConsumptionKey, args, Long.valueOf(1),
			false);
		finderCache.putResult(
			_finderPathFetchByProductConsumptionKey, args,
			productConsumptionModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ProductConsumptionModelImpl productConsumptionModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				productConsumptionModelImpl.getProductConsumptionKey()
			};

			finderCache.removeResult(
				_finderPathCountByProductConsumptionKey, args);
			finderCache.removeResult(
				_finderPathFetchByProductConsumptionKey, args);
		}

		if ((productConsumptionModelImpl.getColumnBitmask() &
			 _finderPathFetchByProductConsumptionKey.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				productConsumptionModelImpl.getOriginalProductConsumptionKey()
			};

			finderCache.removeResult(
				_finderPathCountByProductConsumptionKey, args);
			finderCache.removeResult(
				_finderPathFetchByProductConsumptionKey, args);
		}
	}

	/**
	 * Creates a new product consumption with the primary key. Does not add the product consumption to the database.
	 *
	 * @param productConsumptionId the primary key for the new product consumption
	 * @return the new product consumption
	 */
	@Override
	public ProductConsumption create(long productConsumptionId) {
		ProductConsumption productConsumption = new ProductConsumptionImpl();

		productConsumption.setNew(true);
		productConsumption.setPrimaryKey(productConsumptionId);

		String uuid = PortalUUIDUtil.generate();

		productConsumption.setUuid(uuid);

		productConsumption.setCompanyId(CompanyThreadLocal.getCompanyId());

		return productConsumption;
	}

	/**
	 * Removes the product consumption with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productConsumptionId the primary key of the product consumption
	 * @return the product consumption that was removed
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption remove(long productConsumptionId)
		throws NoSuchProductConsumptionException {

		return remove((Serializable)productConsumptionId);
	}

	/**
	 * Removes the product consumption with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the product consumption
	 * @return the product consumption that was removed
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption remove(Serializable primaryKey)
		throws NoSuchProductConsumptionException {

		Session session = null;

		try {
			session = openSession();

			ProductConsumption productConsumption =
				(ProductConsumption)session.get(
					ProductConsumptionImpl.class, primaryKey);

			if (productConsumption == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProductConsumptionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(productConsumption);
		}
		catch (NoSuchProductConsumptionException noSuchEntityException) {
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
	protected ProductConsumption removeImpl(
		ProductConsumption productConsumption) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(productConsumption)) {
				productConsumption = (ProductConsumption)session.get(
					ProductConsumptionImpl.class,
					productConsumption.getPrimaryKeyObj());
			}

			if (productConsumption != null) {
				session.delete(productConsumption);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (productConsumption != null) {
			clearCache(productConsumption);
		}

		return productConsumption;
	}

	@Override
	public ProductConsumption updateImpl(
		ProductConsumption productConsumption) {

		boolean isNew = productConsumption.isNew();

		if (!(productConsumption instanceof ProductConsumptionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(productConsumption.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					productConsumption);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in productConsumption proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProductConsumption implementation " +
					productConsumption.getClass());
		}

		ProductConsumptionModelImpl productConsumptionModelImpl =
			(ProductConsumptionModelImpl)productConsumption;

		if (Validator.isNull(productConsumption.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			productConsumption.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (productConsumption.getCreateDate() == null)) {
			if (serviceContext == null) {
				productConsumption.setCreateDate(date);
			}
			else {
				productConsumption.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!productConsumptionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				productConsumption.setModifiedDate(date);
			}
			else {
				productConsumption.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(productConsumption);

				productConsumption.setNew(false);
			}
			else {
				productConsumption = (ProductConsumption)session.merge(
					productConsumption);
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
				productConsumptionModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				productConsumptionModelImpl.getUuid(),
				productConsumptionModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {productConsumptionModelImpl.getAccountId()};

			finderCache.removeResult(_finderPathCountByAccountId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAccountId, args);

			args = new Object[] {
				productConsumptionModelImpl.getProductEntryId()
			};

			finderCache.removeResult(_finderPathCountByProductEntryId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByProductEntryId, args);

			args = new Object[] {
				productConsumptionModelImpl.getProductPurchaseId()
			};

			finderCache.removeResult(_finderPathCountByProductPurchaseId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByProductPurchaseId, args);

			args = new Object[] {
				productConsumptionModelImpl.getAccountId(),
				productConsumptionModelImpl.getProductEntryId()
			};

			finderCache.removeResult(_finderPathCountByAI_PEI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAI_PEI, args);

			args = new Object[] {
				productConsumptionModelImpl.getUserId(),
				productConsumptionModelImpl.getAccountId(),
				productConsumptionModelImpl.getProductEntryId()
			};

			finderCache.removeResult(_finderPathCountByU_AI_PEI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByU_AI_PEI, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((productConsumptionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					productConsumptionModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {productConsumptionModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((productConsumptionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					productConsumptionModelImpl.getOriginalUuid(),
					productConsumptionModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					productConsumptionModelImpl.getUuid(),
					productConsumptionModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((productConsumptionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByAccountId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					productConsumptionModelImpl.getOriginalAccountId()
				};

				finderCache.removeResult(_finderPathCountByAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountId, args);

				args = new Object[] {
					productConsumptionModelImpl.getAccountId()
				};

				finderCache.removeResult(_finderPathCountByAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountId, args);
			}

			if ((productConsumptionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByProductEntryId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					productConsumptionModelImpl.getOriginalProductEntryId()
				};

				finderCache.removeResult(
					_finderPathCountByProductEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductEntryId, args);

				args = new Object[] {
					productConsumptionModelImpl.getProductEntryId()
				};

				finderCache.removeResult(
					_finderPathCountByProductEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductEntryId, args);
			}

			if ((productConsumptionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByProductPurchaseId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					productConsumptionModelImpl.getOriginalProductPurchaseId()
				};

				finderCache.removeResult(
					_finderPathCountByProductPurchaseId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductPurchaseId, args);

				args = new Object[] {
					productConsumptionModelImpl.getProductPurchaseId()
				};

				finderCache.removeResult(
					_finderPathCountByProductPurchaseId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductPurchaseId, args);
			}

			if ((productConsumptionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByAI_PEI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					productConsumptionModelImpl.getOriginalAccountId(),
					productConsumptionModelImpl.getOriginalProductEntryId()
				};

				finderCache.removeResult(_finderPathCountByAI_PEI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAI_PEI, args);

				args = new Object[] {
					productConsumptionModelImpl.getAccountId(),
					productConsumptionModelImpl.getProductEntryId()
				};

				finderCache.removeResult(_finderPathCountByAI_PEI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAI_PEI, args);
			}

			if ((productConsumptionModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByU_AI_PEI.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					productConsumptionModelImpl.getOriginalUserId(),
					productConsumptionModelImpl.getOriginalAccountId(),
					productConsumptionModelImpl.getOriginalProductEntryId()
				};

				finderCache.removeResult(_finderPathCountByU_AI_PEI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_AI_PEI, args);

				args = new Object[] {
					productConsumptionModelImpl.getUserId(),
					productConsumptionModelImpl.getAccountId(),
					productConsumptionModelImpl.getProductEntryId()
				};

				finderCache.removeResult(_finderPathCountByU_AI_PEI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_AI_PEI, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, ProductConsumptionImpl.class,
			productConsumption.getPrimaryKey(), productConsumption, false);

		clearUniqueFindersCache(productConsumptionModelImpl, false);
		cacheUniqueFindersCache(productConsumptionModelImpl);

		productConsumption.resetOriginalValues();

		return productConsumption;
	}

	/**
	 * Returns the product consumption with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the product consumption
	 * @return the product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProductConsumptionException {

		ProductConsumption productConsumption = fetchByPrimaryKey(primaryKey);

		if (productConsumption == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProductConsumptionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return productConsumption;
	}

	/**
	 * Returns the product consumption with the primary key or throws a <code>NoSuchProductConsumptionException</code> if it could not be found.
	 *
	 * @param productConsumptionId the primary key of the product consumption
	 * @return the product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption findByPrimaryKey(long productConsumptionId)
		throws NoSuchProductConsumptionException {

		return findByPrimaryKey((Serializable)productConsumptionId);
	}

	/**
	 * Returns the product consumption with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productConsumptionId the primary key of the product consumption
	 * @return the product consumption, or <code>null</code> if a product consumption with the primary key could not be found
	 */
	@Override
	public ProductConsumption fetchByPrimaryKey(long productConsumptionId) {
		return fetchByPrimaryKey((Serializable)productConsumptionId);
	}

	/**
	 * Returns all the product consumptions.
	 *
	 * @return the product consumptions
	 */
	@Override
	public List<ProductConsumption> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product consumptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @return the range of product consumptions
	 */
	@Override
	public List<ProductConsumption> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the product consumptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product consumptions
	 */
	@Override
	public List<ProductConsumption> findAll(
		int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product consumptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductConsumptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product consumptions
	 * @param end the upper bound of the range of product consumptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of product consumptions
	 */
	@Override
	public List<ProductConsumption> findAll(
		int start, int end,
		OrderByComparator<ProductConsumption> orderByComparator,
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

		List<ProductConsumption> list = null;

		if (useFinderCache) {
			list = (List<ProductConsumption>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PRODUCTCONSUMPTION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PRODUCTCONSUMPTION;

				sql = sql.concat(ProductConsumptionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ProductConsumption>)QueryUtil.list(
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
	 * Removes all the product consumptions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProductConsumption productConsumption : findAll()) {
			remove(productConsumption);
		}
	}

	/**
	 * Returns the number of product consumptions.
	 *
	 * @return the number of product consumptions
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
					_SQL_COUNT_PRODUCTCONSUMPTION);

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
		return "productConsumptionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PRODUCTCONSUMPTION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ProductConsumptionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the product consumption persistence.
	 */
	@Activate
	public void activate() {
		ProductConsumptionModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		ProductConsumptionModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			ProductConsumptionModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			ProductConsumptionModelImpl.UUID_COLUMN_BITMASK |
			ProductConsumptionModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathFetchByProductConsumptionKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByProductConsumptionKey",
			new String[] {String.class.getName()},
			ProductConsumptionModelImpl.PRODUCTCONSUMPTIONKEY_COLUMN_BITMASK);

		_finderPathCountByProductConsumptionKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByProductConsumptionKey",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountId",
			new String[] {Long.class.getName()},
			ProductConsumptionModelImpl.ACCOUNTID_COLUMN_BITMASK);

		_finderPathCountByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByProductEntryId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProductEntryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByProductEntryId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProductEntryId",
			new String[] {Long.class.getName()},
			ProductConsumptionModelImpl.PRODUCTENTRYID_COLUMN_BITMASK);

		_finderPathCountByProductEntryId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProductEntryId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByProductPurchaseId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProductPurchaseId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByProductPurchaseId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByProductPurchaseId", new String[] {Long.class.getName()},
			ProductConsumptionModelImpl.PRODUCTPURCHASEID_COLUMN_BITMASK);

		_finderPathCountByProductPurchaseId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByProductPurchaseId", new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByAI_PEI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAI_PEI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAI_PEI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAI_PEI",
			new String[] {Long.class.getName(), Long.class.getName()},
			ProductConsumptionModelImpl.ACCOUNTID_COLUMN_BITMASK |
			ProductConsumptionModelImpl.PRODUCTENTRYID_COLUMN_BITMASK);

		_finderPathCountByAI_PEI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAI_PEI",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByU_AI_PEI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_AI_PEI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByU_AI_PEI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductConsumptionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_AI_PEI",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			ProductConsumptionModelImpl.USERID_COLUMN_BITMASK |
			ProductConsumptionModelImpl.ACCOUNTID_COLUMN_BITMASK |
			ProductConsumptionModelImpl.PRODUCTENTRYID_COLUMN_BITMASK);

		_finderPathCountByU_AI_PEI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_AI_PEI",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

		ProductConsumptionUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		ProductConsumptionUtil.setPersistence(null);

		entityCache.removeCache(ProductConsumptionImpl.class.getName());

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
				"value.object.column.bitmask.enabled.com.liferay.osb.koroneiki.trunk.model.ProductConsumption"),
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

	private static final String _SQL_SELECT_PRODUCTCONSUMPTION =
		"SELECT productConsumption FROM ProductConsumption productConsumption";

	private static final String _SQL_SELECT_PRODUCTCONSUMPTION_WHERE =
		"SELECT productConsumption FROM ProductConsumption productConsumption WHERE ";

	private static final String _SQL_COUNT_PRODUCTCONSUMPTION =
		"SELECT COUNT(productConsumption) FROM ProductConsumption productConsumption";

	private static final String _SQL_COUNT_PRODUCTCONSUMPTION_WHERE =
		"SELECT COUNT(productConsumption) FROM ProductConsumption productConsumption WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"productConsumption.productConsumptionId";

	private static final String _FILTER_SQL_SELECT_PRODUCTCONSUMPTION_WHERE =
		"SELECT DISTINCT {productConsumption.*} FROM Koroneiki_ProductConsumption productConsumption WHERE ";

	private static final String
		_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {Koroneiki_ProductConsumption.*} FROM (SELECT DISTINCT productConsumption.productConsumptionId FROM Koroneiki_ProductConsumption productConsumption WHERE ";

	private static final String
		_FILTER_SQL_SELECT_PRODUCTCONSUMPTION_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN Koroneiki_ProductConsumption ON TEMP_TABLE.productConsumptionId = Koroneiki_ProductConsumption.productConsumptionId";

	private static final String _FILTER_SQL_COUNT_PRODUCTCONSUMPTION_WHERE =
		"SELECT COUNT(DISTINCT productConsumption.productConsumptionId) AS COUNT_VALUE FROM Koroneiki_ProductConsumption productConsumption WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "productConsumption";

	private static final String _FILTER_ENTITY_TABLE =
		"Koroneiki_ProductConsumption";

	private static final String _ORDER_BY_ENTITY_ALIAS = "productConsumption.";

	private static final String _ORDER_BY_ENTITY_TABLE =
		"Koroneiki_ProductConsumption.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ProductConsumption exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ProductConsumption exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ProductConsumptionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}