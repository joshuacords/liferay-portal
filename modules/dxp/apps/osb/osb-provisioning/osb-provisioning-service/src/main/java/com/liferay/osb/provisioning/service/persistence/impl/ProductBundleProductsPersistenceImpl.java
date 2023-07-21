/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.service.persistence.impl;

import com.liferay.osb.provisioning.exception.NoSuchProductBundleProductsException;
import com.liferay.osb.provisioning.model.ProductBundleProducts;
import com.liferay.osb.provisioning.model.impl.ProductBundleProductsImpl;
import com.liferay.osb.provisioning.model.impl.ProductBundleProductsModelImpl;
import com.liferay.osb.provisioning.service.persistence.ProductBundleProductsPK;
import com.liferay.osb.provisioning.service.persistence.ProductBundleProductsPersistence;
import com.liferay.osb.provisioning.service.persistence.ProductBundleProductsUtil;
import com.liferay.osb.provisioning.service.persistence.impl.constants.ProvisioningPersistenceConstants;
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
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the product bundle products service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ProductBundleProductsPersistence.class)
public class ProductBundleProductsPersistenceImpl
	extends BasePersistenceImpl<ProductBundleProducts>
	implements ProductBundleProductsPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ProductBundleProductsUtil</code> to access the product bundle products persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ProductBundleProductsImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByProductBundleId;
	private FinderPath _finderPathWithoutPaginationFindByProductBundleId;
	private FinderPath _finderPathCountByProductBundleId;

	/**
	 * Returns all the product bundle productses where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the matching product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findByProductBundleId(
		long productBundleId) {

		return findByProductBundleId(
			productBundleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product bundle productses where productBundleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param productBundleId the product bundle ID
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @return the range of matching product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findByProductBundleId(
		long productBundleId, int start, int end) {

		return findByProductBundleId(productBundleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product bundle productses where productBundleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param productBundleId the product bundle ID
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findByProductBundleId(
		long productBundleId, int start, int end,
		OrderByComparator<ProductBundleProducts> orderByComparator) {

		return findByProductBundleId(
			productBundleId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product bundle productses where productBundleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param productBundleId the product bundle ID
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findByProductBundleId(
		long productBundleId, int start, int end,
		OrderByComparator<ProductBundleProducts> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByProductBundleId;
				finderArgs = new Object[] {productBundleId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByProductBundleId;
			finderArgs = new Object[] {
				productBundleId, start, end, orderByComparator
			};
		}

		List<ProductBundleProducts> list = null;

		if (useFinderCache) {
			list = (List<ProductBundleProducts>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductBundleProducts productBundleProducts : list) {
					if (productBundleId !=
							productBundleProducts.getProductBundleId()) {

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

			sb.append(_SQL_SELECT_PRODUCTBUNDLEPRODUCTS_WHERE);

			sb.append(_FINDER_COLUMN_PRODUCTBUNDLEID_PRODUCTBUNDLEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(ProductBundleProductsModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(productBundleId);

				list = (List<ProductBundleProducts>)QueryUtil.list(
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
	 * Returns the first product bundle products in the ordered set where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle products
	 * @throws NoSuchProductBundleProductsException if a matching product bundle products could not be found
	 */
	@Override
	public ProductBundleProducts findByProductBundleId_First(
			long productBundleId,
			OrderByComparator<ProductBundleProducts> orderByComparator)
		throws NoSuchProductBundleProductsException {

		ProductBundleProducts productBundleProducts =
			fetchByProductBundleId_First(productBundleId, orderByComparator);

		if (productBundleProducts != null) {
			return productBundleProducts;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productBundleId=");
		sb.append(productBundleId);

		sb.append("}");

		throw new NoSuchProductBundleProductsException(sb.toString());
	}

	/**
	 * Returns the first product bundle products in the ordered set where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle products, or <code>null</code> if a matching product bundle products could not be found
	 */
	@Override
	public ProductBundleProducts fetchByProductBundleId_First(
		long productBundleId,
		OrderByComparator<ProductBundleProducts> orderByComparator) {

		List<ProductBundleProducts> list = findByProductBundleId(
			productBundleId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product bundle products in the ordered set where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle products
	 * @throws NoSuchProductBundleProductsException if a matching product bundle products could not be found
	 */
	@Override
	public ProductBundleProducts findByProductBundleId_Last(
			long productBundleId,
			OrderByComparator<ProductBundleProducts> orderByComparator)
		throws NoSuchProductBundleProductsException {

		ProductBundleProducts productBundleProducts =
			fetchByProductBundleId_Last(productBundleId, orderByComparator);

		if (productBundleProducts != null) {
			return productBundleProducts;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productBundleId=");
		sb.append(productBundleId);

		sb.append("}");

		throw new NoSuchProductBundleProductsException(sb.toString());
	}

	/**
	 * Returns the last product bundle products in the ordered set where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle products, or <code>null</code> if a matching product bundle products could not be found
	 */
	@Override
	public ProductBundleProducts fetchByProductBundleId_Last(
		long productBundleId,
		OrderByComparator<ProductBundleProducts> orderByComparator) {

		int count = countByProductBundleId(productBundleId);

		if (count == 0) {
			return null;
		}

		List<ProductBundleProducts> list = findByProductBundleId(
			productBundleId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product bundle productses before and after the current product bundle products in the ordered set where productBundleId = &#63;.
	 *
	 * @param productBundleProductsPK the primary key of the current product bundle products
	 * @param productBundleId the product bundle ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product bundle products
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	@Override
	public ProductBundleProducts[] findByProductBundleId_PrevAndNext(
			ProductBundleProductsPK productBundleProductsPK,
			long productBundleId,
			OrderByComparator<ProductBundleProducts> orderByComparator)
		throws NoSuchProductBundleProductsException {

		ProductBundleProducts productBundleProducts = findByPrimaryKey(
			productBundleProductsPK);

		Session session = null;

		try {
			session = openSession();

			ProductBundleProducts[] array = new ProductBundleProductsImpl[3];

			array[0] = getByProductBundleId_PrevAndNext(
				session, productBundleProducts, productBundleId,
				orderByComparator, true);

			array[1] = productBundleProducts;

			array[2] = getByProductBundleId_PrevAndNext(
				session, productBundleProducts, productBundleId,
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

	protected ProductBundleProducts getByProductBundleId_PrevAndNext(
		Session session, ProductBundleProducts productBundleProducts,
		long productBundleId,
		OrderByComparator<ProductBundleProducts> orderByComparator,
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

		sb.append(_SQL_SELECT_PRODUCTBUNDLEPRODUCTS_WHERE);

		sb.append(_FINDER_COLUMN_PRODUCTBUNDLEID_PRODUCTBUNDLEID_2);

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
			sb.append(ProductBundleProductsModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(productBundleId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						productBundleProducts)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductBundleProducts> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product bundle productses where productBundleId = &#63; from the database.
	 *
	 * @param productBundleId the product bundle ID
	 */
	@Override
	public void removeByProductBundleId(long productBundleId) {
		for (ProductBundleProducts productBundleProducts :
				findByProductBundleId(
					productBundleId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(productBundleProducts);
		}
	}

	/**
	 * Returns the number of product bundle productses where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the number of matching product bundle productses
	 */
	@Override
	public int countByProductBundleId(long productBundleId) {
		FinderPath finderPath = _finderPathCountByProductBundleId;

		Object[] finderArgs = new Object[] {productBundleId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PRODUCTBUNDLEPRODUCTS_WHERE);

			sb.append(_FINDER_COLUMN_PRODUCTBUNDLEID_PRODUCTBUNDLEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(productBundleId);

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
		_FINDER_COLUMN_PRODUCTBUNDLEID_PRODUCTBUNDLEID_2 =
			"productBundleProducts.id.productBundleId = ?";

	private FinderPath _finderPathWithPaginationFindByProductKey;
	private FinderPath _finderPathWithoutPaginationFindByProductKey;
	private FinderPath _finderPathCountByProductKey;

	/**
	 * Returns all the product bundle productses where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @return the matching product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findByProductKey(String productKey) {
		return findByProductKey(
			productKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product bundle productses where productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param productKey the product key
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @return the range of matching product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findByProductKey(
		String productKey, int start, int end) {

		return findByProductKey(productKey, start, end, null);
	}

	/**
	 * Returns an ordered range of all the product bundle productses where productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param productKey the product key
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findByProductKey(
		String productKey, int start, int end,
		OrderByComparator<ProductBundleProducts> orderByComparator) {

		return findByProductKey(
			productKey, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product bundle productses where productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param productKey the product key
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findByProductKey(
		String productKey, int start, int end,
		OrderByComparator<ProductBundleProducts> orderByComparator,
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

		List<ProductBundleProducts> list = null;

		if (useFinderCache) {
			list = (List<ProductBundleProducts>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (ProductBundleProducts productBundleProducts : list) {
					if (!productKey.equals(
							productBundleProducts.getProductKey())) {

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

			sb.append(_SQL_SELECT_PRODUCTBUNDLEPRODUCTS_WHERE);

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
				sb.append(ProductBundleProductsModelImpl.ORDER_BY_JPQL);
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

				list = (List<ProductBundleProducts>)QueryUtil.list(
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
	 * Returns the first product bundle products in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle products
	 * @throws NoSuchProductBundleProductsException if a matching product bundle products could not be found
	 */
	@Override
	public ProductBundleProducts findByProductKey_First(
			String productKey,
			OrderByComparator<ProductBundleProducts> orderByComparator)
		throws NoSuchProductBundleProductsException {

		ProductBundleProducts productBundleProducts = fetchByProductKey_First(
			productKey, orderByComparator);

		if (productBundleProducts != null) {
			return productBundleProducts;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productKey=");
		sb.append(productKey);

		sb.append("}");

		throw new NoSuchProductBundleProductsException(sb.toString());
	}

	/**
	 * Returns the first product bundle products in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle products, or <code>null</code> if a matching product bundle products could not be found
	 */
	@Override
	public ProductBundleProducts fetchByProductKey_First(
		String productKey,
		OrderByComparator<ProductBundleProducts> orderByComparator) {

		List<ProductBundleProducts> list = findByProductKey(
			productKey, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last product bundle products in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle products
	 * @throws NoSuchProductBundleProductsException if a matching product bundle products could not be found
	 */
	@Override
	public ProductBundleProducts findByProductKey_Last(
			String productKey,
			OrderByComparator<ProductBundleProducts> orderByComparator)
		throws NoSuchProductBundleProductsException {

		ProductBundleProducts productBundleProducts = fetchByProductKey_Last(
			productKey, orderByComparator);

		if (productBundleProducts != null) {
			return productBundleProducts;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("productKey=");
		sb.append(productKey);

		sb.append("}");

		throw new NoSuchProductBundleProductsException(sb.toString());
	}

	/**
	 * Returns the last product bundle products in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle products, or <code>null</code> if a matching product bundle products could not be found
	 */
	@Override
	public ProductBundleProducts fetchByProductKey_Last(
		String productKey,
		OrderByComparator<ProductBundleProducts> orderByComparator) {

		int count = countByProductKey(productKey);

		if (count == 0) {
			return null;
		}

		List<ProductBundleProducts> list = findByProductKey(
			productKey, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the product bundle productses before and after the current product bundle products in the ordered set where productKey = &#63;.
	 *
	 * @param productBundleProductsPK the primary key of the current product bundle products
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product bundle products
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	@Override
	public ProductBundleProducts[] findByProductKey_PrevAndNext(
			ProductBundleProductsPK productBundleProductsPK, String productKey,
			OrderByComparator<ProductBundleProducts> orderByComparator)
		throws NoSuchProductBundleProductsException {

		productKey = Objects.toString(productKey, "");

		ProductBundleProducts productBundleProducts = findByPrimaryKey(
			productBundleProductsPK);

		Session session = null;

		try {
			session = openSession();

			ProductBundleProducts[] array = new ProductBundleProductsImpl[3];

			array[0] = getByProductKey_PrevAndNext(
				session, productBundleProducts, productKey, orderByComparator,
				true);

			array[1] = productBundleProducts;

			array[2] = getByProductKey_PrevAndNext(
				session, productBundleProducts, productKey, orderByComparator,
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

	protected ProductBundleProducts getByProductKey_PrevAndNext(
		Session session, ProductBundleProducts productBundleProducts,
		String productKey,
		OrderByComparator<ProductBundleProducts> orderByComparator,
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

		sb.append(_SQL_SELECT_PRODUCTBUNDLEPRODUCTS_WHERE);

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
			sb.append(ProductBundleProductsModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(
						productBundleProducts)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<ProductBundleProducts> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the product bundle productses where productKey = &#63; from the database.
	 *
	 * @param productKey the product key
	 */
	@Override
	public void removeByProductKey(String productKey) {
		for (ProductBundleProducts productBundleProducts :
				findByProductKey(
					productKey, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(productBundleProducts);
		}
	}

	/**
	 * Returns the number of product bundle productses where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @return the number of matching product bundle productses
	 */
	@Override
	public int countByProductKey(String productKey) {
		productKey = Objects.toString(productKey, "");

		FinderPath finderPath = _finderPathCountByProductKey;

		Object[] finderArgs = new Object[] {productKey};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_PRODUCTBUNDLEPRODUCTS_WHERE);

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
		"productBundleProducts.id.productKey = ?";

	private static final String _FINDER_COLUMN_PRODUCTKEY_PRODUCTKEY_3 =
		"(productBundleProducts.id.productKey IS NULL OR productBundleProducts.id.productKey = '')";

	public ProductBundleProductsPersistenceImpl() {
		setModelClass(ProductBundleProducts.class);

		setModelImplClass(ProductBundleProductsImpl.class);
		setModelPKClass(ProductBundleProductsPK.class);
	}

	/**
	 * Caches the product bundle products in the entity cache if it is enabled.
	 *
	 * @param productBundleProducts the product bundle products
	 */
	@Override
	public void cacheResult(ProductBundleProducts productBundleProducts) {
		entityCache.putResult(
			entityCacheEnabled, ProductBundleProductsImpl.class,
			productBundleProducts.getPrimaryKey(), productBundleProducts);

		productBundleProducts.resetOriginalValues();
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the product bundle productses in the entity cache if it is enabled.
	 *
	 * @param productBundleProductses the product bundle productses
	 */
	@Override
	public void cacheResult(
		List<ProductBundleProducts> productBundleProductses) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (productBundleProductses.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ProductBundleProducts productBundleProducts :
				productBundleProductses) {

			if (entityCache.getResult(
					entityCacheEnabled, ProductBundleProductsImpl.class,
					productBundleProducts.getPrimaryKey()) == null) {

				cacheResult(productBundleProducts);
			}
			else {
				productBundleProducts.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all product bundle productses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProductBundleProductsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the product bundle products.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProductBundleProducts productBundleProducts) {
		entityCache.removeResult(
			entityCacheEnabled, ProductBundleProductsImpl.class,
			productBundleProducts.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<ProductBundleProducts> productBundleProductses) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProductBundleProducts productBundleProducts :
				productBundleProductses) {

			entityCache.removeResult(
				entityCacheEnabled, ProductBundleProductsImpl.class,
				productBundleProducts.getPrimaryKey());
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, ProductBundleProductsImpl.class,
				primaryKey);
		}
	}

	/**
	 * Creates a new product bundle products with the primary key. Does not add the product bundle products to the database.
	 *
	 * @param productBundleProductsPK the primary key for the new product bundle products
	 * @return the new product bundle products
	 */
	@Override
	public ProductBundleProducts create(
		ProductBundleProductsPK productBundleProductsPK) {

		ProductBundleProducts productBundleProducts =
			new ProductBundleProductsImpl();

		productBundleProducts.setNew(true);
		productBundleProducts.setPrimaryKey(productBundleProductsPK);

		return productBundleProducts;
	}

	/**
	 * Removes the product bundle products with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products that was removed
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	@Override
	public ProductBundleProducts remove(
			ProductBundleProductsPK productBundleProductsPK)
		throws NoSuchProductBundleProductsException {

		return remove((Serializable)productBundleProductsPK);
	}

	/**
	 * Removes the product bundle products with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the product bundle products
	 * @return the product bundle products that was removed
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	@Override
	public ProductBundleProducts remove(Serializable primaryKey)
		throws NoSuchProductBundleProductsException {

		Session session = null;

		try {
			session = openSession();

			ProductBundleProducts productBundleProducts =
				(ProductBundleProducts)session.get(
					ProductBundleProductsImpl.class, primaryKey);

			if (productBundleProducts == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProductBundleProductsException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(productBundleProducts);
		}
		catch (NoSuchProductBundleProductsException noSuchEntityException) {
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
	protected ProductBundleProducts removeImpl(
		ProductBundleProducts productBundleProducts) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(productBundleProducts)) {
				productBundleProducts = (ProductBundleProducts)session.get(
					ProductBundleProductsImpl.class,
					productBundleProducts.getPrimaryKeyObj());
			}

			if (productBundleProducts != null) {
				session.delete(productBundleProducts);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (productBundleProducts != null) {
			clearCache(productBundleProducts);
		}

		return productBundleProducts;
	}

	@Override
	public ProductBundleProducts updateImpl(
		ProductBundleProducts productBundleProducts) {

		boolean isNew = productBundleProducts.isNew();

		if (!(productBundleProducts instanceof
				ProductBundleProductsModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(productBundleProducts.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					productBundleProducts);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in productBundleProducts proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom ProductBundleProducts implementation " +
					productBundleProducts.getClass());
		}

		ProductBundleProductsModelImpl productBundleProductsModelImpl =
			(ProductBundleProductsModelImpl)productBundleProducts;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(productBundleProducts);

				productBundleProducts.setNew(false);
			}
			else {
				productBundleProducts = (ProductBundleProducts)session.merge(
					productBundleProducts);
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
				productBundleProductsModelImpl.getProductBundleId()
			};

			finderCache.removeResult(_finderPathCountByProductBundleId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByProductBundleId, args);

			args = new Object[] {
				productBundleProductsModelImpl.getProductKey()
			};

			finderCache.removeResult(_finderPathCountByProductKey, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByProductKey, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((productBundleProductsModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByProductBundleId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					productBundleProductsModelImpl.getOriginalProductBundleId()
				};

				finderCache.removeResult(
					_finderPathCountByProductBundleId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductBundleId, args);

				args = new Object[] {
					productBundleProductsModelImpl.getProductBundleId()
				};

				finderCache.removeResult(
					_finderPathCountByProductBundleId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductBundleId, args);
			}

			if ((productBundleProductsModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByProductKey.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					productBundleProductsModelImpl.getOriginalProductKey()
				};

				finderCache.removeResult(_finderPathCountByProductKey, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductKey, args);

				args = new Object[] {
					productBundleProductsModelImpl.getProductKey()
				};

				finderCache.removeResult(_finderPathCountByProductKey, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByProductKey, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, ProductBundleProductsImpl.class,
			productBundleProducts.getPrimaryKey(), productBundleProducts,
			false);

		productBundleProducts.resetOriginalValues();

		return productBundleProducts;
	}

	/**
	 * Returns the product bundle products with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the product bundle products
	 * @return the product bundle products
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	@Override
	public ProductBundleProducts findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProductBundleProductsException {

		ProductBundleProducts productBundleProducts = fetchByPrimaryKey(
			primaryKey);

		if (productBundleProducts == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProductBundleProductsException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return productBundleProducts;
	}

	/**
	 * Returns the product bundle products with the primary key or throws a <code>NoSuchProductBundleProductsException</code> if it could not be found.
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	@Override
	public ProductBundleProducts findByPrimaryKey(
			ProductBundleProductsPK productBundleProductsPK)
		throws NoSuchProductBundleProductsException {

		return findByPrimaryKey((Serializable)productBundleProductsPK);
	}

	/**
	 * Returns the product bundle products with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products, or <code>null</code> if a product bundle products with the primary key could not be found
	 */
	@Override
	public ProductBundleProducts fetchByPrimaryKey(
		ProductBundleProductsPK productBundleProductsPK) {

		return fetchByPrimaryKey((Serializable)productBundleProductsPK);
	}

	/**
	 * Returns all the product bundle productses.
	 *
	 * @return the product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the product bundle productses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @return the range of product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the product bundle productses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findAll(
		int start, int end,
		OrderByComparator<ProductBundleProducts> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the product bundle productses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleProductsModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundle productses
	 * @param end the upper bound of the range of product bundle productses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of product bundle productses
	 */
	@Override
	public List<ProductBundleProducts> findAll(
		int start, int end,
		OrderByComparator<ProductBundleProducts> orderByComparator,
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

		List<ProductBundleProducts> list = null;

		if (useFinderCache) {
			list = (List<ProductBundleProducts>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_PRODUCTBUNDLEPRODUCTS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_PRODUCTBUNDLEPRODUCTS;

				sql = sql.concat(ProductBundleProductsModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ProductBundleProducts>)QueryUtil.list(
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
	 * Removes all the product bundle productses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProductBundleProducts productBundleProducts : findAll()) {
			remove(productBundleProducts);
		}
	}

	/**
	 * Returns the number of product bundle productses.
	 *
	 * @return the number of product bundle productses
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
					_SQL_COUNT_PRODUCTBUNDLEPRODUCTS);

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
		return "productBundleProductsPK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_PRODUCTBUNDLEPRODUCTS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ProductBundleProductsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the product bundle products persistence.
	 */
	@Activate
	public void activate() {
		ProductBundleProductsModelImpl.setEntityCacheEnabled(
			entityCacheEnabled);
		ProductBundleProductsModelImpl.setFinderCacheEnabled(
			finderCacheEnabled);

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductBundleProductsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductBundleProductsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByProductBundleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductBundleProductsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProductBundleId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByProductBundleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductBundleProductsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProductBundleId",
			new String[] {Long.class.getName()},
			ProductBundleProductsModelImpl.PRODUCTBUNDLEID_COLUMN_BITMASK);

		_finderPathCountByProductBundleId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProductBundleId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByProductKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductBundleProductsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProductKey",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByProductKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			ProductBundleProductsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProductKey",
			new String[] {String.class.getName()},
			ProductBundleProductsModelImpl.PRODUCTKEY_COLUMN_BITMASK);

		_finderPathCountByProductKey = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProductKey",
			new String[] {String.class.getName()});

		ProductBundleProductsUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		ProductBundleProductsUtil.setPersistence(null);

		entityCache.removeCache(ProductBundleProductsImpl.class.getName());

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
				"value.object.column.bitmask.enabled.com.liferay.osb.provisioning.model.ProductBundleProducts"),
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

	private static final String _SQL_SELECT_PRODUCTBUNDLEPRODUCTS =
		"SELECT productBundleProducts FROM ProductBundleProducts productBundleProducts";

	private static final String _SQL_SELECT_PRODUCTBUNDLEPRODUCTS_WHERE =
		"SELECT productBundleProducts FROM ProductBundleProducts productBundleProducts WHERE ";

	private static final String _SQL_COUNT_PRODUCTBUNDLEPRODUCTS =
		"SELECT COUNT(productBundleProducts) FROM ProductBundleProducts productBundleProducts";

	private static final String _SQL_COUNT_PRODUCTBUNDLEPRODUCTS_WHERE =
		"SELECT COUNT(productBundleProducts) FROM ProductBundleProducts productBundleProducts WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"productBundleProducts.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ProductBundleProducts exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No ProductBundleProducts exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		ProductBundleProductsPersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"productBundleId", "productKey"});

}