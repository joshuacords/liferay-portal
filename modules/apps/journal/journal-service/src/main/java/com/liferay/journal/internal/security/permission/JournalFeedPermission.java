/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.security.permission;

import com.liferay.journal.model.JournalFeed;
import com.liferay.journal.service.JournalFeedLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author     Raymond Augé
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Component(
	property = "model.class.name=com.liferay.journal.model.JournalFeed",
	service = BaseModelPermissionChecker.class
)
@Deprecated
public class JournalFeedPermission implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, JournalFeed feed,
			String actionId)
		throws PortalException {

		_journalFeedModelResourcePermission.check(
			permissionChecker, feed, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, long id, String actionId)
		throws PortalException {

		_journalFeedModelResourcePermission.check(
			permissionChecker, id, actionId);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static void check(
			PermissionChecker permissionChecker, long groupId, String feedId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, feedId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, JournalFeed.class.getName(), feedId,
				actionId);
		}
	}

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

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, String feedId,
			String actionId)
		throws PortalException {

		return _journalFeedModelResourcePermission.contains(
			permissionChecker,
			_journalFeedLocalService.getFeed(groupId, feedId), actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		_journalFeedModelResourcePermission.check(
			permissionChecker, primaryKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setJournalArticleLocalService(
		JournalFeedLocalService journalFeedLocalService) {

		_journalFeedLocalService = journalFeedLocalService;
	}

	@Reference(
		target = "(model.class.name=com.liferay.journal.model.JournalFeed)",
		unbind = "-"
	)
	protected void setModelResourcePermission(
		ModelResourcePermission<JournalFeed> modelResourcePermission) {

		_journalFeedModelResourcePermission = modelResourcePermission;
	}

	private static JournalFeedLocalService _journalFeedLocalService;
	private static ModelResourcePermission<JournalFeed>
		_journalFeedModelResourcePermission;

}