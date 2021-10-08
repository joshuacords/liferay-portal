/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.spi.model.permission;

import com.liferay.petra.function.UnsafeFunction;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Joshua Cords
 */
public class DynamicInheritanceRoleSetContributor
	<C extends GroupedModel, P extends GroupedModel>
		implements SearchPermissionDefinition.RoleSetContributor<C> {

	public DynamicInheritanceRoleSetContributor(
		ModelResourcePermission<P> parentModelResourcePermission,
		UnsafeFunction<C, P, ? extends PortalException>
			fetchParentUnsafeFunction,
		boolean checkParentAccess,
		ResourcePermissionLocalService resourcePermissionLocalService,
		RoleLocalService roleLocalService) {

		_parentModelResourcePermission = Objects.requireNonNull(
			parentModelResourcePermission);
		_fetchParentUnsafeFunction = Objects.requireNonNull(
			fetchParentUnsafeFunction);
		_checkParentAccess = checkParentAccess;
		_resourcePermissionLocalService = resourcePermissionLocalService;
		_roleLocalService = roleLocalService;
		_roleSetContributorHelper = new RoleSetContributorHelper(
			_resourcePermissionLocalService, _roleLocalService);

		_parentDynamicInheritanceRoleSetContributor = this;

		_portletResourcePermission = Objects.requireNonNull(
			parentModelResourcePermission.getPortletResourcePermission());
	}

	public DynamicInheritanceRoleSetContributor(
		ModelResourcePermission<P> parentModelResourcePermission,
		UnsafeFunction<C, P, ? extends PortalException>
			fetchParentUnsafeFunction,
		boolean checkParentAccess,
		ResourcePermissionLocalService resourcePermissionLocalService,
		RoleLocalService roleLocalService,
		DynamicInheritanceRoleSetContributor
			parentDynamicInheritanceRoleSetContributor) {

		_parentModelResourcePermission = Objects.requireNonNull(
			parentModelResourcePermission);
		_fetchParentUnsafeFunction = Objects.requireNonNull(
			fetchParentUnsafeFunction);
		_checkParentAccess = checkParentAccess;
		_parentDynamicInheritanceRoleSetContributor =
			parentDynamicInheritanceRoleSetContributor;

		_resourcePermissionLocalService = resourcePermissionLocalService;
		_roleLocalService = roleLocalService;
		_roleSetContributorHelper = new RoleSetContributorHelper(
			_resourcePermissionLocalService, _roleLocalService);

		_portletResourcePermission = Objects.requireNonNull(
			parentModelResourcePermission.getPortletResourcePermission());
	}

	@Override
	public void apply(
			RoleSetContributorContext roleSetContributorContext, C child,
			long resourcePrimKey)
		throws PortalException {

		P parent = _fetchParentUnsafeFunction.apply(child);

		List<Role> roles = _resourcePermissionLocalService.getRoles(
			roleSetContributorContext.getCompanyId(), child.getModelClassName(),
			ResourceConstants.SCOPE_INDIVIDUAL, Long.toString(resourcePrimKey),
			ActionKeys.VIEW);

		_roleSetContributorHelper.assignRolesAsIndividualViewRoleIdSets(
			roleSetContributorContext, child.getModelClassName(),
			resourcePrimKey, roles);

		if (parent == null) {
			return;
		}

		if (_checkParentAccess) {
			_applyAccessRoles(
				child, parent, resourcePrimKey, roleSetContributorContext,
				roles);
		}

		_parentDynamicInheritanceRoleSetContributor.apply(
			roleSetContributorContext, parent,
			Long.parseLong(String.valueOf(parent.getPrimaryKeyObj()))); //make sure PrimaryKeyObj works for all asset parents

	}

	private void _applyAccessRoles(
			C child, P parent, long resourcePrimKey,
			RoleSetContributorContext roleSetContributorContext,
			List<Role> viewRoles)
		throws PortalException {

		List<Role> accessRoles = _resourcePermissionLocalService.getRoles(
			roleSetContributorContext.getCompanyId(),
			parent.getModelClassName(), ResourceConstants.SCOPE_INDIVIDUAL,
			String.valueOf(parent.getPrimaryKeyObj()), ActionKeys.ACCESS);

		if (!roleSetContributorContext.accessAssigned()) {

			if(accessRoles.isEmpty()) {
				_roleSetContributorHelper.
					assignRolesAsIndividualAccessRoleIdSets(
						roleSetContributorContext, child.getModelClassName(),
						resourcePrimKey, new ArrayList<>());
			}
			else {
				_roleSetContributorHelper.
					assignRolesAsIndividualAccessRoleIdSets(
						roleSetContributorContext, child.getModelClassName(),
						resourcePrimKey, viewRoles);
			}
		}

		_roleSetContributorHelper.assignRolesAsIndividualAccessRoleIdSets(
			roleSetContributorContext, parent.getModelClassName(),
			GetterUtil.getLong(parent.getPrimaryKeyObj()), accessRoles);
	}

	private final DynamicInheritanceRoleSetContributor
		_parentDynamicInheritanceRoleSetContributor;
	private final boolean _checkParentAccess;
	private final UnsafeFunction<C, P, ? extends PortalException>
		_fetchParentUnsafeFunction;
	private final ModelResourcePermission<P> _parentModelResourcePermission;
	private final PortletResourcePermission _portletResourcePermission;
	private final ResourcePermissionLocalService
		_resourcePermissionLocalService;
	private final RoleLocalService _roleLocalService;
	private final RoleSetContributorHelper _roleSetContributorHelper;

}