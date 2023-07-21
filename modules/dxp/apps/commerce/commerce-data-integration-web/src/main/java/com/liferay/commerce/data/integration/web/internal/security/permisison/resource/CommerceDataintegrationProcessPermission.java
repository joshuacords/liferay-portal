/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.web.internal.security.permisison.resource;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = {})
public class CommerceDataintegrationProcessPermission {

	public static boolean contains(
			PermissionChecker permissionChecker,
			CommerceDataIntegrationProcess commerceDataIntegrationProcess,
			String actionId)
		throws PortalException {

		return _commerceDataIntegrationProcessModelResourcePermission.contains(
			permissionChecker,
			commerceDataIntegrationProcess.
				getCommerceDataIntegrationProcessId(),
			actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			long commerceDataIntegrationProcessId, String actionId)
		throws PortalException {

		return _commerceDataIntegrationProcessModelResourcePermission.contains(
			permissionChecker, commerceDataIntegrationProcessId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<CommerceDataIntegrationProcess>
			modelResourcePermission) {

		_commerceDataIntegrationProcessModelResourcePermission =
			modelResourcePermission;
	}

	private static ModelResourcePermission<CommerceDataIntegrationProcess>
		_commerceDataIntegrationProcessModelResourcePermission;

}