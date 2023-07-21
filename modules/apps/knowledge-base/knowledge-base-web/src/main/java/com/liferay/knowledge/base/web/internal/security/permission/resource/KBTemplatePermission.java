/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.web.internal.security.permission.resource;

import com.liferay.knowledge.base.model.KBTemplate;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class KBTemplatePermission {

	public static boolean contains(
			PermissionChecker permissionChecker, KBTemplate kbTemplate,
			String actionId)
		throws PortalException {

		return _kbTemplateModelResourcePermission.contains(
			permissionChecker, kbTemplate, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long kbTemplateId,
			String actionId)
		throws PortalException {

		return _kbTemplateModelResourcePermission.contains(
			permissionChecker, kbTemplateId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.knowledge.base.model.KBTemplate)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<KBTemplate> modelResourcePermission) {

		_kbTemplateModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<KBTemplate>
		_kbTemplateModelResourcePermission;

}