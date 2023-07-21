/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.options.web.internal.security.permission.resource;

import com.liferay.commerce.product.model.CPOption;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = {})
public class CPOptionPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, CPOption cpOption,
			String actionId)
		throws PortalException {

		return _cpOptionModelResourcePermission.contains(
			permissionChecker, cpOption, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long cpOptionId,
			String actionId)
		throws PortalException {

		return _cpOptionModelResourcePermission.contains(
			permissionChecker, cpOptionId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.model.CPOption)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<CPOption> modelResourcePermission) {

		_cpOptionModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<CPOption>
		_cpOptionModelResourcePermission;

}