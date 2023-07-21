/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.web.internal.permission;

import com.liferay.osb.provisioning.constants.ProvisioningActionKeys;
import com.liferay.osb.provisioning.constants.RoleConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = {})
public class AccountPermissionChecker {

	public static boolean contains(
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

	@Reference(unbind = "-")
	protected void setRoleLocalService(RoleLocalService roleLocalService) {
		_roleLocalService = roleLocalService;
	}

	private static final String[] _PROVISIONING_ACCOUNT_WORKER_ACTION_IDS = {
		ProvisioningActionKeys.ASSIGN_CONTACTS,
		ProvisioningActionKeys.MANAGE_ACCOUNTS,
		ProvisioningActionKeys.UPDATE_EXTERNAL_LINKS,
		ProvisioningActionKeys.UPDATE_INSTRUCTIONS,
		ProvisioningActionKeys.UPDATE_LANGUAGE_ID,
		ProvisioningActionKeys.UPDATE_NOTES,
		ProvisioningActionKeys.UPDATE_SALES_INFO
	};

	private static final String[] _PROVISIONING_CONTACT_WORKER_ACTION_IDS = {
		ProvisioningActionKeys.ASSIGN_CONTACTS
	};

	private static final String[] _PROVISIONING_WATCHER_ACTION_IDS = {
		ProvisioningActionKeys.UPDATE_INSTRUCTIONS,
		ProvisioningActionKeys.UPDATE_NOTES,
		ProvisioningActionKeys.UPDATE_SALES_INFO
	};

	private static final String[] _PROVISIONING_WORKER_ACTION_IDS = {
		ProvisioningActionKeys.ASSIGN_CONTACTS,
		ProvisioningActionKeys.MANAGE_ACCOUNTS,
		ProvisioningActionKeys.UPDATE_INSTRUCTIONS,
		ProvisioningActionKeys.UPDATE_LANGUAGE_ID,
		ProvisioningActionKeys.UPDATE_NOTES,
		ProvisioningActionKeys.UPDATE_SALES_INFO
	};

	private static RoleLocalService _roleLocalService;

}