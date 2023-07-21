/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.sitesadmin.search;

import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;

import javax.portlet.RenderResponse;

/**
 * @author Edward Han
 */
public class UserGroupTeamChecker extends EmptyOnClickRowChecker {

	public UserGroupTeamChecker(RenderResponse renderResponse, Team team) {
		super(renderResponse);

		_team = team;
	}

	@Override
	public boolean isChecked(Object obj) {
		return hasTeamUserGroup(obj);
	}

	@Override
	public boolean isDisabled(Object obj) {
		return hasTeamUserGroup(obj);
	}

	protected boolean hasTeamUserGroup(Object obj) {
		UserGroup userGroup = (UserGroup)obj;

		try {
			return UserGroupLocalServiceUtil.hasTeamUserGroup(
				_team.getTeamId(), userGroup.getUserGroupId());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserGroupTeamChecker.class);

	private final Team _team;

}