/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.web.internal.security.permission.resource;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.security.permission.DDMPermissionSupport;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(immediate = true, service = {})
public class DDMStructurePermission {

	public static boolean contains(
			PermissionChecker permissionChecker, DDMStructure structure,
			String actionId)
		throws PortalException {

		return _ddmStructureModelResourcePermission.contains(
			permissionChecker, structure, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long structureId,
			String actionId)
		throws PortalException {

		return _ddmStructureModelResourcePermission.contains(
			permissionChecker, structureId, actionId);
	}

	public static boolean containsAddStructurePermission(
			PermissionChecker permissionChecker, long groupId, long classNameId)
		throws PortalException {

		return _ddmPermissionSupport.containsAddStructurePermission(
			permissionChecker, groupId, classNameId);
	}

	public static String getStructureModelResourceName(long classNameId)
		throws PortalException {

		return _ddmPermissionSupport.getStructureModelResourceName(classNameId);
	}

	@Reference(unbind = "-")
	protected void setDDMPermissionSupport(
		DDMPermissionSupport ddmPermissionSupport) {

		_ddmPermissionSupport = ddmPermissionSupport;
	}

	@Reference(
		target = "(model.class.name=com.liferay.dynamic.data.mapping.model.DDMStructure)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<DDMStructure> modelResourcePermission) {

		_ddmStructureModelResourcePermission = modelResourcePermission;
	}

	private static DDMPermissionSupport _ddmPermissionSupport;
	private static ModelResourcePermission<DDMStructure>
		_ddmStructureModelResourcePermission;

}