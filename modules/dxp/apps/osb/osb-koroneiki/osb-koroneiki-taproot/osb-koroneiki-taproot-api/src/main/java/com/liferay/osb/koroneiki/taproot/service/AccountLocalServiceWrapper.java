/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountLocalService
 * @generated
 */
public class AccountLocalServiceWrapper
	implements AccountLocalService, ServiceWrapper<AccountLocalService> {

	public AccountLocalServiceWrapper(AccountLocalService accountLocalService) {
		_accountLocalService = accountLocalService;
	}

	/**
	 * Adds the account to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param account the account
	 * @return the account that was added
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account addAccount(
		com.liferay.osb.koroneiki.taproot.model.Account account) {

		return _accountLocalService.addAccount(account);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account addAccount(
			long userId, long parentAccountId, String name, String code,
			String description, long logoId, String contactEmailAddress,
			String profileEmailAddress, String phoneNumber, String faxNumber,
			String website, String tier, String region, String dataRegion,
			String language, boolean internal, String status,
			java.util.List<com.liferay.osb.koroneiki.taproot.model.AccountField>
				accountFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountLocalService.addAccount(
			userId, parentAccountId, name, code, description, logoId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website, tier, region, dataRegion, language, internal, status,
			accountFields);
	}

	/**
	 * Creates a new account with the primary key. Does not add the account to the database.
	 *
	 * @param accountId the primary key for the new account
	 * @return the new account
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account createAccount(
		long accountId) {

		return _accountLocalService.createAccount(accountId);
	}

	/**
	 * Deletes the account from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param account the account
	 * @return the account that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account deleteAccount(
			com.liferay.osb.koroneiki.taproot.model.Account account)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountLocalService.deleteAccount(account);
	}

	/**
	 * Deletes the account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountId the primary key of the account
	 * @return the account that was removed
	 * @throws PortalException if a account with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account deleteAccount(
			long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountLocalService.deleteAccount(accountId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _accountLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _accountLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _accountLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _accountLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _accountLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account fetchAccount(
		long accountId) {

		return _accountLocalService.fetchAccount(accountId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account fetchAccount(
		String accountKey) {

		return _accountLocalService.fetchAccount(accountKey);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account fetchAccountByCode(
		String code) {

		return _accountLocalService.fetchAccountByCode(code);
	}

	/**
	 * Returns the account with the matching UUID and company.
	 *
	 * @param uuid the account's UUID
	 * @param companyId the primary key of the company
	 * @return the matching account, or <code>null</code> if a matching account could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account
		fetchAccountByUuidAndCompanyId(String uuid, long companyId) {

		return _accountLocalService.fetchAccountByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns the account with the primary key.
	 *
	 * @param accountId the primary key of the account
	 * @return the account
	 * @throws PortalException if a account with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account getAccount(
			long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountLocalService.getAccount(accountId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account getAccount(
			String accountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountLocalService.getAccount(accountKey);
	}

	/**
	 * Returns the account with the matching UUID and company.
	 *
	 * @param uuid the account's UUID
	 * @param companyId the primary key of the company
	 * @return the matching account
	 * @throws PortalException if a matching account could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account
			getAccountByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountLocalService.getAccountByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of all the accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of accounts
	 * @param end the upper bound of the range of accounts (not inclusive)
	 * @return the range of accounts
	 */
	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Account>
		getAccounts(int start, int end) {

		return _accountLocalService.getAccounts(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Account>
		getAccounts(long parentAccountId, int start, int end) {

		return _accountLocalService.getAccounts(parentAccountId, start, end);
	}

	/**
	 * Returns the number of accounts.
	 *
	 * @return the number of accounts
	 */
	@Override
	public int getAccountsCount() {
		return _accountLocalService.getAccountsCount();
	}

	@Override
	public int getAccountsCount(long parentAccountId) {
		return _accountLocalService.getAccountsCount(parentAccountId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _accountLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Account>
		getContactAccounts(long contactId, int start, int end) {

		return _accountLocalService.getContactAccounts(contactId, start, end);
	}

	@Override
	public int getContactAccountsCount(long contactId) {
		return _accountLocalService.getContactAccountsCount(contactId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _accountLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _accountLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Account>
		getTeamAccounts(long teamId, int start, int end) {

		return _accountLocalService.getTeamAccounts(teamId, start, end);
	}

	@Override
	public int getTeamAccountsCount(long teamId) {
		return _accountLocalService.getTeamAccountsCount(teamId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account reindex(
			long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountLocalService.reindex(accountId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(
			long companyId, String keywords, int start, int end,
			com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountLocalService.search(
			companyId, keywords, start, end, sort);
	}

	/**
	 * Updates the account in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param account the account
	 * @return the account that was updated
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account updateAccount(
		com.liferay.osb.koroneiki.taproot.model.Account account) {

		return _accountLocalService.updateAccount(account);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Account updateAccount(
			long userId, long accountId, long parentAccountId, String name,
			String code, String description, long logoId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website, String tier,
			String region, String dataRegion, String language, boolean internal,
			String status,
			java.util.List<com.liferay.osb.koroneiki.taproot.model.AccountField>
				accountFields)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountLocalService.updateAccount(
			userId, accountId, parentAccountId, name, code, description, logoId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website, tier, region, dataRegion, language, internal, status,
			accountFields);
	}

	@Override
	public AccountLocalService getWrappedService() {
		return _accountLocalService;
	}

	@Override
	public void setWrappedService(AccountLocalService accountLocalService) {
		_accountLocalService = accountLocalService;
	}

	private AccountLocalService _accountLocalService;

}