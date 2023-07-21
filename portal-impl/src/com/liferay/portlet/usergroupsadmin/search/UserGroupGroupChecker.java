/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.usergroupsadmin.search;

import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;

import javax.portlet.RenderResponse;

/**
 * @author Charles May
 */
public class UserGroupGroupChecker extends RowChecker {

	public UserGroupGroupChecker(RenderResponse renderResponse, Group group) {
		super(renderResponse);

		_group = group;
	}

	@Override
	public boolean isChecked(Object obj) {
		UserGroup userGroup = (UserGroup)obj;

		try {
			return UserGroupLocalServiceUtil.hasGroupUserGroup(
				_group.getGroupId(), userGroup.getUserGroupId());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserGroupGroupChecker.class);

	private final Group _group;

}