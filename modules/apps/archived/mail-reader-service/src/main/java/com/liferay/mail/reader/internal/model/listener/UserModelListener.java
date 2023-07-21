/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.internal.model.listener;

import com.liferay.mail.reader.internal.mailbox.MailboxFactoryUtil;
import com.liferay.mail.reader.mailbox.Mailbox;
import com.liferay.mail.reader.model.Account;
import com.liferay.mail.reader.service.AccountLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Scott Lee
 * @author Peter Fellwock
 */
@Component(immediate = true, service = ModelListener.class)
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterRemove(User user) {
		try {
			if (user != null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Removing mail accounts for user " + user.getUserId());
				}

				List<Account> accounts = AccountLocalServiceUtil.getAccounts(
					user.getUserId());

				for (Account account : accounts) {
					Mailbox mailbox = MailboxFactoryUtil.getMailbox(
						user, account, StringPool.BLANK);

					mailbox.deleteAccount();
				}
			}
		}
		catch (Exception exception) {
			_log.error(
				"Unable to remove mail accounts for user " + user.getUserId());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserModelListener.class);

}