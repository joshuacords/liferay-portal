/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.data.engine.rest.internal.security.permission.resource;

import com.liferay.data.engine.rest.internal.constants.DataLayoutConstants;
import com.liferay.data.engine.rest.internal.model.InternalDataLayout;
import com.liferay.dynamic.data.mapping.model.DDMStructureLayout;
import com.liferay.dynamic.data.mapping.service.DDMStructureLayoutLocalService;
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
	property = "model.class.name=" + DataLayoutConstants.RESOURCE_NAME,
	service = ModelResourcePermission.class
)
public class InternalDataLayoutModelResourcePermission
	implements ModelResourcePermission<InternalDataLayout> {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			InternalDataLayout internalDataLayout, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, internalDataLayout, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, DataLayoutConstants.RESOURCE_NAME,
				(long)internalDataLayout.getPrimaryKeyObj(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long primaryKey,
			String actionId)
		throws PortalException {

		InternalDataLayout internalDataLayout = new InternalDataLayout();

		internalDataLayout.setPrimaryKeyObj(primaryKey);

		check(permissionChecker, internalDataLayout, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			InternalDataLayout internalDataLayout, String actionId)
		throws PortalException {

		DDMStructureLayout ddmStructureLayout =
			ddmStructureLayoutLocalService.getStructureLayout(
				(long)internalDataLayout.getPrimaryKeyObj());

		if (permissionChecker.hasOwnerPermission(
				ddmStructureLayout.getCompanyId(),
				InternalDataLayout.class.getName(),
				(long)internalDataLayout.getPrimaryKeyObj(),
				ddmStructureLayout.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			ddmStructureLayout.getGroupId(), InternalDataLayout.class.getName(),
			(long)internalDataLayout.getPrimaryKeyObj(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long primaryKey,
			String actionId)
		throws PortalException {

		InternalDataLayout internalDataLayout = new InternalDataLayout();

		internalDataLayout.setPrimaryKeyObj(primaryKey);

		return contains(permissionChecker, internalDataLayout, actionId);
	}

	@Override
	public String getModelName() {
		return InternalDataLayout.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	protected DDMStructureLayoutLocalService ddmStructureLayoutLocalService;

}