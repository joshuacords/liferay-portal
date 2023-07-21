/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.chat.internal.jabber;

import com.liferay.chat.internal.configuration.ChatGroupServiceConfiguration;
import com.liferay.chat.service.EntryLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

/**
 * @author Bruno Farache
 * @author Peter Fellwock
 */
public class JabberMessageListener implements MessageListener {

	public JabberMessageListener(long companyId, long userId) {
		_companyId = companyId;
		_userId = userId;

		_chatGroupServiceConfiguration = _getChatGroupServiceConfiguration();
	}

	@Override
	public void processMessage(Chat chat, Message message) {
		try {
			String body = message.getBody();

			if (Validator.isNull(body)) {
				return;
			}

			String from = message.getFrom();

			if (StringUtil.equalsIgnoreCase(
					JabberUtil.getResource(from),
					_chatGroupServiceConfiguration.jabberResource())) {

				return;
			}

			long fromUserId = UserLocalServiceUtil.getUserIdByScreenName(
				_companyId, JabberUtil.getScreenName(from));

			EntryLocalServiceUtil.addEntry(fromUserId, _userId, body);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private ChatGroupServiceConfiguration _getChatGroupServiceConfiguration() {
		try {
			return ConfigurationProviderUtil.getCompanyConfiguration(
				ChatGroupServiceConfiguration.class, _companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to load chat group service configuration",
				configurationException);
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JabberMessageListener.class);

	private final ChatGroupServiceConfiguration _chatGroupServiceConfiguration;
	private final long _companyId;
	private final long _userId;

}