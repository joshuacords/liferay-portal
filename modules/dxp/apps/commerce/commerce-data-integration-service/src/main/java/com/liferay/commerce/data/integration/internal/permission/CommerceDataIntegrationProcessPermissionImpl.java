/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.internal.permission;

import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.commerce.data.integration.permission.CommerceDataIntegrationProcessPermission;
import com.liferay.commerce.data.integration.service.CommerceDataIntegrationProcessLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	immediate = true, service = CommerceDataIntegrationProcessPermission.class
)
public class CommerceDataIntegrationProcessPermissionImpl
	implements CommerceDataIntegrationProcessPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceDataIntegrationProcess commerceDataIntegrationProcess,
			String actionId)
		throws PortalException {

		if (!contains(
				permissionChecker, commerceDataIntegrationProcess, actionId)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker,
				CommerceDataIntegrationProcess.class.getName(),
				commerceDataIntegrationProcess.
					getCommerceDataIntegrationProcessId(),
				actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker,
			long commerceDataIntegrationProcessId, String actionId)
		throws PortalException {

		if (!contains(
				permissionChecker, commerceDataIntegrationProcessId,
				actionId)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker,
				CommerceDataIntegrationProcess.class.getName(),
				commerceDataIntegrationProcessId, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceDataIntegrationProcess commerceDataIntegrationProcess,
			String actionId)
		throws PortalException {

		if (contains(
				permissionChecker,
				commerceDataIntegrationProcess.
					getCommerceDataIntegrationProcessId(),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			long commerceDataIntegrationProcessId, String actionId)
		throws PortalException {

		CommerceDataIntegrationProcess commerceDataIntegrationProcess =
			_commerceDataIntegrationProcessLocalService.
				fetchCommerceDataIntegrationProcess(
					commerceDataIntegrationProcessId);

		if (commerceDataIntegrationProcess == null) {
			return false;
		}

		return _contains(
			permissionChecker, commerceDataIntegrationProcess, actionId);
	}

	private boolean _contains(
			PermissionChecker permissionChecker,
			CommerceDataIntegrationProcess commerceDataIntegrationProcess,
			String actionId)
		throws PortalException {

		if (permissionChecker.hasOwnerPermission(
				commerceDataIntegrationProcess.getCompanyId(),
				CommerceDataIntegrationProcess.class.getName(),
				commerceDataIntegrationProcess.
					getCommerceDataIntegrationProcessId(),
				commerceDataIntegrationProcess.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			0, CommerceDataIntegrationProcess.class.getName(),
			commerceDataIntegrationProcess.
				getCommerceDataIntegrationProcessId(),
			actionId);
	}

	@Reference
	private CommerceDataIntegrationProcessLocalService
		_commerceDataIntegrationProcessLocalService;

}