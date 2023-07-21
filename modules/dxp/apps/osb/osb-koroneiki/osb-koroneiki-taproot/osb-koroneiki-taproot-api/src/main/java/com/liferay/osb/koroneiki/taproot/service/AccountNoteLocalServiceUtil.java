/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.AccountNote;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for AccountNote. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.AccountNoteLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccountNoteLocalService
 * @generated
 */
public class AccountNoteLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.AccountNoteLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static AccountNote addAccountNote(AccountNote accountNote) {
		return getService().addAccountNote(accountNote);
	}

	public static AccountNote addAccountNote(
			long userId, String creatorUID, String creatorName, long accountId,
			String type, int priority, String content, String format,
			String status)
		throws PortalException {

		return getService().addAccountNote(
			userId, creatorUID, creatorName, accountId, type, priority, content,
			format, status);
	}

	/**
	 * Creates a new account note with the primary key. Does not add the account note to the database.
	 *
	 * @param accountNoteId the primary key for the new account note
	 * @return the new account note
	 */
	public static AccountNote createAccountNote(long accountNoteId) {
		return getService().createAccountNote(accountNoteId);
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
	public static AccountNote deleteAccountNote(AccountNote accountNote) {
		return getService().deleteAccountNote(accountNote);
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
	public static AccountNote deleteAccountNote(long accountNoteId)
		throws PortalException {

		return getService().deleteAccountNote(accountNoteId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static AccountNote fetchAccountNote(long accountNoteId) {
		return getService().fetchAccountNote(accountNoteId);
	}

	/**
	 * Returns the account note with the matching UUID and company.
	 *
	 * @param uuid the account note's UUID
	 * @param companyId the primary key of the company
	 * @return the matching account note, or <code>null</code> if a matching account note could not be found
	 */
	public static AccountNote fetchAccountNoteByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().fetchAccountNoteByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns the account note with the primary key.
	 *
	 * @param accountNoteId the primary key of the account note
	 * @return the account note
	 * @throws PortalException if a account note with the primary key could not be found
	 */
	public static AccountNote getAccountNote(long accountNoteId)
		throws PortalException {

		return getService().getAccountNote(accountNoteId);
	}

	public static AccountNote getAccountNote(String accountNoteKey)
		throws PortalException {

		return getService().getAccountNote(accountNoteKey);
	}

	/**
	 * Returns the account note with the matching UUID and company.
	 *
	 * @param uuid the account note's UUID
	 * @param companyId the primary key of the company
	 * @return the matching account note
	 * @throws PortalException if a matching account note could not be found
	 */
	public static AccountNote getAccountNoteByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return getService().getAccountNoteByUuidAndCompanyId(uuid, companyId);
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
	public static List<AccountNote> getAccountNotes(int start, int end) {
		return getService().getAccountNotes(start, end);
	}

	public static List<AccountNote> getAccountNotes(
		long accountId, int start, int end) {

		return getService().getAccountNotes(accountId, start, end);
	}

	public static List<AccountNote> getAccountNotes(
		long accountId, String[] types, int[] priorities, String[] statuses,
		int start, int end) {

		return getService().getAccountNotes(
			accountId, types, priorities, statuses, start, end);
	}

	/**
	 * Returns the number of account notes.
	 *
	 * @return the number of account notes
	 */
	public static int getAccountNotesCount() {
		return getService().getAccountNotesCount();
	}

	public static int getAccountNotesCount(long accountId) {
		return getService().getAccountNotesCount(accountId);
	}

	public static int getAccountNotesCount(
		long accountId, String[] types, int[] priorities, String[] statuses) {

		return getService().getAccountNotesCount(
			accountId, types, priorities, statuses);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
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
	public static AccountNote updateAccountNote(AccountNote accountNote) {
		return getService().updateAccountNote(accountNote);
	}

	public static AccountNote updateAccountNote(
			long accountNoteId, String modifierUID, String modifierName,
			int priority, String content, String format, String status)
		throws PortalException {

		return getService().updateAccountNote(
			accountNoteId, modifierUID, modifierName, priority, content, format,
			status);
	}

	public static AccountNoteLocalService getService() {
		return _service;
	}

	public static void setService(AccountNoteLocalService service) {
		_service = service;
	}

	private static volatile AccountNoteLocalService _service;

}