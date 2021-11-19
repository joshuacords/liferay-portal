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

package com.liferay.portal.search.internal.permission;

import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.search.spi.model.permission.DynamicInheritanceRoleSetContributor;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Joshua Cords
 */
@Component(service = DynamicInheritanceRoleSetContributorFactory.class)
public class DynamicInheritanceRoleSetContributorFactory
	<C extends GroupedModel, P extends GroupedModel> {

	public DynamicInheritanceRoleSetContributor create(
		ModelResourcePermission<P> parentModelResourcePermission,
		UnsafeFunction<C, P, ? extends PortalException>
			fetchParentUnsafeFunction,
		boolean checkParentAccess) {

		return new DynamicInheritanceRoleSetContributor<>(
			parentModelResourcePermission, fetchParentUnsafeFunction,
			checkParentAccess, _resourcePermissionLocalService,
			_roleLocalService);
	}

	public DynamicInheritanceRoleSetContributor create(
		ModelResourcePermission<P> parentModelResourcePermission,
		UnsafeFunction<C, P, ? extends PortalException>
			fetchParentUnsafeFunction,
		boolean checkParentAccess,
		DynamicInheritanceRoleSetContributor
			dynamicInheritanceRoleSetContributor) {

		return new DynamicInheritanceRoleSetContributor<>(
			parentModelResourcePermission, fetchParentUnsafeFunction,
			checkParentAccess, _resourcePermissionLocalService,
			_roleLocalService, dynamicInheritanceRoleSetContributor);
	}

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}