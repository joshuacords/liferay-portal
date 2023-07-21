/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.security.permission.resource;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class JournalArticlePermission {

	public static boolean contains(
			PermissionChecker permissionChecker, JournalArticle article,
			String actionId)
		throws PortalException {

		return _journalArticleModelResourcePermission.contains(
			permissionChecker, article, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long classPK, String actionId)
		throws PortalException {

		return _journalArticleModelResourcePermission.contains(
			permissionChecker, classPK, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.journal.model.JournalArticle)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<JournalArticle> modelResourcePermission) {

		_journalArticleModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<JournalArticle>
		_journalArticleModelResourcePermission;

}