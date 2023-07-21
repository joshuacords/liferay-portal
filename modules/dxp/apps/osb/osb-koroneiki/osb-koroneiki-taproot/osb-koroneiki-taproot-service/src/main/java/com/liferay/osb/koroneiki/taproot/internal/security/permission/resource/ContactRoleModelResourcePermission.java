/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.security.permission.resource;

import com.liferay.osb.koroneiki.taproot.model.ContactRole;
import com.liferay.osb.koroneiki.taproot.permission.ContactRolePermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.ContactRole",
	service = ModelResourcePermission.class
)
public class ContactRoleModelResourcePermission
	implements ModelResourcePermission<ContactRole> {

	@Override
	public void check(
			PermissionChecker permissionChecker, ContactRole contactRole,
			String actionId)
		throws PortalException {

		_contactRolePermission.check(permissionChecker, contactRole, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long contactRoleId,
			String actionId)
		throws PortalException {

		_contactRolePermission.check(
			permissionChecker, contactRoleId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, ContactRole contactRole,
			String actionId)
		throws PortalException {

		return _contactRolePermission.contains(
			permissionChecker, contactRole, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long contactRoleId,
			String actionId)
		throws PortalException {

		return _contactRolePermission.contains(
			permissionChecker, contactRoleId, actionId);
	}

	@Override
	public String getModelName() {
		return ContactRole.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	private ContactRolePermission _contactRolePermission;

}