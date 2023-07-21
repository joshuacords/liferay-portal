/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phytohormone.internal.permission;

import com.liferay.osb.koroneiki.phytohormone.model.EntitlementDefinition;
import com.liferay.osb.koroneiki.phytohormone.permission.EntitlementDefinitionPermission;
import com.liferay.osb.koroneiki.phytohormone.service.EntitlementDefinitionLocalService;
import com.liferay.osb.koroneiki.root.permission.ModelPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	service = {EntitlementDefinitionPermission.class, ModelPermission.class}
)
public class EntitlementDefinitionPermissionImpl
	implements EntitlementDefinitionPermission, ModelPermission {

	public static final String RESOURCE_NAME_ENTITLEMENT_DEFINITIONS =
		"com.liferay.osb.koroneiki.phytohormone.entitlement.definitions";

	@Override
	public void check(
			PermissionChecker permissionChecker,
			EntitlementDefinition entitlementDefinition, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, entitlementDefinition, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, EntitlementDefinition.class.getName(),
				entitlementDefinition.getEntitlementDefinitionId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long entitlementDefinitionId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, entitlementDefinitionId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, EntitlementDefinition.class.getName(),
				entitlementDefinitionId, actionId);
		}
	}

	@Override
	public void check(PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, RESOURCE_NAME_ENTITLEMENT_DEFINITIONS, 0,
				actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			EntitlementDefinition entitlementDefinition, String actionId)
		throws PortalException {

		if (permissionChecker.hasOwnerPermission(
				entitlementDefinition.getCompanyId(),
				EntitlementDefinition.class.getName(),
				entitlementDefinition.getEntitlementDefinitionId(),
				entitlementDefinition.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			0, EntitlementDefinition.class.getName(),
			entitlementDefinition.getEntitlementDefinitionId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long entitlementDefinitionId,
			String actionId)
		throws PortalException {

		if (contains(
				permissionChecker,
				_entitlementDefinitionLocalService.getEntitlementDefinition(
					entitlementDefinitionId),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			long[] entitlementDefinitionIds, String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(entitlementDefinitionIds)) {
			return false;
		}

		for (long entitlementDefinitionId : entitlementDefinitionIds) {
			if (!contains(
					permissionChecker, entitlementDefinitionId, actionId)) {

				return false;
			}
		}

		return true;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException {

		return permissionChecker.hasPermission(
			0, RESOURCE_NAME_ENTITLEMENT_DEFINITIONS,
			RESOURCE_NAME_ENTITLEMENT_DEFINITIONS, actionId);
	}

	@Override
	public String getClassName() {
		return EntitlementDefinition.class.getName();
	}

	@Reference
	private EntitlementDefinitionLocalService
		_entitlementDefinitionLocalService;

}