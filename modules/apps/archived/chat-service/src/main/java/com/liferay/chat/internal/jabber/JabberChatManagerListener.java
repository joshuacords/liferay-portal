/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.chat.internal.jabber;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;

/**
 * @author Bruno Farache
 */
public class JabberChatManagerListener implements ChatManagerListener {

	public JabberChatManagerListener(long companyId, long userId) {
		_companyId = companyId;
		_userId = userId;
	}

	@Override
	public void chatCreated(Chat chat, boolean createdLocally) {
		if (!createdLocally) {
			MessageListener messageListener = new JabberMessageListener(
				_companyId, _userId);

			chat.addMessageListener(messageListener);
		}
	}

	private final long _companyId;
	private final long _userId;

}