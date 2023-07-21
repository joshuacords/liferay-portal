/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.SessionAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.notifications.ChannelException;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpSession;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class ChannelSessionDestroyAction extends SessionAction {

	@Override
	public void run(HttpSession session) {
		User user = null;

		try {
			user = (User)session.getAttribute(WebKeys.USER);
		}
		catch (IllegalStateException illegalStateException) {
			return;
		}

		try {
			if (user == null) {
				Long userId = (Long)session.getAttribute(WebKeys.USER_ID);

				if (userId != null) {
					user = UserLocalServiceUtil.getUser(userId);
				}
			}

			if ((user == null) || user.isDefaultUser()) {
				return;
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Destroying channel " + user.getUserId());
			}

			try {
				ChannelHubManagerUtil.destroyChannel(
					user.getCompanyId(), user.getUserId());
			}
			catch (ChannelException channelException) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"User channel " + user.getUserId() +
							" is already unregistered",
						channelException);
				}
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ChannelSessionDestroyAction.class);

}