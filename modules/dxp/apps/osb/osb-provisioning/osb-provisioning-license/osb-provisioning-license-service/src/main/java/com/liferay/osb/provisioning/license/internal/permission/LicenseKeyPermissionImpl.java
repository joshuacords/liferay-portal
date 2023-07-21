/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.internal.permission;

import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.constants.RoleConstants;
import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.license.permission.LicenseKeyPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = LicenseKeyPermission.class)
public class LicenseKeyPermissionImpl implements LicenseKeyPermission {

	@Override
	public void check(PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, LicenseKey.class.getName(), actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (permissionChecker.isOmniadmin()) {
			return true;
		}

		if (_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.PROVISIONING_ACCOUNT_WORKER, false) &&
			ArrayUtil.contains(
				_PROVISIONING_ACCOUNT_WORKER_ACTION_IDS, actionId)) {

			return true;
		}

		if (_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.PROVISIONING_ADMIN, false)) {

			return true;
		}

		if (_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.PROVISIONING_CONTACT_WORKER, false) &&
			ArrayUtil.contains(
				_PROVISIONING_CONTACT_WORKER_ACTION_IDS, actionId)) {

			return true;
		}

		if (_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.PROVISIONING_WATCHER, false) &&
			ArrayUtil.contains(_PROVISIONING_WATCHER_ACTION_IDS, actionId)) {

			return true;
		}

		if (_roleLocalService.hasUserRole(
				permissionChecker.getUserId(), permissionChecker.getCompanyId(),
				RoleConstants.PROVISIONING_WORKER, false) &&
			ArrayUtil.contains(_PROVISIONING_WORKER_ACTION_IDS, actionId)) {

			return true;
		}

		return false;
	}

	private static final String[] _PROVISIONING_ACCOUNT_WORKER_ACTION_IDS = {
		ProvisioningActionKeys.MANAGE_LICENSE_KEYS, ProvisioningActionKeys.VIEW
	};

	private static final String[] _PROVISIONING_CONTACT_WORKER_ACTION_IDS = {
		ProvisioningActionKeys.VIEW
	};

	private static final String[] _PROVISIONING_WATCHER_ACTION_IDS = {
		ProvisioningActionKeys.VIEW
	};

	private static final String[] _PROVISIONING_WORKER_ACTION_IDS = {
		ProvisioningActionKeys.MANAGE_LICENSE_KEYS, ProvisioningActionKeys.VIEW
	};

	@Reference
	private RoleLocalService _roleLocalService;

}