/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.internal.security.permission.resource;

import com.liferay.osb.koroneiki.taproot.model.Contact;
import com.liferay.osb.koroneiki.taproot.permission.ContactPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.osb.koroneiki.taproot.model.Contact",
	service = ModelResourcePermission.class
)
public class ContactModelResourcePermission
	implements ModelResourcePermission<Contact> {

	@Override
	public void check(
			PermissionChecker permissionChecker, Contact contact,
			String actionId)
		throws PortalException {

		_contactPermission.check(permissionChecker, contact, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long contactId,
			String actionId)
		throws PortalException {

		_contactPermission.check(permissionChecker, contactId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, Contact contact,
			String actionId)
		throws PortalException {

		return _contactPermission.contains(
			permissionChecker, contact, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long contactId,
			String actionId)
		throws PortalException {

		return _contactPermission.contains(
			permissionChecker, contactId, actionId);
	}

	@Override
	public String getModelName() {
		return Contact.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	private ContactPermission _contactPermission;

}