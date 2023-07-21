/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import com.liferay.osb.koroneiki.taproot.model.AccountField;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the account field service. This utility wraps <code>com.liferay.osb.koroneiki.taproot.service.persistence.impl.AccountFieldPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountFieldPersistence
 * @generated
 */
public class AccountFieldUtil {

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
	public static void clearCache(AccountField accountField) {
		getPersistence().clearCache(accountField);
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
	public static Map<Serializable, AccountField> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AccountField> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountField> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountField> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccountField> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccountField update(AccountField accountField) {
		return getPersistence().update(accountField);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccountField update(
		AccountField accountField, ServiceContext serviceContext) {

		return getPersistence().update(accountField, serviceContext);
	}

	/**
	 * Returns all the account fields where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the matching account fields
	 */
	public static List<AccountField> findByAccountId(long accountId) {
		return getPersistence().findByAccountId(accountId);
	}

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
	public static List<AccountField> findByAccountId(
		long accountId, int start, int end) {

		return getPersistence().findByAccountId(accountId, start, end);
	}

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
	public static List<AccountField> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<AccountField> orderByComparator) {

		return getPersistence().findByAccountId(
			accountId, start, end, orderByComparator);
	}

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
	public static List<AccountField> findByAccountId(
		long accountId, int start, int end,
		OrderByComparator<AccountField> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAccountId(
			accountId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account field
	 * @throws NoSuchAccountFieldException if a matching account field could not be found
	 */
	public static AccountField findByAccountId_First(
			long accountId, OrderByComparator<AccountField> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountFieldException {

		return getPersistence().findByAccountId_First(
			accountId, orderByComparator);
	}

	/**
	 * Returns the first account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account field, or <code>null</code> if a matching account field could not be found
	 */
	public static AccountField fetchByAccountId_First(
		long accountId, OrderByComparator<AccountField> orderByComparator) {

		return getPersistence().fetchByAccountId_First(
			accountId, orderByComparator);
	}

	/**
	 * Returns the last account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account field
	 * @throws NoSuchAccountFieldException if a matching account field could not be found
	 */
	public static AccountField findByAccountId_Last(
			long accountId, OrderByComparator<AccountField> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountFieldException {

		return getPersistence().findByAccountId_Last(
			accountId, orderByComparator);
	}

	/**
	 * Returns the last account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account field, or <code>null</code> if a matching account field could not be found
	 */
	public static AccountField fetchByAccountId_Last(
		long accountId, OrderByComparator<AccountField> orderByComparator) {

		return getPersistence().fetchByAccountId_Last(
			accountId, orderByComparator);
	}

	/**
	 * Returns the account fields before and after the current account field in the ordered set where accountId = &#63;.
	 *
	 * @param accountFieldId the primary key of the current account field
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account field
	 * @throws NoSuchAccountFieldException if a account field with the primary key could not be found
	 */
	public static AccountField[] findByAccountId_PrevAndNext(
			long accountFieldId, long accountId,
			OrderByComparator<AccountField> orderByComparator)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountFieldException {

		return getPersistence().findByAccountId_PrevAndNext(
			accountFieldId, accountId, orderByComparator);
	}

	/**
	 * Removes all the account fields where accountId = &#63; from the database.
	 *
	 * @param accountId the account ID
	 */
	public static void removeByAccountId(long accountId) {
		getPersistence().removeByAccountId(accountId);
	}

	/**
	 * Returns the number of account fields where accountId = &#63;.
	 *
	 * @param accountId the account ID
	 * @return the number of matching account fields
	 */
	public static int countByAccountId(long accountId) {
		return getPersistence().countByAccountId(accountId);
	}

	/**
	 * Caches the account field in the entity cache if it is enabled.
	 *
	 * @param accountField the account field
	 */
	public static void cacheResult(AccountField accountField) {
		getPersistence().cacheResult(accountField);
	}

	/**
	 * Caches the account fields in the entity cache if it is enabled.
	 *
	 * @param accountFields the account fields
	 */
	public static void cacheResult(List<AccountField> accountFields) {
		getPersistence().cacheResult(accountFields);
	}

	/**
	 * Creates a new account field with the primary key. Does not add the account field to the database.
	 *
	 * @param accountFieldId the primary key for the new account field
	 * @return the new account field
	 */
	public static AccountField create(long accountFieldId) {
		return getPersistence().create(accountFieldId);
	}

	/**
	 * Removes the account field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountFieldId the primary key of the account field
	 * @return the account field that was removed
	 * @throws NoSuchAccountFieldException if a account field with the primary key could not be found
	 */
	public static AccountField remove(long accountFieldId)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountFieldException {

		return getPersistence().remove(accountFieldId);
	}

	public static AccountField updateImpl(AccountField accountField) {
		return getPersistence().updateImpl(accountField);
	}

	/**
	 * Returns the account field with the primary key or throws a <code>NoSuchAccountFieldException</code> if it could not be found.
	 *
	 * @param accountFieldId the primary key of the account field
	 * @return the account field
	 * @throws NoSuchAccountFieldException if a account field with the primary key could not be found
	 */
	public static AccountField findByPrimaryKey(long accountFieldId)
		throws com.liferay.osb.koroneiki.taproot.exception.
			NoSuchAccountFieldException {

		return getPersistence().findByPrimaryKey(accountFieldId);
	}

	/**
	 * Returns the account field with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountFieldId the primary key of the account field
	 * @return the account field, or <code>null</code> if a account field with the primary key could not be found
	 */
	public static AccountField fetchByPrimaryKey(long accountFieldId) {
		return getPersistence().fetchByPrimaryKey(accountFieldId);
	}

	/**
	 * Returns all the account fields.
	 *
	 * @return the account fields
	 */
	public static List<AccountField> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<AccountField> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<AccountField> findAll(
		int start, int end, OrderByComparator<AccountField> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<AccountField> findAll(
		int start, int end, OrderByComparator<AccountField> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the account fields from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of account fields.
	 *
	 * @return the number of account fields
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AccountFieldPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(AccountFieldPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile AccountFieldPersistence _persistence;

}