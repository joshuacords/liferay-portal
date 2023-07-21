/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.persistence;

import com.liferay.osb.koroneiki.trunk.exception.NoSuchProductPurchaseException;
import com.liferay.osb.koroneiki.trunk.model.ProductPurchase;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the product purchase service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductPurchaseUtil
 * @generated
 */
@ProviderType
public interface ProductPurchasePersistence
	extends BasePersistence<ProductPurchase> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProductPurchaseUtil} to access the product purchase persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the product purchases where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product purchases
	 */
	public java.util.List<ProductPurchase> findByUuid(String uuid);

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
	public java.util.List<ProductPurchase> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<ProductPurchase> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

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
	public java.util.List<ProductPurchase> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public ProductPurchase findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns the first product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public ProductPurchase fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

	/**
	 * Returns the last product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public ProductPurchase findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns the last product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public ProductPurchase fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set where uuid = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public ProductPurchase[] findByUuid_PrevAndNext(
			long productPurchaseId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns all the product purchases that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product purchases that the user has permission to view
	 */
	public java.util.List<ProductPurchase> filterFindByUuid(String uuid);

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
	public java.util.List<ProductPurchase> filterFindByUuid(
		String uuid, int start, int end);

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
	public java.util.List<ProductPurchase> filterFindByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set of product purchases that the user has permission to view where uuid = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public ProductPurchase[] filterFindByUuid_PrevAndNext(
			long productPurchaseId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Removes all the product purchases where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of product purchases where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product purchases
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the number of product purchases that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product purchases that the user has permission to view
	 */
	public int filterCountByUuid(String uuid);

	/**
	 * Returns all the product purchases where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product purchases
	 */
	public java.util.List<ProductPurchase> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<ProductPurchase> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<ProductPurchase> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

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
	public java.util.List<ProductPurchase> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product purchase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public ProductPurchase findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns the first product purchase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public ProductPurchase fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

	/**
	 * Returns the last product purchase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public ProductPurchase findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns the last product purchase in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public ProductPurchase fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

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
	public ProductPurchase[] findByUuid_C_PrevAndNext(
			long productPurchaseId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns all the product purchases that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product purchases that the user has permission to view
	 */
	public java.util.List<ProductPurchase> filterFindByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<ProductPurchase> filterFindByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<ProductPurchase> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

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
	public ProductPurchase[] filterFindByUuid_C_PrevAndNext(
			long productPurchaseId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Removes all the product purchases where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of product purchases where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product purchases
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of product purchases that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product purchases that the user has permission to view
	 */
	public int filterCountByUuid_C(String uuid, long companyId);

	/**
	 * Returns the product purchase where productPurchaseKey = &#63; or throws a <code>NoSuchProductPurchaseException</code> if it could not be found.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public ProductPurchase findByProductPurchaseKey(String productPurchaseKey)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns the product purchase where productPurchaseKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public ProductPurchase fetchByProductPurchaseKey(String productPurchaseKey);

	/**
	 * Returns the product purchase where productPurchaseKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public ProductPurchase fetchByProductPurchaseKey(
		String productPurchaseKey, boolean useFinderCache);

	/**
	 * Removes the product purchase where productPurchaseKey = &#63; from the database.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the product purchase that was removed
	 */
	public ProductPurchase removeByProductPurchaseKey(String productPurchaseKey)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns the number of product purchases where productPurchaseKey = &#63;.
	 *
	 * @param productPurchaseKey the product purchase key
	 * @return the number of matching product purchases
	 */
	public int countByProductPurchaseKey(String productPurchaseKey);

	/**
	 * Returns all the product purchases where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching product purchases
	 */
	public java.util.List<ProductPurchase> findByAccountId(long accountId);

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
	public java.util.List<ProductPurchase> findByAccountId(
		long accountId, int start, int end);

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
	public java.util.List<ProductPurchase> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

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
	public java.util.List<ProductPurchase> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public ProductPurchase findByAccountId_First(
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns the first product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public ProductPurchase fetchByAccountId_First(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

	/**
	 * Returns the last product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public ProductPurchase findByAccountId_Last(
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns the last product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public ProductPurchase fetchByAccountId_Last(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set where accountId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public ProductPurchase[] findByAccountId_PrevAndNext(
			long productPurchaseId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns all the product purchases that the user has permission to view where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching product purchases that the user has permission to view
	 */
	public java.util.List<ProductPurchase> filterFindByAccountId(
		long accountId);

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
	public java.util.List<ProductPurchase> filterFindByAccountId(
		long accountId, int start, int end);

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
	public java.util.List<ProductPurchase> filterFindByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set of product purchases that the user has permission to view where accountId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public ProductPurchase[] filterFindByAccountId_PrevAndNext(
			long productPurchaseId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Removes all the product purchases where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	public void removeByAccountId(long accountId);

	/**
	 * Returns the number of product purchases where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching product purchases
	 */
	public int countByAccountId(long accountId);

	/**
	 * Returns the number of product purchases that the user has permission to view where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching product purchases that the user has permission to view
	 */
	public int filterCountByAccountId(long accountId);

	/**
	 * Returns all the product purchases where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching product purchases
	 */
	public java.util.List<ProductPurchase> findByProductEntryId(
		long productEntryId);

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
	public java.util.List<ProductPurchase> findByProductEntryId(
		long productEntryId, int start, int end);

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
	public java.util.List<ProductPurchase> findByProductEntryId(
		long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

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
	public java.util.List<ProductPurchase> findByProductEntryId(
		long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public ProductPurchase findByProductEntryId_First(
			long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns the first product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public ProductPurchase fetchByProductEntryId_First(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

	/**
	 * Returns the last product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public ProductPurchase findByProductEntryId_Last(
			long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns the last product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public ProductPurchase fetchByProductEntryId_Last(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set where productEntryId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public ProductPurchase[] findByProductEntryId_PrevAndNext(
			long productPurchaseId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns all the product purchases that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching product purchases that the user has permission to view
	 */
	public java.util.List<ProductPurchase> filterFindByProductEntryId(
		long productEntryId);

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
	public java.util.List<ProductPurchase> filterFindByProductEntryId(
		long productEntryId, int start, int end);

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
	public java.util.List<ProductPurchase> filterFindByProductEntryId(
		long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

	/**
	 * Returns the product purchases before and after the current product purchase in the ordered set of product purchases that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productPurchaseId the primary key of the current product purchase
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public ProductPurchase[] filterFindByProductEntryId_PrevAndNext(
			long productPurchaseId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Removes all the product purchases where productEntryId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 */
	public void removeByProductEntryId(long productEntryId);

	/**
	 * Returns the number of product purchases where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching product purchases
	 */
	public int countByProductEntryId(long productEntryId);

	/**
	 * Returns the number of product purchases that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching product purchases that the user has permission to view
	 */
	public int filterCountByProductEntryId(long productEntryId);

	/**
	 * Returns all the product purchases where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product purchases
	 */
	public java.util.List<ProductPurchase> findByAI_PEI(
		long accountId, long productEntryId);

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
	public java.util.List<ProductPurchase> findByAI_PEI(
		long accountId, long productEntryId, int start, int end);

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
	public java.util.List<ProductPurchase> findByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

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
	public java.util.List<ProductPurchase> findByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product purchase in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public ProductPurchase findByAI_PEI_First(
			long accountId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns the first product purchase in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public ProductPurchase fetchByAI_PEI_First(
		long accountId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

	/**
	 * Returns the last product purchase in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase
	 * @throws NoSuchProductPurchaseException if a matching product purchase could not be found
	 */
	public ProductPurchase findByAI_PEI_Last(
			long accountId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns the last product purchase in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product purchase, or <code>null</code> if a matching product purchase could not be found
	 */
	public ProductPurchase fetchByAI_PEI_Last(
		long accountId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

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
	public ProductPurchase[] findByAI_PEI_PrevAndNext(
			long productPurchaseId, long accountId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns all the product purchases that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product purchases that the user has permission to view
	 */
	public java.util.List<ProductPurchase> filterFindByAI_PEI(
		long accountId, long productEntryId);

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
	public java.util.List<ProductPurchase> filterFindByAI_PEI(
		long accountId, long productEntryId, int start, int end);

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
	public java.util.List<ProductPurchase> filterFindByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

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
	public ProductPurchase[] filterFindByAI_PEI_PrevAndNext(
			long productPurchaseId, long accountId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
				orderByComparator)
		throws NoSuchProductPurchaseException;

	/**
	 * Removes all the product purchases where accountId = &#63; and productEntryId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 */
	public void removeByAI_PEI(long accountId, long productEntryId);

	/**
	 * Returns the number of product purchases where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product purchases
	 */
	public int countByAI_PEI(long accountId, long productEntryId);

	/**
	 * Returns the number of product purchases that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product purchases that the user has permission to view
	 */
	public int filterCountByAI_PEI(long accountId, long productEntryId);

	/**
	 * Caches the product purchase in the entity cache if it is enabled.
	 *
	 * @param productPurchase the product purchase
	 */
	public void cacheResult(ProductPurchase productPurchase);

	/**
	 * Caches the product purchases in the entity cache if it is enabled.
	 *
	 * @param productPurchases the product purchases
	 */
	public void cacheResult(java.util.List<ProductPurchase> productPurchases);

	/**
	 * Creates a new product purchase with the primary key. Does not add the product purchase to the database.
	 *
	 * @param productPurchaseId the primary key for the new product purchase
	 * @return the new product purchase
	 */
	public ProductPurchase create(long productPurchaseId);

	/**
	 * Removes the product purchase with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productPurchaseId the primary key of the product purchase
	 * @return the product purchase that was removed
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public ProductPurchase remove(long productPurchaseId)
		throws NoSuchProductPurchaseException;

	public ProductPurchase updateImpl(ProductPurchase productPurchase);

	/**
	 * Returns the product purchase with the primary key or throws a <code>NoSuchProductPurchaseException</code> if it could not be found.
	 *
	 * @param productPurchaseId the primary key of the product purchase
	 * @return the product purchase
	 * @throws NoSuchProductPurchaseException if a product purchase with the primary key could not be found
	 */
	public ProductPurchase findByPrimaryKey(long productPurchaseId)
		throws NoSuchProductPurchaseException;

	/**
	 * Returns the product purchase with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productPurchaseId the primary key of the product purchase
	 * @return the product purchase, or <code>null</code> if a product purchase with the primary key could not be found
	 */
	public ProductPurchase fetchByPrimaryKey(long productPurchaseId);

	/**
	 * Returns all the product purchases.
	 *
	 * @return the product purchases
	 */
	public java.util.List<ProductPurchase> findAll();

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
	public java.util.List<ProductPurchase> findAll(int start, int end);

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
	public java.util.List<ProductPurchase> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator);

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
	public java.util.List<ProductPurchase> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductPurchase>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the product purchases from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of product purchases.
	 *
	 * @return the number of product purchases
	 */
	public int countAll();

}