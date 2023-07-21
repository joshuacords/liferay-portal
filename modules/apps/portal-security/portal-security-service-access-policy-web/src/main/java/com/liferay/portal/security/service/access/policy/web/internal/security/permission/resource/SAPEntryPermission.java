/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.service.access.policy.web.internal.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.security.service.access.policy.model.SAPEntry;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class SAPEntryPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, long sapEntryId,
			String actionId)
		throws PortalException {

		return _sapEntryFolderModelResourcePermission.contains(
			permissionChecker, sapEntryId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, SAPEntry sapEntry,
			String actionId)
		throws PortalException {

		return _sapEntryFolderModelResourcePermission.contains(
			permissionChecker, sapEntry, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.portal.security.service.access.policy.model.SAPEntry)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<SAPEntry> modelResourcePermission) {

		_sapEntryFolderModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<SAPEntry>
		_sapEntryFolderModelResourcePermission;

}