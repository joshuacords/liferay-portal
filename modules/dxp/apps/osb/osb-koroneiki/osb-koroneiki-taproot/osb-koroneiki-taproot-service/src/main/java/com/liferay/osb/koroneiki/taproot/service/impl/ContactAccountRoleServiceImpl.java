/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.model.ContactAccountRole;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.permission.AccountPermission;
import com.liferay.osb.koroneiki.taproot.permission.ContactPermission;
import com.liferay.osb.koroneiki.taproot.permission.ContactRolePermission;
import com.liferay.osb.koroneiki.taproot.service.AccountLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.ContactTeamRoleLocalService;
import com.liferay.osb.koroneiki.taproot.service.base.ContactAccountRoleServiceBaseImpl;
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
		"json.web.service.context.path=ContactAccountRole"
	},
	service = AopService.class
)
public class ContactAccountRoleServiceImpl
	extends ContactAccountRoleServiceBaseImpl {

	public ContactAccountRole addContactAccountRole(
			long contactId, long accountId, long contactRoleId)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		_accountPermission.check(
			getPermissionChecker(), accountId,
			TaprootActionKeys.ASSIGN_CONTACT);

		_contactRolePermission.check(
			getPermissionChecker(), contactRoleId,
			TaprootActionKeys.ASSIGN_CONTACT);

		return contactAccountRoleLocalService.addContactAccountRole(
			contactId, accountId, contactRoleId);
	}

	public ContactAccountRole deleteContactAccountRole(
			long contactId, long accountId, long contactRoleId)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		_accountPermission.check(
			getPermissionChecker(), accountId,
			TaprootActionKeys.ASSIGN_CONTACT);

		_contactRolePermission.check(
			getPermissionChecker(), contactRoleId,
			TaprootActionKeys.ASSIGN_CONTACT);

		return contactAccountRoleLocalService.deleteContactAccountRole(
			contactId, accountId, contactRoleId);
	}

	public void deleteContactAccountRoles(
			long contactId, long accountId, String contactRoleType)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		_accountPermission.check(
			getPermissionChecker(), accountId,
			TaprootActionKeys.ASSIGN_CONTACT);

		List<ContactAccountRole> contactAccountRoles =
			contactAccountRolePersistence.findByCI_AI(contactId, accountId);

		for (ContactAccountRole contactAccountRole : contactAccountRoles) {
			ContactRole contactRole = contactAccountRole.getContactRole();

			if (!contactRoleType.equals(contactRole.getType())) {
				continue;
			}

			_contactRolePermission.check(
				getPermissionChecker(), contactRole,
				TaprootActionKeys.ASSIGN_CONTACT);

			contactAccountRoleLocalService.deleteContactAccountRole(
				contactId, accountId, contactRole.getContactRoleId());
		}
	}

	@Reference
	private AccountLocalService _accountLocalService;

	@Reference
	private AccountPermission _accountPermission;

	@Reference
	private ContactLocalService _contactLocalService;

	@Reference
	private ContactPermission _contactPermission;

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

	@Reference
	private ContactRolePermission _contactRolePermission;

	@Reference
	private ContactTeamRoleLocalService _contactTeamRoleLocalService;

}