/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContactService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContactService
 * @generated
 */
public class ContactServiceWrapper
	implements ContactService, ServiceWrapper<ContactService> {

	public ContactServiceWrapper(ContactService contactService) {
		_contactService = contactService;
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact addContact(
			String uuid, String firstName, String middleName, String lastName,
			String emailAddress, String languageId,
			boolean emailAddressVerified)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactService.addContact(
			uuid, firstName, middleName, lastName, emailAddress, languageId,
			emailAddressVerified);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact deleteContact(
			long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactService.deleteContact(contactId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact fetchContactByUuid(
			String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactService.fetchContactByUuid(uuid);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Contact>
			getAccountContacts(
				long accountId, String contactRoleType, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactService.getAccountContacts(
			accountId, contactRoleType, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Contact>
			getAccountContacts(
				String accountKey, String contactRoleType, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactService.getAccountContacts(
			accountKey, contactRoleType, start, end);
	}

	@Override
	public int getAccountContactsCount(long accountId, String contactRoleType)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactService.getAccountContactsCount(
			accountId, contactRoleType);
	}

	@Override
	public int getAccountContactsCount(
			String accountKey, String contactRoleType)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactService.getAccountContactsCount(
			accountKey, contactRoleType);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact getContact(
			long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactService.getContact(contactId);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact
			getContactByEmailAddress(String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactService.getContactByEmailAddress(emailAddress);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact getContactByUuid(
			String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactService.getContactByUuid(uuid);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _contactService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.taproot.model.Contact>
			getTeamContacts(String teamKey, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactService.getTeamContacts(teamKey, start, end);
	}

	@Override
	public int getTeamContactsCount(String teamKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactService.getTeamContactsCount(teamKey);
	}

	@Override
	public com.liferay.osb.koroneiki.taproot.model.Contact updateContact(
			long contactId, String uuid, String firstName, String middleName,
			String lastName, String emailAddress, String languageId,
			boolean emailAddressVerified)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _contactService.updateContact(
			contactId, uuid, firstName, middleName, lastName, emailAddress,
			languageId, emailAddressVerified);
	}

	@Override
	public ContactService getWrappedService() {
		return _contactService;
	}

	@Override
	public void setWrappedService(ContactService contactService) {
		_contactService = contactService;
	}

	private ContactService _contactService;

}