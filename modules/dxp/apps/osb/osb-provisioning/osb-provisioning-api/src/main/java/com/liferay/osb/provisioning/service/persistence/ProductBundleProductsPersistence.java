/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.service.persistence;

import com.liferay.osb.provisioning.exception.NoSuchProductBundleProductsException;
import com.liferay.osb.provisioning.model.ProductBundleProducts;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the product bundle products service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleProductsUtil
 * @generated
 */
@ProviderType
public interface ProductBundleProductsPersistence
	extends BasePersistence<ProductBundleProducts> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProductBundleProductsUtil} to access the product bundle products persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the product bundle productses where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the matching product bundle productses
	 */
	public java.util.List<ProductBundleProducts> findByProductBundleId(
		long productBundleId);

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
	public java.util.List<ProductBundleProducts> findByProductBundleId(
		long productBundleId, int start, int end);

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
	public java.util.List<ProductBundleProducts> findByProductBundleId(
		long productBundleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundleProducts>
			orderByComparator);

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
	public java.util.List<ProductBundleProducts> findByProductBundleId(
		long productBundleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundleProducts>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product bundle products in the ordered set where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle products
	 * @throws NoSuchProductBundleProductsException if a matching product bundle products could not be found
	 */
	public ProductBundleProducts findByProductBundleId_First(
			long productBundleId,
			com.liferay.portal.kernel.util.OrderByComparator
				<ProductBundleProducts> orderByComparator)
		throws NoSuchProductBundleProductsException;

	/**
	 * Returns the first product bundle products in the ordered set where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle products, or <code>null</code> if a matching product bundle products could not be found
	 */
	public ProductBundleProducts fetchByProductBundleId_First(
		long productBundleId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundleProducts>
			orderByComparator);

	/**
	 * Returns the last product bundle products in the ordered set where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle products
	 * @throws NoSuchProductBundleProductsException if a matching product bundle products could not be found
	 */
	public ProductBundleProducts findByProductBundleId_Last(
			long productBundleId,
			com.liferay.portal.kernel.util.OrderByComparator
				<ProductBundleProducts> orderByComparator)
		throws NoSuchProductBundleProductsException;

	/**
	 * Returns the last product bundle products in the ordered set where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle products, or <code>null</code> if a matching product bundle products could not be found
	 */
	public ProductBundleProducts fetchByProductBundleId_Last(
		long productBundleId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundleProducts>
			orderByComparator);

	/**
	 * Returns the product bundle productses before and after the current product bundle products in the ordered set where productBundleId = &#63;.
	 *
	 * @param productBundleProductsPK the primary key of the current product bundle products
	 * @param productBundleId the product bundle ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product bundle products
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	public ProductBundleProducts[] findByProductBundleId_PrevAndNext(
			ProductBundleProductsPK productBundleProductsPK,
			long productBundleId,
			com.liferay.portal.kernel.util.OrderByComparator
				<ProductBundleProducts> orderByComparator)
		throws NoSuchProductBundleProductsException;

	/**
	 * Removes all the product bundle productses where productBundleId = &#63; from the database.
	 *
	 * @param productBundleId the product bundle ID
	 */
	public void removeByProductBundleId(long productBundleId);

	/**
	 * Returns the number of product bundle productses where productBundleId = &#63;.
	 *
	 * @param productBundleId the product bundle ID
	 * @return the number of matching product bundle productses
	 */
	public int countByProductBundleId(long productBundleId);

	/**
	 * Returns all the product bundle productses where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @return the matching product bundle productses
	 */
	public java.util.List<ProductBundleProducts> findByProductKey(
		String productKey);

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
	public java.util.List<ProductBundleProducts> findByProductKey(
		String productKey, int start, int end);

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
	public java.util.List<ProductBundleProducts> findByProductKey(
		String productKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundleProducts>
			orderByComparator);

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
	public java.util.List<ProductBundleProducts> findByProductKey(
		String productKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundleProducts>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product bundle products in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle products
	 * @throws NoSuchProductBundleProductsException if a matching product bundle products could not be found
	 */
	public ProductBundleProducts findByProductKey_First(
			String productKey,
			com.liferay.portal.kernel.util.OrderByComparator
				<ProductBundleProducts> orderByComparator)
		throws NoSuchProductBundleProductsException;

	/**
	 * Returns the first product bundle products in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle products, or <code>null</code> if a matching product bundle products could not be found
	 */
	public ProductBundleProducts fetchByProductKey_First(
		String productKey,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundleProducts>
			orderByComparator);

	/**
	 * Returns the last product bundle products in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle products
	 * @throws NoSuchProductBundleProductsException if a matching product bundle products could not be found
	 */
	public ProductBundleProducts findByProductKey_Last(
			String productKey,
			com.liferay.portal.kernel.util.OrderByComparator
				<ProductBundleProducts> orderByComparator)
		throws NoSuchProductBundleProductsException;

	/**
	 * Returns the last product bundle products in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle products, or <code>null</code> if a matching product bundle products could not be found
	 */
	public ProductBundleProducts fetchByProductKey_Last(
		String productKey,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundleProducts>
			orderByComparator);

	/**
	 * Returns the product bundle productses before and after the current product bundle products in the ordered set where productKey = &#63;.
	 *
	 * @param productBundleProductsPK the primary key of the current product bundle products
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product bundle products
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	public ProductBundleProducts[] findByProductKey_PrevAndNext(
			ProductBundleProductsPK productBundleProductsPK, String productKey,
			com.liferay.portal.kernel.util.OrderByComparator
				<ProductBundleProducts> orderByComparator)
		throws NoSuchProductBundleProductsException;

	/**
	 * Removes all the product bundle productses where productKey = &#63; from the database.
	 *
	 * @param productKey the product key
	 */
	public void removeByProductKey(String productKey);

	/**
	 * Returns the number of product bundle productses where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @return the number of matching product bundle productses
	 */
	public int countByProductKey(String productKey);

	/**
	 * Caches the product bundle products in the entity cache if it is enabled.
	 *
	 * @param productBundleProducts the product bundle products
	 */
	public void cacheResult(ProductBundleProducts productBundleProducts);

	/**
	 * Caches the product bundle productses in the entity cache if it is enabled.
	 *
	 * @param productBundleProductses the product bundle productses
	 */
	public void cacheResult(
		java.util.List<ProductBundleProducts> productBundleProductses);

	/**
	 * Creates a new product bundle products with the primary key. Does not add the product bundle products to the database.
	 *
	 * @param productBundleProductsPK the primary key for the new product bundle products
	 * @return the new product bundle products
	 */
	public ProductBundleProducts create(
		ProductBundleProductsPK productBundleProductsPK);

	/**
	 * Removes the product bundle products with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products that was removed
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	public ProductBundleProducts remove(
			ProductBundleProductsPK productBundleProductsPK)
		throws NoSuchProductBundleProductsException;

	public ProductBundleProducts updateImpl(
		ProductBundleProducts productBundleProducts);

	/**
	 * Returns the product bundle products with the primary key or throws a <code>NoSuchProductBundleProductsException</code> if it could not be found.
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products
	 * @throws NoSuchProductBundleProductsException if a product bundle products with the primary key could not be found
	 */
	public ProductBundleProducts findByPrimaryKey(
			ProductBundleProductsPK productBundleProductsPK)
		throws NoSuchProductBundleProductsException;

	/**
	 * Returns the product bundle products with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productBundleProductsPK the primary key of the product bundle products
	 * @return the product bundle products, or <code>null</code> if a product bundle products with the primary key could not be found
	 */
	public ProductBundleProducts fetchByPrimaryKey(
		ProductBundleProductsPK productBundleProductsPK);

	/**
	 * Returns all the product bundle productses.
	 *
	 * @return the product bundle productses
	 */
	public java.util.List<ProductBundleProducts> findAll();

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
	public java.util.List<ProductBundleProducts> findAll(int start, int end);

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
	public java.util.List<ProductBundleProducts> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundleProducts>
			orderByComparator);

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
	public java.util.List<ProductBundleProducts> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundleProducts>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the product bundle productses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of product bundle productses.
	 *
	 * @return the number of product bundle productses
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}