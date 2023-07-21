/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.security.permission.resource;

import com.liferay.journal.model.JournalFeed;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Preston Crary
 */
@Component(immediate = true, service = {})
public class JournalFeedPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, JournalFeed feed,
			String actionId)
		throws PortalException {

		return _journalFeedModelResourcePermission.contains(
			permissionChecker, feed, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long feedId, String actionId)
		throws PortalException {

		return _journalFeedModelResourcePermission.contains(
			permissionChecker, feedId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferay.journal.model.JournalFeed)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<JournalFeed> modelResourcePermission) {

		_journalFeedModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<JournalFeed>
		_journalFeedModelResourcePermission;

}