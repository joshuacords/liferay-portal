/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccountNoteLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountNoteLocalService
 * @generated
 */
public class AccountNoteLocalServiceWrapper
	implements AccountNoteLocalService,
			   ServiceWrapper<AccountNoteLocalService> {

	public AccountNoteLocalServiceWrapper(
		AccountNoteLocalService accountNoteLocalService) {

		_accountNoteLocalService = accountNoteLocalService;
	}

	/**
	 * Adds the account note to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountNote the account note
	 * @return the account note that was added
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote addAccountNote(
		com.liferay.osb.koroneiki.taproot.model.AccountNote accountNote) {

		return _accountNoteLocalService.addAccountNote(accountNote);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote addAccountNote(
			long userId, String creatorUID, String creatorName, long accountId,
			String type, int priority, String content, String format,
			String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteLocalService.addAccountNote(
			userId, creatorUID, creatorName, accountId, type, priority, content,
			format, status);
	}

	/**
	 * Creates a new account note with the primary key. Does not add the account note to the database.
	 *
	 * @param accountNoteId the primary key for the new account note
	 * @return the new account note
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote
		createAccountNote(long accountNoteId) {

		return _accountNoteLocalService.createAccountNote(accountNoteId);
	}

	/**
	 * Deletes the account note from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountNote the account note
	 * @return the account note that was removed
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote
		deleteAccountNote(
			com.liferay.osb.koroneiki.taproot.model.AccountNote accountNote) {

		return _accountNoteLocalService.deleteAccountNote(accountNote);
	}

	/**
	 * Deletes the account note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountNoteId the primary key of the account note
	 * @return the account note that was removed
	 * @throws PortalException if a account note with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote
			deleteAccountNote(long accountNoteId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteLocalService.deleteAccountNote(accountNoteId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountNoteLocalService.dynamicQuery();
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

		return _accountNoteLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountNoteModelImpl</code>.
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

		return _accountNoteLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountNoteModelImpl</code>.
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

		return _accountNoteLocalService.dynamicQuery(
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

		return _accountNoteLocalService.dynamicQueryCount(dynamicQuery);
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

		return _accountNoteLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote fetchAccountNote(
		long accountNoteId) {

		return _accountNoteLocalService.fetchAccountNote(accountNoteId);
	}

	/**
	 * Returns the account note with the matching UUID and company.
	 *
	 * @param uuid the account note's UUID
	 * @param companyId the primary key of the company
	 * @return the matching account note, or <code>null</code> if a matching account note could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote
		fetchAccountNoteByUuidAndCompanyId(String uuid, long companyId) {

		return _accountNoteLocalService.fetchAccountNoteByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns the account note with the primary key.
	 *
	 * @param accountNoteId the primary key of the account note
	 * @return the account note
	 * @throws PortalException if a account note with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote getAccountNote(
			long accountNoteId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteLocalService.getAccountNote(accountNoteId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote getAccountNote(
			String accountNoteKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteLocalService.getAccountNote(accountNoteKey);
	}

	/**
	 * Returns the account note with the matching UUID and company.
	 *
	 * @param uuid the account note's UUID
	 * @param companyId the primary key of the company
	 * @return the matching account note
	 * @throws PortalException if a matching account note could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote
			getAccountNoteByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteLocalService.getAccountNoteByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of all the account notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.AccountNoteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account notes
	 * @param end the upper bound of the range of account notes (not inclusive)
	 * @return the range of account notes
	 */
	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.AccountNote>
		getAccountNotes(int start, int end) {

		return _accountNoteLocalService.getAccountNotes(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.AccountNote>
		getAccountNotes(long accountId, int start, int end) {

		return _accountNoteLocalService.getAccountNotes(accountId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.AccountNote>
		getAccountNotes(
			long accountId, String[] types, int[] priorities, String[] statuses,
			int start, int end) {

		return _accountNoteLocalService.getAccountNotes(
			accountId, types, priorities, statuses, start, end);
	}

	/**
	 * Returns the number of account notes.
	 *
	 * @return the number of account notes
	 */
	@Override
	public int getAccountNotesCount() {
		return _accountNoteLocalService.getAccountNotesCount();
	}

	@Override
	public int getAccountNotesCount(long accountId) {
		return _accountNoteLocalService.getAccountNotesCount(accountId);
	}

	@Override
	public int getAccountNotesCount(
		long accountId, String[] types, int[] priorities, String[] statuses) {

		return _accountNoteLocalService.getAccountNotesCount(
			accountId, types, priorities, statuses);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _accountNoteLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _accountNoteLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _accountNoteLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _accountNoteLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the account note in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AccountNoteLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountNote the account note
	 * @return the account note that was updated
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote
		updateAccountNote(
			com.liferay.osb.koroneiki.taproot.model.AccountNote accountNote) {

		return _accountNoteLocalService.updateAccountNote(accountNote);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.AccountNote
			updateAccountNote(
				long accountNoteId, String modifierUID, String modifierName,
				int priority, String content, String format, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _accountNoteLocalService.updateAccountNote(
			accountNoteId, modifierUID, modifierName, priority, content, format,
			status);
	}

	@Override
	public AccountNoteLocalService getWrappedService() {
		return _accountNoteLocalService;
	}

	@Override
	public void setWrappedService(
		AccountNoteLocalService accountNoteLocalService) {

		_accountNoteLocalService = accountNoteLocalService;
	}

	private AccountNoteLocalService _accountNoteLocalService;

}