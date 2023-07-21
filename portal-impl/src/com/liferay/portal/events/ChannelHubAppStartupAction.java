/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.notifications.DuplicateChannelHubException;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public class ChannelHubAppStartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			long companyId = GetterUtil.getLong(ids[0]);

			if (_log.isDebugEnabled()) {
				_log.debug("Creating channel hub " + companyId);
			}

			ChannelHubManagerUtil.createChannelHub(companyId);
		}
		catch (DuplicateChannelHubException duplicateChannelHubException) {
			if (_log.isWarnEnabled()) {
				_log.warn(duplicateChannelHubException.getMessage());
			}
		}
		catch (Exception exception) {
			throw new ActionException(exception);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ChannelHubAppStartupAction.class);

}