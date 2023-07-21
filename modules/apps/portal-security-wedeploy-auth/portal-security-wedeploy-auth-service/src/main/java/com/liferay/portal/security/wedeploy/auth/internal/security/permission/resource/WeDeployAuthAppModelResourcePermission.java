/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.wedeploy.auth.internal.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.security.wedeploy.auth.constants.WeDeployConstants;
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp",
	service = ModelResourcePermission.class
)
public class WeDeployAuthAppModelResourcePermission
	implements ModelResourcePermission<WeDeployAuthApp> {

	@Override
	public void check(
			PermissionChecker permissionChecker, long weDeployAuthAppId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, weDeployAuthAppId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, WeDeployAuthApp.class.getName(),
				weDeployAuthAppId, actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker,
			WeDeployAuthApp weDeployAuthApp, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, weDeployAuthApp, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, WeDeployAuthApp.class.getName(),
				weDeployAuthApp.getWeDeployAuthAppId(), actionId);
		}
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, long weDeployAuthAppId,
		String actionId) {

		return permissionChecker.hasPermission(
			null, WeDeployAuthApp.class.getName(), weDeployAuthAppId, actionId);
	}

	@Override
	public boolean contains(
		PermissionChecker permissionChecker, WeDeployAuthApp weDeployAuthApp,
		String actionId) {

		return permissionChecker.hasPermission(
			null, WeDeployAuthApp.class.getName(),
			weDeployAuthApp.getWeDeployAuthAppId(), actionId);
	}

	@Override
	public String getModelName() {
		return WeDeployAuthApp.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return _portletResourcePermission;
	}

	@Reference(
		target = "(resource.name=" + WeDeployConstants.RESOURCE_NAME + ")"
	)
	private PortletResourcePermission _portletResourcePermission;

}