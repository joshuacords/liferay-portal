/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountFieldLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountFieldLocalService
 * @generated
 */
public class AccountFieldLocalServiceWrapper
	implements AccountFieldLocalService,
			   ServiceWrapper<AccountFieldLocalService> {

	public AccountFieldLocalServiceWrapper(
		AccountFieldLocalService accountFieldLocalService) {

		_accountFieldLocalService = accountFieldLocalService;
	}

	/**
	 * Adds the account field to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountFieldLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountField the account field
	 * @return the account field that was added
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountField addAccountField(
		com.liferay.osb.koroneiki.taproot.model.AccountField accountField) {

		return _accountFieldLocalService.addAccountField(accountField);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountField addAccountField(
			long userId, long accountId, String name, String value)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountFieldLocalService.addAccountField(
			userId, accountId, name, value);
	}

	/**
	 * Creates a new account field with the primary key. Does not add the account field to the database.
	 *
	 * @param accountFieldId the primary key for the new account field
	 * @return the new account field
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountField
		createAccountField(long accountFieldId) {

		return _accountFieldLocalService.createAccountField(accountFieldId);
	}

	/**
	 * Deletes the account field from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountFieldLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountField the account field
	 * @return the account field that was removed
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountField
		deleteAccountField(
			com.liferay.osb.koroneiki.taproot.model.AccountField accountField) {

		return _accountFieldLocalService.deleteAccountField(accountField);
	}

	/**
	 * Deletes the account field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountFieldLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountFieldId the primary key of the account field
	 * @return the account field that was removed
	 * @throws PortalException if a account field with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountField
			deleteAccountField(long accountFieldId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountFieldLocalService.deleteAccountField(accountFieldId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountFieldLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountFieldLocalService.dynamicQuery();
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

		return _accountFieldLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountFieldModelImpl</code>.
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

		return _accountFieldLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountFieldModelImpl</code>.
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

		return _accountFieldLocalService.dynamicQuery(
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

		return _accountFieldLocalService.dynamicQueryCount(dynamicQuery);
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

		return _accountFieldLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountField
		fetchAccountField(long accountFieldId) {

		return _accountFieldLocalService.fetchAccountField(accountFieldId);
	}

	/**
	 * Returns the account field with the primary key.
	 *
	 * @param accountFieldId the primary key of the account field
	 * @return the account field
	 * @throws PortalException if a account field with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountField getAccountField(
			long accountFieldId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountFieldLocalService.getAccountField(accountFieldId);
	}

	@Override
	public java.util.List<String> getAccountFieldNames() {
		return _accountFieldLocalService.getAccountFieldNames();
	}

	/**
	 * Returns a range of all the account fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountFieldModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account fields
	 * @param end the upper bound of the range of account fields (not inclusive)
	 * @return the range of account fields
	 */
	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.AccountField>
		getAccountFields(int start, int end) {

		return _accountFieldLocalService.getAccountFields(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.AccountField>
		getAccountFields(long accountId) {

		return _accountFieldLocalService.getAccountFields(accountId);
	}

	/**
	 * Returns the number of account fields.
	 *
	 * @return the number of account fields
	 */
	@Override
	public int getAccountFieldsCount() {
		return _accountFieldLocalService.getAccountFieldsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _accountFieldLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _accountFieldLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountFieldLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountFieldLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the account field in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountFieldLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountField the account field
	 * @return the account field that was updated
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountField
		updateAccountField(
			com.liferay.osb.koroneiki.taproot.model.AccountField accountField) {

		return _accountFieldLocalService.updateAccountField(accountField);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountField
			updateAccountField(long accountFieldId, String value)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountFieldLocalService.updateAccountField(
			accountFieldId, value);
	}

	@Override
	public AccountFieldLocalService getWrappedService() {
		return _accountFieldLocalService;
	}

	@Override
	public void setWrappedService(
		AccountFieldLocalService accountFieldLocalService) {

		_accountFieldLocalService = accountFieldLocalService;
	}

	private AccountFieldLocalService _accountFieldLocalService;

}