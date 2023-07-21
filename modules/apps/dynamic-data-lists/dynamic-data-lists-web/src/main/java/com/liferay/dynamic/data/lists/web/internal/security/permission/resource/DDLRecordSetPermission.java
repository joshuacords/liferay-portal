/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.web.internal.security.permission.resource;

import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class DDLRecordSetPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, DDLRecordSet recordSet,
			String actionId)
		throws PortalException {

		return _ddlRecordSetModelResourcePermission.contains(
			permissionChecker, recordSet, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long recordSetId,
			String actionId)
		throws PortalException {

		return _ddlRecordSetModelResourcePermission.contains(
			permissionChecker, recordSetId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.dynamic.data.lists.model.DDLRecordSet)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<DDLRecordSet> modelResourcePermission) {

		_ddlRecordSetModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<DDLRecordSet>
		_ddlRecordSetModelResourcePermission;

}