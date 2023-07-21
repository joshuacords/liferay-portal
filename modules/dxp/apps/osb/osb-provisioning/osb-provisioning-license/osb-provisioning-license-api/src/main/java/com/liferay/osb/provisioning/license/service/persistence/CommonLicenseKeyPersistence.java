/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.service.persistence;

import com.liferay.osb.provisioning.license.exception.NoSuchCommonLicenseKeyException;
import com.liferay.osb.provisioning.license.model.CommonLicenseKey;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the common license key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommonLicenseKeyUtil
 * @generated
 */
@ProviderType
public interface CommonLicenseKeyPersistence
	extends BasePersistence<CommonLicenseKey> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommonLicenseKeyUtil} to access the common license key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the common license keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByUuid(String uuid);

	/**
	 * Returns a range of all the common license keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @return the range of matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the common license keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator);

	/**
	 * Returns an ordered range of all the common license keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first common license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public CommonLicenseKey findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
				orderByComparator)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Returns the first common license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public CommonLicenseKey fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator);

	/**
	 * Returns the last common license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public CommonLicenseKey findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
				orderByComparator)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Returns the last common license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public CommonLicenseKey fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator);

	/**
	 * Returns the common license keies before and after the current common license key in the ordered set where uuid = &#63;.
	 *
	 * @param commonLicenseKeyId the primary key of the current common license key
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next common license key
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	public CommonLicenseKey[] findByUuid_PrevAndNext(
			long commonLicenseKeyId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
				orderByComparator)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Removes all the common license keies where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of common license keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching common license keies
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the common license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the common license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @return the range of matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the common license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator);

	/**
	 * Returns an ordered range of all the common license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first common license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public CommonLicenseKey findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
				orderByComparator)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Returns the first common license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public CommonLicenseKey fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator);

	/**
	 * Returns the last common license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public CommonLicenseKey findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
				orderByComparator)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Returns the last common license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public CommonLicenseKey fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator);

	/**
	 * Returns the common license keies before and after the current common license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commonLicenseKeyId the primary key of the current common license key
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next common license key
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	public CommonLicenseKey[] findByUuid_C_PrevAndNext(
			long commonLicenseKeyId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
				orderByComparator)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Removes all the common license keies where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of common license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching common license keies
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the common license keies where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @return the matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByProductGroup(
		String productGroup);

	/**
	 * Returns a range of all the common license keies where productGroup = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productGroup the product group
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @return the range of matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByProductGroup(
		String productGroup, int start, int end);

	/**
	 * Returns an ordered range of all the common license keies where productGroup = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productGroup the product group
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByProductGroup(
		String productGroup, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator);

	/**
	 * Returns an ordered range of all the common license keies where productGroup = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productGroup the product group
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByProductGroup(
		String productGroup, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public CommonLicenseKey findByProductGroup_First(
			String productGroup,
			com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
				orderByComparator)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Returns the first common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public CommonLicenseKey fetchByProductGroup_First(
		String productGroup,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator);

	/**
	 * Returns the last common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public CommonLicenseKey findByProductGroup_Last(
			String productGroup,
			com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
				orderByComparator)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Returns the last common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public CommonLicenseKey fetchByProductGroup_Last(
		String productGroup,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator);

	/**
	 * Returns the common license keies before and after the current common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param commonLicenseKeyId the primary key of the current common license key
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next common license key
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	public CommonLicenseKey[] findByProductGroup_PrevAndNext(
			long commonLicenseKeyId, String productGroup,
			com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
				orderByComparator)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Removes all the common license keies where productGroup = &#63; from the database.
	 *
	 * @param productGroup the product group
	 */
	public void removeByProductGroup(String productGroup);

	/**
	 * Returns the number of common license keies where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @return the number of matching common license keies
	 */
	public int countByProductGroup(String productGroup);

	/**
	 * Returns the common license key where fileName = &#63; or throws a <code>NoSuchCommonLicenseKeyException</code> if it could not be found.
	 *
	 * @param fileName the file name
	 * @return the matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public CommonLicenseKey findByFileName(String fileName)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Returns the common license key where fileName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fileName the file name
	 * @return the matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public CommonLicenseKey fetchByFileName(String fileName);

	/**
	 * Returns the common license key where fileName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fileName the file name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public CommonLicenseKey fetchByFileName(
		String fileName, boolean useFinderCache);

	/**
	 * Removes the common license key where fileName = &#63; from the database.
	 *
	 * @param fileName the file name
	 * @return the common license key that was removed
	 */
	public CommonLicenseKey removeByFileName(String fileName)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Returns the number of common license keies where fileName = &#63;.
	 *
	 * @param fileName the file name
	 * @return the number of matching common license keies
	 */
	public int countByFileName(String fileName);

	/**
	 * Returns all the common license keies where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate);

	/**
	 * Returns a range of all the common license keies where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @return the range of matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate, int start, int end);

	/**
	 * Returns an ordered range of all the common license keies where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator);

	/**
	 * Returns an ordered range of all the common license keies where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching common license keies
	 */
	public java.util.List<CommonLicenseKey> findByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first common license key in the ordered set where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public CommonLicenseKey findByPG_PE_PV_gtS_ltE_First(
			String productGroup, String productEnvironment,
			String productVersion, Date startDate, Date endDate,
			com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
				orderByComparator)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Returns the first common license key in the ordered set where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public CommonLicenseKey fetchByPG_PE_PV_gtS_ltE_First(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator);

	/**
	 * Returns the last common license key in the ordered set where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public CommonLicenseKey findByPG_PE_PV_gtS_ltE_Last(
			String productGroup, String productEnvironment,
			String productVersion, Date startDate, Date endDate,
			com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
				orderByComparator)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Returns the last common license key in the ordered set where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public CommonLicenseKey fetchByPG_PE_PV_gtS_ltE_Last(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator);

	/**
	 * Returns the common license keies before and after the current common license key in the ordered set where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param commonLicenseKeyId the primary key of the current common license key
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next common license key
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	public CommonLicenseKey[] findByPG_PE_PV_gtS_ltE_PrevAndNext(
			long commonLicenseKeyId, String productGroup,
			String productEnvironment, String productVersion, Date startDate,
			Date endDate,
			com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
				orderByComparator)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Removes all the common license keies where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63; from the database.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 */
	public void removeByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate);

	/**
	 * Returns the number of common license keies where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63;.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the number of matching common license keies
	 */
	public int countByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate);

	/**
	 * Caches the common license key in the entity cache if it is enabled.
	 *
	 * @param commonLicenseKey the common license key
	 */
	public void cacheResult(CommonLicenseKey commonLicenseKey);

	/**
	 * Caches the common license keies in the entity cache if it is enabled.
	 *
	 * @param commonLicenseKeies the common license keies
	 */
	public void cacheResult(
		java.util.List<CommonLicenseKey> commonLicenseKeies);

	/**
	 * Creates a new common license key with the primary key. Does not add the common license key to the database.
	 *
	 * @param commonLicenseKeyId the primary key for the new common license key
	 * @return the new common license key
	 */
	public CommonLicenseKey create(long commonLicenseKeyId);

	/**
	 * Removes the common license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commonLicenseKeyId the primary key of the common license key
	 * @return the common license key that was removed
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	public CommonLicenseKey remove(long commonLicenseKeyId)
		throws NoSuchCommonLicenseKeyException;

	public CommonLicenseKey updateImpl(CommonLicenseKey commonLicenseKey);

	/**
	 * Returns the common license key with the primary key or throws a <code>NoSuchCommonLicenseKeyException</code> if it could not be found.
	 *
	 * @param commonLicenseKeyId the primary key of the common license key
	 * @return the common license key
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	public CommonLicenseKey findByPrimaryKey(long commonLicenseKeyId)
		throws NoSuchCommonLicenseKeyException;

	/**
	 * Returns the common license key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commonLicenseKeyId the primary key of the common license key
	 * @return the common license key, or <code>null</code> if a common license key with the primary key could not be found
	 */
	public CommonLicenseKey fetchByPrimaryKey(long commonLicenseKeyId);

	/**
	 * Returns all the common license keies.
	 *
	 * @return the common license keies
	 */
	public java.util.List<CommonLicenseKey> findAll();

	/**
	 * Returns a range of all the common license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @return the range of common license keies
	 */
	public java.util.List<CommonLicenseKey> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the common license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of common license keies
	 */
	public java.util.List<CommonLicenseKey> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator);

	/**
	 * Returns an ordered range of all the common license keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommonLicenseKeyModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of common license keies
	 * @param end the upper bound of the range of common license keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of common license keies
	 */
	public java.util.List<CommonLicenseKey> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CommonLicenseKey>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the common license keies from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of common license keies.
	 *
	 * @return the number of common license keies
	 */
	public int countAll();

}