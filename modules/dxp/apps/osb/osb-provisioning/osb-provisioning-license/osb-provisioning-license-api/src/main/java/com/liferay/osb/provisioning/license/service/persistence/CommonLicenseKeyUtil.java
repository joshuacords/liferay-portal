/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.service.persistence;

import com.liferay.osb.provisioning.license.model.CommonLicenseKey;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the common license key service. This utility wraps <code>com.liferay.osb.provisioning.license.service.persistence.impl.CommonLicenseKeyPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommonLicenseKeyPersistence
 * @generated
 */
public class CommonLicenseKeyUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(CommonLicenseKey commonLicenseKey) {
		getPersistence().clearCache(commonLicenseKey);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, CommonLicenseKey> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommonLicenseKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommonLicenseKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommonLicenseKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommonLicenseKey update(CommonLicenseKey commonLicenseKey) {
		return getPersistence().update(commonLicenseKey);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommonLicenseKey update(
		CommonLicenseKey commonLicenseKey, ServiceContext serviceContext) {

		return getPersistence().update(commonLicenseKey, serviceContext);
	}

	/**
	 * Returns all the common license keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching common license keies
	 */
	public static List<CommonLicenseKey> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<CommonLicenseKey> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<CommonLicenseKey> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<CommonLicenseKey> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first common license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public static CommonLicenseKey findByUuid_First(
			String uuid, OrderByComparator<CommonLicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first common license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public static CommonLicenseKey fetchByUuid_First(
		String uuid, OrderByComparator<CommonLicenseKey> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last common license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public static CommonLicenseKey findByUuid_Last(
			String uuid, OrderByComparator<CommonLicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last common license key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public static CommonLicenseKey fetchByUuid_Last(
		String uuid, OrderByComparator<CommonLicenseKey> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the common license keies before and after the current common license key in the ordered set where uuid = &#63;.
	 *
	 * @param commonLicenseKeyId the primary key of the current common license key
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next common license key
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	public static CommonLicenseKey[] findByUuid_PrevAndNext(
			long commonLicenseKeyId, String uuid,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().findByUuid_PrevAndNext(
			commonLicenseKeyId, uuid, orderByComparator);
	}

	/**
	 * Removes all the common license keies where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of common license keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching common license keies
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the common license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching common license keies
	 */
	public static List<CommonLicenseKey> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<CommonLicenseKey> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<CommonLicenseKey> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<CommonLicenseKey> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first common license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public static CommonLicenseKey findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first common license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public static CommonLicenseKey fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last common license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public static CommonLicenseKey findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last common license key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public static CommonLicenseKey fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

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
	public static CommonLicenseKey[] findByUuid_C_PrevAndNext(
			long commonLicenseKeyId, String uuid, long companyId,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().findByUuid_C_PrevAndNext(
			commonLicenseKeyId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the common license keies where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of common license keies where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching common license keies
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the common license keies where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @return the matching common license keies
	 */
	public static List<CommonLicenseKey> findByProductGroup(
		String productGroup) {

		return getPersistence().findByProductGroup(productGroup);
	}

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
	public static List<CommonLicenseKey> findByProductGroup(
		String productGroup, int start, int end) {

		return getPersistence().findByProductGroup(productGroup, start, end);
	}

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
	public static List<CommonLicenseKey> findByProductGroup(
		String productGroup, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return getPersistence().findByProductGroup(
			productGroup, start, end, orderByComparator);
	}

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
	public static List<CommonLicenseKey> findByProductGroup(
		String productGroup, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByProductGroup(
			productGroup, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public static CommonLicenseKey findByProductGroup_First(
			String productGroup,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().findByProductGroup_First(
			productGroup, orderByComparator);
	}

	/**
	 * Returns the first common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public static CommonLicenseKey fetchByProductGroup_First(
		String productGroup,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return getPersistence().fetchByProductGroup_First(
			productGroup, orderByComparator);
	}

	/**
	 * Returns the last common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public static CommonLicenseKey findByProductGroup_Last(
			String productGroup,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().findByProductGroup_Last(
			productGroup, orderByComparator);
	}

	/**
	 * Returns the last common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public static CommonLicenseKey fetchByProductGroup_Last(
		String productGroup,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return getPersistence().fetchByProductGroup_Last(
			productGroup, orderByComparator);
	}

	/**
	 * Returns the common license keies before and after the current common license key in the ordered set where productGroup = &#63;.
	 *
	 * @param commonLicenseKeyId the primary key of the current common license key
	 * @param productGroup the product group
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next common license key
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	public static CommonLicenseKey[] findByProductGroup_PrevAndNext(
			long commonLicenseKeyId, String productGroup,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().findByProductGroup_PrevAndNext(
			commonLicenseKeyId, productGroup, orderByComparator);
	}

	/**
	 * Removes all the common license keies where productGroup = &#63; from the database.
	 *
	 * @param productGroup the product group
	 */
	public static void removeByProductGroup(String productGroup) {
		getPersistence().removeByProductGroup(productGroup);
	}

	/**
	 * Returns the number of common license keies where productGroup = &#63;.
	 *
	 * @param productGroup the product group
	 * @return the number of matching common license keies
	 */
	public static int countByProductGroup(String productGroup) {
		return getPersistence().countByProductGroup(productGroup);
	}

	/**
	 * Returns the common license key where fileName = &#63; or throws a <code>NoSuchCommonLicenseKeyException</code> if it could not be found.
	 *
	 * @param fileName the file name
	 * @return the matching common license key
	 * @throws NoSuchCommonLicenseKeyException if a matching common license key could not be found
	 */
	public static CommonLicenseKey findByFileName(String fileName)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().findByFileName(fileName);
	}

	/**
	 * Returns the common license key where fileName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fileName the file name
	 * @return the matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public static CommonLicenseKey fetchByFileName(String fileName) {
		return getPersistence().fetchByFileName(fileName);
	}

	/**
	 * Returns the common license key where fileName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fileName the file name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching common license key, or <code>null</code> if a matching common license key could not be found
	 */
	public static CommonLicenseKey fetchByFileName(
		String fileName, boolean useFinderCache) {

		return getPersistence().fetchByFileName(fileName, useFinderCache);
	}

	/**
	 * Removes the common license key where fileName = &#63; from the database.
	 *
	 * @param fileName the file name
	 * @return the common license key that was removed
	 */
	public static CommonLicenseKey removeByFileName(String fileName)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().removeByFileName(fileName);
	}

	/**
	 * Returns the number of common license keies where fileName = &#63;.
	 *
	 * @param fileName the file name
	 * @return the number of matching common license keies
	 */
	public static int countByFileName(String fileName) {
		return getPersistence().countByFileName(fileName);
	}

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
	public static List<CommonLicenseKey> findByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate) {

		return getPersistence().findByPG_PE_PV_gtS_ltE(
			productGroup, productEnvironment, productVersion, startDate,
			endDate);
	}

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
	public static List<CommonLicenseKey> findByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate, int start, int end) {

		return getPersistence().findByPG_PE_PV_gtS_ltE(
			productGroup, productEnvironment, productVersion, startDate,
			endDate, start, end);
	}

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
	public static List<CommonLicenseKey> findByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return getPersistence().findByPG_PE_PV_gtS_ltE(
			productGroup, productEnvironment, productVersion, startDate,
			endDate, start, end, orderByComparator);
	}

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
	public static List<CommonLicenseKey> findByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate, int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPG_PE_PV_gtS_ltE(
			productGroup, productEnvironment, productVersion, startDate,
			endDate, start, end, orderByComparator, useFinderCache);
	}

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
	public static CommonLicenseKey findByPG_PE_PV_gtS_ltE_First(
			String productGroup, String productEnvironment,
			String productVersion, Date startDate, Date endDate,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().findByPG_PE_PV_gtS_ltE_First(
			productGroup, productEnvironment, productVersion, startDate,
			endDate, orderByComparator);
	}

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
	public static CommonLicenseKey fetchByPG_PE_PV_gtS_ltE_First(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return getPersistence().fetchByPG_PE_PV_gtS_ltE_First(
			productGroup, productEnvironment, productVersion, startDate,
			endDate, orderByComparator);
	}

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
	public static CommonLicenseKey findByPG_PE_PV_gtS_ltE_Last(
			String productGroup, String productEnvironment,
			String productVersion, Date startDate, Date endDate,
			OrderByComparator<CommonLicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().findByPG_PE_PV_gtS_ltE_Last(
			productGroup, productEnvironment, productVersion, startDate,
			endDate, orderByComparator);
	}

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
	public static CommonLicenseKey fetchByPG_PE_PV_gtS_ltE_Last(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return getPersistence().fetchByPG_PE_PV_gtS_ltE_Last(
			productGroup, productEnvironment, productVersion, startDate,
			endDate, orderByComparator);
	}

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
	public static CommonLicenseKey[] findByPG_PE_PV_gtS_ltE_PrevAndNext(
			long commonLicenseKeyId, String productGroup,
			String productEnvironment, String productVersion, Date startDate,
			Date endDate, OrderByComparator<CommonLicenseKey> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().findByPG_PE_PV_gtS_ltE_PrevAndNext(
			commonLicenseKeyId, productGroup, productEnvironment,
			productVersion, startDate, endDate, orderByComparator);
	}

	/**
	 * Removes all the common license keies where productGroup = &#63; and productEnvironment = &#63; and productVersion = &#63; and startDate &lt; &#63; and endDate &gt; &#63; from the database.
	 *
	 * @param productGroup the product group
	 * @param productEnvironment the product environment
	 * @param productVersion the product version
	 * @param startDate the start date
	 * @param endDate the end date
	 */
	public static void removeByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate) {

		getPersistence().removeByPG_PE_PV_gtS_ltE(
			productGroup, productEnvironment, productVersion, startDate,
			endDate);
	}

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
	public static int countByPG_PE_PV_gtS_ltE(
		String productGroup, String productEnvironment, String productVersion,
		Date startDate, Date endDate) {

		return getPersistence().countByPG_PE_PV_gtS_ltE(
			productGroup, productEnvironment, productVersion, startDate,
			endDate);
	}

	/**
	 * Caches the common license key in the entity cache if it is enabled.
	 *
	 * @param commonLicenseKey the common license key
	 */
	public static void cacheResult(CommonLicenseKey commonLicenseKey) {
		getPersistence().cacheResult(commonLicenseKey);
	}

	/**
	 * Caches the common license keies in the entity cache if it is enabled.
	 *
	 * @param commonLicenseKeies the common license keies
	 */
	public static void cacheResult(List<CommonLicenseKey> commonLicenseKeies) {
		getPersistence().cacheResult(commonLicenseKeies);
	}

	/**
	 * Creates a new common license key with the primary key. Does not add the common license key to the database.
	 *
	 * @param commonLicenseKeyId the primary key for the new common license key
	 * @return the new common license key
	 */
	public static CommonLicenseKey create(long commonLicenseKeyId) {
		return getPersistence().create(commonLicenseKeyId);
	}

	/**
	 * Removes the common license key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commonLicenseKeyId the primary key of the common license key
	 * @return the common license key that was removed
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	public static CommonLicenseKey remove(long commonLicenseKeyId)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().remove(commonLicenseKeyId);
	}

	public static CommonLicenseKey updateImpl(
		CommonLicenseKey commonLicenseKey) {

		return getPersistence().updateImpl(commonLicenseKey);
	}

	/**
	 * Returns the common license key with the primary key or throws a <code>NoSuchCommonLicenseKeyException</code> if it could not be found.
	 *
	 * @param commonLicenseKeyId the primary key of the common license key
	 * @return the common license key
	 * @throws NoSuchCommonLicenseKeyException if a common license key with the primary key could not be found
	 */
	public static CommonLicenseKey findByPrimaryKey(long commonLicenseKeyId)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchCommonLicenseKeyException {

		return getPersistence().findByPrimaryKey(commonLicenseKeyId);
	}

	/**
	 * Returns the common license key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commonLicenseKeyId the primary key of the common license key
	 * @return the common license key, or <code>null</code> if a common license key with the primary key could not be found
	 */
	public static CommonLicenseKey fetchByPrimaryKey(long commonLicenseKeyId) {
		return getPersistence().fetchByPrimaryKey(commonLicenseKeyId);
	}

	/**
	 * Returns all the common license keies.
	 *
	 * @return the common license keies
	 */
	public static List<CommonLicenseKey> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<CommonLicenseKey> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<CommonLicenseKey> findAll(
		int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<CommonLicenseKey> findAll(
		int start, int end,
		OrderByComparator<CommonLicenseKey> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the common license keies from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of common license keies.
	 *
	 * @return the number of common license keies
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommonLicenseKeyPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(CommonLicenseKeyPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile CommonLicenseKeyPersistence _persistence;

}