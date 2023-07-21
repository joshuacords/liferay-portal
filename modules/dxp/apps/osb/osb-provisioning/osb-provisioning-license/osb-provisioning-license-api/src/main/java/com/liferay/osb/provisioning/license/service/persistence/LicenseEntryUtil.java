/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.service.persistence;

import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the license entry service. This utility wraps <code>com.liferay.osb.provisioning.license.service.persistence.impl.LicenseEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryPersistence
 * @generated
 */
public class LicenseEntryUtil {

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
	public static void clearCache(LicenseEntry licenseEntry) {
		getPersistence().clearCache(licenseEntry);
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
	public static Map<Serializable, LicenseEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LicenseEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LicenseEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LicenseEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LicenseEntry update(LicenseEntry licenseEntry) {
		return getPersistence().update(licenseEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LicenseEntry update(
		LicenseEntry licenseEntry, ServiceContext serviceContext) {

		return getPersistence().update(licenseEntry, serviceContext);
	}

	/**
	 * Returns all the license entries where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @return the matching license entries
	 */
	public static List<LicenseEntry> findByProductKey(String productKey) {
		return getPersistence().findByProductKey(productKey);
	}

	/**
	 * Returns a range of all the license entries where productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productKey the product key
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 */
	public static List<LicenseEntry> findByProductKey(
		String productKey, int start, int end) {

		return getPersistence().findByProductKey(productKey, start, end);
	}

	/**
	 * Returns an ordered range of all the license entries where productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productKey the product key
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 */
	public static List<LicenseEntry> findByProductKey(
		String productKey, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().findByProductKey(
			productKey, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license entries where productKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productKey the product key
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license entries
	 */
	public static List<LicenseEntry> findByProductKey(
		String productKey, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByProductKey(
			productKey, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license entry in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public static LicenseEntry findByProductKey_First(
			String productKey,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByProductKey_First(
			productKey, orderByComparator);
	}

	/**
	 * Returns the first license entry in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public static LicenseEntry fetchByProductKey_First(
		String productKey, OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().fetchByProductKey_First(
			productKey, orderByComparator);
	}

	/**
	 * Returns the last license entry in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public static LicenseEntry findByProductKey_Last(
			String productKey,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByProductKey_Last(
			productKey, orderByComparator);
	}

	/**
	 * Returns the last license entry in the ordered set where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public static LicenseEntry fetchByProductKey_Last(
		String productKey, OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().fetchByProductKey_Last(
			productKey, orderByComparator);
	}

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where productKey = &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param productKey the product key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public static LicenseEntry[] findByProductKey_PrevAndNext(
			long licenseEntryId, String productKey,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByProductKey_PrevAndNext(
			licenseEntryId, productKey, orderByComparator);
	}

	/**
	 * Removes all the license entries where productKey = &#63; from the database.
	 *
	 * @param productKey the product key
	 */
	public static void removeByProductKey(String productKey) {
		getPersistence().removeByProductKey(productKey);
	}

	/**
	 * Returns the number of license entries where productKey = &#63;.
	 *
	 * @param productKey the product key
	 * @return the number of matching license entries
	 */
	public static int countByProductKey(String productKey) {
		return getPersistence().countByProductKey(productKey);
	}

	/**
	 * Returns all the license entries where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the matching license entries
	 */
	public static List<LicenseEntry> findByLikeName(String name) {
		return getPersistence().findByLikeName(name);
	}

	/**
	 * Returns a range of all the license entries where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 */
	public static List<LicenseEntry> findByLikeName(
		String name, int start, int end) {

		return getPersistence().findByLikeName(name, start, end);
	}

	/**
	 * Returns an ordered range of all the license entries where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 */
	public static List<LicenseEntry> findByLikeName(
		String name, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().findByLikeName(
			name, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license entries where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license entries
	 */
	public static List<LicenseEntry> findByLikeName(
		String name, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByLikeName(
			name, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license entry in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public static LicenseEntry findByLikeName_First(
			String name, OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByLikeName_First(name, orderByComparator);
	}

	/**
	 * Returns the first license entry in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public static LicenseEntry fetchByLikeName_First(
		String name, OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().fetchByLikeName_First(name, orderByComparator);
	}

	/**
	 * Returns the last license entry in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public static LicenseEntry findByLikeName_Last(
			String name, OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByLikeName_Last(name, orderByComparator);
	}

	/**
	 * Returns the last license entry in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public static LicenseEntry fetchByLikeName_Last(
		String name, OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().fetchByLikeName_Last(name, orderByComparator);
	}

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where name LIKE &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public static LicenseEntry[] findByLikeName_PrevAndNext(
			long licenseEntryId, String name,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByLikeName_PrevAndNext(
			licenseEntryId, name, orderByComparator);
	}

	/**
	 * Removes all the license entries where name LIKE &#63; from the database.
	 *
	 * @param name the name
	 */
	public static void removeByLikeName(String name) {
		getPersistence().removeByLikeName(name);
	}

	/**
	 * Returns the number of license entries where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the number of matching license entries
	 */
	public static int countByLikeName(String name) {
		return getPersistence().countByLikeName(name);
	}

	/**
	 * Returns all the license entries where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching license entries
	 */
	public static List<LicenseEntry> findByType(String type) {
		return getPersistence().findByType(type);
	}

	/**
	 * Returns a range of all the license entries where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 */
	public static List<LicenseEntry> findByType(
		String type, int start, int end) {

		return getPersistence().findByType(type, start, end);
	}

	/**
	 * Returns an ordered range of all the license entries where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 */
	public static List<LicenseEntry> findByType(
		String type, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().findByType(type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license entries where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license entries
	 */
	public static List<LicenseEntry> findByType(
		String type, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByType(
			type, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license entry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public static LicenseEntry findByType_First(
			String type, OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByType_First(type, orderByComparator);
	}

	/**
	 * Returns the first license entry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public static LicenseEntry fetchByType_First(
		String type, OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().fetchByType_First(type, orderByComparator);
	}

	/**
	 * Returns the last license entry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public static LicenseEntry findByType_Last(
			String type, OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByType_Last(type, orderByComparator);
	}

	/**
	 * Returns the last license entry in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public static LicenseEntry fetchByType_Last(
		String type, OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().fetchByType_Last(type, orderByComparator);
	}

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where type = &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public static LicenseEntry[] findByType_PrevAndNext(
			long licenseEntryId, String type,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByType_PrevAndNext(
			licenseEntryId, type, orderByComparator);
	}

	/**
	 * Removes all the license entries where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	public static void removeByType(String type) {
		getPersistence().removeByType(type);
	}

	/**
	 * Returns the number of license entries where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching license entries
	 */
	public static int countByType(String type) {
		return getPersistence().countByType(type);
	}

	/**
	 * Returns the license entry where productKey = &#63; and type = &#63; or throws a <code>NoSuchLicenseEntryException</code> if it could not be found.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @return the matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public static LicenseEntry findByPK_T(String productKey, String type)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByPK_T(productKey, type);
	}

	/**
	 * Returns the license entry where productKey = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public static LicenseEntry fetchByPK_T(String productKey, String type) {
		return getPersistence().fetchByPK_T(productKey, type);
	}

	/**
	 * Returns the license entry where productKey = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public static LicenseEntry fetchByPK_T(
		String productKey, String type, boolean useFinderCache) {

		return getPersistence().fetchByPK_T(productKey, type, useFinderCache);
	}

	/**
	 * Removes the license entry where productKey = &#63; and type = &#63; from the database.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @return the license entry that was removed
	 */
	public static LicenseEntry removeByPK_T(String productKey, String type)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseEntryException {

		return getPersistence().removeByPK_T(productKey, type);
	}

	/**
	 * Returns the number of license entries where productKey = &#63; and type = &#63;.
	 *
	 * @param productKey the product key
	 * @param type the type
	 * @return the number of matching license entries
	 */
	public static int countByPK_T(String productKey, String type) {
		return getPersistence().countByPK_T(productKey, type);
	}

	/**
	 * Caches the license entry in the entity cache if it is enabled.
	 *
	 * @param licenseEntry the license entry
	 */
	public static void cacheResult(LicenseEntry licenseEntry) {
		getPersistence().cacheResult(licenseEntry);
	}

	/**
	 * Caches the license entries in the entity cache if it is enabled.
	 *
	 * @param licenseEntries the license entries
	 */
	public static void cacheResult(List<LicenseEntry> licenseEntries) {
		getPersistence().cacheResult(licenseEntries);
	}

	/**
	 * Creates a new license entry with the primary key. Does not add the license entry to the database.
	 *
	 * @param licenseEntryId the primary key for the new license entry
	 * @return the new license entry
	 */
	public static LicenseEntry create(long licenseEntryId) {
		return getPersistence().create(licenseEntryId);
	}

	/**
	 * Removes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry that was removed
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public static LicenseEntry remove(long licenseEntryId)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseEntryException {

		return getPersistence().remove(licenseEntryId);
	}

	public static LicenseEntry updateImpl(LicenseEntry licenseEntry) {
		return getPersistence().updateImpl(licenseEntry);
	}

	/**
	 * Returns the license entry with the primary key or throws a <code>NoSuchLicenseEntryException</code> if it could not be found.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public static LicenseEntry findByPrimaryKey(long licenseEntryId)
		throws com.liferay.osb.provisioning.license.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByPrimaryKey(licenseEntryId);
	}

	/**
	 * Returns the license entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry, or <code>null</code> if a license entry with the primary key could not be found
	 */
	public static LicenseEntry fetchByPrimaryKey(long licenseEntryId) {
		return getPersistence().fetchByPrimaryKey(licenseEntryId);
	}

	/**
	 * Returns all the license entries.
	 *
	 * @return the license entries
	 */
	public static List<LicenseEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of license entries
	 */
	public static List<LicenseEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of license entries
	 */
	public static List<LicenseEntry> findAll(
		int start, int end, OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of license entries
	 */
	public static List<LicenseEntry> findAll(
		int start, int end, OrderByComparator<LicenseEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the license entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of license entries.
	 *
	 * @return the number of license entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LicenseEntryPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(LicenseEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile LicenseEntryPersistence _persistence;

}