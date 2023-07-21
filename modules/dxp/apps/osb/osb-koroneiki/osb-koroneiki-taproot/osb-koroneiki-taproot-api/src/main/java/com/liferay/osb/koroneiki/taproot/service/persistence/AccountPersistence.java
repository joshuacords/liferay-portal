/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchAccountException;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the account service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountUtil
 * @generated
 */
@ProviderType
public interface AccountPersistence extends BasePersistence<Account> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountUtil} to access the account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the accounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching accounts
	 */
	public java.util.List<Account> findByUuid(String uuid);

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
	public java.util.List<Account> findByUuid(String uuid, int start, int end);

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
	public java.util.List<Account> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator);

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
	public java.util.List<Account> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public Account findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Account>
				orderByComparator)
		throws NoSuchAccountException;

	/**
	 * Returns the first account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account, or <code>null</code> if a matching account could not be found
	 */
	public Account fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator);

	/**
	 * Returns the last account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public Account findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Account>
				orderByComparator)
		throws NoSuchAccountException;

	/**
	 * Returns the last account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account, or <code>null</code> if a matching account could not be found
	 */
	public Account fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator);

	/**
	 * Returns the accounts before and after the current account in the ordered set where uuid = &#63;.
	 *
	 * @param accountId the primary key of the current account
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account
	 * @throws NoSuchAccountException if a account with the primary key could not be found
	 */
	public Account[] findByUuid_PrevAndNext(
			long accountId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Account>
				orderByComparator)
		throws NoSuchAccountException;

	/**
	 * Returns all the accounts that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching accounts that the user has permission to view
	 */
	public java.util.List<Account> filterFindByUuid(String uuid);

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
	public java.util.List<Account> filterFindByUuid(
		String uuid, int start, int end);

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
	public java.util.List<Account> filterFindByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator);

	/**
	 * Returns the accounts before and after the current account in the ordered set of accounts that the user has permission to view where uuid = &#63;.
	 *
	 * @param accountId the primary key of the current account
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account
	 * @throws NoSuchAccountException if a account with the primary key could not be found
	 */
	public Account[] filterFindByUuid_PrevAndNext(
			long accountId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Account>
				orderByComparator)
		throws NoSuchAccountException;

	/**
	 * Removes all the accounts where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of accounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching accounts
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the number of accounts that the user has permission to view where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching accounts that the user has permission to view
	 */
	public int filterCountByUuid(String uuid);

	/**
	 * Returns all the accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching accounts
	 */
	public java.util.List<Account> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<Account> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<Account> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator);

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
	public java.util.List<Account> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public Account findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Account>
				orderByComparator)
		throws NoSuchAccountException;

	/**
	 * Returns the first account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account, or <code>null</code> if a matching account could not be found
	 */
	public Account fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator);

	/**
	 * Returns the last account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public Account findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Account>
				orderByComparator)
		throws NoSuchAccountException;

	/**
	 * Returns the last account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account, or <code>null</code> if a matching account could not be found
	 */
	public Account fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator);

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
	public Account[] findByUuid_C_PrevAndNext(
			long accountId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Account>
				orderByComparator)
		throws NoSuchAccountException;

	/**
	 * Returns all the accounts that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching accounts that the user has permission to view
	 */
	public java.util.List<Account> filterFindByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<Account> filterFindByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<Account> filterFindByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator);

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
	public Account[] filterFindByUuid_C_PrevAndNext(
			long accountId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Account>
				orderByComparator)
		throws NoSuchAccountException;

	/**
	 * Removes all the accounts where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching accounts
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of accounts that the user has permission to view where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching accounts that the user has permission to view
	 */
	public int filterCountByUuid_C(String uuid, long companyId);

	/**
	 * Returns the account where accountKey = &#63; or throws a <code>NoSuchAccountException</code> if it could not be found.
	 *
	 * @param accountKey the account key
	 * @return the matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public Account findByAccountKey(String accountKey)
		throws NoSuchAccountException;

	/**
	 * Returns the account where accountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountKey the account key
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 */
	public Account fetchByAccountKey(String accountKey);

	/**
	 * Returns the account where accountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountKey the account key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 */
	public Account fetchByAccountKey(String accountKey, boolean useFinderCache);

	/**
	 * Removes the account where accountKey = &#63; from the database.
	 *
	 * @param accountKey the account key
	 * @return the account that was removed
	 */
	public Account removeByAccountKey(String accountKey)
		throws NoSuchAccountException;

	/**
	 * Returns the number of accounts where accountKey = &#63;.
	 *
	 * @param accountKey the account key
	 * @return the number of matching accounts
	 */
	public int countByAccountKey(String accountKey);

	/**
	 * Returns all the accounts where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @return the matching accounts
	 */
	public java.util.List<Account> findByParentAccountId(long parentAccountId);

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
	public java.util.List<Account> findByParentAccountId(
		long parentAccountId, int start, int end);

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
	public java.util.List<Account> findByParentAccountId(
		long parentAccountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator);

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
	public java.util.List<Account> findByParentAccountId(
		long parentAccountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first account in the ordered set where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public Account findByParentAccountId_First(
			long parentAccountId,
			com.liferay.portal.kernel.util.OrderByComparator<Account>
				orderByComparator)
		throws NoSuchAccountException;

	/**
	 * Returns the first account in the ordered set where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account, or <code>null</code> if a matching account could not be found
	 */
	public Account fetchByParentAccountId_First(
		long parentAccountId,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator);

	/**
	 * Returns the last account in the ordered set where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public Account findByParentAccountId_Last(
			long parentAccountId,
			com.liferay.portal.kernel.util.OrderByComparator<Account>
				orderByComparator)
		throws NoSuchAccountException;

	/**
	 * Returns the last account in the ordered set where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account, or <code>null</code> if a matching account could not be found
	 */
	public Account fetchByParentAccountId_Last(
		long parentAccountId,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator);

	/**
	 * Returns the accounts before and after the current account in the ordered set where parentAccountId = &#63;.
	 *
	 * @param accountId the primary key of the current account
	 * @param parentAccountId the parent account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account
	 * @throws NoSuchAccountException if a account with the primary key could not be found
	 */
	public Account[] findByParentAccountId_PrevAndNext(
			long accountId, long parentAccountId,
			com.liferay.portal.kernel.util.OrderByComparator<Account>
				orderByComparator)
		throws NoSuchAccountException;

	/**
	 * Returns all the accounts that the user has permission to view where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @return the matching accounts that the user has permission to view
	 */
	public java.util.List<Account> filterFindByParentAccountId(
		long parentAccountId);

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
	public java.util.List<Account> filterFindByParentAccountId(
		long parentAccountId, int start, int end);

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
	public java.util.List<Account> filterFindByParentAccountId(
		long parentAccountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator);

	/**
	 * Returns the accounts before and after the current account in the ordered set of accounts that the user has permission to view where parentAccountId = &#63;.
	 *
	 * @param accountId the primary key of the current account
	 * @param parentAccountId the parent account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account
	 * @throws NoSuchAccountException if a account with the primary key could not be found
	 */
	public Account[] filterFindByParentAccountId_PrevAndNext(
			long accountId, long parentAccountId,
			com.liferay.portal.kernel.util.OrderByComparator<Account>
				orderByComparator)
		throws NoSuchAccountException;

	/**
	 * Removes all the accounts where parentAccountId = &#63; from the database.
	 *
	 * @param parentAccountId the parent account ID
	 */
	public void removeByParentAccountId(long parentAccountId);

	/**
	 * Returns the number of accounts where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @return the number of matching accounts
	 */
	public int countByParentAccountId(long parentAccountId);

	/**
	 * Returns the number of accounts that the user has permission to view where parentAccountId = &#63;.
	 *
	 * @param parentAccountId the parent account ID
	 * @return the number of matching accounts that the user has permission to view
	 */
	public int filterCountByParentAccountId(long parentAccountId);

	/**
	 * Returns the account where name = &#63; or throws a <code>NoSuchAccountException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public Account findByName(String name) throws NoSuchAccountException;

	/**
	 * Returns the account where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 */
	public Account fetchByName(String name);

	/**
	 * Returns the account where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 */
	public Account fetchByName(String name, boolean useFinderCache);

	/**
	 * Removes the account where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the account that was removed
	 */
	public Account removeByName(String name) throws NoSuchAccountException;

	/**
	 * Returns the number of accounts where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching accounts
	 */
	public int countByName(String name);

	/**
	 * Returns the account where code = &#63; or throws a <code>NoSuchAccountException</code> if it could not be found.
	 *
	 * @param code the code
	 * @return the matching account
	 * @throws NoSuchAccountException if a matching account could not be found
	 */
	public Account findByCode(String code) throws NoSuchAccountException;

	/**
	 * Returns the account where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param code the code
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 */
	public Account fetchByCode(String code);

	/**
	 * Returns the account where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param code the code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 */
	public Account fetchByCode(String code, boolean useFinderCache);

	/**
	 * Removes the account where code = &#63; from the database.
	 *
	 * @param code the code
	 * @return the account that was removed
	 */
	public Account removeByCode(String code) throws NoSuchAccountException;

	/**
	 * Returns the number of accounts where code = &#63;.
	 *
	 * @param code the code
	 * @return the number of matching accounts
	 */
	public int countByCode(String code);

	/**
	 * Caches the account in the entity cache if it is enabled.
	 *
	 * @param account the account
	 */
	public void cacheResult(Account account);

	/**
	 * Caches the accounts in the entity cache if it is enabled.
	 *
	 * @param accounts the accounts
	 */
	public void cacheResult(java.util.List<Account> accounts);

	/**
	 * Creates a new account with the primary key. Does not add the account to the database.
	 *
	 * @param accountId the primary key for the new account
	 * @return the new account
	 */
	public Account create(long accountId);

	/**
	 * Removes the account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountId the primary key of the account
	 * @return the account that was removed
	 * @throws NoSuchAccountException if a account with the primary key could not be found
	 */
	public Account remove(long accountId) throws NoSuchAccountException;

	public Account updateImpl(Account account);

	/**
	 * Returns the account with the primary key or throws a <code>NoSuchAccountException</code> if it could not be found.
	 *
	 * @param accountId the primary key of the account
	 * @return the account
	 * @throws NoSuchAccountException if a account with the primary key could not be found
	 */
	public Account findByPrimaryKey(long accountId)
		throws NoSuchAccountException;

	/**
	 * Returns the account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountId the primary key of the account
	 * @return the account, or <code>null</code> if a account with the primary key could not be found
	 */
	public Account fetchByPrimaryKey(long accountId);

	/**
	 * Returns all the accounts.
	 *
	 * @return the accounts
	 */
	public java.util.List<Account> findAll();

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
	public java.util.List<Account> findAll(int start, int end);

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
	public java.util.List<Account> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator);

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
	public java.util.List<Account> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Account>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the accounts from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of accounts.
	 *
	 * @return the number of accounts
	 */
	public int countAll();

}