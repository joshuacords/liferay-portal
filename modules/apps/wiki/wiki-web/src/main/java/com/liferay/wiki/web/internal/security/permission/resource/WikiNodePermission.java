/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.web.internal.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.wiki.model.WikiNode;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class WikiNodePermission {

	public static boolean contains(
			PermissionChecker permissionChecker, long nodeId, String actionId)
		throws PortalException {

		return _wikiNodeModelResourcePermission.contains(
			permissionChecker, nodeId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, WikiNode node, String actionId)
		throws PortalException {

		return _wikiNodeModelResourcePermission.contains(
			permissionChecker, node, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.wiki.model.WikiNode)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<WikiNode> modelResourcePermission) {

		_wikiNodeModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<WikiNode>
		_wikiNodeModelResourcePermission;

}