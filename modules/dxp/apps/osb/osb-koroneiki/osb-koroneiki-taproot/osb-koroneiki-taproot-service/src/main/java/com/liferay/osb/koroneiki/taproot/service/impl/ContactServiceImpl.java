/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.model.Team;
import com.liferay.osb.koroneiki.taproot.permission.AccountPermission;
import com.liferay.osb.koroneiki.taproot.permission.ContactPermission;
import com.liferay.osb.koroneiki.taproot.permission.TeamPermission;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.TeamLocalService;
import com.liferay.osb.koroneiki.taproot.service.base.ContactServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=Contact"
	},
	service = AopService.class
)
public class ContactServiceImpl extends ContactServiceBaseImpl {

	public Contact addContact(
			String uuid, String firstName, String middleName, String lastName,
			String emailAddress, String languageId,
			boolean emailAddressVerified)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), TaprootActionKeys.ADD_CONTACT);

		return contactLocalService.addContact(
			uuid, getUserId(), firstName, middleName, lastName, emailAddress,
			languageId, emailAddressVerified);
	}

	public Contact deleteContact(long contactId) throws PortalException {
		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.DELETE);

		return contactLocalService.deleteContact(contactId);
	}

	public Contact fetchContactByUuid(String uuid) throws PortalException {
		Contact contact = contactLocalService.fetchContactByUuid(uuid);

		if (contact != null) {
			_contactPermission.check(
				getPermissionChecker(), contact, ActionKeys.VIEW);
		}

		return contact;
	}

	public List<Contact> getAccountContacts(
			long accountId, String contactRoleType, int start, int end)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return contactLocalService.getAccountContacts(
			accountId, contactRoleType, start, end);
	}

	public List<Contact> getAccountContacts(
			String accountKey, String contactRoleType, int start, int end)
		throws PortalException {

		Account account = _accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			getPermissionChecker(), account, ActionKeys.VIEW);

		return contactLocalService.getAccountContacts(
			account.getAccountId(), contactRoleType, start, end);
	}

	public int getAccountContactsCount(long accountId, String contactRoleType)
		throws PortalException {

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return contactLocalService.getAccountContactsCount(
			accountId, contactRoleType);
	}

	public int getAccountContactsCount(
			String accountKey, String contactRoleType)
		throws PortalException {

		Account account = _accountLocalService.getAccount(accountKey);

		_accountPermission.check(
			getPermissionChecker(), account, ActionKeys.VIEW);

		return contactLocalService.getAccountContactsCount(
			account.getAccountId(), contactRoleType);
	}

	public Contact getContact(long contactId) throws PortalException {
		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		return contactLocalService.getContact(contactId);
	}

	public Contact getContactByEmailAddress(String emailAddress)
		throws PortalException {

		Contact contact = contactLocalService.getContactByEmailAddress(
			emailAddress);

		_contactPermission.check(
			getPermissionChecker(), contact, ActionKeys.VIEW);

		return contact;
	}

	public Contact getContactByUuid(String uuid) throws PortalException {
		Contact contact = contactLocalService.getContactByUuid(uuid);

		_contactPermission.check(
			getPermissionChecker(), contact, ActionKeys.VIEW);

		return contact;
	}

	public List<Contact> getTeamContacts(String teamKey, int start, int end)
		throws PortalException {

		Team team = _teamLocalService.getTeam(teamKey);

		_teamPermission.check(getPermissionChecker(), team, ActionKeys.VIEW);

		return contactLocalService.getTeamContacts(
			team.getTeamId(), start, end);
	}

	public int getTeamContactsCount(String teamKey) throws PortalException {
		Team team = _teamLocalService.getTeam(teamKey);

		_teamPermission.check(getPermissionChecker(), team, ActionKeys.VIEW);

		return contactLocalService.getTeamContactsCount(team.getTeamId());
	}

	public Contact updateContact(
			long contactId, String uuid, String firstName, String middleName,
			String lastName, String emailAddress, String languageId,
			boolean emailAddressVerified)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.UPDATE);

		return contactLocalService.updateContact(
			contactId, uuid, firstName, middleName, lastName, emailAddress,
			languageId, emailAddressVerified);
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private AccountPermission _accountPermission;

	@Reference
	private ContactPermission _contactPermission;

	@Reference
	private TeamLocalService _teamLocalService;

	@Reference
	private TeamPermission _teamPermission;

}