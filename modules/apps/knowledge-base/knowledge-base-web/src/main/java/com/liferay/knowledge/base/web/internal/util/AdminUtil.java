/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.web.internal.util;

import com.liferay.knowledge.base.util.AdminHelper;
import com.liferay.portal.kernel.diff.DiffVersionsInfo;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lance Ji
 */
@Component(service = {})
public class AdminUtil {

	public static DiffVersionsInfo getDiffVersionsInfo(
		long groupId, long kbArticleResourcePrimKey, int sourceVersion,
		int targetVersion) {

		return _adminHelper.getDiffVersionsInfo(
			groupId, kbArticleResourcePrimKey, sourceVersion, targetVersion);
	}

	public static String[] unescapeSections(String sections) {
		return _adminHelper.unescapeSections(sections);
	}

	@Reference(unbind = "-")
	protected void setAdminUtilHelper(AdminHelper adminHelper) {
		_adminHelper = adminHelper;
	}

	private static AdminHelper _adminHelper;

}