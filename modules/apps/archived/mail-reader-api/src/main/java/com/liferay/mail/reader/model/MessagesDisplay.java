/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.reader.model;

import java.util.List;

/**
 * @author Scott Lee
 */
public class MessagesDisplay {

	public MessagesDisplay(
		List<Message> messages, int pageNumber, int messagesPerPage,
		int messageCount) {

		_messages = messages;
		_pageNumber = pageNumber;
		_messagesPerPage = messagesPerPage;
		_messageCount = messageCount;
	}

	public int getEndMessageNumber() {
		int messageNumber = _pageNumber * _messagesPerPage;

		if (messageNumber > _messageCount) {
			return _messageCount;
		}

		return messageNumber;
	}

	public int getMessageCount() {
		return _messageCount;
	}

	public List<Message> getMessages() {
		return _messages;
	}

	public int getPageCount() {
		return (int)Math.ceil(_messageCount / (double)_messagesPerPage);
	}

	public int getPageNumber() {
		return _pageNumber;
	}

	public int getStartMessageNumber() {
		if (_messageCount == 0) {
			return 0;
		}

		return ((_pageNumber - 1) * _messagesPerPage) + 1;
	}

	private int _messageCount;
	private final List<Message> _messages;
	private final int _messagesPerPage;
	private final int _pageNumber;

}