/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.internal.messaging;

import com.liferay.mail.reader.exception.NoSuchAccountException;
import com.liferay.mail.reader.internal.mailbox.MailboxFactoryUtil;
import com.liferay.mail.reader.mailbox.Mailbox;
import com.liferay.mail.reader.service.AccountLocalServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Scott Lee
 * @author Ryan Park
 */
public class MailSynchronizationMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");

		if (command.equals("synchronize")) {
			synchronize(message);
		}
		else if (command.equals("flag")) {
			flagMessage(message);
		}
	}

	protected void flagMessage(Message message) throws Exception {
		String password = message.getString("password");

		long messageId = message.getLong("messageId");

		if (_log.isDebugEnabled()) {
			_log.debug("Flagging message for messageId " + messageId);
		}

		if (Validator.isNull(password)) {
			return;
		}

		long userId = message.getLong("userId");
		long accountId = message.getLong("accountId");
		long folderId = message.getLong("folderId");
		int flag = message.getInteger("flag");
		boolean flagValue = message.getBoolean("flagValue");

		Mailbox mailbox = MailboxFactoryUtil.getMailbox(
			UserLocalServiceUtil.getUser(userId),
			AccountLocalServiceUtil.getAccount(accountId), password);

		mailbox.updateFlags(folderId, new long[] {messageId}, flag, flagValue);
	}

	protected void synchronize(Message message) throws Exception {
		long userId = message.getLong("userId");
		long accountId = message.getLong("accountId");
		String password = message.getString("password");
		long folderId = message.getLong("folderId");
		long messageId = message.getLong("messageId");
		int pageNumber = message.getInteger("pageNumber");
		int messagesPerPage = message.getInteger("messagesPerPage");

		if (_log.isDebugEnabled()) {
			_log.debug(
				StringBundler.concat(
					"Starting synch for accountId ", accountId, " folderId ",
					folderId, " and messageId ", messageId));
		}

		try {
			if (!password.equals(StringPool.BLANK)) {
				Mailbox mailbox = MailboxFactoryUtil.getMailbox(
					UserLocalServiceUtil.getUser(userId),
					AccountLocalServiceUtil.getAccount(accountId), password);

				if (messageId != 0) {
					mailbox.synchronizeMessage(messageId);
				}
				else if (folderId != 0) {
					if (pageNumber != 0) {
						mailbox.synchronizePage(
							folderId, pageNumber, messagesPerPage);
					}
					else {
						mailbox.synchronizeFolder(folderId);
					}
				}
				else {
					mailbox.synchronize();
				}
			}
			else {
				if (_log.isDebugEnabled()) {
					_log.debug(
						StringBundler.concat(
							"Unable to acquire synch lock for accountId ",
							accountId, " and folderId ", folderId,
							" and messageId ", messageId));
				}
			}
		}
		catch (NoSuchAccountException noSuchAccountException) {
			if (_log.isDebugEnabled()) {
				_log.debug("Skipping syncronization of accountId " + accountId);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MailSynchronizationMessageListener.class);

}