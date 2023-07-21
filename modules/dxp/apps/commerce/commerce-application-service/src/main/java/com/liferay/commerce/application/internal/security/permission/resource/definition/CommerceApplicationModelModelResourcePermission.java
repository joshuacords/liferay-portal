/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.application.internal.security.permission.resource.definition;

import com.liferay.commerce.application.model.CommerceApplicationModel;
import com.liferay.commerce.application.permission.CommerceApplicationModelPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.commerce.application.model.CommerceApplicationModel",
	service = ModelResourcePermission.class
)
public class CommerceApplicationModelModelResourcePermission
	implements ModelResourcePermission<CommerceApplicationModel> {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceApplicationModel commerceApplicationModel, String actionId)
		throws PortalException {

		commerceApplicationModelPermission.check(
			permissionChecker, commerceApplicationModel, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker,
			long commerceApplicationModelId, String actionId)
		throws PortalException {

		commerceApplicationModelPermission.check(
			permissionChecker, commerceApplicationModelId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceApplicationModel commerceApplicationModel, String actionId)
		throws PortalException {

		return commerceApplicationModelPermission.contains(
			permissionChecker, commerceApplicationModel, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			long commerceApplicationModelId, String actionId)
		throws PortalException {

		return commerceApplicationModelPermission.contains(
			permissionChecker, commerceApplicationModelId, actionId);
	}

	@Override
	public String getModelName() {
		return CommerceApplicationModel.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	protected CommerceApplicationModelPermission
		commerceApplicationModelPermission;

}