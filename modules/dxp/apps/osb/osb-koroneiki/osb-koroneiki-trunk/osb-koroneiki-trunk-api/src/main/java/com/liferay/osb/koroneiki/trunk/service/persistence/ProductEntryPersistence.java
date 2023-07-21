/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.trunk.service.persistence;

import com.liferay.osb.koroneiki.trunk.exception.NoSuchProductEntryException;
import com.liferay.osb.koroneiki.trunk.model.ProductEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the product entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryUtil
 * @generated
 */
@ProviderType
public interface ProductEntryPersistence extends BasePersistence<ProductEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProductEntryUtil} to access the product entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the product entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product entries
	 */
	public java.util.List<ProductEntry> findByUuid(String uuid);

	/**
	 * Returns a range of all the product entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @return the range of matching product entries
	 */
	public java.util.List<ProductEntry> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the product entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product entries
	 */
	public java.util.List<ProductEntry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the product entries where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product entries
	 */
	public java.util.List<ProductEntry> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public ProductEntry findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
				orderByComparator)
		throws NoSuchProductEntryException;

	/**
	 * Returns the first product entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public ProductEntry fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator);

	/**
	 * Returns the last product entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public ProductEntry findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
				orderByComparator)
		throws NoSuchProductEntryException;

	/**
	 * Returns the last product entry in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public ProductEntry fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator);

	/**
	 * Returns the product entries before and after the current product entry in the ordered set where uuid = &#63;.
	 *
	 * @param productEntryId the primary key of the current product entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product entry
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	public ProductEntry[] findByUuid_PrevAndNext(
			long productEntryId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
				orderByComparator)
		throws NoSuchProductEntryException;

	/**
	 * Returns all the product entries that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching product entries that the user has permission to view
	 */
	public java.util.List<ProductEntry> filterFindByUuid(String uuid);

	/**
	 * Returns a range of all the product entries that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @return the range of matching product entries that the user has permission to view
	 */
	public java.util.List<ProductEntry> filterFindByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the product entries that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product entries that the user has permission to view
	 */
	public java.util.List<ProductEntry> filterFindByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator);

	/**
	 * Returns the product entries before and after the current product entry in the ordered set of product entries that the user has permission to view where uuid = &#63;.
	 *
	 * @param productEntryId the primary key of the current product entry
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product entry
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	public ProductEntry[] filterFindByUuid_PrevAndNext(
			long productEntryId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
				orderByComparator)
		throws NoSuchProductEntryException;

	/**
	 * Removes all the product entries where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of product entries where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product entries
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the number of product entries that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching product entries that the user has permission to view
	 */
	public int filterCountByUuid(String uuid);

	/**
	 * Returns all the product entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product entries
	 */
	public java.util.List<ProductEntry> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the product entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @return the range of matching product entries
	 */
	public java.util.List<ProductEntry> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the product entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product entries
	 */
	public java.util.List<ProductEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the product entries where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product entries
	 */
	public java.util.List<ProductEntry> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public ProductEntry findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
				orderByComparator)
		throws NoSuchProductEntryException;

	/**
	 * Returns the first product entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public ProductEntry fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator);

	/**
	 * Returns the last product entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public ProductEntry findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
				orderByComparator)
		throws NoSuchProductEntryException;

	/**
	 * Returns the last product entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public ProductEntry fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator);

	/**
	 * Returns the product entries before and after the current product entry in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param productEntryId the primary key of the current product entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product entry
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	public ProductEntry[] findByUuid_C_PrevAndNext(
			long productEntryId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
				orderByComparator)
		throws NoSuchProductEntryException;

	/**
	 * Returns all the product entries that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching product entries that the user has permission to view
	 */
	public java.util.List<ProductEntry> filterFindByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the product entries that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @return the range of matching product entries that the user has permission to view
	 */
	public java.util.List<ProductEntry> filterFindByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the product entries that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product entries that the user has permission to view
	 */
	public java.util.List<ProductEntry> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator);

	/**
	 * Returns the product entries before and after the current product entry in the ordered set of product entries that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param productEntryId the primary key of the current product entry
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product entry
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	public ProductEntry[] filterFindByUuid_C_PrevAndNext(
			long productEntryId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
				orderByComparator)
		throws NoSuchProductEntryException;

	/**
	 * Removes all the product entries where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of product entries where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product entries
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of product entries that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching product entries that the user has permission to view
	 */
	public int filterCountByUuid_C(String uuid, long companyId);

	/**
	 * Returns the product entry where productEntryKey = &#63; or throws a <code>NoSuchProductEntryException</code> if it could not be found.
	 *
	 * @param productEntryKey the product entry key
	 * @return the matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public ProductEntry findByProductEntryKey(String productEntryKey)
		throws NoSuchProductEntryException;

	/**
	 * Returns the product entry where productEntryKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productEntryKey the product entry key
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public ProductEntry fetchByProductEntryKey(String productEntryKey);

	/**
	 * Returns the product entry where productEntryKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productEntryKey the product entry key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public ProductEntry fetchByProductEntryKey(
		String productEntryKey, boolean useFinderCache);

	/**
	 * Removes the product entry where productEntryKey = &#63; from the database.
	 *
	 * @param productEntryKey the product entry key
	 * @return the product entry that was removed
	 */
	public ProductEntry removeByProductEntryKey(String productEntryKey)
		throws NoSuchProductEntryException;

	/**
	 * Returns the number of product entries where productEntryKey = &#63;.
	 *
	 * @param productEntryKey the product entry key
	 * @return the number of matching product entries
	 */
	public int countByProductEntryKey(String productEntryKey);

	/**
	 * Returns the product entry where name = &#63; or throws a <code>NoSuchProductEntryException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public ProductEntry findByName(String name)
		throws NoSuchProductEntryException;

	/**
	 * Returns the product entry where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public ProductEntry fetchByName(String name);

	/**
	 * Returns the product entry where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public ProductEntry fetchByName(String name, boolean useFinderCache);

	/**
	 * Removes the product entry where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the product entry that was removed
	 */
	public ProductEntry removeByName(String name)
		throws NoSuchProductEntryException;

	/**
	 * Returns the number of product entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching product entries
	 */
	public int countByName(String name);

	/**
	 * Caches the product entry in the entity cache if it is enabled.
	 *
	 * @param productEntry the product entry
	 */
	public void cacheResult(ProductEntry productEntry);

	/**
	 * Caches the product entries in the entity cache if it is enabled.
	 *
	 * @param productEntries the product entries
	 */
	public void cacheResult(java.util.List<ProductEntry> productEntries);

	/**
	 * Creates a new product entry with the primary key. Does not add the product entry to the database.
	 *
	 * @param productEntryId the primary key for the new product entry
	 * @return the new product entry
	 */
	public ProductEntry create(long productEntryId);

	/**
	 * Removes the product entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry that was removed
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	public ProductEntry remove(long productEntryId)
		throws NoSuchProductEntryException;

	public ProductEntry updateImpl(ProductEntry productEntry);

	/**
	 * Returns the product entry with the primary key or throws a <code>NoSuchProductEntryException</code> if it could not be found.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	public ProductEntry findByPrimaryKey(long productEntryId)
		throws NoSuchProductEntryException;

	/**
	 * Returns the product entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry, or <code>null</code> if a product entry with the primary key could not be found
	 */
	public ProductEntry fetchByPrimaryKey(long productEntryId);

	/**
	 * Returns all the product entries.
	 *
	 * @return the product entries
	 */
	public java.util.List<ProductEntry> findAll();

	/**
	 * Returns a range of all the product entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @return the range of product entries
	 */
	public java.util.List<ProductEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the product entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product entries
	 */
	public java.util.List<ProductEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the product entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of product entries
	 */
	public java.util.List<ProductEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the product entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of product entries.
	 *
	 * @return the number of product entries
	 */
	public int countAll();

}