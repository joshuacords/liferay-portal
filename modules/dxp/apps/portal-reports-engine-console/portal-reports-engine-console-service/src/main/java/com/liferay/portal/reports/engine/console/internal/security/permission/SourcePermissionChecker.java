/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.reports.engine.console.internal.security.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.reports.engine.console.model.Source;
import com.liferay.portal.reports.engine.console.service.SourceLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author     Michael C. Han
 * @author     Gavin Wan
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.portal.reports.engine.console.model.Source",
	service = BaseModelPermissionChecker.class
)
@Deprecated
public class SourcePermissionChecker implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, long sourceId, String actionId)
		throws PortalException {

		_sourceModelResourcePermission.check(
			permissionChecker, sourceId, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, Source source, String actionId)
		throws PortalException {

		_sourceModelResourcePermission.check(
			permissionChecker, source, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long sourceId, String actionId)
		throws PortalException {

		return _sourceModelResourcePermission.contains(
			permissionChecker, sourceId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, Source source, String actionId)
		throws PortalException {

		return _sourceModelResourcePermission.contains(
			permissionChecker, source, actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		_sourceModelResourcePermission.check(
			permissionChecker, primaryKey, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.portal.reports.engine.console.model.Source)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<Source> modelResourcePermission) {

		_sourceModelResourcePermission = modelResourcePermission;
	}

	protected void setSourceLocalService(
		SourceLocalService sourceLocalService) {
	}

	private static ModelResourcePermission<Source>
		_sourceModelResourcePermission;

}