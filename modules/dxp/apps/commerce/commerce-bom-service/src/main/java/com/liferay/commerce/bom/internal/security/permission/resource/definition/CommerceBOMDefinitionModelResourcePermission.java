/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.internal.security.permission.resource.definition;

import com.liferay.commerce.bom.model.CommerceBOMDefinition;
import com.liferay.commerce.bom.permission.CommerceBOMDefinitionPermission;
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
	property = "model.class.name=com.liferay.commerce.bom.model.CommerceBOMDefinition",
	service = ModelResourcePermission.class
)
public class CommerceBOMDefinitionModelResourcePermission
	implements ModelResourcePermission<CommerceBOMDefinition> {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceBOMDefinition commerceBOMDefinition, String actionId)
		throws PortalException {

		commerceBOMDefinitionPermission.check(
			permissionChecker, commerceBOMDefinition, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commerceBOMDefinitionId,
			String actionId)
		throws PortalException {

		commerceBOMDefinitionPermission.check(
			permissionChecker, commerceBOMDefinitionId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceBOMDefinition commerceBOMDefinition, String actionId)
		throws PortalException {

		return commerceBOMDefinitionPermission.contains(
			permissionChecker, commerceBOMDefinition, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long commerceBOMDefinitionId,
			String actionId)
		throws PortalException {

		return commerceBOMDefinitionPermission.contains(
			permissionChecker, commerceBOMDefinitionId, actionId);
	}

	@Override
	public String getModelName() {
		return CommerceBOMDefinition.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	protected CommerceBOMDefinitionPermission commerceBOMDefinitionPermission;

}