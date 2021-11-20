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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;

import java.util.ArrayList;
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

		_portletResourcePermission = Objects.requireNonNull(
			parentModelResourcePermission.getPortletResourcePermission());

		_setParentContributor(this);
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

		this(
			parentModelResourcePermission, fetchParentUnsafeFunction,
			checkParentAccess, resourcePermissionLocalService,
			roleLocalService);

		_setParentContributor(parentDynamicInheritanceRoleSetContributor);
	}

	public void apply(
			RoleSetContributorContext roleSetContributorContext,
			DynamicInheritanceRoleSetContributorContext
				dynamicInheritanceRoleSetContributorContext,
			C child, String resourcePrimKey)
		throws PortalException {

		P parent = _fetchParentUnsafeFunction.apply(child);

		List<Role> viewRoles = _resourcePermissionLocalService.getRoles(
			dynamicInheritanceRoleSetContributorContext.getCompanyId(),
			child.getModelClassName(), ResourceConstants.SCOPE_INDIVIDUAL,
			String.valueOf(resourcePrimKey), ActionKeys.VIEW);

		dynamicInheritanceRoleSetContributorContext.addViewRoleIdLevel(
			_roleSetContributorHelper.createRoleIdSet(
				dynamicInheritanceRoleSetContributorContext.getCompanyId(),
				dynamicInheritanceRoleSetContributorContext.getGroupId(),
				child.getModelClassName(), resourcePrimKey, viewRoles));

		if (parent == null) {
			for (Set<String> roleIdSet :
					dynamicInheritanceRoleSetContributorContext.
						getCombinedPermissionRoleIdSets()) {

				roleSetContributorContext.addPermissionRoleIdSet(roleIdSet);
			}

			return;
		}

		if (_checkParentAccess) {
			List<Role> accessRoles = _resourcePermissionLocalService.getRoles(
				dynamicInheritanceRoleSetContributorContext.getCompanyId(),
				parent.getModelClassName(), ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(parent.getPrimaryKeyObj()), ActionKeys.ACCESS);

			if (!dynamicInheritanceRoleSetContributorContext.accessAssigned()) {
				if (accessRoles.isEmpty()) {
					dynamicInheritanceRoleSetContributorContext.
						addAccessRoleIdLevel(
							_roleSetContributorHelper.createRoleIdSet(
								dynamicInheritanceRoleSetContributorContext.
									getCompanyId(),
								dynamicInheritanceRoleSetContributorContext.
									getGroupId(),
								child.getModelClassName(), resourcePrimKey,
								new ArrayList<>()));
				}
				else {
					dynamicInheritanceRoleSetContributorContext.
						addAccessRoleIdLevel(
							_roleSetContributorHelper.createRoleIdSet(
								dynamicInheritanceRoleSetContributorContext.
									getCompanyId(),
								dynamicInheritanceRoleSetContributorContext.
									getGroupId(),
								child.getModelClassName(), resourcePrimKey,
								viewRoles));
				}
			}

			dynamicInheritanceRoleSetContributorContext.addAccessRoleIdLevel(
				_roleSetContributorHelper.createRoleIdSet(
					dynamicInheritanceRoleSetContributorContext.getCompanyId(),
					dynamicInheritanceRoleSetContributorContext.getGroupId(),
					parent.getModelClassName(),
					String.valueOf(parent.getPrimaryKeyObj()), accessRoles));
		}

		_parentDynamicInheritanceRoleSetContributor.apply(
			roleSetContributorContext,
			dynamicInheritanceRoleSetContributorContext, parent,
			String.valueOf(parent.getPrimaryKeyObj())); //make sure PrimaryKeyObj works for all asset parents

	}

	@Override
	public void apply(RoleSetContributorContext<C> roleSetContributorContext)
		throws PortalException {

		Role guestRole = _roleLocalService.getRole(
			roleSetContributorContext.getCompanyId(), RoleConstants.GUEST);

		Role ownerRole = _roleLocalService.getRole(
			roleSetContributorContext.getCompanyId(), RoleConstants.OWNER);

		DynamicInheritanceRoleSetContributorContext
			dynamicInheritanceRoleSetContributorContext =
				new DynamicInheritanceRoleSetContributorContext(
					roleSetContributorContext,
					String.valueOf(guestRole.getRoleId()),
					String.valueOf(ownerRole.getRoleId()));

		apply(
			roleSetContributorContext,
			dynamicInheritanceRoleSetContributorContext,
			roleSetContributorContext.getModel(),
			roleSetContributorContext.getResourcePrimKey());
	}

	private void _setParentContributor(
		DynamicInheritanceRoleSetContributor
			dynamicInheritanceRoleSetContributor) {

		_parentDynamicInheritanceRoleSetContributor =
			dynamicInheritanceRoleSetContributor;
	}

	private final boolean _checkParentAccess;
	private final UnsafeFunction<C, P, ? extends PortalException>
		_fetchParentUnsafeFunction;
	private DynamicInheritanceRoleSetContributor
		_parentDynamicInheritanceRoleSetContributor;
	private final ModelResourcePermission<P> _parentModelResourcePermission;
	private final PortletResourcePermission _portletResourcePermission;
	private final ResourcePermissionLocalService
		_resourcePermissionLocalService;
	private final RoleLocalService _roleLocalService;
	private final RoleSetContributorHelper _roleSetContributorHelper;

}