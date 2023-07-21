/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.persistence;

import com.liferay.osb.koroneiki.trunk.exception.NoSuchProductConsumptionException;
import com.liferay.osb.koroneiki.trunk.model.ProductConsumption;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the product consumption service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductConsumptionUtil
 * @generated
 */
@ProviderType
public interface ProductConsumptionPersistence
	extends BasePersistence<ProductConsumption> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProductConsumptionUtil} to access the product consumption persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the product consumptions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product consumptions
	 */
	public java.util.List<ProductConsumption> findByUuid(String uuid);

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
	public java.util.List<ProductConsumption> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<ProductConsumption> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public java.util.List<ProductConsumption> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public ProductConsumption findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the first product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

	/**
	 * Returns the last product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public ProductConsumption findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the last product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where uuid = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public ProductConsumption[] findByUuid_PrevAndNext(
			long productConsumptionId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns all the product consumptions that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product consumptions that the user has permission to view
	 */
	public java.util.List<ProductConsumption> filterFindByUuid(String uuid);

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
	public java.util.List<ProductConsumption> filterFindByUuid(
		String uuid, int start, int end);

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
	public java.util.List<ProductConsumption> filterFindByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where uuid = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public ProductConsumption[] filterFindByUuid_PrevAndNext(
			long productConsumptionId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Removes all the product consumptions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of product consumptions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product consumptions
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the number of product consumptions that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	public int filterCountByUuid(String uuid);

	/**
	 * Returns all the product consumptions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product consumptions
	 */
	public java.util.List<ProductConsumption> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<ProductConsumption> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<ProductConsumption> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public java.util.List<ProductConsumption> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product consumption in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public ProductConsumption findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the first product consumption in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

	/**
	 * Returns the last product consumption in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public ProductConsumption findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the last product consumption in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public ProductConsumption[] findByUuid_C_PrevAndNext(
			long productConsumptionId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns all the product consumptions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	public java.util.List<ProductConsumption> filterFindByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<ProductConsumption> filterFindByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<ProductConsumption> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public ProductConsumption[] filterFindByUuid_C_PrevAndNext(
			long productConsumptionId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Removes all the product consumptions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of product consumptions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product consumptions
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of product consumptions that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	public int filterCountByUuid_C(String uuid, long companyId);

	/**
	 * Returns the product consumption where productConsumptionKey = &#63; or throws a <code>NoSuchProductConsumptionException</code> if it could not be found.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @return the matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public ProductConsumption findByProductConsumptionKey(
			String productConsumptionKey)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the product consumption where productConsumptionKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @return the matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByProductConsumptionKey(
		String productConsumptionKey);

	/**
	 * Returns the product consumption where productConsumptionKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByProductConsumptionKey(
		String productConsumptionKey, boolean useFinderCache);

	/**
	 * Removes the product consumption where productConsumptionKey = &#63; from the database.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @return the product consumption that was removed
	 */
	public ProductConsumption removeByProductConsumptionKey(
			String productConsumptionKey)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the number of product consumptions where productConsumptionKey = &#63;.
	 *
	 * @param productConsumptionKey the product consumption key
	 * @return the number of matching product consumptions
	 */
	public int countByProductConsumptionKey(String productConsumptionKey);

	/**
	 * Returns all the product consumptions where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching product consumptions
	 */
	public java.util.List<ProductConsumption> findByAccountId(long accountId);

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
	public java.util.List<ProductConsumption> findByAccountId(
		long accountId, int start, int end);

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
	public java.util.List<ProductConsumption> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public java.util.List<ProductConsumption> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public ProductConsumption findByAccountId_First(
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the first product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByAccountId_First(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

	/**
	 * Returns the last product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public ProductConsumption findByAccountId_Last(
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the last product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByAccountId_Last(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where accountId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public ProductConsumption[] findByAccountId_PrevAndNext(
			long productConsumptionId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns all the product consumptions that the user has permission to view where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	public java.util.List<ProductConsumption> filterFindByAccountId(
		long accountId);

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
	public java.util.List<ProductConsumption> filterFindByAccountId(
		long accountId, int start, int end);

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
	public java.util.List<ProductConsumption> filterFindByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where accountId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public ProductConsumption[] filterFindByAccountId_PrevAndNext(
			long productConsumptionId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Removes all the product consumptions where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	public void removeByAccountId(long accountId);

	/**
	 * Returns the number of product consumptions where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching product consumptions
	 */
	public int countByAccountId(long accountId);

	/**
	 * Returns the number of product consumptions that the user has permission to view where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	public int filterCountByAccountId(long accountId);

	/**
	 * Returns all the product consumptions where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions
	 */
	public java.util.List<ProductConsumption> findByProductEntryId(
		long productEntryId);

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
	public java.util.List<ProductConsumption> findByProductEntryId(
		long productEntryId, int start, int end);

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
	public java.util.List<ProductConsumption> findByProductEntryId(
		long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public java.util.List<ProductConsumption> findByProductEntryId(
		long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public ProductConsumption findByProductEntryId_First(
			long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the first product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByProductEntryId_First(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

	/**
	 * Returns the last product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public ProductConsumption findByProductEntryId_Last(
			long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the last product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByProductEntryId_Last(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where productEntryId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public ProductConsumption[] findByProductEntryId_PrevAndNext(
			long productConsumptionId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns all the product consumptions that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	public java.util.List<ProductConsumption> filterFindByProductEntryId(
		long productEntryId);

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
	public java.util.List<ProductConsumption> filterFindByProductEntryId(
		long productEntryId, int start, int end);

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
	public java.util.List<ProductConsumption> filterFindByProductEntryId(
		long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public ProductConsumption[] filterFindByProductEntryId_PrevAndNext(
			long productConsumptionId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Removes all the product consumptions where productEntryId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 */
	public void removeByProductEntryId(long productEntryId);

	/**
	 * Returns the number of product consumptions where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions
	 */
	public int countByProductEntryId(long productEntryId);

	/**
	 * Returns the number of product consumptions that the user has permission to view where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	public int filterCountByProductEntryId(long productEntryId);

	/**
	 * Returns all the product consumptions where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @return the matching product consumptions
	 */
	public java.util.List<ProductConsumption> findByProductPurchaseId(
		long productPurchaseId);

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
	public java.util.List<ProductConsumption> findByProductPurchaseId(
		long productPurchaseId, int start, int end);

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
	public java.util.List<ProductConsumption> findByProductPurchaseId(
		long productPurchaseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public java.util.List<ProductConsumption> findByProductPurchaseId(
		long productPurchaseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public ProductConsumption findByProductPurchaseId_First(
			long productPurchaseId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the first product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByProductPurchaseId_First(
		long productPurchaseId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

	/**
	 * Returns the last product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public ProductConsumption findByProductPurchaseId_Last(
			long productPurchaseId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the last product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByProductPurchaseId_Last(
		long productPurchaseId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set where productPurchaseId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public ProductConsumption[] findByProductPurchaseId_PrevAndNext(
			long productConsumptionId, long productPurchaseId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns all the product consumptions that the user has permission to view where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	public java.util.List<ProductConsumption> filterFindByProductPurchaseId(
		long productPurchaseId);

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
	public java.util.List<ProductConsumption> filterFindByProductPurchaseId(
		long productPurchaseId, int start, int end);

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
	public java.util.List<ProductConsumption> filterFindByProductPurchaseId(
		long productPurchaseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

	/**
	 * Returns the product consumptions before and after the current product consumption in the ordered set of product consumptions that the user has permission to view where productPurchaseId = &#63;.
	 *
	 * @param productConsumptionId the primary key of the current product consumption
	 * @param productPurchaseId the product purchase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public ProductConsumption[] filterFindByProductPurchaseId_PrevAndNext(
			long productConsumptionId, long productPurchaseId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Removes all the product consumptions where productPurchaseId = &#63; from the database.
	 *
	 * @param productPurchaseId the product purchase ID
	 */
	public void removeByProductPurchaseId(long productPurchaseId);

	/**
	 * Returns the number of product consumptions where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @return the number of matching product consumptions
	 */
	public int countByProductPurchaseId(long productPurchaseId);

	/**
	 * Returns the number of product consumptions that the user has permission to view where productPurchaseId = &#63;.
	 *
	 * @param productPurchaseId the product purchase ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	public int filterCountByProductPurchaseId(long productPurchaseId);

	/**
	 * Returns all the product consumptions where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions
	 */
	public java.util.List<ProductConsumption> findByAI_PEI(
		long accountId, long productEntryId);

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
	public java.util.List<ProductConsumption> findByAI_PEI(
		long accountId, long productEntryId, int start, int end);

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
	public java.util.List<ProductConsumption> findByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public java.util.List<ProductConsumption> findByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product consumption in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public ProductConsumption findByAI_PEI_First(
			long accountId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the first product consumption in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByAI_PEI_First(
		long accountId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

	/**
	 * Returns the last product consumption in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption
	 * @throws NoSuchProductConsumptionException if a matching product consumption could not be found
	 */
	public ProductConsumption findByAI_PEI_Last(
			long accountId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the last product consumption in the ordered set where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByAI_PEI_Last(
		long accountId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public ProductConsumption[] findByAI_PEI_PrevAndNext(
			long productConsumptionId, long accountId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns all the product consumptions that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	public java.util.List<ProductConsumption> filterFindByAI_PEI(
		long accountId, long productEntryId);

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
	public java.util.List<ProductConsumption> filterFindByAI_PEI(
		long accountId, long productEntryId, int start, int end);

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
	public java.util.List<ProductConsumption> filterFindByAI_PEI(
		long accountId, long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public ProductConsumption[] filterFindByAI_PEI_PrevAndNext(
			long productConsumptionId, long accountId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Removes all the product consumptions where accountId = &#63; and productEntryId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 */
	public void removeByAI_PEI(long accountId, long productEntryId);

	/**
	 * Returns the number of product consumptions where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions
	 */
	public int countByAI_PEI(long accountId, long productEntryId);

	/**
	 * Returns the number of product consumptions that the user has permission to view where accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	public int filterCountByAI_PEI(long accountId, long productEntryId);

	/**
	 * Returns all the product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions
	 */
	public java.util.List<ProductConsumption> findByU_AI_PEI(
		long userId, long accountId, long productEntryId);

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
	public java.util.List<ProductConsumption> findByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end);

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
	public java.util.List<ProductConsumption> findByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public java.util.List<ProductConsumption> findByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator,
		boolean useFinderCache);

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
	public ProductConsumption findByU_AI_PEI_First(
			long userId, long accountId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the first product consumption in the ordered set where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByU_AI_PEI_First(
		long userId, long accountId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public ProductConsumption findByU_AI_PEI_Last(
			long userId, long accountId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the last product consumption in the ordered set where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product consumption, or <code>null</code> if a matching product consumption could not be found
	 */
	public ProductConsumption fetchByU_AI_PEI_Last(
		long userId, long accountId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public ProductConsumption[] findByU_AI_PEI_PrevAndNext(
			long productConsumptionId, long userId, long accountId,
			long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns all the product consumptions that the user has permission to view where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the matching product consumptions that the user has permission to view
	 */
	public java.util.List<ProductConsumption> filterFindByU_AI_PEI(
		long userId, long accountId, long productEntryId);

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
	public java.util.List<ProductConsumption> filterFindByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end);

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
	public java.util.List<ProductConsumption> filterFindByU_AI_PEI(
		long userId, long accountId, long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public ProductConsumption[] filterFindByU_AI_PEI_PrevAndNext(
			long productConsumptionId, long userId, long accountId,
			long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
				orderByComparator)
		throws NoSuchProductConsumptionException;

	/**
	 * Removes all the product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 */
	public void removeByU_AI_PEI(
		long userId, long accountId, long productEntryId);

	/**
	 * Returns the number of product consumptions where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions
	 */
	public int countByU_AI_PEI(
		long userId, long accountId, long productEntryId);

	/**
	 * Returns the number of product consumptions that the user has permission to view where userId = &#63; and accountId = &#63; and productEntryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param accountId the account ID
	 * @param productEntryId the product entry ID
	 * @return the number of matching product consumptions that the user has permission to view
	 */
	public int filterCountByU_AI_PEI(
		long userId, long accountId, long productEntryId);

	/**
	 * Caches the product consumption in the entity cache if it is enabled.
	 *
	 * @param productConsumption the product consumption
	 */
	public void cacheResult(ProductConsumption productConsumption);

	/**
	 * Caches the product consumptions in the entity cache if it is enabled.
	 *
	 * @param productConsumptions the product consumptions
	 */
	public void cacheResult(
		java.util.List<ProductConsumption> productConsumptions);

	/**
	 * Creates a new product consumption with the primary key. Does not add the product consumption to the database.
	 *
	 * @param productConsumptionId the primary key for the new product consumption
	 * @return the new product consumption
	 */
	public ProductConsumption create(long productConsumptionId);

	/**
	 * Removes the product consumption with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productConsumptionId the primary key of the product consumption
	 * @return the product consumption that was removed
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public ProductConsumption remove(long productConsumptionId)
		throws NoSuchProductConsumptionException;

	public ProductConsumption updateImpl(ProductConsumption productConsumption);

	/**
	 * Returns the product consumption with the primary key or throws a <code>NoSuchProductConsumptionException</code> if it could not be found.
	 *
	 * @param productConsumptionId the primary key of the product consumption
	 * @return the product consumption
	 * @throws NoSuchProductConsumptionException if a product consumption with the primary key could not be found
	 */
	public ProductConsumption findByPrimaryKey(long productConsumptionId)
		throws NoSuchProductConsumptionException;

	/**
	 * Returns the product consumption with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productConsumptionId the primary key of the product consumption
	 * @return the product consumption, or <code>null</code> if a product consumption with the primary key could not be found
	 */
	public ProductConsumption fetchByPrimaryKey(long productConsumptionId);

	/**
	 * Returns all the product consumptions.
	 *
	 * @return the product consumptions
	 */
	public java.util.List<ProductConsumption> findAll();

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
	public java.util.List<ProductConsumption> findAll(int start, int end);

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
	public java.util.List<ProductConsumption> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator);

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
	public java.util.List<ProductConsumption> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductConsumption>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the product consumptions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of product consumptions.
	 *
	 * @return the number of product consumptions
	 */
	public int countAll();

}