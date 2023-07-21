/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.persistence.impl;

import com.liferay.osb.koroneiki.trunk.exception.NoSuchProductPurchaseException;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.osb.koroneiki.trunk.model.impl.ProductPurchaseImpl;
import com.liferay.osb.koroneiki.trunk.model.impl.ProductPurchaseModelImpl;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductPurchasePersistence;
import com.liferay.osb.koroneiki.trunk.service.persistence.ProductPurchaseUtil;
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
 * The persistence implementation for the product purchase service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ProductPurchasePersistence.class)
public class ProductPurchasePersistenceImpl
	extends BasePersistenceImpl<ProductPurchase>
	implements ProductPurchasePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ProductPurchaseUtil</code> to access the product purchase persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ProductPurchaseImpl.class.getName();

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
	 * Returns all the product purchases where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product purchases where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product purchases where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product purchases where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator,
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

		List<ProductPurchase> list = null;

		if (useFinderCache) {
			list = (List<ProductPurchase>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductPurchase productPurchase : list) {
					if (!uuid.equals(productPurchase.getUuid())) {
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

			sb.append(_SQL_SELECT_PRODUCTPURCHASE_WHERE);

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
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
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

				list = (List<ProductPurchase>)QueryUtil.list(
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
	 * Returns the first product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase findByUuid_First(
			String uuid, OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = fetchByUuid_First(
			uuid, orderByComparator);

		if (productPurchase != null) {
			return productPurchase;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchProductPurchaseException(sb.toString());
	}

	/**
	 * Returns the first product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase fetchByUuid_First(
		String uuid, OrderByComparator<ProductPurchase> orderByComparator) {

		List<ProductPurchase> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase findByUuid_Last(
			String uuid, OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = fetchByUuid_Last(
			uuid, orderByComparator);

		if (productPurchase != null) {
			return productPurchase;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchProductPurchaseException(sb.toString());
	}

	/**
	 * Returns the last product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase fetchByUuid_Last(
		String uuid, OrderByComparator<ProductPurchase> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<ProductPurchase> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase[] findByUuid_PrevAndNext(
			long productPurchaseId, String uuid,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		uuid = Objects.toString(uuid, "");

		ProductPurchase productPurchase = findByPrimaryKey(productPurchaseId);

		Session session = null;

		try {
			session = openSession();

			ProductPurchase[] array = new ProductPurchaseImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, productPurchase, uuid, orderByComparator, true);

			array[1] = productPurchase;

			array[2] = getByUuid_PrevAndNext(
				session, productPurchase, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProductPurchase getByUuid_PrevAndNext(
		Session session, ProductPurchase productPurchase, String uuid,
		OrderByComparator<ProductPurchase> orderByComparator,
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

		sb.append(_SQL_SELECT_PRODUCTPURCHASE_WHERE);

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
			sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
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
						productPurchase)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductPurchase> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the product purchases that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByUuid(String uuid) {
		return filterFindByUuid(
			uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product purchases that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByUuid(
		String uuid, int start, int end) {

		return filterFindByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product purchases that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

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
			sb.append(_FILTER_SQL_SELECT_PRODUCTPURCHASE_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_1);
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
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_2);
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
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductPurchaseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductPurchase.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, ProductPurchaseImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, ProductPurchaseImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (bindUuid) {
				queryPos.add(uuid);
			}

			return (List<ProductPurchase>)QueryUtil.list(
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
	 * Returns the product purchases before and after the current product purchase in the ordered set of product purchases that the user has permission to view where uuid = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase[] filterFindByUuid_PrevAndNext(
			long productPurchaseId, String uuid,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid_PrevAndNext(
				productPurchaseId, uuid, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		ProductPurchase productPurchase = findByPrimaryKey(productPurchaseId);

		Session session = null;

		try {
			session = openSession();

			ProductPurchase[] array = new ProductPurchaseImpl[3];

			array[0] = filterGetByUuid_PrevAndNext(
				session, productPurchase, uuid, orderByComparator, true);

			array[1] = productPurchase;

			array[2] = filterGetByUuid_PrevAndNext(
				session, productPurchase, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProductPurchase filterGetByUuid_PrevAndNext(
		Session session, ProductPurchase productPurchase, String uuid,
		OrderByComparator<ProductPurchase> orderByComparator,
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
			sb.append(_FILTER_SQL_SELECT_PRODUCTPURCHASE_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_1);
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
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_2);
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
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductPurchaseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductPurchase.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, ProductPurchaseImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, ProductPurchaseImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productPurchase)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductPurchase> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product purchases where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (ProductPurchase productPurchase :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(productPurchase);
		}
	}

	/**
	 * Returns the number of product purchases where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product purchases
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PRODUCTPURCHASE_WHERE);

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
	 * Returns the number of product purchases that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product purchases that the user has permission to view
	 */
	@Override
	public int filterCountByUuid(String uuid) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUuid(uuid);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler sb = new StringBundler(2);

		sb.append(_FILTER_SQL_COUNT_PRODUCTPURCHASE_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3_SQL);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2_SQL);
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductPurchase.class.getName(),
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
		"productPurchase.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(productPurchase.uuid IS NULL OR productPurchase.uuid = '')";

	private static final String _FINDER_COLUMN_UUID_UUID_2_SQL =
		"productPurchase.uuid_ = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3_SQL =
		"(productPurchase.uuid_ IS NULL OR productPurchase.uuid_ = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the product purchases where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product purchases where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product purchases where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product purchases where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator,
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

		List<ProductPurchase> list = null;

		if (useFinderCache) {
			list = (List<ProductPurchase>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductPurchase productPurchase : list) {
					if (!uuid.equals(productPurchase.getUuid()) ||
						(companyId != productPurchase.getCompanyId())) {

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

			sb.append(_SQL_SELECT_PRODUCTPURCHASE_WHERE);

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
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
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

				list = (List<ProductPurchase>)QueryUtil.list(
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
	 * Returns the first product purchase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (productPurchase != null) {
			return productPurchase;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchProductPurchaseException(sb.toString());
	}

	/**
	 * Returns the first product purchase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<ProductPurchase> orderByComparator) {

		List<ProductPurchase> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product purchase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (productPurchase != null) {
			return productPurchase;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchProductPurchaseException(sb.toString());
	}

	/**
	 * Returns the last product purchase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<ProductPurchase> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<ProductPurchase> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase[] findByUuid_C_PrevAndNext(
			long productPurchaseId, String uuid, long companyId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		uuid = Objects.toString(uuid, "");

		ProductPurchase productPurchase = findByPrimaryKey(productPurchaseId);

		Session session = null;

		try {
			session = openSession();

			ProductPurchase[] array = new ProductPurchaseImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, productPurchase, uuid, companyId, orderByComparator,
				true);

			array[1] = productPurchase;

			array[2] = getByUuid_C_PrevAndNext(
				session, productPurchase, uuid, companyId, orderByComparator,
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

	protected ProductPurchase getByUuid_C_PrevAndNext(
		Session session, ProductPurchase productPurchase, String uuid,
		long companyId, OrderByComparator<ProductPurchase> orderByComparator,
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

		sb.append(_SQL_SELECT_PRODUCTPURCHASE_WHERE);

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
			sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
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
						productPurchase)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductPurchase> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the product purchases that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByUuid_C(
		String uuid, long companyId) {

		return filterFindByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product purchases that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return filterFindByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product purchases that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

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
			sb.append(_FILTER_SQL_SELECT_PRODUCTPURCHASE_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_1);
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
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_2);
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
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductPurchaseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductPurchase.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, ProductPurchaseImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, ProductPurchaseImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			if (bindUuid) {
				queryPos.add(uuid);
			}

			queryPos.add(companyId);

			return (List<ProductPurchase>)QueryUtil.list(
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
	 * Returns the product purchases before and after the current product purchase in the ordered set of product purchases that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase[] filterFindByUuid_C_PrevAndNext(
			long productPurchaseId, String uuid, long companyId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return findByUuid_C_PrevAndNext(
				productPurchaseId, uuid, companyId, orderByComparator);
		}

		uuid = Objects.toString(uuid, "");

		ProductPurchase productPurchase = findByPrimaryKey(productPurchaseId);

		Session session = null;

		try {
			session = openSession();

			ProductPurchase[] array = new ProductPurchaseImpl[3];

			array[0] = filterGetByUuid_C_PrevAndNext(
				session, productPurchase, uuid, companyId, orderByComparator,
				true);

			array[1] = productPurchase;

			array[2] = filterGetByUuid_C_PrevAndNext(
				session, productPurchase, uuid, companyId, orderByComparator,
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

	protected ProductPurchase filterGetByUuid_C_PrevAndNext(
		Session session, ProductPurchase productPurchase, String uuid,
		long companyId, OrderByComparator<ProductPurchase> orderByComparator,
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
			sb.append(_FILTER_SQL_SELECT_PRODUCTPURCHASE_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_1);
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
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_2);
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
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductPurchaseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductPurchase.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, ProductPurchaseImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, ProductPurchaseImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productPurchase)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductPurchase> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product purchases where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (ProductPurchase productPurchase :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(productPurchase);
		}
	}

	/**
	 * Returns the number of product purchases where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product purchases
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_PRODUCTPURCHASE_WHERE);

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
	 * Returns the number of product purchases that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product purchases that the user has permission to view
	 */
	@Override
	public int filterCountByUuid_C(String uuid, long companyId) {
		if (!InlineSQLHelperUtil.isEnabled(companyId, 0)) {
			return countByUuid_C(uuid, companyId);
		}

		uuid = Objects.toString(uuid, "");

		StringBundler sb = new StringBundler(3);

		sb.append(_FILTER_SQL_COUNT_PRODUCTPURCHASE_WHERE);

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
			sb.toString(), ProductPurchase.class.getName(),
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
		"productPurchase.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(productPurchase.uuid IS NULL OR productPurchase.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_2_SQL =
		"productPurchase.uuid_ = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3_SQL =
		"(productPurchase.uuid_ IS NULL OR productPurchase.uuid_ = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"productPurchase.companyId = ?";

	private FinderPath _finderPathFetchByProductPurchaseKey;
	private FinderPath _finderPathCountByProductPurchaseKey;

	/**
	 * Returns the product purchase where productPurchaseKey = &#63; or throws a <code>NoSuchProductPurchaseException</code> if it could not be found.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase findByProductPurchaseKey(String productPurchaseKey)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = fetchByProductPurchaseKey(
			productPurchaseKey);

		if (productPurchase == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("productPurchaseKey=");
			sb.append(productPurchaseKey);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchProductPurchaseException(sb.toString());
		}

		return productPurchase;
	}

	/**
	 * Returns the product purchase where productPurchaseKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase fetchByProductPurchaseKey(
		String productPurchaseKey) {

		return fetchByProductPurchaseKey(productPurchaseKey, true);
	}

	/**
	 * Returns the product purchase where productPurchaseKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase fetchByProductPurchaseKey(
		String productPurchaseKey, boolean useFinderCache) {

		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {productPurchaseKey};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByProductPurchaseKey, finderArgs, this);
		}

		if (result instanceof ProductPurchase) {
			ProductPurchase productPurchase = (ProductPurchase)result;

			if (!Objects.equals(
					productPurchaseKey,
					productPurchase.getProductPurchaseKey())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_PRODUCTPURCHASE_WHERE);

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_PRODUCTPURCHASEKEY_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(
					_FINDER_COLUMN_PRODUCTPURCHASEKEY_PRODUCTPURCHASEKEY_2);
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

				List<ProductPurchase> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByProductPurchaseKey, finderArgs,
							list);
					}
				}
				else {
					ProductPurchase productPurchase = list.get(0);

					result = productPurchase;

					cacheResult(productPurchase);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByProductPurchaseKey, finderArgs);
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
			return (ProductPurchase)result;
		}
	}

	/**
	 * Removes the product purchase where productPurchaseKey = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the product purchase that was removed
	 */
	@Override
	public ProductPurchase removeByProductPurchaseKey(String productPurchaseKey)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = findByProductPurchaseKey(
			productPurchaseKey);

		return remove(productPurchase);
	}

	/**
	 * Returns the number of product purchases where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the number of matching product purchases
	 */
	@Override
	public int countByProductPurchaseKey(String productPurchaseKey) {
		productPurchaseKey = Objects.toString(productPurchaseKey, "");

		FinderPath finderPath = _finderPathCountByProductPurchaseKey;

		Object[] finderArgs = new Object[] {productPurchaseKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PRODUCTPURCHASE_WHERE);

			boolean bindProductPurchaseKey = false;

			if (productPurchaseKey.isEmpty()) {
				sb.append(
					_FINDER_COLUMN_PRODUCTPURCHASEKEY_PRODUCTPURCHASEKEY_3);
			}
			else {
				bindProductPurchaseKey = true;

				sb.append(
					_FINDER_COLUMN_PRODUCTPURCHASEKEY_PRODUCTPURCHASEKEY_2);
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
		_FINDER_COLUMN_PRODUCTPURCHASEKEY_PRODUCTPURCHASEKEY_2 =
			"productPurchase.productPurchaseKey = ?";

	private static final String
		_FINDER_COLUMN_PRODUCTPURCHASEKEY_PRODUCTPURCHASEKEY_3 =
			"(productPurchase.productPurchaseKey IS NULL OR productPurchase.productPurchaseKey = '')";

	private FinderPath _finderPathWithPaginationFindByAccountId;
	private FinderPath _finderPathWithoutPaginationFindByAccountId;
	private FinderPath _finderPathCountByAccountId;

	/**
	 * Returns all the product purchases where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByAccountId(long accountId) {
		return findByAccountId(
			accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product purchases where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByAccountId(
		long accountId, int start, int end) {

		return findByAccountId(accountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product purchases where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return findByAccountId(accountId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product purchases where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator,
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

		List<ProductPurchase> list = null;

		if (useFinderCache) {
			list = (List<ProductPurchase>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductPurchase productPurchase : list) {
					if (accountId != productPurchase.getAccountId()) {
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

			sb.append(_SQL_SELECT_PRODUCTPURCHASE_WHERE);

			sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

				list = (List<ProductPurchase>)QueryUtil.list(
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
	 * Returns the first product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase findByAccountId_First(
			long accountId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = fetchByAccountId_First(
			accountId, orderByComparator);

		if (productPurchase != null) {
			return productPurchase;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchProductPurchaseException(sb.toString());
	}

	/**
	 * Returns the first product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase fetchByAccountId_First(
		long accountId, OrderByComparator<ProductPurchase> orderByComparator) {

		List<ProductPurchase> list = findByAccountId(
			accountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase findByAccountId_Last(
			long accountId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = fetchByAccountId_Last(
			accountId, orderByComparator);

		if (productPurchase != null) {
			return productPurchase;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchProductPurchaseException(sb.toString());
	}

	/**
	 * Returns the last product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase fetchByAccountId_Last(
		long accountId, OrderByComparator<ProductPurchase> orderByComparator) {

		int count = countByAccountId(accountId);

		if (count == 0) {
			return null;
		}

		List<ProductPurchase> list = findByAccountId(
			accountId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase[] findByAccountId_PrevAndNext(
			long productPurchaseId, long accountId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = findByPrimaryKey(productPurchaseId);

		Session session = null;

		try {
			session = openSession();

			ProductPurchase[] array = new ProductPurchaseImpl[3];

			array[0] = getByAccountId_PrevAndNext(
				session, productPurchase, accountId, orderByComparator, true);

			array[1] = productPurchase;

			array[2] = getByAccountId_PrevAndNext(
				session, productPurchase, accountId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProductPurchase getByAccountId_PrevAndNext(
		Session session, ProductPurchase productPurchase, long accountId,
		OrderByComparator<ProductPurchase> orderByComparator,
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

		sb.append(_SQL_SELECT_PRODUCTPURCHASE_WHERE);

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
			sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
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
						productPurchase)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductPurchase> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the product purchases that the user has permission to view where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByAccountId(long accountId) {
		return filterFindByAccountId(
			accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product purchases that the user has permission to view where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByAccountId(
		long accountId, int start, int end) {

		return filterFindByAccountId(accountId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product purchases that the user has permissions to view where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByAccountId(
		long accountId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

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
			sb.append(_FILTER_SQL_SELECT_PRODUCTPURCHASE_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_2);
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
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductPurchaseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductPurchase.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, ProductPurchaseImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, ProductPurchaseImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(accountId);

			return (List<ProductPurchase>)QueryUtil.list(
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
	 * Returns the product purchases before and after the current product purchase in the ordered set of product purchases that the user has permission to view where accountId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase[] filterFindByAccountId_PrevAndNext(
			long productPurchaseId, long accountId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByAccountId_PrevAndNext(
				productPurchaseId, accountId, orderByComparator);
		}

		ProductPurchase productPurchase = findByPrimaryKey(productPurchaseId);

		Session session = null;

		try {
			session = openSession();

			ProductPurchase[] array = new ProductPurchaseImpl[3];

			array[0] = filterGetByAccountId_PrevAndNext(
				session, productPurchase, accountId, orderByComparator, true);

			array[1] = productPurchase;

			array[2] = filterGetByAccountId_PrevAndNext(
				session, productPurchase, accountId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected ProductPurchase filterGetByAccountId_PrevAndNext(
		Session session, ProductPurchase productPurchase, long accountId,
		OrderByComparator<ProductPurchase> orderByComparator,
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
			sb.append(_FILTER_SQL_SELECT_PRODUCTPURCHASE_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_2);
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
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductPurchaseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductPurchase.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, ProductPurchaseImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, ProductPurchaseImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(accountId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productPurchase)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductPurchase> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product purchases where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	@Override
	public void removeByAccountId(long accountId) {
		for (ProductPurchase productPurchase :
				findByAccountId(
					accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(productPurchase);
		}
	}

	/**
	 * Returns the number of product purchases where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching product purchases
	 */
	@Override
	public int countByAccountId(long accountId) {
		FinderPath finderPath = _finderPathCountByAccountId;

		Object[] finderArgs = new Object[] {accountId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PRODUCTPURCHASE_WHERE);

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
	 * Returns the number of product purchases that the user has permission to view where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching product purchases that the user has permission to view
	 */
	@Override
	public int filterCountByAccountId(long accountId) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByAccountId(accountId);
		}

		StringBundler sb = new StringBundler(2);

		sb.append(_FILTER_SQL_COUNT_PRODUCTPURCHASE_WHERE);

		sb.append(_FINDER_COLUMN_ACCOUNTID_ACCOUNTID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductPurchase.class.getName(),
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
		"productPurchase.accountId = ?";

	private FinderPath _finderPathWithPaginationFindByProductEntryId;
	private FinderPath _finderPathWithoutPaginationFindByProductEntryId;
	private FinderPath _finderPathCountByProductEntryId;

	/**
	 * Returns all the product purchases where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByProductEntryId(long productEntryId) {
		return findByProductEntryId(
			productEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product purchases where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByProductEntryId(
		long productEntryId, int start, int end) {

		return findByProductEntryId(productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product purchases where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return findByProductEntryId(
			productEntryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product purchases where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator,
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

		List<ProductPurchase> list = null;

		if (useFinderCache) {
			list = (List<ProductPurchase>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductPurchase productPurchase : list) {
					if (productEntryId != productPurchase.getProductEntryId()) {
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

			sb.append(_SQL_SELECT_PRODUCTPURCHASE_WHERE);

			sb.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(productEntryId);

				list = (List<ProductPurchase>)QueryUtil.list(
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
	 * Returns the first product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase findByProductEntryId_First(
			long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = fetchByProductEntryId_First(
			productEntryId, orderByComparator);

		if (productPurchase != null) {
			return productPurchase;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productEntryId=");
		sb.append(productEntryId);

		sb.append("}");

		throw new NoSuchProductPurchaseException(sb.toString());
	}

	/**
	 * Returns the first product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase fetchByProductEntryId_First(
		long productEntryId,
		OrderByComparator<ProductPurchase> orderByComparator) {

		List<ProductPurchase> list = findByProductEntryId(
			productEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase findByProductEntryId_Last(
			long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = fetchByProductEntryId_Last(
			productEntryId, orderByComparator);

		if (productPurchase != null) {
			return productPurchase;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productEntryId=");
		sb.append(productEntryId);

		sb.append("}");

		throw new NoSuchProductPurchaseException(sb.toString());
	}

	/**
	 * Returns the last product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase fetchByProductEntryId_Last(
		long productEntryId,
		OrderByComparator<ProductPurchase> orderByComparator) {

		int count = countByProductEntryId(productEntryId);

		if (count == 0) {
			return null;
		}

		List<ProductPurchase> list = findByProductEntryId(
			productEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase[] findByProductEntryId_PrevAndNext(
			long productPurchaseId, long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = findByPrimaryKey(productPurchaseId);

		Session session = null;

		try {
			session = openSession();

			ProductPurchase[] array = new ProductPurchaseImpl[3];

			array[0] = getByProductEntryId_PrevAndNext(
				session, productPurchase, productEntryId, orderByComparator,
				true);

			array[1] = productPurchase;

			array[2] = getByProductEntryId_PrevAndNext(
				session, productPurchase, productEntryId, orderByComparator,
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

	protected ProductPurchase getByProductEntryId_PrevAndNext(
		Session session, ProductPurchase productPurchase, long productEntryId,
		OrderByComparator<ProductPurchase> orderByComparator,
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

		sb.append(_SQL_SELECT_PRODUCTPURCHASE_WHERE);

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
			sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
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
						productPurchase)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductPurchase> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the product purchases that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByProductEntryId(
		long productEntryId) {

		return filterFindByProductEntryId(
			productEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product purchases that the user has permission to view where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByProductEntryId(
		long productEntryId, int start, int end) {

		return filterFindByProductEntryId(productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product purchases that the user has permissions to view where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

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
			sb.append(_FILTER_SQL_SELECT_PRODUCTPURCHASE_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_2);
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
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductPurchaseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductPurchase.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, ProductPurchaseImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, ProductPurchaseImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(productEntryId);

			return (List<ProductPurchase>)QueryUtil.list(
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
	 * Returns the product purchases before and after the current product purchase in the ordered set of product purchases that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase[] filterFindByProductEntryId_PrevAndNext(
			long productPurchaseId, long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByProductEntryId_PrevAndNext(
				productPurchaseId, productEntryId, orderByComparator);
		}

		ProductPurchase productPurchase = findByPrimaryKey(productPurchaseId);

		Session session = null;

		try {
			session = openSession();

			ProductPurchase[] array = new ProductPurchaseImpl[3];

			array[0] = filterGetByProductEntryId_PrevAndNext(
				session, productPurchase, productEntryId, orderByComparator,
				true);

			array[1] = productPurchase;

			array[2] = filterGetByProductEntryId_PrevAndNext(
				session, productPurchase, productEntryId, orderByComparator,
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

	protected ProductPurchase filterGetByProductEntryId_PrevAndNext(
		Session session, ProductPurchase productPurchase, long productEntryId,
		OrderByComparator<ProductPurchase> orderByComparator,
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
			sb.append(_FILTER_SQL_SELECT_PRODUCTPURCHASE_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_2);
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
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductPurchaseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductPurchase.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, ProductPurchaseImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, ProductPurchaseImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(productEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productPurchase)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductPurchase> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product purchases where productEntryId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 */
	@Override
	public void removeByProductEntryId(long productEntryId) {
		for (ProductPurchase productPurchase :
				findByProductEntryId(
					productEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(productPurchase);
		}
	}

	/**
	 * Returns the number of product purchases where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching product purchases
	 */
	@Override
	public int countByProductEntryId(long productEntryId) {
		FinderPath finderPath = _finderPathCountByProductEntryId;

		Object[] finderArgs = new Object[] {productEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PRODUCTPURCHASE_WHERE);

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
	 * Returns the number of product purchases that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching product purchases that the user has permission to view
	 */
	@Override
	public int filterCountByProductEntryId(long productEntryId) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByProductEntryId(productEntryId);
		}

		StringBundler sb = new StringBundler(2);

		sb.append(_FILTER_SQL_COUNT_PRODUCTPURCHASE_WHERE);

		sb.append(_FINDER_COLUMN_PRODUCTENTRYID_PRODUCTENTRYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductPurchase.class.getName(),
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
		"productPurchase.productEntryId = ?";

	private FinderPath _finderPathWithPaginationFindByAI_PEI;
	private FinderPath _finderPathWithoutPaginationFindByAI_PEI;
	private FinderPath _finderPathCountByAI_PEI;

	/**
	 * Returns all the product purchases where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByAI_PEI(
		long accountId, long productEntryId) {

		return findByAI_PEI(
			accountId, productEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the product purchases where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByAI_PEI(
		long accountId, long productEntryId, int start, int end) {

		return findByAI_PEI(accountId, productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product purchases where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return findByAI_PEI(
			accountId, productEntryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product purchases where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product purchases
	 */
	@Override
	public List<ProductPurchase> findByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator,
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

		List<ProductPurchase> list = null;

		if (useFinderCache) {
			list = (List<ProductPurchase>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductPurchase productPurchase : list) {
					if ((accountId != productPurchase.getAccountId()) ||
						(productEntryId !=
							productPurchase.getProductEntryId())) {

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

			sb.append(_SQL_SELECT_PRODUCTPURCHASE_WHERE);

			sb.append(_FINDER_COLUMN_AI_PEI_ACCOUNTID_2);

			sb.append(_FINDER_COLUMN_AI_PEI_PRODUCTENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(accountId);

				queryPos.add(productEntryId);

				list = (List<ProductPurchase>)QueryUtil.list(
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
	 * Returns the first product purchase in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase findByAI_PEI_First(
			long accountId, long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = fetchByAI_PEI_First(
			accountId, productEntryId, orderByComparator);

		if (productPurchase != null) {
			return productPurchase;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append(", productEntryId=");
		sb.append(productEntryId);

		sb.append("}");

		throw new NoSuchProductPurchaseException(sb.toString());
	}

	/**
	 * Returns the first product purchase in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase fetchByAI_PEI_First(
		long accountId, long productEntryId,
		OrderByComparator<ProductPurchase> orderByComparator) {

		List<ProductPurchase> list = findByAI_PEI(
			accountId, productEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product purchase in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase findByAI_PEI_Last(
			long accountId, long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = fetchByAI_PEI_Last(
			accountId, productEntryId, orderByComparator);

		if (productPurchase != null) {
			return productPurchase;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("accountId=");
		sb.append(accountId);

		sb.append(", productEntryId=");
		sb.append(productEntryId);

		sb.append("}");

		throw new NoSuchProductPurchaseException(sb.toString());
	}

	/**
	 * Returns the last product purchase in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	@Override
	public ProductPurchase fetchByAI_PEI_Last(
		long accountId, long productEntryId,
		OrderByComparator<ProductPurchase> orderByComparator) {

		int count = countByAI_PEI(accountId, productEntryId);

		if (count == 0) {
			return null;
		}

		List<ProductPurchase> list = findByAI_PEI(
			accountId, productEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase[] findByAI_PEI_PrevAndNext(
			long productPurchaseId, long accountId, long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = findByPrimaryKey(productPurchaseId);

		Session session = null;

		try {
			session = openSession();

			ProductPurchase[] array = new ProductPurchaseImpl[3];

			array[0] = getByAI_PEI_PrevAndNext(
				session, productPurchase, accountId, productEntryId,
				orderByComparator, true);

			array[1] = productPurchase;

			array[2] = getByAI_PEI_PrevAndNext(
				session, productPurchase, accountId, productEntryId,
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

	protected ProductPurchase getByAI_PEI_PrevAndNext(
		Session session, ProductPurchase productPurchase, long accountId,
		long productEntryId,
		OrderByComparator<ProductPurchase> orderByComparator,
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

		sb.append(_SQL_SELECT_PRODUCTPURCHASE_WHERE);

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
			sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
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
						productPurchase)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductPurchase> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the product purchases that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByAI_PEI(
		long accountId, long productEntryId) {

		return filterFindByAI_PEI(
			accountId, productEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the product purchases that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByAI_PEI(
		long accountId, long productEntryId, int start, int end) {

		return filterFindByAI_PEI(accountId, productEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product purchases that the user has permissions to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product purchases that the user has permission to view
	 */
	@Override
	public List<ProductPurchase> filterFindByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

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
			sb.append(_FILTER_SQL_SELECT_PRODUCTPURCHASE_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_AI_PEI_ACCOUNTID_2);

		sb.append(_FINDER_COLUMN_AI_PEI_PRODUCTENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_2);
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
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductPurchaseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductPurchase.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, ProductPurchaseImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, ProductPurchaseImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(accountId);

			queryPos.add(productEntryId);

			return (List<ProductPurchase>)QueryUtil.list(
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
	 * Returns the product purchases before and after the current product purchase in the ordered set of product purchases that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase[] filterFindByAI_PEI_PrevAndNext(
			long productPurchaseId, long accountId, long productEntryId,
			OrderByComparator<ProductPurchase> orderByComparator)
		throws NoSuchProductPurchaseException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByAI_PEI_PrevAndNext(
				productPurchaseId, accountId, productEntryId,
				orderByComparator);
		}

		ProductPurchase productPurchase = findByPrimaryKey(productPurchaseId);

		Session session = null;

		try {
			session = openSession();

			ProductPurchase[] array = new ProductPurchaseImpl[3];

			array[0] = filterGetByAI_PEI_PrevAndNext(
				session, productPurchase, accountId, productEntryId,
				orderByComparator, true);

			array[1] = productPurchase;

			array[2] = filterGetByAI_PEI_PrevAndNext(
				session, productPurchase, accountId, productEntryId,
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

	protected ProductPurchase filterGetByAI_PEI_PrevAndNext(
		Session session, ProductPurchase productPurchase, long accountId,
		long productEntryId,
		OrderByComparator<ProductPurchase> orderByComparator,
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
			sb.append(_FILTER_SQL_SELECT_PRODUCTPURCHASE_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_AI_PEI_ACCOUNTID_2);

		sb.append(_FINDER_COLUMN_AI_PEI_PRODUCTENTRYID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_2);
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
				sb.append(ProductPurchaseModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(ProductPurchaseModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductPurchase.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, ProductPurchaseImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, ProductPurchaseImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(accountId);

		queryPos.add(productEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productPurchase)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductPurchase> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product purchases where accountId = &#63; and productEntryId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 */
	@Override
	public void removeByAI_PEI(long accountId, long productEntryId) {
		for (ProductPurchase productPurchase :
				findByAI_PEI(
					accountId, productEntryId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(productPurchase);
		}
	}

	/**
	 * Returns the number of product purchases where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product purchases
	 */
	@Override
	public int countByAI_PEI(long accountId, long productEntryId) {
		FinderPath finderPath = _finderPathCountByAI_PEI;

		Object[] finderArgs = new Object[] {accountId, productEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_PRODUCTPURCHASE_WHERE);

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
	 * Returns the number of product purchases that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product purchases that the user has permission to view
	 */
	@Override
	public int filterCountByAI_PEI(long accountId, long productEntryId) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByAI_PEI(accountId, productEntryId);
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_FILTER_SQL_COUNT_PRODUCTPURCHASE_WHERE);

		sb.append(_FINDER_COLUMN_AI_PEI_ACCOUNTID_2);

		sb.append(_FINDER_COLUMN_AI_PEI_PRODUCTENTRYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), ProductPurchase.class.getName(),
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
		"productPurchase.accountId = ? AND ";

	private static final String _FINDER_COLUMN_AI_PEI_PRODUCTENTRYID_2 =
		"productPurchase.productEntryId = ?";

	public ProductPurchasePersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(ProductPurchase.class);

		setModelImplClass(ProductPurchaseImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the product purchase in the entity cache if it is enabled.
	 *
	 * @param productPurchase the product purchase
	 */
	@Override
	public void cacheResult(ProductPurchase productPurchase) {
		entityCache.putResult(
			entityCacheEnabled, ProductPurchaseImpl.class,
			productPurchase.getPrimaryKey(), productPurchase);

		finderCache.putResult(
			_finderPathFetchByProductPurchaseKey,
			new Object[] {productPurchase.getProductPurchaseKey()},
			productPurchase);

		productPurchase.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the product purchases in the entity cache if it is enabled.
	 *
	 * @param productPurchases the product purchases
	 */
	@Override
	public void cacheResult(List<ProductPurchase> productPurchases) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (productPurchases.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ProductPurchase productPurchase : productPurchases) {
			if (entityCache.getResult(
					entityCacheEnabled, ProductPurchaseImpl.class,
					productPurchase.getPrimaryKey()) == null) {

				cacheResult(productPurchase);
			}
			else {
				productPurchase.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all product purchases.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProductPurchaseImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the product purchase.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProductPurchase productPurchase) {
		entityCache.removeResult(
			entityCacheEnabled, ProductPurchaseImpl.class,
			productPurchase.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(ProductPurchaseModelImpl)productPurchase, true);
	}

	@Override
	public void clearCache(List<ProductPurchase> productPurchases) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProductPurchase productPurchase : productPurchases) {
			entityCache.removeResult(
				entityCacheEnabled, ProductPurchaseImpl.class,
				productPurchase.getPrimaryKey());

			clearUniqueFindersCache(
				(ProductPurchaseModelImpl)productPurchase, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, ProductPurchaseImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		ProductPurchaseModelImpl productPurchaseModelImpl) {

		Object[] args = new Object[] {
			productPurchaseModelImpl.getProductPurchaseKey()
		};

		finderCache.putResult(
			_finderPathCountByProductPurchaseKey, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByProductPurchaseKey, args,
			productPurchaseModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		ProductPurchaseModelImpl productPurchaseModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				productPurchaseModelImpl.getProductPurchaseKey()
			};

			finderCache.removeResult(
				_finderPathCountByProductPurchaseKey, args);
			finderCache.removeResult(
				_finderPathFetchByProductPurchaseKey, args);
		}

		if ((productPurchaseModelImpl.getColumnBitmask() &
			 _finderPathFetchByProductPurchaseKey.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				productPurchaseModelImpl.getOriginalProductPurchaseKey()
			};

			finderCache.removeResult(
				_finderPathCountByProductPurchaseKey, args);
			finderCache.removeResult(
				_finderPathFetchByProductPurchaseKey, args);
		}
	}

	/**
	 * Creates a new product purchase with the primary key. Does not add the product purchase to the database.
	 *
	 * @param productPurchaseId the primary key for the new product purchase
	 * @return the new product purchase
	 */
	@Override
	public ProductPurchase create(long productPurchaseId) {
		ProductPurchase productPurchase = new ProductPurchaseImpl();

		productPurchase.setNew(true);
		productPurchase.setPrimaryKey(productPurchaseId);

		String uuid = PortalUUIDUtil.generate();

		productPurchase.setUuid(uuid);

		productPurchase.setCompanyId(CompanyThreadLocal.getCompanyId());

		return productPurchase;
	}

	/**
	 * Removes the product purchase with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productPurchaseId the primary key of the product purchase
	 * @return the product purchase that was removed
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase remove(long productPurchaseId)
		throws NoSuchProductPurchaseException {

		return remove((Serializable)productPurchaseId);
	}

	/**
	 * Removes the product purchase with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the product purchase
	 * @return the product purchase that was removed
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase remove(Serializable primaryKey)
		throws NoSuchProductPurchaseException {

		Session session = null;

		try {
			session = openSession();

			ProductPurchase productPurchase = (ProductPurchase)session.get(
				ProductPurchaseImpl.class, primaryKey);

			if (productPurchase == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProductPurchaseException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(productPurchase);
		}
		catch (NoSuchProductPurchaseException noSuchEntityException) {
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
	protected ProductPurchase removeImpl(ProductPurchase productPurchase) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(productPurchase)) {
				productPurchase = (ProductPurchase)session.get(
					ProductPurchaseImpl.class,
					productPurchase.getPrimaryKeyObj());
			}

			if (productPurchase != null) {
				session.delete(productPurchase);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (productPurchase != null) {
			clearCache(productPurchase);
		}

		return productPurchase;
	}

	@Override
	public ProductPurchase updateImpl(ProductPurchase productPurchase) {
		boolean isNew = productPurchase.isNew();

		if (!(productPurchase instanceof ProductPurchaseModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(productPurchase.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					productPurchase);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in productPurchase proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProductPurchase implementation " +
					productPurchase.getClass());
		}

		ProductPurchaseModelImpl productPurchaseModelImpl =
			(ProductPurchaseModelImpl)productPurchase;

		if (Validator.isNull(productPurchase.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			productPurchase.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (productPurchase.getCreateDate() == null)) {
			if (serviceContext == null) {
				productPurchase.setCreateDate(date);
			}
			else {
				productPurchase.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!productPurchaseModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				productPurchase.setModifiedDate(date);
			}
			else {
				productPurchase.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(productPurchase);

				productPurchase.setNew(false);
			}
			else {
				productPurchase = (ProductPurchase)session.merge(
					productPurchase);
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
			Object[] args = new Object[] {productPurchaseModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				productPurchaseModelImpl.getUuid(),
				productPurchaseModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {productPurchaseModelImpl.getAccountId()};

			finderCache.removeResult(_finderPathCountByAccountId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAccountId, args);

			args = new Object[] {productPurchaseModelImpl.getProductEntryId()};

			finderCache.removeResult(_finderPathCountByProductEntryId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByProductEntryId, args);

			args = new Object[] {
				productPurchaseModelImpl.getAccountId(),
				productPurchaseModelImpl.getProductEntryId()
			};

			finderCache.removeResult(_finderPathCountByAI_PEI, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByAI_PEI, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((productPurchaseModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					productPurchaseModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {productPurchaseModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((productPurchaseModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					productPurchaseModelImpl.getOriginalUuid(),
					productPurchaseModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					productPurchaseModelImpl.getUuid(),
					productPurchaseModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((productPurchaseModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByAccountId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					productPurchaseModelImpl.getOriginalAccountId()
				};

				finderCache.removeResult(_finderPathCountByAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountId, args);

				args = new Object[] {productPurchaseModelImpl.getAccountId()};

				finderCache.removeResult(_finderPathCountByAccountId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAccountId, args);
			}

			if ((productPurchaseModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByProductEntryId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					productPurchaseModelImpl.getOriginalProductEntryId()
				};

				finderCache.removeResult(
					_finderPathCountByProductEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductEntryId, args);

				args = new Object[] {
					productPurchaseModelImpl.getProductEntryId()
				};

				finderCache.removeResult(
					_finderPathCountByProductEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductEntryId, args);
			}

			if ((productPurchaseModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByAI_PEI.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					productPurchaseModelImpl.getOriginalAccountId(),
					productPurchaseModelImpl.getOriginalProductEntryId()
				};

				finderCache.removeResult(_finderPathCountByAI_PEI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAI_PEI, args);

				args = new Object[] {
					productPurchaseModelImpl.getAccountId(),
					productPurchaseModelImpl.getProductEntryId()
				};

				finderCache.removeResult(_finderPathCountByAI_PEI, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByAI_PEI, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, ProductPurchaseImpl.class,
			productPurchase.getPrimaryKey(), productPurchase, false);

		clearUniqueFindersCache(productPurchaseModelImpl, false);
		cacheUniqueFindersCache(productPurchaseModelImpl);

		productPurchase.resetOriginalValues();

		return productPurchase;
	}

	/**
	 * Returns the product purchase with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the product purchase
	 * @return the product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProductPurchaseException {

		ProductPurchase productPurchase = fetchByPrimaryKey(primaryKey);

		if (productPurchase == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProductPurchaseException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return productPurchase;
	}

	/**
	 * Returns the product purchase with the primary key or throws a <code>NoSuchProductPurchaseException</code> if it could not be found.
	 *
	 * @param productPurchaseId the primary key of the product purchase
	 * @return the product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase findByPrimaryKey(long productPurchaseId)
		throws NoSuchProductPurchaseException {

		return findByPrimaryKey((Serializable)productPurchaseId);
	}

	/**
	 * Returns the product purchase with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productPurchaseId the primary key of the product purchase
	 * @return the product purchase, or <code>null</code> if a product purchase with the primary key could not be found
	 */
	@Override
	public ProductPurchase fetchByPrimaryKey(long productPurchaseId) {
		return fetchByPrimaryKey((Serializable)productPurchaseId);
	}

	/**
	 * Returns all the product purchases.
	 *
	 * @return the product purchases
	 */
	@Override
	public List<ProductPurchase> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product purchases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @return the range of product purchases
	 */
	@Override
	public List<ProductPurchase> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the product purchases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product purchases
	 */
	@Override
	public List<ProductPurchase> findAll(
		int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product purchases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductPurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product purchases
	 * @param end the upper bound of the range of product purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of product purchases
	 */
	@Override
	public List<ProductPurchase> findAll(
		int start, int end,
		OrderByComparator<ProductPurchase> orderByComparator,
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

		List<ProductPurchase> list = null;

		if (useFinderCache) {
			list = (List<ProductPurchase>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PRODUCTPURCHASE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PRODUCTPURCHASE;

				sql = sql.concat(ProductPurchaseModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ProductPurchase>)QueryUtil.list(
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
	 * Removes all the product purchases from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProductPurchase productPurchase : findAll()) {
			remove(productPurchase);
		}
	}

	/**
	 * Returns the number of product purchases.
	 *
	 * @return the number of product purchases
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_PRODUCTPURCHASE);

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
		return "productPurchaseId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PRODUCTPURCHASE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ProductPurchaseModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the product purchase persistence.
	 */
	@Activate
	public void activate() {
		ProductPurchaseModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		ProductPurchaseModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ProductPurchaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ProductPurchaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ProductPurchaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ProductPurchaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			ProductPurchaseModelImpl.UUID_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ProductPurchaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ProductPurchaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			ProductPurchaseModelImpl.UUID_COLUMN_BITMASK |
			ProductPurchaseModelImpl.COMPANYID_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathFetchByProductPurchaseKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ProductPurchaseImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByProductPurchaseKey",
			new String[] {String.class.getName()},
			ProductPurchaseModelImpl.PRODUCTPURCHASEKEY_COLUMN_BITMASK);

		_finderPathCountByProductPurchaseKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByProductPurchaseKey", new String[] {String.class.getName()});

		_finderPathWithPaginationFindByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ProductPurchaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAccountId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ProductPurchaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAccountId",
			new String[] {Long.class.getName()},
			ProductPurchaseModelImpl.ACCOUNTID_COLUMN_BITMASK);

		_finderPathCountByAccountId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByProductEntryId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ProductPurchaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProductEntryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByProductEntryId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ProductPurchaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProductEntryId",
			new String[] {Long.class.getName()},
			ProductPurchaseModelImpl.PRODUCTENTRYID_COLUMN_BITMASK);

		_finderPathCountByProductEntryId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProductEntryId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByAI_PEI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ProductPurchaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAI_PEI",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByAI_PEI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, ProductPurchaseImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAI_PEI",
			new String[] {Long.class.getName(), Long.class.getName()},
			ProductPurchaseModelImpl.ACCOUNTID_COLUMN_BITMASK |
			ProductPurchaseModelImpl.PRODUCTENTRYID_COLUMN_BITMASK);

		_finderPathCountByAI_PEI = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAI_PEI",
			new String[] {Long.class.getName(), Long.class.getName()});

		ProductPurchaseUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		ProductPurchaseUtil.setPersistence(null);

		entityCache.removeCache(ProductPurchaseImpl.class.getName());

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
				"value.object.column.bitmask.enabled.com.liferay.osb.koroneiki.trunk.model.ProductPurchase"),
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

	private static final String _SQL_SELECT_PRODUCTPURCHASE =
		"SELECT productPurchase FROM ProductPurchase productPurchase";

	private static final String _SQL_SELECT_PRODUCTPURCHASE_WHERE =
		"SELECT productPurchase FROM ProductPurchase productPurchase WHERE ";

	private static final String _SQL_COUNT_PRODUCTPURCHASE =
		"SELECT COUNT(productPurchase) FROM ProductPurchase productPurchase";

	private static final String _SQL_COUNT_PRODUCTPURCHASE_WHERE =
		"SELECT COUNT(productPurchase) FROM ProductPurchase productPurchase WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"productPurchase.productPurchaseId";

	private static final String _FILTER_SQL_SELECT_PRODUCTPURCHASE_WHERE =
		"SELECT DISTINCT {productPurchase.*} FROM Koroneiki_ProductPurchase productPurchase WHERE ";

	private static final String
		_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {Koroneiki_ProductPurchase.*} FROM (SELECT DISTINCT productPurchase.productPurchaseId FROM Koroneiki_ProductPurchase productPurchase WHERE ";

	private static final String
		_FILTER_SQL_SELECT_PRODUCTPURCHASE_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN Koroneiki_ProductPurchase ON TEMP_TABLE.productPurchaseId = Koroneiki_ProductPurchase.productPurchaseId";

	private static final String _FILTER_SQL_COUNT_PRODUCTPURCHASE_WHERE =
		"SELECT COUNT(DISTINCT productPurchase.productPurchaseId) AS COUNT_VALUE FROM Koroneiki_ProductPurchase productPurchase WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "productPurchase";

	private static final String _FILTER_ENTITY_TABLE =
		"Koroneiki_ProductPurchase";

	private static final String _ORDER_BY_ENTITY_ALIAS = "productPurchase.";

	private static final String _ORDER_BY_ENTITY_TABLE =
		"Koroneiki_ProductPurchase.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ProductPurchase exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ProductPurchase exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ProductPurchasePersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

}