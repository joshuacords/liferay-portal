/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.internal.security.permission.resource;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.commerce.data.integration.permission.CommerceDataIntegrationProcessPermission;
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
	property = "model.class.name=com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess",
	service = ModelResourcePermission.class
)
public class CommerceDataIntegrationProcessModelResourcePermission
	implements ModelResourcePermission<CommerceDataIntegrationProcess> {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceDataIntegrationProcess commerceDataIntegrationProcess,
			String actionId)
		throws PortalException {

		commerceDataIntegrationProcessPermission.check(
			permissionChecker, commerceDataIntegrationProcess, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker,
			long commerceDataIntegrationProcessId, String actionId)
		throws PortalException {

		commerceDataIntegrationProcessPermission.check(
			permissionChecker, commerceDataIntegrationProcessId, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceDataIntegrationProcess commerceDataIntegrationProcess,
			String actionId)
		throws PortalException {

		return commerceDataIntegrationProcessPermission.contains(
			permissionChecker, commerceDataIntegrationProcess, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			long commerceDataIntegrationProcessId, String actionId)
		throws PortalException {

		return commerceDataIntegrationProcessPermission.contains(
			permissionChecker, commerceDataIntegrationProcessId, actionId);
	}

	@Override
	public String getModelName() {
		return CommerceDataIntegrationProcess.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	protected CommerceDataIntegrationProcessPermission
		commerceDataIntegrationProcessPermission;

}