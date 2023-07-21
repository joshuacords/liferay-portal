/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.permission.AccountPermission;
import com.liferay.osb.koroneiki.taproot.permission.ContactPermission;
import com.liferay.osb.koroneiki.taproot.permission.ContactRolePermission;
import com.liferay.osb.koroneiki.taproot.permission.TeamPermission;
import com.liferay.osb.koroneiki.taproot.service.base.ContactRoleServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(
	property = {
		"json.web.service.context.name=koroneiki",
		"json.web.service.context.path=ContactRole"
	},
	service = AopService.class
)
public class ContactRoleServiceImpl extends ContactRoleServiceBaseImpl {

	public ContactRole addContactRole(
			String name, String description, String type)
		throws PortalException {

		_contactRolePermission.check(
			getPermissionChecker(), TaprootActionKeys.ADD_CONTACT_ROLE);

		return contactRoleLocalService.addContactRole(
			getUserId(), name, description, type);
	}

	public ContactRole deleteContactRole(long contactRoleId)
		throws PortalException {

		_contactRolePermission.check(
			getPermissionChecker(), contactRoleId, ActionKeys.DELETE);

		return contactRoleLocalService.deleteContactRole(contactRoleId);
	}

	public ContactRole deleteContactRole(String contactRoleKey)
		throws PortalException {

		ContactRole contactRole = contactRoleLocalService.getContactRole(
			contactRoleKey);

		_contactRolePermission.check(
			getPermissionChecker(), contactRole, ActionKeys.DELETE);

		return contactRoleLocalService.deleteContactRole(contactRole);
	}

	public List<ContactRole> getContactAccountContactRoles(
			long accountId, long contactId, String[] types, int start, int end)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return contactRoleLocalService.getContactAccountContactRoles(
			accountId, contactId, types, start, end);
	}

	public int getContactAccountContactRolesCount(
			long accountId, long contactId, String[] types)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		_accountPermission.check(
			getPermissionChecker(), accountId, ActionKeys.VIEW);

		return contactRoleLocalService.getContactAccountContactRolesCount(
			accountId, contactId, types);
	}

	public List<ContactRole> getContactContactRoles(
			long contactId, int start, int end)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		return contactRoleLocalService.getContactContactRoles(
			contactId, start, end);
	}

	public ContactRole getContactRole(long contactRoleId)
		throws PortalException {

		_contactRolePermission.check(
			getPermissionChecker(), contactRoleId, ActionKeys.VIEW);

		return contactRoleLocalService.getContactRole(contactRoleId);
	}

	public ContactRole getContactRole(String contactRoleKey)
		throws PortalException {

		ContactRole contactRole = contactRoleLocalService.getContactRole(
			contactRoleKey);

		_contactRolePermission.check(
			getPermissionChecker(), contactRole, ActionKeys.VIEW);

		return contactRole;
	}

	public ContactRole getContactRole(String name, String type)
		throws PortalException {

		ContactRole contactRole = contactRoleLocalService.getContactRole(
			name, type);

		_contactRolePermission.check(
			getPermissionChecker(), contactRole, ActionKeys.VIEW);

		return contactRole;
	}

	public List<ContactRole> getContactTeamContactRoles(
			long teamId, long contactId, String[] types, int start, int end)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		_teamPermission.check(getPermissionChecker(), teamId, ActionKeys.VIEW);

		return contactRoleLocalService.getContactTeamContactRoles(
			teamId, contactId, types, start, end);
	}

	public int getContactTeamContactRolesCount(
			long teamId, long contactId, String[] types)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		_teamPermission.check(getPermissionChecker(), teamId, ActionKeys.VIEW);

		return contactRoleLocalService.getContactTeamContactRolesCount(
			teamId, contactId, types);
	}

	public ContactRole updateContactRole(
			long contactRoleId, String name, String description)
		throws PortalException {

		_contactRolePermission.check(
			getPermissionChecker(), contactRoleId, ActionKeys.UPDATE);

		return contactRoleLocalService.updateContactRole(
			contactRoleId, name, description);
	}

	@Reference
	private AccountPermission _accountPermission;

	@Reference
	private ContactPermission _contactPermission;

	@Reference
	private ContactRolePermission _contactRolePermission;

	@Reference
	private TeamPermission _teamPermission;

}