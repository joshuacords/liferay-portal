/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.impl;

import com.liferay.osb.koroneiki.taproot.constants.TaprootActionKeys;
import com.liferay.osb.koroneiki.taproot.model.ContactTeamRole;
import com.liferay.osb.koroneiki.taproot.permission.ContactPermission;
import com.liferay.osb.koroneiki.taproot.permission.ContactRolePermission;
import com.liferay.osb.koroneiki.taproot.permission.TeamPermission;
import com.liferay.osb.koroneiki.taproot.service.base.ContactTeamRoleServiceBaseImpl;
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
		"json.web.service.context.path=ContactTeamRole"
	},
	service = AopService.class
)
public class ContactTeamRoleServiceImpl extends ContactTeamRoleServiceBaseImpl {

	public ContactTeamRole addContactTeamRole(
			long contactId, long teamId, long contactRoleId)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		_teamPermission.check(
			getPermissionChecker(), teamId, TaprootActionKeys.ASSIGN_CONTACT);

		_contactRolePermission.check(
			getPermissionChecker(), contactRoleId,
			TaprootActionKeys.ASSIGN_CONTACT);

		return contactTeamRoleLocalService.addContactTeamRole(
			contactId, teamId, contactRoleId);
	}

	public ContactTeamRole deleteContactTeamRole(
			long contactId, long teamId, long contactRoleId)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		_teamPermission.check(
			getPermissionChecker(), teamId, TaprootActionKeys.ASSIGN_CONTACT);

		_contactRolePermission.check(
			getPermissionChecker(), contactRoleId,
			TaprootActionKeys.ASSIGN_CONTACT);

		return contactTeamRoleLocalService.deleteContactTeamRole(
			contactId, teamId, contactRoleId);
	}

	public void deleteContactTeamRoles(long contactId, long teamId)
		throws PortalException {

		_contactPermission.check(
			getPermissionChecker(), contactId, ActionKeys.VIEW);

		_teamPermission.check(
			getPermissionChecker(), teamId, TaprootActionKeys.ASSIGN_CONTACT);

		List<ContactTeamRole> contactTeamRoles =
			contactTeamRolePersistence.findByCI_TI(contactId, teamId);

		for (ContactTeamRole contactTeamRole : contactTeamRoles) {
			_contactRolePermission.check(
				getPermissionChecker(), contactTeamRole.getContactRoleId(),
				TaprootActionKeys.ASSIGN_CONTACT);
		}

		contactTeamRoleLocalService.deleteContactTeamRoles(contactId, teamId);
	}

	@Reference
	private ContactPermission _contactPermission;

	@Reference
	private ContactRolePermission _contactRolePermission;

	@Reference
	private TeamPermission _teamPermission;

}