/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.security.permission.resource;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class DLFileEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, DLFileEntry fileEntry,
			String actionId)
		throws PortalException {

		_dlFileEntryModelResourcePermission.check(
			permissionChecker, fileEntry, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, DLFileEntry dlFileEntry,
			String actionId)
		throws PortalException {

		return _dlFileEntryModelResourcePermission.contains(
			permissionChecker, dlFileEntry, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, FileEntry fileEntry,
			String actionId)
		throws PortalException {

		return _fileEntryModelResourcePermission.contains(
			permissionChecker, fileEntry, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long fileEntryId,
			String actionId)
		throws PortalException {

		return _fileEntryModelResourcePermission.contains(
			permissionChecker, fileEntryId, actionId);
	}

	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(model.class.name=com.liferay.document.library.kernel.model.DLFileEntry)"
	)
	protected void setDLFileEntryModelResourcePermission(
		ModelResourcePermission<DLFileEntry> modelResourcePermission) {

		_dlFileEntryModelResourcePermission = modelResourcePermission;
	}

	@Reference(
		target = "(model.class.name=com.liferay.portal.kernel.repository.model.FileEntry)",
		unbind = "-"
	)
	protected void setFileEntryModelResourcePermission(
		ModelResourcePermission<FileEntry> modelResourcePermission) {

		_fileEntryModelResourcePermission = modelResourcePermission;
	}

	protected void unsetDLFileEntryModelResourcePermission(
		ModelResourcePermission<DLFileEntry> modelResourcePermission) {
	}

	private static ModelResourcePermission<DLFileEntry>
		_dlFileEntryModelResourcePermission;
	private static ModelResourcePermission<FileEntry>
		_fileEntryModelResourcePermission;

}