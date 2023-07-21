/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.web.internal.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.reports.engine.console.model.Entry;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Leon Chi
 */
@Component(immediate = true, service = {})
public class EntryPermissionChecker {

	public static boolean contains(
			PermissionChecker permissionChecker, Entry entry, String actionId)
		throws PortalException {

		return _entryModelResourcePermission.contains(
			permissionChecker, entry, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long entryId, String actionId)
		throws PortalException {

		return _entryModelResourcePermission.contains(
			permissionChecker, entryId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.portal.reports.engine.console.model.Entry)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<Entry> modelResourcePermission) {

		_entryModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<Entry> _entryModelResourcePermission;

}