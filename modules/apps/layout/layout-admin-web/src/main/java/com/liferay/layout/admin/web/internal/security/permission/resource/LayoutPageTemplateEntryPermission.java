/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.security.permission.resource;

import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class LayoutPageTemplateEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, long layoutPageTemplateEntryId,
			String actionId)
		throws PortalException {

		_layoutPageTemplateEntryModelResourcePermission.check(
			permissionChecker, layoutPageTemplateEntryId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			LayoutPageTemplateEntry layoutPageTemplateEntry, String actionId)
		throws PortalException {

		return _layoutPageTemplateEntryModelResourcePermission.contains(
			permissionChecker, layoutPageTemplateEntry, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long layoutPageTemplateEntryId,
			String actionId)
		throws PortalException {

		return _layoutPageTemplateEntryModelResourcePermission.contains(
			permissionChecker, layoutPageTemplateEntryId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.layout.page.template.model.LayoutPageTemplateEntry)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<LayoutPageTemplateEntry>
			modelResourcePermission) {

		_layoutPageTemplateEntryModelResourcePermission =
			modelResourcePermission;
	}

	private static ModelResourcePermission<LayoutPageTemplateEntry>
		_layoutPageTemplateEntryModelResourcePermission;

}