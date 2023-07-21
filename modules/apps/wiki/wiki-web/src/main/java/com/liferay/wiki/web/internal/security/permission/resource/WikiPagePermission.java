/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.web.internal.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.wiki.model.WikiPage;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class WikiPagePermission {

	public static boolean contains(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws PortalException {

		return _wikiPageModelResourcePermission.contains(
			permissionChecker, classPK, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, WikiPage page, String actionId)
		throws PortalException {

		return _wikiPageModelResourcePermission.contains(
			permissionChecker, page, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.wiki.model.WikiPage)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<WikiPage> modelResourcePermission) {

		_wikiPageModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<WikiPage>
		_wikiPageModelResourcePermission;

}