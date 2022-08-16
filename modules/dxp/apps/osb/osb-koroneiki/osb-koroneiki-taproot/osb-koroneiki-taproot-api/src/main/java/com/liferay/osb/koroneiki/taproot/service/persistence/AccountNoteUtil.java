/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the account note service. This utility wraps <code>com.liferay.osb.koroneiki.taproot.service.persistence.impl.AccountNotePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountNotePersistence
 * @generated
 */
public class AccountNoteUtil {

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
	public static void clearCache(AccountNote accountNote) {
		getPersistence().clearCache(accountNote);
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
	public static Map<Serializable, AccountNote> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AccountNote> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountNote> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountNote> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccountNote update(AccountNote accountNote) {
		return getPersistence().update(accountNote);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccountNote update(
		AccountNote accountNote, ServiceContext serviceContext) {

		return getPersistence().update(accountNote, serviceContext);
	}

	/**
	 * Returns all the account notes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching account notes
	 */
	public static List<AccountNote> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the account notes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @return the range of matching account notes
	 */
	public static List<AccountNote> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the account notes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account notes
	 */
	public static List<AccountNote> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account notes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account notes
	 */
	public static List<AccountNote> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AccountNote> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first account note in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public static AccountNote findByUuid_First(
			String uuid, OrderByComparator<AccountNote> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first account note in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public static AccountNote fetchByUuid_First(
		String uuid, OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last account note in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public static AccountNote findByUuid_Last(
			String uuid, OrderByComparator<AccountNote> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last account note in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public static AccountNote fetchByUuid_Last(
		String uuid, OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the account notes before and after the current account note in the ordered set where uuid = &#63;.
	 *
	 * @param accountNoteId the primary key of the current account note
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account note
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	public static AccountNote[] findByUuid_PrevAndNext(
			long accountNoteId, String uuid,
			OrderByComparator<AccountNote> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().findByUuid_PrevAndNext(
			accountNoteId, uuid, orderByComparator);
	}

	/**
	 * Removes all the account notes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of account notes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching account notes
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the account notes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching account notes
	 */
	public static List<AccountNote> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the account notes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @return the range of matching account notes
	 */
	public static List<AccountNote> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the account notes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account notes
	 */
	public static List<AccountNote> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account notes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account notes
	 */
	public static List<AccountNote> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AccountNote> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first account note in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public static AccountNote findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<AccountNote> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first account note in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public static AccountNote fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last account note in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public static AccountNote findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<AccountNote> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last account note in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public static AccountNote fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the account notes before and after the current account note in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param accountNoteId the primary key of the current account note
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account note
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	public static AccountNote[] findByUuid_C_PrevAndNext(
			long accountNoteId, String uuid, long companyId,
			OrderByComparator<AccountNote> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().findByUuid_C_PrevAndNext(
			accountNoteId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the account notes where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of account notes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching account notes
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the account note where accountNoteKey = &#63; or throws a <code>NoSuchAccountNoteException</code> if it could not be found.
	 *
	 * @param accountNoteKey the account note key
	 * @return the matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public static AccountNote findByAccountNoteKey(String accountNoteKey)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().findByAccountNoteKey(accountNoteKey);
	}

	/**
	 * Returns the account note where accountNoteKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountNoteKey the account note key
	 * @return the matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public static AccountNote fetchByAccountNoteKey(String accountNoteKey) {
		return getPersistence().fetchByAccountNoteKey(accountNoteKey);
	}

	/**
	 * Returns the account note where accountNoteKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountNoteKey the account note key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public static AccountNote fetchByAccountNoteKey(
		String accountNoteKey, boolean useFinderCache) {

		return getPersistence().fetchByAccountNoteKey(
			accountNoteKey, useFinderCache);
	}

	/**
	 * Removes the account note where accountNoteKey = &#63; from the database.
	 *
	 * @param accountNoteKey the account note key
	 * @return the account note that was removed
	 */
	public static AccountNote removeByAccountNoteKey(String accountNoteKey)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().removeByAccountNoteKey(accountNoteKey);
	}

	/**
	 * Returns the number of account notes where accountNoteKey = &#63;.
	 *
	 * @param accountNoteKey the account note key
	 * @return the number of matching account notes
	 */
	public static int countByAccountNoteKey(String accountNoteKey) {
		return getPersistence().countByAccountNoteKey(accountNoteKey);
	}

	/**
	 * Returns all the account notes where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching account notes
	 */
	public static List<AccountNote> findByAccountId(long accountId) {
		return getPersistence().findByAccountId(accountId);
	}

	/**
	 * Returns a range of all the account notes where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @return the range of matching account notes
	 */
	public static List<AccountNote> findByAccountId(
		long accountId, int start, int end) {

		return getPersistence().findByAccountId(accountId, start, end);
	}

	/**
	 * Returns an ordered range of all the account notes where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account notes
	 */
	public static List<AccountNote> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().findByAccountId(
			accountId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account notes where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account notes
	 */
	public static List<AccountNote> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<AccountNote> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAccountId(
			accountId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public static AccountNote findByAccountId_First(
			long accountId, OrderByComparator<AccountNote> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().findByAccountId_First(
			accountId, orderByComparator);
	}

	/**
	 * Returns the first account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public static AccountNote fetchByAccountId_First(
		long accountId, OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().fetchByAccountId_First(
			accountId, orderByComparator);
	}

	/**
	 * Returns the last account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public static AccountNote findByAccountId_Last(
			long accountId, OrderByComparator<AccountNote> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().findByAccountId_Last(
			accountId, orderByComparator);
	}

	/**
	 * Returns the last account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public static AccountNote fetchByAccountId_Last(
		long accountId, OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().fetchByAccountId_Last(
			accountId, orderByComparator);
	}

	/**
	 * Returns the account notes before and after the current account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountNoteId the primary key of the current account note
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account note
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	public static AccountNote[] findByAccountId_PrevAndNext(
			long accountNoteId, long accountId,
			OrderByComparator<AccountNote> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().findByAccountId_PrevAndNext(
			accountNoteId, accountId, orderByComparator);
	}

	/**
	 * Removes all the account notes where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	public static void removeByAccountId(long accountId) {
		getPersistence().removeByAccountId(accountId);
	}

	/**
	 * Returns the number of account notes where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching account notes
	 */
	public static int countByAccountId(long accountId) {
		return getPersistence().countByAccountId(accountId);
	}

	/**
	 * Returns all the account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @return the matching account notes
	 */
	public static List<AccountNote> findByAI_T_P_S(
		long accountId, String type, int priority, String status) {

		return getPersistence().findByAI_T_P_S(
			accountId, type, priority, status);
	}

	/**
	 * Returns a range of all the account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @return the range of matching account notes
	 */
	public static List<AccountNote> findByAI_T_P_S(
		long accountId, String type, int priority, String status, int start,
		int end) {

		return getPersistence().findByAI_T_P_S(
			accountId, type, priority, status, start, end);
	}

	/**
	 * Returns an ordered range of all the account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account notes
	 */
	public static List<AccountNote> findByAI_T_P_S(
		long accountId, String type, int priority, String status, int start,
		int end, OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().findByAI_T_P_S(
			accountId, type, priority, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account notes
	 */
	public static List<AccountNote> findByAI_T_P_S(
		long accountId, String type, int priority, String status, int start,
		int end, OrderByComparator<AccountNote> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAI_T_P_S(
			accountId, type, priority, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first account note in the ordered set where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public static AccountNote findByAI_T_P_S_First(
			long accountId, String type, int priority, String status,
			OrderByComparator<AccountNote> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().findByAI_T_P_S_First(
			accountId, type, priority, status, orderByComparator);
	}

	/**
	 * Returns the first account note in the ordered set where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public static AccountNote fetchByAI_T_P_S_First(
		long accountId, String type, int priority, String status,
		OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().fetchByAI_T_P_S_First(
			accountId, type, priority, status, orderByComparator);
	}

	/**
	 * Returns the last account note in the ordered set where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public static AccountNote findByAI_T_P_S_Last(
			long accountId, String type, int priority, String status,
			OrderByComparator<AccountNote> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().findByAI_T_P_S_Last(
			accountId, type, priority, status, orderByComparator);
	}

	/**
	 * Returns the last account note in the ordered set where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public static AccountNote fetchByAI_T_P_S_Last(
		long accountId, String type, int priority, String status,
		OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().fetchByAI_T_P_S_Last(
			accountId, type, priority, status, orderByComparator);
	}

	/**
	 * Returns the account notes before and after the current account note in the ordered set where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountNoteId the primary key of the current account note
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account note
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	public static AccountNote[] findByAI_T_P_S_PrevAndNext(
			long accountNoteId, long accountId, String type, int priority,
			String status, OrderByComparator<AccountNote> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().findByAI_T_P_S_PrevAndNext(
			accountNoteId, accountId, type, priority, status,
			orderByComparator);
	}

	/**
	 * Returns all the account notes where accountId = &#63; and type = any &#63; and priority = any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param types the types
	 * @param priorities the priorities
	 * @param statuses the statuses
	 * @return the matching account notes
	 */
	public static List<AccountNote> findByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses) {

		return getPersistence().findByAI_T_P_S(
			accountId, types, priorities, statuses);
	}

	/**
	 * Returns a range of all the account notes where accountId = &#63; and type = any &#63; and priority = any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param types the types
	 * @param priorities the priorities
	 * @param statuses the statuses
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @return the range of matching account notes
	 */
	public static List<AccountNote> findByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses,
		int start, int end) {

		return getPersistence().findByAI_T_P_S(
			accountId, types, priorities, statuses, start, end);
	}

	/**
	 * Returns an ordered range of all the account notes where accountId = &#63; and type = any &#63; and priority = any &#63; and status = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param types the types
	 * @param priorities the priorities
	 * @param statuses the statuses
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account notes
	 */
	public static List<AccountNote> findByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses,
		int start, int end, OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().findByAI_T_P_S(
			accountId, types, priorities, statuses, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param types the types
	 * @param priorities the priorities
	 * @param statuses the statuses
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account notes
	 */
	public static List<AccountNote> findByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses,
		int start, int end, OrderByComparator<AccountNote> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAI_T_P_S(
			accountId, types, priorities, statuses, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63; from the database.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 */
	public static void removeByAI_T_P_S(
		long accountId, String type, int priority, String status) {

		getPersistence().removeByAI_T_P_S(accountId, type, priority, status);
	}

	/**
	 * Returns the number of account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @return the number of matching account notes
	 */
	public static int countByAI_T_P_S(
		long accountId, String type, int priority, String status) {

		return getPersistence().countByAI_T_P_S(
			accountId, type, priority, status);
	}

	/**
	 * Returns the number of account notes where accountId = &#63; and type = any &#63; and priority = any &#63; and status = any &#63;.
	 *
	 * @param accountId the account ID
	 * @param types the types
	 * @param priorities the priorities
	 * @param statuses the statuses
	 * @return the number of matching account notes
	 */
	public static int countByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses) {

		return getPersistence().countByAI_T_P_S(
			accountId, types, priorities, statuses);
	}

	/**
	 * Caches the account note in the entity cache if it is enabled.
	 *
	 * @param accountNote the account note
	 */
	public static void cacheResult(AccountNote accountNote) {
		getPersistence().cacheResult(accountNote);
	}

	/**
	 * Caches the account notes in the entity cache if it is enabled.
	 *
	 * @param accountNotes the account notes
	 */
	public static void cacheResult(List<AccountNote> accountNotes) {
		getPersistence().cacheResult(accountNotes);
	}

	/**
	 * Creates a new account note with the primary key. Does not add the account note to the database.
	 *
	 * @param accountNoteId the primary key for the new account note
	 * @return the new account note
	 */
	public static AccountNote create(long accountNoteId) {
		return getPersistence().create(accountNoteId);
	}

	/**
	 * Removes the account note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountNoteId the primary key of the account note
	 * @return the account note that was removed
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	public static AccountNote remove(long accountNoteId)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().remove(accountNoteId);
	}

	public static AccountNote updateImpl(AccountNote accountNote) {
		return getPersistence().updateImpl(accountNote);
	}

	/**
	 * Returns the account note with the primary key or throws a <code>NoSuchAccountNoteException</code> if it could not be found.
	 *
	 * @param accountNoteId the primary key of the account note
	 * @return the account note
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	public static AccountNote findByPrimaryKey(long accountNoteId)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountNoteException {

		return getPersistence().findByPrimaryKey(accountNoteId);
	}

	/**
	 * Returns the account note with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountNoteId the primary key of the account note
	 * @return the account note, or <code>null</code> if a account note with the primary key could not be found
	 */
	public static AccountNote fetchByPrimaryKey(long accountNoteId) {
		return getPersistence().fetchByPrimaryKey(accountNoteId);
	}

	/**
	 * Returns all the account notes.
	 *
	 * @return the account notes
	 */
	public static List<AccountNote> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the account notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @return the range of account notes
	 */
	public static List<AccountNote> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the account notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account notes
	 */
	public static List<AccountNote> findAll(
		int start, int end, OrderByComparator<AccountNote> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of account notes
	 */
	public static List<AccountNote> findAll(
		int start, int end, OrderByComparator<AccountNote> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the account notes from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of account notes.
	 *
	 * @return the number of account notes
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AccountNotePersistence getPersistence() {
		return _persistence;
	}

	private static volatile AccountNotePersistence _persistence;

}