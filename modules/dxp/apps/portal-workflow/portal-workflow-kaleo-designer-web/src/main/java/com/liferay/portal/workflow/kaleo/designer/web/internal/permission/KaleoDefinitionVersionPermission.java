/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.designer.web.internal.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = {})
public class KaleoDefinitionVersionPermission {

	public static boolean contains(
			PermissionChecker permissionChecker,
			KaleoDefinitionVersion kaleoDefinitionVersion, String actionId)
		throws PortalException {

		return _kaleoDefinitionVersionModelResourcePermission.contains(
			permissionChecker, kaleoDefinitionVersion, actionId);
	}

	public static boolean hasViewPermission(
			PermissionChecker permissionChecker,
			KaleoDefinitionVersion kaleoDefinitionVersion, long companyGroupId)
		throws PortalException {

		if (contains(
				permissionChecker, kaleoDefinitionVersion, ActionKeys.DELETE) ||
			contains(
				permissionChecker, kaleoDefinitionVersion,
				ActionKeys.PERMISSIONS) ||
			contains(
				permissionChecker, kaleoDefinitionVersion, ActionKeys.UPDATE) ||
			contains(
				permissionChecker, kaleoDefinitionVersion, ActionKeys.VIEW) ||
			KaleoDesignerPermission.contains(
				permissionChecker, companyGroupId, ActionKeys.VIEW)) {

			return true;
		}

		return false;
	}

	@Reference(
		target = "(model.class.name=com.liferay.portal.workflow.kaleo.model.KaleoDefinitionVersion)",
		unbind = "-"
	)
	protected void setModelPermissionChecker(
		ModelResourcePermission<KaleoDefinitionVersion>
			modelResourcePermission) {

		_kaleoDefinitionVersionModelResourcePermission =
			modelResourcePermission;
	}

	private static ModelResourcePermission<KaleoDefinitionVersion>
		_kaleoDefinitionVersionModelResourcePermission;

}