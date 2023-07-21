/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.web.internal.security.permission.resource;

import com.liferay.knowledge.base.model.KBComment;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class KBCommentPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, KBComment kbComment,
			String actionId)
		throws PortalException {

		return _kbCommentModelResourcePermission.contains(
			permissionChecker, kbComment, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long kbCommentId,
			String actionId)
		throws PortalException {

		return _kbCommentModelResourcePermission.contains(
			permissionChecker, kbCommentId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.knowledge.base.model.KBComment)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<KBComment> modelResourcePermission) {

		_kbCommentModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<KBComment>
		_kbCommentModelResourcePermission;

}