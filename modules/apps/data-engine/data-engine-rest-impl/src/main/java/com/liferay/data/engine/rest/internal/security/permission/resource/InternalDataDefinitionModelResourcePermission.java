/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.security.permission.resource;

import com.liferay.data.engine.rest.internal.constants.DataDefinitionConstants;
import com.liferay.data.engine.rest.internal.model.InternalDataDefinition;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Leonardo Barros
 */
@Component(
	immediate = true,
	property = "model.class.name=" + DataDefinitionConstants.RESOURCE_NAME,
	service = ModelResourcePermission.class
)
public class InternalDataDefinitionModelResourcePermission
	implements ModelResourcePermission<InternalDataDefinition> {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			InternalDataDefinition internalDataDefinition, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, internalDataDefinition, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, DataDefinitionConstants.RESOURCE_NAME,
				(long)internalDataDefinition.getPrimaryKeyObj(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long primaryKey,
			String actionId)
		throws PortalException {

		InternalDataDefinition internalDataDefinition =
			new InternalDataDefinition();

		internalDataDefinition.setPrimaryKeyObj(primaryKey);

		check(permissionChecker, internalDataDefinition, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			InternalDataDefinition internalDataDefinition, String actionId)
		throws PortalException {

		DDMStructure ddmStructure = ddmStructureLocalService.getStructure(
			(long)internalDataDefinition.getPrimaryKeyObj());

		if (permissionChecker.hasOwnerPermission(
				ddmStructure.getCompanyId(),
				InternalDataDefinition.class.getName(),
				(long)internalDataDefinition.getPrimaryKeyObj(),
				ddmStructure.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			ddmStructure.getGroupId(), InternalDataDefinition.class.getName(),
			(long)internalDataDefinition.getPrimaryKeyObj(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long primaryKey,
			String actionId)
		throws PortalException {

		InternalDataDefinition internalDataDefinition =
			new InternalDataDefinition();

		internalDataDefinition.setPrimaryKeyObj(primaryKey);

		return contains(permissionChecker, internalDataDefinition, actionId);
	}

	@Override
	public String getModelName() {
		return InternalDataDefinition.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	protected DDMStructureLocalService ddmStructureLocalService;

}