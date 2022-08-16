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

import com.liferay.osb.koroneiki.taproot.exception.NoSuchAccountNoteException;
import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the account note service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountNoteUtil
 * @generated
 */
@ProviderType
public interface AccountNotePersistence extends BasePersistence<AccountNote> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountNoteUtil} to access the account note persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the account notes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching account notes
	 */
	public java.util.List<AccountNote> findByUuid(String uuid);

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
	public java.util.List<AccountNote> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<AccountNote> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator);

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
	public java.util.List<AccountNote> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first account note in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public AccountNote findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
				orderByComparator)
		throws NoSuchAccountNoteException;

	/**
	 * Returns the first account note in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public AccountNote fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator);

	/**
	 * Returns the last account note in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public AccountNote findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
				orderByComparator)
		throws NoSuchAccountNoteException;

	/**
	 * Returns the last account note in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public AccountNote fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator);

	/**
	 * Returns the account notes before and after the current account note in the ordered set where uuid = &#63;.
	 *
	 * @param accountNoteId the primary key of the current account note
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account note
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	public AccountNote[] findByUuid_PrevAndNext(
			long accountNoteId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
				orderByComparator)
		throws NoSuchAccountNoteException;

	/**
	 * Removes all the account notes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of account notes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching account notes
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the account notes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching account notes
	 */
	public java.util.List<AccountNote> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<AccountNote> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<AccountNote> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator);

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
	public java.util.List<AccountNote> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first account note in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public AccountNote findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
				orderByComparator)
		throws NoSuchAccountNoteException;

	/**
	 * Returns the first account note in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public AccountNote fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator);

	/**
	 * Returns the last account note in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public AccountNote findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
				orderByComparator)
		throws NoSuchAccountNoteException;

	/**
	 * Returns the last account note in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public AccountNote fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator);

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
	public AccountNote[] findByUuid_C_PrevAndNext(
			long accountNoteId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
				orderByComparator)
		throws NoSuchAccountNoteException;

	/**
	 * Removes all the account notes where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of account notes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching account notes
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the account note where accountNoteKey = &#63; or throws a <code>NoSuchAccountNoteException</code> if it could not be found.
	 *
	 * @param accountNoteKey the account note key
	 * @return the matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public AccountNote findByAccountNoteKey(String accountNoteKey)
		throws NoSuchAccountNoteException;

	/**
	 * Returns the account note where accountNoteKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountNoteKey the account note key
	 * @return the matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public AccountNote fetchByAccountNoteKey(String accountNoteKey);

	/**
	 * Returns the account note where accountNoteKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountNoteKey the account note key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public AccountNote fetchByAccountNoteKey(
		String accountNoteKey, boolean useFinderCache);

	/**
	 * Removes the account note where accountNoteKey = &#63; from the database.
	 *
	 * @param accountNoteKey the account note key
	 * @return the account note that was removed
	 */
	public AccountNote removeByAccountNoteKey(String accountNoteKey)
		throws NoSuchAccountNoteException;

	/**
	 * Returns the number of account notes where accountNoteKey = &#63;.
	 *
	 * @param accountNoteKey the account note key
	 * @return the number of matching account notes
	 */
	public int countByAccountNoteKey(String accountNoteKey);

	/**
	 * Returns all the account notes where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching account notes
	 */
	public java.util.List<AccountNote> findByAccountId(long accountId);

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
	public java.util.List<AccountNote> findByAccountId(
		long accountId, int start, int end);

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
	public java.util.List<AccountNote> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator);

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
	public java.util.List<AccountNote> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public AccountNote findByAccountId_First(
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
				orderByComparator)
		throws NoSuchAccountNoteException;

	/**
	 * Returns the first account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public AccountNote fetchByAccountId_First(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator);

	/**
	 * Returns the last account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note
	 * @throws NoSuchAccountNoteException if a matching account note could not be found
	 */
	public AccountNote findByAccountId_Last(
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
				orderByComparator)
		throws NoSuchAccountNoteException;

	/**
	 * Returns the last account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public AccountNote fetchByAccountId_Last(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator);

	/**
	 * Returns the account notes before and after the current account note in the ordered set where accountId = &#63;.
	 *
	 * @param accountNoteId the primary key of the current account note
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account note
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	public AccountNote[] findByAccountId_PrevAndNext(
			long accountNoteId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
				orderByComparator)
		throws NoSuchAccountNoteException;

	/**
	 * Removes all the account notes where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	public void removeByAccountId(long accountId);

	/**
	 * Returns the number of account notes where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching account notes
	 */
	public int countByAccountId(long accountId);

	/**
	 * Returns all the account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @return the matching account notes
	 */
	public java.util.List<AccountNote> findByAI_T_P_S(
		long accountId, String type, int priority, String status);

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
	public java.util.List<AccountNote> findByAI_T_P_S(
		long accountId, String type, int priority, String status, int start,
		int end);

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
	public java.util.List<AccountNote> findByAI_T_P_S(
		long accountId, String type, int priority, String status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator);

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
	public java.util.List<AccountNote> findByAI_T_P_S(
		long accountId, String type, int priority, String status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator,
		boolean useFinderCache);

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
	public AccountNote findByAI_T_P_S_First(
			long accountId, String type, int priority, String status,
			com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
				orderByComparator)
		throws NoSuchAccountNoteException;

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
	public AccountNote fetchByAI_T_P_S_First(
		long accountId, String type, int priority, String status,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator);

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
	public AccountNote findByAI_T_P_S_Last(
			long accountId, String type, int priority, String status,
			com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
				orderByComparator)
		throws NoSuchAccountNoteException;

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
	public AccountNote fetchByAI_T_P_S_Last(
		long accountId, String type, int priority, String status,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator);

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
	public AccountNote[] findByAI_T_P_S_PrevAndNext(
			long accountNoteId, long accountId, String type, int priority,
			String status,
			com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
				orderByComparator)
		throws NoSuchAccountNoteException;

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
	public java.util.List<AccountNote> findByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses);

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
	public java.util.List<AccountNote> findByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses,
		int start, int end);

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
	public java.util.List<AccountNote> findByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator);

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
	public java.util.List<AccountNote> findByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63; from the database.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 */
	public void removeByAI_T_P_S(
		long accountId, String type, int priority, String status);

	/**
	 * Returns the number of account notes where accountId = &#63; and type = &#63; and priority = &#63; and status = &#63;.
	 *
	 * @param accountId the account ID
	 * @param type the type
	 * @param priority the priority
	 * @param status the status
	 * @return the number of matching account notes
	 */
	public int countByAI_T_P_S(
		long accountId, String type, int priority, String status);

	/**
	 * Returns the number of account notes where accountId = &#63; and type = any &#63; and priority = any &#63; and status = any &#63;.
	 *
	 * @param accountId the account ID
	 * @param types the types
	 * @param priorities the priorities
	 * @param statuses the statuses
	 * @return the number of matching account notes
	 */
	public int countByAI_T_P_S(
		long accountId, String[] types, int[] priorities, String[] statuses);

	/**
	 * Caches the account note in the entity cache if it is enabled.
	 *
	 * @param accountNote the account note
	 */
	public void cacheResult(AccountNote accountNote);

	/**
	 * Caches the account notes in the entity cache if it is enabled.
	 *
	 * @param accountNotes the account notes
	 */
	public void cacheResult(java.util.List<AccountNote> accountNotes);

	/**
	 * Creates a new account note with the primary key. Does not add the account note to the database.
	 *
	 * @param accountNoteId the primary key for the new account note
	 * @return the new account note
	 */
	public AccountNote create(long accountNoteId);

	/**
	 * Removes the account note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountNoteId the primary key of the account note
	 * @return the account note that was removed
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	public AccountNote remove(long accountNoteId)
		throws NoSuchAccountNoteException;

	public AccountNote updateImpl(AccountNote accountNote);

	/**
	 * Returns the account note with the primary key or throws a <code>NoSuchAccountNoteException</code> if it could not be found.
	 *
	 * @param accountNoteId the primary key of the account note
	 * @return the account note
	 * @throws NoSuchAccountNoteException if a account note with the primary key could not be found
	 */
	public AccountNote findByPrimaryKey(long accountNoteId)
		throws NoSuchAccountNoteException;

	/**
	 * Returns the account note with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountNoteId the primary key of the account note
	 * @return the account note, or <code>null</code> if a account note with the primary key could not be found
	 */
	public AccountNote fetchByPrimaryKey(long accountNoteId);

	/**
	 * Returns all the account notes.
	 *
	 * @return the account notes
	 */
	public java.util.List<AccountNote> findAll();

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
	public java.util.List<AccountNote> findAll(int start, int end);

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
	public java.util.List<AccountNote> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator);

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
	public java.util.List<AccountNote> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountNote>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the account notes from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of account notes.
	 *
	 * @return the number of account notes
	 */
	public int countAll();

}