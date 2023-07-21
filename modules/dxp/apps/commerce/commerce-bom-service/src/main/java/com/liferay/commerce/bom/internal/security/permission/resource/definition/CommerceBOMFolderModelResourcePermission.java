/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.internal.security.permission.resource.definition;

import com.liferay.commerce.bom.model.CommerceBOMFolder;
import com.liferay.commerce.bom.permission.CommerceBOMFolderPermission;
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
	property = "model.class.name=com.liferay.commerce.bom.model.CommerceBOMFolder",
	service = ModelResourcePermission.class
)
public class CommerceBOMFolderModelResourcePermission
	implements ModelResourcePermission<CommerceBOMFolder> {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceBOMFolder commerceBOMFolder, String actionId)
		throws PortalException {

		commerceBOMFolderPermission.check(
			permissionChecker, commerceBOMFolder, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long commerceBOMFolderId,
			String actionId)
		throws PortalException {

		commerceBOMFolderPermission.check(
			permissionChecker, commerceBOMFolderId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceBOMFolder commerceBOMFolder, String actionId)
		throws PortalException {

		return commerceBOMFolderPermission.contains(
			permissionChecker, commerceBOMFolder, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long commerceBOMFolderId,
			String actionId)
		throws PortalException {

		return commerceBOMFolderPermission.contains(
			permissionChecker, commerceBOMFolderId, actionId);
	}

	@Override
	public String getModelName() {
		return CommerceBOMFolder.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	protected CommerceBOMFolderPermission commerceBOMFolderPermission;

}