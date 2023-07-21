/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.web.internal.security.permission.resource;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = BlogsEntryPermission.class)
public class BlogsEntryPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, BlogsEntry entry,
			String actionId)
		throws PortalException {

		return _blogsEntryModelResourcePermission.contains(
			permissionChecker, entry, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long entryId, String actionId)
		throws PortalException {

		return _blogsEntryModelResourcePermission.contains(
			permissionChecker, entryId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.blogs.model.BlogsEntry)",
		unbind = "-"
	)
	protected void setEntryModelPermission(
		ModelResourcePermission<BlogsEntry> modelResourcePermission) {

		_blogsEntryModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<BlogsEntry>
		_blogsEntryModelResourcePermission;

}