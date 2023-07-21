/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.service.permission;

import com.liferay.knowledge.base.constants.KBActionKeys;
import com.liferay.knowledge.base.constants.KBArticleConstants;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.service.KBArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author     Adolfo Pérez
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class SuggestionPermission {

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId,
			KBArticle kbArticle, String actionId)
		throws PortalException {

		if (!actionId.equals(KBActionKeys.VIEW_SUGGESTIONS)) {
			throw new IllegalArgumentException(
				"Suggestions only support the " +
					KBActionKeys.VIEW_SUGGESTIONS + " permission");
		}

		if (AdminPermission.contains(
				permissionChecker, groupId, KBActionKeys.VIEW_SUGGESTIONS) ||
			KBArticlePermission.contains(
				permissionChecker, kbArticle, KBActionKeys.UPDATE)) {

			return true;
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long groupId, String className,
			long classPK, String actionId)
		throws PortalException {

		if (!className.equals(KBArticleConstants.getClassName())) {
			throw new IllegalArgumentException(
				"Only KB articles support suggestions");
		}

		KBArticle kbArticle = KBArticleLocalServiceUtil.fetchKBArticle(classPK);

		if (kbArticle != null) {
			kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
				kbArticle.getResourcePrimKey(), WorkflowConstants.STATUS_ANY);
		}
		else {
			kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
				classPK, WorkflowConstants.STATUS_ANY);
		}

		return contains(permissionChecker, groupId, kbArticle, actionId);
	}

}