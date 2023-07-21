/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.security.permission.resource;

import com.liferay.layout.page.template.model.LayoutPageTemplateCollection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class LayoutPageTemplateCollectionPermission {

	public static boolean contains(
			PermissionChecker permissionChecker,
			LayoutPageTemplateCollection layoutPageTemplateCollection,
			String actionId)
		throws PortalException {

		return _layoutPageTemplateCollectionModelResourcePermission.contains(
			permissionChecker, layoutPageTemplateCollection, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			long layoutPageTemplateCollectionId, String actionId)
		throws PortalException {

		return _layoutPageTemplateCollectionModelResourcePermission.contains(
			permissionChecker, layoutPageTemplateCollectionId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.layout.page.template.model.LayoutPageTemplateCollection)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<LayoutPageTemplateCollection>
			modelResourcePermission) {

		_layoutPageTemplateCollectionModelResourcePermission =
			modelResourcePermission;
	}

	private static ModelResourcePermission<LayoutPageTemplateCollection>
		_layoutPageTemplateCollectionModelResourcePermission;

}