/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the account service. This utility wraps <code>com.liferay.osb.koroneiki.taproot.service.persistence.impl.AccountPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountPersistence
 * @generated
 */
public class AccountUtil {

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
	public static void clearCache(Account account) {
		getPersistence().clearCache(account);
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
	public static Map<Serializable, Account> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Account> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Account> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Account> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Account> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Account update(Account account) {
		return getPersistence().update(account);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Account update(
		Account account, ServiceContext serviceContext) {

		return getPersistence().update(account, serviceContext);
	}

	/**
	 * Returns all the accounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching accounts
	 */
	public static List<Account> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the accounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @return the range of matching accounts
	 */
	public static List<Account> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the accounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching accounts
	 */
	public static List<Account> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Account> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the accounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching accounts
	 */
	public static List<Account> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Account> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public static Account findByUuid_First(
			String uuid, OrderByComparator<Account> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account, or <code>null</code> if a matching account could not be found
	 */
	public static Account fetchByUuid_First(
		String uuid, OrderByComparator<Account> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public static Account findByUuid_Last(
			String uuid, OrderByComparator<Account> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account, or <code>null</code> if a matching account could not be found
	 */
	public static Account fetchByUuid_Last(
		String uuid, OrderByComparator<Account> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the accounts before and after the current account in the ordered set where uuid = &#63;.
	 *
	 * @param accountId the primary key of the current account
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account
	 * @throws NoSuchAccountException if a account with the primary key could not be found
	 */
	public static Account[] findByUuid_PrevAndNext(
			long accountId, String uuid,
			OrderByComparator<Account> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().findByUuid_PrevAndNext(
			accountId, uuid, orderByComparator);
	}

	/**
	 * Returns all the accounts that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching accounts that the user has permission to view
	 */
	public static List<Account> filterFindByUuid(String uuid) {
		return getPersistence().filterFindByUuid(uuid);
	}

	/**
	 * Returns a range of all the accounts that the user has permission to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @return the range of matching accounts that the user has permission to view
	 */
	public static List<Account> filterFindByUuid(
		String uuid, int start, int end) {

		return getPersistence().filterFindByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the accounts that the user has permissions to view where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching accounts that the user has permission to view
	 */
	public static List<Account> filterFindByUuid(
		String uuid, int start, int end,
		OrderByComparator<Account> orderByComparator) {

		return getPersistence().filterFindByUuid(
			uuid, start, end, orderByComparator);
	}

	/**
	 * Returns the accounts before and after the current account in the ordered set of accounts that the user has permission to view where uuid = &#63;.
	 *
	 * @param accountId the primary key of the current account
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account
	 * @throws NoSuchAccountException if a account with the primary key could not be found
	 */
	public static Account[] filterFindByUuid_PrevAndNext(
			long accountId, String uuid,
			OrderByComparator<Account> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().filterFindByUuid_PrevAndNext(
			accountId, uuid, orderByComparator);
	}

	/**
	 * Removes all the accounts where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of accounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching accounts
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the number of accounts that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching accounts that the user has permission to view
	 */
	public static int filterCountByUuid(String uuid) {
		return getPersistence().filterCountByUuid(uuid);
	}

	/**
	 * Returns all the accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching accounts
	 */
	public static List<Account> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @return the range of matching accounts
	 */
	public static List<Account> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching accounts
	 */
	public static List<Account> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Account> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching accounts
	 */
	public static List<Account> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Account> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public static Account findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Account> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account, or <code>null</code> if a matching account could not be found
	 */
	public static Account fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Account> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public static Account findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Account> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account, or <code>null</code> if a matching account could not be found
	 */
	public static Account fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Account> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the accounts before and after the current account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param accountId the primary key of the current account
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account
	 * @throws NoSuchAccountException if a account with the primary key could not be found
	 */
	public static Account[] findByUuid_C_PrevAndNext(
			long accountId, String uuid, long companyId,
			OrderByComparator<Account> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().findByUuid_C_PrevAndNext(
			accountId, uuid, companyId, orderByComparator);
	}

	/**
	 * Returns all the accounts that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching accounts that the user has permission to view
	 */
	public static List<Account> filterFindByUuid_C(
		String uuid, long companyId) {

		return getPersistence().filterFindByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the accounts that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @return the range of matching accounts that the user has permission to view
	 */
	public static List<Account> filterFindByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().filterFindByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the accounts that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching accounts that the user has permission to view
	 */
	public static List<Account> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Account> orderByComparator) {

		return getPersistence().filterFindByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the accounts before and after the current account in the ordered set of accounts that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param accountId the primary key of the current account
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account
	 * @throws NoSuchAccountException if a account with the primary key could not be found
	 */
	public static Account[] filterFindByUuid_C_PrevAndNext(
			long accountId, String uuid, long companyId,
			OrderByComparator<Account> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().filterFindByUuid_C_PrevAndNext(
			accountId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the accounts where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching accounts
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of accounts that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching accounts that the user has permission to view
	 */
	public static int filterCountByUuid_C(String uuid, long companyId) {
		return getPersistence().filterCountByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the account where accountKey = &#63; or throws a <code>NoSuchAccountException</code> if it could not be found.
	 *
	 * @param accountKey the account key
	 * @return the matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public static Account findByAccountKey(String accountKey)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().findByAccountKey(accountKey);
	}

	/**
	 * Returns the account where accountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountKey the account key
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 */
	public static Account fetchByAccountKey(String accountKey) {
		return getPersistence().fetchByAccountKey(accountKey);
	}

	/**
	 * Returns the account where accountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountKey the account key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 */
	public static Account fetchByAccountKey(
		String accountKey, boolean useFinderCache) {

		return getPersistence().fetchByAccountKey(accountKey, useFinderCache);
	}

	/**
	 * Removes the account where accountKey = &#63; from the database.
	 *
	 * @param accountKey the account key
	 * @return the account that was removed
	 */
	public static Account removeByAccountKey(String accountKey)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().removeByAccountKey(accountKey);
	}

	/**
	 * Returns the number of accounts where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @return the number of matching accounts
	 */
	public static int countByAccountKey(String accountKey) {
		return getPersistence().countByAccountKey(accountKey);
	}

	/**
	 * Returns all the accounts where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @return the matching accounts
	 */
	public static List<Account> findByParentAccountId(long parentAccountId) {
		return getPersistence().findByParentAccountId(parentAccountId);
	}

	/**
	 * Returns a range of all the accounts where parentAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param parentAccountId the parent account ID
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @return the range of matching accounts
	 */
	public static List<Account> findByParentAccountId(
		long parentAccountId, int start, int end) {

		return getPersistence().findByParentAccountId(
			parentAccountId, start, end);
	}

	/**
	 * Returns an ordered range of all the accounts where parentAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param parentAccountId the parent account ID
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching accounts
	 */
	public static List<Account> findByParentAccountId(
		long parentAccountId, int start, int end,
		OrderByComparator<Account> orderByComparator) {

		return getPersistence().findByParentAccountId(
			parentAccountId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the accounts where parentAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param parentAccountId the parent account ID
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching accounts
	 */
	public static List<Account> findByParentAccountId(
		long parentAccountId, int start, int end,
		OrderByComparator<Account> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByParentAccountId(
			parentAccountId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first account in the ordered set where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public static Account findByParentAccountId_First(
			long parentAccountId, OrderByComparator<Account> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().findByParentAccountId_First(
			parentAccountId, orderByComparator);
	}

	/**
	 * Returns the first account in the ordered set where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account, or <code>null</code> if a matching account could not be found
	 */
	public static Account fetchByParentAccountId_First(
		long parentAccountId, OrderByComparator<Account> orderByComparator) {

		return getPersistence().fetchByParentAccountId_First(
			parentAccountId, orderByComparator);
	}

	/**
	 * Returns the last account in the ordered set where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public static Account findByParentAccountId_Last(
			long parentAccountId, OrderByComparator<Account> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().findByParentAccountId_Last(
			parentAccountId, orderByComparator);
	}

	/**
	 * Returns the last account in the ordered set where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account, or <code>null</code> if a matching account could not be found
	 */
	public static Account fetchByParentAccountId_Last(
		long parentAccountId, OrderByComparator<Account> orderByComparator) {

		return getPersistence().fetchByParentAccountId_Last(
			parentAccountId, orderByComparator);
	}

	/**
	 * Returns the accounts before and after the current account in the ordered set where parentAccountId = &#63;.
	 *
	 * @param accountId the primary key of the current account
	 * @param parentAccountId the parent account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account
	 * @throws NoSuchAccountException if a account with the primary key could not be found
	 */
	public static Account[] findByParentAccountId_PrevAndNext(
			long accountId, long parentAccountId,
			OrderByComparator<Account> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().findByParentAccountId_PrevAndNext(
			accountId, parentAccountId, orderByComparator);
	}

	/**
	 * Returns all the accounts that the user has permission to view where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @return the matching accounts that the user has permission to view
	 */
	public static List<Account> filterFindByParentAccountId(
		long parentAccountId) {

		return getPersistence().filterFindByParentAccountId(parentAccountId);
	}

	/**
	 * Returns a range of all the accounts that the user has permission to view where parentAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param parentAccountId the parent account ID
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @return the range of matching accounts that the user has permission to view
	 */
	public static List<Account> filterFindByParentAccountId(
		long parentAccountId, int start, int end) {

		return getPersistence().filterFindByParentAccountId(
			parentAccountId, start, end);
	}

	/**
	 * Returns an ordered range of all the accounts that the user has permissions to view where parentAccountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param parentAccountId the parent account ID
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching accounts that the user has permission to view
	 */
	public static List<Account> filterFindByParentAccountId(
		long parentAccountId, int start, int end,
		OrderByComparator<Account> orderByComparator) {

		return getPersistence().filterFindByParentAccountId(
			parentAccountId, start, end, orderByComparator);
	}

	/**
	 * Returns the accounts before and after the current account in the ordered set of accounts that the user has permission to view where parentAccountId = &#63;.
	 *
	 * @param accountId the primary key of the current account
	 * @param parentAccountId the parent account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account
	 * @throws NoSuchAccountException if a account with the primary key could not be found
	 */
	public static Account[] filterFindByParentAccountId_PrevAndNext(
			long accountId, long parentAccountId,
			OrderByComparator<Account> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().filterFindByParentAccountId_PrevAndNext(
			accountId, parentAccountId, orderByComparator);
	}

	/**
	 * Removes all the accounts where parentAccountId = &#63; from the database.
	 *
	 * @param parentAccountId the parent account ID
	 */
	public static void removeByParentAccountId(long parentAccountId) {
		getPersistence().removeByParentAccountId(parentAccountId);
	}

	/**
	 * Returns the number of accounts where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @return the number of matching accounts
	 */
	public static int countByParentAccountId(long parentAccountId) {
		return getPersistence().countByParentAccountId(parentAccountId);
	}

	/**
	 * Returns the number of accounts that the user has permission to view where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @return the number of matching accounts that the user has permission to view
	 */
	public static int filterCountByParentAccountId(long parentAccountId) {
		return getPersistence().filterCountByParentAccountId(parentAccountId);
	}

	/**
	 * Returns the account where name = &#63; or throws a <code>NoSuchAccountException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public static Account findByName(String name)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().findByName(name);
	}

	/**
	 * Returns the account where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 */
	public static Account fetchByName(String name) {
		return getPersistence().fetchByName(name);
	}

	/**
	 * Returns the account where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 */
	public static Account fetchByName(String name, boolean useFinderCache) {
		return getPersistence().fetchByName(name, useFinderCache);
	}

	/**
	 * Removes the account where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the account that was removed
	 */
	public static Account removeByName(String name)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().removeByName(name);
	}

	/**
	 * Returns the number of accounts where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching accounts
	 */
	public static int countByName(String name) {
		return getPersistence().countByName(name);
	}

	/**
	 * Returns the account where code = &#63; or throws a <code>NoSuchAccountException</code> if it could not be found.
	 *
	 * @param code the code
	 * @return the matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public static Account findByCode(String code)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().findByCode(code);
	}

	/**
	 * Returns the account where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param code the code
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 */
	public static Account fetchByCode(String code) {
		return getPersistence().fetchByCode(code);
	}

	/**
	 * Returns the account where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param code the code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 */
	public static Account fetchByCode(String code, boolean useFinderCache) {
		return getPersistence().fetchByCode(code, useFinderCache);
	}

	/**
	 * Removes the account where code = &#63; from the database.
	 *
	 * @param code the code
	 * @return the account that was removed
	 */
	public static Account removeByCode(String code)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().removeByCode(code);
	}

	/**
	 * Returns the number of accounts where code = &#63;.
	 *
	 * @param code the code
	 * @return the number of matching accounts
	 */
	public static int countByCode(String code) {
		return getPersistence().countByCode(code);
	}

	/**
	 * Caches the account in the entity cache if it is enabled.
	 *
	 * @param account the account
	 */
	public static void cacheResult(Account account) {
		getPersistence().cacheResult(account);
	}

	/**
	 * Caches the accounts in the entity cache if it is enabled.
	 *
	 * @param accounts the accounts
	 */
	public static void cacheResult(List<Account> accounts) {
		getPersistence().cacheResult(accounts);
	}

	/**
	 * Creates a new account with the primary key. Does not add the account to the database.
	 *
	 * @param accountId the primary key for the new account
	 * @return the new account
	 */
	public static Account create(long accountId) {
		return getPersistence().create(accountId);
	}

	/**
	 * Removes the account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountId the primary key of the account
	 * @return the account that was removed
	 * @throws NoSuchAccountException if a account with the primary key could not be found
	 */
	public static Account remove(long accountId)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().remove(accountId);
	}

	public static Account updateImpl(Account account) {
		return getPersistence().updateImpl(account);
	}

	/**
	 * Returns the account with the primary key or throws a <code>NoSuchAccountException</code> if it could not be found.
	 *
	 * @param accountId the primary key of the account
	 * @return the account
	 * @throws NoSuchAccountException if a account with the primary key could not be found
	 */
	public static Account findByPrimaryKey(long accountId)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountException {

		return getPersistence().findByPrimaryKey(accountId);
	}

	/**
	 * Returns the account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountId the primary key of the account
	 * @return the account, or <code>null</code> if a account with the primary key could not be found
	 */
	public static Account fetchByPrimaryKey(long accountId) {
		return getPersistence().fetchByPrimaryKey(accountId);
	}

	/**
	 * Returns all the accounts.
	 *
	 * @return the accounts
	 */
	public static List<Account> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @return the range of accounts
	 */
	public static List<Account> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of accounts
	 */
	public static List<Account> findAll(
		int start, int end, OrderByComparator<Account> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of accounts
	 */
	public static List<Account> findAll(
		int start, int end, OrderByComparator<Account> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the accounts from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of accounts.
	 *
	 * @return the number of accounts
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AccountPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(AccountPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile AccountPersistence _persistence;

}