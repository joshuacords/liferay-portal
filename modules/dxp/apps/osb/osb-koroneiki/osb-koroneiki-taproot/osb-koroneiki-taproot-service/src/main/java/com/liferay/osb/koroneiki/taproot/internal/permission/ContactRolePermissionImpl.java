/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.permission;

import com.liferay.osb.koroneiki.root.permission.ModelPermission;
import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.permission.ContactRolePermission;
import com.liferay.osb.koroneiki.taproot.service.ContactRoleLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	service = {ContactRolePermission.class, ModelPermission.class}
)
public class ContactRolePermissionImpl
	implements ContactRolePermission, ModelPermission {

	public static final String RESOURCE_NAME_CONTACT_ROLES =
		"com.liferay.osb.koroneiki.taproot.contact.roles";

	@Override
	public void check(
			PermissionChecker permissionChecker, ContactRole contactRole,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, contactRole, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ContactRole.class.getName(),
				contactRole.getContactRoleId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long contactRoleId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, contactRoleId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, ContactRole.class.getName(), contactRoleId,
				actionId);
		}
	}

	@Override
	public void check(PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, RESOURCE_NAME_CONTACT_ROLES, 0, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, ContactRole contactRole,
			String actionId)
		throws PortalException {

		if (permissionChecker.hasOwnerPermission(
				contactRole.getCompanyId(), ContactRole.class.getName(),
				contactRole.getContactRoleId(), contactRole.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			0, ContactRole.class.getName(), contactRole.getContactRoleId(),
			actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long contactRoleId,
			String actionId)
		throws PortalException {

		if (contains(
				permissionChecker,
				_contactRoleLocalService.getContactRole(contactRoleId),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] contactRoleIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(contactRoleIds)) {
			return false;
		}

		for (long contactRoleId : contactRoleIds) {
			if (!contains(permissionChecker, contactRoleId, actionId)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		return permissionChecker.hasPermission(
			0, RESOURCE_NAME_CONTACT_ROLES, RESOURCE_NAME_CONTACT_ROLES,
			actionId);
	}

	@Override
	public String getClassName() {
		return ContactRole.class.getName();
	}

	@Reference
	private ContactRoleLocalService _contactRoleLocalService;

}