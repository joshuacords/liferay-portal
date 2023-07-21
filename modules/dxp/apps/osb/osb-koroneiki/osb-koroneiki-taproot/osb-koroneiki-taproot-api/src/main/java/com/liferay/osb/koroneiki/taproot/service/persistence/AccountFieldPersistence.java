/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.exception.NoSuchAccountFieldException;
import com.liferay.osb.koroneiki.taproot.model.AccountField;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the account field service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountFieldUtil
 * @generated
 */
@ProviderType
public interface AccountFieldPersistence extends BasePersistence<AccountField> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountFieldUtil} to access the account field persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the account fields where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching account fields
	 */
	public java.util.List<AccountField> findByAccountId(long accountId);

	/**
	 * Returns a range of all the account fields where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountFieldModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of account fields
	 * @param end the upper bound of the range of account fields (not inclusive)
	 * @return the range of matching account fields
	 */
	public java.util.List<AccountField> findByAccountId(
		long accountId, int start, int end);

	/**
	 * Returns an ordered range of all the account fields where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountFieldModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of account fields
	 * @param end the upper bound of the range of account fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account fields
	 */
	public java.util.List<AccountField> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountField>
			orderByComparator);

	/**
	 * Returns an ordered range of all the account fields where accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountFieldModelImpl</code>.
	 * </p>
	 *
	 * @param accountId the account ID
	 * @param start the lower bound of the range of account fields
	 * @param end the upper bound of the range of account fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account fields
	 */
	public java.util.List<AccountField> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountField>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account field
	 * @throws NoSuchAccountFieldException if a matching account field could not be found
	 */
	public AccountField findByAccountId_First(
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountField>
				orderByComparator)
		throws NoSuchAccountFieldException;

	/**
	 * Returns the first account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account field, or <code>null</code> if a matching account field could not be found
	 */
	public AccountField fetchByAccountId_First(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountField>
			orderByComparator);

	/**
	 * Returns the last account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account field
	 * @throws NoSuchAccountFieldException if a matching account field could not be found
	 */
	public AccountField findByAccountId_Last(
			long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountField>
				orderByComparator)
		throws NoSuchAccountFieldException;

	/**
	 * Returns the last account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account field, or <code>null</code> if a matching account field could not be found
	 */
	public AccountField fetchByAccountId_Last(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountField>
			orderByComparator);

	/**
	 * Returns the account fields before and after the current account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountFieldId the primary key of the current account field
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account field
	 * @throws NoSuchAccountFieldException if a account field with the primary key could not be found
	 */
	public AccountField[] findByAccountId_PrevAndNext(
			long accountFieldId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountField>
				orderByComparator)
		throws NoSuchAccountFieldException;

	/**
	 * Removes all the account fields where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	public void removeByAccountId(long accountId);

	/**
	 * Returns the number of account fields where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching account fields
	 */
	public int countByAccountId(long accountId);

	/**
	 * Caches the account field in the entity cache if it is enabled.
	 *
	 * @param accountField the account field
	 */
	public void cacheResult(AccountField accountField);

	/**
	 * Caches the account fields in the entity cache if it is enabled.
	 *
	 * @param accountFields the account fields
	 */
	public void cacheResult(java.util.List<AccountField> accountFields);

	/**
	 * Creates a new account field with the primary key. Does not add the account field to the database.
	 *
	 * @param accountFieldId the primary key for the new account field
	 * @return the new account field
	 */
	public AccountField create(long accountFieldId);

	/**
	 * Removes the account field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountFieldId the primary key of the account field
	 * @return the account field that was removed
	 * @throws NoSuchAccountFieldException if a account field with the primary key could not be found
	 */
	public AccountField remove(long accountFieldId)
		throws NoSuchAccountFieldException;

	public AccountField updateImpl(AccountField accountField);

	/**
	 * Returns the account field with the primary key or throws a <code>NoSuchAccountFieldException</code> if it could not be found.
	 *
	 * @param accountFieldId the primary key of the account field
	 * @return the account field
	 * @throws NoSuchAccountFieldException if a account field with the primary key could not be found
	 */
	public AccountField findByPrimaryKey(long accountFieldId)
		throws NoSuchAccountFieldException;

	/**
	 * Returns the account field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountFieldId the primary key of the account field
	 * @return the account field, or <code>null</code> if a account field with the primary key could not be found
	 */
	public AccountField fetchByPrimaryKey(long accountFieldId);

	/**
	 * Returns all the account fields.
	 *
	 * @return the account fields
	 */
	public java.util.List<AccountField> findAll();

	/**
	 * Returns a range of all the account fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account fields
	 * @param end the upper bound of the range of account fields (not inclusive)
	 * @return the range of account fields
	 */
	public java.util.List<AccountField> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the account fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account fields
	 * @param end the upper bound of the range of account fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account fields
	 */
	public java.util.List<AccountField> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountField>
			orderByComparator);

	/**
	 * Returns an ordered range of all the account fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account fields
	 * @param end the upper bound of the range of account fields (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of account fields
	 */
	public java.util.List<AccountField> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountField>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the account fields from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of account fields.
	 *
	 * @return the number of account fields
	 */
	public int countAll();

}