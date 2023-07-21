/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.chat.internal.model.listener;

import com.liferay.chat.internal.jabber.JabberUtil;
import com.liferay.chat.model.Status;
import com.liferay.chat.service.EntryLocalServiceUtil;
import com.liferay.chat.service.StatusLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;

import org.osgi.service.component.annotations.Component;

/**
 * @author Scott Lee
 * @author Bruno Farache
 * @author Peter Fellwock
 */
@Component(enabled = false, immediate = true, service = ModelListener.class)
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterRemove(User user) {
		try {
			if (user != null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Removing chat entries and status for user " +
							user.getUserId());
				}

				EntryLocalServiceUtil.deleteEntries(user.getUserId());

				Status status = StatusLocalServiceUtil.getUserStatus(
					user.getUserId());

				if (status != null) {
					StatusLocalServiceUtil.deleteStatus(status);
				}
			}
		}
		catch (Exception exception) {
			_log.error(
				"Unable to remove chat entries and status for user " +
					user.getUserId());
		}
	}

	@Override
	public void onAfterUpdate(User user) {
		JabberUtil.updatePassword(
			user.getUserId(), user.getPasswordUnencrypted());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserModelListener.class);

}