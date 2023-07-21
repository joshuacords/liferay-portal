/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContactLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContactLocalService
 * @generated
 */
public class ContactLocalServiceWrapper
	implements ContactLocalService, ServiceWrapper<ContactLocalService> {

	public ContactLocalServiceWrapper(ContactLocalService contactLocalService) {
		_contactLocalService = contactLocalService;
	}

	/**
	 * Adds the contact to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contact the contact
	 * @return the contact that was added
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact addContact(
		com.liferay.osb.koroneiki.taproot.model.Contact contact) {

		return _contactLocalService.addContact(contact);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact addContact(
			String uuid, long userId, String firstName, String middleName,
			String lastName, String emailAddress, String languageId,
			boolean emailAddressVerified)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactLocalService.addContact(
			uuid, userId, firstName, middleName, lastName, emailAddress,
			languageId, emailAddressVerified);
	}

	/**
	 * Creates a new contact with the primary key. Does not add the contact to the database.
	 *
	 * @param contactId the primary key for the new contact
	 * @return the new contact
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact createContact(
		long contactId) {

		return _contactLocalService.createContact(contactId);
	}

	/**
	 * Deletes the contact from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contact the contact
	 * @return the contact that was removed
	 * @throws PortalException
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact deleteContact(
			com.liferay.osb.koroneiki.taproot.model.Contact contact)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactLocalService.deleteContact(contact);
	}

	/**
	 * Deletes the contact with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contactId the primary key of the contact
	 * @return the contact that was removed
	 * @throws PortalException if a contact with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact deleteContact(
			long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactLocalService.deleteContact(contactId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contactLocalService.dynamicQuery();
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

		return _contactLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactModelImpl</code>.
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

		return _contactLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactModelImpl</code>.
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

		return _contactLocalService.dynamicQuery(
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

		return _contactLocalService.dynamicQueryCount(dynamicQuery);
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

		return _contactLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact fetchContact(
		long contactId) {

		return _contactLocalService.fetchContact(contactId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact
		fetchContactByEmailAddress(String emailAddress) {

		return _contactLocalService.fetchContactByEmailAddress(emailAddress);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact fetchContactByUuid(
		String uuid) {

		return _contactLocalService.fetchContactByUuid(uuid);
	}

	/**
	 * Returns the contact with the matching UUID and company.
	 *
	 * @param uuid the contact's UUID
	 * @param companyId the primary key of the company
	 * @return the matching contact, or <code>null</code> if a matching contact could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact
		fetchContactByUuidAndCompanyId(String uuid, long companyId) {

		return _contactLocalService.fetchContactByUuidAndCompanyId(
			uuid, companyId);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Contact>
		getAccountContacts(
			long accountId, String contactRoleType, int start, int end) {

		return _contactLocalService.getAccountContacts(
			accountId, contactRoleType, start, end);
	}

	@Override
	public int getAccountContactsCount(long accountId, String contactRoleType) {
		return _contactLocalService.getAccountContactsCount(
			accountId, contactRoleType);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _contactLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the contact with the primary key.
	 *
	 * @param contactId the primary key of the contact
	 * @return the contact
	 * @throws PortalException if a contact with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact getContact(
			long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactLocalService.getContact(contactId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact
			getContactByContactKey(String contactKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactLocalService.getContactByContactKey(contactKey);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact
			getContactByEmailAddress(String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactLocalService.getContactByEmailAddress(emailAddress);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact getContactByUuid(
			String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactLocalService.getContactByUuid(uuid);
	}

	/**
	 * Returns the contact with the matching UUID and company.
	 *
	 * @param uuid the contact's UUID
	 * @param companyId the primary key of the company
	 * @return the matching contact
	 * @throws PortalException if a matching contact could not be found
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact
			getContactByUuidAndCompanyId(String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactLocalService.getContactByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of all the contacts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.koroneiki.taproot.model.impl.ContactModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of contacts
	 * @param end the upper bound of the range of contacts (not inclusive)
	 * @return the range of contacts
	 */
	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Contact>
		getContacts(int start, int end) {

		return _contactLocalService.getContacts(start, end);
	}

	/**
	 * Returns the number of contacts.
	 *
	 * @return the number of contacts
	 */
	@Override
	public int getContactsCount() {
		return _contactLocalService.getContactsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _contactLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _contactLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contactLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Contact>
		getTeamContacts(long teamId, int start, int end) {

		return _contactLocalService.getTeamContacts(teamId, start, end);
	}

	@Override
	public int getTeamContactsCount(long teamId) {
		return _contactLocalService.getTeamContactsCount(teamId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact reindex(
			long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactLocalService.reindex(contactId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(
			long companyId, String keywords, int start, int end,
			com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactLocalService.search(
			companyId, keywords, start, end, sort);
	}

	/**
	 * Updates the contact in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ContactLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param contact the contact
	 * @return the contact that was updated
	 */
	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact updateContact(
		com.liferay.osb.koroneiki.taproot.model.Contact contact) {

		return _contactLocalService.updateContact(contact);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact updateContact(
			long contactId, String uuid, String firstName, String middleName,
			String lastName, String emailAddress, String languageId,
			boolean emailAddressVerified)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactLocalService.updateContact(
			contactId, uuid, firstName, middleName, lastName, emailAddress,
			languageId, emailAddressVerified);
	}

	@Override
	public ContactLocalService getWrappedService() {
		return _contactLocalService;
	}

	@Override
	public void setWrappedService(ContactLocalService contactLocalService) {
		_contactLocalService = contactLocalService;
	}

	private ContactLocalService _contactLocalService;

}