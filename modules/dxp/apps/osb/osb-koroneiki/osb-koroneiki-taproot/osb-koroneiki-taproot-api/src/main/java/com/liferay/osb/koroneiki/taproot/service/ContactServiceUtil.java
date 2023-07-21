/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service;

import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for Contact. This utility wraps
 * <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ContactService
 * @generated
 */
public class ContactServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.koroneiki.taproot.service.impl.ContactServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static Contact addContact(
			String uuid, String firstName, String middleName, String lastName,
			String emailAddress, String languageId,
			boolean emailAddressVerified)
		throws PortalException {

		return getService().addContact(
			uuid, firstName, middleName, lastName, emailAddress, languageId,
			emailAddressVerified);
	}

	public static Contact deleteContact(long contactId) throws PortalException {
		return getService().deleteContact(contactId);
	}

	public static Contact fetchContactByUuid(String uuid)
		throws PortalException {

		return getService().fetchContactByUuid(uuid);
	}

	public static List<Contact> getAccountContacts(
			long accountId, String contactRoleType, int start, int end)
		throws PortalException {

		return getService().getAccountContacts(
			accountId, contactRoleType, start, end);
	}

	public static List<Contact> getAccountContacts(
			String accountKey, String contactRoleType, int start, int end)
		throws PortalException {

		return getService().getAccountContacts(
			accountKey, contactRoleType, start, end);
	}

	public static int getAccountContactsCount(
			long accountId, String contactRoleType)
		throws PortalException {

		return getService().getAccountContactsCount(accountId, contactRoleType);
	}

	public static int getAccountContactsCount(
			String accountKey, String contactRoleType)
		throws PortalException {

		return getService().getAccountContactsCount(
			accountKey, contactRoleType);
	}

	public static Contact getContact(long contactId) throws PortalException {
		return getService().getContact(contactId);
	}

	public static Contact getContactByEmailAddress(String emailAddress)
		throws PortalException {

		return getService().getContactByEmailAddress(emailAddress);
	}

	public static Contact getContactByUuid(String uuid) throws PortalException {
		return getService().getContactByUuid(uuid);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static List<Contact> getTeamContacts(
			String teamKey, int start, int end)
		throws PortalException {

		return getService().getTeamContacts(teamKey, start, end);
	}

	public static int getTeamContactsCount(String teamKey)
		throws PortalException {

		return getService().getTeamContactsCount(teamKey);
	}

	public static Contact updateContact(
			long contactId, String uuid, String firstName, String middleName,
			String lastName, String emailAddress, String languageId,
			boolean emailAddressVerified)
		throws PortalException {

		return getService().updateContact(
			contactId, uuid, firstName, middleName, lastName, emailAddress,
			languageId, emailAddressVerified);
	}

	public static ContactService getService() {
		return _service;
	}

	public static void setService(ContactService service) {
		_service = service;
	}

	private static volatile ContactService _service;

}