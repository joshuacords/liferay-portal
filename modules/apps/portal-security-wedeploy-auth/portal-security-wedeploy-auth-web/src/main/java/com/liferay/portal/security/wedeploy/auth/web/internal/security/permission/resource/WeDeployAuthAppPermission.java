/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.wedeploy.auth.web.internal.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class WeDeployAuthAppPermission {

	public static boolean contains(
			PermissionChecker permissionChecker,
			WeDeployAuthApp weDeployAuthApp, String actionId)
		throws PortalException {

		return _weDeployAuthAppModelResourcePermission.contains(
			permissionChecker, weDeployAuthApp, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<WeDeployAuthApp> modelResourcePermission) {

		_weDeployAuthAppModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<WeDeployAuthApp>
		_weDeployAuthAppModelResourcePermission;

}