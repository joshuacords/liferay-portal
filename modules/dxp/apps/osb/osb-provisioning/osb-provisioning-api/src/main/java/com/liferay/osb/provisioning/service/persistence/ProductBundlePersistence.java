/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.service.persistence;

import com.liferay.osb.provisioning.exception.NoSuchProductBundleException;
import com.liferay.osb.provisioning.model.ProductBundle;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the product bundle service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductBundleUtil
 * @generated
 */
@ProviderType
public interface ProductBundlePersistence
	extends BasePersistence<ProductBundle> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProductBundleUtil} to access the product bundle persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the product bundles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product bundles
	 */
	public java.util.List<ProductBundle> findByUuid(String uuid);

	/**
	 * Returns a range of all the product bundles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @return the range of matching product bundles
	 */
	public java.util.List<ProductBundle> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the product bundles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product bundles
	 */
	public java.util.List<ProductBundle> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
			orderByComparator);

	/**
	 * Returns an ordered range of all the product bundles where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product bundles
	 */
	public java.util.List<ProductBundle> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product bundle in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle
	 * @throws NoSuchProductBundleException if a matching product bundle could not be found
	 */
	public ProductBundle findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
				orderByComparator)
		throws NoSuchProductBundleException;

	/**
	 * Returns the first product bundle in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle, or <code>null</code> if a matching product bundle could not be found
	 */
	public ProductBundle fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
			orderByComparator);

	/**
	 * Returns the last product bundle in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle
	 * @throws NoSuchProductBundleException if a matching product bundle could not be found
	 */
	public ProductBundle findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
				orderByComparator)
		throws NoSuchProductBundleException;

	/**
	 * Returns the last product bundle in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle, or <code>null</code> if a matching product bundle could not be found
	 */
	public ProductBundle fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
			orderByComparator);

	/**
	 * Returns the product bundles before and after the current product bundle in the ordered set where uuid = &#63;.
	 *
	 * @param productBundleId the primary key of the current product bundle
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product bundle
	 * @throws NoSuchProductBundleException if a product bundle with the primary key could not be found
	 */
	public ProductBundle[] findByUuid_PrevAndNext(
			long productBundleId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
				orderByComparator)
		throws NoSuchProductBundleException;

	/**
	 * Removes all the product bundles where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of product bundles where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product bundles
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the product bundles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product bundles
	 */
	public java.util.List<ProductBundle> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the product bundles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @return the range of matching product bundles
	 */
	public java.util.List<ProductBundle> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the product bundles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product bundles
	 */
	public java.util.List<ProductBundle> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
			orderByComparator);

	/**
	 * Returns an ordered range of all the product bundles where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product bundles
	 */
	public java.util.List<ProductBundle> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product bundle in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle
	 * @throws NoSuchProductBundleException if a matching product bundle could not be found
	 */
	public ProductBundle findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
				orderByComparator)
		throws NoSuchProductBundleException;

	/**
	 * Returns the first product bundle in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product bundle, or <code>null</code> if a matching product bundle could not be found
	 */
	public ProductBundle fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
			orderByComparator);

	/**
	 * Returns the last product bundle in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle
	 * @throws NoSuchProductBundleException if a matching product bundle could not be found
	 */
	public ProductBundle findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
				orderByComparator)
		throws NoSuchProductBundleException;

	/**
	 * Returns the last product bundle in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product bundle, or <code>null</code> if a matching product bundle could not be found
	 */
	public ProductBundle fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
			orderByComparator);

	/**
	 * Returns the product bundles before and after the current product bundle in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param productBundleId the primary key of the current product bundle
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product bundle
	 * @throws NoSuchProductBundleException if a product bundle with the primary key could not be found
	 */
	public ProductBundle[] findByUuid_C_PrevAndNext(
			long productBundleId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
				orderByComparator)
		throws NoSuchProductBundleException;

	/**
	 * Removes all the product bundles where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of product bundles where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product bundles
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the product bundle where name = &#63; or throws a <code>NoSuchProductBundleException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching product bundle
	 * @throws NoSuchProductBundleException if a matching product bundle could not be found
	 */
	public ProductBundle findByName(String name)
		throws NoSuchProductBundleException;

	/**
	 * Returns the product bundle where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching product bundle, or <code>null</code> if a matching product bundle could not be found
	 */
	public ProductBundle fetchByName(String name);

	/**
	 * Returns the product bundle where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product bundle, or <code>null</code> if a matching product bundle could not be found
	 */
	public ProductBundle fetchByName(String name, boolean useFinderCache);

	/**
	 * Removes the product bundle where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the product bundle that was removed
	 */
	public ProductBundle removeByName(String name)
		throws NoSuchProductBundleException;

	/**
	 * Returns the number of product bundles where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching product bundles
	 */
	public int countByName(String name);

	/**
	 * Caches the product bundle in the entity cache if it is enabled.
	 *
	 * @param productBundle the product bundle
	 */
	public void cacheResult(ProductBundle productBundle);

	/**
	 * Caches the product bundles in the entity cache if it is enabled.
	 *
	 * @param productBundles the product bundles
	 */
	public void cacheResult(java.util.List<ProductBundle> productBundles);

	/**
	 * Creates a new product bundle with the primary key. Does not add the product bundle to the database.
	 *
	 * @param productBundleId the primary key for the new product bundle
	 * @return the new product bundle
	 */
	public ProductBundle create(long productBundleId);

	/**
	 * Removes the product bundle with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productBundleId the primary key of the product bundle
	 * @return the product bundle that was removed
	 * @throws NoSuchProductBundleException if a product bundle with the primary key could not be found
	 */
	public ProductBundle remove(long productBundleId)
		throws NoSuchProductBundleException;

	public ProductBundle updateImpl(ProductBundle productBundle);

	/**
	 * Returns the product bundle with the primary key or throws a <code>NoSuchProductBundleException</code> if it could not be found.
	 *
	 * @param productBundleId the primary key of the product bundle
	 * @return the product bundle
	 * @throws NoSuchProductBundleException if a product bundle with the primary key could not be found
	 */
	public ProductBundle findByPrimaryKey(long productBundleId)
		throws NoSuchProductBundleException;

	/**
	 * Returns the product bundle with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productBundleId the primary key of the product bundle
	 * @return the product bundle, or <code>null</code> if a product bundle with the primary key could not be found
	 */
	public ProductBundle fetchByPrimaryKey(long productBundleId);

	/**
	 * Returns all the product bundles.
	 *
	 * @return the product bundles
	 */
	public java.util.List<ProductBundle> findAll();

	/**
	 * Returns a range of all the product bundles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @return the range of product bundles
	 */
	public java.util.List<ProductBundle> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the product bundles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product bundles
	 */
	public java.util.List<ProductBundle> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
			orderByComparator);

	/**
	 * Returns an ordered range of all the product bundles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductBundleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product bundles
	 * @param end the upper bound of the range of product bundles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of product bundles
	 */
	public java.util.List<ProductBundle> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductBundle>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the product bundles from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of product bundles.
	 *
	 * @return the number of product bundles
	 */
	public int countAll();

}