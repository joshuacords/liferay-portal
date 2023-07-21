/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.sitesadmin.search;

import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.membershippolicy.SiteMembershipPolicyUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import javax.portlet.RenderResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class SiteMembershipChecker extends RowChecker {

	public SiteMembershipChecker(RenderResponse renderResponse, Group group) {
		super(renderResponse);

		_group = group;
	}

	@Override
	public boolean isChecked(Object obj) {
		User user = null;

		if (obj instanceof User) {
			user = (User)obj;
		}
		else if (obj instanceof Object[]) {
			user = (User)((Object[])obj)[0];
		}
		else {
			throw new IllegalArgumentException(obj + " is not a user");
		}

		try {
			return UserLocalServiceUtil.hasGroupUser(
				_group.getGroupId(), user.getUserId());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return false;
		}
	}

	@Override
	public boolean isDisabled(Object obj) {
		User user = (User)obj;

		try {
			if (isChecked(user)) {
				if (SiteMembershipPolicyUtil.isMembershipProtected(
						PermissionThreadLocal.getPermissionChecker(),
						user.getUserId(), _group.getGroupId()) ||
					SiteMembershipPolicyUtil.isMembershipRequired(
						user.getUserId(), _group.getGroupId())) {

					return true;
				}
			}
			else {
				if (!SiteMembershipPolicyUtil.isMembershipAllowed(
						user.getUserId(), _group.getGroupId())) {

					return true;
				}
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return super.isDisabled(obj);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SiteMembershipChecker.class);

	private final Group _group;

}