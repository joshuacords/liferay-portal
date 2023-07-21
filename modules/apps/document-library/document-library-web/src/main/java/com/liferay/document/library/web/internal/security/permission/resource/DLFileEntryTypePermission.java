/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.security.permission.resource;

import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class DLFileEntryTypePermission {

	public static boolean contains(
			PermissionChecker permissionChecker, DLFileEntryType fileEntryType,
			String actionId)
		throws PortalException {

		return _dlFileEntryTypeModelResourcePermission.contains(
			permissionChecker, fileEntryType, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long fileEntryTypeId,
			String actionId)
		throws PortalException {

		return _dlFileEntryTypeModelResourcePermission.contains(
			permissionChecker, fileEntryTypeId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.document.library.kernel.model.DLFileEntryType)",
		unbind = "-"
	)
	protected void setDLFileEntryTypeModelResourcePermission(
		ModelResourcePermission<DLFileEntryType> modelResourcePermission) {

		_dlFileEntryTypeModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<DLFileEntryType>
		_dlFileEntryTypeModelResourcePermission;

}