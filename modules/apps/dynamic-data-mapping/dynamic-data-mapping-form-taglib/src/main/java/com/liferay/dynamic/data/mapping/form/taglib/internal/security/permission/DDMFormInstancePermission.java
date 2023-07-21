/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.form.taglib.internal.security.permission;

import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pedro Queiroz
 */
@Component(immediate = true, service = {})
public class DDMFormInstancePermission {

	public static boolean contains(
			PermissionChecker permissionChecker,
			DDMFormInstance ddmFormInstance, String actionId)
		throws PortalException {

		return _ddmFormInstanceModelResourcePermission.contains(
			permissionChecker, ddmFormInstance, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.dynamic.data.mapping.model.DDMFormInstance)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<DDMFormInstance> modelResourcePermission) {

		_ddmFormInstanceModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<DDMFormInstance>
		_ddmFormInstanceModelResourcePermission;

}